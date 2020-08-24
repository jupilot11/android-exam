package com.joeydee.android_exam.objects

import androidx.annotation.NonNull
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "user_table")
class UserResult(
    @NonNull @PrimaryKey(autoGenerate = true)
    var primaryKey: Int? = null
) {
    var cell: String? = null
    var email: String? = null
    var gender: String? = null
    var nat: String? = null
    var phone: String? = null
    var id: Id? = null
    var dob: Dob? = null
    var location: Location? = null
    var name: Name? = null
    var picture: Picture? = null
}