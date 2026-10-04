package com.akshayashokcode.androiddesignpatterns.behavioral.state

/** Each state decides what the same user action does. No big if/else on a status flag. */
sealed interface PlayerState {
    val label: String
    fun onPlayPause(): PlayerState

    object Stopped : PlayerState {
        override val label = "Stopped"
        override fun onPlayPause(): PlayerState = Playing
    }

    object Playing : PlayerState {
        override val label = "Playing"
        override fun onPlayPause(): PlayerState = Paused
    }

    object Paused : PlayerState {
        override val label = "Paused"
        override fun onPlayPause(): PlayerState = Playing
    }
}

class Player {
    var state: PlayerState = PlayerState.Stopped
        private set

    fun tap() { state = state.onPlayPause() }
}
