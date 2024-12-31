// GENERATED CODE ********************************
package molecule.coreTests.spi.filter.one.types

import java.time.LocalDate
import molecule.core.api.Api_async
import molecule.core.spi.Spi_async
import molecule.core.util.Executor._
import molecule.coreTests.domains.dsl.Types._
import molecule.coreTests.setup._

case class FilterOne_LocalDate_(
  suite: Test,
  api: Api_async with Spi_async with DbProviders
) extends TestUtils {

  import api._
  import suite._

  "Mandatory" - types { implicit conn =>
    val a = (1, localDate1)
    val b = (2, localDate2)
    val c = (3, localDate3)
    for {
      _ <- Entity.i.localDate.insert(List(a, b, c)).transact

      // Find all attribute values
      _ <- Entity.i.a1.localDate.query.get.map(_ ==> List(a, b, c))

      // Find value(s) matching
      _ <- Entity.i.a1.localDate(localDate0).query.get.map(_ ==> List())
      _ <- Entity.i.a1.localDate(localDate1).query.get.map(_ ==> List(a))
      _ <- Entity.i.a1.localDate(Seq(localDate0)).query.get.map(_ ==> List())
      _ <- Entity.i.a1.localDate(Seq(localDate1)).query.get.map(_ ==> List(a))
      // OR semantics for multiple args ("is this or that")
      _ <- Entity.i.a1.localDate(localDate1, localDate2).query.get.map(_ ==> List(a, b))
      _ <- Entity.i.a1.localDate(localDate1, localDate0).query.get.map(_ ==> List(a))
      _ <- Entity.i.a1.localDate(Seq(localDate1, localDate2)).query.get.map(_ ==> List(a, b))
      _ <- Entity.i.a1.localDate(Seq(localDate1, localDate0)).query.get.map(_ ==> List(a))
      // Empty Seq of args matches no values
      _ <- Entity.i.a1.localDate(Seq.empty[LocalDate]).query.get.map(_ ==> List())

      // Find values not matching
      _ <- Entity.i.a1.localDate.not(localDate0).query.get.map(_ ==> List(a, b, c))
      _ <- Entity.i.a1.localDate.not(localDate1).query.get.map(_ ==> List(b, c))
      _ <- Entity.i.a1.localDate.not(localDate2).query.get.map(_ ==> List(a, c))
      _ <- Entity.i.a1.localDate.not(localDate3).query.get.map(_ ==> List(a, b))
      _ <- Entity.i.a1.localDate.not(Seq(localDate0)).query.get.map(_ ==> List(a, b, c))
      _ <- Entity.i.a1.localDate.not(Seq(localDate1)).query.get.map(_ ==> List(b, c))
      _ <- Entity.i.a1.localDate.not(Seq(localDate2)).query.get.map(_ ==> List(a, c))
      _ <- Entity.i.a1.localDate.not(Seq(localDate3)).query.get.map(_ ==> List(a, b))
      // OR semantics for multiple args
      _ <- Entity.i.a1.localDate.not(localDate0, localDate1).query.get.map(_ ==> List(b, c))
      _ <- Entity.i.a1.localDate.not(localDate1, localDate2).query.get.map(_ ==> List(c))
      _ <- Entity.i.a1.localDate.not(localDate2, localDate3).query.get.map(_ ==> List(a))
      _ <- Entity.i.a1.localDate.not(Seq(localDate0, localDate1)).query.get.map(_ ==> List(b, c))
      _ <- Entity.i.a1.localDate.not(Seq(localDate1, localDate2)).query.get.map(_ ==> List(c))
      _ <- Entity.i.a1.localDate.not(Seq(localDate2, localDate3)).query.get.map(_ ==> List(a))
      // Empty Seq of negation args matches all values
      _ <- Entity.i.a1.localDate.not(Seq.empty[LocalDate]).query.get.map(_ ==> List(a, b, c))

      // Find values in range
      _ <- Entity.i.a1.localDate.<(localDate2).query.get.map(_ ==> List(a))
      _ <- Entity.i.a1.localDate.>(localDate2).query.get.map(_ ==> List(c))
      _ <- Entity.i.a1.localDate.<=(localDate2).query.get.map(_ ==> List(a, b))
      _ <- Entity.i.a1.localDate.>=(localDate2).query.get.map(_ ==> List(b, c))
    } yield ()
  }


  "Tacit" - types { implicit conn =>
    val (a, b, c, x) = (1, 2, 3, 4)
    for {
      _ <- Entity.i.localDate_?.insert(List(
        (a, Some(localDate1)),
        (b, Some(localDate2)),
        (c, Some(localDate3)),
        (x, None)
      )).transact

      // Match asserted attribute without returning its value
      _ <- Entity.i.a1.localDate_.query.get.map(_ ==> List(a, b, c))

      // Match non-asserted attribute (null)
      _ <- Entity.i.a1.localDate_().query.get.map(_ ==> List(x))

      // Match attribute values without returning them
      _ <- Entity.i.a1.localDate_(localDate0).query.get.map(_ ==> List())
      _ <- Entity.i.a1.localDate_(localDate1).query.get.map(_ ==> List(a))
      _ <- Entity.i.a1.localDate_(Seq(localDate0)).query.get.map(_ ==> List())
      _ <- Entity.i.a1.localDate_(Seq(localDate1)).query.get.map(_ ==> List(a))
      // OR semantics for multiple args ("is this or that")
      _ <- Entity.i.a1.localDate_(localDate1, localDate2).query.get.map(_ ==> List(a, b))
      _ <- Entity.i.a1.localDate_(localDate1, localDate0).query.get.map(_ ==> List(a))
      _ <- Entity.i.a1.localDate_(Seq(localDate1, localDate2)).query.get.map(_ ==> List(a, b))
      _ <- Entity.i.a1.localDate_(Seq(localDate1, localDate0)).query.get.map(_ ==> List(a))
      // Empty Seq of args matches no values
      _ <- Entity.i.a1.localDate_(Seq.empty[LocalDate]).query.get.map(_ ==> List())

      // Match non-matching values without returning them
      _ <- Entity.i.a1.localDate_.not(localDate0).query.get.map(_ ==> List(a, b, c))
      _ <- Entity.i.a1.localDate_.not(localDate1).query.get.map(_ ==> List(b, c))
      _ <- Entity.i.a1.localDate_.not(localDate2).query.get.map(_ ==> List(a, c))
      _ <- Entity.i.a1.localDate_.not(localDate3).query.get.map(_ ==> List(a, b))
      _ <- Entity.i.a1.localDate_.not(Seq(localDate0)).query.get.map(_ ==> List(a, b, c))
      _ <- Entity.i.a1.localDate_.not(Seq(localDate1)).query.get.map(_ ==> List(b, c))
      _ <- Entity.i.a1.localDate_.not(Seq(localDate2)).query.get.map(_ ==> List(a, c))
      _ <- Entity.i.a1.localDate_.not(Seq(localDate3)).query.get.map(_ ==> List(a, b))
      // OR semantics for multiple args
      _ <- Entity.i.a1.localDate_.not(localDate0, localDate1).query.get.map(_ ==> List(b, c))
      _ <- Entity.i.a1.localDate_.not(localDate1, localDate2).query.get.map(_ ==> List(c))
      _ <- Entity.i.a1.localDate_.not(localDate2, localDate3).query.get.map(_ ==> List(a))
      _ <- Entity.i.a1.localDate_.not(Seq(localDate0, localDate1)).query.get.map(_ ==> List(b, c))
      _ <- Entity.i.a1.localDate_.not(Seq(localDate1, localDate2)).query.get.map(_ ==> List(c))
      _ <- Entity.i.a1.localDate_.not(Seq(localDate2, localDate3)).query.get.map(_ ==> List(a))
      // Empty Seq of negation args matches all asserted values (non-null)
      _ <- Entity.i.a1.localDate_.not(Seq.empty[LocalDate]).query.get.map(_ ==> List(a, b, c))

      // Match value ranges without returning them
      _ <- Entity.i.a1.localDate_.<(localDate2).query.get.map(_ ==> List(a))
      _ <- Entity.i.a1.localDate_.>(localDate2).query.get.map(_ ==> List(c))
      _ <- Entity.i.a1.localDate_.<=(localDate2).query.get.map(_ ==> List(a, b))
      _ <- Entity.i.a1.localDate_.>=(localDate2).query.get.map(_ ==> List(b, c))
    } yield ()
  }


  "Optional" - types { implicit conn =>
    val a = (1, Some(localDate1))
    val b = (2, Some(localDate2))
    val c = (3, Some(localDate3))
    val x = (4, Option.empty[LocalDate])
    for {
      _ <- Entity.i.localDate_?.insert(List(a, b, c, x)).transact

      // Find all optional attribute values
      _ <- Entity.i.a1.localDate_?.query.get.map(_ ==> List(a, b, c, x))

      // Find optional values matching
      _ <- Entity.i.a1.localDate_?(Some(localDate0)).query.get.map(_ ==> List())
      _ <- Entity.i.a1.localDate_?(Some(localDate1)).query.get.map(_ ==> List(a))

      // None matches non-asserted/null values
      _ <- Entity.i.a1.localDate_?(Option.empty[LocalDate]).query.get.map(_ ==> List(x))
      // Easier to apply nothing to tacit attribute
      _ <- Entity.i.a1.localDate_().query.get.map(_ ==> List(4))
    } yield ()
  }


  "Combinations" - types { implicit conn =>
    for {
      _ <- Entity.i.localDate.insert(
        (1, localDate1),
        (2, localDate2),
        (3, localDate3),
        (4, localDate4),
        (5, localDate5),
        (6, localDate6),
        (7, localDate7),
        (8, localDate8),
        (9, localDate9),
      ).transact

      _ <- Entity.i.a1.localDate_.>(localDate2).query.get.map(_ ==> List(3, 4, 5, 6, 7, 8, 9))
      _ <- Entity.i.a1.localDate_.>(localDate2).localDate_.<=(localDate8).query.get.map(_ ==> List(3, 4, 5, 6, 7, 8))
      _ <- Entity.i.a1.localDate_.>(localDate2).localDate_.<=(localDate8).localDate_.not(localDate4, localDate5).query.get.map(_ ==> List(3, 6, 7, 8))
    } yield ()
  }
}
