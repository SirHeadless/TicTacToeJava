package com.sirheadless.kt.game

import com.sirheadless.kt.*
import com.sirheadless.kt.Messages.FieldMessage
import com.sirheadless.kt.StatusUtils.GameStatus

/**
 * Created by
 * User: creuter
 * Date: 1/3/2018
 * Time: 5:49 PM
 * Test
 */
class Game(val playerX: String, val playerO: String){

	var board : Board = Board()

	var turn = PlayerType.PLAYERX

	fun setField(fieldMessage: FieldMessage) : Boolean{
		if (getGameStatus().isOver()){
			return false
		}
		if (fieldMessage.player != turn.symbol || board.fields[fieldMessage.field] != null) {
			return false
		}

		board.setField(fieldMessage.field, PlayerType.getPlayerTypeOfSymbol(fieldMessage.player))
		toogleTurn()
		return true
	}

	fun getGameStatus(): GameStatus {
		return board.getGameStatus()
	}

	fun hasUser(user: String) :Boolean {
		return playerX.equals(user) || playerO.equals(user)
	}

    fun getAllUser() : List<String> {
        return listOf(playerX, playerO)
    }

	private fun toogleTurn() {
		turn = if (turn.equals(PlayerType.PLAYERX)) PlayerType.PLAYERO else PlayerType.PLAYERX
	}

	fun getBoardWithString() : Array<String?> {
		var boardString = arrayOfNulls<String?>(9)
		for (index in 0 .. (this.board.fields.size - 1)) {
			boardString[index] = this.board.fields[index]?.symbol
		}
		return boardString
	}

	fun getSymbolForPlayer(user: String) : String? {
		if (!getAllUser().contains(user)) {
			return null
		}
		return if (playerX.equals(user) ) PlayerType.PLAYERX.symbol else PlayerType.PLAYERO.symbol
	}

	fun getOpponentForPlayer(user: String) : String? {
		if (!getAllUser().contains(user)) {
			return null
		}
		return if (playerX.equals(user) ) this.playerO else this.playerX
	}
}