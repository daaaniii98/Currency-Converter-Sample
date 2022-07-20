package com.example.currencychallenge

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.content.ContextCompat
import com.example.currencychallenge.databinding.ActivityMainBinding
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private var _binding: ActivityMainBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityMainBinding.inflate(layoutInflater).apply {
            setContentView(root)
        }
    }
    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    fun showSnackBar(msg:String){
        Snackbar.make(binding.root, msg, Snackbar.LENGTH_SHORT)
            .setActionTextColor(ContextCompat.getColor(this@MainActivity, android.R.color.holo_red_light))
            .show()
    }

}