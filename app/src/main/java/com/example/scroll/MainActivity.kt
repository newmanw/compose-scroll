package com.example.scroll

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent

class MainActivity : ComponentActivity() {
    private val survey = Survey()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        survey.forms.value = mutableListOf(
            Form("Form 1"),
            Form("Form 2")
        )

        setContent {
            MainScreen(
                survey = survey,
                onAddForm = { addForm() }
            )
        }
    }

    private fun addForm() {
        val forms = survey.forms.value.toMutableList()
        forms.add(Form("Form ${forms.size + 1}"))
        survey.forms.value = forms
    }
}