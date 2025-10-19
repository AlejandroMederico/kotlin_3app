package com.mederico.android.superheroapp

import android.os.Bundle
import android.util.Log
import android.util.TypedValue
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.isVisible
import com.mederico.android.R
import com.mederico.android.databinding.ActivityDetailSuperHeroBinding
import com.mederico.android.superheroapp.SuperHeroListActivity.Companion.EXTRA_ID
import com.squareup.picasso.Picasso
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.Retrofit.Builder
import retrofit2.converter.gson.GsonConverterFactory
import kotlin.math.roundToInt

class DetailSuperHeroActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailSuperHeroBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityDetailSuperHeroBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val id: String = intent.getStringExtra(EXTRA_ID).orEmpty()
        getSuperHeroInformation(id)
    }

    private fun getSuperHeroInformation(id: String) {
        CoroutineScope(Dispatchers.IO).launch {
            val superHeroDetail = getRetrofit().create(ApiService::class.java).getSuperHeroDetails(id)
            if (superHeroDetail.body() != null) {
                runOnUiThread {
                    createUI(superHeroDetail.body()!!)
                }
            }

        }
    }

    private fun createUI(superHero: SuperHeroDetailResponse) {
        Picasso.get().load(superHero.image.url).into(binding.ivSuperheroDetail)
        binding.tvSuperheroNameDetail.text = superHero.name
        prepareStats(superHero.powerstats)
        binding.tvSuperheroRealName.text = superHero.biography.fullName
        binding.tvSuperheroPublisher.text = superHero.biography.publisher
    }

    private fun prepareStats(powerstats: PowerStatsResponse) {
        updateHeight(binding.viewIntelligence, powerstats.intelligence)
        updateHeight(binding.viewStrength, powerstats.strength)
        updateHeight(binding.viewSpeed, powerstats.speed)
        updateHeight(binding.viewDurability, powerstats.durability)
        updateHeight(binding.viewPower, powerstats.power)
        updateHeight(binding.viewCombat, powerstats.combat)
    }

    private fun updateHeight(view: View, stat: String) {
        val params = view.layoutParams
        params.height = pixelToDP(stat.toFloat())
        view.layoutParams = params
    }

    private fun pixelToDP(px: Float): Int {
        return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, px, resources.displayMetrics).roundToInt()
    }

    private fun getRetrofit(): Retrofit {
        return Builder()
            .baseUrl("https://www.superheroapi.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
}