package com.sirheadless.kt.Messages

import com.sirheadless.kt.PlayerType

/**
 * Created by
 * User: creuter
 * Date: 12/29/2017
 * Time: 3:48 PM
 * Test
 */

data class FieldMessage(val field: Int, val player: String?){



	constructor() : this(field = -1, player = "")

}