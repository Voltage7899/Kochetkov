package com.company.nemov

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.company.nemov.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.createTest.setOnClickListener {
            startActivity(Intent(this,CreateTest::class.java))
        }
        binding.enterTest.setOnClickListener {
            startActivity(Intent(this,ChooseSub::class.java))
        }
    }
}