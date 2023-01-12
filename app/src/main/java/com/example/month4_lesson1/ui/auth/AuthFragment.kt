package com.example.month4_lesson1.ui.auth

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.navigation.fragment.findNavController
import com.example.month4_lesson1.databinding.FragmentAuthBinding
import com.example.month4_lesson1.ui.utils.showToast
import com.fraggjkee.smsconfirmationview.SmsConfirmationView
import com.google.firebase.FirebaseException
import com.google.firebase.auth.*
import java.util.concurrent.TimeUnit

class AuthFragment : Fragment() {

    private lateinit var binding: FragmentAuthBinding
    private var auth = FirebaseAuth.getInstance()
    private var correctCode : String? = null


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentAuthBinding.inflate(inflater, container, false)
        initListeners()
        return binding.root
    }

    private fun initListeners() {
        binding.btnSend.setOnClickListener{
            if (binding.etPhone.text!!.isNotEmpty()){
                sendPhone()
                Toast.makeText(requireContext(), "отправка ", Toast.LENGTH_SHORT).show()
            } else{
                binding.etPhone.error = "Введите  номер телефона"
            }
            binding.btnConfirm.setOnClickListener{
                sendCode()
            }
            binding.smsCodeView.onChangeListener = SmsConfirmationView.OnChangeListener { code, isComplete ->

            }
            binding.smsCodeView.startListeningForIncomingMessages()
        }
    }

    private fun sendPhone() {
        auth.setLanguageCode("RU")
        val options = PhoneAuthOptions.newBuilder(auth)
            .setPhoneNumber(binding.etPhone.text.toString())       // Phone number to verify
            .setTimeout(60L, TimeUnit.SECONDS) // Timeout and unit
            .setActivity(requireActivity())                 // Activity (for callback binding)
            .setCallbacks(object: PhoneAuthProvider.OnVerificationStateChangedCallbacks(){
                override fun onVerificationCompleted(p0: PhoneAuthCredential) {

                }

                override fun onVerificationFailed(exeption: FirebaseException) {
                    showToast(exeption.message.toString())
                }

                override fun onCodeSent(verificationCode: String, p1: PhoneAuthProvider.ForceResendingToken) {
                    correctCode = verificationCode
                    binding.etLayoutPhone.isVisible = false
                    binding.btnSend.isVisible = false

                    binding.smsCodeView.isVisible = true
                    binding.btnConfirm.isVisible = true

                    Log.e("ololo" , "Correct code: $verificationCode")
                    super.onCodeSent(verificationCode, p1)
                }

            })          // OnVerificationStateChangedCallbacks
            .build()
        PhoneAuthProvider.verifyPhoneNumber(options)
    }

    private fun sendCode(){
        val credential =
            correctCode?.let { it1 -> PhoneAuthProvider.getCredential(it1, binding.smsCodeView.toString()) }
        if (credential != null) {
            signInWithPhoneAuthCredential(credential)
        }
    }

    private fun signInWithPhoneAuthCredential(credential: PhoneAuthCredential) {
        auth.signInWithCredential(credential)
            .addOnCompleteListener(requireActivity()) { task ->
                if (task.isSuccessful){
                    findNavController().navigate(com.example.month4_lesson1.R.id.navigation_home)
                } else {
                    Log.w("ololo", "signInWithCredential:failure", task.exception)
                    if (task.exception is FirebaseAuthInvalidCredentialsException) {
                        Log.e("ololo", "signInWithCredential: " +task.exception.toString())
                    }
                }
            }
    }

}