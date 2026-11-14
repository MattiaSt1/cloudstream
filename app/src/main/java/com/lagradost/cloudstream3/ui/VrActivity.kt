package com.lagradost.cloudstream3.ui

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import java.util.concurrent.ConcurrentHashMap

class VrActivity : ComponentActivity() {
    companion object {
        const val EXTRA_HANDLER = "vr_handler"

        private val handlers = ConcurrentHashMap<String, (VrActivity, Bundle?) -> Unit>()

        fun register(id: String, handler: (VrActivity, Bundle?) -> Unit) {
            handlers[id] = handler
        }

        fun unregister(id: String) {
            handlers.remove(id)
        }

        fun intent(context: Context, id: String): Intent =
            Intent(context, VrActivity::class.java)
                .setAction(Intent.ACTION_MAIN)
                .addCategory("com.oculus.intent.category.VR")
                .putExtra(EXTRA_HANDLER, id)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val handler = intent.getStringExtra(EXTRA_HANDLER)?.let { handlers[it] }
        if (handler == null) {
            finish()
            return
        }
        handler(this, savedInstanceState)
    }
}
