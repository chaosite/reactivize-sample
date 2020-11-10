package il.ac.technion.cs.reactivize.sample.splitting.factory

import il.ac.technion.cs.reactivize.sample.finance.QuoteGetter

class QuoteGetterFactory(val symbol: String) {
    fun create(): QuoteGetter {
        return QuoteGetter(symbol)
    }
}

class ReactivizedQuoteGetter(factory: QuoteGetterFactory) {
    var counter = 0

    init {
        val qg = factory.create()
        qg.priceObservable.subscribe {
            println("reactivized price: $it")
            counter += 1
        }
    }

    fun print(): String {
        return "counter was called $counter times"
    }
}

class SimpleQuoteGetter(factory: QuoteGetterFactory) {
    val qg = factory.create()

    fun print(): String {
        return "current value is ${qg.price}"
    }
}

/*
 Same as the instance split example, but with a class wrapper
 Also, all instances are created from the same place

 In this example, there's just one factory - so it'll use the reactivized class
 */
fun main() {
    val factory = QuoteGetterFactory("GOOG")

    val qgNeedsReactivize = ReactivizedQuoteGetter(factory)
    val qgSimple = SimpleQuoteGetter(factory)

    println("Simple: ${qgSimple.print()}")
    Thread.sleep(5000)

    println("Reactivized: ${qgNeedsReactivize.print()}")
}
