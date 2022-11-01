// GENERATED CODE ********************************
package molecule.db.datomic.test.expr

import molecule.boilerplate.api.Keywords._
import molecule.coreTests.dataModels.core.types.dsl.CardOne._
import molecule.db.datomic._
import molecule.db.datomic.setup.DatomicTestSuite
import utest._

object Expr_Byte_ extends DatomicTestSuite {

  lazy val tests = Tests {

    "Mandatory" - cardOne { implicit conn =>
      val a = (1, byte1)
      val b = (2, byte2)
      val c = (3, byte3)
      One.n.byte.insert(List(a, b, c)).transact

      // Apply args to find matching values
      One.n.a1.byte(byte0).query.get ==> List()
      One.n.a1.byte(byte1).query.get ==> List(a)
      One.n.a1.byte(byte1, byte2).query.get ==> List(a, b)
      One.n.a1.byte(byte1, byte0).query.get ==> List(a)
      // Arguments as Seq
      One.n.a1.byte(Seq(byte0)).query.get ==> List()
      One.n.a1.byte(Seq(byte1)).query.get ==> List(a)
      One.n.a1.byte(Seq(byte1, byte2)).query.get ==> List(a, b)
      One.n.a1.byte(Seq(byte1, byte0)).query.get ==> List(a)
      One.n.a1.byte(Seq.empty[Byte]).query.get ==> List()


      One.n.a1.byte.not(byte0).query.get ==> List(a, b, c)
      One.n.a1.byte.not(byte1).query.get ==> List(b, c)
      One.n.a1.byte.not(byte2).query.get ==> List(a, c)
      One.n.a1.byte.not(byte3).query.get ==> List(a, b)
      One.n.a1.byte.not(byte0, byte1).query.get ==> List(b, c)
      One.n.a1.byte.not(byte1, byte2).query.get ==> List(c)
      One.n.a1.byte.not(byte2, byte3).query.get ==> List(a)
      // Arguments as Seq
      One.n.a1.byte.not(Seq(byte0)).query.get ==> List(a, b, c)
      One.n.a1.byte.not(Seq(byte1)).query.get ==> List(b, c)
      One.n.a1.byte.not(Seq(byte2)).query.get ==> List(a, c)
      One.n.a1.byte.not(Seq(byte3)).query.get ==> List(a, b)
      One.n.a1.byte.not(Seq(byte0, byte1)).query.get ==> List(b, c)
      One.n.a1.byte.not(Seq(byte1, byte2)).query.get ==> List(c)
      One.n.a1.byte.not(Seq(byte2, byte3)).query.get ==> List(a)
      One.n.a1.byte.not(Seq.empty[Byte]).query.get ==> List(a, b, c)


      One.n.a1.byte.<(byte2).query.get ==> List(a)
      One.n.a1.byte.>(byte2).query.get ==> List(c)
      One.n.a1.byte.<=(byte2).query.get ==> List(a, b)
      One.n.a1.byte.>=(byte2).query.get ==> List(b, c)
    }


    "Tacit" - cardOne { implicit conn =>
      One.n.byte_?.insert(List(
        (1, Some(byte1)),
        (2, Some(byte2)),
        (3, Some(byte3)),
        (4, None)
      )).transact

      val a = 1
      val b = 2
      val c = 3
      val d = 4

      // No value asserted
      One.n.a1.byte_.apply().query.get ==> List(d)

      One.n.a1.byte_(byte0).query.get ==> List()
      One.n.a1.byte_.apply(byte1).query.get ==> List(a)
      One.n.a1.byte_(byte1, byte2).query.get ==> List(a, b)
      One.n.a1.byte_(byte1, byte0).query.get ==> List(a)
      // Same as
      One.n.a1.byte_(Seq(byte0)).query.get ==> List()
      One.n.a1.byte_(Seq(byte1)).query.get ==> List(a)
      One.n.a1.byte_(Seq(byte1, byte2)).query.get ==> List(a, b)
      One.n.a1.byte_(Seq(byte1, byte0)).query.get ==> List(a)
      One.n.a1.byte_(Seq.empty[Byte]).query.get ==> List()


      One.n.a1.byte_.not(byte0).query.get ==> List(a, b, c)
      One.n.a1.byte_.not(byte1).query.get ==> List(b, c)
      One.n.a1.byte_.not(byte2).query.get ==> List(a, c)
      One.n.a1.byte_.not(byte3).query.get ==> List(a, b)
      One.n.a1.byte_.not(byte0, byte1).query.get ==> List(b, c)
      One.n.a1.byte_.not(byte1, byte2).query.get ==> List(c)
      One.n.a1.byte_.not(byte2, byte3).query.get ==> List(a)
      // Same as
      One.n.a1.byte_.not(Seq(byte0)).query.get ==> List(a, b, c)
      One.n.a1.byte_.not(Seq(byte1)).query.get ==> List(b, c)
      One.n.a1.byte_.not(Seq(byte2)).query.get ==> List(a, c)
      One.n.a1.byte_.not(Seq(byte3)).query.get ==> List(a, b)
      One.n.a1.byte_.not(Seq(byte0, byte1)).query.get ==> List(b, c)
      One.n.a1.byte_.not(Seq(byte1, byte2)).query.get ==> List(c)
      One.n.a1.byte_.not(Seq(byte2, byte3)).query.get ==> List(a)
      One.n.a1.byte_.not(Seq.empty[Byte]).query.get ==> List(a, b, c)


      One.n.a1.byte_.<(byte2).query.get ==> List(a)
      One.n.a1.byte_.>(byte2).query.get ==> List(c)
      One.n.a1.byte_.<=(byte2).query.get ==> List(a, b)
      One.n.a1.byte_.>=(byte2).query.get ==> List(b, c)
    }


    "Optional" - cardOne { implicit conn =>
      val a = (1, Some(byte1))
      val b = (2, Some(byte2))
      val c = (3, Some(byte3))
      val x = (4, Option.empty[Byte])

      One.n.byte_?.insert(List(a, b, c, x)).transact

      One.n.a1.byte_?(Some(byte0)).query.get ==> List()
      One.n.a1.byte_?.apply(Some(byte1)).query.get ==> List(a)

      One.n.a1.byte_?(Some(Seq(byte0))).query.get ==> List()
      One.n.a1.byte_?(Some(Seq(byte1))).query.get ==> List(a)
      One.n.a1.byte_?(Some(Seq(byte1, byte2))).query.get ==> List(a, b)
      One.n.a1.byte_?(Some(Seq(byte1, byte0))).query.get ==> List(a)
      One.n.a1.byte_?(Some(Seq.empty[Byte])).query.get ==> List()

      One.n.a1.byte_?(Option.empty[Byte]).query.get ==> List(x)
      One.n.a1.byte_?(Option.empty[Seq[Byte]]).query.get ==> List(x)


      // For non-String cardinality-one attributes, `not` and `!=` have same semantics
      // "Not this value"
      One.n.a1.byte_?.not(Some(byte0)).query.get ==> List(a, b, c)
      One.n.a1.byte_?.not(Some(byte1)).query.get ==> List(b, c)
      One.n.a1.byte_?.not(Some(byte2)).query.get ==> List(a, c)
      One.n.a1.byte_?.not(Some(byte3)).query.get ==> List(a, b)
      // Same as
      One.n.a1.byte_?.not(Some(Seq(byte0))).query.get ==> List(a, b, c)
      One.n.a1.byte_?.not(Some(Seq(byte1))).query.get ==> List(b, c)
      One.n.a1.byte_?.not(Some(Seq(byte2))).query.get ==> List(a, c)
      One.n.a1.byte_?.not(Some(Seq(byte3))).query.get ==> List(a, b)

      // "Not this OR that value
      One.n.a1.byte_?.not(Some(Seq(byte0, byte1))).query.get ==> List(b, c)
      One.n.a1.byte_?.not(Some(Seq(byte1, byte2))).query.get ==> List(c)
      One.n.a1.byte_?.not(Some(Seq(byte2, byte3))).query.get ==> List(a)
      One.n.a1.byte_?.not(Some(Seq.empty[Byte])).query.get ==> List(a, b, c)

      One.n.a1.byte_?.not(Option.empty[Byte]).query.get ==> List(a, b, c)
      One.n.a1.byte_?.not(Option.empty[Seq[Byte]]).query.get ==> List(a, b, c)


      One.n.a1.byte_?.<(Some(byte2)).query.get ==> List(a)
      One.n.a1.byte_?.>(Some(byte2)).query.get ==> List(c)
      One.n.a1.byte_?.<=(Some(byte2)).query.get ==> List(a, b)
      One.n.a1.byte_?.>=(Some(byte2)).query.get ==> List(b, c)

      // None can't be compared
      One.n.a1.byte_?.<(None).query.get ==> List()
      One.n.a1.byte_?.<=(None).query.get ==> List()
      One.n.a1.byte_?.>(None).query.get ==> List()
      One.n.a1.byte_?.>=(None).query.get ==> List()
    }
  }
}
