package com.sirheadless.kt

/**
 * Created by
 * User: creuter
 * Date: 12/29/2017
 * Time: 2:35 PM
 * Test
 */

class Board {

	val fields = IntArray(9);

	fun setField(field: Int, player: Int) {
		fields.set(field, player)
	}

	fun hasWinner(): Boolean {
		return false;
	}

	fun isFull(): Boolean {
		return true;
	}
}