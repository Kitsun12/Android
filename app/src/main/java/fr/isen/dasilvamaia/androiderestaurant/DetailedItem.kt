package fr.isen.dasilvamaia.androiderestaurant

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import com.squareup.picasso.Picasso

class DetailedItem : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detailed_item)

        val name = intent.getStringExtra("name_fr")
        val description = intent.getStringExtra("description")
        val price = intent.getStringExtra("price")

        // Utilisez les données récupérées pour afficher les détails du plat choisi dans la nouvelle activité
        val nameView = findViewById<TextView>(R.id.nameView)
        val descriptionView = findViewById<TextView>(R.id.descriptionView)
        val priceView = findViewById<TextView>(R.id.priceView)
        val imageUrl = intent.getStringExtra("imageUrl")
        if (imageUrl != null) {
            val imageView = findViewById<ImageView>(R.id.imageView2)
            Picasso.get().load(imageUrl).into(imageView)
        }
        nameView.text = name
        descriptionView.text = description
        priceView.text = price
    }
}