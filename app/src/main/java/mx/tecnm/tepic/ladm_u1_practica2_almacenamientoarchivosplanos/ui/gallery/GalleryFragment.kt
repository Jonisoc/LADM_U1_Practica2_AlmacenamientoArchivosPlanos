package mx.tecnm.tepic.ladm_u1_practica2_almacenamientoarchivosplanos.ui.gallery

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import mx.tecnm.tepic.ladm_u1_practica2_almacenamientoarchivosplanos.ListaDeJuegos
import mx.tecnm.tepic.ladm_u1_practica2_almacenamientoarchivosplanos.databinding.FragmentGalleryBinding

class GalleryFragment : Fragment() {

    private var _binding: FragmentGalleryBinding? = null
    val lista = ListaDeJuegos
    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val galleryViewModel =
            ViewModelProvider(this).get(GalleryViewModel::class.java)

        _binding = FragmentGalleryBinding.inflate(inflater, container, false)
        val root: View = binding.root

        binding.insertar.setOnClickListener {
            val titulo = binding.titulo.text.toString()
            val descri = binding.descripcion.text.toString()
            var indice = binding.indice.text.toString().toInt()
            if (indice > 0 && indice < 16){
                indice--
                lista.setJuego(indice, titulo, descri)
                binding.titulo.setText("")
                binding.descripcion.setText("")
                binding.indice.setText("")
                Toast.makeText(context, "Insertado correctamente, pulsa guardar para guardar todos los cambios", Toast.LENGTH_LONG).show()
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