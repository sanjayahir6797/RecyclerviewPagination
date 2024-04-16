package com.app.practicalrecycleviewpagination.utils

object MemoizationUtil {
    fun <T, R> ((T) -> R).memoize(): (T) -> R {
        val cache = mutableMapOf<T, R>()
        return { input: T ->
            cache.getOrPut(input) { this(input) }
        }
    }

}