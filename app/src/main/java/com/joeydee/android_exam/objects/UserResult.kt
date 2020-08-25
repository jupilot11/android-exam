package com.joeydee.android_exam.objects

import androidx.annotation.NonNull
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity(tableName = "user_table")
class UserResult(
    @NonNull @PrimaryKey
    var primaryKey: String = UUID.randomUUID().toString()
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