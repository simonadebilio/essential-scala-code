package _mySolutions.chapter03

// Write two classes, Director and Film , with fields and methods as follows:
//  • Director should contain:
//    – a field firstName of type String
//    – a field lastName of type String
//    – a field yearOfBirth of type Int
//    – a method called name that accepts no parameters and returns the full name
case class Director(
    firstName: String,
    lastName: String,
    yearOfBirth: Int
) {
  def name = s"$firstName $lastName $yearOfBirth"

}

// Write a companion object for Director that should contain:
//    – an apply method that accepts the same parameters as the constructor of the class
//      and returns a new Director;
//    – a method older that accepts two Directors and returns the oldest of the two.
object Director {
  def older(directorN1: Director, directorN2: Director): Director =
    if (directorN1.yearOfBirth < directorN2.yearOfBirth)
      directorN2
    else
      directorN1
}

// • Film should contain:
//   - a field name of type String
//   – a field yearOfRelease of type Int
//   – a field imdbRating of type Double
//   – a field director of type Director
//   – a method directorsAge that returns the age of the director at the time of release
//   - a method isDirectedBy that accepts a Director as a parameter and returns a Boolean
case class Film(
    name: String,
    yearOfRelease: Int,
    imdbRating: Double,
    director: Director
) {
  def directorsAge: Int =
    yearOfRelease - director.yearOfBirth

  def isDirectedBy(director: Director): Boolean =
    this.director == director

  val eastwood = new Director("Clint", "Eastwood", 1930)
  val mcTiernan = new Director("John", "McTiernan", 1951)
  val nolan = new Director("Christopher", "Nolan", 1970)
  val someBody = new Director("Just", "SomeBody", 1990)

  val inception = new Film("Inception", 2010, 8.8, nolan)
  val granTorino = new Film("Gran Torino", 2008, 8.2, eastwood)
  val dieHard = new Film("Die Hard", 1988, 8.3, mcTiernan)
  val something = new Film("something", 1994, 10, someBody)

}

// Write a companion object for Film that should contain:
//    – an apply method that accepts the same parameters as the constructor of the class
//        and returns a new Film ;
//    – a method highestRating that accepts two Films and returns the highest imdbRating of the two;
//    – a method oldestDirectorAtTheTime that accepts two Films
//        and returns the Director who was oldest at the respective time of filming.
object Film {
  def highestRating(filmN1: Film, filmN2: Film): Film =
    if (filmN1.imdbRating > filmN2.imdbRating)
      filmN1
    else
      filmN2

  def ageAtTheTime(film: Film): Int =
    film.yearOfRelease - film.director.yearOfBirth

  def oldestDirectorAtTheTime(filmN1: Film, filmN2: Film): Director =
    if (filmN1.directorsAge > filmN2.directorsAge)
      filmN1.director
    else
      filmN2.director
}

// In this exercise we’re going to write a simulator of my Dad, the movie critic.
//  It’s quite simple: any movie directed by Clint Eastwood gets a rating 10.0,
//  any movie directed by John McTiernan gets a 7.0,
//  while any other movie gets a 3.0.
//  Implement an object called Dad with a method rate which accepts a Film and returns a Double.
//  Use pattern matching.
object Dad {
  def rate(film: Film): Double =
    film match {
      case Film(_, _, _, Director("Clint", "Eastwood", _)) => 10.0
      case Film(_, _, _, Director("John", "McTiernan", _)) => 7.0
      case Film(_, _, _, Director(_, _, _))                => 3.0
    }
}
