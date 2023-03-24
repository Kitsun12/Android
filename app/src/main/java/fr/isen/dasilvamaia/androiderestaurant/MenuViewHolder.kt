package fr.isen.dasilvamaia.androiderestaurant
import com.squareup.picasso.Picasso
import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class MenuViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    private val menuItemTextView: TextView = itemView.findViewById(R.id.menuItemTitle)
    private val descriptionTextView: TextView = itemView.findViewById(R.id.menuItemDescription)
    private val priceTextView: TextView = itemView.findViewById(R.id.menuItemPrice)
    private val imageCarte: ImageView = itemView.findViewById(R.id.menuItemImage)
    fun bind(menuItem: Items) {
        menuItemTextView.text = menuItem.nameFr
        //descriptionTextView.text = menuItem.price
        priceTextView.text = menuItem.prices.get(0).price
        if(menuItem.images!=null && menuItem.images[0].isNotEmpty()){
        Picasso.get()

            .load(menuItem.images[0])
            .into(imageCarte)
        }

    }
}
