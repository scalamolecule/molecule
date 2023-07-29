package molecule.coreTests.test.validation.save

import molecule.base.error._
import molecule.core.spi.SpiAsync
import molecule.core.util.Executor._
import molecule.coreTests.api.ApiAsyncImplicits
import molecule.coreTests.async._
import molecule.coreTests.dataModels.core.dsl.Validation._
import molecule.coreTests.setup.CoreTestSuite
import utest._
import scala.language.implicitConversions

trait Composites extends CoreTestSuite with ApiAsyncImplicits { self: SpiAsync  =>

  override lazy val tests = Tests {

    "1 + 1" - validation { implicit conn =>
      for {
        // bad, ok
        _ <- (Type.int(1) + Enum.luckyNumber(7)).save.transact
          .map(_ ==> "Unexpected success").recover {
          case ValidationErrors(errorMap) =>
            errorMap ==>
              Map(
                "Type.int" -> Seq(
                  s"""Type.int with value `1` doesn't satisfy validation:
                     |  _ > 2
                     |""".stripMargin
                )
              )
        }

        // ok, bad
        _ <- (Type.int(3) + Enum.luckyNumber(0)).save.transact
          .map(_ ==> "Unexpected success").recover {
          case ValidationErrors(errorMap) =>
            errorMap ==>
              Map(
                "Enum.luckyNumber" -> Seq(
                  "Value `0` is not one of the allowed values in Seq(7, 9, 13)"
                )
              )
        }

        // bad, bad
        _ <- (Type.int(1) + Enum.luckyNumber(0)).save.transact
          .map(_ ==> "Unexpected success").recover {
          case ValidationErrors(errorMap) =>
            errorMap ==>
              Map(
                "Type.int" -> Seq(
                  s"""Type.int with value `1` doesn't satisfy validation:
                     |  _ > 2
                     |""".stripMargin
                ),
                "Enum.luckyNumber" -> Seq(
                  "Value `0` is not one of the allowed values in Seq(7, 9, 13)"
                )
              )
        }
      } yield ()
    }


    "2 + 1" - validation { implicit conn =>
      for {
        // (bad, ok), ok
        _ <- (Type.int(1).string("c") + Enum.luckyNumber(7)).save.transact
          .map(_ ==> "Unexpected success").recover {
          case ValidationErrors(errorMap) =>
            errorMap ==>
              Map(
                "Type.int" -> Seq(
                  s"""Type.int with value `1` doesn't satisfy validation:
                     |  _ > 2
                     |""".stripMargin
                )
              )
        }

        // (ok, bad), ok
        _ <- (Type.int(3).string("a") + Enum.luckyNumber(7)).save.transact
          .map(_ ==> "Unexpected success").recover {
          case ValidationErrors(errorMap) =>
            errorMap ==>
              Map(
                "Type.string" -> Seq(
                  s"""Type.string with value `a` doesn't satisfy validation:
                     |  _ > "b"
                     |""".stripMargin
                )
              )
        }

        // (ok, ok), bad
        _ <- (Type.int(3).string("c") + Enum.luckyNumber(0)).save.transact
          .map(_ ==> "Unexpected success").recover {
          case ValidationErrors(errorMap) =>
            errorMap ==>
              Map(
                "Enum.luckyNumber" -> Seq(
                  "Value `0` is not one of the allowed values in Seq(7, 9, 13)"
                )
              )
        }

        // (bad, bad), ok
        _ <- (Type.int(1).string("a") + Enum.luckyNumber(7)).save.transact
          .map(_ ==> "Unexpected success").recover {
          case ValidationErrors(errorMap) =>
            errorMap ==>
              Map(
                "Type.int" -> Seq(
                  s"""Type.int with value `1` doesn't satisfy validation:
                     |  _ > 2
                     |""".stripMargin
                ),
                "Type.string" -> Seq(
                  s"""Type.string with value `a` doesn't satisfy validation:
                     |  _ > "b"
                     |""".stripMargin
                )
              )
        }

        // (bad, ok), bad
        _ <- (Type.int(1).string("c") + Enum.luckyNumber(0)).save.transact
          .map(_ ==> "Unexpected success").recover {
          case ValidationErrors(errorMap) =>
            errorMap ==>
              Map(
                "Type.int" -> Seq(
                  s"""Type.int with value `1` doesn't satisfy validation:
                     |  _ > 2
                     |""".stripMargin
                ),
                "Enum.luckyNumber" -> Seq(
                  "Value `0` is not one of the allowed values in Seq(7, 9, 13)"
                )
              )
        }

        // (ok, bad), bad
        _ <- (Type.int(3).string("a") + Enum.luckyNumber(0)).save.transact
          .map(_ ==> "Unexpected success").recover {
          case ValidationErrors(errorMap) =>
            errorMap ==>
              Map(
                "Type.string" -> Seq(
                  s"""Type.string with value `a` doesn't satisfy validation:
                     |  _ > "b"
                     |""".stripMargin
                ),
                "Enum.luckyNumber" -> Seq(
                  "Value `0` is not one of the allowed values in Seq(7, 9, 13)"
                )
              )
        }

        // (bad, bad), bad
        _ <- (Type.int(1).string("a") + Enum.luckyNumber(0)).save.transact
          .map(_ ==> "Unexpected success").recover {
          case ValidationErrors(errorMap) =>
            errorMap ==>
              Map(
                "Type.int" -> Seq(
                  s"""Type.int with value `1` doesn't satisfy validation:
                     |  _ > 2
                     |""".stripMargin
                ),
                "Type.string" -> Seq(
                  s"""Type.string with value `a` doesn't satisfy validation:
                     |  _ > "b"
                     |""".stripMargin
                ),
                "Enum.luckyNumber" -> Seq(
                  "Value `0` is not one of the allowed values in Seq(7, 9, 13)"
                )
              )
        }
      } yield ()
    }


    "1 + 2" - validation { implicit conn =>
      for {
        // ok, (bad, ok)
        _ <- (Enum.luckyNumber(7) + Type.int(1).string("c")).save.transact
          .map(_ ==> "Unexpected success").recover {
          case ValidationErrors(errorMap) =>
            errorMap ==>
              Map(
                "Type.int" -> Seq(
                  s"""Type.int with value `1` doesn't satisfy validation:
                     |  _ > 2
                     |""".stripMargin
                )
              )
        }

        // ok, (ok, bad)
        _ <- (Enum.luckyNumber(7) + Type.int(3).string("a")).save.transact
          .map(_ ==> "Unexpected success").recover {
          case ValidationErrors(errorMap) =>
            errorMap ==>
              Map(
                "Type.string" -> Seq(
                  s"""Type.string with value `a` doesn't satisfy validation:
                     |  _ > "b"
                     |""".stripMargin
                )
              )
        }

        // bad, (ok, ok)
        _ <- (Enum.luckyNumber(0) + Type.int(3).string("c")).save.transact
          .map(_ ==> "Unexpected success").recover {
          case ValidationErrors(errorMap) =>
            errorMap ==>
              Map(
                "Enum.luckyNumber" -> Seq(
                  "Value `0` is not one of the allowed values in Seq(7, 9, 13)"
                )
              )
        }

        // ok, (bad, bad)
        _ <- (Enum.luckyNumber(7) + Type.int(1).string("a")).save.transact
          .map(_ ==> "Unexpected success").recover {
          case ValidationErrors(errorMap) =>
            errorMap ==>
              Map(
                "Type.int" -> Seq(
                  s"""Type.int with value `1` doesn't satisfy validation:
                     |  _ > 2
                     |""".stripMargin
                ),
                "Type.string" -> Seq(
                  s"""Type.string with value `a` doesn't satisfy validation:
                     |  _ > "b"
                     |""".stripMargin
                )
              )
        }

        // bad, (bad, ok)
        _ <- (Enum.luckyNumber(0) + Type.int(1).string("c")).save.transact
          .map(_ ==> "Unexpected success").recover {
          case ValidationErrors(errorMap) =>
            errorMap ==>
              Map(
                "Enum.luckyNumber" -> Seq(
                  "Value `0` is not one of the allowed values in Seq(7, 9, 13)"
                ),
                "Type.int" -> Seq(
                  s"""Type.int with value `1` doesn't satisfy validation:
                     |  _ > 2
                     |""".stripMargin
                )
              )
        }

        // bad, (ok, bad)
        _ <- (Enum.luckyNumber(0) + Type.int(3).string("a")).save.transact
          .map(_ ==> "Unexpected success").recover {
          case ValidationErrors(errorMap) =>
            errorMap ==>
              Map(
                "Enum.luckyNumber" -> Seq(
                  "Value `0` is not one of the allowed values in Seq(7, 9, 13)"
                ),
                "Type.string" -> Seq(
                  s"""Type.string with value `a` doesn't satisfy validation:
                     |  _ > "b"
                     |""".stripMargin
                )
              )
        }

        // bad, (bad, bad)
        _ <- (Enum.luckyNumber(0) + Type.int(1).string("a")).save.transact
          .map(_ ==> "Unexpected success").recover {
          case ValidationErrors(errorMap) =>
            errorMap ==>
              Map(
                "Enum.luckyNumber" -> Seq(
                  "Value `0` is not one of the allowed values in Seq(7, 9, 13)"
                ),
                "Type.int" -> Seq(
                  s"""Type.int with value `1` doesn't satisfy validation:
                     |  _ > 2
                     |""".stripMargin
                ),
                "Type.string" -> Seq(
                  s"""Type.string with value `a` doesn't satisfy validation:
                     |  _ > "b"
                     |""".stripMargin
                )
              )
        }
      } yield ()
    }


    "2 + 2" - validation { implicit conn =>
      for {
        // (bad, ok), (ok, ok)
        _ <- (Type.int(1).string("c") + Enum.luckyNumber(7).luckyNumber2(9)).save.transact
          .map(_ ==> "Unexpected success").recover {
          case ValidationErrors(errorMap) =>
            errorMap ==>
              Map(
                "Type.int" -> Seq(
                  s"""Type.int with value `1` doesn't satisfy validation:
                     |  _ > 2
                     |""".stripMargin
                )
              )
        }

        // (ok, bad), (ok, ok)
        _ <- (Type.int(3).string("a") + Enum.luckyNumber(7).luckyNumber2(9)).save.transact
          .map(_ ==> "Unexpected success").recover {
          case ValidationErrors(errorMap) =>
            errorMap ==>
              Map(
                "Type.string" -> Seq(
                  s"""Type.string with value `a` doesn't satisfy validation:
                     |  _ > "b"
                     |""".stripMargin
                )
              )
        }

        // (ok, ok), (bad, ok)
        _ <- (Type.int(3).string("c") + Enum.luckyNumber(0).luckyNumber2(9)).save.transact
          .map(_ ==> "Unexpected success").recover {
          case ValidationErrors(errorMap) =>
            errorMap ==>
              Map(
                "Enum.luckyNumber" -> Seq(
                  "Value `0` is not one of the allowed values in Seq(7, 9, 13)"
                )
              )

        }

        // (ok, ok), (ok, bad)
        _ <- (Type.int(3).string("c") + Enum.luckyNumber(7).luckyNumber2(0)).save.transact
          .map(_ ==> "Unexpected success").recover {
          case ValidationErrors(errorMap) =>
            errorMap ==>
              Map(
                "Enum.luckyNumber2" -> Seq(
                  "Lucky number can only be 7, 9 or 13"
                )
              )
        }

        // Jackpot
        // (bad, bad), (bad, bad)
        _ <- (Type.int(1).string("a") + Enum.luckyNumber(0).luckyNumber2(2)).save.transact
          .map(_ ==> "Unexpected success").recover {
          case ValidationErrors(errorMap) =>
            errorMap ==>
              Map(
                "Type.int" -> Seq(
                  s"""Type.int with value `1` doesn't satisfy validation:
                     |  _ > 2
                     |""".stripMargin
                ),
                "Type.string" -> Seq(
                  s"""Type.string with value `a` doesn't satisfy validation:
                     |  _ > "b"
                     |""".stripMargin
                ),
                "Enum.luckyNumber" -> Seq(
                  "Value `0` is not one of the allowed values in Seq(7, 9, 13)"
                ),
                "Enum.luckyNumber2" -> Seq(
                  "Lucky number can only be 7, 9 or 13"
                )
              )
        }
      } yield ()
    }
  }
}
