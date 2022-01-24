package com.deviot.adapters.infra

import com.deviot.application.ports.infra.Log
import jakarta.inject.Singleton
import org.apache.logging.log4j.LogManager
import org.apache.logging.log4j.Logger

@Singleton
class LogImpl: Log {

    companion object{
        private val logger: Logger = LogManager.getLogger()
    }

    private fun formatMessage(message: String): String {
        return message.toString()
    }

    override fun debug(message: String) {
        logger.debug(formatMessage(message))
    }

    override fun info(message: String) {
        logger.info(formatMessage(message))
    }

    override fun warning(message: String) {
        logger.warn(formatMessage(message))
    }

    override fun error(message: String) {
        logger.error(formatMessage(message))
    }

    override fun error(exception: Exception) {
        error(exception.message.toString())
    }

    override fun critical(message: String) {
        logger.error(formatMessage(message))
    }

    override fun critical(exception: Exception) {
        critical(exception.message.toString())
    }
}