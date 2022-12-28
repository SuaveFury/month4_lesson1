package com.example.month4_lesson1.ui.home

import android.app.AlertDialog
import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.month4_lesson1.App
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
        setHasOptionsMenu(true)


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

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.sort){
            val items = arrayOf("По дате", "По алфавиту ")
            val builder = AlertDialog.Builder(requireContext())
            with(builder){
                setTitle("Сортировать по:")
                setItems(items){dialog, which ->
                    when(which){
                        0 ->{taskAdapter.addTasks(App.db.dao().getListByDate())}
                        1 ->{taskAdapter.addTasks(App.db.dao().getListByAlphabet())}
                    }
                }
                show()
            }
        }
        return super.onOptionsItemSelected(item)

    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_home, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    private fun initViews() {
        binding.rvHome.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = taskAdapter
        }
        val listOfTasks = App.db.dao().getAllTasks()
        taskAdapter.addTasks(listOfTasks)
        }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


}
