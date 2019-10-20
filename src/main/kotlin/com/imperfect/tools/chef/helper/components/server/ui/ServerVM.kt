package com.imperfect.tools.chef.helper.components.server.ui

import com.imperfect.tools.chef.helper.components.server.model.Server
import com.imperfect.tools.chef.helper.components.server.model.ServerUser
import javafx.beans.property.SimpleStringProperty
import javafx.beans.property.StringProperty
import javafx.collections.ObservableList
import tornadofx.asObservable

class ServerVM {
	
	val nameProperty: StringProperty = SimpleStringProperty()
	val ipAddressProperty: StringProperty = SimpleStringProperty()
	val descriptionProperty: StringProperty = SimpleStringProperty()
	val users: ObservableList<ServerUser> = arrayListOf<ServerUser>().asObservable()
	
	fun set(server: Server) {
		nameProperty.value = server.name
		ipAddressProperty.value = server.ipAddress
		descriptionProperty.value = server.description
		users.remove(0, users.size)
		users.addAll(server.users)
	}
	
	fun clear() {
		set(Server("", "", "", arrayListOf()))
	}
	
	fun asServer(): Server {
		val nonObservableUsers = arrayListOf<ServerUser>()
		users.forEach { e -> nonObservableUsers.add(e)}
		return Server(
			name = nameProperty.get(), ipAddress =  ipAddressProperty.get(),
			description = descriptionProperty.get(),
			users = nonObservableUsers)
	}
	
}