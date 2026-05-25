package da.pinci.share.collection

import org.junit.jupiter.api.Assertions.assertFalse
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Nested
import java.util.Collections.emptyList
import kotlin.test.Test

internal class CollectionExTest {
    @Nested
    inner class IsNotEmpty {
        @Test
        fun `isNotEmpty should return true if collection is not empty`() {
            val collection = listOf(1, 2, 3)
            assertTrue(collection.isNotEmpty())
        }

        @Test
        fun `isNotEmpty should return false if collection is empty`() {
            val collection = emptyList<Int>()
            assertFalse(collection.isNotEmpty())
        }
    }

    @Nested
    inner class OnEmpty {
        @Test
        fun `onEmpty should execute action if collection is empty`() {
            var actionExecuted = false
            val collection = emptyList<Int>()
            collection.onEmpty { actionExecuted = true }
            assertTrue(actionExecuted)
        }

        @Test
        fun `onEmpty should not execute action if collection is not empty`() {
            var actionExecuted = false
            val collection = listOf(1, 2, 3)
            collection.onEmpty { actionExecuted = true }
            assertFalse(actionExecuted)
        }
    }

    @Nested
    inner class OnHasAny {
        @Test
        fun `onHasAny should execute action if collection is not empty`() {
            var actionExecuted = false
            val collection = listOf(1, 2, 3)
            collection.onHasAny { actionExecuted = true }
            assertTrue(actionExecuted)
        }

        @Test
        fun `onHasAny should not execute action if collection is empty`() {
            var actionExecuted = false
            val collection = emptyList<Int>()
            collection.onHasAny { actionExecuted = true }
            assertFalse(actionExecuted)
        }
    }
}
