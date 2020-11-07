package il.ac.technion.cs.reactivize.sample.splitting.instance_through_return_of_property

import il.ac.technion.cs.reactivize.sample.finance.QuoteGetter

class Returner(val qg: QuoteGetter) {
    fun giveBack(): QuoteGetter {
        return qg
    }
}

/*
 Different objects have different usages of the price property
 one uses the observable, while the other accesses it directly, and another performs both
 To make matters worse, the QuoteGetter is received through and intermediate object (Returner)

 The simple one will get the original class, while the others the reactivized one
 The Returner class should not be affected
 */
fun main() {
    val qgNeedsReactivize = Returner(QuoteGetter("GOOG"))
    val qgSimple = Returner(QuoteGetter("GOOG"))
    val qgBoth = Returner(QuoteGetter("GOOG"))

    var counter1 = 0
    qgNeedsReactivize.giveBack().priceObservable.subscribe {
        println("reactivized price: $it")
        counter1 += 1
    }

    println("Simple getPrice call: ${qgSimple.giveBack().price}")

    var counter2 = 0
    qgBoth.giveBack().priceObservable.subscribe {
        println("reactivized price (both): $it")
        counter2 += 1
    }

    println("Simple (both) getPrice call: ${qgBoth.giveBack().price}")
    Thread.sleep(5000)

    println("consumer1 was called $counter1 times")
    println("consumer2 was called $counter2 times")
}
