package com.sirheadless.kt.game

/**
 * Created by
 * User: creuter
 * Date: 1/3/2018
 * Time: 5:50 PM
 * Test
 */
class GamesManager {

	companion object {
		private var waitingUser: String? = null

		val games : MutableList<Game>  = mutableListOf();

		fun addUser(user : String) {
			if (waitingUser != null ){
				games.add(Game(waitingUser!!, user))
			} else {
				waitingUser = user
			}
		}

		fun findGameOfUser(user: String) : Game? {
			return games.stream().filter {g -> g.hasUser(user)}.findFirst().orElse(null)

		}
	}
}