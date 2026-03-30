plugins {
    kotlin("jvm") version "2.2.20"
    // https://github.com/kotlin/kotlinx-benchmark
    id("org.jetbrains.kotlinx.benchmark") version "0.4.16"
    kotlin("plugin.allopen") version "2.2.20"
}

group = "da.pinci"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.10.2")
    implementation("org.jetbrains.kotlinx:kotlinx-benchmark-runtime:0.4.16")
    testImplementation(kotlin("test"))
}

tasks.test {
    useJUnitPlatform()
}
kotlin {
    jvmToolchain(23)
}

allOpen {
    // open all classes annotated with @State, which is required by JMH
    annotation("org.openjdk.jmh.annotations.State")
}
benchmark {
    targets {
        register("main")
    }
    configurations {
        named("main") {
            warmups = 3
            iterations = 5
            iterationTime = 1
            iterationTimeUnit = "s"
        }
    }
}