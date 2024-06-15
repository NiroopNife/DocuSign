package com.example.docusignlivepad.data


interface DocuSignRepository {
    fun fetchToken():String;
}