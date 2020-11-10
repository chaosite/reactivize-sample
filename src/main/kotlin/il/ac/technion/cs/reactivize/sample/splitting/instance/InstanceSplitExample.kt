package il.ac.technion.cs.reactivize.sample.splitting.instance

import il.ac.technion.cs.reactivize.sample.finance.QuoteGetter

/*
 Different objects have different usages of the price property
 one uses the observable, while the other accesses it directly, and another performs both

 The simple one will get the original class, while the others the reactivized one
 */
fun main() {
    val qgNeedsReactivize = QuoteGetter("GOOG")
    val qgSimple = QuoteGetter("GOOG")
    val qgBoth = QuoteGetter("GOOG")

    var counter1 = 0
    qgNeedsReactivize.priceObservable.subscribe {
        println("reactivized price: $it")
        counter1 += 1
    }

    println("Simple getPrice call: ${qgSimple.price}")

    var counter2 = 0
    qgBoth.priceObservable.subscribe {
        println("reactivized price (both): $it")
        counter2 += 1
    }

    println("Simple (both) getPrice call: ${qgBoth.price}")
    Thread.sleep(5000)

    println("consumer1 was called $counter1 times")
    println("consumer2 was called $counter2 times")
}
