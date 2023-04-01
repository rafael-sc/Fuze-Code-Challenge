package com.orafaelsc.fuzecodechallenge.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.orafaelsc.fuzecodechallenge.R
import com.orafaelsc.fuzecodechallenge.di.MainModule
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.context.GlobalContext.loadKoinModules

class MainActivity : AppCompatActivity() {

    private val viewModel: MainViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        loadKoinModules(MainModule.module)
        setContentView(R.layout.activity_main)
    }
}
