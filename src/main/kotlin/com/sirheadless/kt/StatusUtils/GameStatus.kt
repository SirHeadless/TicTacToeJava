package com.sirheadless.kt.StatusUtils

import com.sirheadless.kt.PlayerType

/**
 * Created by
 * User: creuter
 * Date: 1/27/2018
 * Time: 10:37 AM
 * Test
 */

data class GameStatus(val winnerData : WinnerData, val full : Boolean)  {

	fun isOver() : Boolean {
		return hasWinner() || full
	}

	private fun hasWinner() :Boolean {
		return winnerData.winner != null
	}

}