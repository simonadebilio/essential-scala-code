package _mySolutions.chapter03

// Implement a companion object for Person containing an apply method that
//  accepts a whole name as a single string rather than individual first and last
//  names.
case class Person(firstName: String, lastName: String) {
  val name: String = s"$firstName $lastName"
}

object Person {
  def apply(name: String): Person = {
    val parts: Array[String] = name.split(" ")
    if (parts.length>= 2) {
      new Person(parts(0), parts(1))
    } else {
      new Person(name, "")
    }
  }

  def main(args: Array[String]): Unit = {
    println(Person("ab cd").lastName)
  }
}