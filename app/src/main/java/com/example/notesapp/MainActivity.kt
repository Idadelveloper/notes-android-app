package com.example.notesapp

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.notesapp.R
import com.example.notesapp.activities.AddEditActivity
import com.example.notesapp.adaptors.NoteAdaptor
import com.example.notesapp.room.Note
import com.example.notesapp.room.NoteViewModel
import com.example.notesapp.utils.Constants
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainActivity : AppCompatActivity() {
    @Suppress("UNCHECKED_CAST")
    private val factory: ViewModelProvider.Factory = object : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return NoteViewModel(application) as T
        }
    }

    private val noteViewModel: NoteViewModel by viewModels { factory }
    private lateinit var recyclerView: RecyclerView
    private lateinit var notesAdaptor: NoteAdaptor
    private lateinit var addNoteButton: FloatingActionButton
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        addNoteButton = findViewById(R.id.add_note_button)
        recyclerView = findViewById(R.id.recycler_view)
        notesAdaptor = NoteAdaptor()
        recyclerView.adapter = notesAdaptor
        recyclerView.layoutManager = LinearLayoutManager(this)

//        noteViewModel = ViewModelProvider(
//            this,
//            ViewModelProvider.AndroidViewModelFactory(application)
//        )[NoteViewModel::class.java]


        noteViewModel.allNotes.observe(this) {
            // here we can add the data to our recycler view
            notesAdaptor.setNotes(it)
        }

        val getResult =
            registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
                if (it.resultCode == Constants.REQUEST_CODE) {
                    val title = it.data?.getStringExtra(Constants.EXTRA_TITLE)
                    val descripton = it.data?.getStringExtra(Constants.EXTRA_DESCRIPTION)
                    val priority = it.data?.getIntExtra(Constants.EXTRA_PRIORITY, -1)

                    val note = Note(title!!, descripton!!, priority!!)
                    noteViewModel.addNote(note)
                }
            }

        addNoteButton.setOnClickListener {
            val intent = Intent(this@MainActivity, AddEditActivity::class.java)
            getResult.launch(intent)
        }
    }
}