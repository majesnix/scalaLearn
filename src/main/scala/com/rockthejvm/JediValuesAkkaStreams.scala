package com.rockthejvm

import akka.{Done, NotUsed}
import akka.actor.ActorSystem
import akka.stream.ActorMaterializer
import akka.stream.scaladsl.{Flow, Keep, RunnableGraph, Sink, Source}

import scala.concurrent.Future

object JediValuesAkkaStreams {

  // Reactive Streams
  // sources, sinks and flows

  implicit val system: ActorSystem = ActorSystem()
  implicit val materializer: ActorMaterializer = ActorMaterializer() // Akka Streams
  import system.dispatcher

  // streaming components
  val source: Source[Int, NotUsed] = Source(1 to 1000)
  val flow: Flow[Int, Int, NotUsed] = Flow[Int].map(_ * 2)
  val sink: Sink[Int, Future[Done]] = Sink.foreach[Int](println)
  val summingSink: Sink[Int, Future[Int]] = Sink.fold[Int, Int](0)((currentSum, incomingElement) => currentSum + incomingElement)
  val graph: RunnableGraph[NotUsed] = source.via(flow).to(sink)

  val anotherGraph: RunnableGraph[Future[Done]] = source.via(flow).toMat(sink)(Keep.right)
  // Jedi values

  def main(args: Array[String]): Unit = {
    /* val jediValue = graph.run() // make the graph come alive
    val anotherJediValue: Future[Done] = anotherGraph.run()
    // value?!
    anotherJediValue.onComplete(_ => println("Stream is done")) */

    val sumFuture = source.toMat(summingSink)(Keep.right).run()
    sumFuture.foreach(println)
  }

  // once you start, no turning back
  // Jedi values = MATERIALIZED VALUES
  // Jedi values may or may not be connected to the actual elements that go through the graph
  // Jedi values can have ANY type
}
