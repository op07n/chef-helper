package com.imperfect.tools.chef.helper.components.user.model

import com.fasterxml.jackson.annotation.JsonCreator
import com.fasterxml.jackson.annotation.JsonCreator.Mode.PROPERTIES
import com.fasterxml.jackson.annotation.JsonProperty

data class User

@JsonCreator(mode = PROPERTIES)
constructor(
	@JsonProperty("username") val username: String,
	@JsonProperty("fullName") val fullName: String,
	@JsonProperty("description") val description: String
)
