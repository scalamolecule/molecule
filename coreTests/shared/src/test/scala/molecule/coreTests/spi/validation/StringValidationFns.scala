package molecule.coreTests.spi.validation

import molecule.base.error.*
import molecule.core.api.Api_async
import molecule.core.spi.Spi_async
import molecule.core.util.Executor.*
import molecule.coreTests.domains.dsl.Validation.*
import molecule.coreTests.setup.*
import scala.language.implicitConversions


case class StringValidationFns(
  suite: Test,
  api: Api_async & Spi_async & DbProviders
) extends TestUtils {

  import api.*
  import suite.*

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
          case InsertErrors(Seq((_, Seq(InsertError(_, _, Seq(error), _)))), _) =>
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
