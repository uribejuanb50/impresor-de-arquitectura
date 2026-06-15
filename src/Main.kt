import java.io.File//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
fun main(args : Array<String>) {

    val raiz = verificarEntrada(args)

}

fun verificarEntrada(args : Array<String>) : File{

    if(args.isEmpty()){
        throw IllegalArgumentException("[Main] Está vacío cabrón")
    }

    val path = File(args.first())

    if(path.isFile){
        throw IllegalArgumentException("[Main] Es un archivo, no un directorio mi socio")
    }

    return path
}