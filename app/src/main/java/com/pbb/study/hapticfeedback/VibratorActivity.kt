package com.pbb.study.hapticfeedback

import android.content.Context
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Vibrator
import android.os.VibrationEffect
import android.view.View
import android.widget.Button
import androidx.annotation.RequiresApi
import androidx.core.content.getSystemService

class VibratorActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_vibrator)
        this.title = "Vibrator"
    }

    @RequiresApi(Build.VERSION_CODES.Q)
    fun performPredefinedVibration(view: View) {
        val vibrator = applicationContext.getSystemService(Context.VIBRATOR_SERVICE) as Vibrator

        if (view is Button) {
            when (view.text) {
                "EFFECT_TICK" -> vibrator.vibrate(VibrationEffect.createPredefined(VibrationEffect.EFFECT_CLICK))
                "EFFECT_CLICK" -> vibrator.vibrate(VibrationEffect.createPredefined(VibrationEffect.EFFECT_CLICK))
                "EFFECT_HEAVY_CLICK" -> vibrator.vibrate(VibrationEffect.createPredefined(VibrationEffect.EFFECT_HEAVY_CLICK))
                "EFFECT_DOUBLE_CLICK" -> vibrator.vibrate(VibrationEffect.createPredefined(VibrationEffect.EFFECT_DOUBLE_CLICK))
            }
        }

    }
}
