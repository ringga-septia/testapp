package com.ringga.myapplication.storage

import android.content.Context
import android.content.SharedPreferences
import com.ringga.myapplication.models.User


class SharedPrefManager private constructor(mCtx: Context) {
    private val mCtx: Context
    fun saveUser(user: User) {
        val sharedPreferences: SharedPreferences = mCtx.getSharedPreferences(
            SHARED_PREF_NAME,
            Context.MODE_PRIVATE
        )
        val editor = sharedPreferences.edit()
        editor.putInt("id", user.id)
        editor.putString("email", user.email)
        editor.putString("name", user.name)
        editor.putInt("nim", user.nim)
        editor.putString("prodi", user.prodi)
        editor.putInt("semester", user.semerter)
        editor.putString("image", user.image)
        editor.putInt("thn", user.thn)
        editor.putInt("role", user.role)
        editor.putInt("id_class", user.id_class)
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

    val user: User
        get() {
            val sharedPreferences: SharedPreferences = mCtx.getSharedPreferences(
                SHARED_PREF_NAME,
                Context.MODE_PRIVATE
            )
            return User(
                sharedPreferences.getInt("id", -1),
                sharedPreferences.getString("email", null)!!,
                sharedPreferences.getString("name", null)!!,
                sharedPreferences.getInt("nim", -1),
                sharedPreferences.getString("prodi", null)!!,
                sharedPreferences.getInt("semester", -1),
                sharedPreferences.getString("image", null)!!,
                sharedPreferences.getInt("thn", -1),
                sharedPreferences.getInt("role", -1),
                sharedPreferences.getInt("id_class", -1)
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
        private const val SHARED_PREF_NAME = "my_shared_preff"
        private var mInstance: SharedPrefManager? = null
        @Synchronized
        fun getInstance(mCtx: Context): SharedPrefManager? {
            if (mInstance == null) {
                mInstance = SharedPrefManager(mCtx)
            }
            return mInstance as SharedPrefManager
        }
    }

    init {
        this.mCtx = mCtx
    }
}