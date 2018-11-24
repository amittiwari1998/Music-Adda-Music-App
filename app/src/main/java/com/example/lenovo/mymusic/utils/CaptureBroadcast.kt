package com.example.lenovo.mymusic.utils

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.telephony.TelephonyManager
import com.example.lenovo.mymusic.R
import com.example.lenovo.mymusic.activities.NaviActivity
import com.example.lenovo.mymusic.fragments.SongsPlayingFragment

class CaptureBroadcast : BroadcastReceiver() {
    /*The broadcast receiver has a mandatory method called the onReceive() method
    * The onReceive() method receives the intent outside the app and performs the functions accordingly
    * Here the intent will be the calling state*/
    override fun onReceive(context: Context?, intent: Intent?) {
        /*Here we check whether the user has an outgoing call or not*/
        if (intent?.action == Intent.ACTION_NEW_OUTGOING_CALL) {
            try {
                try {
                    NaviActivity.Statified.notificationManager?.cancel(1978)
                } catch (e: Exception) {
                    e.printStackTrace()
                }
                /*If the media player was playing we pause it and change the play/pause button*/
                if (SongsPlayingFragment.Statified.mediaPlayer?.isPlaying as Boolean) {
                    SongsPlayingFragment.Statified.mediaPlayer?.pause()
                    SongsPlayingFragment.Statified.playPauseImageButton?.setBackgroundResource(R.drawable.play_icon)
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        } else {
            /*Here we use the telephony manager to get the access for the service*/
            val tm: TelephonyManager = context?.getSystemService(Context.TELEPHONY_SERVICE) as TelephonyManager
            when (tm?.callState) {
            /*We check the call state and if the call is ringing i.e. the user has an incoming call
            * then also we pause the media player*/
                TelephonyManager.CALL_STATE_RINGING -> {
                    try {
                        try {
                            NaviActivity.Statified.notificationManager?.cancel(1978)
                        } catch (e: Exception) {
                            e.printStackTrace()
                        }
                        if (SongsPlayingFragment.Statified.mediaPlayer?.isPlaying as Boolean) {
                            SongsPlayingFragment.Statified.mediaPlayer?.pause()
                            SongsPlayingFragment.Statified.playPauseImageButton?.setBackgroundResource(R.drawable.play_icon)
                        }
                    } catch (e: Exception) {
                        e.printStackTrace()
                    }
                }
            /*Else we do nothing*/
                else -> {
                }
            }
        }
    }
}