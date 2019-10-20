package com.imperfect.tools.chef.helper.main.ui

import com.imperfect.tools.chef.helper.components.server.ui.ServerPane
import com.imperfect.tools.chef.helper.components.user.ui.UserPane
import com.imperfect.tools.chef.helper.util.R
import com.imperfect.tools.chef.helper.util.Styles
import javafx.geometry.Side
import javafx.scene.control.TabPane
import tornadofx.*

class MainWindow: View() {
	
	private val serverMainPane: ServerPane by di()
	private val userMainPane: UserPane by di()
	
	init {
		title = R.strings.mainTitle
		setStageIcon(R.images.chefIcon)
	}
	
	override val root = borderpane {
		top = hbox {
			addClass(Styles.header)
			imageview(R.images.chefLogo) {
				fitHeight = 64.0
				isSmooth = true
				isPreserveRatio = true
			}
		}
		center = tabpane {
			side = Side.LEFT
			tabClosingPolicy = TabPane.TabClosingPolicy.UNAVAILABLE
			tab(R.strings.serversTabTitle) {
				add(serverMainPane)
			}
			tab(R.strings.usersTabTitle) {
				add(userMainPane)
			}
		}
	}
	
}
