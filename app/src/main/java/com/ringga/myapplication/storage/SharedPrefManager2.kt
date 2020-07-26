package com.ringga.myapplication.storage

import android.content.Context
import android.content.SharedPreferences
import com.ringga.myapplication.models.User
import com.ringga.myapplication.models.UserDosen


class SharedPrefManager2 private constructor(mCtx: Context) {
    private val mCtx: Context
    fun saveUserDosen(user: UserDosen) {
        val sharedPreferences: SharedPreferences = mCtx.getSharedPreferences(
            SHARED_PREF_NAME,
            Context.MODE_PRIVATE
        )
        val editor = sharedPreferences.edit()
        editor.putInt("id", user.id)
        editor.putString("nama", user.nama)
        editor.putString("email", user.email)
        editor.putInt("nidn", user.nidn)
        editor.putInt("nrp", user.nrp)
        editor.putString("jabatan", user.jabatan)
        editor.putString("keterangan", user.keterangan)
        editor.putString("image", user.image)
        editor.putInt("role", user.role)
        editor.apply()
    }

    val isLoggedIn: Boolean
        get() {
            val sharedPreferences: SharedPreferences = mCtx.getSharedPreferences(
                SHARED_PREF_NAME,
                Context.MODE_PRIVATE
            )
            return sharedPreferences.getInt("id", -1) != -1
        }

    val userDosen: UserDosen
        get() {
            val sharedPreferences: SharedPreferences = mCtx.getSharedPreferences(
                SHARED_PREF_NAME,
                Context.MODE_PRIVATE
            )
            return UserDosen(
                sharedPreferences.getInt("id", -1),
                sharedPreferences.getString("nama", null)!!,
                sharedPreferences.getString("email", null)!!,
                sharedPreferences.getInt("nidn", -1),
                sharedPreferences.getInt("nrp", -1),
                sharedPreferences.getString("jabatan", null)!!,
                sharedPreferences.getString("keterangan", null)!!,
                sharedPreferences.getString("image", null)!!,
                sharedPreferences.getInt("role", -1)
            )
        }

    fun clear() {
        val sharedPreferences: SharedPreferences = mCtx.getSharedPreferences(
            SHARED_PREF_NAME,
            Context.MODE_PRIVATE
        )
        val editor = sharedPreferences.edit()
        editor.clear()
        editor.apply()
    }

    companion object {
        private const val SHARED_PREF_NAME = "my_shared_preff2"
        private var mInstance: SharedPrefManager2? = null
        @Synchronized
        fun getInstance(mCtx: Context): SharedPrefManager2? {
            if (mInstance == null) {
                mInstance = SharedPrefManager2(mCtx)
            }
            return mInstance as SharedPrefManager2
        }
    }

    init {
        this.mCtx = mCtx
    }
}