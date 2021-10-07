package com.example.basicstructure.ui.main.view.activity

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity
import com.example.basicstructure.R


class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        Handler().postDelayed(Runnable
        // Using handler with postDelayed called runnable run method
        {
            val i = Intent(this@SplashActivity, LoginActivity::class.java)
            startActivity(i)

            // close this activity
            finish()
        }, 2500
        ) // wait for 2.5 seconds

    }
}