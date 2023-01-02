package com.example.month4_lesson1.ui.home


import android.app.AlertDialog
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.net.toUri
import androidx.recyclerview.widget.RecyclerView
import com.example.month4_lesson1.App
import com.example.month4_lesson1.R
import com.example.month4_lesson1.databinding.TaskItemBinding
import com.example.month4_lesson1.ui.utils.loadImage


class TaskAdapter(
    private var onClick: (Int) -> Unit,
    private var onLongClick: (Int) -> Unit
) :  RecyclerView.Adapter<TaskAdapter.ViewHolder>() {

private var taskList = arrayListOf<TaskModel>()

    fun addTasks(list: List<TaskModel>){
        taskList.clear()
        taskList.addAll(list)
        notifyDataSetChanged()
    }
     fun getTask(pos:Int): TaskModel{
        return taskList[pos]
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return  ViewHolder(TaskItemBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
    holder.bind(taskList[position])
        if (position %2 == 1){
            holder.itemView.setBackgroundResource(R.color.back)
        } else{
            holder.itemView.setBackgroundResource(R.color.back2)
        }

    }

    override fun getItemCount(): Int = taskList.size


    inner class ViewHolder(private var binding: TaskItemBinding): RecyclerView.ViewHolder(binding.root){

        fun bind(taskModel: TaskModel){
            binding.tvTitle.text = taskModel.title
            binding.tvDesc.text = taskModel.description
//            binding.imgItem.setImageURI(taskModel.imgUri.toUri())
            binding.imgItem.loadImage(taskModel.imgUri)
            itemView.setOnLongClickListener{
                onLongClick(adapterPosition)
                Toast.makeText(itemView.context, adapterPosition.toString(), Toast.LENGTH_SHORT).show()
                return@setOnLongClickListener true
            }
            itemView.setOnClickListener{
                onClick(adapterPosition)
            }
        }
    }
}