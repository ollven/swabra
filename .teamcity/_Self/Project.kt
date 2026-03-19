package _Self

import _Self.buildTypes.*
import _Self.pipelines.*
import jetbrains.buildServer.configs.kotlin.*
import jetbrains.buildServer.configs.kotlin.Project

object Project : Project({

    buildType(SwabraBuild)

    pipeline(Swabra_Defaults)
    pipeline(Swabra_Step)
    pipeline(Swabra_Swabra)
    pipeline(Swabra_SwabraCopyYaml)
    pipeline(Swabra_SwabraYAML)
    pipeline(Swabra_SwabraYamlMissingFields)
})
