package mx.tecnm.tepic.ladm_u1_practica2_almacenamientoarchivosplanos

object ListaDeJuegos {

    var titles: Array<String?> = arrayOfNulls(15)
    var details: Array<String?> = arrayOfNulls(15)
    var images = intArrayOf(R.drawable.ic_launcher_foreground,R.drawable.ic_launcher_foreground,R.drawable.ic_launcher_foreground,
        R.drawable.ic_launcher_foreground,R.drawable.ic_launcher_foreground,R.drawable.ic_launcher_foreground,
        R.drawable.ic_launcher_foreground,R.drawable.ic_launcher_foreground,R.drawable.ic_launcher_foreground,
        R.drawable.ic_launcher_foreground,R.drawable.ic_launcher_foreground,R.drawable.ic_launcher_foreground,
        R.drawable.ic_launcher_foreground,R.drawable.ic_launcher_foreground,R.drawable.ic_launcher_foreground)

    //public fun listaJuegos(context:Context){
    init {
        System.out.println("Init")
        System.out.println("${titles[0]} || ${details[0]}")
    }

    public fun ListaJuegos(){
        System.out.println("Inicializar")
    }

    public fun getDatos():String{
        var cadena = ""
        for (i in 0..(titles.size-1)){
            cadena += "${titles[i]}||${details[i]}&&"
        }
        return cadena
    }

    public fun setDatos(cadena:String){
        if(cadena == ""){
            var titulos = arrayOf("","","","","","","","","","","","","","","")
            var descripciones = arrayOf("","","","","","","","","","","","","","","")
            return
        }
        var linea = cadena.split("&&")
        System.out.println(linea[1])
        var titulos = arrayOf("","","","","","","","","","","","","","","")
        var descripciones = arrayOf("","","","","","","","","","","","","","","")
        for (i in 0..(linea.size-2)){
            var prov = linea[i].split("||")
            titulos[i] = prov[0]
            descripciones[i] = prov[1]
        }
        for (i in 0..(titles.size-1)){
            titles[i] = titulos[i]
            details[i] = descripciones[i]
        }
    }

    public fun setJuego(indice:Int, titulo:String, descri:String){
        titles[indice] = titulo
        details[indice] = descri
    }

    public fun deletejuego(indice:Int){
        titles[indice] = ""
        details[indice] = ""
    }

    public fun getJuego(indice: Int): String{
        return "${titles[indice]}||${details[indice]}"
    }

}