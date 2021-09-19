package net.larntech.firebase

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.BoringLayout
import android.util.Log
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.ktx.Firebase
import net.larntech.firebase.databinding.ActivityRegisterBinding

class RegisterActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRegisterBinding

    private lateinit var auth: FirebaseAuth;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        initData();
    }

    private fun initData(){
        auth = FirebaseAuth.getInstance();

        binding.btnSignUp.setOnClickListener {
            //create user
            var email = binding.etEmail.text.toString()
            var password = binding.etPassword.text.toString()
            var cpassword = binding.etcPassword.text.toString()
            if(email.isNotEmpty() && password.isNotEmpty()){
                if(password == cpassword)
                createUser(email,password)
                else
                    Toast.makeText(this," Password mismatch ", Toast.LENGTH_LONG).show()
            }else{
                Toast.makeText(this," All inputs required ...",Toast.LENGTH_LONG).show()
            }

        }

        binding.llAccount.setOnClickListener {
            startActivity(Intent(this,MainActivity::class.java))
            finish()
        }

    }

    private fun createUser(email: String, password: String){
        auth.createUserWithEmailAndPassword(email,password)
            .addOnCompleteListener {
                createUserResult(it.isSuccessful)
            }

    }


    private fun createUserResult(isSuccess: Boolean){
        if(isSuccess){
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }else{
            Toast.makeText(this," Unable to create user ...",Toast.LENGTH_LONG).show()
        }
    }

}