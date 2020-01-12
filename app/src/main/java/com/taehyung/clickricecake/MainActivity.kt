package com.taehyung.clickricecake

import androidx.appcompat.app.AppCompatActivity

import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    var mClickMaking = 1L
    var mAutoMaking = 0L

    var mTotalMaking: Long = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        main_soup_img.setOnClickListener(mClickListener)
        main_soup_making.text = "1초당 ${mAutoMaking}개 생산중 !"

    }

    private val mClickListener = object: View.OnClickListener {
        override fun onClick(view: View?) {
            if (view?.id == main_soup_count.id) {
                mTotalMaking += mClickMaking
                main_soup_count.text = mTotalMaking.toString()
            }
        }
    }
}
