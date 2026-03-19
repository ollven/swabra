package _Self.buildTypes

import jetbrains.buildServer.configs.kotlin.*
import jetbrains.buildServer.configs.kotlin.buildFeatures.Swabra
import jetbrains.buildServer.configs.kotlin.buildFeatures.perfmon
import jetbrains.buildServer.configs.kotlin.buildFeatures.swabra
import jetbrains.buildServer.configs.kotlin.buildSteps.script

object SwabraBuild : BuildType({
    name = "Swabra-Build"

    vcs {
        root(DslContext.settingsRoot)
    }

    steps {
        script {
            id = "simpleRunner"
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
        perfmon {
        }
        swabra {
            filesCleanup = Swabra.FilesCleanup.AFTER_BUILD
            forceCleanCheckout = true
            verbose = true
            paths = "hhcds.ds"
        }
    }
})
