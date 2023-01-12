package com.example.month4_lesson1.ui.profile


import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.widget.addTextChangedListener
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.example.month4_lesson1.R

import com.example.month4_lesson1.databinding.FragmentProfileBinding
import com.example.month4_lesson1.ui.utils.Preference
import com.google.firebase.auth.FirebaseAuth


class ProfileFragment : Fragment() {
    private lateinit var prefOnBoarding: Preference
    lateinit var binding: FragmentProfileBinding
    private lateinit var preference: Preference

    var mGetContent: ActivityResultLauncher<String> = registerForActivityResult(
        ActivityResultContracts.GetContent(), { uri->
            Glide.with(requireContext()).load(uri).centerCrop().placeholder(R.drawable.def_pic)
                .into(binding.ivImage)
            Preference(requireContext()).saveImg(uri.toString())
        })



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentProfileBinding.inflate(LayoutInflater.from(context), container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initListener()
        prefOnBoarding = Preference(requireContext())
        binding.etName.setText(prefOnBoarding.getName())
        binding.etName.addTextChangedListener {
            prefOnBoarding.saveName(binding.etName.text.toString())
        }
        preference = Preference(requireContext())
        Glide.with(requireContext()).load(Preference(requireContext()).getImg()).centerCrop().placeholder(R.drawable.def_pic)
            .into(binding.ivImage)

        }

    private fun initListener() {
        binding.ivImage.setOnClickListener {
            mGetContent.launch("image/*")

        }
        binding.btnOut.setOnClickListener{
            FirebaseAuth.getInstance().signOut()
            findNavController().navigate(R.id.authFragment)
        }


    }


}