package com.example.madpractical11_20012021014_1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.core.view.WindowCompat
import com.example.madpractical11_20012021014_1.databinding.ActivityNoteViewBinding

class NoteViewActivity : AppCompatActivity() {
    private lateinit var binding: ActivityNoteViewBinding
    private lateinit var note:Note
    override fun onCreate(savedInstanceState: Bundle?) {
        WindowCompat.setDecorFitsSystemWindows(window, false)
        super.onCreate(savedInstanceState)
        note = intent.getSerializableExtra("Object") as Note
        binding = ActivityNoteViewBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.toolbar)
        with(note){
            binding.noteTitle.text = this.title
            binding.noteSubtitle.text = this.subTitle
            binding.noteContent.text = this.Description
            binding.noteDate.text = this.modifiedTime
            this.calcReminder()
            if(this.isReminder)
            {
                binding.noteReminderDateTime.visibility = View.VISIBLE
                binding.noteReminderDateTime.text = this.getReminderText()
            }
            else
                binding.noteReminderDateTime.visibility = View.GONE
        }
    }
}


