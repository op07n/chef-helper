package com.imperfect.tools.chef.helper.components.user.controller

import com.fasterxml.jackson.core.type.TypeReference
import com.fasterxml.jackson.databind.ObjectMapper
import com.imperfect.tools.chef.helper.components.user.model.User
import com.imperfect.tools.chef.helper.util.R
import tornadofx.asObservable

class UserService(mapper: ObjectMapper) {
	
	val allUsers = arrayListOf<User>().asObservable()
	
	init {
		val typeReference = object: TypeReference<List<User>>() {}
		val users = mapper.readValue(R.testStreams.users, typeReference)
		allUsers.addAll(users)
	}
	
	fun add(user: User) {
		allUsers.add(user)
	}
	
	fun remove(user: User) {
		allUsers.remove(user)
	}
	
}
