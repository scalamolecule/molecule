package molecule.datomic.test.validation.save

import java.net.URI
import java.util.{Date, UUID}
import molecule.base.error.ValidationErrors
import molecule.core.util.Executor._
import molecule.coreTests.dataModels.core.dsl.Validation._
import molecule.datomic.async._
import molecule.datomic.setup.DatomicTestSuite
import utest._
import scala.language.implicitConversions

object TypesOneOpt extends DatomicTestSuite {

  override lazy val tests = Tests {

    "String" - validation { implicit conn =>
      for {
        _ <- Type.string_?(Some("a")).save.transact.expect {
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
        // Focusing only on the first (and only) error message
        // (See ValidationFormatting tests for multi-error validations)
        _ <- Type.string_?(Some("a")).save.transact.expect {
          case ValidationErrors(errorMap, _) =>
            errorMap.head._2.head ==>
              s"""Type.string with value `a` doesn't satisfy validation:
                 |  _ > "a"
                 |""".stripMargin
        }
      } yield ()
    }

    "Int" - validation { implicit conn =>
      for {
        _ <- Type.int_?(Some(1)).save.transact.expect {
          case ValidationErrors(errorMap, _) =>
            errorMap.head._2.head ==>
              s"""Type.int with value `1` doesn't satisfy validation:
                 |  _ > 1
                 |""".stripMargin
        }
      } yield ()
    }

    "Long" - validation { implicit conn =>
      for {
        _ <- Type.long_?(Some(1L)).save.transact.expect {
          case ValidationErrors(errorMap, _) =>
            errorMap.head._2.head ==>
              s"""Type.long with value `1` doesn't satisfy validation:
                 |  _ > 1L
                 |""".stripMargin
        }
      } yield ()
    }

    "Float" - validation { implicit conn =>
      for {
        _ <- Type.float_?(Some(1.1f)).save.transact.expect {
          case ValidationErrors(errorMap, _) =>
            errorMap.head._2.head ==>
              s"""Type.float with value `1.1` doesn't satisfy validation:
                 |  _ > 1.1f
                 |""".stripMargin
        }
      } yield ()
    }

    "Double" - validation { implicit conn =>
      for {
        _ <- Type.double_?(Some(1.1)).save.transact.expect {
          case ValidationErrors(errorMap, _) =>
            errorMap.head._2.head ==>
              s"""Type.double with value `1.1` doesn't satisfy validation:
                 |  _ > 1.1
                 |""".stripMargin
        }
      } yield ()
    }

    "Boolean" - validation { implicit conn =>
      for {
        _ <- Type.boolean_?(Some(true)).save.transact.expect {
          case ValidationErrors(errorMap, _) =>
            errorMap.head._2.head ==>
              s"""Type.boolean with value `true` doesn't satisfy validation:
                 |  _ == false
                 |""".stripMargin
        }
      } yield ()
    }

    "BigInt" - validation { implicit conn =>
      for {
        _ <- Type.bigInt_?(Some(BigInt(1))).save.transact.expect {
          case ValidationErrors(errorMap, _) =>
            errorMap.head._2.head ==>
              s"""Type.bigInt with value `1` doesn't satisfy validation:
                 |  _ > BigInt(1)
                 |""".stripMargin
        }
      } yield ()
    }

    "BigDecimal" - validation { implicit conn =>
      for {
        _ <- Type.bigDecimal_?(Some(BigDecimal(1.1))).save.transact.expect {
          case ValidationErrors(errorMap, _) =>
            errorMap.head._2.head ==>
              s"""Type.bigDecimal with value `1.1` doesn't satisfy validation:
                 |  _ > BigDecimal(1.1)
                 |""".stripMargin
        }
      } yield ()
    }

    "Date" - validation { implicit conn =>
      for {
        _ <- Type.date_?(Some(new Date(993942000000L))).save.transact.expect {
          case ValidationErrors(errorMap, _) =>
            errorMap.head._2.head ==>
              s"""Type.date with value `$date1` doesn't satisfy validation:
                 |  _.after(new Date(993942000000L))
                 |""".stripMargin
        }
      } yield ()
    }

    "UUID" - validation { implicit conn =>
      for {
        _ <- Type.uuid_?(Some(UUID.fromString("aaaaaaaa-aaaa-aaaa-aaaa-aaaaaaaaaaaa"))).save.transact.expect {
          case ValidationErrors(errorMap, _) =>
            errorMap.head._2.head ==>
              s"""Type.uuid with value `aaaaaaaa-aaaa-aaaa-aaaa-aaaaaaaaaaaa` doesn't satisfy validation:
                 |  _.toString != "aaaaaaaa-aaaa-aaaa-aaaa-aaaaaaaaaaaa"
                 |""".stripMargin
        }
      } yield ()
    }

    "URI" - validation { implicit conn =>
      val uri = new URI("x")
      for {
        _ <- Type.uri_?(Some(uri)).save.transact.expect {
          case ValidationErrors(errorMap, _) =>
            errorMap.head._2.head ==>
              s"""Type.uri with value `x` doesn't satisfy validation:
                 |  _.toString.length > 1
                 |""".stripMargin
        }
      } yield ()
    }

    "Byte" - validation { implicit conn =>
      for {
        _ <- Type.byte_?(Some(byte1)).save.transact.expect {
          case ValidationErrors(errorMap, _) =>
            errorMap.head._2.head ==>
              s"""Type.byte with value `$byte1` doesn't satisfy validation:
                 |  _ > $byte1
                 |""".stripMargin
        }
      } yield ()
    }

    "Short" - validation { implicit conn =>
      for {
        _ <- Type.short_?(Some(short1)).save.transact.expect {
          case ValidationErrors(errorMap, _) =>
            errorMap.head._2.head ==>
              s"""Type.short with value `$short1` doesn't satisfy validation:
                 |  _ > $short1
                 |""".stripMargin
        }
      } yield ()
    }

    "Char" - validation { implicit conn =>
      for {
        _ <- Type.char_?(Some('a')).save.transact.expect {
          case ValidationErrors(errorMap, _) =>
            errorMap.head._2.head ==>
              s"""Type.char with value `a` doesn't satisfy validation:
                 |  _ > 'a'
                 |""".stripMargin
        }
      } yield ()
    }

    "ref" - validation { implicit conn =>
      for {
        _ <- Type.ref_?(Some(1L)).save.transact.expect {
          case ValidationErrors(errorMap, _) =>
            errorMap.head._2.head ==>
              s"""Type.ref with value `1` doesn't satisfy validation:
                 |  _ > 1L
                 |""".stripMargin
        }
      } yield ()
    }
  }
}
