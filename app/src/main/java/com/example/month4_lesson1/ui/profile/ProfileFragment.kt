package com.example.month4_lesson1.ui.profile

import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.ImageDecoder
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.provider.MediaStore
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.core.widget.addTextChangedListener
import com.example.month4_lesson1.R
import com.example.month4_lesson1.databinding.FragmentNotificationsBinding
import com.example.month4_lesson1.databinding.FragmentProfileBinding
import com.example.month4_lesson1.ui.local.PrefOnBoarding

class ProfileFragment : Fragment() {

    private lateinit var prefOnBoarding: PrefOnBoarding
    private lateinit var binding:FragmentProfileBinding
    var launchForResult = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
            result ->
        if (result.resultCode == AppCompatActivity.RESULT_OK) {
            val image = result.data?.data
            binding.ivImage.setImageURI(image)}
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentProfileBinding.inflate(inflater , container , false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initListeners()
        prefOnBoarding = PrefOnBoarding(requireContext())
        binding.etName.setText(prefOnBoarding.getName())
        binding.etName.addTextChangedListener{
            prefOnBoarding.saveName(binding.etName.text.toString())
        }

    }



    private fun initListeners() {
        binding.ivImage.setOnClickListener{
            val intent = Intent(Intent.ACTION_GET_CONTENT)
            intent.type = "image/*"
            launchForResult.launch(intent)
        }
    }


}