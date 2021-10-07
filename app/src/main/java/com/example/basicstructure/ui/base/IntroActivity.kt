package com.example.basicstructure.ui.base

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.basicstructure.ui.main.view.activity.MainActivity
import com.example.basicstructure.ui.main.view.activity.SplashActivity

/**
 * Created by Bharat.
 */

class IntroActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        intent = Intent(
            this,
            MainActivity::class.java
        )
        startActivity(intent)
        finish()
    }
}