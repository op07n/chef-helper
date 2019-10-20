package com.imperfect.tools.chef.helper.components.server.ui

import com.imperfect.tools.chef.helper.components.server.model.ServerUser
import com.imperfect.tools.chef.helper.components.user.controller.UserService
import com.imperfect.tools.chef.helper.components.user.model.User
import com.imperfect.tools.chef.helper.util.R
import javafx.application.Platform
import javafx.beans.property.SimpleBooleanProperty
import javafx.beans.property.SimpleObjectProperty
import javafx.scene.control.ButtonType
import javafx.scene.control.Dialog
import javafx.util.StringConverter
import tornadofx.*


class AddServerUserDialog(userService: UserService): Dialog<ServerUser>() {
	
	private val userProperty = SimpleObjectProperty<User>()
	private val isRootProperty = SimpleBooleanProperty()
	
	init {
		title = R.strings.addUserToServerTitle
		headerText =  R.strings.addUserToServerDescription
		dialogPane.buttonTypes.addAll(ButtonType.OK, ButtonType.CANCEL)
		dialogPane.content = gridpane {
			hgap = 10.0
			vgap = 10.0
			row {
				label("${R.strings.user}:")
				combobox<User> {
					items = userService.allUsers
					userProperty.bind(selectionModel.selectedItemProperty())
					dialogPane.lookupButton(ButtonType.OK)
						.disableProperty()
						.bind(selectionModel.selectedItemProperty().isNull)
					
					promptText =  R.strings.selectUser
					converter = UserStringConverter()
					Platform.runLater { requestFocus() }
				}
			}
			row {
				checkbox(R.strings.isRoot) {
					isRootProperty.bind(selectedProperty())
					gridpaneConstraints {
						columnSpan = 2
					}
				}
			}
		}
		setupResultConverter()
	}
	
	private fun setupResultConverter() {
		setResultConverter { dialogButton ->
			if (dialogButton == ButtonType.OK) {
				ServerUser(
					userProperty.get().username,
					isRootProperty.get()
				)
			} else {
				null
			}
		}
	}
	
	class UserStringConverter: StringConverter<User>() {
		override fun fromString(string: String?): User {
			throw NotImplementedError()
		}
		
		override fun toString(user: User?): String {
			return "${user?.fullName} - (${user?.username})"
		}
	}
	
}
