package com.deviot.adapters.configuration.interceptors

import com.deviot.application.base.ValidationExceptionBase
import com.deviot.application.exceptions.NotFoundRegisterException
import io.grpc.*
import jakarta.inject.Singleton
import javax.validation.ConstraintViolationException

@Singleton
class RpcCallErrorInterceptor: ServerInterceptor {

    internal class CheckErrorForwardingServerCall<ReqT, RespT>(
        serverCall: ServerCall<ReqT, RespT>,
    ) : ForwardingServerCall.SimpleForwardingServerCall<ReqT, RespT>(serverCall) {

        override fun close(status: Status, trailers: Metadata) {
            if (status.isOk)
                return super.close(status, trailers)

            super.close(translateStatus(status), trailers)
        }

        private fun translateStatus(status: Status): Status {
            val cause = status.cause
            val translatedStatus = when (cause) {
                is NotFoundRegisterException -> Status.NOT_FOUND
                is ValidationExceptionBase -> Status.INVALID_ARGUMENT
                else -> status
            }

            if (cause is ConstraintViolationException) {
                var errors = cause.constraintViolations.map() {
                    it.message
                }

                return translatedStatus.withDescription(errors.toString())
                    .withCause(cause)
            }

            return translatedStatus.withDescription(cause?.message)
                .withCause(cause)
        }
    }

    override fun <ReqT : Any?, RespT : Any?> interceptCall(
        call: ServerCall<ReqT, RespT>,
        headers: Metadata,
        next: ServerCallHandler<ReqT, RespT>
    ): ServerCall.Listener<ReqT> {
        return next.startCall(CheckErrorForwardingServerCall(call), headers)
    }
}