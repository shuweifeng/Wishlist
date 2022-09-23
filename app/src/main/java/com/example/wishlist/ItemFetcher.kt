package com.example.wishlist

class ItemFetcher {
    companion object {
        val itemNames = listOf("Apple", "Banana", "Orange", "Avocado", "Plum")
        val prices = listOf("1.29", "0.49", "3.99", "2.99", "4.99")
        val urls =  listOf("www.google.com", "www.amazon.com", "www.amazon.com", "www.amazon.com", "www.amazon.com")
        fun getItems(): MutableList<Item> {
            var items: MutableList<Item> = ArrayList()
            items.add(Item(itemNames[0], prices[0], urls[0]))
            return items
            /*
            for (i in 0..4) {
                val item = Item(itemNames[i], prices[i], urls[i])
                items.add(item)
            }
            return items
            */
        }
    }
}