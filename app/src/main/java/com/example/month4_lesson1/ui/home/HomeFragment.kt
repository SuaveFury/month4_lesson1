package com.example.month4_lesson1.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.setFragmentResultListener
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.month4_lesson1.R
import com.example.month4_lesson1.databinding.FragmentHomeBinding


class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private lateinit var  taskAdapter: TaskAdapter

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {


        _binding = FragmentHomeBinding.inflate(inflater, container, false)

        initViews()
        initListeners()

        return binding.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        taskAdapter = TaskAdapter()
    }

    private fun initListeners() {
        binding.fabHome.setOnClickListener{
            findNavController().navigate(R.id.newTaskFragment)
        }
    }

    private fun initViews() {
        binding.rvHome.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = taskAdapter


        }



        setFragmentResultListener("new_task"){key, bundle ->

            val title = bundle.get("title")
            val description = bundle.get("desc")
            taskAdapter.addTask(TaskModel(title.toString(), description.toString()))


        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}