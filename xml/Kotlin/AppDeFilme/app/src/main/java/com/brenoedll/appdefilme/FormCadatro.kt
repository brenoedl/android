package com.brenoedll.appdefilme

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.brenoedll.appdefilme.databinding.ActivityFormCadatroBinding

class FormCadatro : AppCompatActivity() {
    private lateinit var binding: ActivityFormCadatroBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFormCadatroBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar!!.hide()
    }
}