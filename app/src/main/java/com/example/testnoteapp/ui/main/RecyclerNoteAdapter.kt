package com.example.testnoteapp.ui.main

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.testnoteapp.Note
import com.example.testnoteapp.R
import kotlinx.android.synthetic.main.recycler_item_note.view.*

class RecyclerNoteAdapter(
    private val onLongListItemClickListener: OnLongListItemClickListener,
    private val onListItemClickListener: OnListItemClickListener,
    private val deleteNote: DeleteNote,
    private val note: MutableList<Note>
) : RecyclerView.Adapter<RecyclerNoteAdapter.NoteViewHolder>(), ItemTouchHelperAdapter {



    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): NoteViewHolder = NoteViewHolder(
        LayoutInflater.from(parent.context)
            .inflate(R.layout.recycler_item_note, parent, false)
    )

    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {
        holder.bind(note[position])

    }

    override fun getItemCount(): Int {
        return note.size
    }

    inner class NoteViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        fun bind(notes: Note) {
            itemView.apply {
                titleTextView.text = notes.title
                dateTextView.text = notes.date
                recyclerViewItem.setOnLongClickListener {
                    onLongListItemClickListener.onItemClick(
                        itemView,
                        notes,
                        note,
                        layoutPosition
                    )
                }
                recyclerViewItem.setOnClickListener { onListItemClickListener.onItemClick(notes) }
            }
        }

        private fun removeItem() {
            note.removeAt(layoutPosition)
            notifyItemRemoved(layoutPosition)
        }
    }

    interface OnLongListItemClickListener {
        fun onItemClick(
            view: View,
            note: Note,
            list: MutableList<Note>,
            layoutPosition: Int
        ): Boolean
    }

    interface OnListItemClickListener {
        fun onItemClick(note: Note)
    }

    interface DeleteNote{
        fun delete(note: Int)
    }

    override fun onItemMove(fromPosition: Int, toPosition: Int) {
        note.removeAt(fromPosition).apply {
            note.add(if (toPosition > fromPosition) toPosition - 1 else toPosition, this)
        }
        notifyItemMoved(fromPosition, toPosition)
    }

    override fun onItemDismiss(position: Int) {
        note.removeAt(position)
        notifyItemRemoved(position)
        val notes =note[position-1].id
        deleteNote.delete(notes)
    }
}

interface ItemTouchHelperAdapter {
    fun onItemMove(fromPosition: Int, toPosition: Int)
    fun onItemDismiss(position: Int)
}