package com.example.month4_lesson1.ui.home.new_task


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.navigation.fragment.findNavController
import com.example.month4_lesson1.App
import com.example.month4_lesson1.databinding.FragmentNewTaskBinding
import com.example.month4_lesson1.ui.home.TaskModel
import com.google.android.material.datepicker.MaterialDatePicker
import java.text.SimpleDateFormat
import java.util.*


class NewTaskFragment : Fragment() {
    private lateinit var binding: FragmentNewTaskBinding
    var imgUri:String = ""
    var mGetContent: ActivityResultLauncher<String> = registerForActivityResult(
        ActivityResultContracts.GetContent(), { uri-> binding.imageTask.setImageURI(uri)
        imgUri = uri.toString()})



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentNewTaskBinding.inflate(LayoutInflater.from(context), container, false)

        initViews()
        initListeners()


        return binding.root


    }



    private fun initListeners() {
        binding.btnSave.setOnClickListener{
            App.db.dao().insert(
                TaskModel(
                  title =  binding.etTitle.text.toString(),
                   description =  binding.etDesc.text.toString(),
                    imgUri = imgUri
                )
            )
            findNavController().navigateUp()

        }
        binding.imageTask.setOnClickListener{
            mGetContent.launch("image/*")
        }
    }

    private fun initViews() {

        binding.btnTime.setOnClickListener {
showDataRangePicker()
        }

    }

    private fun showDataRangePicker() {

        val dateRangePicker =
            MaterialDatePicker
                .Builder.dateRangePicker()
                .setTitleText("Select Date")
                .build()

        dateRangePicker.show(
            childFragmentManager,
            "date_range_picker"
        )

        dateRangePicker.addOnPositiveButtonClickListener { dateSelected ->

            val startDate = dateSelected.first
            val endDate = dateSelected.second

            if (startDate != null && endDate != null) {
                binding.timerView.text =
                    "Reserved\nStartDate: ${convertLongToTime(startDate)}\n" +
                            "EndDate: ${convertLongToTime(endDate)}"
            }
        }

    }


    private fun convertLongToTime(time: Long): String {
        val date = Date(time)
        val format = SimpleDateFormat(
            "dd.MM.yyyy",
            Locale.getDefault())
        return format.format(date)
    }



}
