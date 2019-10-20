package com.imperfect.tools.chef.helper

import com.google.inject.Guice
import com.imperfect.tools.chef.helper.configuration.ConfigurationModule
import com.imperfect.tools.chef.helper.main.ui.MainWindow
import com.imperfect.tools.chef.helper.util.Styles
import tornadofx.*
import kotlin.reflect.KClass

class Application: App(MainWindow::class, Styles::class) {
	
	init {
		val guice = Guice.createInjector(ConfigurationModule())
		FX.dicontainer = object : DIContainer {
			override fun <T : Any> getInstance(type: KClass<T>)
					= guice.getInstance(type.java)
		}
		reloadViewsOnFocus()
		reloadStylesheetsOnFocus()
	}
	
}
