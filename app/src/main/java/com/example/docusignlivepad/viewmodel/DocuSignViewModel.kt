package com.example.docusignlivepad.viewmodel

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.docusign.androidsdk.DSEnvironment
import com.docusign.androidsdk.DocuSign
import com.docusign.androidsdk.exceptions.DocuSignNotInitializedException
import com.docusign.androidsdk.util.DSMode
import com.example.docusignlivepad.Utils.Constants
import com.example.docusignlivepad.data.DocuSignRepository
import com.example.docusignlivepad.model.TokenResponseModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import retrofit2.Response
import javax.inject.Inject

@HiltViewModel
class DocuSignViewModel @Inject constructor(
    private val repository: DocuSignRepository
) : ViewModel() {


    fun initializeDocuSign(applicationContext: Context) {
        try {
            DocuSign.init(
                applicationContext,
                Constants.DOCUSIGN_INTEGRATOR_KEY,
                Constants.CLIENT_SECRET_KEY,
                Constants.REDIRECT_URI,
                DSMode.DEBUG
            ).setEnvironment(DSEnvironment.DEMO_ENVIRONMENT)
        } catch (e: DocuSignNotInitializedException) {
            Toast.makeText(applicationContext, "Failed to Initialize DocuSign. " + e.message, Toast.LENGTH_LONG).show()
        }
    }

    fun fetchToken(authorization: String, requestBody: Map<String, String>) : String {
        var responseLiveData:String="";
        viewModelScope.launch {
            val response:String = repository.fetchToken()
            responseLiveData = response
        }
        return responseLiveData
    }
}