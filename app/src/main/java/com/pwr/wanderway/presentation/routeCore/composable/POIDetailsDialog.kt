package com.pwr.wanderway.presentation.routeCore.composable

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
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
            Text(
                text = name,
                style = MaterialTheme.typography.bodyMedium,
                modifier = Modifier.padding(8.dp),
            )
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
            )

            Text(
                text = description,
                style = MaterialTheme.typography.bodyMedium,
                modifier = Modifier.padding(8.dp),
            )


            TextButton(
                onClick = { onDismissRequest() },
                modifier = Modifier.padding(8.dp),
            ) {
                Text("Ok")

            }
        }
    }
}