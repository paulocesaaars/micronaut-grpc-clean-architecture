package com.deviot.functional.configuration

import com.deviot.UserServiceGrpcKt
import io.grpc.ManagedChannel
import io.micronaut.context.annotation.Bean
import io.micronaut.context.annotation.Factory
import io.micronaut.grpc.annotation.GrpcChannel

@Factory
internal class GrpcConfig {
    @Bean
    fun userServiceCoroutineStub(
        @GrpcChannel("administrationservice")
        channel: ManagedChannel
    ): UserServiceGrpcKt.UserServiceCoroutineStub
    {
        return UserServiceGrpcKt.UserServiceCoroutineStub(channel)
    }
}