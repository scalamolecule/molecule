// GENERATED CODE ********************************
package molecule.coreTests.spi.filter.one.types

import java.time.OffsetDateTime
import molecule.core.api.ApiAsync
import molecule.core.spi.SpiAsync
import molecule.core.util.Executor._
import molecule.coreTests.dataModels.dsl.Types._
import molecule.coreTests.setup.CoreTestSuite
import utest._

trait FilterOne_OffsetDateTime_ extends CoreTestSuite with ApiAsync { spi: SpiAsync =>

  override lazy val tests = Tests {

    "Mandatory" - types { implicit conn =>
      val a = (1, offsetDateTime1)
      val b = (2, offsetDateTime2)
      val c = (3, offsetDateTime3)
      for {
        _ <- Ns.i.offsetDateTime.insert(List(a, b, c)).transact

        // Find all attribute values
        _ <- Ns.i.a1.offsetDateTime.query.get.map(_ ==> List(a, b, c))

        // Find value(s) matching
        _ <- Ns.i.a1.offsetDateTime(offsetDateTime0).query.get.map(_ ==> List())
        _ <- Ns.i.a1.offsetDateTime(offsetDateTime1).query.get.map(_ ==> List(a))
        _ <- Ns.i.a1.offsetDateTime(Seq(offsetDateTime0)).query.get.map(_ ==> List())
        _ <- Ns.i.a1.offsetDateTime(Seq(offsetDateTime1)).query.get.map(_ ==> List(a))
        // OR semantics for multiple args ("is this or that")
        _ <- Ns.i.a1.offsetDateTime(offsetDateTime1, offsetDateTime2).query.get.map(_ ==> List(a, b))
        _ <- Ns.i.a1.offsetDateTime(offsetDateTime1, offsetDateTime0).query.get.map(_ ==> List(a))
        _ <- Ns.i.a1.offsetDateTime(Seq(offsetDateTime1, offsetDateTime2)).query.get.map(_ ==> List(a, b))
        _ <- Ns.i.a1.offsetDateTime(Seq(offsetDateTime1, offsetDateTime0)).query.get.map(_ ==> List(a))
        // Empty Seq of args matches no values
        _ <- Ns.i.a1.offsetDateTime(Seq.empty[OffsetDateTime]).query.get.map(_ ==> List())

        // Find values not matching
        _ <- Ns.i.a1.offsetDateTime.not(offsetDateTime0).query.get.map(_ ==> List(a, b, c))
        _ <- Ns.i.a1.offsetDateTime.not(offsetDateTime1).query.get.map(_ ==> List(b, c))
        _ <- Ns.i.a1.offsetDateTime.not(offsetDateTime2).query.get.map(_ ==> List(a, c))
        _ <- Ns.i.a1.offsetDateTime.not(offsetDateTime3).query.get.map(_ ==> List(a, b))
        _ <- Ns.i.a1.offsetDateTime.not(Seq(offsetDateTime0)).query.get.map(_ ==> List(a, b, c))
        _ <- Ns.i.a1.offsetDateTime.not(Seq(offsetDateTime1)).query.get.map(_ ==> List(b, c))
        _ <- Ns.i.a1.offsetDateTime.not(Seq(offsetDateTime2)).query.get.map(_ ==> List(a, c))
        _ <- Ns.i.a1.offsetDateTime.not(Seq(offsetDateTime3)).query.get.map(_ ==> List(a, b))
        // OR semantics for multiple args
        _ <- Ns.i.a1.offsetDateTime.not(offsetDateTime0, offsetDateTime1).query.get.map(_ ==> List(b, c))
        _ <- Ns.i.a1.offsetDateTime.not(offsetDateTime1, offsetDateTime2).query.get.map(_ ==> List(c))
        _ <- Ns.i.a1.offsetDateTime.not(offsetDateTime2, offsetDateTime3).query.get.map(_ ==> List(a))
        _ <- Ns.i.a1.offsetDateTime.not(Seq(offsetDateTime0, offsetDateTime1)).query.get.map(_ ==> List(b, c))
        _ <- Ns.i.a1.offsetDateTime.not(Seq(offsetDateTime1, offsetDateTime2)).query.get.map(_ ==> List(c))
        _ <- Ns.i.a1.offsetDateTime.not(Seq(offsetDateTime2, offsetDateTime3)).query.get.map(_ ==> List(a))
        // Empty Seq of negation args matches all values
        _ <- Ns.i.a1.offsetDateTime.not(Seq.empty[OffsetDateTime]).query.get.map(_ ==> List(a, b, c))

        // Find values in range
        _ <- Ns.i.a1.offsetDateTime.<(offsetDateTime2).query.get.map(_ ==> List(a))
        _ <- Ns.i.a1.offsetDateTime.>(offsetDateTime2).query.get.map(_ ==> List(c))
        _ <- Ns.i.a1.offsetDateTime.<=(offsetDateTime2).query.get.map(_ ==> List(a, b))
        _ <- Ns.i.a1.offsetDateTime.>=(offsetDateTime2).query.get.map(_ ==> List(b, c))
      } yield ()
    }


    "Tacit" - types { implicit conn =>
      val (a, b, c, x) = (1, 2, 3, 4)
      for {
        _ <- Ns.i.offsetDateTime_?.insert(List(
          (a, Some(offsetDateTime1)),
          (b, Some(offsetDateTime2)),
          (c, Some(offsetDateTime3)),
          (x, None)
        )).transact

        // Match asserted attribute without returning its value
        _ <- Ns.i.a1.offsetDateTime_.query.get.map(_ ==> List(a, b, c))

        // Match non-asserted attribute (null)
        _ <- Ns.i.a1.offsetDateTime_().query.get.map(_ ==> List(x))

        // Match attribute values without returning them
        _ <- Ns.i.a1.offsetDateTime_(offsetDateTime0).query.get.map(_ ==> List())
        _ <- Ns.i.a1.offsetDateTime_(offsetDateTime1).query.get.map(_ ==> List(a))
        _ <- Ns.i.a1.offsetDateTime_(Seq(offsetDateTime0)).query.get.map(_ ==> List())
        _ <- Ns.i.a1.offsetDateTime_(Seq(offsetDateTime1)).query.get.map(_ ==> List(a))
        // OR semantics for multiple args ("is this or that")
        _ <- Ns.i.a1.offsetDateTime_(offsetDateTime1, offsetDateTime2).query.get.map(_ ==> List(a, b))
        _ <- Ns.i.a1.offsetDateTime_(offsetDateTime1, offsetDateTime0).query.get.map(_ ==> List(a))
        _ <- Ns.i.a1.offsetDateTime_(Seq(offsetDateTime1, offsetDateTime2)).query.get.map(_ ==> List(a, b))
        _ <- Ns.i.a1.offsetDateTime_(Seq(offsetDateTime1, offsetDateTime0)).query.get.map(_ ==> List(a))
        // Empty Seq of args matches no values
        _ <- Ns.i.a1.offsetDateTime_(Seq.empty[OffsetDateTime]).query.get.map(_ ==> List())

        // Match non-matching values without returning them
        _ <- Ns.i.a1.offsetDateTime_.not(offsetDateTime0).query.get.map(_ ==> List(a, b, c))
        _ <- Ns.i.a1.offsetDateTime_.not(offsetDateTime1).query.get.map(_ ==> List(b, c))
        _ <- Ns.i.a1.offsetDateTime_.not(offsetDateTime2).query.get.map(_ ==> List(a, c))
        _ <- Ns.i.a1.offsetDateTime_.not(offsetDateTime3).query.get.map(_ ==> List(a, b))
        _ <- Ns.i.a1.offsetDateTime_.not(Seq(offsetDateTime0)).query.get.map(_ ==> List(a, b, c))
        _ <- Ns.i.a1.offsetDateTime_.not(Seq(offsetDateTime1)).query.get.map(_ ==> List(b, c))
        _ <- Ns.i.a1.offsetDateTime_.not(Seq(offsetDateTime2)).query.get.map(_ ==> List(a, c))
        _ <- Ns.i.a1.offsetDateTime_.not(Seq(offsetDateTime3)).query.get.map(_ ==> List(a, b))
        // OR semantics for multiple args
        _ <- Ns.i.a1.offsetDateTime_.not(offsetDateTime0, offsetDateTime1).query.get.map(_ ==> List(b, c))
        _ <- Ns.i.a1.offsetDateTime_.not(offsetDateTime1, offsetDateTime2).query.get.map(_ ==> List(c))
        _ <- Ns.i.a1.offsetDateTime_.not(offsetDateTime2, offsetDateTime3).query.get.map(_ ==> List(a))
        _ <- Ns.i.a1.offsetDateTime_.not(Seq(offsetDateTime0, offsetDateTime1)).query.get.map(_ ==> List(b, c))
        _ <- Ns.i.a1.offsetDateTime_.not(Seq(offsetDateTime1, offsetDateTime2)).query.get.map(_ ==> List(c))
        _ <- Ns.i.a1.offsetDateTime_.not(Seq(offsetDateTime2, offsetDateTime3)).query.get.map(_ ==> List(a))
        // Empty Seq of negation args matches all asserted values (non-null)
        _ <- Ns.i.a1.offsetDateTime_.not(Seq.empty[OffsetDateTime]).query.get.map(_ ==> List(a, b, c))

        // Match value ranges without returning them
        _ <- Ns.i.a1.offsetDateTime_.<(offsetDateTime2).query.get.map(_ ==> List(a))
        _ <- Ns.i.a1.offsetDateTime_.>(offsetDateTime2).query.get.map(_ ==> List(c))
        _ <- Ns.i.a1.offsetDateTime_.<=(offsetDateTime2).query.get.map(_ ==> List(a, b))
        _ <- Ns.i.a1.offsetDateTime_.>=(offsetDateTime2).query.get.map(_ ==> List(b, c))
      } yield ()
    }


    "Optional" - types { implicit conn =>
      val a = (1, Some(offsetDateTime1))
      val b = (2, Some(offsetDateTime2))
      val c = (3, Some(offsetDateTime3))
      val x = (4, Option.empty[OffsetDateTime])
      for {
        _ <- Ns.i.offsetDateTime_?.insert(List(a, b, c, x)).transact

        // Find all optional attribute values
        _ <- Ns.i.a1.offsetDateTime_?.query.get.map(_ ==> List(a, b, c, x))

        // Find optional values matching
        _ <- Ns.i.a1.offsetDateTime_?(Some(offsetDateTime0)).query.get.map(_ ==> List())
        _ <- Ns.i.a1.offsetDateTime_?(Some(offsetDateTime1)).query.get.map(_ ==> List(a))

        // None matches non-asserted/null values
        _ <- Ns.i.a1.offsetDateTime_?(Option.empty[OffsetDateTime]).query.get.map(_ ==> List(x))
        // Easier to apply nothing to tacit attribute
        _ <- Ns.i.a1.offsetDateTime_().query.get.map(_ ==> List(4))
      } yield ()
    }
  }
}
