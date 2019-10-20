package com.imperfect.tools.chef.helper.components.user.ui

import com.imperfect.tools.chef.helper.components.user.model.User
import javafx.beans.property.SimpleStringProperty
import javafx.beans.property.StringProperty

class UserVM {
	
	val usernameProperty: StringProperty = SimpleStringProperty()
	val fullNameProperty: StringProperty = SimpleStringProperty()
	val descriptionProperty: StringProperty = SimpleStringProperty()
	
	fun set(user: User) {
		usernameProperty.value = user.username
		fullNameProperty.value = user.fullName
		descriptionProperty.value = user.description
	}
	
	fun clear() {
		set(User("", "", ""))
	}
	
	fun asUser(): User {
		return User(
			username = usernameProperty.get(),
			fullName =  fullNameProperty.get(),
			description = descriptionProperty.get()
		)
	}
	
}
