package com.wang.tools.button

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity
import com.wang.tools.R
import com.wang.tools.main.MainActivity
import com.wang.uilibrary.button.TransitionButton

class ButtonActivity : AppCompatActivity() {
    private lateinit var transitionButton: TransitionButton
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_button)
        transitionButton = findViewById(R.id.transition_button)

        transitionButton.setOnClickListener {
            transitionButton.startAnimation()
            val handler = Handler()
            handler.postDelayed({
                val isSuccessful = true
                if (isSuccessful) {
                    transitionButton.stopAnimation(TransitionButton.StopAnimationStyle.EXPAND) {
                        val intent = Intent(baseContext, MainActivity::class.java)
                        intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION)
                        startActivity(intent)
                    }
                } else {
                    transitionButton.stopAnimation(TransitionButton.StopAnimationStyle.SHAKE, null)
                }
            }, 2000)
        }
    }
}