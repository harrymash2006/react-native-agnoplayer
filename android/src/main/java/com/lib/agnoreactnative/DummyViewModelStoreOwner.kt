package com.lib.agnoreactnative

import androidx.lifecycle.ViewModelStore
import androidx.lifecycle.ViewModelStoreOwner

class DummyViewModelStoreOwner : ViewModelStoreOwner {
    override val viewModelStore = ViewModelStore()
}