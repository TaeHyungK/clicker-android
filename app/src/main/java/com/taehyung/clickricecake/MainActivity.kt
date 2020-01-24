package com.taehyung.clickricecake

import android.os.Bundle
import android.os.Handler
import android.os.PersistableBundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.taehyung.clickricecake.controller.SoupController
import com.taehyung.clickricecake.ui.data.Const
import com.taehyung.clickricecake.ui.data.ItemInfo
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.dialog_purchase.view.*
import kotlin.properties.Delegates

class MainActivity : AppCompatActivity() {
    val LOGD = "MainActivity"

    val mSoupController: SoupController by lazy {
        SoupController.getInstance(main_soup_count)
    }

    // observable 등록하여 값이 변하게 되면 문구 변경 처리
    var mAutoMaking: Long by Delegates.observable(0L) {
        property, oldValue, newValue
        ->
        run {
            main_soup_making.text = getString(R.string.text_auto_making_count_per_second, newValue)
        }
    }

    private val mMakerHandler: Handler by lazy {
        Handler()
    }

    private fun callMakerHandler(){
        // 1초 후에 실행 처리
        mMakerHandler.postDelayed(::makingDks, 1000)
    }

    private fun makingDks(){
        mSoupController.addDks(mAutoMaking)
        // 동작 후 재호출 반복
        callMakerHandler()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // 최초 생성 시 전달 받음 데이터로 셋팅
        mSoupController.mMakedTotal = intent.getLongExtra(Const.TOTAL_MAKED_COUNT.value, 0)

        // set Text value
        main_soup_making.text = getString(R.string.text_auto_making_count_per_second, mAutoMaking)
        main_item_dog.text = "🐶/댕댕이/1/1dk/s"
        main_item_baby.text = "👶/뽀시래기/2/2dk/s"
        main_item_kids.text = "🙇‍️/급식동생/3/3dk/s"

        // set Item info
        main_item_dog.setTag(R.attr.key_item_info, ItemInfo("댕댕이", 1, 1, "🐶"))
        main_item_baby.setTag(R.attr.key_item_info, ItemInfo("뽀시래기", 2, 1, "👶"))
        main_item_kids.setTag(R.attr.key_item_info, ItemInfo("급식동생", 3, 1, "🙇‍♂️"))

        // set clickListener
        main_soup_img.setOnClickListener {
            mSoupController.addDks(1L)
        }
        main_item_dog.setOnClickListener {
            makeDialog(it.getTag(R.attr.key_item_info) as ItemInfo)
        }
        main_item_baby.setOnClickListener {
            makeDialog(it.getTag(R.attr.key_item_info) as ItemInfo)
        }
        main_item_kids.setOnClickListener {
            makeDialog(it.getTag(R.attr.key_item_info) as ItemInfo)
        }
    }

    /**
     * 구매/취소 Dialog 생성
     */
    fun makeDialog(item: ItemInfo) {
        if (item == null) {
            return
        }

        val builder = AlertDialog.Builder(this)
        val dialogView = layoutInflater.inflate(R.layout.dialog_purchase, null)

        dialogView.dialog_title.text = "구매하기"
        dialogView.dialog_desc.text = getString(R.string.text_purchase, item.title)

        builder.setView(dialogView)
                .setPositiveButton(getString(R.string.btn_purchase)) { dialogInterface, i ->
                    if (mSoupController.getDks() >= item.price) {
                        // 떡국 소모
                        mSoupController.addDks(-item.price)
                        // 초당 생산 떡국 추가
                        mAutoMaking += item.count
                        // 구매한 아이템 추가
                        main_item_text.append(item.emoji)

                        Toast.makeText(applicationContext, getString(R.string.text_purchase_success, item.title), Toast.LENGTH_SHORT).show()

                    } else {
                        Toast.makeText(applicationContext, getString(R.string.text_purchase_failed, item.title), Toast.LENGTH_SHORT).show()
                    }
                }
                .setNegativeButton(R.string.btn_cancel) { dialogInterface, i ->
                    // 취소일 경우 동작 없음
                }
                .show()
    }

    override fun onResume() {
        super.onResume()
        Log.d(LOGD, "onResume() called. hasMessages: ${mMakerHandler.hasMessages(0)}")
        // 생성되어 있는 핸들러가 없을 경우에만 재시작
        if (!mMakerHandler.hasMessages(0)) {
            callMakerHandler()
        }
    }

    override fun onPause() {
        super.onPause()
        Log.d(LOGD, "onPause() called. hasMessages: ${mMakerHandler.hasMessages(0)}")
        if (mMakerHandler.hasMessages(0)) {
            mMakerHandler.removeMessages(0)
        }
    }

    override fun onSaveInstanceState(outState: Bundle, outPersistentState: PersistableBundle) {
        super.onSaveInstanceState(outState, outPersistentState)

    }
}
