package com.gm.mvies.feature.splash

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.lifecycleScope
import com.gm.mvies.R
import com.gm.mvies.feature.HomeActivity
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class SplashActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        lifecycleScope.launch {
            delay(1700L)
            navigateToNextScreen()
        }
    }

    private fun navigateToNextScreen() {
        startActivity(Intent(this@SplashActivity, HomeActivity::class.java))
        finish()
    }


}