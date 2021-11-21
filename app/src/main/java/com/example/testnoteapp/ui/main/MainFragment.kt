package com.example.testnoteapp.ui.main

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import android.widget.PopupMenu
import android.widget.Toast
import androidx.recyclerview.widget.ItemTouchHelper
import com.example.testnoteapp.Note
import com.example.testnoteapp.R
import com.example.testnoteapp.app.App
import com.example.testnoteapp.dataBase.RoomRepository
import com.example.testnoteapp.dataBase.RoomRepositoryImpl
import com.example.testnoteapp.databinding.FragmentDetailsNoteBinding
import com.example.testnoteapp.databinding.MainFragmentBinding
import kotlinx.android.synthetic.main.main_fragment.*

class MainFragment : Fragment() {
    private val roomRepository: RoomRepository = RoomRepositoryImpl(App.getNoteDao())

    private var _binding: MainFragmentBinding? = null
    private val binding get() = _binding!!

    companion object {
        fun newInstance() = MainFragment()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = MainFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val note = roomRepository.getAllHistory()
        recyclerView.adapter = RecyclerNoteAdapter(
            object : RecyclerNoteAdapter.OnLongListItemClickListener {
                override fun onItemClick(
                    view: View,
                    note: Note,
                    list: MutableList<Note>,
                    layoutPosition: Int
                ): Boolean {
                    showPopupMenu(view, note, list, layoutPosition)
                    return true
                }
            }, object : RecyclerNoteAdapter.OnListItemClickListener {
                override fun onItemClick(note: Note) {
                    activity?.supportFragmentManager?.apply {
                        val bundle = Bundle()
                        bundle.putParcelable(DetailsNoteFragment.BUNDLE_EXTRA, note)
                        beginTransaction()
                            .replace(R.id.container, DetailsNoteFragment.newInstance(bundle))
                            .addToBackStack("")
                            .commitAllowingStateLoss()
                    }
                }
            }, object : RecyclerNoteAdapter.DeleteNote{
                override fun delete(note: Int) {
                    roomRepository.deleteNoteById(note)
                }
            }, note
        )

        ItemTouchHelper(ItemTouchHelperCallback(recyclerView.adapter as RecyclerNoteAdapter))
            .attachToRecyclerView(recyclerView)
        recyclerActivityFAB.setOnClickListener {
            activity?.supportFragmentManager?.apply {
                beginTransaction()
                    .replace(R.id.container, NoteFragment())
                    .addToBackStack("")
                    .commitAllowingStateLoss()
            }
        }
    }

    private fun showPopupMenu(
        view: View,
        note: Note,
        list: MutableList<Note>,
        layoutPosition: Int
    ) {
        val popupMenu = PopupMenu(context, view)
        popupMenu.inflate(R.menu.popup_menu)

        popupMenu.setOnMenuItemClickListener(PopupMenu.OnMenuItemClickListener { item: MenuItem? ->
            when (item!!.itemId) {
                R.id.menuDelete -> {
                    roomRepository.deleteNoteById(note.id)
                    list.removeAt(layoutPosition)
                    recyclerView.adapter?.notifyItemRemoved(layoutPosition)

                }
                R.id.menuEdit -> {
                    Toast.makeText(context, "edit", Toast.LENGTH_SHORT).show()
                }
            }
            true
        })
        popupMenu.show()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
