import androidx.compose.foundation.layout.*
//noinspection UsingMaterialAndMaterial3Libraries
import androidx.compose.material.*
import androidx.compose.material3.Button
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
//noinspection UsingMaterialAndMaterial3Libraries
import androidx.compose.material.Text

@Composable
fun MyActivityLayout(
    onStartLocationUpdatesClick: () -> Unit,
    outputText: String
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Button(
            onClick = onStartLocationUpdatesClick,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Start Location Updates")
        }

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = outputText,
            modifier = Modifier.fillMaxWidth(),
            color = Color.Black,
            maxLines = 3,
            overflow = TextOverflow.Ellipsis
        )
    }
}
