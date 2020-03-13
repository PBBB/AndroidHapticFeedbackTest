package com.pbb.study.hapticfeedback

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class VibratorActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_vibrator)
        this.title = "Vibrator"
    }
}
