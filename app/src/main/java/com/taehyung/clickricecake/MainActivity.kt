package com.taehyung.clickricecake

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.dialog_purchase.view.*

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
            makeDialog("댕댕이")
        }
        main_item_baby.setOnClickListener {
            makeDialog("뽀시래기")
        }
        main_item_kids.setOnClickListener {
            makeDialog("급식동생")
        }
    }

    /**
     * 구매/취소 Dialog 생성
     */
    fun makeDialog(item: String) {
        val builder = AlertDialog.Builder(this)
        val dialogView = layoutInflater.inflate(R.layout.dialog_purchase, null)

        dialogView.dialog_title.text = "구매하기"
        dialogView.dialog_desc.text = getString(R.string.text_purchase, item)

        builder.setView(dialogView)
                .setPositiveButton(getString(R.string.btn_purchase)) { dialogInterface, i ->
                    // TODO 확인일 경우 RecyclerView에 아이템 추가 처리필요
                    Toast.makeText(applicationContext, "$item 구매!", Toast.LENGTH_SHORT).show()
                }
                .setNegativeButton(R.string.btn_cancel) { dialogInterface, i ->
                    // 취소일 경우 동작 없음
                }
                .show()
    }
}
