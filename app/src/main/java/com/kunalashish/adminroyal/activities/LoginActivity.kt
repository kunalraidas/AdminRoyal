package com.kunalashish.adminroyal.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Patterns
import androidx.appcompat.app.AlertDialog
import com.kunalashish.adminroyal.databinding.ActivityLoginBinding


class LoginActivity : AppCompatActivity() {
        private lateinit var binding : ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        emailFocusListner()
        PasswordFocusListner()

        binding.btnLogin.setOnClickListener {
            submitForm()

        }
        binding.haventAccount.setOnClickListener {
            startActivity(Intent(this, RegisterActivity::class.java))
        }




    }

    private fun submitForm() {
        val validEmail = binding.emailContainor.helperText == null
        val validPassword = binding.passwordContainor.helperText == null

        if (validEmail && validPassword)
        {
            reserForm()
        }
        else
        {
            invalidForm()
        }
    }

    private fun invalidForm() {
        var message = ""
        if (binding.emailContainor.helperText == null) {
            message += "\n \n Email : " + binding.emailContainor.helperText
        }
        if (binding.passwordContainor.helperText == null){
            message += "\n \n Password" + binding.passwordContainor.helperText
        }

        AlertDialog.Builder(
            this
        ).setTitle("Please Full fill Details")
            .setMessage(message)
            .setPositiveButton("Okay"){ _,_->
                // do nothing
            }
    }

    private fun reserForm() {
        startActivity(Intent(this, HomeActivity::class.java))
    }

    private fun emailFocusListner()
    {
        binding.edtEmailAddress.setOnFocusChangeListener { _, focused ->

            if(!focused){
                binding.emailContainor.helperText = validEmail()
            }
        }
    }

    private fun validEmail() : String?{
        val emailText = binding.edtEmailAddress.text.toString()
        if (!Patterns.EMAIL_ADDRESS.matcher(emailText).matches()){
            return "Invalid Email Address"
        }
        return null
    }

    private fun PasswordFocusListner()
    {
        binding.edtPassword.setOnFocusChangeListener { _, focused ->
            if (!focused)
            {
                binding.passwordContainor.helperText = validPassword()
            }

        }
    }

    private fun validPassword(): String? {
        val password = binding.edtPassword.text.toString()
        if (password.length < 8)
        {
            return "Minimum 8 Character Password"
        }
        if (password.length > 16)
        {
            return "Maximum 16 Character Password"
        }
        if (password.matches(".*/[A-Z].*".toRegex()))
        {
            return "Must Contain 1 UpperCase Letter"
        }
        if (password.matches(".*/[a-z].*".toRegex()))
        {
            return "Must Contain 1 Lowercase Letter"
        }
        if (password.matches(".*/[@#$%^&+-].*".toRegex()))
        {
            return "Must Contain 1 Special Character"
        }

        return null
    }


}