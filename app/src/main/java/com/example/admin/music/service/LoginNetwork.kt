package com.example.admin.music.service

import com.example.admin.music.data.LoginBean
import retrofit2.Call

class LoginNetwork private constructor() : BasedNetwork() {
    private val login: Api.Login = retrofit.create(Api.Login::class.java)

    fun loginCall(phone: String, password: String): Call<LoginBean.LoginData> {
        return login.login(phone, password)
    }

    companion object {
        val instance: LoginNetwork
            get() = LoginNetwork()
    }
}