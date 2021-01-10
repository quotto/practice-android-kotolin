package com.example.myscheduler

import android.content.Intent
import android.os.Bundle
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import androidx.recyclerview.widget.LinearLayoutManager
import io.realm.Realm
import io.realm.kotlin.where

import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_main.*

class MainActivity : AppCompatActivity() {
    private lateinit var realm: Realm

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)
        realm = Realm.getDefaultInstance()
        list.layoutManager = LinearLayoutManager(this)
        val schedules = realm.where<Schedule>().findAll()
        val adapter = ScheduleAdapter(schedules)
        adapter.setOnItemClickListener { id ->
            val intent = Intent(this, ScheduleEditActivity::class.java)
                .putExtra("schedule_id", id)
            startActivity(intent)
        }
        list.adapter = adapter

        fab.setOnClickListener { view ->
            val intent = Intent(this, ScheduleEditActivity::class.java)
            startActivity(intent)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        realm.close()
    }
}
