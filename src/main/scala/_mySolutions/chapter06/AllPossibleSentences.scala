package _mySolutions.chapter06

object AllPossibleSentences {

//  Write a program to generate all possible sentences given the following model:
  val subjects: List[String] = List("Noel", "The cat", "The dog")
  val verbs: List[String] = List("wrote", "chased", "slept on")
  val objects: List[String] = List("the book", "the ball", "the bed")

  def allPossibleSentences(
      subjects: List[String],
      verbs: List[String],
      objects: List[String]
  ): List[(String, String, String)] = {
    for {
      sub <- subjects
      verb <- verbs
      obj <- objects
    } yield (sub, verb, obj)
  }

  /*
  This model creates some clearly nonsensical sentences.
  We can do better by making the choice of verb depend on the subject, and the object depend on the verb.
  Let’s use the following model:
    • The subjects are as before.
    • For the verbs:
        • If the subject is “Noel” the possible verbs are “wrote”, “chased”, and “slept on”.
        • If the subject is “The cat” the possible verbs are “meowed at”, “chased”, and “slept on”.
        • If the subject is “The dog” the possible verbs are “barked at”, “chased”, and “slept on”.
    • For the objects:
        • If the verb is “wrote” the possible objects are “the book”, “the letter”, and “the code”.
        • If the verb is “chased” the possible objects are “the ball”, “the dog”, and “the cat”.
        • If the verb is “slept on” the possible objects are “the bed”, “the mat”, and “the train”
        • If the verb is “meowed at” the possible objects are “Noel”, “the door”, “the food cupboard”.
        • If the verb is “barked at” the possible objects are “the postman”, “the car”, and “the cat”.
   */

  def moreSense(
      subjects: List[String],
      verbs: List[String],
      objects: List[String]
  ): List[(String, String, String)] = {

    def verbsForSubjects(subject: String): List[String] =
      subject match {
        case "Noel"    => List("wrote", "chased", "slept on")
        case "The cat" => List("meowed at", "chased", "slept on")
        case "The dog" => List("barked at", "chased", "slept on")
      }

    def objectsForVerbs(verb: String): List[String] =
      verb match {
        case "wrote"     => List("the book", "the letter", "the code")
        case "chased"    => List("the ball", "the dog", "the cat")
        case "slept on"  => List("the bed", "the mat", "the train")
        case "meowed at" => List("Noel", "the door", "the cupboard")
        case "barked at" => List("the postman", "the car", "the cat")
      }

    for {
      subject <- subjects
      verb <- verbsForSubjects(subject)
      obj <- objectsForVerbs(verb)
    } yield (subject, verb, obj)
  }


  def main(args: Array[String]): Unit = {
//    println(moreSense(subjects, verbs, objects))
  }

}
