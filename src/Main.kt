//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
fun main() {
    val files = arrayOf(
        "/America/Mexico/Puebla/Puebla",
        "/Oceania/Australia",
        "/Europa/Francia/Toulouse",
        "/America/Mexico/Cdmx",
        "/America/Peru/Arequipa",
        "/Africa/Egipto/Cairo",
        "/Asia/India",
        "/America/Mexico/Tlaxcala",
        "/Asia/China/Peking",
        "/Europa/Francia/Loira",
        "/America",
        "/Asia/Corea",
        "/Antartida.jpg",
        "/America/Usa/Texas/Houston",
        "/America/Mexico/Puebla/Tehuacan",
        "/Europa/Francia/Paris",
        "/Europa"
    )
    val files2 = mutableListOf<List<String>>()

    for (file in files) {
        val names = file.split("/").let {
            it.subList(1, it.size)
        }
        files2.add(names)
    }
    val tree = sortFiles(files2)?.toSortedMap()
    val x = 0
}

fun sortFiles(files: List<List<String>>): Map<String, Any?>? {
    if (files.isEmpty()) return null
    val setFiles = getMainFiles(files)
    val auxMap = getSubFiles(files, setFiles)
    val tree = mutableMapOf<String, Any?>()
    for (entry in auxMap) {
        val subMap = sortFiles(entry.value)
        tree[entry.key] = subMap
    }
    return tree
}

fun getMainFiles(files: List<List<String>>): Set<String> {
    val setFiles = mutableSetOf<String>()
    for (file in files) {
        if (file.isEmpty()) continue
        setFiles.add(file[0])
    }
    return setFiles
}

fun getSubFiles(files: List<List<String>>, mainFiles: Set<String>): MutableMap<String, MutableList<List<String>>> {
    val map = mutableMapOf<String, MutableList<List<String>>>()
    for (mainFile in mainFiles) {
        for (file in files) {
            if (file[0] != mainFile) continue
            if (map[mainFile] == null) map[mainFile] = mutableListOf()
            val value = map[mainFile]!!
            if (file.size == 1) continue
            value.add(file.subList(1, file.size))
        }
    }
    return map
}