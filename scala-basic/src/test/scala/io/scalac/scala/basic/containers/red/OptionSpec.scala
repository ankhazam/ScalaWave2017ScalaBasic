package io.scalac.scala.basic.red

import org.scalatest._

class UserDatabase() {
  val usersByName = Map("James" -> "Bond", "Dana" -> "Scully", "Deep" -> "Thought", "Guy" -> "Montag")
  val usersBySurname = Map("Bond" -> "James", "Dana" -> "Scully", "Thought" -> "Deep", "Montag" -> "Guy")

  def findByName(username: String): Option[String] = ???
  def findByNameOrSurname(nameOrUsername: String): Option[String] = ???
  def findByNameStartingWithLetter(firstLetter: Char): Option[Seq[String]] = ???
}

class OptionSpec extends WordSpec with MustMatchers {
  "Containers.Option" should {

    val databaseForTest = new UserDatabase()

    "allow search by single field" in {
      databaseForTest.findByName("James") mustBe Some("James Bond")
      databaseForTest.findByName("Deep") mustBe Some("Deep Thought")
      databaseForTest.findByName("Dana") mustBe Some("Dana Scully")
      databaseForTest.findByName("Rick") mustBe None
    }

    "allow search by one of many fields" in {
      databaseForTest.findByNameOrSurname("James") mustBe Some("James Bond")
      databaseForTest.findByNameOrSurname("Thought") mustBe Some("Deep Thought")
      databaseForTest.findByNameOrSurname("Dana") mustBe Some("Dana Scully")
      databaseForTest.findByNameOrSurname("Rick") mustBe None
    }

    "allow search by first letter" in {
      databaseForTest.findByNameStartingWithLetter('J') mustBe Some(Seq("James Bond"))
      databaseForTest.findByNameStartingWithLetter('D') mustBe Some(Seq("Deep Thought", "Dana Scully"))
      databaseForTest.findByNameStartingWithLetter('G') mustBe Some(Seq("Guy Montag"))
      databaseForTest.findByNameStartingWithLetter('R') mustBe None
    }
  }
}
