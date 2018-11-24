package com.example.lenovo.mymusic.adapters

import android.app.FragmentTransaction
import android.content.Context
import android.support.v4.app.Fragment
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RelativeLayout
import android.widget.TextView
import com.example.lenovo.mymusic.R

import com.example.lenovo.mymusic.activities.NaviActivity
import com.example.lenovo.mymusic.fragments.AboutUsFragment
import com.example.lenovo.mymusic.fragments.FavoriteFragment
import com.example.lenovo.mymusic.fragments.NaviScreenFragment
import com.example.lenovo.mymusic.fragments.SettingsFragment

class NavigationDrawerAdapter(_contentList: ArrayList<String>, _getImages: IntArray, _context: Context)
    : RecyclerView.Adapter<NavigationDrawerAdapter.NavViewHolder>(){
    var contentList: ArrayList<String>?=null
    var getImages: IntArray?=null
    var mcontext: Context?=null

    init {
        this.contentList=_contentList
        this.getImages=_getImages
        this.mcontext=_context

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NavViewHolder {

        val itemView = LayoutInflater.from(parent.context)
                .inflate(R.layout.row_custom_navigationdrawer, parent, false)
        val returnThis = NavViewHolder(itemView)
        return returnThis
    }

    override fun getItemCount(): Int {
        return contentList?.size!!
    }
    override fun onBindViewHolder(holder: NavViewHolder, position: Int) {
        holder.icon_GET?.setBackgroundResource(getImages?.get(position) as Int)
        holder.text_GET?.setText(contentList?.get(position))
        holder.contentHlder?.setOnClickListener({
            if(position==0){
                val naviScreenFragment= NaviScreenFragment()
                (mcontext as NaviActivity).supportFragmentManager
                        .beginTransaction()
                        .replace(R.id.details_fragment,naviScreenFragment)
                        .commit()
            }
            else if(position==1){
                val favoriteFragment= FavoriteFragment()
                (mcontext as NaviActivity).supportFragmentManager
                        .beginTransaction()
                        .replace(R.id.details_fragment,favoriteFragment)
                        ?.commit()
            }
            else if(position==2){
                val settingsFragment= SettingsFragment()
                (mcontext as NaviActivity).supportFragmentManager
                        .beginTransaction()
                        .replace(R.id.details_fragment,settingsFragment)
                        .commit()
            }
            else if(position==3){
                val aboutUsFragment= AboutUsFragment()
                (mcontext as NaviActivity).supportFragmentManager
                        .beginTransaction()
                        .replace(R.id.details_fragment,aboutUsFragment)
                        .commit()
            }
            NaviActivity.Statified.drawerLayout?.closeDrawers()
        })

    }


    class NavViewHolder(itemView: View?) : RecyclerView.ViewHolder(itemView) {
        var icon_GET: ImageView?=null
        var text_GET: TextView?=null
        var contentHlder: RelativeLayout?=null
        init {
            icon_GET=itemView?.findViewById(R.id.icon_navdrawer)
            text_GET=itemView?.findViewById(R.id.text_navdrawer)
            contentHlder=itemView?.findViewById(R.id.nav_drawer_item_content_holder)
        }
    }



}

