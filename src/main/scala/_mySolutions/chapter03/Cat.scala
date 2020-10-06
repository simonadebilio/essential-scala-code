package _mySolutions.chapter03

// Define a class Cat and then create an object for each cat
case class Cat(colour: String = "colour", food: String = "food") {
  val oswald = Cat("Black", "Milk")
  val henderson = Cat("Ginger", "Chips")
  val quentin = Cat("Tabby and white", "Curry")
}

// Define an object ChipShop with a method willServe. This method should
//  accept a Cat and return true if the cat’s favourite food is chips, and false
//  otherwise.
object ChipShop {
  def willServe(c: Cat): Boolean =
    if (c.food == "Chips") true else false
}

object ChipShops2 {
  def willServe(cat: Cat): Boolean =
    cat match {
      case Cat(_, "Chips") => true
      case Cat(_, _)    => false
    }
}
