package src
import java.io.File

class Nodo (val nombre : String, val path : File) {

    val listaSubArchivos: MutableList<Nodo> = mutableListOf()
    var letrasPalabraMasLarga : Int = 0

    fun crearSubDirectorios() : Nodo? {

        if(this.path.isFile){
            return this
        }

        val directorio = this.path.listFiles()?: return null

        for(direccion in directorio){

            val nodo = Nodo(direccion.name, direccion)
            val nodoActualizado = nodo.crearSubDirectorios()

            if(nodoActualizado != null)
                this.listaSubArchivos.add(nodoActualizado)
        }

        return this
    }

    //se puede mejorar usando el this
    fun calcularMedidaPalabraMasLarga() : Int{

        if(this.path.isFile) {
            return this.nombre.length
        }
        if(this.listaSubArchivos.isEmpty()){
            return 0
        }

        var palabraMasGrande : Int = 0

        for(nodo in this.listaSubArchivos){

            val mayorSubDirectorio = nodo.calcularMedidaPalabraMasLarga()

            palabraMasGrande = maxOf(mayorSubDirectorio, this.nombre.length, palabraMasGrande)
        }
        return palabraMasGrande
    }

    fun calcularProfundidad() : Int{
        if(this.path.isFile){
            return 1
        }
        if(this.listaSubArchivos.isEmpty()){
            return 0
        }

        var nivelMasAlto : Int = 0

        for(nodo in this.listaSubArchivos){

            val sumatoria = nodo.calcularProfundidad() + 1
            nivelMasAlto = maxOf(sumatoria, nivelMasAlto)
        }

        return nivelMasAlto
    }

    //para que imprima primero subdirectorios luego archivos

    fun reversarListas() : Unit{
        if(this.path.isFile){
            return
        }
        if(this.listaSubArchivos.isEmpty()){
            return
        }

        this.listaSubArchivos.sortWith(compareByDescending<Nodo> {it.path.isDirectory}.thenBy { it.nombre })

        for (subdirectorio in this.listaSubArchivos){
            subdirectorio.reversarListas()
        }

        return
    }
    fun impresionUltraSencilla(espacio : String = "   ", nivel : Int = 0) : String {

        if(this.path.isFile){
            return espacio.repeat(nivel) + this.nombre + "\n"
        }
        if(this.listaSubArchivos.isEmpty()){
            return espacio.repeat(nivel) + this.nombre + "\n"
        }
        var devolver : String = espacio.repeat(nivel) + this.nombre + "/\n"

        for(subdirectorio in this.listaSubArchivos){
            devolver += subdirectorio.impresionUltraSencilla(espacio,nivel + 1)
        }

        return devolver
    }

    fun generarArquitectura(mdreadme : ArrayList<String>, nivel : Int = 0, flagUltimoPuesto : Boolean = false) : String {

        if(this.path.isFile){
            val conector = if(flagUltimoPuesto) "└── " else "├── "
            return mdreadme[nivel - 1] + conector + this.nombre + "\n"
        }
        if(this.listaSubArchivos.isEmpty()){
            val conector = if(flagUltimoPuesto) "└── " else "├── "
            return mdreadme[nivel - 1] + conector + this.nombre + "/\n"
        }

        var arquitectura = ""

        if(nivel == 0){
            arquitectura = this.nombre + "/\n"
            mdreadme[nivel] = ""
        }
        else{
            val conector = if(flagUltimoPuesto) "└── " else "├── "
            arquitectura += mdreadme[nivel - 1] + conector + this.nombre + "/\n"

            mdreadme[nivel] = mdreadme[nivel - 1] + if(flagUltimoPuesto) "    " else "│   "
        }

        for((indice, subdirectorio) in this.listaSubArchivos.withIndex()) {

            val ultimoPuesto = if(indice == this.listaSubArchivos.lastIndex) true else false

            if(nivel > 0)
                mdreadme[nivel] = mdreadme[nivel - 1] + (if(flagUltimoPuesto) "    " else "│   ")

            arquitectura += subdirectorio.generarArquitectura(mdreadme, nivel + 1, ultimoPuesto)
        }
        return arquitectura
    }

    fun buscarArchivosPorNombreYCondicion(
        busqueda : String,
        condicion : (nombre : String, busqueda : String) -> Boolean
    ): ArrayList<String>?{

        if(condicion(this.path.name, busqueda)){
            return arrayListOf(this.path.path)
        }
        if(this.path.isFile){
            return null
        }
        if(this.listaSubArchivos.isEmpty()){
            return null
        }

        var retorno : ArrayList<String> = arrayListOf()

        for(subdirectorios in this.listaSubArchivos){
            val listaRetornante = subdirectorios.buscarArchivosPorNombreYCondicion(busqueda, condicion)

            if(listaRetornante != null)
                retorno.addAll(listaRetornante)
        }

        return retorno
    }

    fun devolverPadresConHijos() : ArrayList<String>{
        if(this.path.isFile){
            return arrayListOf()
        }
        if(this.listaSubArchivos.isEmpty())
            return arrayListOf()

        val lista : ArrayList<String> = arrayListOf()
        lista.add(this.nombre + "/")

        for(subdirectorio in this.listaSubArchivos){
            val strSubdirectorio = if(subdirectorio.path.isDirectory) "${subdirectorio.nombre}/" else subdirectorio.nombre
            lista.add(strSubdirectorio)
        }
        for(subdirectorio in this.listaSubArchivos){
            lista.addAll(subdirectorio.devolverPadresConHijos())
        }
        return lista
    }

    init{
        //print("[Nodo] Nombre: ${this.nombre} | path : ${this.path}")
    }
}

fun MutableList<Nodo>.toCustomString() : String{
    var retorno : String = "["
    for(nodo in this){
        retorno += nodo.nombre + ", "
    }
    return "$retorno]"
}


















