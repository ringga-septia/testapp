package com.ringga.myapplication.activities.data

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.ProgressBar
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.airbnb.lottie.LottieAnimationView
import com.ringga.myapplication.R
import com.ringga.myapplication.activities.HomeActivity
import com.ringga.myapplication.api.RetrofitClient
import com.ringga.myapplication.models.DefaulResponse
import com.ringga.myapplication.storage.SharedPrefManager
import kotlinx.android.synthetic.main.activity_hadir.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HadirActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_hadir)



        val mypref = SharedPrefManager.getInstance(this)!!.user
        val nim = mypref.nim
        val prodi = mypref.prodi
        val id_class = mypref.id_class
        val extras = intent.extras
        val code = extras!!.getString("inputBc")
        val absen = findViewById<ProgressBar>(R.id.pb_absen)
        val anim = findViewById<LottieAnimationView>(R.id.l_anim)
        if (code != null) {

            absen.setVisibility(View.VISIBLE)
            RetrofitClient.instance.absen(code, nim, id_class, prodi)
                .enqueue(object : Callback<DefaulResponse> {
                    override fun onFailure(call: Call<DefaulResponse>, t: Throwable) {
                        Toast.makeText(applicationContext, t.message, Toast.LENGTH_LONG).show()
                        absen.setVisibility(View.GONE)
                        anim.setVisibility(View.GONE)
                    }

                    override fun onResponse(
                        call: Call<DefaulResponse>,
                        response: Response<DefaulResponse>
                    ) {
                        if(response.isSuccessful){

                        Toast.makeText(applicationContext, response.body()?.message, Toast.LENGTH_LONG).show()
                        val intent = Intent(applicationContext,  HomeActivity::class.java)
                        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                        absen.setVisibility(View.GONE)
                            anim.setVisibility(View.GONE)
                        startActivity(intent)
                        }else{
                            if(response.code() == 400) {
                                absen.setVisibility(View.GONE)
                                Toast.makeText(
                                    applicationContext,
                                    "anda gagal untuk absen",
                                    Toast.LENGTH_LONG
                                ).show()
                                anim.setAnimation("26594-add-text.json")
                                anim.playAnimation()
                                anim.loop(true)
                            }else if (response.code() == 401) {
                                absen.setVisibility(View.GONE)
                                Toast.makeText(
                                    applicationContext,
                                    "anda sudah absen",
                                    Toast.LENGTH_LONG
                                ).show()
                                anim.setAnimation("25920-questions.json")
                                anim.playAnimation()
                                anim.loop(true)
                            }else {
                                absen.setVisibility(View.GONE)
                                Toast.makeText(
                                    applicationContext,
                                    "data gagal untuk absen",
                                    Toast.LENGTH_LONG
                                ).show()
                                anim.setAnimation("17801-stay-at-home.json")
                                anim.playAnimation()
                                anim.loop(true)
                            }
                        }
                    }
                })
        }else{
            absen.setVisibility(View.GONE)
            Toast.makeText(applicationContext, "Absen Anda Gagal", Toast.LENGTH_LONG).show()
        }

    }

}
