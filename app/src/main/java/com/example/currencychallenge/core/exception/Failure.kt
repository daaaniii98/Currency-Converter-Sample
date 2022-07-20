package com.example.currencychallenge.core.exception

/**
 * Base Class for handling errors/failures/exceptions.
 */
object NetworkException: Exception("Network not available")
object ServerException: Exception("Server error")
