package com.suit.team.b.ui.main

import android.content.Intent
import android.os.Bundle
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import com.suit.team.b.R
import com.suit.team.b.ui.game.GameActivity
import com.suit.team.b.ui.menu_about.MenuAboutActivity
import com.suit.team.b.ui.profile.show.ProfilePageActivity
import com.suit.team.b.ui.score.ScoreActivity
import com.suit.team.b.utils.GameType
import com.suit.team.b.utils.SoundBackground

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        startService(Intent(this, SoundBackground::class.java))
        mutableListOf(
            R.id.ivPlayerVsPlayer,
            R.id.ivPlayerVsComputer,
            R.id.ivScore,
            R.id.ivSetting,
            R.id.ivAbout2,
        ).forEachIndexed { index, i ->
            findViewById<ImageView>(i).setOnClickListener {
                when (index) {
                    0 -> {
                        val intent = Intent(this, GameActivity::class.java)
                        intent.putExtra("mode", GameType.Multiplayer)
                        startActivity(intent)
                        finish()
                    }
                    1 -> {
                        val intent = Intent(this, GameActivity::class.java)
                        intent.putExtra("mode", GameType.Singleplayer)
                        startActivity(intent)
                        finish()
                    }
                    2 -> startActivity(Intent(this, ScoreActivity::class.java))
                    3 -> {
                        startActivity(Intent(this, ProfilePageActivity::class.java))
                        finish()
                    }
                    4 -> startActivity(Intent(this, MenuAboutActivity::class.java))
                    else -> finish()
                }
                stopSound()
            }
        }
    }

    override fun onBackPressed() {
        super.onBackPressed()
        stopSound()
    }

    private fun stopSound() {
        stopService(
            Intent(
                this,
                SoundBackground::class.java
            )
        )
    }
}
