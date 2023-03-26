package molecule.datomic.test.validation.update

import java.net.URI
import java.util.{Date, UUID}
import molecule.base.error.ValidationErrors
import molecule.core.util.Executor._
import molecule.coreTests.dataModels.core.dsl.Validation._
import molecule.datomic.async._
import molecule.datomic.setup.DatomicTestSuite
import molecule.datomic.test.validation.save.TypesSet.{byte0, byte1, date0, date1, short0, short1}
import utest._
import scala.language.implicitConversions

object TypesSet extends DatomicTestSuite {

  override lazy val tests = Tests {

    "String" - validation { implicit conn =>
      for {
        eid <- Type.strings(Set("b")).save.transact.map(_.eids.head)

        _ <- Type(eid).strings(Set("-", "a", "b")).update.transact.expect {
          case ValidationErrors(errorMap, _) =>
            errorMap ==>
              Map(
                "Type.strings" -> Seq(
                  s"""Type.strings with value `-` doesn't satisfy validation:
                     |  _ > "a"
                     |""".stripMargin,
                  s"""Type.strings with value `a` doesn't satisfy validation:
                     |  _ > "a"
                     |""".stripMargin
                  // (value b is ok)
                )
              )
        }

        // Focusing on error message only
        _ <- Type(eid).strings(Set("-", "a", "b")).update.transact.expect {
          case ValidationErrors(errorMap, _) =>
            errorMap.head._2 ==> Seq(
              s"""Type.strings with value `-` doesn't satisfy validation:
                 |  _ > "a"
                 |""".stripMargin,
              s"""Type.strings with value `a` doesn't satisfy validation:
                 |  _ > "a"
                 |""".stripMargin
              // (value b is ok)
            )
        }

        // Value hasn't changed
        _ <- Type.strings.query.get.map(_ ==> List(Set("b")))
      } yield ()
    }

    "Int" - validation { implicit conn =>
      for {
        eid <- Type.ints(Set(2)).save.transact.map(_.eids.head)
        _ <- Type(eid).ints(Set(0, 1, 2)).update.transact.expect {
          case ValidationErrors(errorMap, _) =>
            errorMap.head._2 ==> Seq(
              s"""Type.ints with value `0` doesn't satisfy validation:
                 |  _ > 1
                 |""".stripMargin,
              s"""Type.ints with value `1` doesn't satisfy validation:
                 |  _ > 1
                 |""".stripMargin
            )
        }
      } yield ()
    }

    "Long" - validation { implicit conn =>
      for {
        eid <- Type.longs(Set(2L)).save.transact.map(_.eids.head)
        _ <- Type(eid).longs(Set(0L, 1L, 2L)).update.transact.expect {
          case ValidationErrors(errorMap, _) =>
            errorMap.head._2 ==> Seq(
              s"""Type.longs with value `0` doesn't satisfy validation:
                 |  _ > 1L
                 |""".stripMargin,
              s"""Type.longs with value `1` doesn't satisfy validation:
                 |  _ > 1L
                 |""".stripMargin
            )
        }
      } yield ()
    }

    "Float" - validation { implicit conn =>
      for {
        eid <- Type.floats(Set(float2)).save.transact.map(_.eids.head)
        _ <- Type(eid).floats(Set(float0, float1, float2)).update.transact.expect {
          case ValidationErrors(errorMap, _) =>
            errorMap.head._2 ==> Seq(
              s"""Type.floats with value `0.0` doesn't satisfy validation:
                 |  _ > 1.1f
                 |""".stripMargin,
              s"""Type.floats with value `1.1` doesn't satisfy validation:
                 |  _ > 1.1f
                 |""".stripMargin
            )
        }
      } yield ()
    }

    "Double" - validation { implicit conn =>
      for {
        eid <- Type.double(double2).save.transact.map(_.eids.head)
        _ <- Type(eid).doubles(Set(double0, double1, double2)).update.transact.expect {
          case ValidationErrors(errorMap, _) =>
            errorMap.head._2 ==> Seq(
              s"""Type.doubles with value `0.0` doesn't satisfy validation:
                 |  _ > 1.1
                 |""".stripMargin,
              s"""Type.doubles with value `1.1` doesn't satisfy validation:
                 |  _ > 1.1
                 |""".stripMargin
            )
        }
      } yield ()
    }

    "Boolean" - validation { implicit conn =>
      for {
        eid <- Type.boolean(false).save.transact.map(_.eids.head)
        _ <- Type(eid).booleans(Set(true, false)).update.transact.expect {
          case ValidationErrors(errorMap, _) =>
            errorMap.head._2 ==> Seq(
              s"""Type.booleans with value `true` doesn't satisfy validation:
                 |  _ == false
                 |""".stripMargin
            )
        }
      } yield ()
    }

    "BigInt" - validation { implicit conn =>
      for {
        eid <- Type.bigInts(Set(bigInt2)).save.transact.map(_.eids.head)
        _ <- Type(eid).bigInts(Set(bigInt0, bigInt1, bigInt2)).update.transact.expect {
          case ValidationErrors(errorMap, _) =>
            errorMap.head._2 ==> Seq(
              s"""Type.bigInts with value `0` doesn't satisfy validation:
                 |  _ > BigInt(1)
                 |""".stripMargin,
              s"""Type.bigInts with value `1` doesn't satisfy validation:
                 |  _ > BigInt(1)
                 |""".stripMargin
            )
        }
      } yield ()
    }

    "BigDecimal" - validation { implicit conn =>
      for {
        eid <- Type.bigDecimals(bigDecimal2).save.transact.map(_.eids.head)
        _ <- Type(eid).bigDecimals(Set(bigDecimal0, bigDecimal1, bigDecimal2)).update.transact.expect {
          case ValidationErrors(errorMap, _) =>
            errorMap.head._2 ==> Seq(
              s"""Type.bigDecimals with value `0.0` doesn't satisfy validation:
                 |  _ > BigDecimal(1.1)
                 |""".stripMargin,
              s"""Type.bigDecimals with value `1.1` doesn't satisfy validation:
                 |  _ > BigDecimal(1.1)
                 |""".stripMargin
            )
        }
      } yield ()
    }

    "Date" - validation { implicit conn =>
      for {
        eid <- Type.dates(date2).save.transact.map(_.eids.head)
        _ <- Type(eid).dates(Set(date0, date1, date2)).update.transact.expect {
          case ValidationErrors(errorMap, _) =>
            errorMap.head._2 ==> Seq(
              s"""Type.dates with value `$date0` doesn't satisfy validation:
                 |  _.after(new Date(993942000000L))
                 |""".stripMargin,
              s"""Type.dates with value `$date1` doesn't satisfy validation:
                 |  _.after(new Date(993942000000L))
                 |""".stripMargin
            )
        }
      } yield ()
    }

    "UUID" - validation { implicit conn =>
      for {
        eid <- Type.uuids(Set(UUID.fromString("aaaaaaaa-aaaa-aaaa-aaaa-bbbbbbbbbbbb"))).save.transact.map(_.eids.head)
        _ <- Type(eid).uuids(Set(UUID.fromString("aaaaaaaa-aaaa-aaaa-aaaa-aaaaaaaaaaaa"))).update.transact.expect {
          case ValidationErrors(errorMap, _) =>
            errorMap.head._2 ==> Seq(
              s"""Type.uuids with value `aaaaaaaa-aaaa-aaaa-aaaa-aaaaaaaaaaaa` doesn't satisfy validation:
                 |  _.toString != "aaaaaaaa-aaaa-aaaa-aaaa-aaaaaaaaaaaa"
                 |""".stripMargin
            )
        }
      } yield ()
    }

    "URI" - validation { implicit conn =>
      val uri0 = new URI("x")
      val uri1 = new URI("y")
      val uri2 = new URI("xy")
      for {
        eid <- Type.uris(Set(uri2)).save.transact.map(_.eids.head)
        _ <- Type(eid).uris(Set(uri0, uri1, uri2)).update.transact.expect {
          case ValidationErrors(errorMap, _) =>
            errorMap.head._2 ==> Seq(
              s"""Type.uris with value `x` doesn't satisfy validation:
                 |  _.toString.length > 1
                 |""".stripMargin,
              s"""Type.uris with value `y` doesn't satisfy validation:
                 |  _.toString.length > 1
                 |""".stripMargin
            )
        }
      } yield ()
    }

    "Byte" - validation { implicit conn =>
      for {
        eid <- Type.bytes(Set(byte2)).save.transact.map(_.eids.head)
        _ <- Type(eid).bytes(Set(byte0, byte1, byte2)).update.transact.expect {
          case ValidationErrors(errorMap, _) =>
            errorMap.head._2 ==> Seq(
              s"""Type.bytes with value `$byte0` doesn't satisfy validation:
                 |  _ > $byte1
                 |""".stripMargin,
              s"""Type.bytes with value `$byte1` doesn't satisfy validation:
                 |  _ > $byte1
                 |""".stripMargin
            )
        }
      } yield ()
    }

    "Short" - validation { implicit conn =>
      for {
        eid <- Type.shorts(Set(short2)).save.transact.map(_.eids.head)
        _ <- Type(eid).shorts(Set(short0, short1, short2)).update.transact.expect {
          case ValidationErrors(errorMap, _) =>
            errorMap.head._2 ==> Seq(
              s"""Type.shorts with value `$short0` doesn't satisfy validation:
                 |  _ > $short1
                 |""".stripMargin,
              s"""Type.shorts with value `$short1` doesn't satisfy validation:
                 |  _ > $short1
                 |""".stripMargin
            )
        }
      } yield ()
    }

    "Char" - validation { implicit conn =>
      for {
        eid <- Type.chars(Set('b')).save.transact.map(_.eids.head)
        _ <- Type(eid).chars(Set('-', 'a', 'b')).update.transact.expect {
          case ValidationErrors(errorMap, _) =>
            errorMap.head._2 ==> Seq(
              s"""Type.chars with value `-` doesn't satisfy validation:
                 |  _ > 'a'
                 |""".stripMargin,
              s"""Type.chars with value `a` doesn't satisfy validation:
                 |  _ > 'a'
                 |""".stripMargin
            )
        }
      } yield ()
    }

    "ref" - validation { implicit conn =>
      for {
        eid <- Type.refs(Set(2L)).save.transact.map(_.eids.head)
        _ <- Type(eid).refs(Set(0L, 1L, 2L)).update.transact.expect {
          case ValidationErrors(errorMap, _) =>
            errorMap.head._2 ==> Seq(
              s"""Type.refs with value `0` doesn't satisfy validation:
                 |  _ > 1L
                 |""".stripMargin,
              s"""Type.refs with value `1` doesn't satisfy validation:
                 |  _ > 1L
                 |""".stripMargin
            )
        }
      } yield ()
    }
  }
}