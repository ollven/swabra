package _Self.pipelines

import jetbrains.buildServer.configs.kotlin.*
import jetbrains.buildServer.configs.kotlin.pipelines.*
import jetbrains.buildServer.configs.kotlin.triggers.vcs

object Swabra_SwabraYAML : Pipeline({
    id("SwabraYAML")
    name = "SwabraYAML"

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

    job(Swabra_SwabraYAML_Job1)
    job(Swabra_SwabraYAML_Job2)
})

object Swabra_SwabraYAML_Job1 : Job({
    id("Job1")
    name = "Job 1"
    allowReuse = false
    enableDependencyCacheOptimization = false

    steps {
        script {
            scriptContent = "ls"
        }
    }

    features {
        swabra {
            filesCleanup = Swabra.FilesCleanup.BEFORE_BUILD
            forceCleanCheckout = false
            lockingProcesses = Swabra.LockingProcessPolicy.DISABLED
            verbose = true
            paths = """
                -:TestData
                -:"Test Data/**"
                -:"Test Data 2/**"
            """.trimIndent()
        }
    }
})

object Swabra_SwabraYAML_Job2 : Job({
    id("Job2")
    name = "Job 2"
    allowReuse = false
    enableDependencyCacheOptimization = false

    steps {
        script {
            scriptContent = "ls"
        }
    }

    features {
        swabra {
            filesCleanup = Swabra.FilesCleanup.DISABLED
            forceCleanCheckout = false
            lockingProcesses = Swabra.LockingProcessPolicy.REPORT
            verbose = true
            paths = """
                -:TestData
                -:"Test Data/**"
                -:"Test Data 2/**"
            """.trimIndent()
        }
    }
})
