import java.io.File

class Nodo (nombre : String, path : File) {

    val listaSubArchivos: MutableList<Nodo> = mutableListOf()

    fun crearSubdirectorios(path: File): Nodo {

        if (path.isFile) {
            val archivo = Nodo(path.name, path)
            return archivo
        }

        val iterar : (path : File) -> Unit = { estructura : File ->
            val subArchivo = crearSubdirectorios(estructura)
            listaSubArchivos.add(subArchivo)
        }

        val directorio = path.listFiles()
        directorio?.forEach(iterar)

        val subArchivo = Nodo(path.name, path)
        subArchivo.listaSubArchivos.addAll(this.listaSubArchivos.toMutableList())

        return subArchivo
    }
}