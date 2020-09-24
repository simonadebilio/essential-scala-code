package _mySolutions

// Define an object called 'human' that contains fields called 'firstName' and
//  'lastName'. Define a second object called 'alien' containing a method called
//  'greet' that takes your person as a parameter and returns a greeting using their
//  firstName.
  object Human {
    val firstName = "Luca"
    val lastName = "Parruca"
  }


  object Alien {
    def greet(p: Human.type) =
      "Greetings," + "p.firstName"
  }

