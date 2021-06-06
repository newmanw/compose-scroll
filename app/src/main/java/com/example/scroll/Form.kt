package com.example.scroll

import androidx.compose.runtime.mutableStateOf

class FormField(val name: String) {
    val value = mutableStateOf("")
}

class Form(val name: String) {
    val fields = listOf(
        FormField("First Field"),
        FormField("Second Field"),
        FormField("Third Field"),
        FormField("Forth Field"),
        FormField("Fifth Field"),
        FormField("Sixth Field"),
        FormField("Seventh Field"),
        FormField("Eighth Field"),
        FormField("Ninth Field"),
        FormField("Tenth Field"),
        FormField("Eleventh Field"),
    )
}

class Survey {
    val forms = mutableStateOf(listOf<Form>())
}