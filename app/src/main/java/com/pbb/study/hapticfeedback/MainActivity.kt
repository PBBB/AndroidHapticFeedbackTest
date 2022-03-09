package com.pbb.study.hapticfeedback

import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.view.HapticFeedbackConstants
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        button0.setOnLongClickListener {
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
            }
        }
    }

    fun performAndroidRHaptics (view: View) {
        if (view is Button) {
            when (view.text.toString()) {
                "GESTURE_START (12, Android R)" -> view.performHapticFeedback(12)
                "GESTURE_END (13, Android R)" -> view.performHapticFeedback(13)
                "CONFIRM (16, Android R)" -> view.performHapticFeedback(16)
                "REJECT (17, Android R)" -> view.performHapticFeedback(17)
            }
        }
    }

    fun playCustomHaptics(view: View) {
        val id = hapticsID.text.toString().toIntOrNull()
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
