package lectures.part1basics

object ValuesVariablesTypes extends App {

  val x: Int = 42
  println(x)

  // vals are immutable
  // compile can infer types

  val aString: String = "Hello"
  val anotherString = "goodbye"

  val aBoolean: Boolean = false
  val aChar: Char = 'a'
  val aShort: Short = 4613
  val aLong: Long = 52745456456456456L
  val aFloat: Float = 2.0f
  val aDouble: Double = 3.14

  var aVariable: Int = 4
  aVariable = 5

}
