package com.sirheadless.kt.game

import com.sirheadless.kt.Messages.NewGameMessage
import com.sirheadless.kt.controller.TestController
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.messaging.simp.SimpMessageSendingOperations

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

		fun addUserToGame(user : String) : Game?{
			var game : Game? =  userAlreadyInGame(user)
			if(game != null) {
				return null
			}

			if (waitingUser != null ){
                var newGame = Game(waitingUser!!, user)
				games.add(newGame)
//				TestController.sendPlayersGameStarts(newGame)
//				var test =  TestController()
//				test.sendPlayersGameStarts(newGame)
                return newGame
			} else {
				waitingUser = user
			}
            return null
		}

		fun findGameOfUser(user: String) : Game? {
			return games.stream().filter {g -> g.hasUser(user)}.findFirst().orElse(null)

		}

		private fun userAlreadyInGame(user : String ): Game? {
			return games.firstOrNull { it.getAllUser().contains(user) }
		}


	}


}