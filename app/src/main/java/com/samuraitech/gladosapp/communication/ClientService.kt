package com.samuraitech.gladosapp.communication

import org.zeromq.SocketType
import org.zeromq.ZMQ

object ClientService {
    fun sendMessageToServer(message: String) {
        val context = ZMQ.context(1)
        try {

            //Set socket configuration
            val socket = context.socket(SocketType.REQ)
            socket.connect("tcp://192.168.1.88:5897")

            val request = "$message "

            //Convert request to ByteArray
            val byteRequest = request.toByteArray()
            byteRequest[byteRequest.size - 1] = 0

            //Send a request to server
            socket.send(byteRequest, 0)

            //Receive response from server
            val byteReply = socket.recv(0)
            val plainReply = String(byteReply, 0, byteReply.size - 1)

            println(plainReply)
        } catch (exception: Exception) {
            exception.printStackTrace()
        }
    }
}
