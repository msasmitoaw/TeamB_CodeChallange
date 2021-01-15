package com.suit.team.b.ui.game

import android.content.Context
import com.suit.team.b.R
import com.suit.team.b.data.model.Player
import com.suit.team.b.utils.string

class GamePresenterImp(
    private val view: GameView
) : GamePresenter {
    private lateinit var player: Player
    private lateinit var playerTwo: Player
    private val context = view as Context
    private var weakness =
        mapOf(
            context.string(R.string.scissors) to context.string(R.string.rock),
            context.string(R.string.paper) to context.string(R.string.scissors),
            context.string(R.string.rock) to context.string(R.string.paper)
        )

    override fun setPlayer(player: Player) {
        this.player = player
    }

    override fun setPlayerTwo() {
        this.playerTwo = Player(weakness.entries.random().key)
    }

    override fun setPlayerTwo(player: Player) {
        this.playerTwo = player
    }

    override fun result() {
        view.onResult(
            when (player.bet) {
                playerTwo.bet -> context.string(R.string.draw)
                weakness[playerTwo.bet] -> context.string(R.string.player_one_win)
                else -> context.string(R.string.player_two_win)
            }
        )
    }
}