package net.etherealnation.gradle

import org.gradle.testfixtures.ProjectBuilder
import org.junit.Test
import kotlin.test.assertNotNull
import kotlin.test.assertTrue

internal class TestablePluginTest {
    @Test
    fun thePluginGetsApplied() {
        val project = ProjectBuilder.builder().build()
        project.pluginManager.apply("testable-plugin")
        assertNotNull(project.plugins.getPlugin(TestablePlugin::class.java))
//        assertTrue(false)
    }
}