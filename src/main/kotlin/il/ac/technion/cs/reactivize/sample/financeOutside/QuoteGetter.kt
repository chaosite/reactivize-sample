package il.ac.technion.cs.reactivize.sample.financeOutside

import il.ac.technion.cs.reactivize.api.Reactivize
import yahoofinance.YahooFinance
import kotlin.concurrent.thread

class QuoteGetter(symbol: String) {
    private val stock = YahooFinance.get(symbol)
    private var price = stock.quote.price

    // @Reactivize
    fun printQuote() {
        println(this.price)
    }

    fun startRefreshing() {
        thread(isDaemon = true) {
            while (true) {
                price = stock.getQuote(true).price
                Thread.sleep(1000)
            }
        }
    }
}

fun main() {
    println("Started main")
    val qg = QuoteGetter("GOOG")
    println("Initialized")
    qg.printQuote()
    println("First quote")
    qg.startRefreshing()
    println("Started refreshing")
    Thread.sleep(5000)
    println("End")
}