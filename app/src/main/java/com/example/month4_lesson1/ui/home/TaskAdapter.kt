package com.example.month4_lesson1.ui.home


import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.month4_lesson1.databinding.TaskItemBinding


class TaskAdapter :  RecyclerView.Adapter<TaskAdapter.ViewHolder>() {

private var taskLIst = arrayListOf<TaskModel>()

    fun addTask(taskModel: TaskModel){
        taskLIst.add(0,taskModel)
        notifyItemChanged(0)
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return  ViewHolder(TaskItemBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
    holder.bind(taskLIst[position])
    }

    override fun getItemCount(): Int = taskLIst.size


    inner class ViewHolder(private var binding: TaskItemBinding): RecyclerView.ViewHolder(binding.root){

        fun bind(taskModel: TaskModel){
            binding.tvTitle.text = taskModel.title
            binding.tvDesc.text = taskModel.description
        }


    }
}