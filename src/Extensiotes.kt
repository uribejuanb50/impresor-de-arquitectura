package src

import java.io.File

//ARRAYLIST
fun ArrayList<String?>?.toCustomString() : String {
    if(this == null)
        return "[Vacío]"

    var retorno = "["

    for((indice, str) in this.withIndex()){

        if(str == null)
            retorno += "null"
        else
            retorno += str

        if(indice != this.lastIndex)
            retorno += ", "
    }

    return "$retorno]"
}

@JvmName("toCustomStringArrayListString")
fun ArrayList<String>?.toCustomString() : String {
    if(this == null){
        return "NoEncontrado"
    }

    var retorno = "["

    for((indice, str) in this.withIndex()){

        if(indice != this.lastIndex)
            retorno += "$str, "
        else
            retorno += str
    }
    return "$retorno]"
}

@JvmName("toCustomStringArrayListFile")
fun ArrayList<File>.toCustomString() : String {

    val transformacion = { path : File ->
        if(path.isDirectory)
            "${path.name}/"
        else
            "${path.name}"
    }

    return this.map(transformacion).toCollection(ArrayList()).toCustomString()
}

//Mutable list
@JvmName("toCustomStringMutableListNodo")
fun MutableList<Nodo>.toCustomString() : String{
    var retorno : String = "["
    for(nodo in this){
        retorno += nodo.nombre + ", "
    }
    return "$retorno]"
}

fun ArrayList<String>?.unirString() : String {

    if(this == null){
        return "Vacio"
    }

    var retorno = ""

    for(str in this){
        retorno += str
    }

    return retorno
}