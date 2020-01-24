package com.taehyung.clickricecake.ui.splash

import android.content.Intent
import android.content.Intent.FLAG_ACTIVITY_REORDER_TO_FRONT
import android.os.Bundle
import android.os.SystemClock
import android.util.Log
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import com.taehyung.clickricecake.MainActivity
import com.taehyung.clickricecake.ui.data.Const

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
        intent.addFlags(FLAG_ACTIVITY_REORDER_TO_FRONT)

        intent.putExtra(Const.TOTAL_MAKED_COUNT.value, 0L)
        intent.putExtra(Const.ITEM_LIST.value, 0L)

        startActivity(intent)
        finish()
    }
}