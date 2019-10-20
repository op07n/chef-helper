package com.imperfect.tools.chef.helper.configuration

import com.fasterxml.jackson.databind.ObjectMapper
import com.google.inject.AbstractModule
import com.google.inject.Provides
import com.google.inject.Singleton
import com.imperfect.tools.chef.helper.components.server.controller.ServerService
import com.imperfect.tools.chef.helper.components.server.ui.ServerPane
import com.imperfect.tools.chef.helper.components.user.controller.UserService
import com.imperfect.tools.chef.helper.components.user.ui.UserPane

class ConfigurationModule: AbstractModule() {
	
	@Provides
	@Singleton
	fun serverService(mapper: ObjectMapper): ServerService {
		return ServerService(mapper)
	}
	
	@Provides
	@Singleton
	fun userService(mapper: ObjectMapper): UserService {
		return UserService(mapper)
	}
	
	@Provides
	@Singleton
	fun serverPane(serverService: ServerService, userService: UserService): ServerPane {
		return ServerPane(serverService, userService)
	}
	
	@Provides
	@Singleton
	fun userPane(userService: UserService): UserPane {
		return UserPane(userService)
	}
	
	@Provides
	@Singleton
	fun objectMapper(): ObjectMapper {
		return ObjectMapper()
	}
	
}