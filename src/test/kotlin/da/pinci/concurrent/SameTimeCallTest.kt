package da.pinci.concurrent

import kotlinx.coroutines.runBlocking
import kotlin.system.measureTimeMillis
import kotlin.test.Test

class SameTimeCallTest {

    @Test
    fun `parallel concurrent with Latch`() {
        val time = measureTimeMillis {
            runTasksWithLatch(5) { threadId ->
                Thread.sleep(1000)
                println("thread $threadId: done (${System.currentTimeMillis()})")
            }
        }
        println("time: ${time}ms\n")
    }

    @Test
    fun `parallel concurrent with Coroutine`() {
        val time = measureTimeMillis {
            runBlocking {
                runTasksWithCoroutines(5) { threadId ->
                    kotlinx.coroutines.delay(1000)
                    println("thread-coroutines $threadId: done (${System.currentTimeMillis()})")
                }
            }
        }
        println("time: ${time}ms")
    }
}