package com.imperfect.tools.chef.helper.components.server.model

import com.fasterxml.jackson.annotation.JsonCreator
import com.fasterxml.jackson.annotation.JsonCreator.Mode.PROPERTIES
import com.fasterxml.jackson.annotation.JsonProperty

data class ServerUser
@JsonCreator(mode = PROPERTIES)
constructor(
	@JsonProperty("username") val username: String,
	@JsonProperty("isRoot") val isRoot: Boolean
)
