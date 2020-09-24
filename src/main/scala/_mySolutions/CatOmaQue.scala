package _mySolutions

object CatOmaQue {

  object Oswald {
    val name = "Oswald"
    val color = "Black"
    val food = "Milk"
  }

  object Henderson {
    val name = "Henderson"
    val color = "Ginger"
    val food = "Chips"
  }

  object Quentin {
    val name = "Quentin"
    val color = "Tabby and white"
    val food = "Curry"
  }

  object Cats {
    def cat(name: String, color: String, food: String): String =
      name + ", " + color + ", " + food

    def main(args: Array[String]): Unit = {
      println(cat("Quentin", "Tabby and white", "Curry"))
    }
  }

}

// Square Dance!: define an object called calc with a method square that accepts a Double
//  as an argument and... you guessed it... squares its input. Add a method called
//  cube that cubes its input calling square as part of its result calcula on.

object Calc {
  def square(n: Double): Double =  n * n

  def cube(n: Double): Double = square(n) * n

}

