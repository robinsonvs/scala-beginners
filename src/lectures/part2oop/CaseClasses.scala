package lectures.part2oop

object CaseClasses extends App {

  /*
    equals, hashcode, toString
   */

  case class Person(name: String, age: Int)

  //1. class parameters are fields
  val jim = new Person("Jim", 34)
  println(jim.name)

  //2. sensible toString
  // println(instance) = println(instance.toString) // syntactic sugar
  println(jim.toString)
  println(jim)

  //3. equals and hashcode implemented OutOfTheBox
  val jim2 = new Person("Jim", 34)
  println(jim == jim2)

  //4. Case Classes handy copy method
  val jim3 = jim.copy(age = 45)
  println(jim3)

  //5. Case Classes have companion objects
  val thePerson = Person
  val mary = Person("mary", 23)

  //6. Case Classes are serializable
  //Akka

  //7. Case Classes have extractor patterns = Case Classes can be used in PATTERN MATCHING

  case object UnitedKingdom {
    def name: String = "The UK of GB and NI"
  }

  /*
    Expand MyListGeneric - use case classes and case objects
   */

}
