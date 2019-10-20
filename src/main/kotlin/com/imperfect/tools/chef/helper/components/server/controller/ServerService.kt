package com.imperfect.tools.chef.helper.components.server.controller

import com.fasterxml.jackson.core.type.TypeReference
import com.fasterxml.jackson.databind.ObjectMapper
import com.imperfect.tools.chef.helper.components.server.model.Server
import com.imperfect.tools.chef.helper.util.R
import tornadofx.asObservable

class ServerService(mapper: ObjectMapper) {
	
	val allServers = arrayListOf<Server>().asObservable()
	
	init {
		val typeReference = object: TypeReference<List<Server>>() {}
		val servers = mapper.readValue(R.testStreams.servers, typeReference)
		allServers.addAll(servers)
	}
	
	fun add(server: Server) {
		allServers.add(server)
	}
	
	fun remove(server: Server) {
		val itemToDelete = allServers.find { item -> item.name == server.name }
		allServers.remove(itemToDelete)
	}
	
}
