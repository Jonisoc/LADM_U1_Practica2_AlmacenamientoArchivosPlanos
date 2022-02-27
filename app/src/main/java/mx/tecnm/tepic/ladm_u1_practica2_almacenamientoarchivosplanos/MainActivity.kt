package mx.tecnm.tepic.ladm_u1_practica2_almacenamientoarchivosplanos

import android.os.Bundle
import android.view.Menu
import androidx.appcompat.app.AlertDialog
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.navigation.NavigationView
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import androidx.drawerlayout.widget.DrawerLayout
import androidx.appcompat.app.AppCompatActivity
import mx.tecnm.tepic.ladm_u1_practica2_almacenamientoarchivosplanos.databinding.ActivityMainBinding
import java.io.BufferedReader
import java.io.InputStreamReader
import java.io.OutputStreamWriter

class MainActivity : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityMainBinding
    private val lista = ListaDeJuegos

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.appBarMain.toolbar)

        binding.appBarMain.fab.setOnClickListener { view ->
            Snackbar.make(view, "Datos guardados correctamente", Snackbar.LENGTH_LONG)
                .setAction("Exito", null).show()
            guardarArchivo(lista.getDatos())
        }
        val drawerLayout: DrawerLayout = binding.drawerLayout
        val navView: NavigationView = binding.navView
        val navController = findNavController(R.id.nav_host_fragment_content_main)
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.nav_home, R.id.nav_gallery, R.id.nav_slideshow
            ), drawerLayout
        )
        lista.setDatos(leerArchivo())
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.main, menu)
        return true
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment_content_main)
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }

    private fun leerArchivo(): String {
        try {
            var archivo = BufferedReader(InputStreamReader(openFileInput("juegos.txt")))
            var cadena = archivo.readLine()
            return cadena
        }catch (e:Exception){
            AlertDialog.Builder(this).setMessage("No se encontraron datos guardados :)").show()
            return ""
        }
    }

    private fun guardarArchivo(cadena:String) {
        try {
            System.out.println(cadena)
            //var archivo = OutputStreamWriter(openFileOutput("dos.txt", MODE_PRIVATE))
            var archivo = OutputStreamWriter(openFileOutput("juegos.txt", MODE_PRIVATE))
            //System.out.println("1")
            archivo.write(cadena)
            archivo.flush()
            archivo.close()
            System.out.println("Guardado")
        } catch (e: Exception) {
            System.out.println("Error ${e.message}")
        }
    }
}