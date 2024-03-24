import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Card
import androidx.compose.material.Scaffold
import androidx.compose.material.SnackbarDuration
import androidx.compose.material.SnackbarHost
import androidx.compose.material.SnackbarHostState
import androidx.compose.material.SnackbarResult
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.android.whileinuselocation.R
import com.example.android.whileinuselocation.UI.ForegroundLocationViewModel

@Composable
fun MyActivityLayout(
    onStartLocationUpdatesClick: () -> Unit,
    viewModel: ForegroundLocationViewModel,
) {

    val showSnackbar = viewModel.showSnackbar.observeAsState(initial = false).value
    val snackbarHostState = remember { SnackbarHostState() }

    Scaffold(
        snackbarHost = { SnackbarHost(hostState = snackbarHostState) }
    ) { paddingValues ->

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
                .padding(paddingValues),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Button(
                modifier = Modifier.fillMaxWidth(),
                colors = ButtonDefaults.buttonColors(backgroundColor = colorResource(R.color.colorPrimary)),
                onClick = onStartLocationUpdatesClick,
            ) {
                Text(
                    text = viewModel.buttonText.observeAsState(initial = "").value,
                    color = Color.White,
                )
            }

            Spacer(modifier = Modifier.height(16.dp))
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(),
                border = BorderStroke(1.dp, Color.Black)
            ) {
                Text(
                    text = viewModel.outputText.observeAsState(initial = "").value,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    color = Color.Black,
                    overflow = TextOverflow.Ellipsis
                )
            }

            val snackbarText = viewModel.snackbarText.observeAsState(initial = "").value
            val snackbarActionLabel = viewModel.snackbarActionLabel.observeAsState("").value
            if (showSnackbar) {
                LaunchedEffect(snackbarHostState) {

                    val result = snackbarHostState.showSnackbar(
                        message = snackbarText,
                        actionLabel = snackbarActionLabel,
                        duration = SnackbarDuration.Short,
                    )

                    when (result) {
                        SnackbarResult.ActionPerformed -> {
                            viewModel.action()

                        }

                        SnackbarResult.Dismissed -> {
                            viewModel.dismissed()
                        }
                    }
                    viewModel.resetSnackbar()
                }
            }

        }
    }
}

@Preview
@Composable //to create a preview of a composable create a new composable
fun PreviewMyActivityLayout() {
    val viewModel = viewModel<ForegroundLocationViewModel>()
    MyActivityLayout(
        onStartLocationUpdatesClick = {},
        viewModel = viewModel
    )
}

@Preview
@Composable
fun PreviewMyActivityLayoutDark() {
    val viewModel = viewModel<ForegroundLocationViewModel>()
    MyActivityLayout(
        onStartLocationUpdatesClick = {},
        viewModel = viewModel
    )
}
