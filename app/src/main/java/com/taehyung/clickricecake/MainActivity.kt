package com.taehyung.clickricecake

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    var mClickMaking = 1L
    var mAutoMaking = 0L

    var mTotalMaking: Long = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // set Text value
        main_soup_making.text = "1초당 ${mAutoMaking}개 생산중 !"
        main_item_dog.text = "🐶/댕댕이/1/1dk/s"
        main_item_baby.text = "👶/뽀시래기/2/2dk/s"
        main_item_kids.text = "🙇‍️/급식동생/3/3dk/s"

        // set clickListener
        main_soup_img.setOnClickListener {
            mTotalMaking += mClickMaking
            main_soup_count.text = mTotalMaking.toString()
        }
        main_item_dog.setOnClickListener {
            Toast.makeText(applicationContext, "댕댕이 클릭!", Toast.LENGTH_SHORT).show()
        }
        main_item_baby.setOnClickListener {
            Toast.makeText(applicationContext, "뽀시래기 클릭!", Toast.LENGTH_SHORT).show()
        }
        main_item_kids.setOnClickListener {
            Toast.makeText(applicationContext, "급식동생 클릭!", Toast.LENGTH_SHORT).show()
        }


    }
}
