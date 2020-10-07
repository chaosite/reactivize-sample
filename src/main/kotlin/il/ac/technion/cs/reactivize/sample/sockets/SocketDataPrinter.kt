package il.ac.technion.cs.reactivize.sample.sockets

import il.ac.technion.cs.reactivize.api.Reactivize
import java.net.Socket

class SocketDataPrinter {
    val data = Data()

    // @Reactivize
    fun printData() {
        println(data.data)
    }
}

fun main(args: Array<String>) {
    val test = SocketDataPrinter()
    test.printData()

    Socket("127.0.0.1", 6969).outputStream.write(byteArrayOf(2))
    Socket("127.0.0.1", 6969).outputStream.write(byteArrayOf(69))
    Thread.sleep(5000)
}