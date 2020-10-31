package il.ac.technion.cs.reactivize.sample.splitting.list

import il.ac.technion.cs.reactivize.sample.finance.QuoteGetter

/*
 Different objects have different usages of the price property
 one uses the observable, while the other accesses it directly, and another performs both
 to make things worse - we store all three in a list, and only access them from within it (by index)

 The simple one will get the original class, while the others the reactivized one
 */
fun main() {
    val qgNeedsReactivize = QuoteGetter("GOOG")
    val qgSimple = QuoteGetter("GOOG")
    val qgBoth = QuoteGetter("GOOG")

    val qgList = listOf(qgNeedsReactivize, qgSimple, qgBoth)

    var counter1 = 0
    qgList.get(0).priceObservable.subscribe {
        println("reactivized price: $it")
        counter1 += 1
    }

    println("Simple getPrice call: ${qgList.get(1).price}")

    var counter2 = 0
    qgList.get(2).priceObservable.subscribe {
        println("reactivized price (both): $it")
        counter2 += 1
    }

    println("Simple (both) getPrice call: ${qgList.get(2).price}")
    Thread.sleep(5000)

    println("consumer1 was called $counter1 times")
    println("consumer2 was called $counter2 times")
}
