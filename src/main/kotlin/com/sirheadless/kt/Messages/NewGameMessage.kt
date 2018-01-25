package com.sirheadless.kt.Messages

data class NewGameMessage(val player : String) {
    constructor() : this(player = "")
}