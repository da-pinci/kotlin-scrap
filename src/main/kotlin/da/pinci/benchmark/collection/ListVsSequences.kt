package da.pinci.benchmark.collection

import kotlinx.benchmark.Benchmark
import kotlinx.benchmark.Param
import kotlinx.benchmark.Setup
import org.openjdk.jmh.annotations.Scope
import org.openjdk.jmh.annotations.State

@Suppress("unused")
@State(Scope.Benchmark)
class ListVsSequences {

    @Param("1000", "10000", "100000")
    var size: Int = 0

    private lateinit var source: List<Int>

    @Setup
    fun setup() {
        source = (1..size).toList()
    }

    @Benchmark
    @Suppress("unused")
    fun measureList() {
        @Suppress("unused_variable")
        val result = source
            .filter { it % 3 == 0 }.sumOf { it.toLong() * it }
    }

    @Benchmark
    @Suppress("unused")
    fun measureSequence() {
        @Suppress("unused_variable")
        val result = source
            .asSequence().filter { it % 3 == 0 }.sumOf { it.toLong() * it }
    }

    @Benchmark
    @Suppress("unused")
    fun measureSequenceWithYield() {
        @Suppress("unused_variable")
        val result = sequence {
            // https://kotlinlang.org/api/core/kotlin-stdlib/kotlin.sequences/-sequence-scope/yield.html
            yield(source.asSequence().filter { it % 3 == 0 }.sumOf { it.toLong() })
        }.first()
    }
}