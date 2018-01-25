package com.sirheadless.kt.Messages

/**
 * Created by
 * User: creuter
 * Date: 12/29/2017
 * Time: 3:48 PM
 * Test
 */

data class FieldMessage(val field: Int, val player: String) {
	constructor() : this(field = 0, player = "")
}