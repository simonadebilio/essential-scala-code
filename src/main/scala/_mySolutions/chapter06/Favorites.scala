package _mySolutions.chapter06

object Favorites {
  val people: Set[String] =
    Set("Alice", "Bob", "Charlie", "Derek", "Edith", "Fred")

  val ages: Map[String, Int] = Map(
    "Bob" -> 30,
    "Charlie" -> 50,
    "Derek" -> 40,
    "Edith" -> 10,
    "Fred" -> 60,
    "Alice" -> 20
  )

  val favoriteColors: Map[String, String] =
    Map("Bob" -> "green", "Derek" -> "magenta", "Fred" -> "yellow")

  val favoriteLolcats: Map[String, String] = Map(
    "Alice" -> "Long Cat",
    "Charlie" -> "Ceiling Cat",
    "Edith" -> "Cloud Cat"
  )

  /*
  Write a method `favoriteColor` that accepts a person’s name as a parameter
  and returns their favorite colour.
   */
  def favoriteColor(name: String): Option[String] = {
    favoriteColors.get(name)
  }

  //  Update `favoriteColor` to return a person’s favorite color or beige as a default.
  def favoriteColor2(name: String): String = {
    favoriteColors.get(name).getOrElse("beige")
  }

  // Write a method `printColors` that prints everyone’s favorite color!
  def printColors(listOfPeople: Map[String, String]): Iterable[String] = {
    listOfPeople.map(pair => pair._2)
  }

  /*
  Write a method `lookup` that accepts a name and one of the maps
  and returns the relevant value from the map.
  Ensure that the return type of the method matches the value type of the map.
   */
  def lookUp[A](name: String, whereToLook: Map[String, A]): Option[A] = {
    whereToLook.get(name)
  }

//  Calculate the color of the oldest person
  def colorOfTheOldestPerson(
      ages: Map[String, Int],
      colors: Map[String, String]
  ): Option[String] = {
//    val oldestPerson: (String, Int) =
//      ages.max(Ordering.by[(String, Int), Int](_._2))
//    val oldestPersonsName: String = oldestPerson._1
//
//    colors.get(oldestPersonsName)

    oldestPerson(ages).flatMap(colors.get)
  }

  def older: (Option[(String, Int)], (String, Int)) => Option[(String, Int)] =
    (prevMax: Option[(String, Int)], currentPerson: (String, Int)) =>
      prevMax match {
        case Some(pm) => {
          if (pm._2 > currentPerson._2) {
            Some(pm)
          } else {
            Some(currentPerson)
          }
        }
        case None => Some(currentPerson)
      }

  def oldestPerson(people: Map[String, Int]): Option[String] = {

    people.foldLeft(None: Option[(String, Int)])(older).map(_._1)
  }


  def main(args: Array[String]): Unit = {
    println(colorOfTheOldestPerson(ages, favoriteColors))
    println(oldestPerson(ages))
  }

}
