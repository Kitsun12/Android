package fr.isen.dasilvamaia.androiderestaurant

import android.content.Intent
import android.media.MediaRouter.RouteCategory
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Parcel
import android.os.Parcelable
import android.util.Log
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import fr.isen.dasilvamaia.androiderestaurant.MenuAdapter.OnMenuItemClickListener
import org.json.JSONObject

class menuCarte : AppCompatActivity() {

    private lateinit var menuList: RecyclerView
    private lateinit var menuTextView: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu_carte)
        menuTextView = findViewById(R.id.textView2)
        menuList = findViewById(R.id.menuList)
        menuList.layoutManager = LinearLayoutManager(this)

        val category = intent.getStringExtra("category")
        when (category) {
            "entrees" -> getMenuFromWebService("1", "Entrées")
            "plats" -> getMenuFromWebService("1", "Plats")
            "dessert" -> getMenuFromWebService("1", "Desserts")
            else -> showError()
        }
    }

    private fun getMenuFromWebService(shopId: String, category: String) {
        val queue = Volley.newRequestQueue(this)
        val url = "http://test.api.catering.bluecodegames.com/menu"
        val params = JSONObject()
        params.put("id_shop", shopId)

        val request = JsonObjectRequest(Request.Method.POST, url, params,
            { response ->
                Log.d("ok", response.toString())
                val gson = Gson()
                val menuResult = gson.fromJson(response.toString(), MenuResult::class.java)

                // Filter menu items based on the selected category
                val selectedCategory = menuResult.data.find { it.name_fr == category }
                Log.d("von",selectedCategory.toString())
                // Update the adapter with the filtered menu items
                selectedCategory?.let {
                    val menuItems = it.items
                    displayMenu(menuItems,category)
                } ?: showError()
            },
            { error ->
                showError()
            })

        queue.add(request)
    }

    private fun displayMenu(menuItems: List<MenuItem>,category: String) {
        menuTextView.text = category
        menuList.adapter = MenuAdapter(menuItems, object : MenuAdapter.OnMenuItemClickListener {
            override fun onMenuItemClick(menuItem: MenuItem) {
                val intent = Intent(this@menuCarte, DetailedItem::class.java)
                intent.putExtra("name_fr", menuItem.name_fr)
                intent.putExtra("description", menuItem.description)
                intent.putExtra("price", menuItem.price)
                intent.putExtra("image", menuItem.images)
                startActivity(intent)}
                // Ajouter le code ici pour gérer le clic sur un élément du menu
            }
        )
    }

    private fun showError() {
        menuTextView.text = "ERROR 404"
    }

    data class MenuResult(val data: List<MenuCategory>)
    data class MenuCategory(val name_fr: String, val items: List<MenuItem>)
    data class MenuItem(val name_fr: String, val description: String, val price: String, var images : ArrayList<String>) :
        Parcelable {

        constructor(parcel: Parcel) : this(
            parcel.readString()!!,
            parcel.readString()!!,
            parcel.readString()!!,
            parcel.createStringArrayList()!!,
        )

        override fun writeToParcel(parcel: Parcel, flags: Int) {
            parcel.writeString(name_fr)
            parcel.writeString(description)
            parcel.writeString(price)
            parcel.writeString(images.toString())
        }

        override fun describeContents(): Int {
            return 0
        }

        companion object CREATOR : Parcelable.Creator<MenuItem> {
            override fun createFromParcel(parcel: Parcel): MenuItem {
                return MenuItem(parcel)
            }

            override fun newArray(size: Int): Array<MenuItem?> {
                return arrayOfNulls(size)
            }
        }
    }
}

