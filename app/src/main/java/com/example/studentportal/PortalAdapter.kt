package com.example.studentportal

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_add_portal.view.*
import kotlinx.android.synthetic.main.portals.view.*

class PortalAdapter(private val portals: List<Portal>, val clickListener: (Portal) -> Unit) :
    RecyclerView.Adapter<PortalAdapter.ViewHolder>() {

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(portals[position])
    }

    override fun getItemCount(): Int {
        return portals.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        context = parent.context
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.portals, parent, false)
        )
    }

    lateinit var context: Context


    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        //private val tvPortal: TextView = itemView.findViewById(android.R.id.text1)

        fun bind(portal: Portal) {
            itemView.tvTitle.text = portal.name
            itemView.tvUrl.text = portal.url

            itemView.setOnClickListener { clickListener(portal) }

        }

    }
}

