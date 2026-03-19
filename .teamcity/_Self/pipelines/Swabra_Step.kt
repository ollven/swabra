package _Self.pipelines

import jetbrains.buildServer.configs.kotlin.*
import jetbrains.buildServer.configs.kotlin.pipelines.*
import jetbrains.buildServer.configs.kotlin.triggers.vcs

object Swabra_Step : Pipeline({
    id("Step")
    name = "Step"

    repositories {
        repository(DslContext.settingsRoot)
    }

    triggers {
        vcs {
            branchFilter = """
                -pr:*
                +:*
            """.trimIndent()
        }
    }

    job(Swabra_Step_Job1)
})

object Swabra_Step_Job1 : Job({
    id("Job1")
    name = "Job 1"
    allowReuse = false
    enableDependencyCacheOptimization = false

    steps {
        script {
            enabled = false
            scriptContent = "ls"
        }
    }
})
