package com.chobo.app_beta1

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import java.util.Timer
import kotlin.math.abs
import kotlin.random.Random

class MainActivity : AppCompatActivity() {

    fun main() {
        setContentView(R.layout.activity_main)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }


        var timerTask: Timer? = null
        var stage = 1
        var sec: Int = 0
        val tv: TextView = findViewById(R.id.tv_random)
        val tv_t: TextView = findViewById(R.id.tv_timer)
        val tv_p: TextView = findViewById(R.id.tv_point)
        val btn: Button = findViewById(R.id.btn_main)

        val random_box = Random.Default
        val num = random_box.nextInt(1001)

        tv.text = (num.toFloat() / 100).toString()
        btn.text = "Start"

        btn.setOnClickListener {
            stage++

            if (stage == 2) {
                timerTask = kotlin.concurrent.timer(period = 10) {

                    sec++
                    runOnUiThread {
                        tv_t.text = (sec.toFloat() / 100).toString()
                    }

                }
                btn.text = "Pause"
            } else if (stage == 3) {
                timerTask?.cancel()

                val point = abs(sec - num).toFloat() / 100
                tv_p.text = point.toString()
                btn.text = "Next"
                stage = 0
            } else if (stage == 1) {
                main()
            }

        }


    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        main()


    }
}