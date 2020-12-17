package workshop.android.recyclerviewadapters.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import workshop.android.recyclerviewadapters.databinding.SimpleListLayoutBinding
import workshop.android.recyclerviewadapters.model.CreatureStore
import workshop.android.recyclerviewadapters.simple.SimpleAdapter

class SimpleListLayoutActivity : AppCompatActivity() {

    private lateinit var binding: SimpleListLayoutBinding
    private val adapter = SimpleAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = SimpleListLayoutBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        adapter.data = CreatureStore.getCreatures()
        binding.settingsSimpleList.adapter = adapter
    }
}