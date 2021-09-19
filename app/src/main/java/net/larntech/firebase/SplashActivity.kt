package net.larntech.firebase

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import com.google.firebase.auth.FirebaseAuth
import net.larntech.firebase.databinding.ActivityMainBinding

class SplashActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding;

    private lateinit var auth: FirebaseAuth;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        initData();
    }

    private fun initData(){
        auth = FirebaseAuth.getInstance();

        Handler().postDelayed({
            checkCurrentUser();
        },1500);
        checkCurrentUser();

    }

    private fun checkCurrentUser(){
        val currentUser = auth.currentUser;
        if(currentUser != null){
            startActivity(Intent(this,DashboardActivity::class.java))
            finish()
        }else{
            startActivity(Intent(this,MainActivity::class.java))
        }
    }


}