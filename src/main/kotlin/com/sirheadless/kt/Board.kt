package com.sirheadless.kt

import com.sirheadless.kt.StatusUtils.*

/**
 * Created by
 * User: creuter
 * Date: 12/29/2017
 * Time: 2:35 PM
 * Test
 */

class Board {

	private val WINNERS : List<List<Int>> = listOf(
			listOf(2,4,6),
			listOf(0,1,2),
			listOf(3,4,5),
			listOf(6,7,8),
			listOf(0,4,8),
			listOf(2,5,8),
			listOf(1,4,7),
			listOf(0,3,6))

	private val FIELDVALUES : List<PlayerType> = listOf(PlayerType.PLAYERX, PlayerType.PLAYERO)

	val fields : Array<PlayerType?> = arrayOfNulls<PlayerType?>(9) ;

	fun setField(field: Int, player: PlayerType) {
		fields[field] = player
	}

	private fun getWinner(): WinnerData {
		for (winner in WINNERS) {
			if (FIELDVALUES.contains(fields.get(winner[0])) && fields.get(winner[0]) == fields.get(winner[1]) && fields.get(winner[0])  == fields.get(winner[2]) ) {
				return WinnerData(fields.get(winner[0]), winner)
			}
		}
		return WinnerData(null, null)
	}

	fun getGameStatus() : GameStatus {
		return GameStatus(getWinner(), isFull())
	}

	private fun isFull(): Boolean {
		for (field in fields) {
			if (field == null) {
				return false
			}
		}

		return true
	}

}