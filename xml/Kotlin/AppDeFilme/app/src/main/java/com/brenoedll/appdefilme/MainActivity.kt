package com.brenoedll.appdefilme

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AppCompatActivity
import com.brenoedll.appdefilme.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar!!.hide()

        Handler(Looper.getMainLooper()).postDelayed({
            val intent = Intent(this, FormLogin::class.java)
            startActivity(intent)
            finish()
        }, 2000)
    }
}