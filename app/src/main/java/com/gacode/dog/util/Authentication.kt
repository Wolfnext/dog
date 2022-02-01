package com.gacode.dog.util

import android.content.Context
import android.util.Log
import com.gacode.dog.model.token
import com.gacode.dog.model.user
import com.google.gson.Gson
import java.util.*


object Authentication {

    private val authentication = "authentication"
    private val Token = "token"
    private val User= "user"

    private fun put(context: Context, obj: token): Boolean {
        val preferences = context.getSharedPreferences(authentication, Context.MODE_PRIVATE)
        val editor = preferences.edit()
        return editor.putString(Token, Gson().toJson(obj)).commit()
    }

    private fun put(context: Context, obj: user): Boolean {
        val preferences = context.getSharedPreferences(authentication, Context.MODE_PRIVATE)
        val editor = preferences.edit()
        return editor.putString(User, Gson().toJson(obj)).commit()
    }

    fun get(context: Context): token? {
        val preferences = context.getSharedPreferences(authentication, Context.MODE_PRIVATE)
        val json = preferences.getString(Token, null)
        if(json != null)return Gson().fromJson(json, token::class.java)
        return null
    }

    fun getAccessToken(context: Context): String {
        val preferences = context.getSharedPreferences(authentication, Context.MODE_PRIVATE)
        val json = preferences.getString(Token, null)
        if(json != null)return Gson().fromJson(json, token::class.java).access_token
        return ""
    }

    fun getUser(context: Context): user? {
        val preferences = context.getSharedPreferences(authentication, Context.MODE_PRIVATE)
        val json = preferences.getString(User, null)
        if(json != null)return Gson().fromJson(json, user::class.java)
        return null
    }

    fun save(context: Context, obj: token): Boolean {
        val calendar = GregorianCalendar.getInstance()
        var expiresIn: Long = calendar.time.time
       // expiresIn += obj.expire_date * 1000
        //obj.expire_date = expiresIn

        return put(context, obj)
    }


    fun save(context: Context, obj: user): Boolean {
        return put(context, obj)
    }

    fun isExpired(context: Context): Boolean {
        val token = get(context)
        if(token != null) {
            val calendar = GregorianCalendar.getInstance()
            val currentTime = calendar.time.time
            val expiresIn = JWTUtil.getExpiredTime(context);
            if (expiresIn == null) throw WithoutAuthenticatedException()
            return currentTime > expiresIn!!

        } else {
            throw WithoutAuthenticatedException()
        }
    }

    fun isAuthenticated(context: Context): Boolean = !isExpired(context)

    class WithoutAuthenticatedException : Exception()

    fun getRefresh(context: Context): String {
        val token = get(context)
        if(token != null){
            return token.refresh_token
        }else{
            throw WithoutAuthenticatedException()
        }
    }

    fun delete(context: Context) {
        val preferences = context.getSharedPreferences(authentication, Context.MODE_PRIVATE)
        val editor = preferences.edit()
        editor.remove(Token).apply()
    }
}