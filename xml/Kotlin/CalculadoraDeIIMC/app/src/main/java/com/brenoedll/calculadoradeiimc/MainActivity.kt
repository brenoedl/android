package com.brenoedll.calculadoradeiimc

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.inputmethod.InputMethodManager
import androidx.appcompat.app.AppCompatActivity
import com.brenoedll.calculadoradeiimc.databinding.ActivityMainBinding
import java.text.DecimalFormat

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var imm: InputMethodManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        imm = getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
        setContentView(binding.root)

        binding.btCalcular.setOnClickListener {
            var ok: Boolean = true

            if (binding.etPeso.text.isEmpty()) {
                binding.etPeso.error = getString(R.string.msg_ep)
                ok = false
            }

            if (binding.etAltura.text.isEmpty()) {
                binding.etAltura.error = getString(R.string.msg_ea)
                ok = false
            }

            if (ok) {
                imm.hideSoftInputFromWindow(binding.root.windowToken, 0)
                calcularIMC(binding.etPeso.text.toString(), binding.etAltura.text.toString())
            }
        }
    }

    private fun calcularIMC(p: String, a: String) {
        val peso = p.replace(",", ".").toFloat()
        val altura = a.replace(",", ".").toFloat()
        val imc = peso / (altura * altura)

        val msg: String
        val decimalFormat = DecimalFormat("#.##")

        if (imc < 18.5) {
            msg = """${getString(R.string.msg_imc)}${decimalFormat.format(imc.toDouble())}
${getString(R.string.msg_bp)}""".trimIndent()
            binding.tvResp.text = msg
        } else if (imc <= 24.9) {
           msg = """${getString(R.string.msg_imc)}${decimalFormat.format(imc.toDouble())}
${getString(R.string.msg_pn)}""".trimIndent()
            binding.tvResp.text = msg
        } else if (imc <= 29.9) {
            msg = """${getString(R.string.msg_imc)}${decimalFormat.format(imc.toDouble())}
${getString(R.string.msg_s)}""".trimIndent()
            binding.tvResp.text = msg
        } else if (imc <= 34.9) {
            msg = """${getString(R.string.msg_imc)}${decimalFormat.format(imc.toDouble())}
${getString(R.string.msg_og1)}""".trimIndent()
            binding.tvResp.text = msg
        } else if (imc <= 39.9) {
            msg = """${getString(R.string.msg_imc)}${decimalFormat.format(imc.toDouble())}
${getString(R.string.msg_og2)}""".trimIndent()
            binding.tvResp.text = msg
        } else {
            msg = """${getString(R.string.msg_imc)}${decimalFormat.format(imc.toDouble())}
${getString(R.string.msg_og3)}""".trimIndent()
            binding.tvResp.text = msg
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.menu_princiipal, menu)

        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.reset -> {
                binding.etPeso.setText("")
                binding.etAltura.setText("")
                binding.tvResp.text = ""
            }
        }

        return super.onOptionsItemSelected(item)
    }
}