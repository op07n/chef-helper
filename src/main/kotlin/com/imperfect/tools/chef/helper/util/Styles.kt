package com.imperfect.tools.chef.helper.util

import tornadofx.*

class Styles : Stylesheet() {
	
	companion object {
		val header by cssclass()
		val mainPanel by cssclass()
		val leftPane by cssclass()
		val topControlPane by cssclass()
		val bottomControlPane by cssclass()
	}
	
	init {
		header {
			backgroundColor += c("#ffffff")
		}
		mainPanel {
			padding = box(10.px)
		}
		leftPane {
			padding = box(0.px, 10.px, 0.px, 0.px)
		}
		topControlPane {
			padding = box(0.px, 0.px, 20.px, 0.px)
			spacing = 10.px
		}
		bottomControlPane {
			padding = box(20.px, 0.px, 0.px, 0.px)
			spacing = 10.px
		}
	}
	
}