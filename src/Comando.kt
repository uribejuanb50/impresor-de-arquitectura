package src

import java.io.File

sealed class Comando{
    data class Escondidos(val escondidos : Boolean = false) : Comando()
    data class Reversar(val reversar: Boolean = false) : Comando()
    data class Recortar(val recortar : Boolean = false) : Comando()
    data class Prueba(val prueba : Boolean = false) : Comando()
    data class NivelMax(val nivelMax: Int?) : Comando()
    data class ReadMe(val readMe : Boolean) : Comando()
    data class ToArchivo(val toArchivo : File?) : Comando()
}