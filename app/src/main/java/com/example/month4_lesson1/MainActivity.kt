package com.example.month4_lesson1

import android.os.Bundle
import android.util.Log
import android.view.View
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.month4_lesson1.databinding.ActivityMainBinding
import com.example.month4_lesson1.ui.utils.Preference
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.messaging.FirebaseMessaging

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private var auth = FirebaseAuth.getInstance()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        FirebaseMessaging.getInstance().token.addOnCompleteListener{
            Log.e("ololo", "onCreate: "+it.result )
        }

        val navView: BottomNavigationView = binding.navView

        val navController = findNavController(R.id.nav_host_fragment_activity_main)

        val appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.navigation_home, R.id.navigation_dashboard, R.id.navigation_notifications,R.id.profile_fragment, R.id.newTaskFragment
            )
        )

//      navController.navigate(R.id.authFragment)
       if (!Preference(applicationContext).isBoardingShowed()){
            navController.navigate(R.id.onBoardingFragment)}
         else if(auth.currentUser == null){
           navController.navigate(R.id.authFragment)
       }




        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)


        var list  = setOf(
            R.id. newTaskFragment,
            R.id.onBoardingFragment,
            R.id.authFragment
        )
        navController.addOnDestinationChangedListener{ _, destination, _ ->
            if (list.contains(destination.id)){
                navView.visibility = View.GONE

            }
            if (destination.id == R.id.onBoardingFragment  || destination.id == R.id.authFragment){
                supportActionBar?.hide()
        }
            else navView.visibility = View.VISIBLE

        }
    }
}