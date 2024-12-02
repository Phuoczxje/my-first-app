package com.example.myfirstapp.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.myfirstapp.databinding.ActivityProductsBinding

class ProductsActivity : AppCompatActivity() {
    private lateinit var binding: ActivityProductsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProductsBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        binding.imgBack.setOnClickListener {
            finish()
        }
    }
}