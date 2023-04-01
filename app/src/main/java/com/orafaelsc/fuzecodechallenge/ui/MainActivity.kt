package com.orafaelsc.fuzecodechallenge.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.snackbar.Snackbar
import com.orafaelsc.fuzecodechallenge.R
import com.orafaelsc.fuzecodechallenge.commom.extensions.isConnected
import com.orafaelsc.fuzecodechallenge.di.MainModule
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.context.GlobalContext.loadKoinModules

class MainActivity : AppCompatActivity() {

    private val viewModel: MainViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        loadKoinModules(MainModule.module)
        setContentView(R.layout.activity_main)
        verifyConnection()
        initObserver()
    }

    private fun initObserver() {

    }

    private fun verifyConnection() {
        if (this.isConnected()) {
            viewModel.init()
        } else {
            showDisconnectedWarning()
        }
    }

    private fun showDisconnectedWarning() {
        Snackbar.make(
            findViewById(android.R.id.content),
            R.string.no_internet_connection_available,
            Snackbar.LENGTH_INDEFINITE,
        ).setAction(R.string.general_retry) {
            if (isConnected()) {
                viewModel.init()
            } else {
                showDisconnectedWarning()
            }
        }.show()
    }
}
