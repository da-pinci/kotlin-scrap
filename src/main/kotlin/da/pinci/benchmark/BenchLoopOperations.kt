package da.pinci.benchmark

import kotlinx.benchmark.Benchmark
import kotlinx.benchmark.TearDown
import org.openjdk.jmh.annotations.Level
import org.openjdk.jmh.annotations.Scope
import org.openjdk.jmh.annotations.Setup
import org.openjdk.jmh.annotations.State

@Suppress("unused")
@State(Scope.Benchmark)
class BenchLoopOperations {
    @Setup(Level.Trial)
    fun setUp() {
        // noop
    }

    @TearDown(Level.Trial)
    fun tearDown() {
        // noop
    }

    @Benchmark
    @Suppress("unused")
    fun measure() {
        (1..1000)
            .filter { it % 3 == 0 }
            .sum()
    }
}