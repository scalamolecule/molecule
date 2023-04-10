package molecule.datomic.test.validation.update

import java.net.URI
import java.util.{Date, UUID}
import molecule.base.error.ValidationErrors
import molecule.core.util.Executor._
import molecule.coreTests.dataModels.core.dsl.Types.Ns
import molecule.coreTests.dataModels.core.dsl.Validation._
import molecule.datomic.async._
import molecule.datomic.setup.DatomicTestSuite
import utest._
import scala.language.implicitConversions

object TypesOne extends DatomicTestSuite {

  override lazy val tests = Tests {

    "String" - validation { implicit conn =>
      for {
        eid <- Type.string("c").save.transact.map(_.eids.head)

        _ <- Type(eid).string("a").update.transact
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

        // Focusing on error message only
        _ <- Type(eid).string("a").update.transact
          .map(_ ==> "Unexpected success").recover {
          case ValidationErrors(errorMap) =>
            errorMap.head._2.head ==>
              s"""Type.string with value `a` doesn't satisfy validation:
                 |  _ > "b"
                 |""".stripMargin
        }

        // Value hasn't changed
        _ <- Type.string.query.get.map(_ ==> List("c"))
      } yield ()
    }

    "Int" - validation { implicit conn =>
      for {
        eid <- Type.int(3).save.transact.map(_.eids.head)
        _ <- Type(eid).int(1).update.transact
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
        eid <- Type.long(3L).save.transact.map(_.eids.head)
        _ <- Type(eid).long(1L).update.transact
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
        eid <- Type.float(float3).save.transact.map(_.eids.head)
        _ <- Type(eid).float(float1).update.transact
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
        eid <- Type.double(double3).save.transact.map(_.eids.head)
        _ <- Type(eid).double(double1).update.transact
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
        eid <- Type.boolean(false).save.transact.map(_.eids.head)
        _ <- Type(eid).boolean(true).update.transact
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
        eid <- Type.bigInt(bigInt3).save.transact.map(_.eids.head)
        _ <- Type(eid).bigInt(bigInt1).update.transact
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
        eid <- Type.bigDecimal(bigDecimal3).save.transact.map(_.eids.head)
        _ <- Type(eid).bigDecimal(bigDecimal1).update.transact
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
        eid <- Type.date(date3).save.transact.map(_.eids.head)
        _ <- Type(eid).date(date1).update.transact
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
        eid <- Type.uuid(UUID.fromString("aaaaaaaa-aaaa-aaaa-aaaa-cccccccccccc")).save.transact.map(_.eids.head)
        _ <- Type(eid).uuid(UUID.fromString("aaaaaaaa-aaaa-aaaa-aaaa-aaaaaaaaaaaa")).update.transact
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
      val uri1 = new URI("a")
      val uri4 = new URI("abcd")
      for {
        eid <- Type.uri(uri4).save.transact.map(_.eids.head)
        _ <- Type(eid).uri(uri1).update.transact
          .map(_ ==> "Unexpected success").recover {
          case ValidationErrors(errorMap) =>
            errorMap.head._2.head ==>
              s"""Type.uri with value `a` doesn't satisfy validation:
                 |  _.toString.length > 2
                 |""".stripMargin
        }
      } yield ()
    }

    "Byte" - validation { implicit conn =>
      for {
        eid <- Type.byte(byte3).save.transact.map(_.eids.head)
        _ <- Type(eid).byte(byte1).update.transact
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
        eid <- Type.short(short3).save.transact.map(_.eids.head)
        _ <- Type(eid).short(short1).update.transact
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
        eid <- Type.char('c').save.transact.map(_.eids.head)
        _ <- Type(eid).char('a').update.transact
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
        eid <- Type.ref(3L).save.transact.map(_.eids.head)
        _ <- Type(eid).ref(1L).update.transact
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
