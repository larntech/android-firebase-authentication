package net.larntech.firebase

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.firebase.auth.FirebaseAuth
import net.larntech.firebase.databinding.ActivityDashboardBinding

class DashboardActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDashboardBinding
    private lateinit var auth:FirebaseAuth;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDashboardBinding.inflate(layoutInflater)
        var view = binding.root
        setContentView(view)
        initData();
    }


    private fun initData(){
        auth = FirebaseAuth.getInstance();
        binding.btnSignOut.setOnClickListener {
            auth.signOut()
            startActivity(Intent(this,MainActivity::class.java))
            finish()
        }

        setCurrentUser();
    }

    private fun setCurrentUser(){
        val currentUser = auth.currentUser;
        if(currentUser != null){
            var email = currentUser.email;
            var currentUserEmail = " Welcome:$email";
            binding.tvWelcome.text = currentUserEmail
        }
    }
}