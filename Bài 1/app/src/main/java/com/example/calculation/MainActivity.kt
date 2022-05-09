package com.example.calculation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.view.View
import android.widget.TextView
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), View.OnClickListener {

    private var firstNum: String? = null
    private var secNum: String? = null

    private var calculationType: CalculationType? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        tv_percent.setOnClickListener(this)
        tv_ce.setOnClickListener(this)
        tv_c.setOnClickListener(this)
        tv_back.setOnClickListener(this)
        tv_1x.setOnClickListener(this)
        tv_square.setOnClickListener(this)
        tv_sqrt.setOnClickListener(this)
        tv_div.setOnClickListener(this)
        tv_7.setOnClickListener(this)
        tv_8.setOnClickListener(this)
        tv_9.setOnClickListener(this)
        tv_1.setOnClickListener(this)
        tv_2.setOnClickListener(this)
        tv_3.setOnClickListener(this)
        tv_4.setOnClickListener(this)
        tv_5.setOnClickListener(this)
        tv_6.setOnClickListener(this)
        tv_mult.setOnClickListener(this)
        tv_sub.setOnClickListener(this)
        tv_add.setOnClickListener(this)
        tv_reverse.setOnClickListener(this)
        tv_0.setOnClickListener(this)
        tv_dot.setOnClickListener(this)
        tv_equal.setOnClickListener(this)

    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.tv_percent -> {

            }
            R.id.tv_ce -> {
                if (calculationType == null) {
                    firstNum = null
                } else {
                    secNum = null
                }
            }
            R.id.tv_c -> {
                firstNum = null
                secNum = null
                calculationType = null
            }
            R.id.tv_back -> {
                if (calculationType == null) {
                    firstNum = if (firstNum?.length ?: 0 > 1) {
                        firstNum?.substring(0, firstNum?.length ?: 0 - 1)
                    } else {
                        null
                    }
                } else {
                    secNum = if (secNum?.length ?: 0 > 1) {
                        secNum?.substring(0, secNum?.length ?: 0 - 1)
                    } else {
                        null
                    }
                }
            }
            R.id.tv_1x -> {

            }
            R.id.tv_square -> {

            }
            R.id.tv_sqrt -> {

            }
            R.id.tv_div -> {
                calculationType = CalculationType.DIV
            }
            R.id.tv_7, R.id.tv_6, R.id.tv_5, R.id.tv_4, R.id.tv_3, R.id.tv_2, R.id.tv_1, R.id.tv_8, R.id.tv_9, R.id.tv_0 -> {
                numberClick(v.id)
            }
            R.id.tv_mult -> {
                calculationType = CalculationType.MULT
            }
            R.id.tv_sub -> {
                calculationType = CalculationType.SUB
            }
            R.id.tv_add -> {
                calculationType = CalculationType.ADD
            }
            R.id.tv_reverse -> {
                if (calculationType == null) {
                    firstNum = if (firstNum != null) {
                        var temp = firstNum?.toInt() ?: 0
                        temp *= -1
                        temp.toString()
                    } else {
                        "0"
                    }
                } else {
                    secNum = if (secNum != null) {
                        var temp = secNum?.toInt() ?: 0
                        temp = temp * -1
                        temp.toString()
                    } else {
                        "0"
                    }
                }
            }
            R.id.tv_dot -> {

            }
            R.id.tv_equal -> {
                Handler(Looper.getMainLooper()).postDelayed({
                    calculate()
                    firstNum = null
                    secNum = null
                    calculationType = null
                }, 10L)
            }
        }
        updateUI()
        Log.d("TAG", firstNum + "\n" + calculationType?.value  + "\n" + secNum)
    }

    private fun numberClick(id: Int) {
        val tv = findViewById<TextView>(id)
        if (calculationType == null) {
            if (firstNum == null || firstNum == "0") {
                firstNum = ""
            }
            firstNum += tv.text.toString()
        } else {
            if (secNum == null || secNum == "0") {
                secNum = ""
            }
            secNum += tv.text.toString()
        }
    }

    private fun updateUI() {
        if (calculationType == null) {
            tv_text.text = firstNum ?: "0"
        } else {
            if (secNum != null) {
                tv_text.text = secNum
            }
        }
    }

    private fun calculate() {
        val firstNumberNonNull = firstNum ?: "0"
        val secNumberIntNonNull = secNum ?: "0"
        tv_text.text = (when (calculationType) {
            CalculationType.ADD -> {
                firstNumberNonNull.toInt() + secNumberIntNonNull.toInt()
            }
            CalculationType.SUB -> {
                firstNumberNonNull.toInt() - secNumberIntNonNull.toInt()
            }
            CalculationType.MULT -> {
                firstNumberNonNull.toInt() * secNumberIntNonNull.toInt()
            }
            CalculationType.DIV -> {
                if (secNumberIntNonNull.toInt() == 0) {
                    "error divide by 0"
                } else {
                    firstNumberNonNull.toInt() / secNumberIntNonNull.toInt()
                }
            }
            null -> {
                firstNum ?: "0"
            }
        }).toString()

    }
}