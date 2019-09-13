package lectures.part2oop

object AbstractDataTypes extends App {

  // abstract
  abstract class Animal {
    val creatureType: String
    def eat: Unit
  }

  class Dog extends Animal {
    override val creatureType: String = "canine"
    def eat: Unit = println("crunch, crunch")
  }

  class Bird extends Animal {
    override val creatureType: String = "bird"
    def eat: Unit = println("toc, toc")
  }

  // traits
  trait Carnivore {
    def eat(animal: Animal): Unit
  }

  trait ColdBlooded

  class Crocodile extends Animal with Carnivore with ColdBlooded {
    val creatureType: String = "croc"
    def eat: Unit = println("nomnomnom")
    def eat(animal: Animal): Unit = println(s"I'm a croc and I'm eating ${animal.creatureType}")
  }

  val bird = new Bird
  val dog = new Dog
  val croc = new Crocodile
  croc.eat(dog)
  croc.eat(bird)

  // traits vs abstract classes
  // 1 - traits do not have constructor parameters
  // 2 - multiple traits may be inherited by the same class
  // 3 - traits = behavior, abstract class = "thing"

}
