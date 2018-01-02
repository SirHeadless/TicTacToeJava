package com.sirheadless.kt

import org.springframework.context.annotation.*

/**
 * Created by
 * User: creuter
 * Date: 12/29/2017
 * Time: 3:24 PM
 * Test
 */

@Configuration
open class GameConfig {

	@Bean
	open fun board() : Board {
		return Board();
	}
}