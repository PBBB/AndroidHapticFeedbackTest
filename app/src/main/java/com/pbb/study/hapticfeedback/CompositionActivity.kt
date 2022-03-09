package com.pbb.study.hapticfeedback

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.VibrationEffect
import android.os.Vibrator
import android.view.HapticFeedbackConstants
import android.view.View
import android.widget.Button
import kotlinx.android.synthetic.main.activity_composition.*

class CompositionActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_composition)
        this.title = "Composition"

        // Check support for primitives
        val vibrator = applicationContext.getSystemService(Context.VIBRATOR_SERVICE) as Vibrator
        if (vibrator.areAllPrimitivesSupported(VibrationEffect.Composition.PRIMITIVE_CLICK)) {
            primSupportTextView.text = "This device support some of the following primitives."
        }
    }

    fun performPrimitives(view: View) {
        if (view is Button) {
            val vibrator = applicationContext.getSystemService(Context.VIBRATOR_SERVICE) as Vibrator
            var compositon = VibrationEffect.startComposition()

            when (view.text.toString()) {
                "PRIMITIVE_CLICK (1)" -> compositon = compositon.addPrimitive(VibrationEffect.Composition.PRIMITIVE_CLICK)
                "PRIMITIVE_THUD (2, Android S)" -> compositon = compositon.addPrimitive(VibrationEffect.Composition.PRIMITIVE_THUD)
                "PRIMITIVE_SPIN (3, Android S)" -> compositon = compositon.addPrimitive(VibrationEffect.Composition.PRIMITIVE_SPIN)
                "PRIMITIVE_QUICK_RISE (4)" -> compositon = compositon.addPrimitive(VibrationEffect.Composition.PRIMITIVE_QUICK_RISE)
                "PRIMITIVE_SLOW_RISE (5)" -> compositon = compositon.addPrimitive(VibrationEffect.Composition.PRIMITIVE_SLOW_RISE)
                "PRIMITIVE_QUICK_FALL (6)" -> compositon = compositon.addPrimitive(VibrationEffect.Composition.PRIMITIVE_QUICK_FALL)
                "PRIMITIVE_TICK (7)" -> compositon = compositon.addPrimitive(VibrationEffect.Composition.PRIMITIVE_TICK)
                "PRIMITIVE_LOW_TICK (8, Android S)" -> compositon = compositon.addPrimitive(VibrationEffect.Composition.PRIMITIVE_LOW_TICK)
            }

            val viborationEffect = compositon.compose()
            vibrator.vibrate(viborationEffect)
        }
    }
}

