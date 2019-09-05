package lectures.part1basics

object CallByNameVsCallByValue extends App {

  def calledByValue(x: Long): Unit = {
    //println("by value: " + x)
    //println("by value: " + x)
    println("by value: " + 6144173974417L)
    println("by value: " + 6144173974417L)
  }

  def calledByName(x: => Long): Unit = {
    //println("by name: " + x)
    //println("by name: " + x)
    println("by name: " + System.nanoTime())
    println("by name: " + System.nanoTime())
  }

  calledByValue(6144173974417L)
  //calledByValue(System.nanoTime())
  calledByName(System.nanoTime())

  def infinite(): Int = 1 + infinite()
  def printFirst(x: Int, y: => Int) = println(x)

  //printFirst(infinite(), 34) // java.lang.StackOverflowError
  printFirst(34, infinite())

}
