package fr.isen.dasilvamaia.androiderestaurant

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class menuCarte : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu_carte)
        val category = intent.getStringExtra("category")
        // Display the appropriate menu based on the category
        when (category) {
            "entrees" -> showEntreesMenu()
            "plats" -> showPlatsMenu()
            "dessert" -> showDessertsMenu()
            else -> showError()
        }
    }

    // Helper methods to display the different menus
    private fun showEntreesMenu() {
        val menuTextView = findViewById<TextView>(R.id.textView2)
        "Entre".also { menuTextView.text = it }

        val menuList = findViewById<RecyclerView>(R.id.menuList)
        menuList.layoutManager = LinearLayoutManager(this)
        menuList.adapter = MenuAdapter(resources.getStringArray(R.array.titleEntrees),resources.getStringArray(R.array.descEntrees))
    }

    private fun showPlatsMenu() {
        val menuTextView = findViewById<TextView>(R.id.textView2)
        "Plats".also { menuTextView.text = it }

        val menuList = findViewById<RecyclerView>(R.id.menuList)
        menuList.layoutManager = LinearLayoutManager(this)
        menuList.adapter = MenuAdapter(resources.getStringArray(R.array.titlePlats),resources.getStringArray(R.array.descPlats))
    }

    private fun showDessertsMenu() {
        val menuTextView = findViewById<TextView>(R.id.textView2)
        "Dessert".also { menuTextView.text = it }

        val menuList = findViewById<RecyclerView>(R.id.menuList)
        menuList.layoutManager = LinearLayoutManager(this)
        menuList.adapter = MenuAdapter(resources.getStringArray(R.array.titlePlats),resources.getStringArray(R.array.descDesserts))
    }

    private fun showError() {
        val menuTextView = findViewById<TextView>(R.id.textView2)
        "ERROR 404".also { menuTextView.text = it }
    }
}
