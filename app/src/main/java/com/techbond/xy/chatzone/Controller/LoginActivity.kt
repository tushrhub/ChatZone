package com.techbond.xy.chatzone.Controller
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import com.techbond.xy.chatzone.R
import com.techbond.xy.chatzone.Services.AuthService
import kotlinx.android.synthetic.main.activity_create_user.*
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        loginSpinner.visibility= View.INVISIBLE
    }

    fun loginLoginBtnClicked(view: View) {
        enableSpinner(true)
        val email = loginEmailText.text.toString()
        val password = loginPasswordText.text.toString()
        hideKeyBoard()

         if(email.isNotEmpty()&& password.isNotEmpty()){       AuthService.loginUser(this, email, password) { loginSuccess ->
             if (loginSuccess) {
                 AuthService.findUserByEmail(this) { findSuccess ->
                     if (findSuccess) {
                         finish()
                     }
                     else{
                         Toast.makeText(this, "Login Succes!!!!", Toast.LENGTH_SHORT).show()
                     }
                 }
             }else{
                 errorToast()
             }

         }
         }else{
             Toast.makeText(this, "Please check email & Password", Toast.LENGTH_SHORT).show()
         }

    }

    fun loginCreateUserBtnClicked(view: View) {
        val createUserIntent = Intent(this, CreateUserActivity::class.java)
        startActivity(createUserIntent)
        finish()
    }
    fun errorToast() {
        Toast.makeText(this, "Something went wrong, Retry",
                Toast.LENGTH_SHORT).show()
        enableSpinner(false)
    }

    fun enableSpinner(enable: Boolean) {
        if (enable) {
            loginSpinner.visibility = View.VISIBLE
        } else {
            loginSpinner.visibility = View.INVISIBLE
        }
        loginLoginBtn.isEnabled = !enable
        loginCreateUserBtn.isEnabled = !enable

    }
    fun hideKeyBoard (){
        val inputManager = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        if (inputManager.isAcceptingText){
            inputManager.hideSoftInputFromWindow(currentFocus.windowToken, 0)
        }
    }
}

