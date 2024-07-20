package com.example.tcnh.data.models

import android.util.Log
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Patient(
    @PrimaryKey val contact : Long,
    val name : String,
    val gender : String,
    val age : Int,
    val emergencyContact : Long
)
