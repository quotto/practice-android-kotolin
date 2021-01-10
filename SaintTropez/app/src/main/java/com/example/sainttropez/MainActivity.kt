package com.example.sainttropez

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.ContextMenu
import android.view.Menu
import android.view.MenuItem
import android.view.View
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        webView.settings.javaScriptEnabled = true
        webView.loadUrl("file:///android_asset/html/index.html")

        registerForContextMenu(webView)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        println("onCreateOptionMenu")
        menuInflater.inflate(R.menu.main, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item?.itemId) {
            R.id.top -> {
                webView.loadUrl("file:///android_asset/html/index.html")
                return true
            }
            R.id.launch01 -> {
                webView.loadUrl("file:///android_asset/html/lunch01.html")
                return true
            }
            R.id.launch02 -> {
                webView.loadUrl("file:///android_asset/html/lunch02.html")
                return true
            }
            R.id.dinner01 -> {
                webView.loadUrl("file:///android_asset/html/dinner01.html")
                return true
            }
            R.id.dinner02 -> {
                webView.loadUrl("file:///android_asset/html/dinner02.html")
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onCreateContextMenu(
        menu: ContextMenu?,
        v: View?,
        menuInfo: ContextMenu.ContextMenuInfo?
    ) {
        println("onCreateContextMenu")
        super.onCreateContextMenu(menu, v, menuInfo)
        menuInflater.inflate(R.menu.context, menu)
    }

    override fun onContextItemSelected(item: MenuItem): Boolean {
        when(item?.itemId) {
            R.id.sms -> {
                val number = "999-9999-9999"
                val uri = Uri.parse("sms:$number")
                var intent = Intent(Intent.ACTION_VIEW)
                intent.data = uri
                startActivity(intent)
                return true
            }
            R.id.mail -> {
                val email = "nobody@example.com"
                val subject = "予約の問合せ"
                val text = "以下のとおり予約希望します"
                val uri = Uri.parse("mailto:")
                val intent = Intent(Intent.ACTION_SENDTO)
                intent.data = uri
                intent.putExtra(Intent.EXTRA_EMAIL, arrayOf(email))
                    .putExtra(Intent.EXTRA_SUBJECT, subject)
                    .putExtra(Intent.EXTRA_TEXT, text)
                if(intent.resolveActivity(packageManager) != null) {
                    startActivity(intent)
                }
                return true
            }
            R.id.share -> {
                val text = "美味しいレストランを紹介します"
                val intent = Intent(Intent.ACTION_SEND)
                intent.type = "text/plain"
                intent.putExtra(Intent.EXTRA_TEXT, text)
                val chooser = Intent.createChooser(intent, null)
                if(intent.resolveActivity(packageManager) != null) {
                    startActivity(chooser)
                }
                return true
            }
            R.id.browse -> {
                val url: String = "https://www.yahoo.co.jp"
                val intent = Intent(Intent.ACTION_VIEW)
                intent.data = Uri.parse(url)
                if(intent.resolveActivity(packageManager) != null) {
                    startActivity(intent)
                }
                return true
            }
        }
        return super.onContextItemSelected(item)
    }
}
