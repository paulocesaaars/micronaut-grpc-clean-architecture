package com.deviot.domain

import com.deviot.domain.valueobjects.pageinfo.PageNumber
import com.deviot.domain.valueobjects.pageinfo.PageSize

data class PageInfo(val pageNumber: PageNumber, val pageSize: PageSize) {

    override fun toString(): String {
        return StringBuilder()
            .appendLine("${pageNumber::class.java.name}: ${pageNumber.value}")
            .appendLine("${pageSize::class.java.name}: ${pageSize.value}")
            .toString()
    }
}