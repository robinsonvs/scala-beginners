package exercises

abstract class MyListGeneric[+A] {
  /*
    head = first element of the list
    tail = remainder of the list
    isEmpty = is this list empty
    add(int) => new list with this element added
    toString => a string representation of the list
   */

  def head: A
  def tail: MyListGeneric[A]
  def isEmpty: Boolean
  def add[B >: A](element: B): MyListGeneric[B]
  def printElements: String
  // polymorphic call
  override def toString: String = "[" + printElements + "]"
}

object EmptyGeneric extends MyListGeneric[Nothing] {
  def head: Nothing = throw new NoSuchElementException
  def tail: MyListGeneric[Nothing] = throw new NoSuchElementException
  def isEmpty: Boolean = true
  def add[B >: Nothing](element: B): MyListGeneric[B] = new ConsGeneric(element, EmptyGeneric)
  def printElements: String = ""
}

class ConsGeneric[+A](h: A, t: MyListGeneric[A]) extends MyListGeneric[A] {
  def head: A = h
  def tail: MyListGeneric[A] = t
  def isEmpty: Boolean = false
  def add[B >: A](element: B): MyListGeneric[B] = new ConsGeneric(element, this)
  def printElements: String =
    if (t.isEmpty) "" + h
    else h + " " + t.printElements
}

object ListGenericTest extends App {
  val listOfIntegers: MyListGeneric[Int] = new ConsGeneric(1, new ConsGeneric(2, new ConsGeneric(3, EmptyGeneric)))
  val listOfStrings: MyListGeneric[String] = new ConsGeneric("Hello", new ConsGeneric("Scala", EmptyGeneric))

  println(listOfIntegers.toString)
  println(listOfStrings.toString)
}