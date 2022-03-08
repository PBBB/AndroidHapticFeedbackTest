package com.pbb.study.hapticfeedback

import android.content.Context
import android.os.Build
import android.os.Bundle
import android.os.VibrationEffect
import android.os.Vibrator
import android.view.InputDevice
import android.view.View
import android.widget.Button
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_vibrator.*

class VibratorActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_vibrator)
        this.title = "Vibrator"
    }

    @RequiresApi(Build.VERSION_CODES.Q)
    fun performPredefinedVibration(view: View) {
//        val vibrator = InputDevice.getDevice(InputDevice.getDeviceIds()[0]).vibratorManager.defaultVibrator
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

    fun performOneShotOrWaveformVibration(view: View) {
        val vibrator = applicationContext.getSystemService(Context.VIBRATOR_SERVICE) as Vibrator

        if (radioButtonOneShot.isChecked) {
            val time = timeTextField.text.toString().toLong()
            val amplitude = ampliTextField.text.toString().toInt()
            vibrator.vibrate(VibrationEffect.createOneShot(time, amplitude))
        } else if (radioButtonWaveForm.isChecked) {
            val times = timeTextField.text.toString().split(",").map { it.toLong() }.toLongArray()
            val amplitudes = ampliTextField.text.toString().split(",").map { it.toInt() }.toIntArray()
            val repeat = repeatTextField.text.toString().toInt()
            vibrator.vibrate(VibrationEffect.createWaveform(times, amplitudes, repeat))
        }
    }

    fun changeInfo(view: View) {
        if (view == radioButtonOneShot) {
            tipTextView.text = "Time(ms): The number of milliseconds to vibrate. This must be a positive number. \n\nAmplitude: The strength of the vibration. This must be a value between 1 and 255, or DEFAULT_AMPLITUDE(-1). \n\nRepeat: No use"
            timeTextView.text = "Time(ms)"
            ampliTextView.text = "Amplitude"
            repeatTextView.text = "Repeat"
        } else if (view == radioButtonWaveForm) {
            tipTextView.text = "Timings and Amplitudes array should be 1-to-1 paired\n\nTimings([long]): The pattern of alternating on-off timings, starting with off. Timing values of 0 will cause the timing / amplitude pair to be ignored. e.g. 200, 300\n\nAmplitudes([int]): he amplitude values of the timing / amplitude pairs. Amplitude values must be between 0 and 255, or equal to DEFAULT_AMPLITUDE(-1). An amplitude value of 0 implies the motor is off. e.g. 100, 200\n\nRepeat(int): The index into the timings array at which to repeat, or -1 if you you don't want to repeat."
            timeTextView.text = "Timings([ms])"
            ampliTextView.text = "Amplitudes"
            repeatTextView.text = "Repeat"
        }
    }

    fun stopVibrate(view: View) {
        val vibrator = applicationContext.getSystemService(Context.VIBRATOR_SERVICE) as Vibrator
        vibrator.cancel()
    }
}
