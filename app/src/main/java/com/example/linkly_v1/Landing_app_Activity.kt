package com.example.linkly_v1

import android.content.Intent
import android.media.MediaPlayer
import android.net.Uri
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.WindowManager
import android.widget.Button
import android.widget.VideoView

class Landing_app_Activity : AppCompatActivity() {

    protected lateinit var w_fondo: VideoView
    protected lateinit var mMediaPlayer: MediaPlayer
    protected var mCurrentVideoPosition: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_landing_app)
        val btn_sigin_ = findViewById(R.id.btn_sigin_) as Button
        val btn_siginup_ = findViewById(R.id.btn_siginup__) as Button
        if (Build.VERSION.SDK_INT < 16) {
            window.setFlags(
                WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN)
        }
        // Hide the status bar.
        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_FULLSCREEN
        // Remember that you should never show the action bar if the
        // status bar is hidden, so hide that too if necessary.
        actionBar?.hide()
        w_fondo = findViewById<VideoView>(R.id.w_fondo)

        val uri = Uri.parse("android.resource://"
                +packageName
                +"/"
                +R.raw.shot_landing_video5)
        w_fondo.setVideoURI(uri)
        w_fondo.start()
        w_fondo.setOnPreparedListener { mp ->
            mMediaPlayer = mp
            mMediaPlayer.isLooping = true
            if(mCurrentVideoPosition != 0){
                mMediaPlayer.seekTo(mCurrentVideoPosition)
                mMediaPlayer.start()
            }

        }

        /*Butons*/

        btn_sigin_.setOnClickListener{
            goToSigin()
        }

        btn_siginup_.setOnClickListener{
            goToSiginUp()
        }

    }
    override fun onPause() {
        super.onPause()
        mCurrentVideoPosition = mMediaPlayer.currentPosition
        w_fondo.pause()

    }
    override fun onResume() {
        super.onResume()
        w_fondo.start()
    }
    override fun onDestroy() {
        super.onDestroy()
        mMediaPlayer.release()
        //mMediaPlayer = null
    }

    private fun goToSigin(){
        val intent= Intent (this, SignInActivity::class.java)
        this.startActivity(intent)
    }

    private fun goToSiginUp(){
        val intent= Intent (this, SignUpActivity::class.java)
        this.startActivity(intent)
    }

}