package com.kevin.devil

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import butterknife.ButterKnife
import butterknife.OnClick
import com.kevin.devil.models.DevilMessage
import timber.log.Timber

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        ButterKnife.bind(this)
    }

    @OnClick(R.id.click)
    fun click() {
        Devil.getHermes().sendMessage(
            DevilMessage("user",
                "message message",
                System.currentTimeMillis(),
                0,
                false,
                true)
        )
    }
}