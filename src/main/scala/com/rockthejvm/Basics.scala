package com.rockthejvm

object Basics extends App {
  // val = const (for js compare)
  val meaningOfLife = 42

  val aBoolean = false

  val aString = "I Love Scala"

  // kinda the same as TS `${}`
  val anInterpolatedString = s"The meaning of life is $meaningOfLife"

  // codeblock = computed val?
  val aCodeBlock = {
    // scoped
    val aLocalValue = 67

    aLocalValue + 3
  }

  // define a function
  def myFunction(x: Int, y: String): String = y + " " + x

  def myFunction2(x: Int, y: String): String = {
    y + " " + x
  }

  // recursive func
  def factorial(n: Int): Int =
    if (n <= 1) 1
    else n * factorial(n -1)
}
