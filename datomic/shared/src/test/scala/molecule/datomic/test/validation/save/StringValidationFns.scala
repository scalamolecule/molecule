package molecule.datomic.test.validation.save

import molecule.base.error.ValidationErrors
import molecule.core.util.Executor._
import molecule.coreTests.dataModels.core.dsl.Validation._
import molecule.datomic.async._
import molecule.datomic.setup.DatomicTestSuite
import utest._
import scala.language.implicitConversions


object StringValidationFns extends DatomicTestSuite {

  override lazy val tests = Tests {

    "Email, default msg" - validation { implicit conn =>
      for {
        _ <- Strings.email("foo@bar").save.transact.expect {
          case ValidationErrors(errors, _) =>
            errors.head ==> "Strings.email" -> Seq(
              "`foo@bar` is not a valid email"
            )
        }
        _ <- Strings.email("foo@bar", "foo@baz").save.transact.expect {
          case ValidationErrors(errors, _) =>
            errors.head ==> "Strings.email" -> Seq(
              "`foo@bar` is not a valid email",
              "`foo@baz` is not a valid email",
            )
        }
        _ <- Strings.email("foo@bar.com", "foo@bar").save.transact.expect {
          case ValidationErrors(errors, _) =>
            errors.head ==> "Strings.email" -> Seq(
              "`foo@bar` is not a valid email"
            )
        }
        // ok
        _ <- Strings.email("foo@bar.com").save.transact
      } yield ()
    }

    "Email, msg" - validation { implicit conn =>
      for {
        _ <- Strings.emailWithMsg("foo@bar").save.transact.expect {
          case ValidationErrors(errors, _) =>
            errors.head ==> "Strings.emailWithMsg" -> Seq(
              "Please provide a real email"
            )
        }
        _ <- Strings.emailWithMsg("foo@bar", "foo@baz").save.transact.expect {
          case ValidationErrors(errors, _) =>
            errors.head ==> "Strings.emailWithMsg" -> Seq(
              "Please provide a real email",
              "Please provide a real email",
            )
        }
        _ <- Strings.emailWithMsg("foo@bar.com", "foo@bar").save.transact.expect {
          case ValidationErrors(errors, _) =>
            errors.head ==> "Strings.emailWithMsg" -> Seq(
              "Please provide a real email"
            )
        }
        _ <- Strings.emailWithMsg("foo@bar.com").save.transact
      } yield ()
    }


    "Regex, default msg" - validation { implicit conn =>
      for {
        _ <- Strings.regex("Ben-hur").save.transact.expect {
          case ValidationErrors(errors, _) =>
            errors.head ==> "Strings.regex" -> Seq(
              """"Ben-hur" doesn't match regex pattern: ^[a-zA-Z0-9]+$"""
            )
        }
        _ <- Strings.regex("Benhur").save.transact
      } yield ()
    }

    "Regex, msg" - validation { implicit conn =>
      for {
        _ <- Strings.regexWithMsg("Ben-hur").save.transact.expect {
          case ValidationErrors(errors, _) =>
            errors.head ==> "Strings.regexWithMsg" -> Seq(
              "Username cannot contain special characters."
            )
        }
        _ <- Strings.regexWithMsg("Benhur").save.transact
      } yield ()
    }
  }
}
