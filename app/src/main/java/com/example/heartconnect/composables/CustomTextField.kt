package com.example.heartconnect.composables

import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Icon
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.input.ImeAction
import com.example.heartconnect.ui.theme.BgColor
import com.example.heartconnect.ui.theme.Primary

@Composable
fun CustomTextField(
    modifier: Modifier,
    labelValue: String,
    leadingIcon: ImageVector,
    onTextChanged: (String) -> Unit,
    errorStatus: Boolean = false,
    isEnabled: Boolean,
    textValue: String,
) {
    val localFocusManager = LocalFocusManager.current

    OutlinedTextField(
        modifier = modifier,
        label = { Text(text = labelValue) },
        colors = TextFieldDefaults.outlinedTextFieldColors(
            focusedBorderColor = Primary,
            focusedLabelColor = Primary,
            cursorColor = Primary,
            backgroundColor = BgColor
        ),
        keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next),
        singleLine = true,
        maxLines = 1,
        value = textValue,
        onValueChange = {
            onTextChanged(it)
        },
        leadingIcon = {
            Icon(imageVector = leadingIcon, contentDescription = "")
        },
        isError = !errorStatus,
        enabled = isEnabled,
    )
}