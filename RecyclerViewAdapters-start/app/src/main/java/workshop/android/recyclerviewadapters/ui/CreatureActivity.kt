package workshop.android.recyclerviewadapters.ui

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_creature.*
import workshop.android.recyclerviewadapters.R
import workshop.android.recyclerviewadapters.adapters.FoodAdapter
import workshop.android.recyclerviewadapters.model.Creature
import workshop.android.recyclerviewadapters.model.CreatureStore
import workshop.android.recyclerviewadapters.model.Favorites

class CreatureActivity : AppCompatActivity() {
    private lateinit var creature: Creature
    private val adapter = FoodAdapter(mutableListOf())

    companion object {
        private const val EXTRA_CREATURE_ID = "EXTRA_CREATURE_ID"

        fun newIntent(context: Context, creatureId: Int): Intent {
            val intent = Intent(context, CreatureActivity::class.java)
            intent.putExtra(EXTRA_CREATURE_ID, creatureId)
            return intent
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_creature)

        setupCreature()
        setupTitle()
        setupViews()
        setupFoods()
        setupFavoriteButton()
    }


    private fun setupCreature() {
        val creatureById = CreatureStore.getCreatureById(intent.getIntExtra(EXTRA_CREATURE_ID, 1))
        if (creatureById == null) {
            Toast.makeText(this, getString(R.string.invalid_creature), Toast.LENGTH_SHORT).show()
            finish()
        } else {
            creature = creatureById
        }
    }

    private fun setupTitle() {
        title = String.format(getString(R.string.detail_title_format), creature.nickname)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    private fun setupViews() {
        headerImage.setImageResource(resources.getIdentifier(creature.uri, null, packageName))
        fullName.text = creature.fullName
        planet.text = creature.planet
    }

    private fun setupFavoriteButton() {
        setupFavoriteButtonImage(creature)
        setupFavoriteButtonClickListener(creature)
    }

    private fun setupFavoriteButtonImage(creature: Creature) {
        if (creature.isFavorite) {
            favoriteButton.setImageDrawable(getDrawable(R.drawable.ic_favorite_black_24dp))
        } else {
            favoriteButton.setImageDrawable(getDrawable(R.drawable.ic_favorite_border_black_24dp))
        }
    }

    private fun setupFavoriteButtonClickListener(creature: Creature) {
        favoriteButton.setOnClickListener { _ ->
            if (creature.isFavorite) {
                favoriteButton.setImageDrawable(getDrawable(R.drawable.ic_favorite_border_black_24dp))
                Favorites.removeFavorite(creature, this)
            } else {
                favoriteButton.setImageDrawable(getDrawable(R.drawable.ic_favorite_black_24dp))
                Favorites.addFavorite(creature, this)
            }
        }
    }

    private fun setupFoods(){
        foodRecyclerView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        foodRecyclerView.adapter = adapter
        val foods = CreatureStore.getCreatureFoods(creature)
        adapter.updateFoods(foods)
    }
}