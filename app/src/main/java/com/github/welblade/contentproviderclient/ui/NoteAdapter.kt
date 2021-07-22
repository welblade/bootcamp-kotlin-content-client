package com.github.welblade.contentproviderclient.ui

import android.database.Cursor
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.github.welblade.contentproviderclient.databinding.ItemNoteBinding

class NoteAdapter (private val mCursor: Cursor): RecyclerView.Adapter<NoteAdapter.NoteViewHolder>() {
    lateinit var itemNoteBinding: ItemNoteBinding
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder {
        itemNoteBinding = ItemNoteBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return NoteViewHolder(itemNoteBinding)
    }

    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {
        mCursor.moveToPosition(position)
        val title = mCursor.getString(mCursor.getColumnIndex("title"))
        val description = mCursor.getString(mCursor.getColumnIndex("description"))
        holder.bind(title, description)
    }

    override fun getItemCount(): Int = mCursor.count

    inner class NoteViewHolder(val item: ItemNoteBinding): RecyclerView.ViewHolder(item.root){
        fun bind(title: String, description: String){
            item.tvTitle.text = title
            item.tvDescription.text = description
        }
    }
}