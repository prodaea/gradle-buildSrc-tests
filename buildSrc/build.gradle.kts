repositories {
    jcenter()
}

plugins {
    `kotlin-dsl`
}

dependencies {
    testImplementation("org.jetbrains.kotlin:kotlin-test-junit:1.3.21")
}

gradlePlugin {
    plugins {
        create("testable-plugin") {
            id = "testable-plugin"
            implementationClass = "net.etherealnation.gradle.TestablePlugin"
        }
    }
}
