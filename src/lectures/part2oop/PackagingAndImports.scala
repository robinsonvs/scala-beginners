package lectures.part2oop

//import playground.{Cinderella, PrinceCharmimg}
//import playground._

import java.util.Date
import java.sql.{Date => sqlDate}

import playground.{Cinderella, PrinceCharmimg => Princes} //alias

object PackagingAndImports extends App {

  //package members are acessible by their simple name
  val writer = new Writer("Robinson", "Severo", 2020)

  //import the package
  //val princess = new Cinderella
  //val princess = new playground.Cinderella //playground.Cinderella = fully qualified name
  val princess = new Cinderella

  //packages are in hierarchy
  //matching folder structure

  //package object
  sayHello
  println(SPPED_OF_LIGHT)

  //import
  val prince = new Princes

  //1. use Full Qualified
  val date = new Date()
  //2. use aliasing
  val sqlDate = new sqlDate(2020, 10, 5)

  //default imports
  //java.lang - String, Object, Exception
  //scala - Int, Nothing, Function
  //scala.Predef - println, ???



}
