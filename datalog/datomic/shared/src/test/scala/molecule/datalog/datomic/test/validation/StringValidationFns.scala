package molecule.datalog.datomic.test.validation

import molecule.base.error._
import molecule.core.util.Executor._
import molecule.coreTests.dataModels.core.dsl.Validation._
import molecule.datalog.datomic.async._
import molecule.datalog.datomic.setup.DatomicTestSuite
import utest._
import scala.language.implicitConversions


object StringValidationFns extends DatomicTestSuite {

  override lazy val tests = Tests {

    "Email, default msg" - validation { implicit conn =>
      for {
        _ <- Strings.email("foo@bar").save.transact
          .map(_ ==> "Unexpected success").recover {
          case ValidationErrors(errorMap) =>
            errorMap.head ==> "Strings.email" -> Seq(
              "`foo@bar` is not a valid email"
            )
        }
        // Same with insert
        _ <- Strings.email.insert("foo@bar").transact
          .map(_ ==> "Unexpected success").recover {
          case InsertErrors(Seq((_, Seq(InsertError(_, _, _, Seq(error), _)))), _) =>
            error ==> "`foo@bar` is not a valid email"
        }

        _ <- Strings.email("foo@bar", "foo@baz").save.transact
          .map(_ ==> "Unexpected success").recover {
          case ValidationErrors(errorMap) =>
            errorMap.head ==> "Strings.email" -> Seq(
              "`foo@bar` is not a valid email",
              "`foo@baz` is not a valid email",
            )
        }
        _ <- Strings.email("foo@bar.com", "foo@bar").save.transact
          .map(_ ==> "Unexpected success").recover {
          case ValidationErrors(errorMap) =>
            errorMap.head ==> "Strings.email" -> Seq(
              "`foo@bar` is not a valid email"
            )
        }
        // ok
        _ <- Strings.email("foo@bar.com").save.transact
      } yield ()
    }

    "Email, msg" - validation { implicit conn =>
      for {
        _ <- Strings.emailWithMsg("foo@bar").save.transact
          .map(_ ==> "Unexpected success").recover {
          case ValidationErrors(errorMap) =>
            errorMap.head ==> "Strings.emailWithMsg" -> Seq(
              "Please provide a real email"
            )
        }
        _ <- Strings.emailWithMsg("foo@bar", "foo@baz").save.transact
          .map(_ ==> "Unexpected success").recover {
          case ValidationErrors(errorMap) =>
            errorMap.head ==> "Strings.emailWithMsg" -> Seq(
              "Please provide a real email",
              "Please provide a real email",
            )
        }
        _ <- Strings.emailWithMsg("foo@bar.com", "foo@bar").save.transact
          .map(_ ==> "Unexpected success").recover {
          case ValidationErrors(errorMap) =>
            errorMap.head ==> "Strings.emailWithMsg" -> Seq(
              "Please provide a real email"
            )
        }
        _ <- Strings.emailWithMsg("foo@bar.com").save.transact
      } yield ()
    }


    "Regex, default msg" - validation { implicit conn =>
      for {
        _ <- Strings.regex("Ben-hur").save.transact
          .map(_ ==> "Unexpected success").recover {
          case ValidationErrors(errorMap) =>
            errorMap.head ==> "Strings.regex" -> Seq(
              """"Ben-hur" doesn't match regex pattern: ^[a-zA-Z0-9]+$"""
            )
        }
        _ <- Strings.regex("Benhur").save.transact
      } yield ()
    }

    "Regex, msg" - validation { implicit conn =>
      for {
        _ <- Strings.regexWithMsg("Ben-hur").save.transact
          .map(_ ==> "Unexpected success").recover {
          case ValidationErrors(errorMap) =>
            errorMap.head ==> "Strings.regexWithMsg" -> Seq(
              "Username cannot contain special characters."
            )
        }
        _ <- Strings.regexWithMsg("Benhur").save.transact
      } yield ()
    }
  }
}
