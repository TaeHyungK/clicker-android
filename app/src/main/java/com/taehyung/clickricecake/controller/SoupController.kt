package com.taehyung.clickricecake.controller

import android.util.Log
import android.widget.TextView
import com.taehyung.clickricecake.util.AndroidUtils
import kotlin.properties.Delegates

class SoupController private constructor(){
    val LOGD = "SoupController"

    // 현재 만든 떡국 갯수
    var mMakedTotal: Long by Delegates.observable(0L) {
        property, oldValue, newValue
        ->
        run {
            mMainSoupCount?.text = newValue.toString()
        }
    }

    /**
     * singleton instance
     */
    companion object {
        @Volatile
        private var instance: SoupController? = null
        private var mMainSoupCount: TextView? = null

        @JvmStatic
        fun getInstance(targetView: TextView): SoupController = instance?: synchronized(this) {
            mMainSoupCount = targetView

            instance?: SoupController().also {
                instance = it
            }

        }
    }

    @Synchronized
    fun getDks(): Long {
        return mMakedTotal
    }

    @Synchronized
    fun addDks(value: Int) {
        addDks(value.toLong())
    }

    @Synchronized
    fun addDks(value: Long) {
        Log.d(LOGD, "addDks() called. mMakedTotal: $mMakedTotal, getText: ${mMainSoupCount?.text}, addValue: $value")
        if (checkUi()) return

        mMakedTotal = AndroidUtils.getLongValue(mMainSoupCount?.text.toString(), mMakedTotal)
        mMakedTotal += value
    }

    private fun checkUi(): Boolean {
        return mMainSoupCount == null
    }

}