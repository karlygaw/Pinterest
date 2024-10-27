package kz.narxoz.android2

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import kz.narxoz.android2.ui.theme.Android2Theme
import androidx.compose.material3.CardDefaults

// 1. Data class representing each item with a title and image resource ID
data class PinterestItem(
    val title: String,
    val imageResId: Int
)

// 2. Sample data for demonstration
fun getSamplePinterestItems(): List<PinterestItem> {
    return listOf(
        PinterestItem("Sunset View", R.drawable.beach),
        PinterestItem("Mountain Hike", R.drawable.books),
        PinterestItem("City Lights", R.drawable.bridge),
        PinterestItem("Sunset View", R.drawable.cat),
        PinterestItem("Mountain Hike", R.drawable.cow),
        PinterestItem("City Lights", R.drawable.mount)
    )
}

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Android2Theme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    PinterestGrid(
                        items = getSamplePinterestItems(),
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

// 3. Composable function to display the Pinterest-style grid layout
@Composable
fun PinterestGrid(items: List<PinterestItem>, modifier: Modifier = Modifier) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(2), // Fixed number of columns
        modifier = modifier.padding(8.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp), // Space between rows
        horizontalArrangement = Arrangement.spacedBy(8.dp)  // Space between columns
    ) {
        items(items.size) { index ->
            PinterestCard(item = items[index])
        }
    }
}

// 4. Composable function for each Pinterest card, displaying an image from drawable and a title
@Composable
fun PinterestCard(item: PinterestItem) {
    Card(
        shape = RoundedCornerShape(8.dp),
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight(), // Allows flexible height based on image's aspect ratio
        colors = CardDefaults.cardColors(containerColor = Color.LightGray)
    ) {
        Column {
            // Display the image with ContentScale.Crop to fill the card area without distortion
            Image(
                painter = painterResource(id = item.imageResId),
                contentDescription = item.title,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(150.dp), // Fixed height for the image
                contentScale = ContentScale.Crop
            )
            // Displaying the title of the item
            Text(
                text = item.title,
                modifier = Modifier.padding(8.dp)
            )
        }
    }
}

// 5. Preview for Pinterest Grid to test in Compose Preview
@Preview(showBackground = true)
@Composable
fun PinterestGridPreview() {
    Android2Theme {
        PinterestGrid(getSamplePinterestItems())
    }
}
