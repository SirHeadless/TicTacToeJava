package com.sirheadless.kt

/**
 * Created by
 * User: creuter
 * Date: 1/27/2018
 * Time: 11:08 AM
 * Test
 */
enum class PlayerType(val symbol : String?) {
	PLAYERX("x"), PLAYERO("o"), NONE(null);
	companion object {
		fun getPlayerTypeOfSymbol(symbol: String?): PlayerType {
			PlayerType.values()
					.filter { it.symbol.equals(symbol) }
					.forEach { return it }

			return NONE
		}
	}

}
