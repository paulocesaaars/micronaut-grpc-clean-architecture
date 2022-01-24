package com.deviot.application.ports.infra

interface Log {
    fun debug(message: String)
    fun info(message: String)
    fun warning(message: String)
    fun error(message: String)
    fun error(message: Exception)
    fun critical(message: String)
    fun critical(message: Exception)
}