import kr.entree.spigradle.kotlin.paper

plugins {
    kotlin("jvm") version "1.4.10"
    id("kr.entree.spigradle") version "2.2.3"
}

group = "kr.myoung2"
version = "1.0.0"

repositories {
    mavenCentral()
    maven("https://papermc.io/repo/repository/maven-public/")
    maven("https://jitpack.io")
    //maven("https://repo.dmulloy2.net/repository/public/")
}

dependencies {
    implementation(kotlin("stdlib"))
    compileOnly("com.destroystokyo.paper:paper-api:1.16.5-R0.1-SNAPSHOT")
    compileOnly("com.github.spigradle.spigradle:kr.entree.spigradle.base.gradle.plugin:v2.2.3")
    compileOnly(paper("1.16.5"))
    //compileOnly("com.comphenix.protocol:ProtocolLib:4.6.0")
}

spigot {
    authors = listOf("명이")
    apiVersion = project.property("apiVersion").toString()
    //depends = listOf("ProtocolLib")
    name = "PlayerRidePlayer"
    commands {
        create("ride") {
            permission = "ride.ride"
            permissionMessage = "You do not have the permission!"
        }
        create("rideitem") {
            permission = "ride.rideitem"
            permissionMessage = "You do not have the permission!"
        }
        permissions {
            create("ride.ride") {
                description = "allow ride command"
                defaults = "op"
            }
            create("ride.rideitem") {
                description = "allow rideitem command"
                defaults = "op"
            }
            create("ride.*") {
                description = "All permissions"
                defaults = "op"
                children = mapOf(
                    "ride.ride" to true,
                    "ride.rideitem" to true
                )
            }
        }
    }
}

val shade = configurations.create("shade")
shade.extendsFrom(configurations.implementation.get())

tasks {

    javadoc {
        options.encoding = "UTF-8"
    }

    compileJava {
        options.encoding = "UTF-8"
    }

    compileKotlin {
        kotlinOptions.jvmTarget = "1.8"
    }
    compileTestKotlin {
        kotlinOptions.jvmTarget = "1.8"
    }

    create<Jar>("sourceJar") {
        archiveClassifier.set("source")
        from(sourceSets["main"].allSource)
    }

    jar {
        from (shade.map { if (it.isDirectory) it else zipTree(it) })
    }
}