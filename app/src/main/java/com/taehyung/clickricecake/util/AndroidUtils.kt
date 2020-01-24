package com.taehyung.clickricecake.util

import android.text.TextUtils

object AndroidUtils {
    fun getIntValue(intString: String): Int {
        return getIntValue(intString, 0)
    }

    fun getIntValue(intString: String, defaultValue: Int): Int {
        var result: Int = defaultValue

        if (!TextUtils.isEmpty(intString) && intString.matches("^[0-9]*$".toRegex())) {
            result = intString.toInt()
        }

        return result
    }

    fun getLongValue(longString: String): Long {
        return getLongValue(longString, 0)
    }

    fun getLongValue(longString: String, defaultValue: Long): Long {
        var result: Long = defaultValue

        if (!TextUtils.isEmpty(longString) && longString.matches("^[0-9]*$".toRegex())) {
            result = longString.toLong()
        }

        return result
    }



}