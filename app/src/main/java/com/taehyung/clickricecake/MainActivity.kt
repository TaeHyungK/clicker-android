package com.taehyung.clickricecake

import androidx.appcompat.app.AppCompatActivity

import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    var mClickMaking = 1L
    var mAutoMaking = 0L

    var mTotalMaking: Long = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        main_soup_img.setOnClickListener {
            mTotalMaking += mClickMaking
            main_soup_count.text = mTotalMaking.toString()
        }
        main_soup_making.text = "1초당 ${mAutoMaking}개 생산중 !"
    }
}
