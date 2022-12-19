package com.example.month4_lesson1.ui.profile

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts

import com.example.month4_lesson1.databinding.FragmentProfileBinding


class ProfileFragment : Fragment() {

    lateinit var binding: FragmentProfileBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }
    var mGetContent: ActivityResultLauncher<String> = registerForActivityResult(
        ActivityResultContracts.GetContent(), {uri-> binding.ivImage.setImageURI(uri) })
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentProfileBinding.inflate(LayoutInflater.from(context), container, false)
        initListener()
        return binding.root
    }
    private fun initListener() {
        binding.ivImage.setOnClickListener {
            mGetContent.launch("image/*");

        }
    }


}