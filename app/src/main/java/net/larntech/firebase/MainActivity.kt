package net.larntech.firebase

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import net.larntech.firebase.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private lateinit var auth: FirebaseAuth;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view  = binding.root;
        setContentView(view)
        initData();
    }

    private fun initData(){
        auth = FirebaseAuth.getInstance();

        binding.btnLogin.setOnClickListener {
            //auth user
            val email = binding.etEmail.text.toString();
            var password = binding.etPassword.text.toString();

            if(email.isNotEmpty() && password.isNotEmpty()) {
                authUser(email,password)
            }else{
                Toast.makeText(this,"All inputs required ...",Toast.LENGTH_LONG).show()
            }
        }

        binding.createAccount.setOnClickListener {
            startActivity(Intent(this,RegisterActivity::class.java))
            finish()
        }

    }

    private fun authUser(email: String, password: String){
        auth.signInWithEmailAndPassword(email,password)
            .addOnCompleteListener {
                loginResult(it.isSuccessful);
            }

    }

    private fun loginResult(isSuccess: Boolean){
        if(isSuccess){
            startActivity(Intent(this, DashboardActivity::class.java))
            finish()
        }else{
            Toast.makeText(this," Authentication failed ",Toast.LENGTH_LONG).show()
        }

    }
}