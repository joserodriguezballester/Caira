package com.example.caira2.repository

import com.example.caira2.R
import com.example.caira2.model.WelcomeFrame

object WelcomeList {

    val welcomeFrames = listOf<WelcomeFrame>(
        WelcomeFrame(
            R.drawable.wel_logo_text,
            R.string.futuro_fragment_titulo,
            R.string.nulo
        ),
        WelcomeFrame(
            R.drawable.wel_estudiantes,
            R.string.estudiantes_fragment_titulo,
            R.string.estudiantes_fragment
        ),
        WelcomeFrame(
            R.drawable.wel_picscaira,
            R.string.estudiantes_fragment_subtitulo,
            R.string.futuro_fragment
        ),
        WelcomeFrame(
            R.drawable.captura1,
            R.string.nulo,
            R.string.nulo
            ),
        WelcomeFrame(
            R.drawable.captura2,
            R.string.nulo,
            R.string.nulo
        ), WelcomeFrame(
            R.drawable.captura3,
            R.string.nulo,
            R.string.nulo
        ),
        )
}