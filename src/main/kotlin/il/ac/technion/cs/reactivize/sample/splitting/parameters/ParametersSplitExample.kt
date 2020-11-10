package il.ac.technion.cs.reactivize.sample.splitting.parameters

import il.ac.technion.cs.reactivize.sample.finance.QuoteGetter

/*
 Different Parameters affect the way each object will use the underlying value
 */
fun main() {
    val qgNeedsReactivize = QuoteGetter("GOOG")
    val qgSimple = QuoteGetter("GOOG")
    val qgBoth = QuoteGetter("GOOG")

    makeLifeHard(qgNeedsReactivize, true, "react")
    makeLifeHard(qgSimple, false, "")

    makeLifeHard(qgBoth, true, "both")
    makeLifeHard(qgBoth, false, "")

    Thread.sleep(5000)
}

fun makeLifeHard(qg: QuoteGetter, useObservable: Boolean, name: String) {
    if (useObservable) {
        registerToQuoteGetterObservable(qg, name)
    } else {
        simplePriceAccess(qg)
    }
}

fun registerToQuoteGetterObservable(qg: QuoteGetter, name: String) {
    qg.priceObservable.subscribe {
        println("reactivized ($name) price: $it")
    }
}

fun simplePriceAccess(qg: QuoteGetter) {
    println("Simple getPrice call: ${qg.price}")
}
