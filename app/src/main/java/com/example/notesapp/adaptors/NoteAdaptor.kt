package com.example.notesapp.adaptors

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.notesapp.R
import com.example.notesapp.room.Note

class NoteAdaptor(val listener: OnClickListener) :
    ListAdapter<Note, NoteAdaptor.NoteViewHolder>(DIFF_CALLBACK) {
        companion object {
            private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<Note>() {
                override fun areItemsTheSame(oldItem: Note, newItem: Note): Boolean {
                    return oldItem.id == newItem.id
                }

                override fun areContentsTheSame(oldItem: Note, newItem: Note): Boolean {
                    return oldItem == newItem
                }

            }
        }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder {
        return NoteViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.note_item, parent, false))
    }

    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {
        val note = getItem(position)
        holder.textTitle.text = note.title
        holder.textViewDescription.text = note.description
        holder.textViewPriority.text = note.priority.toString()

    }

    fun getNoteAt(position: Int) : Note {
        return getItem(position)
    }

    inner class NoteViewHolder(view: View): RecyclerView.ViewHolder(view) {
        var textTitle: TextView = view.findViewById(R.id.text_view_title)
        var textViewDescription: TextView = view.findViewById(R.id.text_view_description)
        val textViewPriority: TextView = view.findViewById(R.id.text_view_priority)

        init {
            view.setOnClickListener {
                if (adapterPosition != RecyclerView.NO_POSITION) {
                    listener.onClickItem(getItem(adapterPosition))
                }
            }
        }
    }

    interface OnClickListener {
        fun onClickItem(note: Note)
    }
}