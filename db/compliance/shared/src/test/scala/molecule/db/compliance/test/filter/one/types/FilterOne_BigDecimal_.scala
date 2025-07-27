// GENERATED CODE ********************************
package molecule.db.compliance.test.filter.one.types

import molecule.core.setup.{MUnit, TestUtils}
import molecule.db.compliance.domains.dsl.Types.*
import molecule.db.compliance.setup.DbProviders
import molecule.db.common.api.Api_async
import molecule.db.common.spi.Spi_async
import molecule.db.common.util.Executor.*

case class FilterOne_BigDecimal_(
  suite: MUnit,
  api: Api_async & Spi_async & DbProviders
) extends TestUtils {

  import api.*
  import suite.*

  "Mandatory" - types {
    val a = (1, bigDecimal1)
    val b = (2, bigDecimal2)
    val c = (3, bigDecimal3)
    for {
      _ <- Entity.i.bigDecimal.insert(List(a, b, c)).transact

      // Find all attribute values
      _ <- Entity.i.a1.bigDecimal.query.get.map(_ ==> List(a, b, c))

      // Find value(s) matching
      _ <- Entity.i.a1.bigDecimal(bigDecimal0).query.get.map(_ ==> List())
      _ <- Entity.i.a1.bigDecimal(bigDecimal1).query.get.map(_ ==> List(a))
      _ <- Entity.i.a1.bigDecimal(Seq(bigDecimal0)).query.get.map(_ ==> List())
      _ <- Entity.i.a1.bigDecimal(Seq(bigDecimal1)).query.get.map(_ ==> List(a))
      // OR semantics for multiple args ("is this or that")
      _ <- Entity.i.a1.bigDecimal(bigDecimal1, bigDecimal2).query.get.map(_ ==> List(a, b))
      _ <- Entity.i.a1.bigDecimal(bigDecimal1, bigDecimal0).query.get.map(_ ==> List(a))
      _ <- Entity.i.a1.bigDecimal(Seq(bigDecimal1, bigDecimal2)).query.get.map(_ ==> List(a, b))
      _ <- Entity.i.a1.bigDecimal(Seq(bigDecimal1, bigDecimal0)).query.get.map(_ ==> List(a))
      // Empty Seq of args matches no values
      _ <- Entity.i.a1.bigDecimal(Seq.empty[BigDecimal]).query.get.map(_ ==> List())

      // Find values not matching
      _ <- Entity.i.a1.bigDecimal.not(bigDecimal0).query.get.map(_ ==> List(a, b, c))
      _ <- Entity.i.a1.bigDecimal.not(bigDecimal1).query.get.map(_ ==> List(b, c))
      _ <- Entity.i.a1.bigDecimal.not(bigDecimal2).query.get.map(_ ==> List(a, c))
      _ <- Entity.i.a1.bigDecimal.not(bigDecimal3).query.get.map(_ ==> List(a, b))
      _ <- Entity.i.a1.bigDecimal.not(Seq(bigDecimal0)).query.get.map(_ ==> List(a, b, c))
      _ <- Entity.i.a1.bigDecimal.not(Seq(bigDecimal1)).query.get.map(_ ==> List(b, c))
      _ <- Entity.i.a1.bigDecimal.not(Seq(bigDecimal2)).query.get.map(_ ==> List(a, c))
      _ <- Entity.i.a1.bigDecimal.not(Seq(bigDecimal3)).query.get.map(_ ==> List(a, b))
      // OR semantics for multiple args
      _ <- Entity.i.a1.bigDecimal.not(bigDecimal0, bigDecimal1).query.get.map(_ ==> List(b, c))
      _ <- Entity.i.a1.bigDecimal.not(bigDecimal1, bigDecimal2).query.get.map(_ ==> List(c))
      _ <- Entity.i.a1.bigDecimal.not(bigDecimal2, bigDecimal3).query.get.map(_ ==> List(a))
      _ <- Entity.i.a1.bigDecimal.not(Seq(bigDecimal0, bigDecimal1)).query.get.map(_ ==> List(b, c))
      _ <- Entity.i.a1.bigDecimal.not(Seq(bigDecimal1, bigDecimal2)).query.get.map(_ ==> List(c))
      _ <- Entity.i.a1.bigDecimal.not(Seq(bigDecimal2, bigDecimal3)).query.get.map(_ ==> List(a))
      // Empty Seq of negation args matches all values
      _ <- Entity.i.a1.bigDecimal.not(Seq.empty[BigDecimal]).query.get.map(_ ==> List(a, b, c))

      // Find values in range
      _ <- Entity.i.a1.bigDecimal.<(bigDecimal2).query.get.map(_ ==> List(a))
      _ <- Entity.i.a1.bigDecimal.>(bigDecimal2).query.get.map(_ ==> List(c))
      _ <- Entity.i.a1.bigDecimal.<=(bigDecimal2).query.get.map(_ ==> List(a, b))
      _ <- Entity.i.a1.bigDecimal.>=(bigDecimal2).query.get.map(_ ==> List(b, c))
    } yield ()
  }


  "Tacit" - types {
    val (a, b, c, x) = (1, 2, 3, 4)
    for {
      _ <- Entity.i.bigDecimal_?.insert(List(
        (a, Some(bigDecimal1)),
        (b, Some(bigDecimal2)),
        (c, Some(bigDecimal3)),
        (x, None)
      )).transact

      // Match asserted attribute without returning its value
      _ <- Entity.i.a1.bigDecimal_.query.get.map(_ ==> List(a, b, c))

      // Match non-asserted attribute (null)
      _ <- Entity.i.a1.bigDecimal_().query.get.map(_ ==> List(x))

      // Match attribute values without returning them
      _ <- Entity.i.a1.bigDecimal_(bigDecimal0).query.get.map(_ ==> List())
      _ <- Entity.i.a1.bigDecimal_(bigDecimal1).query.get.map(_ ==> List(a))
      _ <- Entity.i.a1.bigDecimal_(Seq(bigDecimal0)).query.get.map(_ ==> List())
      _ <- Entity.i.a1.bigDecimal_(Seq(bigDecimal1)).query.get.map(_ ==> List(a))
      // OR semantics for multiple args ("is this or that")
      _ <- Entity.i.a1.bigDecimal_(bigDecimal1, bigDecimal2).query.get.map(_ ==> List(a, b))
      _ <- Entity.i.a1.bigDecimal_(bigDecimal1, bigDecimal0).query.get.map(_ ==> List(a))
      _ <- Entity.i.a1.bigDecimal_(Seq(bigDecimal1, bigDecimal2)).query.get.map(_ ==> List(a, b))
      _ <- Entity.i.a1.bigDecimal_(Seq(bigDecimal1, bigDecimal0)).query.get.map(_ ==> List(a))
      // Empty Seq of args matches no values
      _ <- Entity.i.a1.bigDecimal_(Seq.empty[BigDecimal]).query.get.map(_ ==> List())

      // Match non-matching values without returning them
      _ <- Entity.i.a1.bigDecimal_.not(bigDecimal0).query.get.map(_ ==> List(a, b, c))
      _ <- Entity.i.a1.bigDecimal_.not(bigDecimal1).query.get.map(_ ==> List(b, c))
      _ <- Entity.i.a1.bigDecimal_.not(bigDecimal2).query.get.map(_ ==> List(a, c))
      _ <- Entity.i.a1.bigDecimal_.not(bigDecimal3).query.get.map(_ ==> List(a, b))
      _ <- Entity.i.a1.bigDecimal_.not(Seq(bigDecimal0)).query.get.map(_ ==> List(a, b, c))
      _ <- Entity.i.a1.bigDecimal_.not(Seq(bigDecimal1)).query.get.map(_ ==> List(b, c))
      _ <- Entity.i.a1.bigDecimal_.not(Seq(bigDecimal2)).query.get.map(_ ==> List(a, c))
      _ <- Entity.i.a1.bigDecimal_.not(Seq(bigDecimal3)).query.get.map(_ ==> List(a, b))
      // OR semantics for multiple args
      _ <- Entity.i.a1.bigDecimal_.not(bigDecimal0, bigDecimal1).query.get.map(_ ==> List(b, c))
      _ <- Entity.i.a1.bigDecimal_.not(bigDecimal1, bigDecimal2).query.get.map(_ ==> List(c))
      _ <- Entity.i.a1.bigDecimal_.not(bigDecimal2, bigDecimal3).query.get.map(_ ==> List(a))
      _ <- Entity.i.a1.bigDecimal_.not(Seq(bigDecimal0, bigDecimal1)).query.get.map(_ ==> List(b, c))
      _ <- Entity.i.a1.bigDecimal_.not(Seq(bigDecimal1, bigDecimal2)).query.get.map(_ ==> List(c))
      _ <- Entity.i.a1.bigDecimal_.not(Seq(bigDecimal2, bigDecimal3)).query.get.map(_ ==> List(a))
      // Empty Seq of negation args matches all asserted values (non-null)
      _ <- Entity.i.a1.bigDecimal_.not(Seq.empty[BigDecimal]).query.get.map(_ ==> List(a, b, c))

      // Match value ranges without returning them
      _ <- Entity.i.a1.bigDecimal_.<(bigDecimal2).query.get.map(_ ==> List(a))
      _ <- Entity.i.a1.bigDecimal_.>(bigDecimal2).query.get.map(_ ==> List(c))
      _ <- Entity.i.a1.bigDecimal_.<=(bigDecimal2).query.get.map(_ ==> List(a, b))
      _ <- Entity.i.a1.bigDecimal_.>=(bigDecimal2).query.get.map(_ ==> List(b, c))
    } yield ()
  }


  "Optional" - types {
    val a = (1, Some(bigDecimal1))
    val b = (2, Some(bigDecimal2))
    val c = (3, Some(bigDecimal3))
    val x = (4, Option.empty[BigDecimal])
    for {
      _ <- Entity.i.bigDecimal_?.insert(List(a, b, c, x)).transact

      // Find all optional attribute values
      _ <- Entity.i.a1.bigDecimal_?.query.get.map(_ ==> List(a, b, c, x))

      // Find optional values matching
      _ <- Entity.i.a1.bigDecimal_?(Some(bigDecimal0)).query.get.map(_ ==> List())
      _ <- Entity.i.a1.bigDecimal_?(Some(bigDecimal1)).query.get.map(_ ==> List(a))

      // None matches non-asserted/null values
      _ <- Entity.i.a1.bigDecimal_?(Option.empty[BigDecimal]).query.get.map(_ ==> List(x))
      // Easier to apply nothing to tacit attribute
      _ <- Entity.i.a1.bigDecimal_().query.get.map(_ ==> List(4))
    } yield ()
  }


  "Combinations" - types {
    for {
      _ <- Entity.i.bigDecimal.insert(
        (1, bigDecimal1),
        (2, bigDecimal2),
        (3, bigDecimal3),
        (4, bigDecimal4),
        (5, bigDecimal5),
        (6, bigDecimal6),
        (7, bigDecimal7),
        (8, bigDecimal8),
        (9, bigDecimal9),
      ).transact

      _ <- Entity.i.a1.bigDecimal_.>(bigDecimal2).query.get.map(_ ==> List(3, 4, 5, 6, 7, 8, 9))
      _ <- Entity.i.a1.bigDecimal_.>(bigDecimal2).bigDecimal_.<=(bigDecimal8).query.get.map(_ ==> List(3, 4, 5, 6, 7, 8))
      _ <- Entity.i.a1.bigDecimal_.>(bigDecimal2).bigDecimal_.<=(bigDecimal8).bigDecimal_.not(bigDecimal4, bigDecimal5).query.get.map(_ ==> List(3, 6, 7, 8))
    } yield ()
  }
}
