package com.aurelioklv.katalog

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.aurelioklv.katalog.data.Cat
import com.aurelioklv.katalog.ui.ListCatAdapter

class MainActivity : AppCompatActivity() {
    private lateinit var rvCats: RecyclerView
    private val cats = ArrayList<Cat>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        rvCats = findViewById(R.id.rv_cats)
        rvCats.setHasFixedSize(true)

        cats.addAll(getCats())
        showRecyclerList()
    }

    private fun getCats(): ArrayList<Cat> {
        val dataName = resources.getStringArray(R.array.data_name)
        val dataImageUrl = resources.getStringArray(R.array.data_image_url)
        val dataDescription = resources.getStringArray(R.array.data_description)
        val dataOrigin = resources.getStringArray(R.array.data_origin)
        val dataLifeSpan = resources.getStringArray(R.array.data_lifespan)
        val cats = ArrayList<Cat>()

        for (i in dataName.indices) {
            val cat = Cat(
                dataName[i],
                dataImageUrl[i],
                dataDescription[i],
                dataOrigin[i],
                dataLifeSpan[i]
            )
            cats.add(cat)
        }
        return cats
    }

    private fun showRecyclerList() {
        rvCats.layoutManager = LinearLayoutManager(this)
        val listCatAdapter = ListCatAdapter(cats)
        rvCats.adapter = listCatAdapter

        listCatAdapter.setOnItemClickCallback(object : ListCatAdapter.OnItemClickCallback {
            override fun onItemClicked(data: Cat) {
                showSelectedCat(data)
            }
        })
    }

    private fun showSelectedCat(cat: Cat) {
        Log.v("CLICK", "$cat")
        Toast.makeText(this, "Click " + cat.name, Toast.LENGTH_SHORT).show()
    }
}