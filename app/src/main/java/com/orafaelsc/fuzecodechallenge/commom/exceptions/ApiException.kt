package com.orafaelsc.fuzecodechallenge.commom.exceptions

sealed class ApiException : Throwable() {
    class UnableToGetWeatherForecastException : ApiException()
    class UnableToGetConsolidateWeatherException : ApiException()
}
