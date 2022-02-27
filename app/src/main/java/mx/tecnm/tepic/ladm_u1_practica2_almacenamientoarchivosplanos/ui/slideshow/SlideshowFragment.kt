package mx.tecnm.tepic.ladm_u1_practica2_almacenamientoarchivosplanos.ui.slideshow

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import mx.tecnm.tepic.ladm_u1_practica2_almacenamientoarchivosplanos.ListaDeJuegos
import mx.tecnm.tepic.ladm_u1_practica2_almacenamientoarchivosplanos.databinding.FragmentSlideshowBinding

class SlideshowFragment : Fragment() {

    private var _binding: FragmentSlideshowBinding? = null
    private val lista = ListaDeJuegos

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val slideshowViewModel =
            ViewModelProvider(this).get(SlideshowViewModel::class.java)

        _binding = FragmentSlideshowBinding.inflate(inflater, container, false)
        val root: View = binding.root

        binding.buscar.setOnClickListener {
            var indice = binding.indice.text.toString().toInt()
            if (indice > 0 && indice < 16){
                indice--
                var datos = lista.getJuego(indice)
                var data = datos.split("||")
                if (data[0] == "null"){
                    Toast.makeText(context, "No hay un juego en esa posición", Toast.LENGTH_LONG).show()
                }else{
                    binding.titulo.setText(data[0])
                    binding.descripcion.setText(data[1])
                }
            }else{
                Toast.makeText(context, "Ingresa un indice correcto", Toast.LENGTH_LONG).show()
            }
        }

        binding.eliminar.setOnClickListener {
            var indice = binding.indice.text.toString().toInt()
            if (indice > 0 && indice < 16){
                indice--
                AlertDialog.Builder(requireContext())
                    .setTitle("Eliminar")
                    .setMessage("¿De verdad quieres eliminar ese elemento?")
                    .setPositiveButton("Sí",{ d,i->
                        lista.deletejuego(indice)
                        binding.titulo.setText("")
                        binding.indice.setText("")
                        binding.descripcion.setText("")
                        Toast.makeText(context, "Eliminado correctamente, pulsa guardar para guardar todos los cambios", Toast.LENGTH_LONG).show()
                        d.dismiss()})
                    .setNegativeButton("No", { d,i-> d.cancel()})
                    .show()
            }else{
                Toast.makeText(context, "Ingresa un indice correcto", Toast.LENGTH_LONG).show()
            }
        }

        binding.modificar.setOnClickListener {
            val titulo = binding.titulo.text.toString()
            val descri = binding.descripcion.text.toString()
            var indice = binding.indice.text.toString().toInt()
            if (indice > 0 && indice < 16){
                indice--
                lista.setJuego(indice, titulo, descri)
                binding.titulo.setText("")
                binding.descripcion.setText("")
                binding.indice.setText("")
                Toast.makeText(context, "Modificado correctamente, pulsa guardar para guardar todos los cambios", Toast.LENGTH_LONG).show()
            }else{
                Toast.makeText(context, "Ingresa un indice correcto", Toast.LENGTH_LONG).show()
            }
        }

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}