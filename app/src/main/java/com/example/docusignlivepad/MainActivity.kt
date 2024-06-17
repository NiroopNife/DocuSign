package com.example.docusignlivepad

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.viewModelScope
import com.docusign.androidsdk.DSEnvironment
import com.docusign.androidsdk.DocuSign
import com.docusign.androidsdk.exceptions.DocuSignNotInitializedException
import com.docusign.androidsdk.util.DSMode
import com.example.docusignlivepad.Utils.Constants
import com.example.docusignlivepad.model.TokenResponseModel
import com.example.docusignlivepad.viewmodel.DocuSignViewModel
import com.example.docusignlivepad.viewmodel.SampleDocuSignViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var docuSignViewModel: DocuSignViewModel
    private lateinit var sampleDocuSignViewModel: SampleDocuSignViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val hello = findViewById<TextView>(R.id.helloWorld)
        hello.setOnClickListener {
            val fragment = DocumentsFragment()
            val transaction = supportFragmentManager.beginTransaction()
            transaction.replace(R.id.fragment_container, fragment)
            transaction.addToBackStack(null)
            transaction.commit()
        }

        sampleDocuSignViewModel = SampleDocuSignViewModel("token");
        val tokenString=sampleDocuSignViewModel.tour

        Log.d("MainActivity", "Token Response sampleDocuSignViewModel is $tokenString")
        docuSignViewModel = ViewModelProvider(this)[DocuSignViewModel::class.java]
        docuSignViewModel.initializeDocuSign(applicationContext)

        val authorization = "MjAzZmY3NTEtOGEzZC00ODA5LThkZjItYTRiMDgwMmM3OGY0OjY2MTFhNDFhLTRiNzgtNGRlNC1hN2E5LTliYTc1YTRjMGVkZg=="
        val requestBody = mapOf("code" to "eyJ0eXAiOiJNVCIsImFsZyI6IlJTMjU2Iiwia2lkIjoiNjgxODVmZjEtNGU1MS00Y2U5LWFmMWMtNjg5ODEyMjAzMzE3In0.AQsAAAABAAYABwAASpZ0cIzcSAgAANYcvHCM3EgCABrUApGpBqdHiiGHdmr19VoVAAEAAAAYAAEAAAAFAAAADQAkAAAAMjAzZmY3NTEtOGEzZC00ODA5LThkZjItYTRiMDgwMmM3OGY0IgAkAAAAMjAzZmY3NTEtOGEzZC00ODA5LThkZjItYTRiMDgwMmM3OGY0MAAASpZ0cIzcSBIAAQAAAAsAAABpbnRlcmFjdGl2ZTcAdCFW71lmiUmxJgbFl9SiPg.t7VNnauRstGyaD7B5XisVAcoUqZSPh_0JrsjNsFmIL1k_XjdG4FhzKQBuTUMHhL7SOKkVygmWscCA4opcnEBmcON3_nBLaG-AQXhTKXVuW5QMA1ds4dB-RuXQ_Ln0jf_gSTc3TmK05ASirAiGno2fsvnVpmxQlFie2fM8JdFbVPuMNN4jUTwqlh9Yzuw_exBTpFaTWrHkFBSMHk99eoDmgFxtdSDB6z3ev2h7TUN74e6nSx3BEisdvLvKs_e94eqRwy-kgyGxWHUYCbA24rxBsCoAfNBSteVPJe7dGXhSfM9X19k0MoQpCYRePicWaOm1Y3kzeK37uriBROBgXP3_Q", "grant_type" to "authorization_code")
       val tokenResponse=  docuSignViewModel.fetchToken(authorization, requestBody);
        Log.d("MainActivity", "Token Response is $tokenResponse")
//        docuSignViewModel.fetchToken(authorization, requestBody).observe(this, Observer { response ->
//            if (response.isSuccessful) {
//                val tokenResponse = response.body()
//                Log.d("MainActivity", "Token Response is $tokenResponse")
//            } else {
//                Log.d("MainActivity", "Error")
//            }
//        })
    }

}