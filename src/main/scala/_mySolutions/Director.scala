package _mySolutions

// Write two classes, Director and Film , with fields and methods as follows:
//  • Director should contain:
//    – a field firstName of type String
//    – a field lastName of type String
//    – a field yearOfBirth of type Int
//    – a method called name that accepts no parameters and returns the full name
class Director(
    val firstName: String,
    val lastName: String,
    val yearOfBirth: Int
) {
  def name = s"$firstName $lastName $yearOfBirth"

  def copy(firstName: String, lastName: String, yearOfBirth: Int): Director =
    new Director(firstName, lastName, yearOfBirth)
}

// • Film should contain:
//   - a field name of type String
//   – a field yearOfRelease of type Int
//   – a field imdbRating of type Double
//   – a field director of type Director
//   – a method directorsAge that returns the age of the director at the time of release
//   - a method isDirectedBy that accepts a Director as a parameter and returns a Boolean
class Film(
    val name: String,
    val yearOfRelease: Int,
    val imdbRating: Double,
    val director: Director
) {
  def directorsAge(director: Director, film: Film): Int =
    film.yearOfRelease - director.yearOfBirth

  def isDirectedBy(director: Director): Boolean =
    this.director == director
//      if (director == this.director)
//        true
//      else
//        false

  def copy(
      name: String = "Natale",
      yearOfRelease: Int = 1990,
      imdbRating: Double = 10,
      director: Director = someBody
  ): Film =
    new Film(name, yearOfRelease, imdbRating, director)

  val eastwood = new Director("Clint", "Eastwood", 1930)
  val mcTiernan = new Director("John", "McTiernan", 1951)
  val nolan = new Director("Christopher", "Nolan", 1970)
  val someBody = new Director("Just", "SomeBody", 1990)

  val inception = new Film("Inception", 2010, 8.8, nolan)
  val granTorino = new Film("Gran Torino", 2008, 8.2, eastwood)
  val dieHard = new Film("Die Hard", 1988, 8.3, mcTiernan)
  val something = new Film("something", 1994, 10, someBody)


}
