// GENERATED CODE ********************************
package molecule.db.datomic.test.exprOne

import molecule.coreTests.dataModels.core.types.dsl.CardOne._
import molecule.db.datomic._
import molecule.db.datomic.setup.DatomicTestSuite
import utest._

object ExprOne_Byte_ extends DatomicTestSuite {

  lazy val tests = Tests {

    "Mandatory" - cardOne { implicit conn =>
      val a = (1, byte1)
      val b = (2, byte2)
      val c = (3, byte3)
      NsOne.n.byte.insert(List(a, b, c)).transact

      // Find all attribute values
      NsOne.n.a1.byte.query.get ==> List(a, b, c)

      // Find value(s) matching
      NsOne.n.a1.byte(byte0).query.get ==> List()
      NsOne.n.a1.byte(byte1).query.get ==> List(a)
      NsOne.n.a1.byte(Seq(byte0)).query.get ==> List()
      NsOne.n.a1.byte(Seq(byte1)).query.get ==> List(a)
      // OR semantics for multiple args
      NsOne.n.a1.byte(byte1, byte2).query.get ==> List(a, b)
      NsOne.n.a1.byte(byte1, byte0).query.get ==> List(a)
      NsOne.n.a1.byte(Seq(byte1, byte2)).query.get ==> List(a, b)
      NsOne.n.a1.byte(Seq(byte1, byte0)).query.get ==> List(a)
      // Empty Seq of args matches no values
      NsOne.n.a1.byte(Seq.empty[Byte]).query.get ==> List()

      // Find values not matching
      NsOne.n.a1.byte.not(byte0).query.get ==> List(a, b, c)
      NsOne.n.a1.byte.not(byte1).query.get ==> List(b, c)
      NsOne.n.a1.byte.not(byte2).query.get ==> List(a, c)
      NsOne.n.a1.byte.not(byte3).query.get ==> List(a, b)
      NsOne.n.a1.byte.not(Seq(byte0)).query.get ==> List(a, b, c)
      NsOne.n.a1.byte.not(Seq(byte1)).query.get ==> List(b, c)
      NsOne.n.a1.byte.not(Seq(byte2)).query.get ==> List(a, c)
      NsOne.n.a1.byte.not(Seq(byte3)).query.get ==> List(a, b)
      // OR semantics for multiple args
      NsOne.n.a1.byte.not(byte0, byte1).query.get ==> List(b, c)
      NsOne.n.a1.byte.not(byte1, byte2).query.get ==> List(c)
      NsOne.n.a1.byte.not(byte2, byte3).query.get ==> List(a)
      NsOne.n.a1.byte.not(Seq(byte0, byte1)).query.get ==> List(b, c)
      NsOne.n.a1.byte.not(Seq(byte1, byte2)).query.get ==> List(c)
      NsOne.n.a1.byte.not(Seq(byte2, byte3)).query.get ==> List(a)
      // Empty Seq of negation args matches all values
      NsOne.n.a1.byte.not(Seq.empty[Byte]).query.get ==> List(a, b, c)

      // Find values in range
      NsOne.n.a1.byte.<(byte2).query.get ==> List(a)
      NsOne.n.a1.byte.>(byte2).query.get ==> List(c)
      NsOne.n.a1.byte.<=(byte2).query.get ==> List(a, b)
      NsOne.n.a1.byte.>=(byte2).query.get ==> List(b, c)
    }


    "Tacit" - cardOne { implicit conn =>
      val (a, b, c, x) = (1, 2, 3, 4)
      NsOne.n.byte_?.insert(List(
        (a, Some(byte1)),
        (b, Some(byte2)),
        (c, Some(byte3)),
        (x, None)
      )).transact

      // Match asserted attribute without returning its value
      NsOne.n.a1.byte_.query.get ==> List(a, b, c)

      // Match non-asserted attribute (null)
      NsOne.n.a1.byte_().query.get ==> List(x)

      // Match attribute values without returning them
      NsOne.n.a1.byte_(byte0).query.get ==> List()
      NsOne.n.a1.byte_(byte1).query.get ==> List(a)
      NsOne.n.a1.byte_(Seq(byte0)).query.get ==> List()
      NsOne.n.a1.byte_(Seq(byte1)).query.get ==> List(a)
      // OR semantics for multiple args
      NsOne.n.a1.byte_(byte1, byte2).query.get ==> List(a, b)
      NsOne.n.a1.byte_(byte1, byte0).query.get ==> List(a)
      NsOne.n.a1.byte_(Seq(byte1, byte2)).query.get ==> List(a, b)
      NsOne.n.a1.byte_(Seq(byte1, byte0)).query.get ==> List(a)
      // Empty Seq of args matches no values
      NsOne.n.a1.byte_(Seq.empty[Byte]).query.get ==> List()

      // Match non-matching values without returning them
      NsOne.n.a1.byte_.not(byte0).query.get ==> List(a, b, c)
      NsOne.n.a1.byte_.not(byte1).query.get ==> List(b, c)
      NsOne.n.a1.byte_.not(byte2).query.get ==> List(a, c)
      NsOne.n.a1.byte_.not(byte3).query.get ==> List(a, b)
      NsOne.n.a1.byte_.not(Seq(byte0)).query.get ==> List(a, b, c)
      NsOne.n.a1.byte_.not(Seq(byte1)).query.get ==> List(b, c)
      NsOne.n.a1.byte_.not(Seq(byte2)).query.get ==> List(a, c)
      NsOne.n.a1.byte_.not(Seq(byte3)).query.get ==> List(a, b)
      // OR semantics for multiple args
      NsOne.n.a1.byte_.not(byte0, byte1).query.get ==> List(b, c)
      NsOne.n.a1.byte_.not(byte1, byte2).query.get ==> List(c)
      NsOne.n.a1.byte_.not(byte2, byte3).query.get ==> List(a)
      NsOne.n.a1.byte_.not(Seq(byte0, byte1)).query.get ==> List(b, c)
      NsOne.n.a1.byte_.not(Seq(byte1, byte2)).query.get ==> List(c)
      NsOne.n.a1.byte_.not(Seq(byte2, byte3)).query.get ==> List(a)
      // Empty Seq of negation args matches all asserted values (non-null)
      NsOne.n.a1.byte_.not(Seq.empty[Byte]).query.get ==> List(a, b, c)

      // Match value ranges without returning them
      NsOne.n.a1.byte_.<(byte2).query.get ==> List(a)
      NsOne.n.a1.byte_.>(byte2).query.get ==> List(c)
      NsOne.n.a1.byte_.<=(byte2).query.get ==> List(a, b)
      NsOne.n.a1.byte_.>=(byte2).query.get ==> List(b, c)
    }


    "Optional" - cardOne { implicit conn =>
      val a = (1, Some(byte1))
      val b = (2, Some(byte2))
      val c = (3, Some(byte3))
      val x = (4, Option.empty[Byte])
      NsOne.n.byte_?.insert(List(a, b, c, x)).transact

      // Find all optional attribute values
      NsOne.n.a1.byte_?.query.get ==> List(a, b, c, x)

      // Find optional values matching
      NsOne.n.a1.byte_?(Some(byte0)).query.get ==> List()
      NsOne.n.a1.byte_?(Some(byte1)).query.get ==> List(a)
      NsOne.n.a1.byte_?(Some(Seq(byte0))).query.get ==> List()
      NsOne.n.a1.byte_?(Some(Seq(byte1))).query.get ==> List(a)
      // OR semantics for Ses of multiple args
      NsOne.n.a1.byte_?(Some(Seq(byte1, byte2))).query.get ==> List(a, b)
      NsOne.n.a1.byte_?(Some(Seq(byte1, byte0))).query.get ==> List(a)
      // Empty Seq of args matches no values
      NsOne.n.a1.byte_?(Some(Seq.empty[Byte])).query.get ==> List()
      // None matches non-asserted/null values
      NsOne.n.a1.byte_?(Option.empty[Byte]).query.get ==> List(x)
      NsOne.n.a1.byte_?(Option.empty[Seq[Byte]]).query.get ==> List(x)

      // Find optional values not matching
      NsOne.n.a1.byte_?.not(Some(byte0)).query.get ==> List(a, b, c)
      NsOne.n.a1.byte_?.not(Some(byte1)).query.get ==> List(b, c)
      NsOne.n.a1.byte_?.not(Some(byte2)).query.get ==> List(a, c)
      NsOne.n.a1.byte_?.not(Some(byte3)).query.get ==> List(a, b)
      NsOne.n.a1.byte_?.not(Some(Seq(byte0))).query.get ==> List(a, b, c)
      NsOne.n.a1.byte_?.not(Some(Seq(byte1))).query.get ==> List(b, c)
      NsOne.n.a1.byte_?.not(Some(Seq(byte2))).query.get ==> List(a, c)
      NsOne.n.a1.byte_?.not(Some(Seq(byte3))).query.get ==> List(a, b)
      // OR semantics for multiple negation args
      NsOne.n.a1.byte_?.not(Some(Seq(byte0, byte1))).query.get ==> List(b, c)
      NsOne.n.a1.byte_?.not(Some(Seq(byte1, byte2))).query.get ==> List(c)
      NsOne.n.a1.byte_?.not(Some(Seq(byte2, byte3))).query.get ==> List(a)
      // Empty Seq of negation args matches all asserted values (non-null)
      NsOne.n.a1.byte_?.not(Some(Seq.empty[Byte])).query.get ==> List(a, b, c)
      // None matches all asserted values (non-null)
      NsOne.n.a1.byte_?.not(Option.empty[Byte]).query.get ==> List(a, b, c)
      NsOne.n.a1.byte_?.not(Option.empty[Seq[Byte]]).query.get ==> List(a, b, c)

      // Find optional values in range
      NsOne.n.a1.byte_?.<(Some(byte2)).query.get ==> List(a)
      NsOne.n.a1.byte_?.>(Some(byte2)).query.get ==> List(c)
      NsOne.n.a1.byte_?.<=(Some(byte2)).query.get ==> List(a, b)
      NsOne.n.a1.byte_?.>=(Some(byte2)).query.get ==> List(b, c)
      // None can't be compared and returns empty result
      NsOne.n.a1.byte_?.<(None).query.get ==> List()
      NsOne.n.a1.byte_?.<=(None).query.get ==> List()
      NsOne.n.a1.byte_?.>(None).query.get ==> List()
      NsOne.n.a1.byte_?.>=(None).query.get ==> List()
    }
  }
}
