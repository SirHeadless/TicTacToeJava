package com.sirheadless.kt.Messages

/**
 * Created by
 * User: creuter
 * Date: 1/28/2018
 * Time: 8:25 PM
 * Test
 */
data class LoadGameMessage(val board: List<Int>, val player: String, val opponentName: String, val turn: String) {

}