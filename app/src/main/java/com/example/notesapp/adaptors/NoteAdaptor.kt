package com.example.notesapp.adaptors

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.notesapp.R
import com.example.notesapp.room.Note

class NoteAdaptor(val context: Context, val notesList: MutableList<Note>) : RecyclerView.Adapter<NoteAdaptor.NoteViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder {
        return NoteViewHolder(LayoutInflater.from(context).inflate(R.layout.note_item, parent, false))
    }

    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {
        val note = notesList[position]
        holder.textTitle.text = note.title
        holder.textViewDescription.text = note.description
        holder.textViewPriority.text = note.priority.toString()

    }

    override fun getItemCount() = notesList.size

    inner class NoteViewHolder(view: View): RecyclerView.ViewHolder(view) {
        var textTitle: TextView = view.findViewById(R.id.text_view_title)
        var textViewDescription: TextView = view.findViewById(R.id.text_view_description)
        val textViewPriority: TextView = view.findViewById(R.id.text_view_priority)
    }
}