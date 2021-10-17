package com.mousavi.composeweatherapp.presentation.main.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.NavigateNext
import androidx.compose.material.icons.filled.SettingsApplications
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.mousavi.composeweatherapp.R
import com.mousavi.composeweatherapp.presentation.main.MainViewModel
import com.mousavi.composeweatherapp.ui.theme.ComposeWeatherAppTheme
import com.mousavi.composeweatherapp.util.Screen
import java.util.concurrent.locks.Condition

@Composable
fun MainScreen(
    navController: NavController,
    viewModel: MainViewModel = hiltViewModel(),
) {
    val state = viewModel.state.value

    val scaffoldState = rememberScaffoldState()

    Scaffold(
        scaffoldState = scaffoldState,
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(color = Color(0xff061234))
        ) {
            WeatherMainCard(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
            )
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(all = 16.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = "Today",
                    style = TextStyle(color = Color.White, fontSize = 20.sp)
                )
                Row(
                    modifier = Modifier.clickable {
                        navController.navigate(Screen.SevenDaysScreen.route)
                    }
                ) {
                    Text(
                        text = "7 days",
                        color = Color(0xFF6B7891)
                    )
                    Icon(
                        imageVector = Icons.Default.NavigateNext,
                        contentDescription = "",
                        tint = Color(0xFF6B7891)
                    )
                }
            }
            Row(
                modifier = Modifier
                    .padding(horizontal = 20.dp)
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceAround
            ) {

                for (i in 1..4) {
                    WeatherItem(
                        modifier = Modifier
                            .weight(1f)
                            .padding(horizontal = 10.dp)
                            .padding(vertical = 20.dp),
                        index = i,
                        isSelected = state == i,
                        time = "${10 + i - 1}:00",
                        temperature = 21 + 2 * i
                    ) {
                        viewModel.onEvent(i)
                    }
                }
            }

        }
    }
}

@Composable
fun WeatherMainCard(
    modifier: Modifier = Modifier,
    title: String = "Tehran",
    updatingText: String = "Updating",
    temperature: Int = 21,
    weatherCondition: String = "ThunderStorm",
    date: String = "Monday, 17 may",
    cornerRadius: Dp = 50.dp,
) {
    Box(
        modifier = modifier,
    ) {
        Box(modifier = Modifier
            .graphicsLayer {
                translationY = -3.dp.toPx()
            }
            .padding(horizontal = 10.dp)
            .clip(shape = RoundedCornerShape(size = cornerRadius))
            .align(Alignment.BottomCenter)
            .background(Color(0xff262092))
            .fillMaxHeight(0.2f)
            .fillMaxWidth())
        Card(
            modifier = modifier
                .fillMaxWidth()
                .fillMaxHeight(0.98f),
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
                    Icon(imageVector = Icons.Default.Menu,
                        contentDescription = "",
                        tint = Color.White)
                    Text(text = title, style = TextStyle(color = Color.White, fontSize = 20.sp))
                    Icon(imageVector = Icons.Default.SettingsApplications,
                        contentDescription = "",
                        tint = Color.White)
                }
                Image(
                    modifier = Modifier
                        .padding(horizontal = 24.dp)
                        .weight(1f),
                    painter = painterResource(id = R.drawable.main_cloud),
                    contentDescription = ""
                )
                Text(
                    text = "${temperature}°",
                    style = TextStyle(color = Color.White,
                        fontSize = 80.sp,
                        fontWeight = FontWeight.ExtraBold)
                )
                Text(text = weatherCondition, color = Color.White)
                Text(
                    modifier = Modifier.padding(bottom = 12.dp),
                    text = date,
                    color = Color(0xff6CA9FF),
                    fontSize = 12.sp
                )

                Divider(
                    modifier = Modifier.padding(horizontal = 50.dp),
                    color = Color(0xff4662FF)
                )

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 10.dp),
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

@Composable
fun ConditionItem(
    painter: Painter,
    condition: String,
    label: String,
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(painter = painter, contentDescription = "")
        Text(text = condition, color = Color.White)
        Text(text = label, color = Color(0xff7391FF), fontSize = 12.sp)
    }
}

@Composable
fun WeatherItem(
    modifier: Modifier = Modifier,
    temperature: Int,
    isSelected: Boolean = false,
    icon: Painter = painterResource(id = R.drawable.main_cloud),
    time: String = "11:00",
    index: Int,
    onSelected: (Int) -> Unit,
) {
    Column(modifier = modifier
        .scale(scaleX = 1f, scaleY = if (isSelected) 1.2f else 1f)
        .clip(RoundedCornerShape(20.dp))
        .border(width = 2.dp, color = Color(0xff161D31), shape = RoundedCornerShape(20.dp))
        .shadow(10.dp, shape = RoundedCornerShape(20.dp))
        .background(brush = if (isSelected) Brush.verticalGradient(
            Pair(0f, Color(0xff2497FF)), Pair(1f, Color(0xff4361FF))) else Brush.verticalGradient(
            Pair(0f, Color(0xff01081A)), Pair(1f, Color(0xff01081A))))
        .clickable {
            onSelected(index)
        }
        .padding(5.dp), horizontalAlignment = Alignment.CenterHorizontally) {
        Text(
            text = "${temperature}°",
            color = Color.White
        )
        Image(
            modifier = Modifier.size(20.dp),
            painter = icon,
            contentDescription = null
        )
        Text(
            text = time,
            color = if (isSelected) Color.White else Color(0xff6B748B),
            fontSize = 10.sp
        )
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    ComposeWeatherAppTheme {
        MainScreen(rememberNavController())
    }
}
