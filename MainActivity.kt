package com.bootos.app

import android.app.Activity
import android.os.Bundle
import android.os.Build
import android.graphics.Color
import android.view.Gravity
import android.view.ViewGroup
import android.widget.Button
import android.widget.LinearLayout
import android.widget.ScrollView
import android.widget.TextView

class MainActivity : Activity() {
    private lateinit var statusText: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val root = LinearLayout(this).apply {
            orientation = LinearLayout.VERTICAL
            setPadding(28, 32, 28, 28)
            setBackgroundColor(Color.rgb(9, 44, 145))
        }
        val scroll = ScrollView(this)
        val content = LinearLayout(this).apply {
            orientation = LinearLayout.VERTICAL
            gravity = Gravity.CENTER_HORIZONTAL
        }

        val title = TextView(this).apply {
            text = "BootOS"
            textSize = 34f
            setTextColor(Color.WHITE)
            gravity = Gravity.CENTER
        }
        val subtitle = TextView(this).apply {
            text = "Firmware Research Tool · v0.1.0"
            textSize = 15f
            setTextColor(Color.LTGRAY)
            gravity = Gravity.CENTER
            setPadding(0, 8, 0, 28)
        }
        statusText = TextView(this).apply {
            textSize = 16f
            setTextColor(Color.WHITE)
            setPadding(18, 18, 18, 18)
            setBackgroundColor(Color.rgb(20, 65, 165))
        }

        content.addView(title, matchWrap())
        content.addView(subtitle, matchWrap())
        content.addView(statusText, matchWrap())
        content.addView(actionButton("Check device") { showDeviceInfo() })
        content.addView(actionButton("Firmware browser (coming next)") {
            statusText.text = "Firmware browser is not implemented yet. No firmware has been downloaded."
        })
        content.addView(actionButton("Compatibility check") {
            statusText.text = "iOS boot compatibility: NOT ESTABLISHED\n\nAn unlocked Android bootloader and a Snapdragon CPU do not by themselves make iOS compatible. Boot actions are disabled."
        })
        content.addView(TextView(this).apply {
            text = "Safety: this build does not unlock the bootloader, flash partitions, or attempt to boot iOS."
            textSize = 13f
            setTextColor(Color.LTGRAY)
            setPadding(8, 24, 8, 8)
        }, matchWrap())

        root.addView(scroll, LinearLayout.LayoutParams(
            ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT
        ))
        scroll.addView(content)
        setContentView(root)
        showDeviceInfo()
    }

    private fun matchWrap() = LinearLayout.LayoutParams(
        ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT
    ).apply { bottomMargin = 16 }

    private fun actionButton(label: String, action: () -> Unit) = Button(this).apply {
        text = label
        setOnClickListener { action() }
        isAllCaps = false
        layoutParams = matchWrap()
    }

    private fun showDeviceInfo() {
        val model = "${Build.MANUFACTURER} ${Build.MODEL}"
        val androidVersion = Build.VERSION.RELEASE
        statusText.text = "Device: $model\nAndroid: $androidVersion\nBootloader: UNKNOWN (not reliably exposed to ordinary apps)\n\nFirmware: not selected\nDownload: idle\nCompatibility: not established"
    }
}
