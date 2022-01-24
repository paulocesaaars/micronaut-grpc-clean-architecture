package com.deviot.domain.valueobjects.pageinfo

class PageNumber {

    val value: Int

    constructor(value:Int){
        this.value = value.coerceAtLeast(0)
    }
}