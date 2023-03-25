package fr.isen.dasilvamaia.androiderestaurant

import android.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import com.squareup.picasso.Picasso
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import androidx.viewpager.widget.ViewPager
import androidx.viewpager2.widget.ViewPager2
import kotlin.math.roundToInt
import com.google.gson.Gson
import java.io.File

class DetailedItem : AppCompatActivity() {
    var counter = 0
    var total = 0F
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detailed_item)

        val item = intent.getSerializableExtra("dish") as Items

        //val imageUrl = intent.getStringExtra("imageUrl")

        // Utilisez les données récupérées pour afficher les détails du plat choisi dans la nouvelle activité
        val nameView = findViewById<TextView>(R.id.nameView)
        val descriptionView = findViewById<TextView>(R.id.descriptionView)
        val priceView = findViewById<TextView>(R.id.priceView)
        val viewPager = findViewById<ViewPager>(R.id.viewPager)
        val adapter = ImagePagerAdapter(supportFragmentManager, item.images)
        val buttonplus = findViewById<Button>(R.id.button3)
        val buttontotal = findViewById<Button>(R.id.button2)
        val nombre = findViewById<TextView>(R.id.textView3)
        val prix = (item.prices[0].price ?: "0").toFloat()
        buttonplus.setOnClickListener {
            counter++
            // faire quelque chose avec la variable counter
            nombre.text = counter.toString()
            total = counter * prix
            buttontotal.text = "Total : " + total.toString() + "€"
        }
        val buttonmoins = findViewById<Button>(R.id.button)
        buttonmoins.setOnClickListener {
            if (counter > 0) {
                counter--
            }


            // faire quelque chose avec la variable counter
            nombre.text = counter.toString()
            total = counter * prix
            buttontotal.text = "Total : " + total.toString() + " €"
        }
        val cartItems = mutableListOf<CartItem>()
        buttontotal.setOnClickListener{
            val gson = Gson()
            val file = File(this.filesDir, "itemcart.json")
            if (file.exists()) {
                val json = file.readText()
                val cartItem = gson.fromJson(json, CartItem::class.java)
                cartItems.add(cartItem)
                val alertDialog = AlertDialog.Builder(this)
                alertDialog.setTitle("Item added to cart")
                alertDialog.setPositiveButton("OK") { dialog, _ -> dialog.dismiss() }
                alertDialog.show()
            } else {
                val alertDialog = AlertDialog.Builder(this)
                alertDialog.setTitle("No items in cart")
                alertDialog.setPositiveButton("OK") { dialog, _ -> dialog.dismiss() }
                alertDialog.show()
            }

        }




        viewPager.adapter = adapter
        //Picasso.get().load(imageUrl).into(imageView)
        nameView.text = item.nameFr
        val ingredientsList = item.ingredients
        val sb = StringBuilder()
        for (i in ingredientsList.indices) {
            sb.append(ingredientsList[i].nameFr)
            if (i != ingredientsList.size - 1) {
                sb.append(", ")
            }
        }
        descriptionView.text = "Ingredients : " + sb.toString()
        priceView.text = item.prices[0].price + " €"

    }
}

class ImagePagerAdapter(fm: FragmentManager, private val imageUrls: List<String>) :
    FragmentPagerAdapter(fm) {

    override fun getCount(): Int = imageUrls.size

    override fun getItem(position: Int): Fragment {
        return ImageFragment.newInstance(imageUrls[position])
    }
}

class ImageFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val imageView = ImageView(context)
        imageView.scaleType = ImageView.ScaleType.CENTER_INSIDE
        val imageUrl = arguments?.getString(ARG_IMAGE_URL)
        if (imageUrl != null && imageUrl.isNotEmpty()) {
            Picasso.get().load(imageUrl).into(imageView)
        }
        return imageView
    }

    companion object {
        private const val ARG_IMAGE_URL = "image_url"

        fun newInstance(imageUrl: String): ImageFragment {
            val fragment = ImageFragment()
            val args = Bundle()
            args.putString(ARG_IMAGE_URL, imageUrl)
            fragment.arguments = args
            return fragment
        }
    }
}
