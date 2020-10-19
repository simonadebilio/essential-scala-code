package _mySolutions.chapter06

object Animals {
  /*
  1) Create a Seq containing the String s "cat" , "dog" , and "penguin" .
  Bind it to the name animals.
  2) Append the element "tyrannosaurus" to animals and prepend the element "mouse".
   */
  val animals: Seq[String] = Seq("cat", "dog", "penguin")

  def main(args: Array[String]): Unit = {
    val notOnlyAnimals: Seq[Any] = 2 +: "mouse" +: animals :+ "tyrannosaurus"
  }
}