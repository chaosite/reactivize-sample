package il.ac.technion.cs.reactivize.sample.sockets

import java.net.ServerSocket
import kotlin.concurrent.thread

data class Wrapper(val datum: Int)

open class Data() {
    private val socket = ServerSocket(6969)
    private val t = thread(isDaemon = true) {
        while (true) {
            val s = socket.accept()
            val c = s.inputStream.readNBytes(1)[0]
            s.close()
            data = Wrapper(c.toInt())
        }
    }

    open var data = Wrapper(0)
}