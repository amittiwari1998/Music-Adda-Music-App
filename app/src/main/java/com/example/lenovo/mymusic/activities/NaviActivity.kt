package com.example.lenovo.mymusic.activities


import android.app.Notification
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v4.widget.DrawerLayout
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.DefaultItemAnimator
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.Toolbar
import com.example.lenovo.mymusic.R
import com.example.lenovo.mymusic.adapters.NavigationDrawerAdapter
import com.example.lenovo.mymusic.fragments.NaviScreenFragment

class NaviActivity : AppCompatActivity(){
    var navigationDrawerIconsList: ArrayList<String> = arrayListOf()
    var images_for_navdrawer= intArrayOf(R.drawable.navigation_allsongs,R.drawable.navigation_favorites,
            R.drawable.navigation_settings,R.drawable.navigation_aboutus)
    var trackNotificationBuilder: Notification?=null
    object Statified{
        var drawerLayout: DrawerLayout?=null
        var notificationManager: NotificationManager?= null

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_navi)
        val toolbar=findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)
        Statified.drawerLayout=findViewById(R.id.drawer_layout)
        navigationDrawerIconsList.add("All Songs")
        navigationDrawerIconsList.add("Favorites")
        navigationDrawerIconsList.add("Settings")
        navigationDrawerIconsList.add("About Us")
        val toggle=ActionBarDrawerToggle(this@NaviActivity, Statified.drawerLayout,toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close)
        Statified.drawerLayout?.addDrawerListener(toggle)
        toggle.syncState()

        val naviScreenFragment= NaviScreenFragment()
        this.supportFragmentManager
                .beginTransaction()
                .add(R.id.details_fragment,naviScreenFragment,"MainScreenFragment")
                .commit()
        val _navigationAdapter = NavigationDrawerAdapter(navigationDrawerIconsList, images_for_navdrawer, this)
        _navigationAdapter.notifyDataSetChanged()

        val navigation_recycler_view=findViewById<RecyclerView>(R.id.navigation_recycle_view)
        navigation_recycler_view.layoutManager=LinearLayoutManager(this)
        navigation_recycler_view.itemAnimator=DefaultItemAnimator()
        navigation_recycler_view.adapter=_navigationAdapter
        navigation_recycler_view.setHasFixedSize(true)

        val intent= Intent(this@NaviActivity,NaviActivity::class.java)
        val pIntent = PendingIntent.getActivity(this@NaviActivity,System.currentTimeMillis().toInt(),intent,0)
        trackNotificationBuilder = Notification.Builder(this)
                .setContentTitle("A track is playing in background")
                .setSmallIcon(R.drawable.img)
                .setContentIntent(pIntent)
                .setOngoing(true)
                .setAutoCancel(true)
                .build()
        Statified.notificationManager=getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

    }




    override fun onStart() {
        super.onStart()
        try {
            Statified.notificationManager?.cancel(1978)
        }
        catch (e: Exception){
            e.printStackTrace()
        }
    }

    override fun onStop() {
        super.onStop()
        try {
            Statified.notificationManager?.notify(1978,trackNotificationBuilder)
        }
        catch(e: Exception){
            e.printStackTrace()
        }
    }

    override fun onResume() {
        super.onResume()
        try {
            Statified.notificationManager?.cancel(1978)
        }
        catch (e: Exception){
            e.printStackTrace()
        }
    }
}
