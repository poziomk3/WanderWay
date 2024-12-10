package com.pwr.wanderway.presentation.routeCore.composable

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.pwr.wanderway.data.model.PointOfInterest
import com.pwr.wanderway.network.ImgUrl

@Composable
fun POIDetailsDialog(
    onDismissRequest: () -> Unit,
    pointOfInterest: PointOfInterest?,
) {

    if (pointOfInterest == null) return
    val (id, name, description, _) = pointOfInterest
    DialogWrapper(onDismissRequest={}) {
        Column(
            modifier = Modifier
                .padding(8.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.Top
            ){
                Text(
                    text = name,
                    style = MaterialTheme.typography.bodyMedium,
                    modifier = Modifier
                        .padding(8.dp)
                        .weight(1f),
                )
                Icon(
                    imageVector = Icons.Filled.Close,
                    contentDescription = "Close button",
                    modifier = Modifier
                        .clickable { onDismissRequest() }
                        .padding(8.dp)
                )
            }
            Image(
                painter = rememberAsyncImagePainter(
                    ImgUrl.getPOIImg(
                        id
                    )
                ),
                contentDescription = "Route Image",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(150.dp)
                    .clip(RoundedCornerShape(8.dp))
            )

            Text(
                text = description,
                style = MaterialTheme.typography.bodyMedium,
                modifier = Modifier.padding(8.dp),
            )

        }
    }
}