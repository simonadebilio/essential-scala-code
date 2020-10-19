package _mySolutions.chapter06

package _mySolutions.chapter05

import scala.collection

object MovieDirectors {
case class Film(
                 name: String,
                 yearOfRelease: Int,
                 imdbRating: Double)

case class Director(
                     firstName: String,
                     lastName: String,
                     yearOfBirth: Int,
                     films: Seq[Film])

val memento: Film = Film("Memento", 2000, 8.5)
val darkKnight: Film = Film("Dark Knight", 2008, 9.0)
val inception: Film = Film("Inception", 2010, 8.8)
val highPlainsDrifter: Film = Film("High Plains Drifter", 1973, 7.7)
val outlawJoseyWales: Film = Film("The Outlaw Josey Wales", 1976, 7.9)
val unforgiven: Film = Film("Unforgiven", 1992, 8.3)
val granTorino: Film = Film("Gran Torino", 2008, 8.2)
val invictus: Film = Film("Invictus", 2009, 7.4)
val predator: Film = Film("Predator", 1987, 7.9)
val dieHard: Film = Film("Die Hard", 1988, 8.3)
val huntForRedOctober: Film = Film("The Hunt for Red October", 1990, 7.6)
val thomasCrownAffair: Film = Film("The Thomas Crown Affair", 1999, 6.8)

val eastwood: Director = Director("Clint", "Eastwood", 1930, Seq(highPlainsDrifter, outlawJoseyWales, unforgiven, granTorino, invictus))
val mcTiernan: Director = Director("John", "McTiernan", 1951, Seq(predator, dieHard, huntForRedOctober, thomasCrownAffair))
val nolan: Director = Director("Christopher", "Nolan", 1970, Seq(memento, darkKnight, inception))
  val someGuy: Director = Director("Just", "Some Guy", 1990, Seq())

  val directors: Seq[Director] = Seq(eastwood, mcTiernan, nolan, someGuy)


  /*
Using this sample code, write implementations of the following methods:
  • Accept a parameter numberOfFilms of type Int — find all directors who have directed more than numberOfFilms :
 */
  def moreThanNMovies(n: Int): Seq[Director] = directors.filter(_.films.length > n)


// Accept a parameter year of type Int — find a director who was born before that year:
  def bornBefore(year: Int): Director = directors.find(_.yearOfBirth < year).get

  /*
  Accept two parameters, year and numberOfFilms, and return a list of directors
  who were born before year who have also directed more than numberOfFilms
   */
  def bornBeforeWithEnoughFilms(year: Int, numberOfFilms: Int): Seq[Director] = {
    directors.filter(_.yearOfBirth < year).filter(_.films.length > numberOfFilms)
  }


  /*
  Accept a parameter ascending of type Boolean that defaults to true.
  Sort the directors by age in the specified order:
   */
  def directorsByAge(ascending: Boolean = true): Seq[Director] =
    if (ascending)
      directors.sortWith((a, b) => a.yearOfBirth > b.yearOfBirth)
      else
      directors.sortWith((a, b) => a.yearOfBirth < b.yearOfBirth)


//  Starting with the definition of nolan, create a list containing the names of the
//    films directed by Christopher Nolan.
  val nolanListOfFilms: Seq[String] = nolan.films.map(film => film.name)
  // nolan.film.map(_.name)



  def movieListOfADirector(director: Director): Seq[String] =
    director.films.map(film => film.name)


// Starting with the definition of directors, create a list containing the name of all films by all directors.
  val completeMovieList: Seq[String] = directors.flatMap(_.films.map(_.name))

  def everyMovieOfEveryDirector(listOfDirectors: Seq[Director]): Seq[String] =
    listOfDirectors.flatMap(_.films.map(_.name))


// Starting with mcTiernan, find the date of the earliest McTiernan film.
  // Tip: you can concisely find the minimum of two numbers a and b using math.min(a, b) .
  val earliestMcTiernanMovieDate: Int = mcTiernan.films.foldRight(Int.MaxValue){
    (film, current) => math.min(film.yearOfRelease, current)
  }

  def earliestMovieDateOfADirector(director: Director): Int =
    director.films.foldRight(Int.MaxValue) {
      (film, current) => math.min(film.yearOfRelease, current)
    }


//  Starting with directors, find all films sorted by descending IMDB rating
  val directorsMoviesSortedByAscendingRate: Seq[Film] = directors.flatMap(_.films.sortBy(_.imdbRating))
  val filmsRanking: Seq[Film] = directorsMoviesSortedByAscendingRate.sortBy(_.imdbRating)

val directorsMoviesSortedByDiscendingRate: Seq[Film] =
  directors.flatMap(director => director.films).sortWith((a, b) => a.imdbRating > b.imdbRating)


//  Starting with directors again, find the average score across all films
  def averageMovieScore(directors: Seq[Director]): Double = {
    val allFilms: Seq[Film] = directors.flatMap(_.films)
    val totalNumberOfFilms = allFilms.size
    val totalScore: Double = allFilms.foldRight(0.0)((f, a) => f.imdbRating + a)

    totalScore / totalNumberOfFilms
  }

//  Starting with directors, print the following for every film: "Tonight only! FILM NAME by DIRECTOR!"
  def tonightOnly(directors: Seq[Director]): Unit = {
   directors.foreach(director => director.films
            .foreach(film => println(s"Tonight only! ${film.name} by ${director.lastName}!")))
  }



//  Finally, starting with directors again, find the earliest film by any director
  def earliestFilm(directors: Seq[Director]): Seq[Film] = {
    val movieDateList: Seq[Int] = directors.map(director => director.films
      .foldRight(Int.MaxValue){
        (film, current) => math.min(film.yearOfRelease, current)
      })

    val earliestDate: Int = movieDateList.foldRight(0){
      (date, current) => math.min(date, current)
        }

    directors.flatMap(director => director.films.filter(_.yearOfRelease == earliestDate))
    }

  def earliestFilm2(directors: Seq[Director]): Option[Film] = {
    directors.
      flatMap(director => director.films).
      sortWith((a, b) => a.yearOfRelease < b.yearOfRelease).
      headOption
  }


/*
Repeat the following exercises from the previous sec on without using map or
  flatMap :
  1) List the names of the films directed by Christopher Nolan;
  2) List the names of all films by all directors;
  3) Find all films sorted by descending IMDB rating;
  4) Print the following for every film: "Tonight only! FILM NAME by DIRECTOR!";
 */

  val everyNolanMovie: Unit = for {
  nolanMovies <- nolan.films
  } yield nolanMovies.name

  def everyMovieFromEveryDirector(directors: Seq[Director]): Unit = {
    for {
    director <- directors
    } yield director.films
  }

  def everyMovieByDescendingRating(directors: Seq[Director]): Unit = {
    for {
      director <- directors
    } yield director.films.sortWith((f1, f2) => f1.imdbRating > f2.imdbRating)
  }

  def tonightOnly2(directors: Seq[Director]): Unit = {
    for {
    director <- directors
     film <- director.films
    } println(s"Tonight only! ${film.name} by ${director.lastName}!")
  }

  
  def main(args: Array[String]): Unit = {
    println(earliestFilm2(directors))
  }
}
