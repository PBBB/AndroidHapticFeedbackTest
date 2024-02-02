package com.pbb.study.hapticfeedback

import android.content.Context
import android.os.Build
import android.os.Bundle
import android.os.VibrationEffect
import android.os.Vibrator
import android.view.View
import android.widget.Button
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import com.pbb.study.hapticfeedback.databinding.ActivityVibratorBinding
//import kotlinx.android.synthetic.main.activity_vibrator.*

class VibratorActivity : AppCompatActivity() {

    private lateinit var binding: ActivityVibratorBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityVibratorBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        this.title = "OneShot & Waveform"

        // Check support for amplitude control
        val vibrator = applicationContext.getSystemService(Context.VIBRATOR_SERVICE) as Vibrator
        if (!vibrator.hasAmplitudeControl()){
            binding.ampCheckTextView.text = "The vibrator has NO amplitude control, but the actual behavior may vary."
        }
    }

    @RequiresApi(Build.VERSION_CODES.S)
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

        if (binding.radioButtonOneShot.isChecked) {
            val time = binding.timeTextField.text.toString().toLong()
            val amplitude = binding.ampliTextField.text.toString().toInt()
            vibrator.vibrate(VibrationEffect.createOneShot(time, amplitude))
        } else if (binding.radioButtonWaveForm.isChecked) {
            val times = binding.timeTextField.text.toString().split(",").map { it.toLong() }.toLongArray()
            val amplitudes = binding.ampliTextField.text.toString().split(",").map { it.toInt() }.toIntArray()
            val repeat = binding.repeatTextField.text.toString().toInt()
            vibrator.vibrate(VibrationEffect.createWaveform(times, amplitudes, repeat))
        }
    }

    fun changeInfo(view: View) {
        if (view == binding.radioButtonOneShot) {
            binding.tipTextView.text = "Time(ms): The number of milliseconds to vibrate. This must be a positive number. \n\nAmplitude: The strength of the vibration. This must be a value between 1 and 255, or DEFAULT_AMPLITUDE(-1). \n\nRepeat: No use"
            binding.timeTextView.text = "Time(ms)"
            binding.ampliTextView.text = "Amplitude"
            binding.repeatTextView.text = "Repeat"
        } else if (view == binding.radioButtonWaveForm) {
            binding.tipTextView.text = "Timings and Amplitudes array should be 1-to-1 paired\n\nTimings([long]): The pattern of alternating on-off timings, starting with off. Timing values of 0 will cause the timing / amplitude pair to be ignored. e.g. 200, 300\n\nAmplitudes([int]): he amplitude values of the timing / amplitude pairs. Amplitude values must be between 0 and 255, or equal to DEFAULT_AMPLITUDE(-1). An amplitude value of 0 implies the motor is off. e.g. 100, 200\n\nRepeat(int): The index into the timings array at which to repeat, or -1 if you you don't want to repeat."
            binding.timeTextView.text = "Timings([ms])"
            binding.ampliTextView.text = "Amplitudes"
            binding.repeatTextView.text = "Repeat"
        }
    }

    fun stopVibrate(view: View) {
        val vibrator = applicationContext.getSystemService(Context.VIBRATOR_SERVICE) as Vibrator
        vibrator.cancel()
    }
}
