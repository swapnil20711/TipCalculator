package com.example.tipcalci

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.tipcalci.databinding.ActivityMainBinding
import java.math.RoundingMode
import java.text.NumberFormat
import kotlin.math.ceil

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setUpBtn()
    }

    private fun setUpBtn() {
        binding.calciBtn.setOnClickListener(View.OnClickListener {
            calculateTip()
        })
    }

    private fun calculateTip() {
        var tipPercentage: Float = 0f
        if (!binding.costInput.text.isNullOrEmpty()) {
            when (binding.radioGroup.checkedRadioButtonId) {
                R.id.amazing -> tipPercentage = .2f
                R.id.good -> tipPercentage = .18f
                R.id.ok -> tipPercentage = .15f
            }
            val costInput = binding.costInput.text.toString()
            val tipAmount = costInput.toFloat() * tipPercentage
            val roundUp = binding.roundOffSwitch.isChecked
            if (roundUp) {
                binding.tipAmtOp.text = (ceil(tipAmount).toString())
            } else {
                binding.tipAmtOp.text =
                    (tipAmount.toBigDecimal().setScale(2, RoundingMode.UP).toDouble().toString())
            }
        } else {
            binding.costInput.error = "Enter some amount to calculate tip!!"
        }
    }
}
