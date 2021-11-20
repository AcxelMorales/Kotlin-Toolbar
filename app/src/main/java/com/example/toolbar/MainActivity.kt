package com.example.toolbar

import android.content.Intent

import androidx.appcompat.app.AppCompatActivity

import android.os.Bundle
import android.util.Log

import android.view.Menu
import android.view.MenuItem

import android.widget.Button
import android.widget.Toast

import androidx.appcompat.widget.Toolbar
import androidx.appcompat.widget.SearchView

class MainActivity : AppCompatActivity() {

    var toolbar: Toolbar? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        toolbar = findViewById(R.id.toolbar)
        toolbar?.setTitle(R.string.app_name)

        setSupportActionBar(toolbar)

        val btn = findViewById<Button>(R.id.btnGo)

        btn.setOnClickListener {
            val intent = Intent(this, FavActivity::class.java)
            startActivity(intent)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu, menu)

        val searchItem = menu.findItem(R.id.search)
        var searchView = searchItem.actionView as SearchView

        searchView.queryHint = "Write your name ...."

        searchView.setOnQueryTextFocusChangeListener { v, hasFocus ->
            Log.d("LISTENER_FOCUS", hasFocus.toString())
        }

        searchView.setOnQueryTextListener(object: SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                if (query != null) {
                    Log.d("QUERY_SUBMIT", query)
                }

                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                if (newText != null) {
                    Log.d("QUERY_CHANGE", newText)
                }

                return true
            }
        })

        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_fav -> {
                Toast.makeText(this, "Elemento añadido como favoritos", Toast.LENGTH_SHORT).show()
                true
            } else -> {
                super.onOptionsItemSelected(item)
            }
        }
    }

}
