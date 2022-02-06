package masakiU.softwareArchitectureAsCode.codeGenerator

interface LayerTypeInterface {
    val templateFilePathList: List<String>
}

interface DomainTypeInterface {
    val layerList: List<LayerTypeInterface>
}

data class Directory<Layer>(
    val parentDirectory: Directory<Layer>?,
    val name: String,
    val layerTypeInterface: LayerTypeInterface? = null,
    val compositeFactory: Directory<Layer>.() -> List<Directory<Layer>>?
) where Layer : Enum<*>,
        Layer : LayerTypeInterface {
    val currentDirectoryPath: String = parentDirectory?.currentDirectoryPath?.plus(name) ?: name
}

data class ArchitectureContext<Layer, Domain>(
    val currentLayer: Layer,
    val currentDomain: Domain
) where Layer : Enum<*>,
        Layer : LayerTypeInterface,
        Domain : Enum<*>,
        Domain : DomainTypeInterface
