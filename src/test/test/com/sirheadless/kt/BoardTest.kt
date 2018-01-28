package com.sirheadless.kt

import com.sirheadless.kt.StatusUtils.*
import org.junit.Before
import org.junit.Test

import org.junit.Assert.*

/**
 * Created by
 * User: creuter
 * Date: 1/27/2018
 * Time: 11:19 AM
 * Test
 */
class BoardTest {

	val boardToWin : Board = Board()

	@Before
	fun setUp() {
		boardToWin.setField(0,PlayerType.PLAYERX)
		boardToWin.setField(1, PlayerType.PLAYERO)
		boardToWin.setField(4, PlayerType.PLAYERX)
	}

	@Test
	fun getGameStatusWhenGameIsNotOver() {
		var gameStatus : GameStatus = boardToWin.getGameStatus()
		var expectedGameStatus = GameStatus(WinnerData(null,null), false)
		assertEquals(expectedGameStatus,gameStatus)
	}

	@Test
	fun getGameStatusWhenHasWinner() {
		boardToWin.setField(2,PlayerType.PLAYERO)
		boardToWin.setField(8,PlayerType.PLAYERX)


		var gameStatus : GameStatus = boardToWin.getGameStatus()
		var expectedGameStatus = GameStatus(WinnerData(PlayerType.PLAYERX, listOf(0,4,8)), false)
		assertEquals(expectedGameStatus,gameStatus)
	}

	@Test
	fun getGameStatusWhenIsFull(){
		var boardToFill : Board = Board()
		for (i in 0..8) {
			boardToFill.setField(i, PlayerType.PLAYERX)
		}

		var gameStatus : GameStatus = boardToFill.getGameStatus()
		var expectedGameStatus = GameStatus(WinnerData(PlayerType.PLAYERX,listOf(2,4,6)), true)
		assertEquals(expectedGameStatus,gameStatus)
	}

}