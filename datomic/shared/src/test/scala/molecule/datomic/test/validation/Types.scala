package molecule.datomic.test.validation

import java.net.URI
import java.util.{Date, UUID}
import molecule.base.util.exceptions._
import molecule.core.util.Executor._
import molecule.coreTests.dataModels.core.dsl.Validation._
import molecule.datomic.async._
import molecule.datomic.setup.DatomicTestSuite
import utest._
import scala.language.implicitConversions

object Types extends DatomicTestSuite {

  lazy val tests = Tests {

    "Card one, String" - validation { implicit conn =>
      for {
        _ <- Type.string("a").save.transact.expect {
          case ValidationErrors(errors, _) =>
            errors.head ==>
              "Type.string" -> Seq(
                s"""Type.string with value `a` doesn't satisfy validation:
                   |  _ > "a"
                   |""".stripMargin
              )
        }
      } yield ()
    }

    "Card one, Long" - validation { implicit conn =>
      for {
        _ <- Type.long(1L).save.transact.expect {
          case ValidationErrors(errors, _) =>
            errors.head ==>
              "Type.long" -> Seq(
                s"""Type.long with value `1` doesn't satisfy validation:
                   |  _ > 1L
                   |""".stripMargin
              )
        }
      } yield ()
    }

    "Card one, Float" - validation { implicit conn =>
      for {
        _ <- Type.float(1.1f).save.transact.expect {
          case ValidationErrors(errors, _) =>
            errors.head ==>
              "Type.float" -> Seq(
                s"""Type.float with value `1.1` doesn't satisfy validation:
                   |  _ > 1.1f
                   |""".stripMargin
              )
        }
      } yield ()
    }

    "Card one, Double" - validation { implicit conn =>
      for {
        _ <- Type.double(1.1).save.transact.expect {
          case ValidationErrors(errors, _) =>
            errors.head ==>
              "Type.double" -> Seq(
                s"""Type.double with value `1.1` doesn't satisfy validation:
                   |  _ > 1.1
                   |""".stripMargin
              )
        }
      } yield ()
    }

    "Card one, BigInt" - validation { implicit conn =>
      for {
        _ <- Type.bigInt(BigInt(1)).save.transact.expect {
          case ValidationErrors(errors, _) =>
            errors.head ==>
              "Type.bigInt" -> Seq(
                s"""Type.bigInt with value `1` doesn't satisfy validation:
                   |  _ > BigInt(1)
                   |""".stripMargin
              )
        }
      } yield ()
    }

    "Card one, BigDecimal" - validation { implicit conn =>
      for {
        _ <- Type.bigDecimal(BigDecimal(1.1)).save.transact.expect {
          case ValidationErrors(errors, _) =>
            errors.head ==>
              "Type.bigDecimal" -> Seq(
                s"""Type.bigDecimal with value `1.1` doesn't satisfy validation:
                   |  _ > BigDecimal(1.1)
                   |""".stripMargin
              )
        }
      } yield ()
    }

    "Card one, Date" - validation { implicit conn =>
      for {
        _ <- Type.date(new Date(993942000000L)).save.transact.expect {
          case ValidationErrors(errors, _) =>
            errors.head ==>
              "Type.date" -> Seq(
                s"""Type.date with value `$date1` doesn't satisfy validation:
                   |  _.after(new Date(993942000000L))
                   |""".stripMargin
              )
        }
      } yield ()
    }

    "Card one, UUID" - validation { implicit conn =>
      for {
        _ <- Type.uuid(UUID.fromString("aaaaaaaa-aaaa-aaaa-aaaa-aaaaaaaaaaaa")).save.transact.expect {
          case ValidationErrors(errors, _) =>
            errors.head ==>
              "Type.uuid" -> Seq(
                s"""Type.uuid with value `aaaaaaaa-aaaa-aaaa-aaaa-aaaaaaaaaaaa` doesn't satisfy validation:
                   |  _.toString != "aaaaaaaa-aaaa-aaaa-aaaa-aaaaaaaaaaaa"
                   |""".stripMargin
              )
        }
      } yield ()
    }

    "Card one, URI" - validation { implicit conn =>
      val uri = new URI("x")
      for {
        _ <- Type.uri(uri).save.transact.expect {
          case ValidationErrors(errors, _) =>
            errors.head ==>
              "Type.uri" -> Seq(
                s"""Type.uri with value `x` doesn't satisfy validation:
                   |  _.toString.length > 1
                   |""".stripMargin
              )
        }
      } yield ()
    }

    "Card one, Byte" - validation { implicit conn =>
      for {
        _ <- Type.byte(byte1).save.transact.expect {
          case ValidationErrors(errors, _) =>
            errors.head ==>
              "Type.byte" -> Seq(
                s"""Type.byte with value `$byte1` doesn't satisfy validation:
                   |  _ > $byte1
                   |""".stripMargin
              )
        }
      } yield ()
    }

    "Card one, Short" - validation { implicit conn =>
      for {
        _ <- Type.short(short1).save.transact.expect {
          case ValidationErrors(errors, _) =>
            errors.head ==>
              "Type.short" -> Seq(
                s"""Type.short with value `$short1` doesn't satisfy validation:
                   |  _ > $short1
                   |""".stripMargin
              )
        }
      } yield ()
    }

    "Card one, Char" - validation { implicit conn =>
      for {
        _ <- Type.char('a').save.transact.expect {
          case ValidationErrors(errors, _) =>
            errors.head ==>
              "Type.char" -> Seq(
                s"""Type.char with value `a` doesn't satisfy validation:
                   |  _ > 'a'
                   |""".stripMargin
              )
        }
      } yield ()
    }

    "Card one, ref" - validation { implicit conn =>
      for {
        _ <- Type.ref(1L).save.transact.expect {
          case ValidationErrors(errors, _) =>
            errors.head ==>
              "Type.ref" -> Seq(
                s"""Type.ref with value `1` doesn't satisfy validation:
                   |  _ > 1L
                   |""".stripMargin
              )
        }
      } yield ()
    }
  }
}
