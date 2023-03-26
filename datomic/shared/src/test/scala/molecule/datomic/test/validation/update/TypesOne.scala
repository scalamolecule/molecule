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
        eid <- Type.string("b").save.transact.map(_.eids.head)

        _ <- Type(eid).string("a").update.transact.expect {
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

        // Focusing on error message only
        _ <- Type(eid).string("a").update.transact.expect {
          case ValidationErrors(errorMap, _) =>
            errorMap.head._2.head ==>
              s"""Type.string with value `a` doesn't satisfy validation:
                 |  _ > "a"
                 |""".stripMargin
        }

        // Value hasn't changed
        _ <- Type.string.query.get.map(_ ==> List("b"))
      } yield ()
    }

    "Int" - validation { implicit conn =>
      for {
        eid <- Type.int(2).save.transact.map(_.eids.head)
        _ <- Type(eid).int(1).update.transact.expect {
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
        eid <- Type.long(2L).save.transact.map(_.eids.head)
        _ <- Type(eid).long(1L).update.transact.expect {
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
        eid <- Type.float(float2).save.transact.map(_.eids.head)
        _ <- Type(eid).float(float1).update.transact.expect {
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
        eid <- Type.double(double2).save.transact.map(_.eids.head)
        _ <- Type(eid).double(double1).update.transact.expect {
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
        eid <- Type.boolean(false).save.transact.map(_.eids.head)
        _ <- Type(eid).boolean(true).update.transact.expect {
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
        eid <- Type.bigInt(bigInt2).save.transact.map(_.eids.head)
        _ <- Type(eid).bigInt(bigInt1).update.transact.expect {
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
        eid <- Type.bigDecimal(bigDecimal2).save.transact.map(_.eids.head)
        _ <- Type(eid).bigDecimal(bigDecimal1).update.transact.expect {
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
        eid <- Type.date(date2).save.transact.map(_.eids.head)
        _ <- Type(eid).date(date1).update.transact.expect {
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
        eid <- Type.uuid(UUID.fromString("aaaaaaaa-aaaa-aaaa-aaaa-bbbbbbbbbbbb")).save.transact.map(_.eids.head)
        _ <- Type(eid).uuid(UUID.fromString("aaaaaaaa-aaaa-aaaa-aaaa-aaaaaaaaaaaa")).update.transact.expect {
          case ValidationErrors(errorMap, _) =>
            errorMap.head._2.head ==>
              s"""Type.uuid with value `aaaaaaaa-aaaa-aaaa-aaaa-aaaaaaaaaaaa` doesn't satisfy validation:
                 |  _.toString != "aaaaaaaa-aaaa-aaaa-aaaa-aaaaaaaaaaaa"
                 |""".stripMargin
        }
      } yield ()
    }

    "URI" - validation { implicit conn =>
      val uri1 = new URI("x")
      val uri2 = new URI("xy")
      for {
        eid <- Type.uri(uri2).save.transact.map(_.eids.head)
        _ <- Type(eid).uri(uri1).update.transact.expect {
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
        eid <- Type.byte(byte2).save.transact.map(_.eids.head)
        _ <- Type(eid).byte(byte1).update.transact.expect {
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
        eid <- Type.short(short2).save.transact.map(_.eids.head)
        _ <- Type(eid).short(short1).update.transact.expect {
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
        eid <- Type.char('b').save.transact.map(_.eids.head)
        _ <- Type(eid).char('a').update.transact.expect {
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
        eid <- Type.ref(2L).save.transact.map(_.eids.head)
        _ <- Type(eid).ref(1L).update.transact.expect {
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
