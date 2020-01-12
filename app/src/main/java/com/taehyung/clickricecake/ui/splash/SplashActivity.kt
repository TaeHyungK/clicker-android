package com.taehyung.clickricecake.ui.splash

import android.content.Intent
import android.os.Bundle
import android.os.SystemClock
import android.util.Log
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import com.taehyung.clickricecake.MainActivity

class SplashActivity : AppCompatActivity() {
    val LOGD = "SplashActivity"

    val WAIT_DURATION = 3000L

    lateinit var mImg: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        SystemClock.sleep(WAIT_DURATION)
        startMainActivity()
    }

    /**
     * MainActivity 실행
     * FIXME 임시 4초 후 실행. 초기 데이터 받아온 후 실행으로 변경 필요
     */
    private fun startMainActivity() {
        Log.d(LOGD, "startMainActivity() called")
        val intent = Intent(applicationContext, MainActivity::class.java)
        startActivity(intent)
        finish()
    }
}