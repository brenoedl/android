package com.brenoedl.lojavirtualcliente.activitys.telapedidos

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.brenoedl.lojavirtualcliente.R
import com.brenoedl.lojavirtualcliente.databinding.ActivityTelaPedidosBinding

class TelaPedidos : AppCompatActivity() {
    private lateinit var binding: ActivityTelaPedidosBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTelaPedidosBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}