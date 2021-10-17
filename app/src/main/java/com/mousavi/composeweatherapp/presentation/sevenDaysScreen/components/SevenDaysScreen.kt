package com.mousavi.composeweatherapp.presentation.sevenDaysScreen.components

import androidx.compose.foundation.*
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Menu
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.mousavi.composeweatherapp.R
import com.mousavi.composeweatherapp.presentation.main.components.ConditionItem
import com.mousavi.composeweatherapp.ui.theme.ComposeWeatherAppTheme

@Composable
fun SevenDaysScreen(
    navController: NavController,
) {
    Column(modifier = Modifier
        .fillMaxSize()
        .background(color = Color(0xff061234))
    ) {
        WeatherCard(navController)

        Column(
            modifier = Modifier
                .verticalScroll(rememberScrollState())
        ) {
            RowItem(dayOfWeek = "Mon")
            RowItem(dayOfWeek = "Tue")
            RowItem(dayOfWeek = "Wed")
            RowItem(dayOfWeek = "Thu")
            RowItem(dayOfWeek = "Fri")
            RowItem(dayOfWeek = "Sat")
            RowItem(dayOfWeek = "Sun")
            Spacer(modifier = Modifier.height(30.dp))
        }
    }

}

@Composable
fun RowItem(
    dayOfWeek: String = "Monday",
    condition: String = "Rainy",
    icon: Painter = painterResource(id = R.drawable.main_cloud),
    maxTemp: Int = 20,
    minTempt: Int = 14,
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 20.dp),
        horizontalArrangement = Arrangement.SpaceEvenly,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(text = dayOfWeek, color = Color(0xff6A788F))
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                modifier = Modifier.width(50.dp),
                painter = icon, contentDescription = ""
            )
            Text(text = condition, color = Color(0xff6B768E))
        }

        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(text = "+${maxTemp}°", color = Color.White)
            Text(text = "+${minTempt}°", color = Color(0xff6B768E))
        }

    }
}

@Composable
fun WeatherCard(
    navController: NavController,
    title: String = "7 Days",
    cornerRadius: Dp = 50.dp,
) {
    Box {
        Box(
            modifier = Modifier
                .graphicsLayer {
                    //translationY = 6.dp.toPx()
                }
                .padding(horizontal = 10.dp)
                .clip(shape = RoundedCornerShape(size = cornerRadius))
                .align(Alignment.BottomCenter)
                .background(Color(0xff262092))
                .fillMaxWidth()
                .height(80.dp)
        )
        Card(
            modifier = Modifier
                .graphicsLayer {
                    translationY = -6.dp.toPx()
                }
                .fillMaxWidth(),
            shape = RoundedCornerShape(
                bottomEnd = cornerRadius,
                bottomStart = cornerRadius
            )
        ) {
            Column(
                modifier = Modifier
                    .background(
                        brush = Brush.verticalGradient(
                            Pair(0f, Color(0xff00B3FF)),
                            Pair(1f, Color(0xff4B12F7))
                        )
                    )
                    .border(
                        width = 1.dp, color = Color(0xff4D7BF7), shape = RoundedCornerShape(
                            bottomEnd = cornerRadius,
                            bottomStart = cornerRadius)
                    ),
                horizontalAlignment = Alignment.CenterHorizontally

            ) {

                Row(
                    modifier = Modifier
                        .padding(all = 16.dp)
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        modifier = Modifier.clickable {
                            navController.navigateUp()
                        },
                        imageVector = Icons.Default.ArrowBack,
                        contentDescription = "",
                        tint = Color.White)

                    Text(text = title, style = TextStyle(color = Color.White, fontSize = 20.sp))
                    Icon(imageVector = Icons.Default.Menu,
                        contentDescription = "",
                        tint = Color.White)
                }

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceEvenly,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Image(
                        modifier = Modifier.height(120.dp),
                        painter = painterResource(id = R.drawable.main_cloud),
                        contentDescription = ""
                    )

                    Column {
                        Text(text = "Tomorrow", color = Color.White)
                        Text(
                            text = buildAnnotatedString {
                                withStyle(
                                    style = SpanStyle(
                                        color = Color.White,
                                        fontSize = 50.sp
                                    )
                                ) {
                                    append("20")
                                }
                                withStyle(
                                    style = SpanStyle(
                                        color = Color(0xff6DC5FD),
                                        fontSize = 26.sp
                                    )
                                ) {
                                    append("/17°")
                                }
                            }
                        )
                        Text(
                            text = "Rainy-Cloudy",
                            color = Color(0xff6DC5FD),
                            fontSize = 12.sp
                        )

                    }
                }

                Divider(
                    modifier = Modifier
                        .padding(horizontal = 50.dp)
                        .padding(top = 10.dp),
                    color = Color(0xff4662FF)
                )

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 30.dp),
                    horizontalArrangement = Arrangement.SpaceEvenly
                ) {
                    ConditionItem(
                        painterResource(id = R.drawable.weather_windy),
                        "13km/h",
                        "Wind"
                    )
                    ConditionItem(
                        painterResource(id = R.drawable.humidity),
                        "24%",
                        "Humidity"
                    )
                    ConditionItem(
                        painterResource(id = R.drawable.chance_of_rain),
                        "87%",
                        "Chance of rain"
                    )
                }


            }
        }
    }

}


@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    ComposeWeatherAppTheme {
        SevenDaysScreen(rememberNavController())
    }
}