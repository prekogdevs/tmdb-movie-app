package com.tmdb.movieapp.presentation

import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle

object PartiallyBoldStringBuilder {
    fun buildPartiallyBoldString(boldPart: String, normalPart: String) = buildAnnotatedString {
        withStyle(style = SpanStyle(fontWeight = FontWeight.Bold)) {
            append(boldPart)
        }
        append(" $normalPart")
    }
}