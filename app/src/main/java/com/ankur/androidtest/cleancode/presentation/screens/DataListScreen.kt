package com.ankur.androidtest.cleancode.presentation.screens


import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ankur.androidtest.cleancode.domain.model.DomainModel

@Composable
fun DataListScreen(items: List<DomainModel>, onItemClick: (String) -> Unit) {
    LazyColumn {
        items(items){item: DomainModel ->
            ListItem(
                item,
                 onItemClick
            )
        }
    }

}

@Composable
fun ListItem(item: DomainModel,onItemClick: (String) -> Unit) {
    val borderColor =  Color.Transparent

    Row(
        modifier = Modifier
            .padding(horizontal = 16.dp, vertical = 4.dp)
            .border(1.dp, borderColor, shape = RoundedCornerShape(4.dp))
            .fillMaxWidth()
            .height(75.dp)
            .background(Color.White)
            .padding(horizontal = 16.dp).clickable { onItemClick.invoke(item.content) },
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(text = item.title, fontSize = 16.sp, color = Color.Black)
        Row(verticalAlignment = Alignment.CenterVertically) {
            Text(text = item.subtitle, fontSize = 14.sp, color = Color.Gray)
            Icon(
                imageVector = Icons.Default.KeyboardArrowRight,
                contentDescription = null,
                tint = Color.Gray
            )
        }
    }
}
