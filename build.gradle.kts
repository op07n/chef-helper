import org.gradle.api.JavaVersion.VERSION_11
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
	kotlin("jvm") version "1.3.50"
	id("application")
	id("org.openjfx.javafxplugin") version "0.0.8"
	id("com.github.johnrengelman.shadow") version "5.1.0"
}

val applicationVersion: String by project

group = "com.imperfect.tools.chef.helper"
version = applicationVersion

java {
	sourceCompatibility = VERSION_11
	targetCompatibility = VERSION_11
}

repositories {
	mavenCentral()
}

dependencies {
	implementation(kotlin("stdlib"))
	
	implementation("javax.inject:javax.inject:1")
	implementation("com.google.inject:guice:4.2.2:no_aop")
	
	implementation(platform("com.fasterxml.jackson:jackson-bom:2.10.0"))
	implementation("com.fasterxml.jackson.core:jackson-databind")
	implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
	
	implementation("org.openjfx:javafx-controls:13")
	implementation("no.tornado:tornadofx:1.7.19")
}

javafx {
	// Declare the javafx modules we need to use
	modules("javafx.controls")
}

tasks.jar {
	manifest {
		attributes["Main-Class"] = "${project.group}.MainKt"
	}
}

application {
    mainClassName = "${project.group}.MainKt"
}

tasks.withType<KotlinCompile> {
	kotlinOptions.jvmTarget = "1.8"
}
