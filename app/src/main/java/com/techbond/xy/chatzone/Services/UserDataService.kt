package com.techbond.xy.chatzone.Services

import android.graphics.Color
import java.util.*

/**
 * Created by XY on 3/23/2018.
 */
object UserDataService {

    var id =""
    var email=""
    var avatarColor =""
    var avatarName=""
    var name =""

    fun logout(){
        id =""
        avatarColor =""
        avatarName =""
        email=""
        name=""
        AuthService.authToken=""
        AuthService.userEmail=""
        AuthService.isLoggenIn=false
    }

    fun returnAvatarColor (components: String) : Int{
        val strippedColor = components
                .replace("[","")
                .replace("]","")
                .replace(",","")

        var  r = 0
        var g = 0
        var b= 0

        val scanner = Scanner(strippedColor)
        if (scanner.hasNext()){
            r=(scanner.nextDouble() * 225).toInt()
            g=(scanner.nextDouble() * 225).toInt()
            b=(scanner.nextDouble() * 225).toInt()
        }
        return Color.rgb(r,g,b)
    }

}