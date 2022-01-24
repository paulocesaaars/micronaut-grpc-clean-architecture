package com.deviot.adapters.configuration.interceptors

import io.grpc.Metadata
import io.grpc.ServerCall
import io.grpc.ServerCallHandler
import io.grpc.ServerInterceptor
import jakarta.inject.Singleton
import java.util.*

@Singleton
open class RpcCallInterceptor : ServerInterceptor {

    companion object{
        private const val CORRELATION_ID = "x-deviot-correlationid"
    }

    override fun <ReqT : Any?, RespT : Any?> interceptCall(
        call: ServerCall<ReqT, RespT>,
        headers: Metadata,
        next: ServerCallHandler<ReqT, RespT>
    ): ServerCall.Listener<ReqT> {

        var correlationIdKey = Metadata.Key.of(CORRELATION_ID, Metadata.ASCII_STRING_MARSHALLER)
        var correlationId = headers.get(correlationIdKey)
        if(correlationId.isNullOrEmpty()){
            correlationId = UUID.randomUUID().toString()
        }

        headers.put(correlationIdKey, correlationId)

        return next.startCall(call, headers)
    }
}