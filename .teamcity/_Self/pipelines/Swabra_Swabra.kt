package _Self.pipelines

import jetbrains.buildServer.configs.kotlin.*
import jetbrains.buildServer.configs.kotlin.pipelines.*
import jetbrains.buildServer.configs.kotlin.triggers.vcs

object Swabra_Swabra : Pipeline({
    id("Swabra")
    name = "Swabra"

    repositories {
        repository(DslContext.settingsRoot)
    }

    triggers {
        vcs {
            branchFilter = """
                +:*
                +pr:*
            """.trimIndent()
        }
    }

    job(Swabra_Swabra_Job1)
    job(Swabra_Swabra_Job1clone)
    job(Swabra_Swabra_Job3)
})

object Swabra_Swabra_Job1 : Job({
    id("Job1")
    name = "Job 1"
    allowReuse = true
    enableDependencyCacheOptimization = false

    steps {
        script {
            enabled = false
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
            enabled = false
            filesCleanup = Swabra.FilesCleanup.BEFORE_BUILD
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

object Swabra_Swabra_Job1clone : Job({
    id("Job1clone")
    name = "Job 1 clone"
    allowReuse = true
    enableDependencyCacheOptimization = false

    steps {
        script {
            enabled = false
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
            enabled = false
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

object Swabra_Swabra_Job3 : Job({
    id("Job3")
    name = "Job3"
    allowReuse = true
    enableDependencyCacheOptimization = false

    steps {
        script {
            enabled = false
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
            enabled = false
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
