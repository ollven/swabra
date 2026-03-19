package _Self.pipelines

import jetbrains.buildServer.configs.kotlin.*
import jetbrains.buildServer.configs.kotlin.pipelines.*
import jetbrains.buildServer.configs.kotlin.triggers.vcs

object Swabra_SwabraCopyYaml : Pipeline({
    id("SwabraCopyYaml")
    name = "Swabra-copy-yaml"

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

    job(Swabra_SwabraCopyYaml_Job1)
    job(Swabra_SwabraCopyYaml_Job1clone)
    job(Swabra_SwabraCopyYaml_Job2)
    job(Swabra_SwabraCopyYaml_Job3)
})

object Swabra_SwabraCopyYaml_Job1 : Job({
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
            filesCleanup = Swabra.FilesCleanup.BEFORE_BUILD
            forceCleanCheckout = false
            lockingProcesses = Swabra.LockingProcessPolicy.REPORT
            verbose = true
            paths = "-:TestData"
        }
    }
})

object Swabra_SwabraCopyYaml_Job1clone : Job({
    id("Job1clone")
    name = "Job 1 clone"
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
            filesCleanup = Swabra.FilesCleanup.BEFORE_BUILD
            forceCleanCheckout = false
            lockingProcesses = Swabra.LockingProcessPolicy.DISABLED
            verbose = true
            paths = """-:"Test Data/**""""
        }
    }
})

object Swabra_SwabraCopyYaml_Job2 : Job({
    id("Job2")
    name = "Job 2"
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
            filesCleanup = Swabra.FilesCleanup.BEFORE_BUILD
            verbose = true
            paths = "-:TestData"
        }
    }
})

object Swabra_SwabraCopyYaml_Job3 : Job({
    id("Job3")
    name = "Job3"
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
            filesCleanup = Swabra.FilesCleanup.BEFORE_BUILD
            forceCleanCheckout = false
            lockingProcesses = Swabra.LockingProcessPolicy.DISABLED
            verbose = true
            paths = """-:"Test Data 2/**""""
        }
    }
})
