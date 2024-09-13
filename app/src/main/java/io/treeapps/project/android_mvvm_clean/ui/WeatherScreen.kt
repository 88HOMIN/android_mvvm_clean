package io.treeapps.project.android_mvvm_clean.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import io.treeapps.project.android_mvvm_clean.viewmodel.WeatherViewModel
import io.treeapps.project.domain.entity.Weather

@Composable
fun WeatherScreen(viewModel: WeatherViewModel = hiltViewModel()) {
    val weather by viewModel.weather.collectAsState()
    val cityName = "Seoul"

    LaunchedEffect(Unit) {
        viewModel.loadWeather(cityName)
    }

    weather?.let {
        WeatherItem(it)
    } ?: Text(text = "Loading...")

}


@Composable
fun WeatherItem(weather: Weather) {
    Column(modifier = Modifier.padding(16.dp)) {
        Text(text = "City: ${weather.cityName}")
        Text(text = "Temperature: ${weather.temperature}°C")
        Text(text = "Description: ${weather.weatherDescription}")
        Text(text = "Min Temp: ${weather.minTemperature}°C")
        Text(text = "Max Temp: ${weather.maxTemperature}°C")
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewWeatherItem() {
    WeatherItem(
        Weather(
            cityName = "Seoul",
            temperature = 25.0,
            weatherDescription = "clear sky",
            minTemperature = 22.0,
            maxTemperature = 28.0
        )
    )
}

