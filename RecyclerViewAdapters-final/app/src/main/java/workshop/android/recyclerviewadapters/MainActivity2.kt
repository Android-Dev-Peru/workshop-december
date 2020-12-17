package workshop.android.recyclerviewadapters

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.GridLayoutManager
import workshop.android.recyclerviewadapters.databinding.FragmentSettingsBinding

class MainActivity2 : AppCompatActivity() {

   // private val adapter = CreatureAdapter(CreatureStore.getCreatures())

    private lateinit var binding:FragmentSettingsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding =FragmentSettingsBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

       // binding.settingsList.adapter = adapter

        binding.settingsList.layoutManager = GridLayoutManager(this, 2, GridLayoutManager.VERTICAL, false)
    }

}