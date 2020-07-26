package com.ringga.myapplication.activities.data

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.ringga.myapplication.R
import com.ringga.myapplication.activities.HomeActivity
import com.ringga.myapplication.api.RetrofitClient
import com.ringga.myapplication.models.LoginResponse
import com.ringga.myapplication.models.ResponAbsen
import com.ringga.myapplication.storage.SharedPrefManager
import com.ringga.myapplication.storage.SharedPrefManager2
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class KehadiranActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_kehadiran)

      //  Tampil_absen()
    }
//    private fun Tampil_absen(){
//        val mypref = SharedPrefManager.getInstance(this)!!.user
//        val nim = mypref.nim
//
//        RetrofitClient.instance.absen(nim)
//            .enqueue(object : Callback<ResponAbsen> {
//                override fun onFailure(call: Call<ResponAbsen>, t: Throwable) {
//                    Toast.makeText(applicationContext, t.message, Toast.LENGTH_LONG).show()
//                }
//                override fun onResponse(call: Call<ResponAbsen>, response: Response<ResponAbsen>) {
//                    TODO("Not yet implemented")
//                }
//
//
//            })
//    }

}
