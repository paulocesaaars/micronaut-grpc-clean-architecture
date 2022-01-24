package com.deviot.application.base

abstract class TranslatorBase<In, Out> {

    protected abstract fun fromIn(value: In): Out

    protected abstract fun toOut(value: Out): In

    protected fun fromInList(values: List<In>): List<Out> {
        var result = mutableListOf<Out>()
        values.forEach() {
            result.add(fromIn(it))
        }

        return result
    }

    protected fun toOutList(values: List<Out>): List<In> {
        var result = mutableListOf<In>()
        values.forEach() {
            result.add(toOut(it))
        }

        return result
    }
}