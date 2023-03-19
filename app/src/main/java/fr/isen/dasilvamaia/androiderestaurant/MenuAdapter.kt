package fr.isen.dasilvamaia.androiderestaurant
import fr.isen.dasilvamaia.androiderestaurant.menuCarte.MenuItem
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import android.widget.ActionMenuView.OnMenuItemClickListener
class MenuAdapter(private val menuItems: List<MenuItem>, private val onMenuItemClickListener: OnMenuItemClickListener) :
    RecyclerView.Adapter<MenuViewHolder>() {
    init {
        notifyDataSetChanged()
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MenuViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val itemView = inflater.inflate(R.layout.menu_item, parent, false)
        return MenuViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MenuViewHolder, position: Int) {
        holder.bind(menuItems[position])
        holder.itemView.setOnClickListener { onMenuItemClickListener.onMenuItemClick(menuItems[position]) }

    }

    override fun getItemCount(): Int {
        return menuItems.size
    }
    interface OnMenuItemClickListener {
        fun onMenuItemClick(menuItem: MenuItem)
    }


}
