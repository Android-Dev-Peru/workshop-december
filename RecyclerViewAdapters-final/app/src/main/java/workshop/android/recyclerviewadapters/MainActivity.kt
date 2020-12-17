package workshop.android.recyclerviewadapters

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentPagerAdapter
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.android.synthetic.main.activity_main.*
import workshop.android.recyclerviewadapters.databinding.ActivityMainBinding
import workshop.android.recyclerviewadapters.ui.AllCreatureFragment
import workshop.android.recyclerviewadapters.ui.AllCreatureLinearLayoutManagerFragment

class MainActivity : AppCompatActivity() {

    private lateinit var binding:ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding =ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        setupNavigation()

        setupViewPager()

    }

    private fun setupNavigation() {
        navigation.setOnNavigationItemSelectedListener(onNavigationItemSelectedListener)
    }


    private val onNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        when (item.itemId) {
            R.id.navigation_all -> {
                title = getString(R.string.app_name)
                viewPager.setCurrentItem(0, false)
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_favorites -> {
                title = getString(R.string.title_favorites)
                viewPager.setCurrentItem(1, false)
                return@OnNavigationItemSelectedListener true
            }
        }
        false
    }

    private fun setupViewPager() {

        viewPager.adapter = object : FragmentPagerAdapter(supportFragmentManager, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {
            override fun getItem(position: Int): Fragment {
                if (position == 1) {
                    return AllCreatureLinearLayoutManagerFragment.newInstance()
                }
                return AllCreatureFragment.newInstance()
            }

            override fun getCount() = 2
        }
    }

}