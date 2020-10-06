package _mySolutions.chapter04

// Demand for Cat Simulator 1.0 is exploding! For v2 we’re going to go beyond
//  the domestic cat to model Tigers, Lions, and Panthers in addition to the Cat.
//Define a trait Feline and then define all the different species as subtypes of Feline.
// To make things interesting, define:
//    • on Feline a colour as before;
//    • on Feline a String sound, which for a cat is "meow" and is "roar" for all other felines;
//    • only Cat has a favourite food; and
//    • Lions have an Int maneSize.
trait Feline {
  def colour: String
  def sound: String
}

case class Tigers(colour: String) extends Feline {
  val sound = "roar"
}

case class Panthers(colour: String) extends Feline {
  val sound = "roar"
}

case class Lions(colour: String, maneSize: Int) extends Feline {
  val sound = "Roar!"
}

case class Cats(colour: String, favouriteFood: String) extends Feline {
  val sound = "Meow"
}
