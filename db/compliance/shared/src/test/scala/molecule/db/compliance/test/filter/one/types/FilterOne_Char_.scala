// GENERATED CODE ********************************
package molecule.db.compliance.test.filter.one.types

import molecule.core.setup.{MUnit, TestUtils}
import molecule.db.compliance.domains.dsl.Types.*
import molecule.db.compliance.setup.DbProviders
import molecule.db.common.api.Api_async
import molecule.db.common.spi.Spi_async
import molecule.db.common.util.Executor.*

case class FilterOne_Char_(
  suite: MUnit,
  api: Api_async & Spi_async & DbProviders
) extends TestUtils {

  import api.*
  import suite.*

  "Mandatory" - types {
    val a = (1, char1)
    val b = (2, char2)
    val c = (3, char3)
    for {
      _ <- Entity.i.char.insert(List(a, b, c)).transact

      // Find all attribute values
      _ <- Entity.i.a1.char.query.get.map(_ ==> List(a, b, c))

      // Find value(s) matching
      _ <- Entity.i.a1.char(char0).query.get.map(_ ==> List())
      _ <- Entity.i.a1.char(char1).query.get.map(_ ==> List(a))
      _ <- Entity.i.a1.char(Seq(char0)).query.get.map(_ ==> List())
      _ <- Entity.i.a1.char(Seq(char1)).query.get.map(_ ==> List(a))
      // OR semantics for multiple args ("is this or that")
      _ <- Entity.i.a1.char(char1, char2).query.get.map(_ ==> List(a, b))
      _ <- Entity.i.a1.char(char1, char0).query.get.map(_ ==> List(a))
      _ <- Entity.i.a1.char(Seq(char1, char2)).query.get.map(_ ==> List(a, b))
      _ <- Entity.i.a1.char(Seq(char1, char0)).query.get.map(_ ==> List(a))
      // Empty Seq of args matches no values
      _ <- Entity.i.a1.char(Seq.empty[Char]).query.get.map(_ ==> List())

      // Find values not matching
      _ <- Entity.i.a1.char.not(char0).query.get.map(_ ==> List(a, b, c))
      _ <- Entity.i.a1.char.not(char1).query.get.map(_ ==> List(b, c))
      _ <- Entity.i.a1.char.not(char2).query.get.map(_ ==> List(a, c))
      _ <- Entity.i.a1.char.not(char3).query.get.map(_ ==> List(a, b))
      _ <- Entity.i.a1.char.not(Seq(char0)).query.get.map(_ ==> List(a, b, c))
      _ <- Entity.i.a1.char.not(Seq(char1)).query.get.map(_ ==> List(b, c))
      _ <- Entity.i.a1.char.not(Seq(char2)).query.get.map(_ ==> List(a, c))
      _ <- Entity.i.a1.char.not(Seq(char3)).query.get.map(_ ==> List(a, b))
      // OR semantics for multiple args
      _ <- Entity.i.a1.char.not(char0, char1).query.get.map(_ ==> List(b, c))
      _ <- Entity.i.a1.char.not(char1, char2).query.get.map(_ ==> List(c))
      _ <- Entity.i.a1.char.not(char2, char3).query.get.map(_ ==> List(a))
      _ <- Entity.i.a1.char.not(Seq(char0, char1)).query.get.map(_ ==> List(b, c))
      _ <- Entity.i.a1.char.not(Seq(char1, char2)).query.get.map(_ ==> List(c))
      _ <- Entity.i.a1.char.not(Seq(char2, char3)).query.get.map(_ ==> List(a))
      // Empty Seq of negation args matches all values
      _ <- Entity.i.a1.char.not(Seq.empty[Char]).query.get.map(_ ==> List(a, b, c))

      // Find values in range
      _ <- Entity.i.a1.char.<(char2).query.get.map(_ ==> List(a))
      _ <- Entity.i.a1.char.>(char2).query.get.map(_ ==> List(c))
      _ <- Entity.i.a1.char.<=(char2).query.get.map(_ ==> List(a, b))
      _ <- Entity.i.a1.char.>=(char2).query.get.map(_ ==> List(b, c))
    } yield ()
  }


  "Tacit" - types {
    val (a, b, c, x) = (1, 2, 3, 4)
    for {
      _ <- Entity.i.char_?.insert(List(
        (a, Some(char1)),
        (b, Some(char2)),
        (c, Some(char3)),
        (x, None)
      )).transact

      // Match asserted attribute without returning its value
      _ <- Entity.i.a1.char_.query.get.map(_ ==> List(a, b, c))

      // Match non-asserted attribute (null)
      _ <- Entity.i.a1.char_().query.get.map(_ ==> List(x))

      // Match attribute values without returning them
      _ <- Entity.i.a1.char_(char0).query.get.map(_ ==> List())
      _ <- Entity.i.a1.char_(char1).query.get.map(_ ==> List(a))
      _ <- Entity.i.a1.char_(Seq(char0)).query.get.map(_ ==> List())
      _ <- Entity.i.a1.char_(Seq(char1)).query.get.map(_ ==> List(a))
      // OR semantics for multiple args ("is this or that")
      _ <- Entity.i.a1.char_(char1, char2).query.get.map(_ ==> List(a, b))
      _ <- Entity.i.a1.char_(char1, char0).query.get.map(_ ==> List(a))
      _ <- Entity.i.a1.char_(Seq(char1, char2)).query.get.map(_ ==> List(a, b))
      _ <- Entity.i.a1.char_(Seq(char1, char0)).query.get.map(_ ==> List(a))
      // Empty Seq of args matches no values
      _ <- Entity.i.a1.char_(Seq.empty[Char]).query.get.map(_ ==> List())

      // Match non-matching values without returning them
      _ <- Entity.i.a1.char_.not(char0).query.get.map(_ ==> List(a, b, c))
      _ <- Entity.i.a1.char_.not(char1).query.get.map(_ ==> List(b, c))
      _ <- Entity.i.a1.char_.not(char2).query.get.map(_ ==> List(a, c))
      _ <- Entity.i.a1.char_.not(char3).query.get.map(_ ==> List(a, b))
      _ <- Entity.i.a1.char_.not(Seq(char0)).query.get.map(_ ==> List(a, b, c))
      _ <- Entity.i.a1.char_.not(Seq(char1)).query.get.map(_ ==> List(b, c))
      _ <- Entity.i.a1.char_.not(Seq(char2)).query.get.map(_ ==> List(a, c))
      _ <- Entity.i.a1.char_.not(Seq(char3)).query.get.map(_ ==> List(a, b))
      // OR semantics for multiple args
      _ <- Entity.i.a1.char_.not(char0, char1).query.get.map(_ ==> List(b, c))
      _ <- Entity.i.a1.char_.not(char1, char2).query.get.map(_ ==> List(c))
      _ <- Entity.i.a1.char_.not(char2, char3).query.get.map(_ ==> List(a))
      _ <- Entity.i.a1.char_.not(Seq(char0, char1)).query.get.map(_ ==> List(b, c))
      _ <- Entity.i.a1.char_.not(Seq(char1, char2)).query.get.map(_ ==> List(c))
      _ <- Entity.i.a1.char_.not(Seq(char2, char3)).query.get.map(_ ==> List(a))
      // Empty Seq of negation args matches all asserted values (non-null)
      _ <- Entity.i.a1.char_.not(Seq.empty[Char]).query.get.map(_ ==> List(a, b, c))

      // Match value ranges without returning them
      _ <- Entity.i.a1.char_.<(char2).query.get.map(_ ==> List(a))
      _ <- Entity.i.a1.char_.>(char2).query.get.map(_ ==> List(c))
      _ <- Entity.i.a1.char_.<=(char2).query.get.map(_ ==> List(a, b))
      _ <- Entity.i.a1.char_.>=(char2).query.get.map(_ ==> List(b, c))
    } yield ()
  }


  "Optional" - types {
    val a = (1, Some(char1))
    val b = (2, Some(char2))
    val c = (3, Some(char3))
    val x = (4, Option.empty[Char])
    for {
      _ <- Entity.i.char_?.insert(List(a, b, c, x)).transact

      // Find all optional attribute values
      _ <- Entity.i.a1.char_?.query.get.map(_ ==> List(a, b, c, x))

      // Find optional values matching
      _ <- Entity.i.a1.char_?(Some(char0)).query.get.map(_ ==> List())
      _ <- Entity.i.a1.char_?(Some(char1)).query.get.map(_ ==> List(a))

      // None matches non-asserted/null values
      _ <- Entity.i.a1.char_?(Option.empty[Char]).query.get.map(_ ==> List(x))
      // Easier to apply nothing to tacit attribute
      _ <- Entity.i.a1.char_().query.get.map(_ ==> List(4))
    } yield ()
  }


  "Combinations" - types {
    for {
      _ <- Entity.i.char.insert(
        (1, char1),
        (2, char2),
        (3, char3),
        (4, char4),
        (5, char5),
        (6, char6),
        (7, char7),
        (8, char8),
        (9, char9),
      ).transact

      _ <- Entity.i.a1.char_.>(char2).query.get.map(_ ==> List(3, 4, 5, 6, 7, 8, 9))
      _ <- Entity.i.a1.char_.>(char2).char_.<=(char8).query.get.map(_ ==> List(3, 4, 5, 6, 7, 8))
      _ <- Entity.i.a1.char_.>(char2).char_.<=(char8).char_.not(char4, char5).query.get.map(_ ==> List(3, 6, 7, 8))
    } yield ()
  }
}
