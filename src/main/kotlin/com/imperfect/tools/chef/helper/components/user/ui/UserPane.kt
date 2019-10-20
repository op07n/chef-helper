package com.imperfect.tools.chef.helper.components.user.ui

import com.imperfect.tools.chef.helper.components.user.model.User
import com.imperfect.tools.chef.helper.components.user.controller.UserService
import com.imperfect.tools.chef.helper.util.R
import com.imperfect.tools.chef.helper.util.Styles
import com.imperfect.tools.chef.helper.util.Styles.Companion.leftPane
import tornadofx.*

class UserPane(
	private val userService: UserService
) : UIComponent() {
	
	override val root = borderpane {
		val userToShow = UserVM()
		val table = tableview(userService.allUsers) {
			readonlyColumn(R.strings.username, User::username)
			readonlyColumn(R.strings.fullName, User::fullName)
		}
		table.selectionModel.selectedItemProperty().addListener { _, _, newValue ->
			if(newValue == null) {
				userToShow.clear()
			} else {
				userToShow.set(newValue)
			}
		}
		center = hbox {
			addClass(leftPane)
			add(table)
		}
		right = vbox {
			hbox {
				addClass(Styles.topControlPane)
				button(R.strings.newUserAction) {
					action {
						table.selectionModel.clearSelection()
					}
				}
				button(R.strings.addAction) {
					visibleProperty().bind(table.selectionModel.selectedItemProperty().isNull)
					action {
						userService.add(userToShow.asUser())
					}
				}
				button(R.strings.saveAction) {
					visibleProperty().bind(table.selectionModel.selectedItemProperty().isNotNull)
					action {
						val modifiedServer = userToShow.asUser()
						userService.remove(table.selectionModel.selectedItem)
						userService.add(modifiedServer)
						table.selectionModel.clearSelection()
						table.sort()
					}
				}
				button(R.strings.removeAction) {
					disableProperty().bind(table.selectionModel.selectedItemProperty().isNull)
					action {
						userService.remove(userToShow.asUser())
					}
				}
			}
			hbox {
				label("${R.strings.username}:")
				textfield(userToShow.usernameProperty) {
					disableProperty().bind(table.selectionModel.selectedItemProperty().isNotNull)
				}
			}
			hbox {
				label("${R.strings.fullName}:")
				textfield(userToShow.fullNameProperty) {
				}
			}
			vbox {
				label("${R.strings.description}:")
				textarea(userToShow.descriptionProperty) {
					prefRowCount = 2
				}
			}
		}
	}
	
}