package com.pbb.study.hapticfeedback

import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.view.*
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.pbb.study.hapticfeedback.databinding.ActivityMainBinding
//import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var binding:ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        enableEdgeToEdge()
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        binding.button0.setOnLongClickListener {
//            it.performHapticFeedback(HapticFeedbackConstants.LONG_PRESS)
//            Toast.makeText(this, "Long click detected", Toast.LENGTH_SHORT).show()
            true
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menus, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.vibrator -> {
                val intent = Intent(this, VibratorActivity::class.java)
                startActivity(intent)
                true
            }
            R.id.composition -> {
                val intent = Intent(this, CompositionActivity::class.java)
                startActivity(intent)
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    fun performHaptics(view: View) {
        if (view is Button) {
            when (view.text.toString()) {
                "LONG_PRESS (0)" -> view.performHapticFeedback(HapticFeedbackConstants.LONG_PRESS)
                "VIRTUAL_KEY (1)" -> view.performHapticFeedback(HapticFeedbackConstants.VIRTUAL_KEY)
                "KEYBOARD_PRESS / KEYBOARD_TAP (3)" -> view.performHapticFeedback(HapticFeedbackConstants.KEYBOARD_PRESS)
                "CLOCK_TICK (4)" -> view.performHapticFeedback(HapticFeedbackConstants.CLOCK_TICK)
                "CONTEXT_CLICK (6)" -> view.performHapticFeedback(HapticFeedbackConstants.CONTEXT_CLICK)
                "KEYBOARD_RELEASE (7)" -> view.performHapticFeedback(HapticFeedbackConstants.KEYBOARD_RELEASE)
                "VIRTUAL_KEY_RELEASE (8)" -> view.performHapticFeedback(HapticFeedbackConstants.VIRTUAL_KEY_RELEASE)
                "TEXT_HANDLE_MOVE (9)" -> view.performHapticFeedback(HapticFeedbackConstants.TEXT_HANDLE_MOVE)
                "GESTURE_START (12)" -> view.performHapticFeedback(HapticFeedbackConstants.GESTURE_START)
                "GESTURE_END (13)" -> view.performHapticFeedback(HapticFeedbackConstants.GESTURE_END)
                "CONFIRM (16)" -> view.performHapticFeedback(HapticFeedbackConstants.CONFIRM)
                "REJECT (17)" -> view.performHapticFeedback(HapticFeedbackConstants.REJECT)
                "TOGGLE_ON (21)" -> view.performHapticFeedback(HapticFeedbackConstants.TOGGLE_ON)
                "TOGGLE_OFF (22)" -> view.performHapticFeedback(HapticFeedbackConstants.TOGGLE_OFF)
                "GESTURE_THRESHOLD_ACTIVATE (23)" -> view.performHapticFeedback(HapticFeedbackConstants.GESTURE_THRESHOLD_ACTIVATE)
                "GESTURE_THRESHOLD_DEACTIVATE (24)" -> view.performHapticFeedback(HapticFeedbackConstants.GESTURE_THRESHOLD_DEACTIVATE)
                "DRAG_START (25)" -> view.performHapticFeedback(HapticFeedbackConstants.DRAG_START)
                "SEGMENT_TICK (26)" -> view.performHapticFeedback(HapticFeedbackConstants.SEGMENT_TICK)
                "SEGMENT_FREQUENT_TICK (27)" -> view.performHapticFeedback(HapticFeedbackConstants.SEGMENT_FREQUENT_TICK)
            }
        }
    }

    fun playCustomHaptics(view: View) {
        val id = binding.hapticsID.text.toString().toIntOrNull()
        if (id == null) {
            val dialogBuilder = AlertDialog.Builder(this)
                .setPositiveButton("OK", DialogInterface.OnClickListener {
                        _, _ -> return@OnClickListener
                })
            dialogBuilder.setMessage("")
            val alert =  dialogBuilder.create()
            alert.setTitle("Please Enter a Valid ID")
            alert.setMessage("Haptic feedback constants ID (number) required. ")
            alert.show()
        } else {
            view.performHapticFeedback(id)
        }
    }
}
