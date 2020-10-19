package _mySolutions.chapter05

// A binary Tree can be defined as follows:
//    - a Tree of type A is a Node with a left and right Tree
//    - or a Leaf with an element of type A.
//  Implement this algebraic data type along with a fold method.
sealed trait Tree[A] {
  def fold[B](leaf: A => B, node: (B, B) => B): B =
    this match {
      case Node(left, right) =>
        node(left.fold(leaf, node), right.fold(leaf, node))
      case Leaf(element) => leaf(element)
    }
}

case class Node[A](left: Tree[A], right: Tree[A]) extends Tree[A] {
//  override def fold[B](leaf: A => B, node: (B, B) => B): B =
//    node(left.fold(leaf, node), right.fold(leaf, node))
}

case class Leaf[A](element: A) extends Tree[A] {
//  override def fold[B](leaf: A => B, node: (B, B) => B): B =
//    leaf(element)
}

// Using fold convert the following Tree to a String (remember you can append Strings using the + method).
object TreeMain {
  def main(args: Array[String]): Unit = {
    val tree: Tree[String] =
      Node(
        Node(Leaf("To"), Leaf("iterate")),
        Node(
          Node(Leaf("is"), Leaf("human,")),
          Node(Leaf("to"), Node(Leaf("recurse"), Leaf("divine")))
        )
      )

    println(tree.fold[String](String => String, (a, b) => a + " " + b))
  }
}
