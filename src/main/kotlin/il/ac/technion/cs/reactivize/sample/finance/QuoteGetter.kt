package il.ac.technion.cs.reactivize.sample.finance

import il.ac.technion.cs.reactivize.api.ReactivizeValue
import io.reactivex.rxjava3.subjects.BehaviorSubject
import yahoofinance.Stock
import yahoofinance.YahooFinance
import kotlin.concurrent.thread

class QuoteGetter(symbol: String) {
    val stock: Stock = YahooFinance.get(symbol)
    val priceObservable: BehaviorSubject<Double> = BehaviorSubject.createDefault(13.37)
    init {
        thread(isDaemon = true) {
            while (true) {
                println("Thread heart-beat")
                stock.getQuote(true)
                Thread.sleep(1000)
            }
        }
    }

    val price: Double
        @ReactivizeValue("priceObservable") get() = stock.quote.price.toDouble()
}

fun main() {
    val qg = QuoteGetter("GOOG")
    var counter = 0
    qg.priceObservable.subscribe {
        println("it: $it")
        counter += 1
    }
    println("First getPrice call: ${qg.price}")
    Thread.sleep(5000)
    println("consumer was called $counter times")
}