package fr.isen.dasilvamaia.androiderestaurant

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.widget.Button
import android.widget.Toast
import android.widget.Toolbar

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
         val toolbr = findViewById<androidx.appcompat.widget.Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbr)
        // Référence les boutons
        val entreesButton = findViewById<Button>(R.id.entreesButton)
        val platsButton = findViewById<Button>(R.id.platsButton)
        val dessertsButton = findViewById<Button>(R.id.dessertsButton)

        // Écoute les clics sur les boutons
        entreesButton.setOnClickListener {
            // Lance la nouvelle activité pour les entrées
            val intent = Intent(this, MenuCarteActivity::class.java)
            intent.putExtra("category", "entrees")
            startActivity(intent)
        }

        platsButton.setOnClickListener {
            // Lance la nouvelle activité pour les plats
            val intent = Intent(this, MenuCarteActivity::class.java)
            intent.putExtra("category", "plats")
            startActivity(intent)
        }

        dessertsButton.setOnClickListener {
            // Lance la nouvelle activité pour les desserts
            val intent = Intent(this, MenuCarteActivity::class.java)
            intent.putExtra("category", "dessert")
            startActivity(intent)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.toolbar, menu)
        return true
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.menu_cart -> {
                val intent = Intent(this, MenuPanierActivity::class.java)
                intent.putExtra("category", "panier")
                startActivity(intent)
                return true
            }
            else -> return super.onOptionsItemSelected(item)
        }
    }
}