package lectures.part3fp

object TuplesAndMaps extends App {

  //tuples = finite ordered "lists"
  val aTuple = (2, "hello, scala") // Tuple2[Int, String] = (Int, String)

  println(aTuple._1) //2
  println(aTuple.copy(_2 = "goodby Java"))
  println(aTuple.swap) //("hello", "Scala", 2)

  //Maps keys > values
  val aMap: Map[String, Int] = Map()

  val phonebook = Map(("Jim", 555), "Daniel" -> 789).withDefaultValue(-1)
  //a -> b is sugar for (a,b)
  println(phonebook)

  //map operations
  println(phonebook.contains("Jim"))
  println(phonebook("Jim"))
  println(phonebook("Mary"))

  //add a pairing
  val newPairing = "Mary" -> 678
  val newPhonebook = phonebook + newPairing
  println(newPhonebook)

  //functions on maps
  //map, flatMap, filter
  println(phonebook.map(pair => pair._1.toLowerCase -> pair._2))

  //filterKeys - deprecated 2.13
  println(phonebook.filterKeys(x => x.startsWith("J")))
  //mapValues
  println(phonebook.mapValues(number => number * 10))

  //conversions to other collections
  println(phonebook.toList)
  println(List(("Daniel", 555)).toMap)
  val names = List("Bob", "James", "Angela", "Mary", "Daniel", "Jim")
  println(names.groupBy(name => name.charAt(0)))

  

}
