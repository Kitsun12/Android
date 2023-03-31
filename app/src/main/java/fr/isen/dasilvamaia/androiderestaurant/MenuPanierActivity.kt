package fr.isen.dasilvamaia.androiderestaurant

import android.annotation.SuppressLint
import android.content.Context
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.gson.Gson
import com.google.gson.JsonParser
import org.json.JSONArray
import org.json.JSONException
import org.json.JSONObject
import java.io.BufferedReader
import java.io.File
import java.io.FileReader
import java.io.InputStream
import java.util.*

class MenuPanierActivity : AppCompatActivity() {

    @SuppressLint("MissingInflatedId")
    @RequiresApi(Build.VERSION_CODES.N)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu_cart)
        val gson = Gson()
        val file = File(this.filesDir, "itemcart.json")
        val cartItems = mutableListOf<CartItem>()

        if (file.exists()) {
            try {
                val json = file.readText()
                val jsonsplit = json.split('\n')
                var cartItem: CartItem? = null
                println(jsonsplit)
                for (item in jsonsplit) {
                    if(item != null && item != "")
                     cartItem = gson.fromJson(item, CartItem::class.java)
                    if (cartItem != null) {
                        cartItems.add(cartItem)
                    }

                }

            } catch (e: JSONException) {
                e.printStackTrace()
            }
        }
        val cartRecyclerView: RecyclerView = findViewById(R.id.recyclerview1)
        val cartAdapter = CartItemAdapter(this, cartItems)
        cartRecyclerView.adapter = cartAdapter
        cartRecyclerView.layoutManager = LinearLayoutManager(this)
    }
}
class CartItemAdapter(private val context: Context, private val cartItems: List<CartItem>) : RecyclerView.Adapter<CartItemAdapter.ViewHolder>() {

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val itemName: TextView = itemView.findViewById(R.id.menuItemTitle)
        val itemQuantite : TextView = itemView.findViewById(R.id.menuItemQuantite)
        val itemPrice: TextView = itemView.findViewById(R.id.menuItemPrice)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.menu_item_cart, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currentItem = cartItems[position]
        holder.itemName.text = currentItem.name
        holder.itemQuantite.text = currentItem.quantity.toString()
        holder.itemPrice.text = currentItem.price.toString()
    }

    override fun getItemCount(): Int {
        return cartItems.size
    }
}

