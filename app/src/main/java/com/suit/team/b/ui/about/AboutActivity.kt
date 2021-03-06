package com.suit.team.b.ui.about

import android.content.Intent
import android.os.Bundle
import android.text.method.ScrollingMovementMethod
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.suit.team.b.R
import com.suit.team.b.ui.menu_about.MenuAboutActivity

class AboutActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_about)
        tvAbout.text = getString(R.string.content_about)
        tvAbout.movementMethod = ScrollingMovementMethod()

        btnContent.setOnClickListener {
            startActivity(Intent(this, MenuAboutActivity::class.java))
            finish()
        }
    }

    private val tvAbout: TextView by lazy(LazyThreadSafetyMode.NONE) {
        findViewById(R.id.tvAbout)
    }
    private val btnContent: Button by lazy(LazyThreadSafetyMode.NONE) {
        findViewById(R.id.btnBackContent)
    }
}
