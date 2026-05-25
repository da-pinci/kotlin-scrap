package da.pinci.share.collection

fun <T> Collection<T>.isNotEmpty(): Boolean = this.isEmpty().not()

fun <T, R> Collection<T>.fold(
    onHasAny: (Collection<T>) -> R,
    onEmpty: () -> R
): R {
    return if (this.isEmpty()) {
        onEmpty()
    } else {
        onHasAny(this)
    }
}

fun <T> Collection<T>.onEmpty(action: () -> Unit): Collection<T> {
    if (this.isEmpty()) action()
    return this
}

fun <T> Collection<T>.onHasAny(action: (Collection<T>) -> Unit): Collection<T> {
    if (this.isNotEmpty()) action(this)
    return this
}
