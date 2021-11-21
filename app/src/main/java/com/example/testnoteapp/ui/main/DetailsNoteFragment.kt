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
import com.example.testnoteapp.databinding.FragmentDetailsNoteBinding
import com.example.testnoteapp.databinding.FragmentNoteBinding
import com.example.testnoteapp.databinding.MainFragmentBinding
import kotlinx.android.synthetic.main.fragment_details_note.*
import kotlinx.android.synthetic.main.fragment_note.*
import kotlinx.android.synthetic.main.fragment_note.dateTextView
import kotlinx.android.synthetic.main.fragment_note.titleTextView
import kotlinx.android.synthetic.main.recycler_item_note.*
import java.text.DateFormat
import java.util.*

class DetailsNoteFragment : Fragment() {

    private var _binding: FragmentDetailsNoteBinding? = null
    private val binding get() = _binding!!
    private lateinit var noteBundle: Note

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDetailsNoteBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        noteBundle = arguments?.getParcelable(BUNDLE_EXTRA) ?: Note()

        binding.titleTextView.text = noteBundle.title
        binding.dateTextView.text = noteBundle.date
        binding.contentTextView.text = noteBundle.content
    }

    companion object {
        const val BUNDLE_EXTRA = "note"
        fun newInstance(bundle: Bundle): DetailsNoteFragment {
            val fragment = DetailsNoteFragment()
            fragment.arguments = bundle
            return fragment
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}