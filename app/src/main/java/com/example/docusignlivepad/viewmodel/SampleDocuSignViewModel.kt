package com.example.docusignlivepad.viewmodel

import androidx.lifecycle.ViewModel


class SampleDocuSignViewModel(tour: String): ViewModel() {

    private var _tour: String
    val tour: String
        get() = _tour

    init {
        this._tour = tour
    }
}