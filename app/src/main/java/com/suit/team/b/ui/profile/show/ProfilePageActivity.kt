package com.suit.team.b.ui.profile.show

import android.annotation.SuppressLint
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.MenuItem
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.suit.team.b.R
import com.suit.team.b.ui.auth.AuthActivity
import com.suit.team.b.ui.profile.update.ProfileUpdateActivity

class ProfilePageActivity : AppCompatActivity() {

    private lateinit var viewModel: ProfileViewModel
    private lateinit var tvUsername: TextView
    private lateinit var tvEmail: TextView
    private lateinit var ivPhoto: ImageView
    private lateinit var btUpdate: Button
    private lateinit var btLogout: Button

    @SuppressLint("ResourceType")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.profilepage_activity)

        viewModel = ViewModelProvider(this).get(ProfileViewModel::class.java)

        tvUsername = findViewById(R.id.tvUsername)
        tvEmail = findViewById(R.id.tvEmail)
        ivPhoto = findViewById(R.id.ivPhoto)
        btUpdate = findViewById(R.id.btUpdate)
        btLogout = findViewById(R.id.btLogout)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setTitle(R.string.profile_header)
        supportActionBar?.setBackgroundDrawable(ColorDrawable(Color.parseColor(getString(R.color.app_bg))))

        btUpdate.setOnClickListener {
            startActivity(Intent(this, ProfileUpdateActivity::class.java))
        }

        btLogout.setOnClickListener {
            viewModel.logout()
            this.startActivity(
                Intent(this, AuthActivity::class.java)
            )
            finish()
        }

        viewModel.resultRegister.observe(this) {
            Glide.with(this).load(it.data?.photo).into(ivPhoto)
            tvUsername.text = it.data?.username
            tvEmail.text = it.data?.email
        }
    }

    override fun onStart() {
        super.onStart()
        viewModel.fetchUserData()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            super.onBackPressed()
        }
        return super.onOptionsItemSelected(item)
    }

}
