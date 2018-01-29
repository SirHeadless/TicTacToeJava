package com.sirheadless.kt.game

import java.util.Collections

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

		val games: MutableList<Game> = mutableListOf();

		fun addUserToGame(user: String): Game? {
			var game = getActiveGameOfUser(user)
			if (game != null || waitingUser.equals(user)) {
				return null
			}

			waitingUser?.let {
				var newGame = Game(waitingUser!!, user)
				games.add(newGame)
				waitingUser = null
				return newGame
			} ?: run {
				waitingUser = user
			}
			return null
		}

		fun findGameOfUser(user: String): Game? {
			return games.stream().filter { g -> g.hasUser(user) }.findFirst().orElse(null)

		}

		fun findActiveGameOfUser(user: String): Game? {
			return games.stream().filter { g -> g.hasUser(user) && !g.getGameStatus().isOver() }.findFirst().orElse(null)

		}

		private fun userAlreadyInGame(user: String): Game? {
			return games.firstOrNull { it.getAllUser().contains(user) }
		}

		private fun getActiveGameOfUser(user: String) : Game? {
			var game: Game? = userAlreadyInGame(user)
			game?.let{
				if (game.getGameStatus().isOver()){
					removeGame(game)
					return null
				}
				return game
			}
			return null
		}

		fun resetGame(game: Game) : Boolean{
			if (!game.getGameStatus().isOver()) {
				return false
			}
			Collections.replaceAll(games,game, Game(game.playerO, game.playerX))
			return true
		}

		fun removeGame(game: Game) : Boolean{
			return games.remove(game)
		}
	}


}