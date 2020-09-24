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

  def map[B](transformer: MyTransformer[A, B]): MyListGeneric[B]
  def flatMap[B](transformer: MyTransformer[A, MyListGeneric[B]]): MyListGeneric[B]
  def filter(predicate: Mypredicate[A]): MyListGeneric[A]

  //concatenation
  def ++[B >: A](list: MyListGeneric[B]): MyListGeneric[B]
}

case object EmptyGeneric extends MyListGeneric[Nothing] {
  def head: Nothing = throw new NoSuchElementException
  def tail: MyListGeneric[Nothing] = throw new NoSuchElementException
  def isEmpty: Boolean = true
  def add[B >: Nothing](element: B): MyListGeneric[B] = new ConsGeneric(element, EmptyGeneric)
  def printElements: String = ""

  def map[B](transformer: MyTransformer[Nothing, B]): MyListGeneric[B] = EmptyGeneric
  def flatMap[B](transformer: MyTransformer[Nothing, MyListGeneric[B]]): MyListGeneric[B] = EmptyGeneric
  def filter(predicate: Mypredicate[Nothing]): MyListGeneric[Nothing] = EmptyGeneric

  def ++[B >: Nothing](list: MyListGeneric[B]): MyListGeneric[B] = list
}

case class ConsGeneric[+A](h: A, t: MyListGeneric[A]) extends MyListGeneric[A] {
  def head: A = h
  def tail: MyListGeneric[A] = t
  def isEmpty: Boolean = false
  def add[B >: A](element: B): MyListGeneric[B] = new ConsGeneric(element, this)
  def printElements: String =
    if (t.isEmpty) "" + h
    else h + " " + t.printElements

  /*
    [1,2,3].filter(n % 2 == 0) =
      [2,3].filter(n % 2 == 0) =
      = new ConsGeneric(2, [3].filter(n % 2 == 0))
      = new ConsGeneric(2, EmptyGeneric.filter(n % 2 == 0))
      = new ConsGeneric(2, EmptyGeneric)
   */
  def filter(predicate: Mypredicate[A]): MyListGeneric[A] =
    if (predicate.test(h)) new ConsGeneric(h, t.filter(predicate))
    else t.filter(predicate)

  /*
    [1,2,3].map(n * 2)
      = new ConsGeneric(2, [2,3].map(n * 2)
      = new ConsGeneric(2, new ConsGeneric(4, [3].map(n * 2)
      = new ConsGeneric(2, new ConsGeneric(4, new ConsGeneric(6, EmptyGeneric.map(n * 2))))
      = new ConsGeneric(2, new ConsGeneric(4, new ConsGeneric(6, EmptyGeneric))))
   */
  def map[B](transformer: MyTransformer[A, B]): MyListGeneric[B] =
    new ConsGeneric(transformer.transformer(h), t.map(transformer))

  /*
    [1,2] ++ [3,4,5]
      = new ConsGeneric([1, [2] ++ [3,4,5])
      = new ConsGeneric(1, new ConsGeneric(2, EmptyGeneric ++ [3,4,5]))
      = new ConsGeneric(1, new ConsGeneric(2, new ConsGeneric(3, new ConsGeneric(4, new ConsGeneric(5)))))
   */
  def ++[B >: A](list: MyListGeneric[B]): MyListGeneric[B] = new ConsGeneric(h, t ++ list)

  /*
    [1,2].flatMap(n => [n, n + 1])
      = [1,2] ++ [2].flatMap(n => [n, n + 1])
      = [1,2] ++ [2,3] ++ EmptyGeneric.flatMap(n => [n, n + 1])
      = [1,2] ++ [2,3] ++ EmptyGeneric
      = [1,2,2,3]
   */
  def flatMap[B](transformer: MyTransformer[A, MyListGeneric[B]]): MyListGeneric[B] =
    transformer.transformer(h) ++ t.flatMap(transformer)

}

trait Mypredicate[-T] {
  def test(elem: T): Boolean
}

trait MyTransformer[-A, B] {
  def transformer(elem: A): B
}

/*
  1. Generic trait MyPredicate[-T] with a little method test(T) => Boolean
  2. Generic trait MyTransformer[-A, B] with a method transform(A) => B
  3. MyList:
    - map(transformer) => MyList
    - filter(precicate) = MyList
    - flatMap(transformer from A to MyList[B]) => MyList[B]

    class EvenPredicate extends MyPredicate[Int]
    class StringToIntTransformer extends MyTransformer[String, Int]

    [1,2,3],map(n * 2) = [2,4,6]
    [1,2,3,4].filter(n % 2) = [2,4]
    [1,2,3].flatMap(n => [n, n + 1]) => [1,2,2,3,3,4]

 */

object ListGenericTest extends App {
  val listEmptyOfIntegers: MyListGeneric[Int] = EmptyGeneric
  val listEmptyOfStrings: MyListGeneric[String] = EmptyGeneric

  val listOfIntegers: MyListGeneric[Int] = new ConsGeneric(1, new ConsGeneric(2, new ConsGeneric(3, EmptyGeneric)))
  val closeListOfIntegers: MyListGeneric[Int] = new ConsGeneric(1, new ConsGeneric(2, new ConsGeneric(3, EmptyGeneric)))
  val anotherListOfIntegers: MyListGeneric[Int] = new ConsGeneric(4, new ConsGeneric(5, EmptyGeneric))
  val listOfStrings: MyListGeneric[String] = new ConsGeneric("Hello", new ConsGeneric("Scala", EmptyGeneric))

  println(listEmptyOfIntegers.toString)
  println(listEmptyOfStrings.toString)

  println(listOfIntegers.toString)
  println(listOfStrings.toString)

  println(listOfIntegers.map(new MyTransformer[Int, Int] {
    override def transformer(elem: Int): Int = elem * 2
  }).toString)

  println(listOfIntegers.filter(new Mypredicate[Int] {
    override def test(elem: Int): Boolean = elem % 2 == 0
  }).toString)

  println((listOfIntegers ++ anotherListOfIntegers).toString)
  println(listOfIntegers.flatMap(new MyTransformer[Int, MyListGeneric[Int]] {
    override def transformer(elem: Int): MyListGeneric[Int] = new ConsGeneric(elem, new ConsGeneric(elem + 1, EmptyGeneric))
  }).toString)

  println(closeListOfIntegers == listOfIntegers)
}