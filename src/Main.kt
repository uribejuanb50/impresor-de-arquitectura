package src

import java.io.File

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
fun main(args : Array<String>) {

    val args = verificarEntrada(args)
    val raiz = generarPath(args)

    val arbol : Arbol = Arbol(raiz)
    arbol.crearSubDirectorios()
    val palabraMasLarga = arbol.nPalabraMasLarga()
    val profundidad = arbol.calcularProfundidad()
    val READMEsencillo = arbol.imprimirParaREADMEsencillo(profundidad)
    println("[Main] profundidad mas larga: $profundidad | nletras en palabra mas larga : $palabraMasLarga")
    println("[Main] Main:\n$READMEsencillo")
}

fun verificarEntrada(args : Array<String>) : Array<String> {
    if(args.isEmpty())
        throw IllegalArgumentException("[Main] Los argumentos de entrada están vacíos mani")

    if(args.size > 1)
        throw IllegalArgumentException("[Main] Se espera un solo argumento en la terminal")

    return args
}

fun generarPath(args : Array<String>) : File {
    val path = File(args.first())

    if(!path.exists())
        throw IllegalArgumentException("[Main] El path no existe cabrón")

    if(path.isFile)
        throw IllegalArgumentException("[Main] El path es un archivo, no un directorio")

    println("[Main] Se está imprimiendo la arquitectura de ${path.path}")

    return path
}
