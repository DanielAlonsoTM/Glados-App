package com.samuraitech.gladosapp.ui.login

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatButton
import androidx.fragment.app.FragmentActivity
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.android.gms.tasks.Task
import com.samuraitech.gladosapp.R
import com.samuraitech.gladosapp.api.UserRestAPI
import com.samuraitech.gladosapp.model.User
import com.samuraitech.gladosapp.ui.main.MainActivity
import com.samuraitech.gladosapp.utils.Constants
import com.samuraitech.gladosapp.utils.Constants.RC_SIGN_IN
import com.samuraitech.gladosapp.utils.GraphicsUtilities.makeTransparentActionBar
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        makeTransparentActionBar(window, supportActionBar)

        val buttonSingUpGoogle = findViewById<AppCompatButton>(R.id.btn_signup_google)

        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestEmail()
            .requestId()
            .requestProfile()
            .build()

        val mGoogleSignInClient = GoogleSignIn.getClient(this, gso)

        buttonSingUpGoogle.setOnClickListener {
            signIn(mGoogleSignInClient)
        }
    }

    override fun onStart() {
        super.onStart()

        val account = GoogleSignIn.getLastSignedInAccount(this)

        if (account != null) {
            insertUser(account)

            startActivity(Intent(this, MainActivity::class.java))
        }
    }

    private fun insertUser(account: GoogleSignInAccount) {
        try {
            val user = User("", account.id!!, account.displayName!!, account.email!!)
            UserRestAPI().insertUser(user).enqueue(object : Callback<User> {
                override fun onFailure(call: Call<User>?, t: Throwable?) {
                    t!!.printStackTrace()
                }

                override fun onResponse(call: Call<User>?, response: Response<User>?) {
                    if (response!!.body() == null) {
                        Log.e(Constants.TAG_NULL_RESPONSE, "$response")
                    } else {
                        Log.d("RESPONSE", "$response")
                    }
                }
            })
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    private fun signIn(gClient: GoogleSignInClient) {
        val signInIntent: Intent = gClient.signInIntent
        startActivityForResult(signInIntent, RC_SIGN_IN)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == RC_SIGN_IN) {
            // The Task returned from this call is always completed, no need to attach
            // a listener.
            val task: Task<GoogleSignInAccount> = GoogleSignIn.getSignedInAccountFromIntent(data)
            handleSignInResult(task)
        }
    }

    private fun handleSignInResult(completedTask: Task<GoogleSignInAccount>) {
        try {

            startActivity(Intent(this, MainActivity::class.java))
        } catch (e: ApiException) {
            // The ApiException status code indicates the detailed failure reason.
            // Please refer to the GoogleSignInStatusCodes class reference for more information.
            Log.w("HomeFragment Result", "signInResult:failed code=" + e.statusCode)
//            updateUI(null)
        }
    }
}
