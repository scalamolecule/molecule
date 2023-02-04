// GENERATED CODE ********************************
package molecule.db.datomic.test.expr.one

import molecule.core.util.Executor._
import molecule.coreTests.dataModels.core.dsl.Types._
import molecule.db.datomic._
import molecule.db.datomic.setup.DatomicTestSuite
import utest._

object ExprOne_Byte_ extends DatomicTestSuite {


  lazy val tests = Tests {

    "Mandatory" - types { implicit conn =>
      val a = (1, byte1)
      val b = (2, byte2)
      val c = (3, byte3)
      for {
        _ <- Ns.i.byte.insert(List(a, b, c)).transact

        // Find all attribute values
        _ <- Ns.i.a1.byte.query.get.map(_ ==> List(a, b, c))

        // Find value(s) matching
        _ <- Ns.i.a1.byte(byte0).query.get.map(_ ==> List())
        _ <- Ns.i.a1.byte(byte1).query.get.map(_ ==> List(a))
        _ <- Ns.i.a1.byte(Seq(byte0)).query.get.map(_ ==> List())
        _ <- Ns.i.a1.byte(Seq(byte1)).query.get.map(_ ==> List(a))
        // OR semantics for multiple args
        _ <- Ns.i.a1.byte(byte1, byte2).query.get.map(_ ==> List(a, b))
        _ <- Ns.i.a1.byte(byte1, byte0).query.get.map(_ ==> List(a))
        _ <- Ns.i.a1.byte(Seq(byte1, byte2)).query.get.map(_ ==> List(a, b))
        _ <- Ns.i.a1.byte(Seq(byte1, byte0)).query.get.map(_ ==> List(a))
        // Empty Seq of args matches no values
        _ <- Ns.i.a1.byte(Seq.empty[Byte]).query.get.map(_ ==> List())

        // Find values not matching
        _ <- Ns.i.a1.byte.not(byte0).query.get.map(_ ==> List(a, b, c))
        _ <- Ns.i.a1.byte.not(byte1).query.get.map(_ ==> List(b, c))
        _ <- Ns.i.a1.byte.not(byte2).query.get.map(_ ==> List(a, c))
        _ <- Ns.i.a1.byte.not(byte3).query.get.map(_ ==> List(a, b))
        _ <- Ns.i.a1.byte.not(Seq(byte0)).query.get.map(_ ==> List(a, b, c))
        _ <- Ns.i.a1.byte.not(Seq(byte1)).query.get.map(_ ==> List(b, c))
        _ <- Ns.i.a1.byte.not(Seq(byte2)).query.get.map(_ ==> List(a, c))
        _ <- Ns.i.a1.byte.not(Seq(byte3)).query.get.map(_ ==> List(a, b))
        // OR semantics for multiple args
        _ <- Ns.i.a1.byte.not(byte0, byte1).query.get.map(_ ==> List(b, c))
        _ <- Ns.i.a1.byte.not(byte1, byte2).query.get.map(_ ==> List(c))
        _ <- Ns.i.a1.byte.not(byte2, byte3).query.get.map(_ ==> List(a))
        _ <- Ns.i.a1.byte.not(Seq(byte0, byte1)).query.get.map(_ ==> List(b, c))
        _ <- Ns.i.a1.byte.not(Seq(byte1, byte2)).query.get.map(_ ==> List(c))
        _ <- Ns.i.a1.byte.not(Seq(byte2, byte3)).query.get.map(_ ==> List(a))
        // Empty Seq of negation args matches all values
        _ <- Ns.i.a1.byte.not(Seq.empty[Byte]).query.get.map(_ ==> List(a, b, c))

        // Find values in range
        _ <- Ns.i.a1.byte.<(byte2).query.get.map(_ ==> List(a))
        _ <- Ns.i.a1.byte.>(byte2).query.get.map(_ ==> List(c))
        _ <- Ns.i.a1.byte.<=(byte2).query.get.map(_ ==> List(a, b))
        _ <- Ns.i.a1.byte.>=(byte2).query.get.map(_ ==> List(b, c))
      } yield ()
    }


    "Tacit" - types { implicit conn =>
      val (a, b, c, x) = (1, 2, 3, 4)
      for {
        _ <- Ns.i.byte_?.insert(List(
          (a, Some(byte1)),
          (b, Some(byte2)),
          (c, Some(byte3)),
          (x, None)
        )).transact

        // Match asserted attribute without returning its value
        _ <- Ns.i.a1.byte_.query.get.map(_ ==> List(a, b, c))

        // Match non-asserted attribute (null)
        _ <- Ns.i.a1.byte_().query.get.map(_ ==> List(x))

        // Match attribute values without returning them
        _ <- Ns.i.a1.byte_(byte0).query.get.map(_ ==> List())
        _ <- Ns.i.a1.byte_(byte1).query.get.map(_ ==> List(a))
        _ <- Ns.i.a1.byte_(Seq(byte0)).query.get.map(_ ==> List())
        _ <- Ns.i.a1.byte_(Seq(byte1)).query.get.map(_ ==> List(a))
        // OR semantics for multiple args
        _ <- Ns.i.a1.byte_(byte1, byte2).query.get.map(_ ==> List(a, b))
        _ <- Ns.i.a1.byte_(byte1, byte0).query.get.map(_ ==> List(a))
        _ <- Ns.i.a1.byte_(Seq(byte1, byte2)).query.get.map(_ ==> List(a, b))
        _ <- Ns.i.a1.byte_(Seq(byte1, byte0)).query.get.map(_ ==> List(a))
        // Empty Seq of args matches no values
        _ <- Ns.i.a1.byte_(Seq.empty[Byte]).query.get.map(_ ==> List())

        // Match non-matching values without returning them
        _ <- Ns.i.a1.byte_.not(byte0).query.get.map(_ ==> List(a, b, c))
        _ <- Ns.i.a1.byte_.not(byte1).query.get.map(_ ==> List(b, c))
        _ <- Ns.i.a1.byte_.not(byte2).query.get.map(_ ==> List(a, c))
        _ <- Ns.i.a1.byte_.not(byte3).query.get.map(_ ==> List(a, b))
        _ <- Ns.i.a1.byte_.not(Seq(byte0)).query.get.map(_ ==> List(a, b, c))
        _ <- Ns.i.a1.byte_.not(Seq(byte1)).query.get.map(_ ==> List(b, c))
        _ <- Ns.i.a1.byte_.not(Seq(byte2)).query.get.map(_ ==> List(a, c))
        _ <- Ns.i.a1.byte_.not(Seq(byte3)).query.get.map(_ ==> List(a, b))
        // OR semantics for multiple args
        _ <- Ns.i.a1.byte_.not(byte0, byte1).query.get.map(_ ==> List(b, c))
        _ <- Ns.i.a1.byte_.not(byte1, byte2).query.get.map(_ ==> List(c))
        _ <- Ns.i.a1.byte_.not(byte2, byte3).query.get.map(_ ==> List(a))
        _ <- Ns.i.a1.byte_.not(Seq(byte0, byte1)).query.get.map(_ ==> List(b, c))
        _ <- Ns.i.a1.byte_.not(Seq(byte1, byte2)).query.get.map(_ ==> List(c))
        _ <- Ns.i.a1.byte_.not(Seq(byte2, byte3)).query.get.map(_ ==> List(a))
        // Empty Seq of negation args matches all asserted values (non-null)
        _ <- Ns.i.a1.byte_.not(Seq.empty[Byte]).query.get.map(_ ==> List(a, b, c))

        // Match value ranges without returning them
        _ <- Ns.i.a1.byte_.<(byte2).query.get.map(_ ==> List(a))
        _ <- Ns.i.a1.byte_.>(byte2).query.get.map(_ ==> List(c))
        _ <- Ns.i.a1.byte_.<=(byte2).query.get.map(_ ==> List(a, b))
        _ <- Ns.i.a1.byte_.>=(byte2).query.get.map(_ ==> List(b, c))
      } yield ()
    }


    "Optional" - types { implicit conn =>
      val a = (1, Some(byte1))
      val b = (2, Some(byte2))
      val c = (3, Some(byte3))
      val x = (4, Option.empty[Byte])
      for {
        _ <- Ns.i.byte_?.insert(List(a, b, c, x)).transact

        // Find all optional attribute values
        _ <- Ns.i.a1.byte_?.query.get.map(_ ==> List(a, b, c, x))

        // Find optional values matching
        _ <- Ns.i.a1.byte_?(Some(byte0)).query.get.map(_ ==> List())
        _ <- Ns.i.a1.byte_?(Some(byte1)).query.get.map(_ ==> List(a))
        _ <- Ns.i.a1.byte_?(Some(Seq(byte0))).query.get.map(_ ==> List())
        _ <- Ns.i.a1.byte_?(Some(Seq(byte1))).query.get.map(_ ==> List(a))
        // OR semantics for Ses of multiple args
        _ <- Ns.i.a1.byte_?(Some(Seq(byte1, byte2))).query.get.map(_ ==> List(a, b))
        _ <- Ns.i.a1.byte_?(Some(Seq(byte1, byte0))).query.get.map(_ ==> List(a))
        // Empty Seq of args matches no values
        _ <- Ns.i.a1.byte_?(Some(Seq.empty[Byte])).query.get.map(_ ==> List())
        // None matches non-asserted/null values
        _ <- Ns.i.a1.byte_?(Option.empty[Byte]).query.get.map(_ ==> List(x))
        _ <- Ns.i.a1.byte_?(Option.empty[Seq[Byte]]).query.get.map(_ ==> List(x))

        // Find optional values not matching
        _ <- Ns.i.a1.byte_?.not(Some(byte0)).query.get.map(_ ==> List(a, b, c))
        _ <- Ns.i.a1.byte_?.not(Some(byte1)).query.get.map(_ ==> List(b, c))
        _ <- Ns.i.a1.byte_?.not(Some(byte2)).query.get.map(_ ==> List(a, c))
        _ <- Ns.i.a1.byte_?.not(Some(byte3)).query.get.map(_ ==> List(a, b))
        _ <- Ns.i.a1.byte_?.not(Some(Seq(byte0))).query.get.map(_ ==> List(a, b, c))
        _ <- Ns.i.a1.byte_?.not(Some(Seq(byte1))).query.get.map(_ ==> List(b, c))
        _ <- Ns.i.a1.byte_?.not(Some(Seq(byte2))).query.get.map(_ ==> List(a, c))
        _ <- Ns.i.a1.byte_?.not(Some(Seq(byte3))).query.get.map(_ ==> List(a, b))
        // OR semantics for multiple negation args
        _ <- Ns.i.a1.byte_?.not(Some(Seq(byte0, byte1))).query.get.map(_ ==> List(b, c))
        _ <- Ns.i.a1.byte_?.not(Some(Seq(byte1, byte2))).query.get.map(_ ==> List(c))
        _ <- Ns.i.a1.byte_?.not(Some(Seq(byte2, byte3))).query.get.map(_ ==> List(a))
        // Empty Seq of negation args matches all asserted values (non-null)
        _ <- Ns.i.a1.byte_?.not(Some(Seq.empty[Byte])).query.get.map(_ ==> List(a, b, c))
        // Negating None matches all asserted values (non-null)
        _ <- Ns.i.a1.byte_?.not(Option.empty[Byte]).query.get.map(_ ==> List(a, b, c))
        _ <- Ns.i.a1.byte_?.not(Option.empty[Seq[Byte]]).query.get.map(_ ==> List(a, b, c))

        // Find optional values in range
        _ <- Ns.i.a1.byte_?.<(Some(byte2)).query.get.map(_ ==> List(a))
        _ <- Ns.i.a1.byte_?.>(Some(byte2)).query.get.map(_ ==> List(c))
        _ <- Ns.i.a1.byte_?.<=(Some(byte2)).query.get.map(_ ==> List(a, b))
        _ <- Ns.i.a1.byte_?.>=(Some(byte2)).query.get.map(_ ==> List(b, c))
        // None can't be compared and returns empty result
        _ <- Ns.i.a1.byte_?.<(None).query.get.map(_ ==> List())
        _ <- Ns.i.a1.byte_?.<=(None).query.get.map(_ ==> List())
        _ <- Ns.i.a1.byte_?.>(None).query.get.map(_ ==> List())
        _ <- Ns.i.a1.byte_?.>=(None).query.get.map(_ ==> List())

        // Distinct result even with redundant None's (two i = 4 with no byte value)
        _ <- Ns.i.insert(4).transact
        _ <- Ns.i.>=(3).a1.byte_?.query.get.map(_ ==> List(
          (3, Some(byte3)),
          (4, None),
        ))
      } yield ()
    }
  }
}
