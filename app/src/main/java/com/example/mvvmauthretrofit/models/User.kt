package com.example.mvvmauthretrofit.models

import android.text.TextUtils
import android.util.Patterns
import androidx.databinding.BaseObservable
import org.w3c.dom.Text

class User(var Id_User:Int?,
           var user_Email : String?,
           var user_Password : String?) : BaseObservable() {
           val isDataValid: Boolean
           get() = (!TextUtils.isEmpty(user_Email))
                       && Patterns.EMAIL_ADDRESS.matcher(user_Email).matches() && (user_Password!!.length >6)
            fun getPassword() : String{
                return user_Password!!
            }
            fun getEmail() : String{
                return user_Email!!
            }
            fun setEmail(user_Email:String){
                this.user_Email = user_Email
            }
            fun setPassword(user_Password: String){
                this.user_Password = user_Password
            }

}