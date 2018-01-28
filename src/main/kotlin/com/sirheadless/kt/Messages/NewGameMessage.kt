package com.sirheadless.kt.Messages

data class NewGameMessage(val player : String?, val oppponentName : String) {
    constructor() : this(player = "", oppponentName = "")
}