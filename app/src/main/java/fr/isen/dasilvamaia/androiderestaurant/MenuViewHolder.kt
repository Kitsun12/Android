package fr.isen.dasilvamaia.androiderestaurant

import android.util.Log
import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class MenuViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    private val menuItemTextView: TextView = itemView.findViewById(R.id.menuItemTitle)
    private val descriptionTextView: TextView = itemView.findViewById(R.id.menuItemDescription)
    private val priceTextView: TextView = itemView.findViewById(R.id.menuItemPrice)

    fun bind(menuItem: menuCarte.MenuItem) {
        menuItemTextView.text = menuItem.name_fr
        //descriptionTextView.text = menuItem.price
        priceTextView.text = menuItem.price
    }
}