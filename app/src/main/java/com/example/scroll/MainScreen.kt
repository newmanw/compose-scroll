package com.example.scroll

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.scroll.ui.theme.ScrollTheme

@Composable
fun MainScreen(
    survey: Survey,
    onAddForm: (() -> Unit)? = null,
) {
    val scope = rememberCoroutineScope()
    val scaffoldState = rememberScaffoldState()
    val listState = rememberLazyListState()

    ScrollTheme {
        Scaffold(
            scaffoldState = scaffoldState,
            topBar = {
                TopBar()
            },
            content = {
                Survey(survey, listState)
            },
            floatingActionButton = {
                FloatingActionButton(onClick = { onAddForm?.invoke() }) {
                    Icon(
                        Icons.Default.Add,
                        contentDescription = "Add Thing"
                    )
                }
            }
        )
    }
}

@Composable
fun TopBar() {
    TopAppBar(
        title = {
            Text("Auto Scroll Test")
        }
    )
}

@Composable
fun Survey(
    survey: Survey,
    listState: LazyListState
) {
    val forms by survey.forms
    var initialized by remember { mutableStateOf(false) }

    LaunchedEffect(forms.size) {
        if (initialized) {
            listState.animateScrollToItem(forms.size)
        }

        initialized = true
    }

    LazyColumn(
        state = listState
    ) {
        item {
            Header()
        }

        items(forms) { form ->
            Form(form)
        }
    }
}

@Composable
fun Form(form: Form) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
    ) {
        Text(
            text = form.name,
            style = MaterialTheme.typography.h6,
            modifier = Modifier.padding(8.dp)
        )

        for (field in form.fields) {
            TextField(
                value = field.value.value,
                label = { Text(field.name) },
                onValueChange = { },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            )
        }
    }
}

@Composable
fun Header() {
    Column(
        verticalArrangement = Arrangement.Center,
        modifier = Modifier
            .fillMaxWidth()
            .height(400.dp)
            .background(color = Color.LightGray)
            .padding(16.dp)
    ) {
        Text(
            text = "Header Content",
            style = MaterialTheme.typography.h4
        )
    }
}