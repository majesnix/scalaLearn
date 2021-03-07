package com.rockthejvm

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future
import scala.util.{Failure, Success, Try}

object Advanced extends App {
  /**
   * lazy evaluation
   */
  lazy val aLazyValue = 2
  lazy val lazyValueWithSideEffect = {
    println("I am so very lazy!")
    43
  }

  val eagerValue = lazyValueWithSideEffect + 1
  // useful in infinite collections

  /**
   * "pseudo-collections": Option, Try
   */
  def methodWhichCanReturnNull(): String = "hello, scala"
  val anOption = Option(methodWhichCanReturnNull()) // Some("hello, scala")
  // option = "collection" which contains at most one element: Some(value) or None

  val stringProcessing = anOption match {
    case Some(string) => s"I have obtained a valid string: $string"
    case None => "I obtained nothing"
  }
  // map, flatMap, filter

  def methodWhichCanThrowException(): String = throw new RuntimeException
  val aTry = Try(methodWhichCanThrowException())
  // a try = "collection" with either a value if the code went well, or an exception if the code threw

  val anotherStringProcessing = aTry match {
    case Success(value) => s"I have obtained a valid string: $value"
    case Failure(exception) => s"I have obtained an exception: $exception"
  }
  // map, flatMap, filter

  /**
   * Evaluate something on another thread
   * (async programming)
   */
  val aFuture = Future({
    println("Loading...")
    Thread.sleep(1000)
    println("I have computed a value.")
    67
  })

  // future is a "collection" which contains a value when it's evaluated
  // future is composable with map, flatMap and
  // monads

  /**
   * Implicits basics
   */
  // #1: implicit arguments
  def aMethodWithImplicitArgs(implicit arg: Int) = arg + 1
  implicit  val myImplicitInt: Int = 46
  println(aMethodWithImplicitArgs) // compiler does: aMethodWithImplicitArgs(myImplicitInt)

  // #2: implicit conversions
  implicit class MyRichInteger(n: Int) {
    def isEven: Boolean = n % 2 == 0
  }

  println(23.isEven) // compiler does: new MyRichInteger(23).isEven()
  // use this carefully
}
