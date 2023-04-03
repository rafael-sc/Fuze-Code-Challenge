package com.orafaelsc.fuzecodechallenge.commom.exceptions

sealed class ApiException : Throwable() {
    class UnableToGetMatchesException : ApiException()
    class UnableToGetPlayersException : ApiException()
}
