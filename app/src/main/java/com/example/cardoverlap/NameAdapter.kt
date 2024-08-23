package com.example.cardoverlap

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class NameAdapter(
    private var names: MutableList<Name>,
    private val onListEmpty: () -> Unit // Callback when the list becomes empty
) : RecyclerView.Adapter<NameAdapter.NameViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NameViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.name, parent, false)
        return NameViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: NameViewHolder, position: Int) {
        val currentName = names[position]
        holder.textViewName.text = currentName.name
    }

    override fun getItemCount() = names.size

    class NameViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val textViewName: TextView = itemView.findViewById(R.id.textViewName)
    }

    // Implementing removeItem function
    fun removeItem(position: Int) {
        names.removeAt(position)
        notifyItemRemoved(position)
        notifyItemRangeChanged(position, names.size) // Update subsequent items' positions

        // Check if the list is empty and invoke the callback
        if (names.isEmpty()) {
            onListEmpty()
        }
    }
}
