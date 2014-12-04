package com.twitter.searchbird

import com.twitter.conversions.time._
import com.twitter.finagle.builder.ClientBuilder
import com.twitter.finagle.thrift.ThriftClientFramedCodec
import com.twitter.finagle.tracing.{NullTracer, ConsoleTracer, Tracer}
import com.twitter.finagle.zipkin.thrift.ZipkinTracer
import java.net.InetSocketAddress
import scala.tools.nsc.interpreter._
import scala.tools.nsc.Settings

object SearchbirdConsoleClient extends App {
  val tracerFactory: Tracer.Factory = ZipkinTracer(scribeHost="127.0.0.1", scribePort=1463, sampleRate=1.0f)
  val service = ClientBuilder()
    .hosts(new InetSocketAddress(args(0), args(1).toInt))
    .codec(ThriftClientFramedCodec())
    .hostConnectionLimit(1)
    .tcpConnectTimeout(3.seconds)
    .tracerFactory(tracerFactory)
    //for debug only
    //.tracerFactory(ConsoleTracer.factory)
    .build()

  val client = new SearchbirdService.FinagledClient(service)

  val intLoop = new ILoop()

  Console.println("'client' is bound to your thrift client.")
  intLoop.setPrompt("\nfinagle-client> ")

  intLoop.settings = {
    val s = new Settings(Console.println)
    s.embeddedDefaults[SearchbirdService.FinagledClient]
    s.Yreplsync.value = true
    s
  }

  intLoop.createInterpreter()
  intLoop.in = new JLineReader(new JLineCompletion(intLoop))

  intLoop.intp.beQuietDuring {
    intLoop.intp.interpret("""def exit = println("Type :quit to resume program execution.")""")
    intLoop.intp.bind(NamedParam("client", client))
  }

  intLoop.loop()
  intLoop.closeInterpreter()
}
