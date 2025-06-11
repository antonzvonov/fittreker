package ge.beka.fittreker.ui

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable

@Composable
fun FitTrekerTheme(content: @Composable () -> Unit) {
    val colors = lightColorScheme()
    MaterialTheme(colorScheme = colors, content = content)
}

*** End of File ***
