package il.ac.technion.cs.reactivize.sample.splitting.simple

import il.ac.technion.cs.reactivize.sample.finance.QuoteGetter

/*
 Price observable is not used - hence no need to modify the class
 */
fun main() {
    val qgSimple = QuoteGetter("GOOG")
    println("Simple getPrice call: ${qgSimple.price}")
    Thread.sleep(5000)
}
