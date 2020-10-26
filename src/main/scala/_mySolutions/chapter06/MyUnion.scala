package _mySolutions.chapter06

object MyUnion {
  /*
  Write a method that takes two sets and returns a set containing the union of the elements.
    Use iteration, like map or foldLeft, not the built-in union method to do so!
   */

  def myUnionSet[A](firstSet: Set[A], secondSet: Set[A]): Set[A] =
    firstSet.foldLeft(secondSet)((currentSet, element) => currentSet + element)

  /*
  Now let’s write union for maps.
  Assume we have two Map[A, Int] and add corresponding elements in the two maps.
  So the union of Map('a' -> 1, 'b' -> 2) and Map('a' -> 2, 'b' -> 4) should be Map('a' -> 3, 'b' -> 6)
   */
  def myUnionMap[A](
      firstMap: Map[A, Int],
      secondMap: Map[A, Int]
  ): Map[A, Int] = 
    (for {
      key <- (firstMap.keys ++ secondMap.keys)
      v1 = firstMap.getOrElse(key, 0)
      v2 = secondMap.getOrElse(key, 0)
    } yield (key, v1 + v2)).toMap

//    val partial: Map[A, Int] =  for {
//        firstPair <- firstMap
//        v2 = secondMap.getOrElse(firstPair._1, 0)
//      } yield (firstPair._1, firstPair._2 + v2)
//
//    val difference: Map[A, Int] = secondMap -- firstMap.keys
//
//    partial ++ difference

  def main(args: Array[String]): Unit = {
    val firstSet: Set[Int] = Set()
    val secondSet: Set[Int] = Set(1, 2, 3)
//    println(myUnionSet(firstSet, secondSet))

//    val firstMap: Map[String, Int] = Map()
//    val secondMap: Map[String, Int] = Map("a" -> 1, "b" -> 2 , "c" -> 3)

    val firstMap: Map[String, Int] = Map("a" -> 1, "b" -> 2, "c" -> 3)
    val secondMap: Map[String, Int] = Map("a" -> 1, "b" -> 2, "d" -> 3)
    println(myUnionMap(firstMap, secondMap))

  }
}
