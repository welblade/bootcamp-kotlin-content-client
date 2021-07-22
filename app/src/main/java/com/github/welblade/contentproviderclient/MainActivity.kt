package com.github.welblade.contentproviderclient

import android.database.Cursor
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import com.github.welblade.contentproviderclient.databinding.ActivityMainBinding
import com.github.welblade.contentproviderclient.ui.NoteAdapter

class MainActivity : AppCompatActivity() {
    lateinit var adapter: NoteAdapter
    val activityMainBinding : ActivityMainBinding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(activityMainBinding.root)
        setupListeners()
        setupRecyclerView()
    }

    private fun setupRecyclerView(){
        val cursor: Cursor? = getContentProvider()
        if(cursor != null ) {
            adapter = NoteAdapter(cursor)
            activityMainBinding.rvNote.adapter = adapter
        }
        activityMainBinding.rvNote.layoutManager = LinearLayoutManager(this)
    }

    private fun setupListeners(){
        activityMainBinding.btnRefresh.setOnClickListener {
            setupRecyclerView()
        }
    }
    private fun getContentProvider(): Cursor? {
        var cursor: Cursor? = null
        try {
            val url = "content://com.github.welblade.contentprovider.provider/notes"
            val data = Uri.parse(url)
            cursor = contentResolver.query(data, null, null, null, "title")
        }catch (err: Exception){
            Log.e("Content Provider >>>" , err.message.toString())
        }
        return cursor
    }
}