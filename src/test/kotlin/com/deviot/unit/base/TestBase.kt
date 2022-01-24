package com.deviot.unit.base

import com.deviot.application.ports.infra.Log
import org.mockito.Mockito

abstract class TestBase {

    protected val log = Mockito.mock(Log::class.java)

    protected fun <T> any(type: Class<T>): T = Mockito.any<T>(type)
}