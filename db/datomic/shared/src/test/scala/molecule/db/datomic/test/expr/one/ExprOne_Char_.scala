// GENERATED CODE ********************************
package molecule.db.datomic.test.expr.one

import molecule.core.util.Executor._
import molecule.coreTests.dataModels.core.dsl.Types._
import molecule.db.datomic.async._
import molecule.db.datomic.setup.DatomicTestSuite
import utest._

object ExprOne_Char_ extends DatomicTestSuite {


  lazy val tests = Tests {

    "Mandatory" - types { implicit conn =>
      val a = (1, char1)
      val b = (2, char2)
      val c = (3, char3)
      for {
        _ <- Ns.i.char.insert(List(a, b, c)).transact

        // Find all attribute values
        _ <- Ns.i.a1.char.query.get.map(_ ==> List(a, b, c))

        // Find value(s) matching
        _ <- Ns.i.a1.char(char0).query.get.map(_ ==> List())
        _ <- Ns.i.a1.char(char1).query.get.map(_ ==> List(a))
        _ <- Ns.i.a1.char(Seq(char0)).query.get.map(_ ==> List())
        _ <- Ns.i.a1.char(Seq(char1)).query.get.map(_ ==> List(a))
        // OR semantics for multiple args
        _ <- Ns.i.a1.char(char1, char2).query.get.map(_ ==> List(a, b))
        _ <- Ns.i.a1.char(char1, char0).query.get.map(_ ==> List(a))
        _ <- Ns.i.a1.char(Seq(char1, char2)).query.get.map(_ ==> List(a, b))
        _ <- Ns.i.a1.char(Seq(char1, char0)).query.get.map(_ ==> List(a))
        // Empty Seq of args matches no values
        _ <- Ns.i.a1.char(Seq.empty[Char]).query.get.map(_ ==> List())

        // Find values not matching
        _ <- Ns.i.a1.char.not(char0).query.get.map(_ ==> List(a, b, c))
        _ <- Ns.i.a1.char.not(char1).query.get.map(_ ==> List(b, c))
        _ <- Ns.i.a1.char.not(char2).query.get.map(_ ==> List(a, c))
        _ <- Ns.i.a1.char.not(char3).query.get.map(_ ==> List(a, b))
        _ <- Ns.i.a1.char.not(Seq(char0)).query.get.map(_ ==> List(a, b, c))
        _ <- Ns.i.a1.char.not(Seq(char1)).query.get.map(_ ==> List(b, c))
        _ <- Ns.i.a1.char.not(Seq(char2)).query.get.map(_ ==> List(a, c))
        _ <- Ns.i.a1.char.not(Seq(char3)).query.get.map(_ ==> List(a, b))
        // OR semantics for multiple args
        _ <- Ns.i.a1.char.not(char0, char1).query.get.map(_ ==> List(b, c))
        _ <- Ns.i.a1.char.not(char1, char2).query.get.map(_ ==> List(c))
        _ <- Ns.i.a1.char.not(char2, char3).query.get.map(_ ==> List(a))
        _ <- Ns.i.a1.char.not(Seq(char0, char1)).query.get.map(_ ==> List(b, c))
        _ <- Ns.i.a1.char.not(Seq(char1, char2)).query.get.map(_ ==> List(c))
        _ <- Ns.i.a1.char.not(Seq(char2, char3)).query.get.map(_ ==> List(a))
        // Empty Seq of negation args matches all values
        _ <- Ns.i.a1.char.not(Seq.empty[Char]).query.get.map(_ ==> List(a, b, c))

        // Find values in range
        _ <- Ns.i.a1.char.<(char2).query.get.map(_ ==> List(a))
        _ <- Ns.i.a1.char.>(char2).query.get.map(_ ==> List(c))
        _ <- Ns.i.a1.char.<=(char2).query.get.map(_ ==> List(a, b))
        _ <- Ns.i.a1.char.>=(char2).query.get.map(_ ==> List(b, c))
      } yield ()
    }


    "Tacit" - types { implicit conn =>
      val (a, b, c, x) = (1, 2, 3, 4)
      for {
        _ <- Ns.i.char_?.insert(List(
          (a, Some(char1)),
          (b, Some(char2)),
          (c, Some(char3)),
          (x, None)
        )).transact

        // Match asserted attribute without returning its value
        _ <- Ns.i.a1.char_.query.get.map(_ ==> List(a, b, c))

        // Match non-asserted attribute (null)
        _ <- Ns.i.a1.char_().query.get.map(_ ==> List(x))

        // Match attribute values without returning them
        _ <- Ns.i.a1.char_(char0).query.get.map(_ ==> List())
        _ <- Ns.i.a1.char_(char1).query.get.map(_ ==> List(a))
        _ <- Ns.i.a1.char_(Seq(char0)).query.get.map(_ ==> List())
        _ <- Ns.i.a1.char_(Seq(char1)).query.get.map(_ ==> List(a))
        // OR semantics for multiple args
        _ <- Ns.i.a1.char_(char1, char2).query.get.map(_ ==> List(a, b))
        _ <- Ns.i.a1.char_(char1, char0).query.get.map(_ ==> List(a))
        _ <- Ns.i.a1.char_(Seq(char1, char2)).query.get.map(_ ==> List(a, b))
        _ <- Ns.i.a1.char_(Seq(char1, char0)).query.get.map(_ ==> List(a))
        // Empty Seq of args matches no values
        _ <- Ns.i.a1.char_(Seq.empty[Char]).query.get.map(_ ==> List())

        // Match non-matching values without returning them
        _ <- Ns.i.a1.char_.not(char0).query.get.map(_ ==> List(a, b, c))
        _ <- Ns.i.a1.char_.not(char1).query.get.map(_ ==> List(b, c))
        _ <- Ns.i.a1.char_.not(char2).query.get.map(_ ==> List(a, c))
        _ <- Ns.i.a1.char_.not(char3).query.get.map(_ ==> List(a, b))
        _ <- Ns.i.a1.char_.not(Seq(char0)).query.get.map(_ ==> List(a, b, c))
        _ <- Ns.i.a1.char_.not(Seq(char1)).query.get.map(_ ==> List(b, c))
        _ <- Ns.i.a1.char_.not(Seq(char2)).query.get.map(_ ==> List(a, c))
        _ <- Ns.i.a1.char_.not(Seq(char3)).query.get.map(_ ==> List(a, b))
        // OR semantics for multiple args
        _ <- Ns.i.a1.char_.not(char0, char1).query.get.map(_ ==> List(b, c))
        _ <- Ns.i.a1.char_.not(char1, char2).query.get.map(_ ==> List(c))
        _ <- Ns.i.a1.char_.not(char2, char3).query.get.map(_ ==> List(a))
        _ <- Ns.i.a1.char_.not(Seq(char0, char1)).query.get.map(_ ==> List(b, c))
        _ <- Ns.i.a1.char_.not(Seq(char1, char2)).query.get.map(_ ==> List(c))
        _ <- Ns.i.a1.char_.not(Seq(char2, char3)).query.get.map(_ ==> List(a))
        // Empty Seq of negation args matches all asserted values (non-null)
        _ <- Ns.i.a1.char_.not(Seq.empty[Char]).query.get.map(_ ==> List(a, b, c))

        // Match value ranges without returning them
        _ <- Ns.i.a1.char_.<(char2).query.get.map(_ ==> List(a))
        _ <- Ns.i.a1.char_.>(char2).query.get.map(_ ==> List(c))
        _ <- Ns.i.a1.char_.<=(char2).query.get.map(_ ==> List(a, b))
        _ <- Ns.i.a1.char_.>=(char2).query.get.map(_ ==> List(b, c))
      } yield ()
    }


    "Optional" - types { implicit conn =>
      val a = (1, Some(char1))
      val b = (2, Some(char2))
      val c = (3, Some(char3))
      val x = (4, Option.empty[Char])
      for {
        _ <- Ns.i.char_?.insert(List(a, b, c, x)).transact

        // Find all optional attribute values
        _ <- Ns.i.a1.char_?.query.get.map(_ ==> List(a, b, c, x))

        // Find optional values matching
        _ <- Ns.i.a1.char_?(Some(char0)).query.get.map(_ ==> List())
        _ <- Ns.i.a1.char_?(Some(char1)).query.get.map(_ ==> List(a))
        _ <- Ns.i.a1.char_?(Some(Seq(char0))).query.get.map(_ ==> List())
        _ <- Ns.i.a1.char_?(Some(Seq(char1))).query.get.map(_ ==> List(a))
        // OR semantics for Ses of multiple args
        _ <- Ns.i.a1.char_?(Some(Seq(char1, char2))).query.get.map(_ ==> List(a, b))
        _ <- Ns.i.a1.char_?(Some(Seq(char1, char0))).query.get.map(_ ==> List(a))
        // Empty Seq of args matches no values
        _ <- Ns.i.a1.char_?(Some(Seq.empty[Char])).query.get.map(_ ==> List())
        // None matches non-asserted/null values
        _ <- Ns.i.a1.char_?(Option.empty[Char]).query.get.map(_ ==> List(x))
        _ <- Ns.i.a1.char_?(Option.empty[Seq[Char]]).query.get.map(_ ==> List(x))

        // Find optional values not matching
        _ <- Ns.i.a1.char_?.not(Some(char0)).query.get.map(_ ==> List(a, b, c))
        _ <- Ns.i.a1.char_?.not(Some(char1)).query.get.map(_ ==> List(b, c))
        _ <- Ns.i.a1.char_?.not(Some(char2)).query.get.map(_ ==> List(a, c))
        _ <- Ns.i.a1.char_?.not(Some(char3)).query.get.map(_ ==> List(a, b))
        _ <- Ns.i.a1.char_?.not(Some(Seq(char0))).query.get.map(_ ==> List(a, b, c))
        _ <- Ns.i.a1.char_?.not(Some(Seq(char1))).query.get.map(_ ==> List(b, c))
        _ <- Ns.i.a1.char_?.not(Some(Seq(char2))).query.get.map(_ ==> List(a, c))
        _ <- Ns.i.a1.char_?.not(Some(Seq(char3))).query.get.map(_ ==> List(a, b))
        // OR semantics for multiple negation args
        _ <- Ns.i.a1.char_?.not(Some(Seq(char0, char1))).query.get.map(_ ==> List(b, c))
        _ <- Ns.i.a1.char_?.not(Some(Seq(char1, char2))).query.get.map(_ ==> List(c))
        _ <- Ns.i.a1.char_?.not(Some(Seq(char2, char3))).query.get.map(_ ==> List(a))
        // Empty Seq of negation args matches all asserted values (non-null)
        _ <- Ns.i.a1.char_?.not(Some(Seq.empty[Char])).query.get.map(_ ==> List(a, b, c))
        // Negating None matches all asserted values (non-null)
        _ <- Ns.i.a1.char_?.not(Option.empty[Char]).query.get.map(_ ==> List(a, b, c))
        _ <- Ns.i.a1.char_?.not(Option.empty[Seq[Char]]).query.get.map(_ ==> List(a, b, c))

        // Find optional values in range
        _ <- Ns.i.a1.char_?.<(Some(char2)).query.get.map(_ ==> List(a))
        _ <- Ns.i.a1.char_?.>(Some(char2)).query.get.map(_ ==> List(c))
        _ <- Ns.i.a1.char_?.<=(Some(char2)).query.get.map(_ ==> List(a, b))
        _ <- Ns.i.a1.char_?.>=(Some(char2)).query.get.map(_ ==> List(b, c))
        // None can't be compared and returns empty result
        _ <- Ns.i.a1.char_?.<(None).query.get.map(_ ==> List())
        _ <- Ns.i.a1.char_?.<=(None).query.get.map(_ ==> List())
        _ <- Ns.i.a1.char_?.>(None).query.get.map(_ ==> List())
        _ <- Ns.i.a1.char_?.>=(None).query.get.map(_ ==> List())

        // Distinct result even with redundant None's (two i = 4 with no char value)
        _ <- Ns.i.insert(4).transact
        _ <- Ns.i.>=(3).a1.char_?.query.get.map(_ ==> List(
          (3, Some(char3)),
          (4, None),
        ))
      } yield ()
    }
  }
}
