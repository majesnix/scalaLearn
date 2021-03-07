package com.rockthejvm

object ObjectOrientation extends App {

  // class and instance
  class Animal {
    val age: Int = 0
    def eat(): Unit = println("I'm eating")
  }
  val anAnimal = new Animal

  // inheritance
  class Dog(val name: String = "Buster") extends Animal
  val aDog = new Dog("Lassie")

  // constructor arguments are NOT fields: need to put a val before the constructor argument
  aDog.name

  // subtype polymorphism
  val aDeclaredAnimal: Animal = new Dog("Hachi")
  aDeclaredAnimal.eat() // the most derived method will be called at runtime

  // abstract class
  abstract class WalkingAnimal {
    protected  val hasLegs = true // by default public, but can be restricted with protected/private
    def walk(): Unit
  }

  // "interface" = ultimate abstract type
  trait Carnivore {
    def eat(animal: Animal): Unit
  }

  // single-class inheritance, multi-trait "mixing"
  class Crocodile extends Animal with Carnivore {
    override def eat(animal: Animal): Unit = println(s"I am eating you, $animal!")
  }

  val aCroc = new Crocodile
  aCroc.eat(aDog)
  aCroc eat aDog // infix notation = object method argument, only available for methods with ONE argument

  // anonymous classes
  val dinosaur = new Carnivore {
    override def eat(animal: Animal): Unit = println("I am a dinosaur so i can eat pretty much anything!")
  }

  /*
    What you tell the compiler:

    class Carnivore_Anonymous_299592 extends Carnivore {
      override def eat(animal: Animal): Unit = println("I am a dinosaur so i can eat pretty much anything!")
    }

    val dinosaur = new Carnivore_Anonymous_299592
   */

  // singleton object
  object MySingleton { // the only instance of the MySingleton type
    val mySpecialValue = 1245
    def mySpecialMethod(): Int = 1337
    def apply(x: Int): Int = x + 1
  }

  MySingleton.mySpecialMethod()
  MySingleton.apply(65)
  MySingleton(65) // equivalent to .apply(65)

  object Animal { // companions - companion object
    // companions can access each others private fields/methods
    // singleton Animal and instances of Animal are different things
    val canLiveIndefinitely = false
  }

  val animalsCanLiveForever = Animal.canLiveIndefinitely // "static" fields/methods

  /*
  case classes = lightweight data structures with some boilerplate
  - sensible equals and has code
  - serialization
  - companion with apply
   */
  case class Person(name: String, age: Int)
  // may be constructed without new
  val bob = Person("Bob", 54) // Person.apply("Bob", 54)

  // exceptions
  try {
    // code that can throw
    val x: String = null
    x.length
  } catch {
    case e: Exception => "some faulty error message"
  } finally {
    // execute some code no matter what
  }

  // generics
  abstract class MyList[T] {
    def head: T
    def tail: MyList[T]
  }

  // using a generic with a concrete type
  val aList: List[Int] = List(1,2,3) // List.apply(1,2,3)
  val first = aList.head
  val rest = aList.tail
  val aStringList = List("hello", "Scala")
  val firstString = aStringList.head // string

  // Point #1: In Scala we usually operate with IMMUTABLE values/objects
  // Any modification to an object must return ANOTHER object
  /*
    Benefits
    1) works miracles in multithreaded/distributed env
    2) helps making sense of the code ("reasoning about")
   */
  val reversedList = aList.reverse // return a NEW list

  // Point #2: Scala is closest to the object oriented Ideal
}
