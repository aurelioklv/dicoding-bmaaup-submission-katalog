package com.aurelioklv.katalog

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
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

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.about_page -> {
                val aboutIntent = Intent(this@MainActivity, AboutActivity::class.java)
                startActivity(aboutIntent)
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun getCats(): ArrayList<Cat> {
        val dataName = resources.getStringArray(R.array.data_name)
        val dataImageUrl = resources.getStringArray(R.array.data_image_url)
        val dataDescription = resources.getStringArray(R.array.data_description)
        val dataOrigin = resources.getStringArray(R.array.data_origin)
        val dataTemperament = resources.getStringArray(R.array.data_temperament)
        val dataLifeSpan = resources.getStringArray(R.array.data_lifespan)
        val cats = ArrayList<Cat>()

        for (i in dataName.indices) {
            val cat = Cat(
                dataName[i],
                dataImageUrl[i],
                dataDescription[i],
                dataOrigin[i],
                dataTemperament[i],
                dataLifeSpan[i],
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
        val detailsIntent = Intent(this@MainActivity, DetailsActivity::class.java)
        detailsIntent.putExtra(DetailsActivity.EXTRA_CAT, cat)
        startActivity(detailsIntent)
    }
}