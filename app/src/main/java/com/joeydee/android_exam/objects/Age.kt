package com.joeydee.android_exam.objects

 class Age(var days: Int = 0,var months: Int = 0,var years: Int = 0) {


    override fun toString(): String {
        return "$years Years, $months Months, $days Days"
    }
}