package com.taehyung.clickricecake

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity

import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.TextView

class MainActivity : AppCompatActivity() {

    private lateinit var mSoupImg: ImageView
    private lateinit var mSoupCount: TextView
    private lateinit var mSoupMaking: TextView

    var mClickMaking = 1L
    var mAutoMaking = 0L

    var mTotalMaking: Long = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mSoupImg = findViewById(R.id.main_soup_img)
        mSoupCount = findViewById(R.id.main_soup_count)
        mSoupMaking = findViewById(R.id.main_soup_making)

        mSoupImg.setOnClickListener(mClickListener)

        mSoupMaking.text = "1초당 ${mAutoMaking}개 생산중 !"

    }

    private val mClickListener = object: View.OnClickListener {
        override fun onClick(view: View?) {
            if (view?.id == mSoupImg.id) {
                mTotalMaking += mClickMaking
                mSoupCount.text = mTotalMaking.toString()
            }
        }
    }
}
