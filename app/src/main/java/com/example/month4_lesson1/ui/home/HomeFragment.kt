package com.example.month4_lesson1.ui.home

import android.app.AlertDialog
import android.os.Bundle
import android.view.*

import androidx.core.os.bundleOf
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
        setData()
        return binding.root
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        taskAdapter = TaskAdapter(this::onClick,this:: onLongClick)
    }

    private fun initListeners() {
        binding.fabHome.setOnClickListener{
            findNavController().navigate(R.id.newTaskFragment)
        }
    }

    private fun onClick(pos: Int){
        val task = taskAdapter.getTask(pos)
        findNavController().navigate(R.id.newTaskFragment, bundleOf(EDIT_KEY to task))
    }

    private fun onLongClick(pos: Int){
        val builder = AlertDialog.Builder(requireContext())
        with(builder){
            setTitle(getString(R.string.are_you_sure_delete))
            setPositiveButton(getString(R.string.yes)){ dialog, _ ->
                App.db.dao().deleteTask(taskAdapter.getTask(pos))
                setData()
                dialog.dismiss()

            }
            setNegativeButton(getString(R.string.no)){ dialog, which ->
                dialog.dismiss()

            }
            show()
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.sort){
            val items = arrayOf(getString(R.string.by_date), getString(R.string.by_alphabet))
            val builder = AlertDialog.Builder(requireContext())
            with(builder){
                setTitle(getString(R.string.sort_by))
                setItems(items){dialog, which ->
                    when(which){
                        0 ->{taskAdapter.addTasks(App.db.dao().getListByDate())
                        dialog.dismiss()}
                        1 ->{taskAdapter.addTasks(App.db.dao().getListByAlphabet())
                        dialog.dismiss()}
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
        }

    private fun setData(){
        val listOfTasks = App.db.dao().getAllTasks()
        taskAdapter.addTasks(listOfTasks)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
    companion object{
        const val EDIT_KEY: String = "edit"
    }
}
