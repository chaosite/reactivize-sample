package il.ac.technion.cs.reactivize.sample.splitting.multiple_factories

import il.ac.technion.cs.reactivize.sample.splitting.factory.QuoteGetterFactory
import il.ac.technion.cs.reactivize.sample.splitting.factory.ReactivizedQuoteGetter
import il.ac.technion.cs.reactivize.sample.splitting.factory.SimpleQuoteGetter

/*
 Same as the instance split example, but with a class wrapper
 Also, all instances are created from the same place

 In this example, we have two different factories
 We would like each to have a different class (based on a different QuoteGetter class)
 */
fun main() {
    val reactivizedFactory = QuoteGetterFactory("GOOG")
    val simpleFactory = QuoteGetterFactory("GOOG")

    val qgNeedsReactivize = ReactivizedQuoteGetter(reactivizedFactory)
    val qgSimple = SimpleQuoteGetter(simpleFactory)

    println("Simple: ${qgSimple.print()}")
    Thread.sleep(5000)

    println("Reactivized: ${qgNeedsReactivize.print()}")
}
