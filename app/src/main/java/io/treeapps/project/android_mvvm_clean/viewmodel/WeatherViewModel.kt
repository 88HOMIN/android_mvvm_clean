package io.treeapps.project.android_mvvm_clean.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import io.treeapps.project.domain.entity.Weather
import io.treeapps.project.domain.usecase.GetWeatherByCityUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class WeatherViewModel @Inject constructor(
    private val getWeatherByCityUseCase: GetWeatherByCityUseCase
) : ViewModel() {

    private val _weather = MutableStateFlow<Weather?>(null)
    val weather: StateFlow<Weather?> get() = _weather

    fun loadWeather(cityName: String) {
        viewModelScope.launch {
            val weatherData = getWeatherByCityUseCase(cityName)
            _weather.value = weatherData
        }
    }

}