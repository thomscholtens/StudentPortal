package com.example.studentportal

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.browser.customtabs.CustomTabsIntent
import androidx.recyclerview.widget.StaggeredGridLayoutManager


import kotlinx.android.synthetic.main.activity_list_portal.*
import kotlinx.android.synthetic.main.content_main.*

const val ADD_PORTAL_CODE = 100

class ListPortalActivity : AppCompatActivity() {

    private val portals = arrayListOf<Portal>()
    private val portalAdapter = PortalAdapter(portals)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list_portal)
        setSupportActionBar(toolbar)

        fab.setOnClickListener {
            startAddPortalActivity()
        }
        initViews()
    }

    private fun initViews() {
        rvPortals.layoutManager = StaggeredGridLayoutManager(2, LinearLayoutManager.VERTICAL)
        rvPortals.adapter = portalAdapter
        portals.add(Portal("Google", "http://google.com"))
        portalAdapter.notifyDataSetChanged()

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (resultCode == Activity.RESULT_OK) {
            when (requestCode) {
                ADD_PORTAL_CODE -> {
                    val portal = data!!.getParcelableExtra<Portal>(EXTRA_PORTAL)
                    portals.add(portal)
                    portalAdapter.notifyDataSetChanged()
                }
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        return false
    }

//    override fun onOptionsItemSelected(item: MenuItem): Boolean {
//        return when (item.itemId) {
//            R.id.action_settings -> true
//            else -> super.onOptionsItemSelected(item)
//        }
//    }

    private fun startAddPortalActivity() {
        val intent = Intent(this, AddPortalActivity::class.java)
        startActivityForResult(intent, ADD_PORTAL_CODE)
    }

    private fun onPortalClicked(portal: Portal) {
        val builder = CustomTabsIntent.Builder()
        builder.setToolbarColor(resources.getColor(R.color.colorPrimary)) // Use primary color
        val customTabsIntent = builder.build()
        try { // Try to open the tab
            customTabsIntent.launchUrl(this, Uri.parse(portal.url))
        } catch (ex: Exception) { // Catch exception when an invalid url has been filled in.
            Toast.makeText(this, "We failed opening the page...", Toast.LENGTH_LONG).show()
        }
    }


}
