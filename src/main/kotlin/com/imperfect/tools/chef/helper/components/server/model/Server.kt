package com.imperfect.tools.chef.helper.components.server.model

import com.fasterxml.jackson.annotation.JsonCreator
import com.fasterxml.jackson.annotation.JsonCreator.Mode.PROPERTIES
import com.fasterxml.jackson.annotation.JsonProperty

data class Server
@JsonCreator(mode = PROPERTIES)
constructor(
	@JsonProperty("name") val name: String,
	@JsonProperty("ipAddress") val ipAddress: String,
	@JsonProperty("description") val description: String,
	@JsonProperty("users") val users: List<ServerUser>
)
