package com.sirheadless.kt.game

/**
 * Created by
 * User: creuter
 * Date: 1/3/2018
 * Time: 5:49 PM
 * Test
 */
class Game(val playerX: String, val playerY: String){

	fun hasUser(user: String) :Boolean {
		return playerX.equals(user) || playerY.equals(user)
	}

    fun getAllUser() : List<String> {
        return listOf(playerX,playerY)
    }

}