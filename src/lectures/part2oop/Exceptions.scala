package lectures.part2oop

object Exceptions extends App {

  var x: String = null
  //println(x.length)
  //this ^^ will crash with a NullPointerException

  //1. throwing exceptions

  //var aWeirdValue: String = throw new NullPointerException

  //throwable classes extend the Throwable class
  //Exception and Error are the major Throwable subtypes

  //2. how to catch exception
  def getInt(withExceptions: Boolean): Int =
    if (withExceptions) throw new RuntimeException("No int for you!")
    else 42

  var potencialFail = try {
    //code that might throw
    getInt(true)
  } catch {
    //case e: NullPointerException => println("caught a RuntimeException")
    case e: RuntimeException => 43
  } finally {
    //code that will get executed NO MATTER WHAT
    //optional
    //does not influence the return type of this expression
    //use finally only for side effects
    println("finally")
  }

  println(potencialFail)

  //3. how to define your own exceptions
  class MyExcepion extends Exception
  val exception = new MyExcepion
  //throw exception

  /*
    1. Crash your program with an OutOfMemoryError
    2. Crash with SOError
    3. PocketCalculator
        - add(x, y)
        - subtract(x, y)
        - multiply(x, y)
        - divide(x, y)

        Throw
          - OverflowException if add(x, y) exceeds Int.MAX.VALUE
          - UnderflowException if subtract(x, y) exceeds Int.MIN.VALUE
          - MathCalculationException for division by 0
   */

  // OutOfMemory
  // val array = Array.ofDim(Int.MaxValue)

  // StackOverFlow
  // def infinite: Int = 1 + infinite
  // val noLimit = infinite

  class OverflowException extends RuntimeException
  class UnderflowException extends RuntimeException
  class MathCalculationException extends RuntimeException("Division by zero")

  object PocketCalculator {
    def add(x: Int, y: Int) = {
      val result = x + y

      if (x > 0 && y > 0 && result < 0) throw new OverflowException
      else if (x < 0 && y < 0 && result > 0) throw new UnderflowException
      else result
    }

    def subtract(x: Int, y: Int) = {
      val result = x - y
      if (x > 0 && y < 0 && result < 0) throw new OverflowException
      else if (x < 0 && y > 0 && result > 0) throw new UnderflowException
      else result
    }

    def multiply(x: Int, y: Int) = {
      val result = x * y
      if (x > 0 && y > 0 && result < 0) throw new OverflowException
      else if (x < 0 && y < 0 && result < 0) throw new OverflowException
      else if (x > 0 && y < 0 && result > 0) throw new UnderflowException
      else if (x < 0 && y > 0 && result > 0) throw new UnderflowException
      else result
    }

    def divide(x: Int, y: Int) = {
      if (y == 0) throw new MathCalculationException
      else x / y
    }
  }

  println(PocketCalculator.add(Int.MaxValue, 10))
  println(PocketCalculator.divide(2, 0))
}
