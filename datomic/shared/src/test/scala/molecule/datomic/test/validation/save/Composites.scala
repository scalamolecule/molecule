package molecule.datomic.test.validation.save

import molecule.base.error._
import molecule.core.util.Executor._
import molecule.coreTests.dataModels.core.dsl.Validation._
import molecule.datomic.async._
import molecule.datomic.setup.DatomicTestSuite
import utest._
import scala.language.implicitConversions

object Composites extends DatomicTestSuite {

  override lazy val tests = Tests {

    "1 + 1" - validation { implicit conn =>
      for {
        // bad, ok
        _ <- (Type.int(1) + Allowed.luckyNumber(7)).save.transact.expect {
          case ValidationErrors(errorMap, _) =>
            errorMap ==>
              Map(
                "Type.int" -> Seq(
                  s"""Type.int with value `1` doesn't satisfy validation:
                     |  _ > 1
                     |""".stripMargin
                )
              )
        }

        // ok, bad
        _ <- (Type.int(2) + Allowed.luckyNumber(0)).save.transact.expect {
          case ValidationErrors(errorMap, _) =>
            errorMap ==>
              Map(
                "Allowed.luckyNumber" -> Seq(
                  "Value `0` is not one of the allowed values in Seq(7, 9, 13)"
                )
              )
        }

        // bad, bad
        _ <- (Type.int(1) + Allowed.luckyNumber(0)).save.transact.expect {
          case ValidationErrors(errorMap, _) =>
            errorMap ==>
              Map(
                "Type.int" -> Seq(
                  s"""Type.int with value `1` doesn't satisfy validation:
                     |  _ > 1
                     |""".stripMargin
                ),
                "Allowed.luckyNumber" -> Seq(
                  "Value `0` is not one of the allowed values in Seq(7, 9, 13)"
                )
              )
        }
      } yield ()
    }


    "2 + 1" - validation { implicit conn =>
      for {
        // (bad, ok), ok
        _ <- (Type.int(1).string("b") + Allowed.luckyNumber(7)).save.transact.expect {
          case ValidationErrors(errorMap, _) =>
            errorMap ==>
              Map(
                "Type.int" -> Seq(
                  s"""Type.int with value `1` doesn't satisfy validation:
                     |  _ > 1
                     |""".stripMargin
                )
              )
        }

        // (ok, bad), ok
        _ <- (Type.int(2).string("a") + Allowed.luckyNumber(7)).save.transact.expect {
          case ValidationErrors(errorMap, _) =>
            errorMap ==>
              Map(
                "Type.string" -> Seq(
                  s"""Type.string with value `a` doesn't satisfy validation:
                     |  _ > "a"
                     |""".stripMargin
                )
              )
        }

        // (ok, ok), bad
        _ <- (Type.int(2).string("b") + Allowed.luckyNumber(0)).save.transact.expect {
          case ValidationErrors(errorMap, _) =>
            errorMap ==>
              Map(
                "Allowed.luckyNumber" -> Seq(
                  "Value `0` is not one of the allowed values in Seq(7, 9, 13)"
                )
              )
        }

        // (bad, bad), ok
        _ <- (Type.int(1).string("a") + Allowed.luckyNumber(7)).save.transact.expect {
          case ValidationErrors(errorMap, _) =>
            errorMap ==>
              Map(
                "Type.int" -> Seq(
                  s"""Type.int with value `1` doesn't satisfy validation:
                     |  _ > 1
                     |""".stripMargin
                ),
                "Type.string" -> Seq(
                  s"""Type.string with value `a` doesn't satisfy validation:
                     |  _ > "a"
                     |""".stripMargin
                )
              )
        }

        // (bad, ok), bad
        _ <- (Type.int(1).string("b") + Allowed.luckyNumber(0)).save.transact.expect {
          case ValidationErrors(errorMap, _) =>
            errorMap ==>
              Map(
                "Type.int" -> Seq(
                  s"""Type.int with value `1` doesn't satisfy validation:
                     |  _ > 1
                     |""".stripMargin
                ),
                "Allowed.luckyNumber" -> Seq(
                  "Value `0` is not one of the allowed values in Seq(7, 9, 13)"
                )
              )
        }

        // (ok, bad), bad
        _ <- (Type.int(2).string("a") + Allowed.luckyNumber(0)).save.transact.expect {
          case ValidationErrors(errorMap, _) =>
            errorMap ==>
              Map(
                "Type.string" -> Seq(
                  s"""Type.string with value `a` doesn't satisfy validation:
                     |  _ > "a"
                     |""".stripMargin
                ),
                "Allowed.luckyNumber" -> Seq(
                  "Value `0` is not one of the allowed values in Seq(7, 9, 13)"
                )
              )
        }

        // (bad, bad), bad
        _ <- (Type.int(1).string("a") + Allowed.luckyNumber(0)).save.transact.expect {
          case ValidationErrors(errorMap, _) =>
            errorMap ==>
              Map(
                "Type.int" -> Seq(
                  s"""Type.int with value `1` doesn't satisfy validation:
                     |  _ > 1
                     |""".stripMargin
                ),
                "Type.string" -> Seq(
                  s"""Type.string with value `a` doesn't satisfy validation:
                     |  _ > "a"
                     |""".stripMargin
                ),
                "Allowed.luckyNumber" -> Seq(
                  "Value `0` is not one of the allowed values in Seq(7, 9, 13)"
                )
              )
        }
      } yield ()
    }


    "1 + 2" - validation { implicit conn =>
      for {
        // ok, (bad, ok)
        _ <- (Allowed.luckyNumber(7) + Type.int(1).string("b")).save.transact.expect {
          case ValidationErrors(errorMap, _) =>
            errorMap ==>
              Map(
                "Type.int" -> Seq(
                  s"""Type.int with value `1` doesn't satisfy validation:
                     |  _ > 1
                     |""".stripMargin
                )
              )
        }

        // ok, (ok, bad)
        _ <- (Allowed.luckyNumber(7) + Type.int(2).string("a")).save.transact.expect {
          case ValidationErrors(errorMap, _) =>
            errorMap ==>
              Map(
                "Type.string" -> Seq(
                  s"""Type.string with value `a` doesn't satisfy validation:
                     |  _ > "a"
                     |""".stripMargin
                )
              )
        }

        // bad, (ok, ok)
        _ <- (Allowed.luckyNumber(0) + Type.int(2).string("b")).save.transact.expect {
          case ValidationErrors(errorMap, _) =>
            errorMap ==>
              Map(
                "Allowed.luckyNumber" -> Seq(
                  "Value `0` is not one of the allowed values in Seq(7, 9, 13)"
                )
              )
        }

        // ok, (bad, bad)
        _ <- (Allowed.luckyNumber(7) + Type.int(1).string("a")).save.transact.expect {
          case ValidationErrors(errorMap, _) =>
            errorMap ==>
              Map(
                "Type.int" -> Seq(
                  s"""Type.int with value `1` doesn't satisfy validation:
                     |  _ > 1
                     |""".stripMargin
                ),
                "Type.string" -> Seq(
                  s"""Type.string with value `a` doesn't satisfy validation:
                     |  _ > "a"
                     |""".stripMargin
                )
              )
        }

        // bad, (bad, ok)
        _ <- (Allowed.luckyNumber(0) + Type.int(1).string("b")).save.transact.expect {
          case ValidationErrors(errorMap, _) =>
            errorMap ==>
              Map(
                "Allowed.luckyNumber" -> Seq(
                  "Value `0` is not one of the allowed values in Seq(7, 9, 13)"
                ),
                "Type.int" -> Seq(
                  s"""Type.int with value `1` doesn't satisfy validation:
                     |  _ > 1
                     |""".stripMargin
                )
              )
        }

        // bad, (ok, bad)
        _ <- (Allowed.luckyNumber(0) + Type.int(2).string("a")).save.transact.expect {
          case ValidationErrors(errorMap, _) =>
            errorMap ==>
              Map(
                "Allowed.luckyNumber" -> Seq(
                  "Value `0` is not one of the allowed values in Seq(7, 9, 13)"
                ),
                "Type.string" -> Seq(
                  s"""Type.string with value `a` doesn't satisfy validation:
                     |  _ > "a"
                     |""".stripMargin
                )
              )
        }

        // bad, (bad, bad)
        _ <- (Allowed.luckyNumber(0) + Type.int(1).string("a")).save.transact.expect {
          case ValidationErrors(errorMap, _) =>
            errorMap ==>
              Map(
                "Allowed.luckyNumber" -> Seq(
                  "Value `0` is not one of the allowed values in Seq(7, 9, 13)"
                ),
                "Type.int" -> Seq(
                  s"""Type.int with value `1` doesn't satisfy validation:
                     |  _ > 1
                     |""".stripMargin
                ),
                "Type.string" -> Seq(
                  s"""Type.string with value `a` doesn't satisfy validation:
                     |  _ > "a"
                     |""".stripMargin
                )
              )
        }
      } yield ()
    }


    "2 + 2" - validation { implicit conn =>
      for {
        // (bad, ok), (ok, ok)
        _ <- (Type.int(1).string("b") + Allowed.luckyNumber(7).luckyNumber2(9)).save.transact.expect {
          case ValidationErrors(errorMap, _) =>
            errorMap ==>
              Map(
                "Type.int" -> Seq(
                  s"""Type.int with value `1` doesn't satisfy validation:
                     |  _ > 1
                     |""".stripMargin
                )
              )
        }

        // (ok, bad), (ok, ok)
        _ <- (Type.int(2).string("a") + Allowed.luckyNumber(7).luckyNumber2(9)).save.transact.expect {
          case ValidationErrors(errorMap, _) =>
            errorMap ==>
              Map(
                "Type.string" -> Seq(
                  s"""Type.string with value `a` doesn't satisfy validation:
                     |  _ > "a"
                     |""".stripMargin
                )
              )
        }

        // (ok, ok), (bad, ok)
        _ <- (Type.int(2).string("b") + Allowed.luckyNumber(0).luckyNumber2(9)).save.transact.expect {
          case ValidationErrors(errorMap, _) =>
            errorMap ==>
              Map(
                "Allowed.luckyNumber" -> Seq(
                  "Value `0` is not one of the allowed values in Seq(7, 9, 13)"
                )
              )

        }

        // (ok, ok), (ok, bad)
        _ <- (Type.int(2).string("b") + Allowed.luckyNumber(7).luckyNumber2(0)).save.transact.expect {
          case ValidationErrors(errorMap, _) =>
            errorMap ==>
              Map(
                "Allowed.luckyNumber2" -> Seq(
                  "Lucky number can only be 7, 9 or 13"
                )
              )
        }

        // Jackpot
        // (bad, bad), (bad, bad)
        _ <- (Type.int(1).string("a") + Allowed.luckyNumber(0).luckyNumber2(2)).save.transact.expect {
          case ValidationErrors(errorMap, _) =>
            errorMap ==>
              Map(
                "Type.int" -> Seq(
                  s"""Type.int with value `1` doesn't satisfy validation:
                     |  _ > 1
                     |""".stripMargin
                ),
                "Type.string" -> Seq(
                  s"""Type.string with value `a` doesn't satisfy validation:
                     |  _ > "a"
                     |""".stripMargin
                ),
                "Allowed.luckyNumber" -> Seq(
                  "Value `0` is not one of the allowed values in Seq(7, 9, 13)"
                ),
                "Allowed.luckyNumber2" -> Seq(
                  "Lucky number can only be 7, 9 or 13"
                )
              )
        }
      } yield ()
    }
  }
}
