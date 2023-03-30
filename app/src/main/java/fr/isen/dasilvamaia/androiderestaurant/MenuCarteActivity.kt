package fr.isen.dasilvamaia.androiderestaurant

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.volley.Request
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.google.gson.Gson
import org.json.JSONObject

class MenuCarteActivity : AppCompatActivity() {

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
                val menuResult = gson.fromJson(response.toString(), ListMenu::class.java)

                // Filter menu items based on the selected category
                val filteredItems: List<Items> = menuResult.data
                    .flatMap { it.items }
                    .filter { item -> item.categNameFr == category.toString() }
                displayMenu(filteredItems,category)

            },
            { error ->
                showError()
            })

        queue.add(request)
    }

    private fun displayMenu(menuItems: List<Items>, category: String) {
        menuTextView.text = category
        menuList.adapter = MenuAdapter(menuItems, object : MenuAdapter.OnMenuItemClickListener {
            override fun onMenuItemClick(menuItem: Items) {
                val intent = Intent(this@MenuCarteActivity, DetailedItemActivity::class.java)
                intent.putExtra("dish",menuItem)
                startActivity(intent)}
                // Ajouter le code ici pour gérer le clic sur un élément du menu
            }
        )
    }

    private fun showError() {
        menuTextView.text = "ER525ROR 404"
    }

}

