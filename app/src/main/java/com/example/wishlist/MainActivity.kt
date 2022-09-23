package com.example.wishlist

import android.content.ActivityNotFoundException
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {

    lateinit var items: MutableList<Item>


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // items.add(Item("Apple", "1.29", "www.amazon.com"))

        // Lookup the RecyclerView in activity layout
        val itemsRv = findViewById<RecyclerView>(R.id.itemsRv)
        items = ItemFetcher.getItems()
        // Create adapter passing in the list of emails
        val adapter = ItemAdapter(items)
        // Attach the adapter to the RecyclerView to populate items
        itemsRv.adapter = adapter
        // Set layout manager to position the items
        itemsRv.layoutManager = LinearLayoutManager(this)
        items.removeAt(0)
        adapter.notifyItemRemoved(0)


        findViewById<Button>(R.id.button).setOnClickListener {
            // Fetch next 5 emails
            val itemName = findViewById<EditText>(R.id.itemNameText).text.toString()
            val price = findViewById<EditText>(R.id.priceText).text.toString()
            val url = findViewById<EditText>(R.id.urlText).text.toString()
            val newItem = Item(itemName, price, url)
            // Add new emails to existing list of emails
            items.add(newItem)
            // Notify the adapter there's new emails so the RecyclerView layout is updated
            adapter.notifyItemInserted(items.size - 1)
            itemsRv.scrollToPosition(items.size - 1)
        }

        adapter.setOnItemClickListener(object : ItemAdapter.OnItemClickListener {
            override fun onItemClick(itemView: View?, position: Int) {
                val item = items[position]

                try {
                    val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse("https://www.amazon.com/"))
                    ContextCompat.startActivity(itemsRv.context, browserIntent, null)
                } catch (e: ActivityNotFoundException) {
                    Toast.makeText(applicationContext, "Invalid URL for " + item.itemName, Toast.LENGTH_LONG).show()
                }

            }
        })

        adapter.setOnItemLongClickListener(object : ItemAdapter.OnItemLongClickListener {
            override fun onItemLongClick(itemView: View?, position: Int) {
                val itemName = items[position].itemName
                items.removeAt(position)
                adapter.notifyItemRemoved(position)
                Toast.makeText(applicationContext, "$itemName Deleted", Toast.LENGTH_LONG).show()
            }
        })


    }
}

