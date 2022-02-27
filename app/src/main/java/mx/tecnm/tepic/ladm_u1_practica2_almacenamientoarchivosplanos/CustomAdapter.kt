package mx.tecnm.tepic.ladm_u1_practica2_almacenamientoarchivosplanos

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class CustomAdapter: RecyclerView.Adapter<CustomAdapter.ViewHolder>() {

    var titles = arrayOf("","","","","","","","","","","","","","","")
    //var listaJ = ListaJuegos()
    var lista = ListaDeJuegos

    override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int): ViewHolder {
        val v = LayoutInflater.from(viewGroup.context).inflate(R.layout.card_layout, viewGroup, false)
        return ViewHolder(v)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, i: Int) {
        if (lista.titles[i] == null || lista.titles[i].equals("") || lista.titles[i].equals("null")){
            viewHolder.itemTitle.text = "-"
            viewHolder.itemDetail.text = "-"
            viewHolder.itemImage.setImageResource(R.drawable.nogame)
        }else{
            viewHolder.itemTitle.text = lista.titles[i]
            viewHolder.itemDetail.text = lista.details[i]
            viewHolder.itemImage.setImageResource(R.drawable.game)
        }
        //viewHolder.itemImage.setImageResource(lista.images[i])
    }

    override fun getItemCount(): Int {
        return titles.size
    }

    inner class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        var itemImage: ImageView
        var itemTitle: TextView
        var itemDetail: TextView

        init {
            itemImage = itemView.findViewById(R.id.item_image)
            itemTitle = itemView.findViewById(R.id.item_title)
            itemDetail = itemView.findViewById(R.id.item_detail)
        }
    }
}