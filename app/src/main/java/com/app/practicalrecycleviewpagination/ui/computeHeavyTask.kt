/*
package com.app.practicalrecycleviewpagination.ui

import com.app.practicalrecycleviewpagination.model.Post
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import kotlin.system.measureTimeMillis

private suspend fun computeHeavyTask(item: Post): Result {
    val elapsedTime = measureTimeMillis {
        // Record start time
        val startTime = System.currentTimeMillis()

        // Perform heavy computation
        val result = withContext(Dispatchers.Default) {
            // Heavy computation task
            heavyComputation(item)
        }

        // Record end time
        val endTime = System.currentTimeMillis()

        // Calculate elapsed time
        val duration = endTime - startTime

        // Log elapsed time
        println("Heavy computation took $duration milliseconds")

        result
    }

    // You can return the result or do other processing here if needed
    // For example: return result
}*/
