package com.gacode.dog.util

import android.content.Context
import android.util.Log
import com.auth0.android.jwt.JWT
import com.gacode.dog.model.token
import com.gacode.dog.view.profile.ProfileActivity


object JWTUtil {
   private var jwt =  JWT("eyJhbGciOiJIUzI1NiJ9.e30.ZRrHA1JJJW8opsbCGfG_HACGpVUMN_a9IV7pAx_Zmeo"); //  empty jwt token

    fun decodeJWT(context: Context, claimName: String) :String?{
        var tk = Authentication.get(context)
        Log.d("token",tk.toString())
        if (tk != null) {
            jwt = JWT(tk.access_token)
        }

        return jwt.getClaim(claimName).asString()
    }


    fun getEmail(context: Context) : String?{
        return decodeJWT(context, "email")
    }

    fun getType(context: Context) : String?{
        return decodeJWT(context, "type")
    }

    fun getUID(context: Context) : String?{
        return decodeJWT(context, "uid")
    }

    fun getExpiredTime(context: Context) :Long ?{
        return decodeJWT(context, "exp")?.toLong()
    }


}