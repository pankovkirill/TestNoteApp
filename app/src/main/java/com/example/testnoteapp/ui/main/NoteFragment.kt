package com.example.testnoteapp.ui.main

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.addTextChangedListener
import com.example.testnoteapp.Note
import com.example.testnoteapp.R
import com.example.testnoteapp.app.App
import com.example.testnoteapp.dataBase.RoomRepository
import com.example.testnoteapp.dataBase.RoomRepositoryImpl
import com.example.testnoteapp.databinding.FragmentNoteBinding
import com.example.testnoteapp.databinding.MainFragmentBinding
import kotlinx.android.synthetic.main.fragment_note.*
import kotlinx.android.synthetic.main.fragment_note.dateTextView
import kotlinx.android.synthetic.main.fragment_note.titleTextView
import kotlinx.android.synthetic.main.recycler_item_note.*
import java.text.DateFormat
import java.util.*

class NoteFragment : Fragment() {

    private val roomRepository: RoomRepository = RoomRepositoryImpl(App.getNoteDao())
    private val date = DateFormat.getDateInstance().format(Calendar.getInstance().getTime())
    private var _binding: FragmentNoteBinding? = null
    private val binding get() = _binding!!
    private var flag = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentNoteBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        dateTextView.text = date

        titleTextView.addTextChangedListener {
            recyclerActivityFAB.visibility = View.VISIBLE
            flag = true
        }
        contentTextView.addTextChangedListener {
            recyclerActivityFAB.visibility = View.VISIBLE
            flag = true
        }
        recyclerActivityFAB.setOnClickListener {
            if (flag)
                if (contentTextView.textSize > 0) {
                    roomRepository.saveEntity(
                        Note(
                            0,
                            "${titleTextView.text}",
                            "${contentTextView.text}",
                            "${dateTextView.text}",
                        )
                    )
                    recyclerActivityFAB.visibility = View.GONE
                }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}