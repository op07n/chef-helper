package com.imperfect.tools.chef.helper.util

import com.imperfect.tools.chef.helper.util.resources.Images
import com.imperfect.tools.chef.helper.util.resources.Strings
import com.imperfect.tools.chef.helper.util.resources.TestStreams
import javafx.scene.image.Image
import tornadofx.get
import java.io.InputStream
import java.util.*

object R {
	
	private val classLoader = R::class.java.classLoader
	private val resourceBundle = ResourceBundle.getBundle("string/dictionary")
	
	val testStreams = TestStreams(
		users = getResourceAsStream("test_data/users.json"),
		servers = getResourceAsStream("test_data/servers.json")
	)
	
	val images = Images(
		chefIcon = Image(getResourceAsStream("image/chef_icon.png")),
		chefLogo = Image(getResourceAsStream("image/chef_logo.png"))
	)
	
	val strings = Strings(
		mainTitle = translate("main_title"),
		serversTabTitle = translate("servers_tab_title"),
		usersTabTitle = translate("users_tab_title"),
		username = translate("username"),
		fullName = translate("full_name"),
		isRoot = translate("is_root"),
		serverName = translate("server_name"),
		ipAddress = translate("ip_address"),
		description = translate("description"),
		newServerAction = translate("new_server_action"),
		newUserAction = translate("new_user_action"),
		addAction = translate("add_action"),
		saveAction = translate("save_action"),
		removeAction = translate("remove_action"),
		addUserToServerTitle = translate("add_user_to_server_title"),
		addUserToServerDescription = translate("add_user_to_server_description"),
		selectUser = translate("select_user"),
		user = translate("user")
	)
	
	private fun getResourceAsStream(path: String): InputStream? {
		return classLoader.getResourceAsStream(path)
	}
	
	private fun translate(text: String): String {
		return resourceBundle[text].orEmpty()
	}
	
}
