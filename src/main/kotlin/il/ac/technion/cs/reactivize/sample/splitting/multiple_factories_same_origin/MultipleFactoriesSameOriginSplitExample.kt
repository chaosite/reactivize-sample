package il.ac.technion.cs.reactivize.sample.splitting.multiple_factories_same_origin

import il.ac.technion.cs.reactivize.sample.splitting.factory.QuoteGetterFactory
import il.ac.technion.cs.reactivize.sample.splitting.factory.ReactivizedQuoteGetter
import il.ac.technion.cs.reactivize.sample.splitting.factory.SimpleQuoteGetter

/*
 Same as the instance split example, but with a class wrapper
 Also, all instances are created from the same place
 To make matter worse - both factories are created from the same method

 In this example, we have two different factories
 We would like each to have a different class (based on a different QuoteGetter class)
 This would also entail splitting the method into two separate ones
 */
fun main() {
    val reactivizedFactory = makeLifeHard("GOOG")
    val simpleFactory = makeLifeHard("GOOG")

    val qgNeedsReactivize = ReactivizedQuoteGetter(reactivizedFactory)
    val qgSimple = SimpleQuoteGetter(simpleFactory)

    println("Simple: ${qgSimple.print()}")
    Thread.sleep(5000)

    println("Reactivized: ${qgNeedsReactivize.print()}")
}

fun makeLifeHard(symbol: String): QuoteGetterFactory {
    return QuoteGetterFactory(symbol)
}
