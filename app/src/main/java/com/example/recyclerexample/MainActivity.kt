 package com.example.recyclerexample

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

 class MainActivity : AppCompatActivity() {
    private lateinit var rv_adapter: RecyclerAdapter
    private lateinit var id_rv_recycler: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // initialize the recycler view
        id_rv_recycler = findViewById(R.id.id_rv_recycler)

        // initialize the recycler adapter class
        rv_adapter = RecyclerAdapter(applicationContext,
            id_rv_recycler,
            R.layout.recycler_item,
            LinearLayoutManager(applicationContext, RecyclerView.VERTICAL, false)
        )

        // add stuff to the recycler adapter so it display in the recycler view
        rv_adapter.addToAdapter(DataClass("Jumanji", "Welcome to the jungle"))
        rv_adapter.addToAdapter(DataClass("Venom", "Let there be carnage"))
        rv_adapter.addToAdapter(DataClass("The Witcher", "Nightmare of the wolf"))
        rv_adapter.addToAdapter(DataClass("Teen Titans", "Go to the movies"))
        rv_adapter.onClickListener(
            object : RecyclerAdapter.OnItemClickListener {
                override fun onItemClick(position: Int, view: View) {
                    // click execution goes here
                    Toast.makeText(applicationContext, "position $position was clicked", Toast.LENGTH_SHORT).show()
                }
                override fun onLongItemClick(position: Int, view: View) {
                    // long click execution goes here
                    Toast.makeText(applicationContext, "position $position was long  clicked", Toast.LENGTH_SHORT).show()
                }
            }
        )
    }
}