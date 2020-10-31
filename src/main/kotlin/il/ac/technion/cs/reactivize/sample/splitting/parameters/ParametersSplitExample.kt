package il.ac.technion.cs.reactivize.sample.splitting.parameters

import il.ac.technion.cs.reactivize.sample.finance.QuoteGetter

/*
 Different Parameters affect the way each object will use the underlying value
 */
fun main() {
    val qgNeedsReactivize = QuoteGetter("GOOG")
    val qgSimple = QuoteGetter("GOOG")
    val qgBoth = QuoteGetter("GOOG")

    makeLifeHard(qgNeedsReactivize, true)
    makeLifeHard(qgSimple, false)

    makeLifeHard(qgBoth, true)
    makeLifeHard(qgBoth, false)

    Thread.sleep(5000)
}

fun makeLifeHard(qg: QuoteGetter, useObservable: Boolean) {
    if (useObservable) {
        registerToQuoteGetterObservable(qg)
    } else {
        simplePriceAccess(qg)
    }
}

fun registerToQuoteGetterObservable(qg: QuoteGetter) {
    qg.priceObservable.subscribe {
        println("reactivized price: $it")
    }
}

fun simplePriceAccess(qg: QuoteGetter) {
    println("Simple getPrice call: ${qg.price}")
}
