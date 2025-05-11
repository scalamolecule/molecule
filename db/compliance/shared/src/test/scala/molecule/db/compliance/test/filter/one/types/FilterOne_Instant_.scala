// GENERATED CODE ********************************
package molecule.db.compliance.test.filter.one.types

import java.time.Instant
import molecule.db.compliance.setup.*
import molecule.db.compliance.setup.{DbProviders, Test, TestUtils}
import molecule.db.core.api.Api_async
import molecule.db.core.spi.Spi_async
import molecule.db.core.util.Executor.*
import molecule.db.compliance.domains.dsl.Types.*
import molecule.db.compliance.domains.dsl.Refs.*

case class FilterOne_Instant_(
  suite: Test,
  api: Api_async & Spi_async & DbProviders
) extends TestUtils {

  import api.*
  import suite.*

  "Mandatory" - types { implicit conn =>
    val a = (1, instant1)
    val b = (2, instant2)
    val c = (3, instant3)
    for {
      _ <- Entity.i.instant.insert(List(a, b, c)).transact

      // Find all attribute values
      _ <- Entity.i.a1.instant.query.get.map(_ ==> List(a, b, c))

      // Find value(s) matching
      _ <- Entity.i.a1.instant(instant0).query.get.map(_ ==> List())
      _ <- Entity.i.a1.instant(instant1).query.get.map(_ ==> List(a))
      _ <- Entity.i.a1.instant(Seq(instant0)).query.get.map(_ ==> List())
      _ <- Entity.i.a1.instant(Seq(instant1)).query.get.map(_ ==> List(a))
      // OR semantics for multiple args ("is this or that")
      _ <- Entity.i.a1.instant(instant1, instant2).query.get.map(_ ==> List(a, b))
      _ <- Entity.i.a1.instant(instant1, instant0).query.get.map(_ ==> List(a))
      _ <- Entity.i.a1.instant(Seq(instant1, instant2)).query.get.map(_ ==> List(a, b))
      _ <- Entity.i.a1.instant(Seq(instant1, instant0)).query.get.map(_ ==> List(a))
      // Empty Seq of args matches no values
      _ <- Entity.i.a1.instant(Seq.empty[Instant]).query.get.map(_ ==> List())

      // Find values not matching
      _ <- Entity.i.a1.instant.not(instant0).query.get.map(_ ==> List(a, b, c))
      _ <- Entity.i.a1.instant.not(instant1).query.get.map(_ ==> List(b, c))
      _ <- Entity.i.a1.instant.not(instant2).query.get.map(_ ==> List(a, c))
      _ <- Entity.i.a1.instant.not(instant3).query.get.map(_ ==> List(a, b))
      _ <- Entity.i.a1.instant.not(Seq(instant0)).query.get.map(_ ==> List(a, b, c))
      _ <- Entity.i.a1.instant.not(Seq(instant1)).query.get.map(_ ==> List(b, c))
      _ <- Entity.i.a1.instant.not(Seq(instant2)).query.get.map(_ ==> List(a, c))
      _ <- Entity.i.a1.instant.not(Seq(instant3)).query.get.map(_ ==> List(a, b))
      // OR semantics for multiple args
      _ <- Entity.i.a1.instant.not(instant0, instant1).query.get.map(_ ==> List(b, c))
      _ <- Entity.i.a1.instant.not(instant1, instant2).query.get.map(_ ==> List(c))
      _ <- Entity.i.a1.instant.not(instant2, instant3).query.get.map(_ ==> List(a))
      _ <- Entity.i.a1.instant.not(Seq(instant0, instant1)).query.get.map(_ ==> List(b, c))
      _ <- Entity.i.a1.instant.not(Seq(instant1, instant2)).query.get.map(_ ==> List(c))
      _ <- Entity.i.a1.instant.not(Seq(instant2, instant3)).query.get.map(_ ==> List(a))
      // Empty Seq of negation args matches all values
      _ <- Entity.i.a1.instant.not(Seq.empty[Instant]).query.get.map(_ ==> List(a, b, c))

      // Find values in range
      _ <- Entity.i.a1.instant.<(instant2).query.get.map(_ ==> List(a))
      _ <- Entity.i.a1.instant.>(instant2).query.get.map(_ ==> List(c))
      _ <- Entity.i.a1.instant.<=(instant2).query.get.map(_ ==> List(a, b))
      _ <- Entity.i.a1.instant.>=(instant2).query.get.map(_ ==> List(b, c))
    } yield ()
  }


  "Tacit" - types { implicit conn =>
    val (a, b, c, x) = (1, 2, 3, 4)
    for {
      _ <- Entity.i.instant_?.insert(List(
        (a, Some(instant1)),
        (b, Some(instant2)),
        (c, Some(instant3)),
        (x, None)
      )).transact

      // Match asserted attribute without returning its value
      _ <- Entity.i.a1.instant_.query.get.map(_ ==> List(a, b, c))

      // Match non-asserted attribute (null)
      _ <- Entity.i.a1.instant_().query.get.map(_ ==> List(x))

      // Match attribute values without returning them
      _ <- Entity.i.a1.instant_(instant0).query.get.map(_ ==> List())
      _ <- Entity.i.a1.instant_(instant1).query.get.map(_ ==> List(a))
      _ <- Entity.i.a1.instant_(Seq(instant0)).query.get.map(_ ==> List())
      _ <- Entity.i.a1.instant_(Seq(instant1)).query.get.map(_ ==> List(a))
      // OR semantics for multiple args ("is this or that")
      _ <- Entity.i.a1.instant_(instant1, instant2).query.get.map(_ ==> List(a, b))
      _ <- Entity.i.a1.instant_(instant1, instant0).query.get.map(_ ==> List(a))
      _ <- Entity.i.a1.instant_(Seq(instant1, instant2)).query.get.map(_ ==> List(a, b))
      _ <- Entity.i.a1.instant_(Seq(instant1, instant0)).query.get.map(_ ==> List(a))
      // Empty Seq of args matches no values
      _ <- Entity.i.a1.instant_(Seq.empty[Instant]).query.get.map(_ ==> List())

      // Match non-matching values without returning them
      _ <- Entity.i.a1.instant_.not(instant0).query.get.map(_ ==> List(a, b, c))
      _ <- Entity.i.a1.instant_.not(instant1).query.get.map(_ ==> List(b, c))
      _ <- Entity.i.a1.instant_.not(instant2).query.get.map(_ ==> List(a, c))
      _ <- Entity.i.a1.instant_.not(instant3).query.get.map(_ ==> List(a, b))
      _ <- Entity.i.a1.instant_.not(Seq(instant0)).query.get.map(_ ==> List(a, b, c))
      _ <- Entity.i.a1.instant_.not(Seq(instant1)).query.get.map(_ ==> List(b, c))
      _ <- Entity.i.a1.instant_.not(Seq(instant2)).query.get.map(_ ==> List(a, c))
      _ <- Entity.i.a1.instant_.not(Seq(instant3)).query.get.map(_ ==> List(a, b))
      // OR semantics for multiple args
      _ <- Entity.i.a1.instant_.not(instant0, instant1).query.get.map(_ ==> List(b, c))
      _ <- Entity.i.a1.instant_.not(instant1, instant2).query.get.map(_ ==> List(c))
      _ <- Entity.i.a1.instant_.not(instant2, instant3).query.get.map(_ ==> List(a))
      _ <- Entity.i.a1.instant_.not(Seq(instant0, instant1)).query.get.map(_ ==> List(b, c))
      _ <- Entity.i.a1.instant_.not(Seq(instant1, instant2)).query.get.map(_ ==> List(c))
      _ <- Entity.i.a1.instant_.not(Seq(instant2, instant3)).query.get.map(_ ==> List(a))
      // Empty Seq of negation args matches all asserted values (non-null)
      _ <- Entity.i.a1.instant_.not(Seq.empty[Instant]).query.get.map(_ ==> List(a, b, c))

      // Match value ranges without returning them
      _ <- Entity.i.a1.instant_.<(instant2).query.get.map(_ ==> List(a))
      _ <- Entity.i.a1.instant_.>(instant2).query.get.map(_ ==> List(c))
      _ <- Entity.i.a1.instant_.<=(instant2).query.get.map(_ ==> List(a, b))
      _ <- Entity.i.a1.instant_.>=(instant2).query.get.map(_ ==> List(b, c))
    } yield ()
  }


  "Optional" - types { implicit conn =>
    val a = (1, Some(instant1))
    val b = (2, Some(instant2))
    val c = (3, Some(instant3))
    val x = (4, Option.empty[Instant])
    for {
      _ <- Entity.i.instant_?.insert(List(a, b, c, x)).transact

      // Find all optional attribute values
      _ <- Entity.i.a1.instant_?.query.get.map(_ ==> List(a, b, c, x))

      // Find optional values matching
      _ <- Entity.i.a1.instant_?(Some(instant0)).query.get.map(_ ==> List())
      _ <- Entity.i.a1.instant_?(Some(instant1)).query.get.map(_ ==> List(a))

      // None matches non-asserted/null values
      _ <- Entity.i.a1.instant_?(Option.empty[Instant]).query.get.map(_ ==> List(x))
      // Easier to apply nothing to tacit attribute
      _ <- Entity.i.a1.instant_().query.get.map(_ ==> List(4))
    } yield ()
  }


  "Combinations" - types { implicit conn =>
    for {
      _ <- Entity.i.instant.insert(
        (1, instant1),
        (2, instant2),
        (3, instant3),
        (4, instant4),
        (5, instant5),
        (6, instant6),
        (7, instant7),
        (8, instant8),
        (9, instant9),
      ).transact

      _ <- Entity.i.a1.instant_.>(instant2).query.get.map(_ ==> List(3, 4, 5, 6, 7, 8, 9))
      _ <- Entity.i.a1.instant_.>(instant2).instant_.<=(instant8).query.get.map(_ ==> List(3, 4, 5, 6, 7, 8))
      _ <- Entity.i.a1.instant_.>(instant2).instant_.<=(instant8).instant_.not(instant4, instant5).query.get.map(_ ==> List(3, 6, 7, 8))
    } yield ()
  }
}
