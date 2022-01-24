package com.deviot.domain.valueobjects.pageinfo

class PageSize {
    val value: Int

    constructor(value:Int){
        this.value = value.coerceIn(0,1000)
    }
}