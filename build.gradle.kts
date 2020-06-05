plugins {
    kotlin("jvm") version "1.3.72"
}

group = "com.github.ifrankwang"
version = "1.0-SNAPSHOT"

repositories {
    mavenLocal()
    maven("https://maven.aliyun.com/repository/public")
    maven("https://maven.aliyun.com/repositories/jcenter")
    maven("https://maven.aliyun.com/repositories/google")
    maven("https://maven.aliyun.com/repository/central")
    maven("https://maven.aliyun.com/repository/gradle-plugin")
    maven("https://repository.aspose.com/repo")
}

dependencies {
    implementation(kotlin("stdlib-jdk8"))
}

tasks {
    compileKotlin {
        kotlinOptions.jvmTarget = "1.8"
    }
    compileTestKotlin {
        kotlinOptions.jvmTarget = "1.8"
    }
}
