package com.example.cardoverlap

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Find views
        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)
        val textEmptyCards = findViewById<TextView>(R.id.textEmptyCards)

        // List of names to display
        val names = mutableListOf(
            Name(1, "John"),
            Name(2, "Jane"),
            Name(3, "Alice"),
            Name(4, "Bob"),
            Name(5, "Charlie")
        )

        // Set up RecyclerView
        recyclerView.layoutManager = OverlappingLayoutManager(this)
        val adapter = NameAdapter(names) {
            // Show the empty text when the list is empty
            textEmptyCards.visibility = View.VISIBLE
        }
        recyclerView.adapter = adapter

        // Set up ItemTouchHelper for swipe actions
        val itemTouchHelper = ItemTouchHelper(object : ItemTouchHelper.SimpleCallback(
            0, ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT
        ) {
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                return false
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val position = viewHolder.adapterPosition
                val removedItem = names[position]
                adapter.removeItem(position)

                // Determine the delay based on swipe direction
                val delay = when (direction) {
                    ItemTouchHelper.LEFT -> 20000L // 20 seconds
                    ItemTouchHelper.RIGHT -> 10000L // 10 seconds
                    else -> 0L
                }

                // Reinsert the card back into the list after the delay
                Handler(Looper.getMainLooper()).postDelayed({
                    names.add(position, removedItem)
                    adapter.notifyItemInserted(position)
                    recyclerView.scrollToPosition(position) // Scroll to the reinserted position

                    // Hide the empty text if a card is reinserted
                    textEmptyCards.visibility = View.GONE
                }, delay)
            }
        })

        // Attach the ItemTouchHelper to the RecyclerView
        itemTouchHelper.attachToRecyclerView(recyclerView)
    }
}
