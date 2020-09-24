package _mySolutions

// Define a class Cat and then create an object for each cat
class Cat(val colour: String = "colour", val food: String = "food") {
  val oswald = new Cat("Black", "Milk")
  val henderson = new Cat("Ginger", "Chips")
  val quentin = new Cat("Tabby and white", "Curry")
}

// Define an object ChipShop with a method willServe . This method should
//  accept a Cat and return true if the cat’s favourite food is chips, and false
//  otherwise.
object ChipShop {
  def willServe(c: Cat): Boolean =
    if (c.food == "Chips") true else false
}
