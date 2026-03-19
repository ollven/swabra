package _Self.pipelines

import jetbrains.buildServer.configs.kotlin.*
import jetbrains.buildServer.configs.kotlin.pipelines.*
import jetbrains.buildServer.configs.kotlin.triggers.vcs

object Swabra_SwabraYamlMissingFields : Pipeline({
    id("SwabraYamlMissingFields")
    name = "SwabraYamlMissingFields"

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

    job(Swabra_SwabraYamlMissingFields_Job1)
})

object Swabra_SwabraYamlMissingFields_Job1 : Job({
    id("Job1")
    name = "Job 1"
    allowReuse = true
    enableDependencyCacheOptimization = false

    steps {
        script {
            scriptContent = "ls"
        }
    }

    features {
        swabra {
        }
    }
})
