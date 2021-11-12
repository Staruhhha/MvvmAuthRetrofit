package com.example.mvvmauthretrofit

import android.content.Context
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import androidx.lifecycle.ViewModel
import com.example.mvvmauthretrofit.models.User
import com.example.mvvmauthretrofit.services.ApiServices
import com.example.mvvmauthretrofit.services.ServiceBuilder
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginViewModel(private val listener:LoginResultCallBacks, context: Context):ViewModel() {

    private var user : User
        init {
            this.user = User(0,"","")
        }

    val emailRegTextWatcher: TextWatcher
        get() = object:TextWatcher{
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                user.setEmail(s.toString())
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

            }

            override fun afterTextChanged(s: Editable?) {
                user.setEmail(s.toString())
            }

        }
    val passwordRegTextWatcher : TextWatcher
        get() = object:TextWatcher{
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

            }

            override fun afterTextChanged(s: Editable?) {
                user.setPassword(s.toString())
            }

        }
    fun onLogInClick(v: View){
        if(user.isDataValid){
            val userServices = ServiceBuilder.buildServices(ApiServices::class.java)
            val requestCall = userServices.getUserByEmail(user.getEmail())

            requestCall.enqueue(object :Callback<User>{
                override fun onResponse(call: Call<User>, response: Response<User>) {
                    if (response.isSuccessful){
                        val userLog = response.body()
                        if (userLog!!.user_Password!!.equals(user.getPassword())) {
                            listener.onSuccess("Авторизация прошла")
                        }else {
                            listener.onError("Неверный пароль")
                        }
                    }
                }

                override fun onFailure(call: Call<User>, t: Throwable) {
                    listener.onError("Пользователя с такой почтой не существует")
                }

            })
        }else{
            listener.onError("Неверно заполнены данные")
        }
    }

}