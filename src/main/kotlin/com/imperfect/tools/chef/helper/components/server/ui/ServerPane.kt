package com.imperfect.tools.chef.helper.components.server.ui

import com.imperfect.tools.chef.helper.components.server.controller.ServerService
import com.imperfect.tools.chef.helper.components.server.model.Server
import com.imperfect.tools.chef.helper.components.server.model.ServerUser
import com.imperfect.tools.chef.helper.components.user.controller.UserService
import com.imperfect.tools.chef.helper.util.R
import com.imperfect.tools.chef.helper.util.Styles
import com.imperfect.tools.chef.helper.util.Styles.Companion.leftPane
import tornadofx.*


class ServerPane(
	private val serverService: ServerService,
	private val userService: UserService
) : UIComponent() {
	
	override val root = borderpane {
		addClass(Styles.mainPanel)
		val serverToShow = ServerVM()
		val table = tableview(serverService.allServers) {
			readonlyColumn(R.strings.serverName, Server::name)
			readonlyColumn(R.strings.ipAddress, Server::ipAddress)
		}
		table.selectionModel.selectedItemProperty().addListener { _, _, newValue ->
			if(newValue == null) {
				serverToShow.clear()
			} else {
				serverToShow.set(newValue)
			}
		}
		center = hbox {
			addClass(leftPane)
			add(table)
		}
		right = vbox {
			val usernameInput = textfield(serverToShow.nameProperty) {
				disableProperty().bind(table.selectionModel.selectedItemProperty().isNotNull)
			}
			hbox {
				addClass(Styles.topControlPane)
				button(R.strings.newServerAction) {
					action {
						if(table.selectionModel.selectedIndex < 0) {
							serverToShow.clear()
						} else {
							table.selectionModel.clearSelection()
						}
					}
				}
				button(R.strings.addAction) {
					visibleProperty().bind(table.selectionModel.selectedItemProperty().isNull)
					disableProperty().bind(usernameInput.textProperty().isBlank())
					action {
						serverService.add(serverToShow.asServer())
					}
				}
				button(R.strings.saveAction) {
					visibleProperty().bind(table.selectionModel.selectedItemProperty().isNotNull)
					disableProperty().bind(usernameInput.textProperty().isBlank())
					action {
						val modifiedServer = serverToShow.asServer()
						serverService.remove(table.selectionModel.selectedItem)
						serverService.add(modifiedServer)
						table.selectionModel.clearSelection()
						table.sort()
					}
				}
				button(R.strings.removeAction) {
					disableProperty().bind(table.selectionModel.selectedItemProperty().isNull)
					action {
						serverService.remove(serverToShow.asServer())
					}
				}
			}
			gridpane {
				hgap = 10.0
				vgap = 10.0
				row {
					label("${R.strings.serverName}:")
					add(usernameInput)
				}
				row {
					label("${R.strings.ipAddress}:")
					textfield(serverToShow.ipAddressProperty)
				}
				row {
					label("${R.strings.description}:") {
						gridpaneConstraints {
							columnSpan = 2
						}
					}
				}
				row {
					textarea(serverToShow.descriptionProperty) {
						gridpaneConstraints {
							columnSpan = 2
						}
					}
				}
				row {
					borderpane {
						gridpaneConstraints {
							columnSpan = 2
						}
						val serverUserTable = tableview(serverToShow.users) {
							readonlyColumn(R.strings.username, ServerUser::username)
							readonlyColumn(R.strings.isRoot, ServerUser::isRoot)
						}
						center = serverUserTable
						bottom = hbox {
							addClass(Styles.bottomControlPane)
							button(R.strings.addAction) {
								action {
									val dialog = AddServerUserDialog(userService)
									val serverUser = dialog.showAndWait()
									if(serverUser.isPresent) {
										serverToShow.users.add(serverUser.get())
									}
								}
							}
							button(R.strings.removeAction) {
								disableProperty().bind(serverUserTable.selectionModel.selectedItemProperty().isNull)
								action {
									serverToShow.users.remove(serverUserTable.selectionModel.selectedItem)
								}
							}
						}
					}
				}
			}
		}
	}
	
}
