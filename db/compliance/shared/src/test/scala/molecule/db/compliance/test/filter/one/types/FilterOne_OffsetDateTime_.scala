// GENERATED CODE ********************************
package molecule.db.compliance.test.filter.one.types

import java.time.OffsetDateTime
import molecule.core.setup.{MUnit, TestUtils}
import molecule.db.compliance.domains.dsl.Types.*
import molecule.db.compliance.setup.DbProviders
import molecule.db.common.api.Api_async
import molecule.db.common.spi.Spi_async
import molecule.db.common.util.Executor.*

case class FilterOne_OffsetDateTime_(
  suite: MUnit,
  api: Api_async & Spi_async & DbProviders
) extends TestUtils {

  import api.*
  import suite.*

  "Mandatory" - types {
    val a = (1, offsetDateTime1)
    val b = (2, offsetDateTime2)
    val c = (3, offsetDateTime3)
    for {
      _ <- Entity.i.offsetDateTime.insert(List(a, b, c)).transact

      // Find all attribute values
      _ <- Entity.i.a1.offsetDateTime.query.get.map(_ ==> List(a, b, c))

      // Find value(s) matching
      _ <- Entity.i.a1.offsetDateTime(offsetDateTime0).query.get.map(_ ==> List())
      _ <- Entity.i.a1.offsetDateTime(offsetDateTime1).query.get.map(_ ==> List(a))
      _ <- Entity.i.a1.offsetDateTime(Seq(offsetDateTime0)).query.get.map(_ ==> List())
      _ <- Entity.i.a1.offsetDateTime(Seq(offsetDateTime1)).query.get.map(_ ==> List(a))
      // OR semantics for multiple args ("is this or that")
      _ <- Entity.i.a1.offsetDateTime(offsetDateTime1, offsetDateTime2).query.get.map(_ ==> List(a, b))
      _ <- Entity.i.a1.offsetDateTime(offsetDateTime1, offsetDateTime0).query.get.map(_ ==> List(a))
      _ <- Entity.i.a1.offsetDateTime(Seq(offsetDateTime1, offsetDateTime2)).query.get.map(_ ==> List(a, b))
      _ <- Entity.i.a1.offsetDateTime(Seq(offsetDateTime1, offsetDateTime0)).query.get.map(_ ==> List(a))
      // Empty Seq of args matches no values
      _ <- Entity.i.a1.offsetDateTime(Seq.empty[OffsetDateTime]).query.get.map(_ ==> List())

      // Find values not matching
      _ <- Entity.i.a1.offsetDateTime.not(offsetDateTime0).query.get.map(_ ==> List(a, b, c))
      _ <- Entity.i.a1.offsetDateTime.not(offsetDateTime1).query.get.map(_ ==> List(b, c))
      _ <- Entity.i.a1.offsetDateTime.not(offsetDateTime2).query.get.map(_ ==> List(a, c))
      _ <- Entity.i.a1.offsetDateTime.not(offsetDateTime3).query.get.map(_ ==> List(a, b))
      _ <- Entity.i.a1.offsetDateTime.not(Seq(offsetDateTime0)).query.get.map(_ ==> List(a, b, c))
      _ <- Entity.i.a1.offsetDateTime.not(Seq(offsetDateTime1)).query.get.map(_ ==> List(b, c))
      _ <- Entity.i.a1.offsetDateTime.not(Seq(offsetDateTime2)).query.get.map(_ ==> List(a, c))
      _ <- Entity.i.a1.offsetDateTime.not(Seq(offsetDateTime3)).query.get.map(_ ==> List(a, b))
      // OR semantics for multiple args
      _ <- Entity.i.a1.offsetDateTime.not(offsetDateTime0, offsetDateTime1).query.get.map(_ ==> List(b, c))
      _ <- Entity.i.a1.offsetDateTime.not(offsetDateTime1, offsetDateTime2).query.get.map(_ ==> List(c))
      _ <- Entity.i.a1.offsetDateTime.not(offsetDateTime2, offsetDateTime3).query.get.map(_ ==> List(a))
      _ <- Entity.i.a1.offsetDateTime.not(Seq(offsetDateTime0, offsetDateTime1)).query.get.map(_ ==> List(b, c))
      _ <- Entity.i.a1.offsetDateTime.not(Seq(offsetDateTime1, offsetDateTime2)).query.get.map(_ ==> List(c))
      _ <- Entity.i.a1.offsetDateTime.not(Seq(offsetDateTime2, offsetDateTime3)).query.get.map(_ ==> List(a))
      // Empty Seq of negation args matches all values
      _ <- Entity.i.a1.offsetDateTime.not(Seq.empty[OffsetDateTime]).query.get.map(_ ==> List(a, b, c))

      // Find values in range
      _ <- Entity.i.a1.offsetDateTime.<(offsetDateTime2).query.get.map(_ ==> List(a))
      _ <- Entity.i.a1.offsetDateTime.>(offsetDateTime2).query.get.map(_ ==> List(c))
      _ <- Entity.i.a1.offsetDateTime.<=(offsetDateTime2).query.get.map(_ ==> List(a, b))
      _ <- Entity.i.a1.offsetDateTime.>=(offsetDateTime2).query.get.map(_ ==> List(b, c))
    } yield ()
  }


  "Tacit" - types {
    val (a, b, c, x) = (1, 2, 3, 4)
    for {
      _ <- Entity.i.offsetDateTime_?.insert(List(
        (a, Some(offsetDateTime1)),
        (b, Some(offsetDateTime2)),
        (c, Some(offsetDateTime3)),
        (x, None)
      )).transact

      // Match asserted attribute without returning its value
      _ <- Entity.i.a1.offsetDateTime_.query.get.map(_ ==> List(a, b, c))

      // Match non-asserted attribute (null)
      _ <- Entity.i.a1.offsetDateTime_().query.get.map(_ ==> List(x))

      // Match attribute values without returning them
      _ <- Entity.i.a1.offsetDateTime_(offsetDateTime0).query.get.map(_ ==> List())
      _ <- Entity.i.a1.offsetDateTime_(offsetDateTime1).query.get.map(_ ==> List(a))
      _ <- Entity.i.a1.offsetDateTime_(Seq(offsetDateTime0)).query.get.map(_ ==> List())
      _ <- Entity.i.a1.offsetDateTime_(Seq(offsetDateTime1)).query.get.map(_ ==> List(a))
      // OR semantics for multiple args ("is this or that")
      _ <- Entity.i.a1.offsetDateTime_(offsetDateTime1, offsetDateTime2).query.get.map(_ ==> List(a, b))
      _ <- Entity.i.a1.offsetDateTime_(offsetDateTime1, offsetDateTime0).query.get.map(_ ==> List(a))
      _ <- Entity.i.a1.offsetDateTime_(Seq(offsetDateTime1, offsetDateTime2)).query.get.map(_ ==> List(a, b))
      _ <- Entity.i.a1.offsetDateTime_(Seq(offsetDateTime1, offsetDateTime0)).query.get.map(_ ==> List(a))
      // Empty Seq of args matches no values
      _ <- Entity.i.a1.offsetDateTime_(Seq.empty[OffsetDateTime]).query.get.map(_ ==> List())

      // Match non-matching values without returning them
      _ <- Entity.i.a1.offsetDateTime_.not(offsetDateTime0).query.get.map(_ ==> List(a, b, c))
      _ <- Entity.i.a1.offsetDateTime_.not(offsetDateTime1).query.get.map(_ ==> List(b, c))
      _ <- Entity.i.a1.offsetDateTime_.not(offsetDateTime2).query.get.map(_ ==> List(a, c))
      _ <- Entity.i.a1.offsetDateTime_.not(offsetDateTime3).query.get.map(_ ==> List(a, b))
      _ <- Entity.i.a1.offsetDateTime_.not(Seq(offsetDateTime0)).query.get.map(_ ==> List(a, b, c))
      _ <- Entity.i.a1.offsetDateTime_.not(Seq(offsetDateTime1)).query.get.map(_ ==> List(b, c))
      _ <- Entity.i.a1.offsetDateTime_.not(Seq(offsetDateTime2)).query.get.map(_ ==> List(a, c))
      _ <- Entity.i.a1.offsetDateTime_.not(Seq(offsetDateTime3)).query.get.map(_ ==> List(a, b))
      // OR semantics for multiple args
      _ <- Entity.i.a1.offsetDateTime_.not(offsetDateTime0, offsetDateTime1).query.get.map(_ ==> List(b, c))
      _ <- Entity.i.a1.offsetDateTime_.not(offsetDateTime1, offsetDateTime2).query.get.map(_ ==> List(c))
      _ <- Entity.i.a1.offsetDateTime_.not(offsetDateTime2, offsetDateTime3).query.get.map(_ ==> List(a))
      _ <- Entity.i.a1.offsetDateTime_.not(Seq(offsetDateTime0, offsetDateTime1)).query.get.map(_ ==> List(b, c))
      _ <- Entity.i.a1.offsetDateTime_.not(Seq(offsetDateTime1, offsetDateTime2)).query.get.map(_ ==> List(c))
      _ <- Entity.i.a1.offsetDateTime_.not(Seq(offsetDateTime2, offsetDateTime3)).query.get.map(_ ==> List(a))
      // Empty Seq of negation args matches all asserted values (non-null)
      _ <- Entity.i.a1.offsetDateTime_.not(Seq.empty[OffsetDateTime]).query.get.map(_ ==> List(a, b, c))

      // Match value ranges without returning them
      _ <- Entity.i.a1.offsetDateTime_.<(offsetDateTime2).query.get.map(_ ==> List(a))
      _ <- Entity.i.a1.offsetDateTime_.>(offsetDateTime2).query.get.map(_ ==> List(c))
      _ <- Entity.i.a1.offsetDateTime_.<=(offsetDateTime2).query.get.map(_ ==> List(a, b))
      _ <- Entity.i.a1.offsetDateTime_.>=(offsetDateTime2).query.get.map(_ ==> List(b, c))
    } yield ()
  }


  "Optional" - types {
    val a = (1, Some(offsetDateTime1))
    val b = (2, Some(offsetDateTime2))
    val c = (3, Some(offsetDateTime3))
    val x = (4, Option.empty[OffsetDateTime])
    for {
      _ <- Entity.i.offsetDateTime_?.insert(List(a, b, c, x)).transact

      // Find all optional attribute values
      _ <- Entity.i.a1.offsetDateTime_?.query.get.map(_ ==> List(a, b, c, x))

      // Find optional values matching
      _ <- Entity.i.a1.offsetDateTime_?(Some(offsetDateTime0)).query.get.map(_ ==> List())
      _ <- Entity.i.a1.offsetDateTime_?(Some(offsetDateTime1)).query.get.map(_ ==> List(a))

      // None matches non-asserted/null values
      _ <- Entity.i.a1.offsetDateTime_?(Option.empty[OffsetDateTime]).query.get.map(_ ==> List(x))
      // Easier to apply nothing to tacit attribute
      _ <- Entity.i.a1.offsetDateTime_().query.get.map(_ ==> List(4))
    } yield ()
  }


  "Combinations" - types {
    for {
      _ <- Entity.i.offsetDateTime.insert(
        (1, offsetDateTime1),
        (2, offsetDateTime2),
        (3, offsetDateTime3),
        (4, offsetDateTime4),
        (5, offsetDateTime5),
        (6, offsetDateTime6),
        (7, offsetDateTime7),
        (8, offsetDateTime8),
        (9, offsetDateTime9),
      ).transact

      _ <- Entity.i.a1.offsetDateTime_.>(offsetDateTime2).query.get.map(_ ==> List(3, 4, 5, 6, 7, 8, 9))
      _ <- Entity.i.a1.offsetDateTime_.>(offsetDateTime2).offsetDateTime_.<=(offsetDateTime8).query.get.map(_ ==> List(3, 4, 5, 6, 7, 8))
      _ <- Entity.i.a1.offsetDateTime_.>(offsetDateTime2).offsetDateTime_.<=(offsetDateTime8).offsetDateTime_.not(offsetDateTime4, offsetDateTime5).query.get.map(_ ==> List(3, 6, 7, 8))
    } yield ()
  }
}
