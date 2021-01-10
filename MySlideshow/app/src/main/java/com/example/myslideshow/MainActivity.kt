package com.example.myslideshow

import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import kotlinx.android.synthetic.main.activity_main.*
import kotlin.concurrent.timer

class MainActivity : AppCompatActivity() {
    private lateinit var player: MediaPlayer
    class MyAdapter(fm: FragmentManager): FragmentPagerAdapter(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {
        private val resources = listOf(
            R.drawable.slide00, R.drawable.slide01,
            R.drawable.slide02, R.drawable.slide03,
            R.drawable.slide04, R.drawable.slide05,
            R.drawable.slide06, R.drawable.slide07,
            R.drawable.slide08, R.drawable.slide09
        )

        override fun getCount(): Int {
            return resources.size
        }

        override fun getItem(position: Int): Fragment {
            return ImageFragment.newInstance((resources[position]))
        }

        override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
            super.destroyItem(container, position, `object`)
            println("[MyApp] destroy: $position")
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        pager.adapter = MyAdapter(supportFragmentManager)

        val handler = Handler()
        timer(period = 5000) {
            handler.post {
                pager.currentItem = (pager.currentItem + 1) % 10
            }
        }
        player = MediaPlayer.create(this, R.raw.getdown)
        player.isLooping = true
        button.setOnClickListener {
            pager.currentItem = 5
        }
    }

    override fun onResume() {
        super.onResume()
        player.start()
    }

    override fun onPause() {
        super.onPause()
        player.pause()
    }
}
