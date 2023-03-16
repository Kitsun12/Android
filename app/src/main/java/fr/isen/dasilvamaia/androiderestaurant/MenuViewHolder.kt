package fr.isen.dasilvamaia.androiderestaurant

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class MenuViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    private val menuItemTextView: TextView = itemView.findViewById(R.id.menuItemTitle)
    private val descriptionTextView: TextView = itemView.findViewById(R.id.menuItemDescription)

    fun bind(menuItem: String,descriptionItem : String) {
        menuItemTextView.text = menuItem
        descriptionTextView.text = descriptionItem
    }
}