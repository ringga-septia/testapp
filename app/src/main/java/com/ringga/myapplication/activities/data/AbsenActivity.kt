package com.ringga.myapplication.activities.data

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.zxing.Result
import me.dm7.barcodescanner.zxing.ZXingScannerView

class AbsenActivity : AppCompatActivity(), ZXingScannerView.ResultHandler{
    var mScannerView: ZXingScannerView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mScannerView = ZXingScannerView(this)
        setContentView(mScannerView)
    }

   override fun handleResult(result: Result) {
        val getInput = result.text.toString()
        val IntentSent = Intent(this@AbsenActivity, HadirActivity::class.java)
        IntentSent.putExtra("inputBc", getInput)
        finish()
        startActivity(IntentSent)
    }

    override fun onResume() {
        super.onResume()
        mScannerView!!.setResultHandler(this)
        mScannerView!!.startCamera()
    }

    override fun onPause() {
        super.onPause()
        mScannerView!!.stopCamera()
    }
}