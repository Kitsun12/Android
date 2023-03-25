package fr.isen.dasilvamaia.androiderestaurant

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toolbar

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        androidx.appcompat.widget.Toolbar toolbr = findViewById(R.id.toolbar)
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
}