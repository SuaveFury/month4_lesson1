package com.example.month4_lesson1.ui.home


import android.app.AlertDialog
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.net.toUri
import androidx.recyclerview.widget.RecyclerView
import com.example.month4_lesson1.App
import com.example.month4_lesson1.databinding.TaskItemBinding
import com.example.month4_lesson1.ui.utils.loadImage


class TaskAdapter :  RecyclerView.Adapter<TaskAdapter.ViewHolder>() {

private var taskList = arrayListOf<TaskModel>()

    fun addTasks(list: List<TaskModel>){
        taskList.clear()
        taskList.addAll(list)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return  ViewHolder(TaskItemBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
    holder.bind(taskList[position])

    }

    override fun getItemCount(): Int = taskList.size


    inner class ViewHolder(private var binding: TaskItemBinding): RecyclerView.ViewHolder(binding.root){

        fun bind(taskModel: TaskModel){
            binding.tvTitle.text = taskModel.title
            binding.tvDesc.text = taskModel.description
//            binding.imgItem.setImageURI(taskModel.imgUri.toUri())
            binding.imgItem.loadImage(taskModel.imgUri)
            itemView.setOnLongClickListener{
                val builder = AlertDialog.Builder(itemView.context)
                with(builder){
                    setTitle("Вы точно хотите удалить '${taskModel.title}'")
                    setPositiveButton("Да"){dialog , which->
                        App.db.dao().deleteTask(taskModel)
                        taskList.clear()
                        taskList.addAll(App.db.dao().getAllTasks())
                        notifyDataSetChanged()
                    }
                    setNegativeButton("Нет"){dialog, which ->
                        dialog.dismiss()

                    }
                    show()
                }
                return@setOnLongClickListener true
            }
        }
    }
}