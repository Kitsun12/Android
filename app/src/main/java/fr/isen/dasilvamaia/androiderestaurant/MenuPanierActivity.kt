package fr.isen.dasilvamaia.androiderestaurant

import android.content.Context
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.annotation.RequiresApi
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
                val jsonObject = JSONObject(json)

                val cartItem = gson.fromJson(jsonObject.toString(), CartItem::class.java)
                cartItems.add(cartItem)

            } catch (e: JSONException) {
                e.printStackTrace()
            }
        }
    }
}
