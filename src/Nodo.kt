package src

import java.io.File

class Nodo (val nombre : String?, val path : File?) {

    val listaSubArchivos: MutableList<Nodo?> = mutableListOf()
    val letrasPalabraMasLarga : Int = 0

    fun crearSubdirectorios(path: File): Nodo? {

        val directorio = path.listFiles()

        if(directorio == null || directorio.isEmpty()){
            return null
        }

        if (path.isFile) {
            val archivo = Nodo(path.name, path)
            return archivo
        }

        val iterar : (path : File) -> Unit = { estructura : File ->
            val subArchivo = crearSubdirectorios(estructura)
            listaSubArchivos.add(subArchivo)
        }

        directorio.forEach(iterar)

        val subArchivo = Nodo(path.name, path)
        subArchivo.listaSubArchivos.addAll(this.listaSubArchivos.toMutableList())

        return subArchivo
    }

    fun calcularMedidaPalabraMasLarga(nodo : Nodo?) : Unit {
        if(nodo == null){
            return
        }
        if(nodo.listaSubArchivos.isEmpty() || nodo.listaSubArchivos.size == 1){
            return
        }
        
    }

    fun imprimirParaREADME(nivel : Int, nodo : Nodo?) : String {
        if(nodo == null) {
            return ""
        }
        if(nodo.listaSubArchivos.size == 1){
            return
        }
    }
}




















