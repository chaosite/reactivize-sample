package il.ac.technion.cs.reactivize.sample.splitting.nested

import il.ac.technion.cs.reactivize.sample.finance.QuoteGetter

class ReactivizedQuoteGetter(symbol: String) {
    var counter = 0

    init {
        val qg = QuoteGetter(symbol)
        qg.priceObservable.subscribe {
            println("reactivized price: $it")
            counter += 1
        }
    }

    fun print(): String {
        return "counter was called $counter times"
    }
}

class SimpleQuoteGetter(symbol: String) {
    val qg = QuoteGetter(symbol)

    fun print(): String {
        return "current value is $qg.price"
    }
}

class BothQuoteGetter(symbol: String) {
    val qg = QuoteGetter(symbol)
    var counter = 0

    init {
        qg.priceObservable.subscribe {
            println("reactivized price: $it")
            counter += 1
        }
    }


    fun print(): String {
        return "current value is $qg.price; counter was called $counter times"
    }
}

/*
 Same as the instance split example, but with a class wrapper
 */
fun main() {
    val qgNeedsReactivize = ReactivizedQuoteGetter("GOOG")
    val qgSimple = SimpleQuoteGetter("GOOG")
    val qgBoth = BothQuoteGetter("GOOG")

    println("Simple: ${qgSimple.print()}")
    println("Both: ${qgBoth.print()}")
    Thread.sleep(5000)

    println("Reactivized: ${qgNeedsReactivize.print()}")
    println("Both: ${qgBoth.print()}")
}
