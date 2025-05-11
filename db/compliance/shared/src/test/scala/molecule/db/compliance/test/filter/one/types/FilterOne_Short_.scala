// GENERATED CODE ********************************
package molecule.db.compliance.test.filter.one.types

import molecule.db.compliance.setup.*
import molecule.db.compliance.setup.{DbProviders, Test, TestUtils}
import molecule.db.core.api.Api_async
import molecule.db.core.spi.Spi_async
import molecule.db.core.util.Executor.*
import molecule.db.compliance.domains.dsl.Types.*
import molecule.db.compliance.domains.dsl.Refs.*

case class FilterOne_Short_(
  suite: Test,
  api: Api_async & Spi_async & DbProviders
) extends TestUtils {

  import api.*
  import suite.*

  "Mandatory" - types { implicit conn =>
    val a = (1, short1)
    val b = (2, short2)
    val c = (3, short3)
    for {
      _ <- Entity.i.short.insert(List(a, b, c)).transact

      // Find all attribute values
      _ <- Entity.i.a1.short.query.get.map(_ ==> List(a, b, c))

      // Find value(s) matching
      _ <- Entity.i.a1.short(short0).query.get.map(_ ==> List())
      _ <- Entity.i.a1.short(short1).query.get.map(_ ==> List(a))
      _ <- Entity.i.a1.short(Seq(short0)).query.get.map(_ ==> List())
      _ <- Entity.i.a1.short(Seq(short1)).query.get.map(_ ==> List(a))
      // OR semantics for multiple args ("is this or that")
      _ <- Entity.i.a1.short(short1, short2).query.get.map(_ ==> List(a, b))
      _ <- Entity.i.a1.short(short1, short0).query.get.map(_ ==> List(a))
      _ <- Entity.i.a1.short(Seq(short1, short2)).query.get.map(_ ==> List(a, b))
      _ <- Entity.i.a1.short(Seq(short1, short0)).query.get.map(_ ==> List(a))
      // Empty Seq of args matches no values
      _ <- Entity.i.a1.short(Seq.empty[Short]).query.get.map(_ ==> List())

      // Find values not matching
      _ <- Entity.i.a1.short.not(short0).query.get.map(_ ==> List(a, b, c))
      _ <- Entity.i.a1.short.not(short1).query.get.map(_ ==> List(b, c))
      _ <- Entity.i.a1.short.not(short2).query.get.map(_ ==> List(a, c))
      _ <- Entity.i.a1.short.not(short3).query.get.map(_ ==> List(a, b))
      _ <- Entity.i.a1.short.not(Seq(short0)).query.get.map(_ ==> List(a, b, c))
      _ <- Entity.i.a1.short.not(Seq(short1)).query.get.map(_ ==> List(b, c))
      _ <- Entity.i.a1.short.not(Seq(short2)).query.get.map(_ ==> List(a, c))
      _ <- Entity.i.a1.short.not(Seq(short3)).query.get.map(_ ==> List(a, b))
      // OR semantics for multiple args
      _ <- Entity.i.a1.short.not(short0, short1).query.get.map(_ ==> List(b, c))
      _ <- Entity.i.a1.short.not(short1, short2).query.get.map(_ ==> List(c))
      _ <- Entity.i.a1.short.not(short2, short3).query.get.map(_ ==> List(a))
      _ <- Entity.i.a1.short.not(Seq(short0, short1)).query.get.map(_ ==> List(b, c))
      _ <- Entity.i.a1.short.not(Seq(short1, short2)).query.get.map(_ ==> List(c))
      _ <- Entity.i.a1.short.not(Seq(short2, short3)).query.get.map(_ ==> List(a))
      // Empty Seq of negation args matches all values
      _ <- Entity.i.a1.short.not(Seq.empty[Short]).query.get.map(_ ==> List(a, b, c))

      // Find values in range
      _ <- Entity.i.a1.short.<(short2).query.get.map(_ ==> List(a))
      _ <- Entity.i.a1.short.>(short2).query.get.map(_ ==> List(c))
      _ <- Entity.i.a1.short.<=(short2).query.get.map(_ ==> List(a, b))
      _ <- Entity.i.a1.short.>=(short2).query.get.map(_ ==> List(b, c))
    } yield ()
  }


  "Tacit" - types { implicit conn =>
    val (a, b, c, x) = (1, 2, 3, 4)
    for {
      _ <- Entity.i.short_?.insert(List(
        (a, Some(short1)),
        (b, Some(short2)),
        (c, Some(short3)),
        (x, None)
      )).transact

      // Match asserted attribute without returning its value
      _ <- Entity.i.a1.short_.query.get.map(_ ==> List(a, b, c))

      // Match non-asserted attribute (null)
      _ <- Entity.i.a1.short_().query.get.map(_ ==> List(x))

      // Match attribute values without returning them
      _ <- Entity.i.a1.short_(short0).query.get.map(_ ==> List())
      _ <- Entity.i.a1.short_(short1).query.get.map(_ ==> List(a))
      _ <- Entity.i.a1.short_(Seq(short0)).query.get.map(_ ==> List())
      _ <- Entity.i.a1.short_(Seq(short1)).query.get.map(_ ==> List(a))
      // OR semantics for multiple args ("is this or that")
      _ <- Entity.i.a1.short_(short1, short2).query.get.map(_ ==> List(a, b))
      _ <- Entity.i.a1.short_(short1, short0).query.get.map(_ ==> List(a))
      _ <- Entity.i.a1.short_(Seq(short1, short2)).query.get.map(_ ==> List(a, b))
      _ <- Entity.i.a1.short_(Seq(short1, short0)).query.get.map(_ ==> List(a))
      // Empty Seq of args matches no values
      _ <- Entity.i.a1.short_(Seq.empty[Short]).query.get.map(_ ==> List())

      // Match non-matching values without returning them
      _ <- Entity.i.a1.short_.not(short0).query.get.map(_ ==> List(a, b, c))
      _ <- Entity.i.a1.short_.not(short1).query.get.map(_ ==> List(b, c))
      _ <- Entity.i.a1.short_.not(short2).query.get.map(_ ==> List(a, c))
      _ <- Entity.i.a1.short_.not(short3).query.get.map(_ ==> List(a, b))
      _ <- Entity.i.a1.short_.not(Seq(short0)).query.get.map(_ ==> List(a, b, c))
      _ <- Entity.i.a1.short_.not(Seq(short1)).query.get.map(_ ==> List(b, c))
      _ <- Entity.i.a1.short_.not(Seq(short2)).query.get.map(_ ==> List(a, c))
      _ <- Entity.i.a1.short_.not(Seq(short3)).query.get.map(_ ==> List(a, b))
      // OR semantics for multiple args
      _ <- Entity.i.a1.short_.not(short0, short1).query.get.map(_ ==> List(b, c))
      _ <- Entity.i.a1.short_.not(short1, short2).query.get.map(_ ==> List(c))
      _ <- Entity.i.a1.short_.not(short2, short3).query.get.map(_ ==> List(a))
      _ <- Entity.i.a1.short_.not(Seq(short0, short1)).query.get.map(_ ==> List(b, c))
      _ <- Entity.i.a1.short_.not(Seq(short1, short2)).query.get.map(_ ==> List(c))
      _ <- Entity.i.a1.short_.not(Seq(short2, short3)).query.get.map(_ ==> List(a))
      // Empty Seq of negation args matches all asserted values (non-null)
      _ <- Entity.i.a1.short_.not(Seq.empty[Short]).query.get.map(_ ==> List(a, b, c))

      // Match value ranges without returning them
      _ <- Entity.i.a1.short_.<(short2).query.get.map(_ ==> List(a))
      _ <- Entity.i.a1.short_.>(short2).query.get.map(_ ==> List(c))
      _ <- Entity.i.a1.short_.<=(short2).query.get.map(_ ==> List(a, b))
      _ <- Entity.i.a1.short_.>=(short2).query.get.map(_ ==> List(b, c))
    } yield ()
  }


  "Optional" - types { implicit conn =>
    val a = (1, Some(short1))
    val b = (2, Some(short2))
    val c = (3, Some(short3))
    val x = (4, Option.empty[Short])
    for {
      _ <- Entity.i.short_?.insert(List(a, b, c, x)).transact

      // Find all optional attribute values
      _ <- Entity.i.a1.short_?.query.get.map(_ ==> List(a, b, c, x))

      // Find optional values matching
      _ <- Entity.i.a1.short_?(Some(short0)).query.get.map(_ ==> List())
      _ <- Entity.i.a1.short_?(Some(short1)).query.get.map(_ ==> List(a))

      // None matches non-asserted/null values
      _ <- Entity.i.a1.short_?(Option.empty[Short]).query.get.map(_ ==> List(x))
      // Easier to apply nothing to tacit attribute
      _ <- Entity.i.a1.short_().query.get.map(_ ==> List(4))
    } yield ()
  }


  "Combinations" - types { implicit conn =>
    for {
      _ <- Entity.i.short.insert(
        (1, short1),
        (2, short2),
        (3, short3),
        (4, short4),
        (5, short5),
        (6, short6),
        (7, short7),
        (8, short8),
        (9, short9),
      ).transact

      _ <- Entity.i.a1.short_.>(short2).query.get.map(_ ==> List(3, 4, 5, 6, 7, 8, 9))
      _ <- Entity.i.a1.short_.>(short2).short_.<=(short8).query.get.map(_ ==> List(3, 4, 5, 6, 7, 8))
      _ <- Entity.i.a1.short_.>(short2).short_.<=(short8).short_.not(short4, short5).query.get.map(_ ==> List(3, 6, 7, 8))
    } yield ()
  }
}
