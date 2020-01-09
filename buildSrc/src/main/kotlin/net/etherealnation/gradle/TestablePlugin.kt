package net.etherealnation.gradle

import org.gradle.api.DefaultTask
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.tasks.Internal
import org.gradle.api.tasks.TaskAction
import org.gradle.kotlin.dsl.create
import org.gradle.kotlin.dsl.get

open class TestablePlugin : Plugin<Project> {
    override fun apply(project: Project): Unit = project.run {
        extensions.create<TestablePluginExtension>(EXTENSION_NAME)

        tasks.maybeCreate(TESTABLE_TASK_NAME, TestableTask::class.java).apply {
            group = TESTABLE_TASK_GROUP
            tasks.findByName("preBuild")?.dependsOn(this)
        }
    }

    companion object {
        const val EXTENSION_NAME = "testablePlugin"

        const val TESTABLE_TASK_NAME = "testableTask"
        const val TESTABLE_TASK_GROUP = "build"
    }
}

open class TestablePluginExtension {
    var title: String? = null
    var message: String? = null
}

val Project.testablePluginExtension: TestablePluginExtension
    get() = project.extensions[TestablePlugin.EXTENSION_NAME] as TestablePluginExtension

open class TestableTask : DefaultTask() {
    private var fullMessage: String = ""

    @Internal
    fun getFullMessage() = fullMessage

    @TaskAction
    fun sendMessage() {
        fullMessage = "Title: ${project.testablePluginExtension.title}, Message: ${project.testablePluginExtension.message}"
        println("Sending message...")
        println(fullMessage)
    }
}