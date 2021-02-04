package com.suit.team.b.ui.profile.update

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.suit.team.b.data.local.SharedPref
import com.suit.team.b.data.model.UsersResponse
import com.suit.team.b.data.remote.ApiModule.service
import com.suit.team.b.utils.getErrorMessage
import com.suit.team.b.utils.getErrorThrowableCode
import com.suit.team.b.utils.getServiceErrorMsg
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.asRequestBody
import okhttp3.RequestBody.Companion.toRequestBody
import java.io.File

class UpdateViewModel : ViewModel() {

    private var compositeDis: CompositeDisposable? = null
    val resultRegister = MutableLiveData<UsersResponse>()
    var errorRegister = MutableLiveData<String>()

    override fun onCleared() {
        super.onCleared()
        compositeDis?.dispose()
    }

    fun fetchUserData() {
        val disposable =
            service.getUserData(SharedPref.token)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    resultRegister.value = it
                }, {
                    errorRegister.value =
                        getErrorMessage(it.getServiceErrorMsg(), it.getErrorThrowableCode())
                    it.printStackTrace()
                })
        compositeDis?.add(disposable)
    }

    fun putUser(username: String, email: String, file: File) {
        val photo: MultipartBody.Part = MultipartBody.Part.createFormData(
            "photo",
            file.name,
            file.asRequestBody("image/*".toMediaTypeOrNull())
        )
        SharedPref.token?.let { it ->
            val disposable = service.putUserPhoto(
                it,
                username.toRequestBody("multipart/form-data".toMediaTypeOrNull()),
                email.toRequestBody("multipart/form-data".toMediaTypeOrNull()),
                photo
            )
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    resultRegister.value = it
                }, {
                    errorRegister.value =
                        getErrorMessage(it.getServiceErrorMsg(), it.getErrorThrowableCode())
                    it.printStackTrace()
                })
            compositeDis?.add(disposable)
        }
    }


    fun putUser(username: String, email: String) {
        SharedPref.token?.let { it ->
            val disposable = service.putUserData(
                it,
                username.toRequestBody("multipart/form-data".toMediaTypeOrNull()),
                email.toRequestBody("multipart/form-data".toMediaTypeOrNull()),
            )
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    resultRegister.value = it
                }, {
                    errorRegister.value =
                        getErrorMessage(it.getServiceErrorMsg(), it.getErrorThrowableCode())
                    it.printStackTrace()
                })
            compositeDis?.add(disposable)
        }
    }
}
