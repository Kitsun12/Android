package fr.isen.dasilvamaia.androiderestaurant

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class MenuAdapter(private val menuItems: Array<String>,private val descriptionItem: Array<String>,private val onMenuItemClickListener: OnMenuItemClickListener) :
    RecyclerView.Adapter<MenuViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MenuViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val itemView = inflater.inflate(R.layout.menu_item, parent, false)
        return MenuViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MenuViewHolder, position: Int) {
        holder.bind(menuItems[position],descriptionItem[position])
        holder.itemView.setOnClickListener {
            onMenuItemClickListener.onItemClick(menuItems[position])
        }
    }

    override fun getItemCount(): Int {
        return menuItems.size
    }
    interface OnMenuItemClickListener{
        fun onItemClick(menuItems: String)
    }
}