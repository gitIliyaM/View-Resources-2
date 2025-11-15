package otus.gpb.homework.viewandresources

import android.content.Context
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.google.android.material.button.MaterialButton
import kotlin.random.Random

class CartActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: CartAdapter
    private lateinit var totalAmountTextView: TextView

    private val cartItems = mutableListOf<CartItem>()
    private companion object {
        const val PREFS_NAME = "ThemePrefs"
        const val KEY_DARK_THEME = "dark_theme"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        applyTheme()
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cart)

        recyclerView = findViewById(R.id.cart_recycler_view)
        totalAmountTextView = findViewById(R.id.total_amount)

        for (i in 1..5) {
            cartItems.add(CartItem("Item $i", 10.0 + i, Random.nextInt(1, 5)))
        }

        adapter = CartAdapter(cartItems) { item ->
            cartItems.remove(item)
            adapter.notifyItemRemoved(cartItems.indexOf(item))
            updateTotal()
        }
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)

        updateTotal()
    }

    private fun updateTotal() {
        val total = cartItems.sumOf { it.price * it.quantity }
        totalAmountTextView.text = getString(R.string.item_price_template, total)
    }

    data class CartItem(val name: String, val price: Double, val quantity: Int)

    inner class CartAdapter(
        private val items: MutableList<CartItem>,
        private val onDeleteClick: (CartItem) -> Unit
    ) : RecyclerView.Adapter<CartAdapter.ViewHolder>() {

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
            val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.item_cart_product, parent, false)
            return ViewHolder(view)
        }

        override fun onBindViewHolder(holder: ViewHolder, position: Int) {
            val item = items[position]
            holder.bind(item)
        }

        override fun getItemCount() = items.size

        inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
            private val nameTextView: TextView = itemView.findViewById(R.id.product_name)
            private val priceTextView: TextView = itemView.findViewById(R.id.product_price)
            private val quantityTextView: TextView = itemView.findViewById(R.id.product_quantity)
            private val deleteButton: MaterialButton = itemView.findViewById(R.id.delete_button)

            fun bind(item: CartItem) {
                nameTextView.text = item.name
                priceTextView.text = itemView.context.getString(R.string.item_price_template, item.price)
                quantityTextView.text = itemView.context.getString(R.string.item_quantity_template, item.quantity)

                deleteButton.setOnClickListener {
                    val position = adapterPosition
                    if (position != RecyclerView.NO_POSITION) {
                        onDeleteClick(items[position])
                    }
                }
            }
        }
    }
    private fun applyTheme() {
        val sharedPreferences = getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
        val isDarkTheme = sharedPreferences.getBoolean(KEY_DARK_THEME, false)
        val mode = if (isDarkTheme) AppCompatDelegate.MODE_NIGHT_YES else AppCompatDelegate.MODE_NIGHT_NO
        AppCompatDelegate.setDefaultNightMode(mode)
    }
}