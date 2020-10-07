package il.ac.technion.cs.reactivize.sample.finance

import il.ac.technion.cs.reactivize.api.ReactivizeValue
import io.reactivex.rxjava3.subjects.BehaviorSubject
import yahoofinance.Stock
import yahoofinance.YahooFinance
import kotlin.concurrent.thread

class QuoteGetter(symbol: String) {
    val stock: Stock = YahooFinance.get(symbol)
    val priceObservable: BehaviorSubject<Double> = BehaviorSubject.createDefault(0.0)
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
    qg.priceObservable.subscribe {
        println("gq.price: ${qg.price} it: $it") // println(it) is more correct (because the values can change)
    }
    Thread.sleep(5000)
}