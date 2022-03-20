package com.sdimosikvip.news.base

import androidx.annotation.StringRes
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.hadilq.liveevent.LiveEvent
import com.sdimosikvip.common.exceptions.NoConnectionException
import com.sdimosikvip.news.R
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.flow.MutableSharedFlow
import timber.log.Timber

abstract class BaseViewModel() : ViewModel() {
    companion object {
        const val TAG = "BaseViewModel"
    }

    private val _state = MutableLiveData<State>(State.Init)
    val state: LiveData<State> = _state

    private val _action = LiveEvent<Action>()
    val action: LiveData<Action> = _action

    protected val handlerException = CoroutineExceptionHandler { _, exception ->
        run {
            Timber.tag(TAG).e(exception)
            val messageRes = getMessageExceptionRes(exception)
            setToastMessage(messageRes)
            hideLoading()
        }
    }

    protected fun setLoading() {
        _state.postValue(State.IsLoading(true))
    }

    protected fun hideLoading() {
        _state.postValue(State.IsLoading(false))
    }

    private fun setToastMessage(@StringRes messageRes: Int) {
        _action.postValue(Action.ShowToast(messageRes))
    }

    private fun getMessageExceptionRes(cause: Throwable): Int {
        return when (cause) {
            is NoConnectionException -> R.string.network_no_connection_error_message
            else -> R.string.generic_error_message
        }
    }

    sealed class State {
        object Init : State()
        data class IsLoading(val isLoading: Boolean) : State()
    }

    sealed class Action {
        data class ShowToast(@StringRes val messageRes: Int) : Action()
    }
}