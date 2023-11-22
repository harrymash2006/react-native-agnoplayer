package com.lib.agnoreactnative

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob

class CustomViewCoroutineScope : CoroutineScope {
    private val job = SupervisorJob()
    override val coroutineContext = Dispatchers.Main + job

    fun cancel() {
        job.cancel()
    }
}
