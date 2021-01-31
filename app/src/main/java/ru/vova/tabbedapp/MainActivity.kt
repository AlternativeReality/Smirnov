package ru.vova.tabbedapp

import android.app.TabActivity
import android.content.Intent
import android.content.pm.ActivityInfo
import android.os.Bundle
import android.widget.TabHost.TabSpec


class MainActivity : TabActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        val tabHost = tabHost
        var tabSpec: TabSpec

        tabSpec = tabHost.newTabSpec("tag1")
        tabSpec.setIndicator("Последние")
        tabSpec.setContent(Intent(this, OneActivity::class.java))
        tabHost.addTab(tabSpec)

        tabSpec = tabHost.newTabSpec("tag2")
        tabSpec.setIndicator("Лучшее")
        tabSpec.setContent(Intent(this, TwoActivity::class.java))
        tabHost.addTab(tabSpec)

        tabSpec = tabHost.newTabSpec("tag3")
        tabSpec.setIndicator("Горячие")
        tabSpec.setContent(Intent(this, ThirdActivity::class.java))
        tabHost.addTab(tabSpec)
    }
}