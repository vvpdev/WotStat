package com.vvp.wotstat.activities

import android.os.Bundle
import androidx.navigation.findNavController
import com.arellomobile.mvp.MvpAppCompatActivity
import com.vvp.wotstat.R

class MainActivity : MvpAppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }


    override fun onSupportNavigateUp(): Boolean {
        return findNavController(R.id.navFragHost).navigateUp() }

}