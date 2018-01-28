package com.sirheadless.kt.Messages

/**
 * Created by
 * User: creuter
 * Date: 1/27/2018
 * Time: 12:18 PM
 * Test
 */
data class ErrorMessage(val error: String) {
	constructor() : this(error = "")
}
