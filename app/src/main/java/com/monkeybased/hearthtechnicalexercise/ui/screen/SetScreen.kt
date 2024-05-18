package com.monkeybased.hearthtechnicalexercise.ui.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.monkeybased.hearthtechnicalexercise.data.model.CardSet
import com.monkeybased.hearthtechnicalexercise.ui.theme.DarkLava
import com.monkeybased.hearthtechnicalexercise.ui.theme.HearthTechnicalExerciseTheme

@Composable
fun SetScreen(onSetSelected: (String) -> Unit) {
    LazyColumn(Modifier.background(Color.LightGray).fillMaxSize()) {
        itemsIndexed(CardSet.entries.toList()) { index, it ->
            Row(modifier = Modifier.fillMaxWidth().height(IntrinsicSize.Min).clickable(onClick = { onSetSelected(it.serverId) })) {
                Icon(
                    painter = painterResource(id = it.iconResId),
                    contentDescription = it.label,
                    modifier = Modifier.size(72.dp).weight(0.2f).padding(6.dp),
                    tint = DarkLava
                )
                Text(modifier = Modifier.align(Alignment.CenterVertically).weight(0.8f), text = it.label, fontSize = 24.sp, color = DarkLava)
            }
            if (index < CardSet.entries.toList().lastIndex) {
                Divider(color = DarkLava, thickness = 1.dp)
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun SetScreenPreview() {
    HearthTechnicalExerciseTheme {
        // A surface container using the 'background' color from the theme
        SetScreen {}
    }
}