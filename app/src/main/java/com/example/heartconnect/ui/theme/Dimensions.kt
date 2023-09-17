package com.example.heartconnect.ui.theme

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.unit.dp

const val vBox0 = 5.0;
const val vBox1 = 10.0;
const val vBox1andHalf = 15.0;
const val hBox1andHalf = 15.0;
const val vBox2 = 20.0;
const val vBox2andHalf = 30;
const val vBox3 = 40.0;
const val vBox4 = 80.0;
const val hBox0 = 5.0;
const val hBox0andHalf = 6.0;
const val hBox1 = 10.0;
const val hBox2 = 20.0;
const val hBox3 = 40.0;
const val hBox4 = 80.0;

@Composable
public fun VSizedBox0() {
    Spacer(modifier = Modifier.height(vBox0.dp))
};
@Composable
public fun VSizedBox1() {
    Spacer(modifier = Modifier.height(vBox1.dp))
}

@Composable
public fun VSizedBox1andHalf() {
    Spacer(modifier = Modifier.height(vBox1andHalf.dp));
}

@Composable
public fun VSizedBox2() {
    Spacer(modifier = Modifier.height(vBox2.dp))
}

@Composable
public fun VSizedBox2andHalf() {
    Spacer(modifier = Modifier.height(vBox2andHalf.dp));
}

@Composable
public fun VSizedBox3() {
    Spacer(modifier = Modifier.height(vBox3.dp));
}

@Composable
public fun VSizedBox4() {
    Spacer(modifier = Modifier.height(vBox4.dp));
}

@Composable
public fun HSizedBox0() {
    Spacer(modifier = Modifier.width(hBox0.dp));
}

@Composable
public fun HSizedBox0andHalf() {
    Spacer(modifier = Modifier.width(hBox0andHalf.dp));
}

@Composable
public fun HSizedBox1() {
    Spacer(modifier = Modifier.width(hBox1.dp));
}

@Composable
public fun HSizedBox1andHalf() {
    Spacer(modifier = Modifier.width(hBox1andHalf.dp));
}

@Composable
public fun HSizedBox2() {
    Spacer(modifier = Modifier.width(hBox2.dp));
}

@Composable
public fun HSizedBox3() {
    Spacer(modifier = Modifier.width(hBox3.dp));
}

@Composable
public fun HSizedBox4() {
    Spacer(modifier = Modifier.width(hBox4.dp));
}

val screenPadding = Modifier.padding(all = 16.dp);
val screenLeftRightPadding = Modifier.padding(start = 16.dp, end = 16.dp);
val screenTopBottomPadding = Modifier.padding(top = 16.dp, bottom = 16.dp);

