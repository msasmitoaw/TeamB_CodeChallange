package com.suit.team.b.ui.profile.update

import com.suit.team.b.data.model.User

interface UpdateView {
    fun onShowSuccess(user: User)
    fun onUpdateSuccess()
    fun onFailed(toastString: String)
    fun onChangedDataReady(pass: String)
}