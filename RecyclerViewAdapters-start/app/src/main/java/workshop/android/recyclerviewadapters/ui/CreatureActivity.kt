package workshop.android.recyclerviewadapters.ui

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_creature.*
import workshop.android.recyclerviewadapters.R
import workshop.android.recyclerviewadapters.model.Creature
import workshop.android.recyclerviewadapters.model.Favorites

class CreatureActivity : AppCompatActivity() {

    companion object {
        private const val EXTRA_CREATURE_ID = "EXTRA_CREATURE_ID"
        //TODO

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
        //TODO
    }

    private fun setupTitle() {
        //TODO

    }

    private fun setupViews() {
        //TODO

    }

    private fun setupFavoriteButton() {
        //TODO

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
    // TODO
    }
}