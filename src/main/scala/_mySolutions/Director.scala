package _mySolutions

class Director(
    val firstName: String,
    val lastName: String,
    val yearOfBirth: Int
) {

  def name: String = s"$firstName $lastName"

  def copy(
      firstName: String = this.firstName,
      lastName: String = this.lastName,
      yearOfBirth: Int = this.yearOfBirth
  ) =
    new Director(firstName, lastName, yearOfBirth)
}

class Film(
    val name: String,
    val yearOfRelease: Int,
    val imdbRating: Double,
    val director: Director
) {
  def directorsAge: Int = yearOfRelease - director.yearOfBirth

  def isDirectedBy(director: Director): Boolean =
    this.director == director

//  Implement a method of Film called copy . This method should accept the
//  same parameters as the constructor and create a new copy of the film. Give
//  each parameter a default value so you can copy a film changing any subset of
//  its values.
  def copy(
          name: String = this.name,
          yearOfRelease: Int = this.yearOfRelease,
          imdbRating: Double = this.imdbRating,
          director: Director = this.director): Film =
  new Film(name, yearOfRelease, imdbRating, director)
}
