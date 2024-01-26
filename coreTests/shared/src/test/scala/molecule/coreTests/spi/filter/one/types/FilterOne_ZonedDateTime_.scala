// GENERATED CODE ********************************
package molecule.coreTests.spi.filter.one.types

import java.time.ZonedDateTime
import molecule.core.api.ApiAsync
import molecule.core.spi.SpiAsync
import molecule.core.util.Executor._
import molecule.coreTests.async._
import molecule.coreTests.dataModels.core.dsl.Types._
import molecule.coreTests.setup.CoreTestSuite
import utest._

trait FilterOne_ZonedDateTime_ extends CoreTestSuite with ApiAsync { spi: SpiAsync =>

  override lazy val tests = Tests {

    "Mandatory" - types { implicit conn =>
      val a = (1, zonedDateTime1)
      val b = (2, zonedDateTime2)
      val c = (3, zonedDateTime3)
      for {
        _ <- Ns.i.zonedDateTime.insert(List(a, b, c)).transact

        // Find all attribute values
        _ <- Ns.i.a1.zonedDateTime.query.get.map(_ ==> List(a, b, c))

        // Find value(s) matching
        _ <- Ns.i.a1.zonedDateTime(zonedDateTime0).query.get.map(_ ==> List())
        _ <- Ns.i.a1.zonedDateTime(zonedDateTime1).query.get.map(_ ==> List(a))
        _ <- Ns.i.a1.zonedDateTime(Seq(zonedDateTime0)).query.get.map(_ ==> List())
        _ <- Ns.i.a1.zonedDateTime(Seq(zonedDateTime1)).query.get.map(_ ==> List(a))
        // OR semantics for multiple args
        _ <- Ns.i.a1.zonedDateTime(zonedDateTime1, zonedDateTime2).query.get.map(_ ==> List(a, b))
        _ <- Ns.i.a1.zonedDateTime(zonedDateTime1, zonedDateTime0).query.get.map(_ ==> List(a))
        _ <- Ns.i.a1.zonedDateTime(Seq(zonedDateTime1, zonedDateTime2)).query.get.map(_ ==> List(a, b))
        _ <- Ns.i.a1.zonedDateTime(Seq(zonedDateTime1, zonedDateTime0)).query.get.map(_ ==> List(a))
        // Empty Seq of args matches no values
        _ <- Ns.i.a1.zonedDateTime(Seq.empty[ZonedDateTime]).query.get.map(_ ==> List())

        // Find values not matching
        _ <- Ns.i.a1.zonedDateTime.not(zonedDateTime0).query.get.map(_ ==> List(a, b, c))
        _ <- Ns.i.a1.zonedDateTime.not(zonedDateTime1).query.get.map(_ ==> List(b, c))
        _ <- Ns.i.a1.zonedDateTime.not(zonedDateTime2).query.get.map(_ ==> List(a, c))
        _ <- Ns.i.a1.zonedDateTime.not(zonedDateTime3).query.get.map(_ ==> List(a, b))
        _ <- Ns.i.a1.zonedDateTime.not(Seq(zonedDateTime0)).query.get.map(_ ==> List(a, b, c))
        _ <- Ns.i.a1.zonedDateTime.not(Seq(zonedDateTime1)).query.get.map(_ ==> List(b, c))
        _ <- Ns.i.a1.zonedDateTime.not(Seq(zonedDateTime2)).query.get.map(_ ==> List(a, c))
        _ <- Ns.i.a1.zonedDateTime.not(Seq(zonedDateTime3)).query.get.map(_ ==> List(a, b))
        // OR semantics for multiple args
        _ <- Ns.i.a1.zonedDateTime.not(zonedDateTime0, zonedDateTime1).query.get.map(_ ==> List(b, c))
        _ <- Ns.i.a1.zonedDateTime.not(zonedDateTime1, zonedDateTime2).query.get.map(_ ==> List(c))
        _ <- Ns.i.a1.zonedDateTime.not(zonedDateTime2, zonedDateTime3).query.get.map(_ ==> List(a))
        _ <- Ns.i.a1.zonedDateTime.not(Seq(zonedDateTime0, zonedDateTime1)).query.get.map(_ ==> List(b, c))
        _ <- Ns.i.a1.zonedDateTime.not(Seq(zonedDateTime1, zonedDateTime2)).query.get.map(_ ==> List(c))
        _ <- Ns.i.a1.zonedDateTime.not(Seq(zonedDateTime2, zonedDateTime3)).query.get.map(_ ==> List(a))
        // Empty Seq of negation args matches all values
        _ <- Ns.i.a1.zonedDateTime.not(Seq.empty[ZonedDateTime]).query.get.map(_ ==> List(a, b, c))

        // Find values in range
        _ <- Ns.i.a1.zonedDateTime.<(zonedDateTime2).query.get.map(_ ==> List(a))
        _ <- Ns.i.a1.zonedDateTime.>(zonedDateTime2).query.get.map(_ ==> List(c))
        _ <- Ns.i.a1.zonedDateTime.<=(zonedDateTime2).query.get.map(_ ==> List(a, b))
        _ <- Ns.i.a1.zonedDateTime.>=(zonedDateTime2).query.get.map(_ ==> List(b, c))
      } yield ()
    }


    "Tacit" - types { implicit conn =>
      val (a, b, c, x) = (1, 2, 3, 4)
      for {
        _ <- Ns.i.zonedDateTime_?.insert(List(
          (a, Some(zonedDateTime1)),
          (b, Some(zonedDateTime2)),
          (c, Some(zonedDateTime3)),
          (x, None)
        )).transact

        // Match asserted attribute without returning its value
        _ <- Ns.i.a1.zonedDateTime_.query.get.map(_ ==> List(a, b, c))

        // Match non-asserted attribute (null)
        _ <- Ns.i.a1.zonedDateTime_().query.get.map(_ ==> List(x))

        // Match attribute values without returning them
        _ <- Ns.i.a1.zonedDateTime_(zonedDateTime0).query.get.map(_ ==> List())
        _ <- Ns.i.a1.zonedDateTime_(zonedDateTime1).query.get.map(_ ==> List(a))
        _ <- Ns.i.a1.zonedDateTime_(Seq(zonedDateTime0)).query.get.map(_ ==> List())
        _ <- Ns.i.a1.zonedDateTime_(Seq(zonedDateTime1)).query.get.map(_ ==> List(a))
        // OR semantics for multiple args
        _ <- Ns.i.a1.zonedDateTime_(zonedDateTime1, zonedDateTime2).query.get.map(_ ==> List(a, b))
        _ <- Ns.i.a1.zonedDateTime_(zonedDateTime1, zonedDateTime0).query.get.map(_ ==> List(a))
        _ <- Ns.i.a1.zonedDateTime_(Seq(zonedDateTime1, zonedDateTime2)).query.get.map(_ ==> List(a, b))
        _ <- Ns.i.a1.zonedDateTime_(Seq(zonedDateTime1, zonedDateTime0)).query.get.map(_ ==> List(a))
        // Empty Seq of args matches no values
        _ <- Ns.i.a1.zonedDateTime_(Seq.empty[ZonedDateTime]).query.get.map(_ ==> List())

        // Match non-matching values without returning them
        _ <- Ns.i.a1.zonedDateTime_.not(zonedDateTime0).query.get.map(_ ==> List(a, b, c))
        _ <- Ns.i.a1.zonedDateTime_.not(zonedDateTime1).query.get.map(_ ==> List(b, c))
        _ <- Ns.i.a1.zonedDateTime_.not(zonedDateTime2).query.get.map(_ ==> List(a, c))
        _ <- Ns.i.a1.zonedDateTime_.not(zonedDateTime3).query.get.map(_ ==> List(a, b))
        _ <- Ns.i.a1.zonedDateTime_.not(Seq(zonedDateTime0)).query.get.map(_ ==> List(a, b, c))
        _ <- Ns.i.a1.zonedDateTime_.not(Seq(zonedDateTime1)).query.get.map(_ ==> List(b, c))
        _ <- Ns.i.a1.zonedDateTime_.not(Seq(zonedDateTime2)).query.get.map(_ ==> List(a, c))
        _ <- Ns.i.a1.zonedDateTime_.not(Seq(zonedDateTime3)).query.get.map(_ ==> List(a, b))
        // OR semantics for multiple args
        _ <- Ns.i.a1.zonedDateTime_.not(zonedDateTime0, zonedDateTime1).query.get.map(_ ==> List(b, c))
        _ <- Ns.i.a1.zonedDateTime_.not(zonedDateTime1, zonedDateTime2).query.get.map(_ ==> List(c))
        _ <- Ns.i.a1.zonedDateTime_.not(zonedDateTime2, zonedDateTime3).query.get.map(_ ==> List(a))
        _ <- Ns.i.a1.zonedDateTime_.not(Seq(zonedDateTime0, zonedDateTime1)).query.get.map(_ ==> List(b, c))
        _ <- Ns.i.a1.zonedDateTime_.not(Seq(zonedDateTime1, zonedDateTime2)).query.get.map(_ ==> List(c))
        _ <- Ns.i.a1.zonedDateTime_.not(Seq(zonedDateTime2, zonedDateTime3)).query.get.map(_ ==> List(a))
        // Empty Seq of negation args matches all asserted values (non-null)
        _ <- Ns.i.a1.zonedDateTime_.not(Seq.empty[ZonedDateTime]).query.get.map(_ ==> List(a, b, c))

        // Match value ranges without returning them
        _ <- Ns.i.a1.zonedDateTime_.<(zonedDateTime2).query.get.map(_ ==> List(a))
        _ <- Ns.i.a1.zonedDateTime_.>(zonedDateTime2).query.get.map(_ ==> List(c))
        _ <- Ns.i.a1.zonedDateTime_.<=(zonedDateTime2).query.get.map(_ ==> List(a, b))
        _ <- Ns.i.a1.zonedDateTime_.>=(zonedDateTime2).query.get.map(_ ==> List(b, c))
      } yield ()
    }


    "Optional" - types { implicit conn =>
      val a = (1, Some(zonedDateTime1))
      val b = (2, Some(zonedDateTime2))
      val c = (3, Some(zonedDateTime3))
      val x = (4, Option.empty[ZonedDateTime])
      for {
        _ <- Ns.i.zonedDateTime_?.insert(List(a, b, c, x)).transact

        // Find all optional attribute values
        _ <- Ns.i.a1.zonedDateTime_?.query.get.map(_ ==> List(a, b, c, x))

        // Find optional values matching
        _ <- Ns.i.a1.zonedDateTime_?(Some(zonedDateTime0)).query.get.map(_ ==> List())
        _ <- Ns.i.a1.zonedDateTime_?(Some(zonedDateTime1)).query.get.map(_ ==> List(a))
        _ <- Ns.i.a1.zonedDateTime_?(Some(Seq(zonedDateTime0))).query.get.map(_ ==> List())
        _ <- Ns.i.a1.zonedDateTime_?(Some(Seq(zonedDateTime1))).query.get.map(_ ==> List(a))
        // OR semantics for Ses of multiple args
        _ <- Ns.i.a1.zonedDateTime_?(Some(Seq(zonedDateTime1, zonedDateTime2))).query.get.map(_ ==> List(a, b))
        _ <- Ns.i.a1.zonedDateTime_?(Some(Seq(zonedDateTime1, zonedDateTime0))).query.get.map(_ ==> List(a))
        // Empty Seq of args matches no values
        _ <- Ns.i.a1.zonedDateTime_?(Some(Seq.empty[ZonedDateTime])).query.get.map(_ ==> List())
        // None matches non-asserted/null values
        _ <- Ns.i.a1.zonedDateTime_?(Option.empty[ZonedDateTime]).query.get.map(_ ==> List(x))
        _ <- Ns.i.a1.zonedDateTime_?(Option.empty[Seq[ZonedDateTime]]).query.get.map(_ ==> List(x))

        // Find optional values not matching
        _ <- Ns.i.a1.zonedDateTime_?.not(Some(zonedDateTime0)).query.get.map(_ ==> List(a, b, c))
        _ <- Ns.i.a1.zonedDateTime_?.not(Some(zonedDateTime1)).query.get.map(_ ==> List(b, c))
        _ <- Ns.i.a1.zonedDateTime_?.not(Some(zonedDateTime2)).query.get.map(_ ==> List(a, c))
        _ <- Ns.i.a1.zonedDateTime_?.not(Some(zonedDateTime3)).query.get.map(_ ==> List(a, b))
        _ <- Ns.i.a1.zonedDateTime_?.not(Some(Seq(zonedDateTime0))).query.get.map(_ ==> List(a, b, c))
        _ <- Ns.i.a1.zonedDateTime_?.not(Some(Seq(zonedDateTime1))).query.get.map(_ ==> List(b, c))
        _ <- Ns.i.a1.zonedDateTime_?.not(Some(Seq(zonedDateTime2))).query.get.map(_ ==> List(a, c))
        _ <- Ns.i.a1.zonedDateTime_?.not(Some(Seq(zonedDateTime3))).query.get.map(_ ==> List(a, b))
        // OR semantics for multiple negation args
        _ <- Ns.i.a1.zonedDateTime_?.not(Some(Seq(zonedDateTime0, zonedDateTime1))).query.get.map(_ ==> List(b, c))
        _ <- Ns.i.a1.zonedDateTime_?.not(Some(Seq(zonedDateTime1, zonedDateTime2))).query.get.map(_ ==> List(c))
        _ <- Ns.i.a1.zonedDateTime_?.not(Some(Seq(zonedDateTime2, zonedDateTime3))).query.get.map(_ ==> List(a))
        // Empty Seq of negation args matches all asserted values (non-null)
        _ <- Ns.i.a1.zonedDateTime_?.not(Some(Seq.empty[ZonedDateTime])).query.get.map(_ ==> List(a, b, c))
        // Negating None matches all asserted values (non-null)
        _ <- Ns.i.a1.zonedDateTime_?.not(Option.empty[ZonedDateTime]).query.get.map(_ ==> List(a, b, c))
        _ <- Ns.i.a1.zonedDateTime_?.not(Option.empty[Seq[ZonedDateTime]]).query.get.map(_ ==> List(a, b, c))

        // Find optional values in range
        _ <- Ns.i.a1.zonedDateTime_?.<(Some(zonedDateTime2)).query.get.map(_ ==> List(a))
        _ <- Ns.i.a1.zonedDateTime_?.>(Some(zonedDateTime2)).query.get.map(_ ==> List(c))
        _ <- Ns.i.a1.zonedDateTime_?.<=(Some(zonedDateTime2)).query.get.map(_ ==> List(a, b))
        _ <- Ns.i.a1.zonedDateTime_?.>=(Some(zonedDateTime2)).query.get.map(_ ==> List(b, c))
        // None can't be compared and returns empty result
        _ <- Ns.i.a1.zonedDateTime_?.<(None).query.get.map(_ ==> List())
        _ <- Ns.i.a1.zonedDateTime_?.<=(None).query.get.map(_ ==> List())
        _ <- Ns.i.a1.zonedDateTime_?.>(None).query.get.map(_ ==> List())
        _ <- Ns.i.a1.zonedDateTime_?.>=(None).query.get.map(_ ==> List())
      } yield ()
    }
  }
}
