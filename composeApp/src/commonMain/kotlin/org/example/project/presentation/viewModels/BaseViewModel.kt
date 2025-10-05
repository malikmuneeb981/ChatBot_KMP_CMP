package org.example.project.presentation.viewModels

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.cancel

open class BaseViewModel {
    // SupervisorJob so one failure doesn't cancel everything
    private val job = SupervisorJob()

    // You can choose Dispatchers.Default or Dispatchers.Main depending on usage
    protected val viewModelScope = CoroutineScope(Dispatchers.Main + job)

    open fun onCleared() {
        // Cancel coroutines when ViewModel is destroyed
        viewModelScope.cancel()
    }
}