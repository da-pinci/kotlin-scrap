package da.pinci.concurrent

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import java.util.concurrent.CountDownLatch
import java.util.concurrent.Executors

fun runTasksWithLatch(threadCount: Int, task: (threadId: Int) -> Unit) {
    val startLatch = CountDownLatch(1)
    val doneLatch = CountDownLatch(threadCount)
    val executor = Executors.newFixedThreadPool(threadCount)

    repeat(threadCount) { threadId ->
        executor.submit {
            try {
                println("スレッド $threadId: 待機中...")
                startLatch.await()
                println("スレッド $threadId: 開始!")
                task(threadId)
            } finally {
                doneLatch.countDown()
            }
        }
    }

    Thread.sleep(100)  // Wait all threads ready

    println("=== Start at the same time ===")
    startLatch.countDown()  // Start all threads at the same time

    doneLatch.await()
    executor.shutdown()
}

suspend fun runTasksWithCoroutines(
    threadCount: Int,
    task: suspend (threadId: Int) -> Unit
) {
    coroutineScope {
        val jobs = List(threadCount) { threadId ->
            async(Dispatchers.Default) {
                task(threadId)
            }
        }
        jobs.forEach { it.await() }
    }
}