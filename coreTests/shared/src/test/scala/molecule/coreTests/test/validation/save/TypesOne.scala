package molecule.coreTests.test.validation.save

import java.net.URI
import java.util.UUID
import molecule.base.error.ValidationErrors
import molecule.core.api.ApiAsync
import molecule.core.spi.SpiAsync
import molecule.core.util.Executor._
import molecule.coreTests.async._
import molecule.coreTests.dataModels.core.dsl.Validation._
import molecule.coreTests.setup.CoreTestSuite
import utest._
import scala.language.implicitConversions

trait TypesOne extends CoreTestSuite with ApiAsync { spi: SpiAsync =>

  override lazy val tests = Tests {

    "String" - validation { implicit conn =>
      for {
        _ <- Type.string("a").save.transact
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

        // Focusing only on the first (and only) error message
        // (See ValidationFormatting tests for multi-error validations)
        _ <- Type.string("a").save.transact
          .map(_ ==> "Unexpected success").recover {
            case ValidationErrors(errorMap) =>
              errorMap.head._2.head ==>
                s"""Type.string with value `a` doesn't satisfy validation:
                   |  _ > "b"
                   |""".stripMargin
          }
      } yield ()
    }

    "Int" - validation { implicit conn =>
      for {
        _ <- Type.int(1).save.transact
          .map(_ ==> "Unexpected success").recover {
            case ValidationErrors(errorMap) =>
              errorMap.head._2.head ==>
                s"""Type.int with value `1` doesn't satisfy validation:
                   |  _ > 2
                   |""".stripMargin
          }
      } yield ()
    }

    "Long" - validation { implicit conn =>
      for {
        _ <- Type.long(1L).save.transact
          .map(_ ==> "Unexpected success").recover {
            case ValidationErrors(errorMap) =>
              errorMap.head._2.head ==>
                s"""Type.long with value `1` doesn't satisfy validation:
                   |  _ > 2L
                   |""".stripMargin
          }
      } yield ()
    }

    "Float" - validation { implicit conn =>
      for {
        _ <- Type.float(float1).save.transact
          .map(_ ==> "Unexpected success").recover {
            case ValidationErrors(errorMap) =>
              errorMap.head._2.head ==>
                s"""Type.float with value `$float1` doesn't satisfy validation:
                   |  _ > 2.2f
                   |""".stripMargin
          }
      } yield ()
    }

    "Double" - validation { implicit conn =>
      for {
        _ <- Type.double(double1).save.transact
          .map(_ ==> "Unexpected success").recover {
            case ValidationErrors(errorMap) =>
              errorMap.head._2.head ==>
                s"""Type.double with value `$double1` doesn't satisfy validation:
                   |  _ > 2.2
                   |""".stripMargin
          }
      } yield ()
    }

    "Boolean" - validation { implicit conn =>
      for {
        _ <- Type.boolean(true).save.transact
          .map(_ ==> "Unexpected success").recover {
            case ValidationErrors(errorMap) =>
              errorMap.head._2.head ==>
                s"""Type.boolean with value `true` doesn't satisfy validation:
                   |  _ == false
                   |""".stripMargin
          }
      } yield ()
    }

    "BigInt" - validation { implicit conn =>
      for {
        _ <- Type.bigInt(bigInt1).save.transact
          .map(_ ==> "Unexpected success").recover {
            case ValidationErrors(errorMap) =>
              errorMap.head._2.head ==>
                s"""Type.bigInt with value `$bigInt1` doesn't satisfy validation:
                   |  _ > BigInt(2)
                   |""".stripMargin
          }
      } yield ()
    }

    "BigDecimal" - validation { implicit conn =>
      for {
        _ <- Type.bigDecimal(bigDecimal1).save.transact
          .map(_ ==> "Unexpected success").recover {
            case ValidationErrors(errorMap) =>
              errorMap.head._2.head ==>
                s"""Type.bigDecimal with value `$bigDecimal1` doesn't satisfy validation:
                   |  _ > BigDecimal(2.2)
                   |""".stripMargin
          }
      } yield ()
    }

    "Date" - validation { implicit conn =>
      for {
        _ <- Type.date(date1).save.transact
          .map(_ ==> "Unexpected success").recover {
            case ValidationErrors(errorMap) =>
              errorMap.head._2.head ==>
                s"""Type.date with value `$date1` doesn't satisfy validation:
                   |  _.after(new Date(993942000000L))
                   |""".stripMargin
          }
      } yield ()
    }

    "UUID" - validation { implicit conn =>
      for {
        _ <- Type.uuid(UUID.fromString("aaaaaaaa-aaaa-aaaa-aaaa-aaaaaaaaaaaa")).save.transact
          .map(_ ==> "Unexpected success").recover {
            case ValidationErrors(errorMap) =>
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
        _ <- Type.uri(uri).save.transact
          .map(_ ==> "Unexpected success").recover {
            case ValidationErrors(errorMap) =>
              errorMap.head._2.head ==>
                s"""Type.uri with value `x` doesn't satisfy validation:
                   |  _.toString.length > 2
                   |""".stripMargin
          }
      } yield ()
    }

    "Byte" - validation { implicit conn =>
      for {
        _ <- Type.byte(byte1).save.transact
          .map(_ ==> "Unexpected success").recover {
            case ValidationErrors(errorMap) =>
              errorMap.head._2.head ==>
                s"""Type.byte with value `$byte1` doesn't satisfy validation:
                   |  _ > $byte2
                   |""".stripMargin
          }
      } yield ()
    }

    "Short" - validation { implicit conn =>
      for {
        _ <- Type.short(short1).save.transact
          .map(_ ==> "Unexpected success").recover {
            case ValidationErrors(errorMap) =>
              errorMap.head._2.head ==>
                s"""Type.short with value `$short1` doesn't satisfy validation:
                   |  _ > $short2
                   |""".stripMargin
          }
      } yield ()
    }

    "Char" - validation { implicit conn =>
      for {
        _ <- Type.char('a').save.transact
          .map(_ ==> "Unexpected success").recover {
            case ValidationErrors(errorMap) =>
              errorMap.head._2.head ==>
                s"""Type.char with value `a` doesn't satisfy validation:
                   |  _ > 'b'
                   |""".stripMargin
          }
      } yield ()
    }

    "ref" - validation { implicit conn =>
      for {
        _ <- Type.ref(1L).save.transact
          .map(_ ==> "Unexpected success").recover {
            case ValidationErrors(errorMap) =>
              errorMap.head._2.head ==>
                s"""Type.ref with value `1` doesn't satisfy validation:
                   |  _ > 2L
                   |""".stripMargin
          }
      } yield ()
    }
  }
}
