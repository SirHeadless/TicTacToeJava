package com.sirheadless.kt.Messages

import com.sirheadless.kt.StatusUtils.GameStatus

/**
 * Created by
 * User: creuter
 * Date: 1/28/2018
 * Time: 9:05 AM
 * Test
 */
class GameOverMessage{

	val full: Boolean
	val winner: String?
	val winnerRow: List<Int>?


	constructor(gamestatus: GameStatus) {
		this.full = gamestatus.full
		this.winner = gamestatus.winnerData.winner?.let{ it.symbol}
		this.winnerRow = gamestatus.winnerData.winnerRow
	}
}