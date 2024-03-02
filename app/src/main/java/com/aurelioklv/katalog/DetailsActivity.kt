package com.aurelioklv.katalog

import android.content.Intent
import android.os.Build
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.aurelioklv.katalog.data.Cat
import com.aurelioklv.katalog.databinding.ActivityDetailsBinding
import com.bumptech.glide.Glide

class DetailsActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details)

        binding = ActivityDetailsBinding.inflate(layoutInflater)

        val cat = if (Build.VERSION.SDK_INT >= 33) {
            intent.getParcelableExtra(EXTRA_CAT, Cat::class.java)
        } else {
            @Suppress("DEPRECATION")
            intent.getParcelableExtra(EXTRA_CAT)
        }
        cat?.apply {
            binding.tvDetailsName.text = name
            binding.tvDetailsDescription.text = description
            binding.tvDetailsOrigin.text = origin
            binding.tvDetailsTemperament.text = temperament
            binding.tvDetailsLifespan.text = lifeSpan

            Glide.with(this@DetailsActivity)
                .load(imageUrl)
                .placeholder(R.drawable.sitting_cat)
                .into(binding.imgDetailsPhoto)

            binding.actionShare.setOnClickListener {
                val message =
                    getString(R.string.share_message, name, imageUrl).trimMargin()

                val shareIntent = Intent().apply {
                    action = Intent.ACTION_SEND
                    putExtra(Intent.EXTRA_TEXT, message)
                    type = "text/plain"
                }

                startActivity(Intent.createChooser(shareIntent, "Share to: "))
            }
        }

        setContentView(binding.root)
    }

    companion object {
        const val EXTRA_CAT = "extra_cat"
    }
}