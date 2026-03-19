package _Self.pipelines

import jetbrains.buildServer.configs.kotlin.*
import jetbrains.buildServer.configs.kotlin.pipelines.*
import jetbrains.buildServer.configs.kotlin.triggers.vcs

object Swabra_Defaults : Pipeline({
    id("Defaults")
    name = "Defaults"

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

    job(Swabra_Defaults_Job1)
})

object Swabra_Defaults_Job1 : Job({
    id("Job1")
    name = "Job 1"
    allowReuse = false
    enableDependencyCacheOptimization = false

    steps {
        script {
            scriptContent = """
                echo file > rootfile.txt
                cd Test\ Data
                echo file > file.txt
                cd ..
                ls
                cd Test\ Data\ 2
                echo file > file2.txt
                cd ..
                cd TestData
                echo file > file3.txt
            """.trimIndent()
        }
    }

    features {
        swabra {
            filesCleanup = Swabra.FilesCleanup.AFTER_BUILD
            verbose = true
            paths = "+TestData"
        }
    }
})
