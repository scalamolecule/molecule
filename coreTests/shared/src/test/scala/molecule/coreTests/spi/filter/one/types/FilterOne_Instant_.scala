// GENERATED CODE ********************************
package molecule.coreTests.spi.filter.one.types

import java.time.Instant
import molecule.core.api.Api_async
import molecule.core.spi.Spi_async
import molecule.core.util.Executor._
import molecule.coreTests.dataModels.dsl.Types._
import molecule.coreTests.setup.CoreTestSuite
import utest._

trait FilterOne_Instant_ extends CoreTestSuite with Api_async { spi: Spi_async =>

  override lazy val tests = Tests {

    "Mandatory" - types { implicit conn =>
      val a = (1, instant1)
      val b = (2, instant2)
      val c = (3, instant3)
      for {
        _ <- Ns.i.instant.insert(List(a, b, c)).transact

        // Find all attribute values
        _ <- Ns.i.a1.instant.query.get.map(_ ==> List(a, b, c))

        // Find value(s) matching
        _ <- Ns.i.a1.instant(instant0).query.get.map(_ ==> List())
        _ <- Ns.i.a1.instant(instant1).query.get.map(_ ==> List(a))
        _ <- Ns.i.a1.instant(Seq(instant0)).query.get.map(_ ==> List())
        _ <- Ns.i.a1.instant(Seq(instant1)).query.get.map(_ ==> List(a))
        // OR semantics for multiple args ("is this or that")
        _ <- Ns.i.a1.instant(instant1, instant2).query.get.map(_ ==> List(a, b))
        _ <- Ns.i.a1.instant(instant1, instant0).query.get.map(_ ==> List(a))
        _ <- Ns.i.a1.instant(Seq(instant1, instant2)).query.get.map(_ ==> List(a, b))
        _ <- Ns.i.a1.instant(Seq(instant1, instant0)).query.get.map(_ ==> List(a))
        // Empty Seq of args matches no values
        _ <- Ns.i.a1.instant(Seq.empty[Instant]).query.get.map(_ ==> List())

        // Find values not matching
        _ <- Ns.i.a1.instant.not(instant0).query.get.map(_ ==> List(a, b, c))
        _ <- Ns.i.a1.instant.not(instant1).query.get.map(_ ==> List(b, c))
        _ <- Ns.i.a1.instant.not(instant2).query.get.map(_ ==> List(a, c))
        _ <- Ns.i.a1.instant.not(instant3).query.get.map(_ ==> List(a, b))
        _ <- Ns.i.a1.instant.not(Seq(instant0)).query.get.map(_ ==> List(a, b, c))
        _ <- Ns.i.a1.instant.not(Seq(instant1)).query.get.map(_ ==> List(b, c))
        _ <- Ns.i.a1.instant.not(Seq(instant2)).query.get.map(_ ==> List(a, c))
        _ <- Ns.i.a1.instant.not(Seq(instant3)).query.get.map(_ ==> List(a, b))
        // OR semantics for multiple args
        _ <- Ns.i.a1.instant.not(instant0, instant1).query.get.map(_ ==> List(b, c))
        _ <- Ns.i.a1.instant.not(instant1, instant2).query.get.map(_ ==> List(c))
        _ <- Ns.i.a1.instant.not(instant2, instant3).query.get.map(_ ==> List(a))
        _ <- Ns.i.a1.instant.not(Seq(instant0, instant1)).query.get.map(_ ==> List(b, c))
        _ <- Ns.i.a1.instant.not(Seq(instant1, instant2)).query.get.map(_ ==> List(c))
        _ <- Ns.i.a1.instant.not(Seq(instant2, instant3)).query.get.map(_ ==> List(a))
        // Empty Seq of negation args matches all values
        _ <- Ns.i.a1.instant.not(Seq.empty[Instant]).query.get.map(_ ==> List(a, b, c))

        // Find values in range
        _ <- Ns.i.a1.instant.<(instant2).query.get.map(_ ==> List(a))
        _ <- Ns.i.a1.instant.>(instant2).query.get.map(_ ==> List(c))
        _ <- Ns.i.a1.instant.<=(instant2).query.get.map(_ ==> List(a, b))
        _ <- Ns.i.a1.instant.>=(instant2).query.get.map(_ ==> List(b, c))
      } yield ()
    }


    "Tacit" - types { implicit conn =>
      val (a, b, c, x) = (1, 2, 3, 4)
      for {
        _ <- Ns.i.instant_?.insert(List(
          (a, Some(instant1)),
          (b, Some(instant2)),
          (c, Some(instant3)),
          (x, None)
        )).transact

        // Match asserted attribute without returning its value
        _ <- Ns.i.a1.instant_.query.get.map(_ ==> List(a, b, c))

        // Match non-asserted attribute (null)
        _ <- Ns.i.a1.instant_().query.get.map(_ ==> List(x))

        // Match attribute values without returning them
        _ <- Ns.i.a1.instant_(instant0).query.get.map(_ ==> List())
        _ <- Ns.i.a1.instant_(instant1).query.get.map(_ ==> List(a))
        _ <- Ns.i.a1.instant_(Seq(instant0)).query.get.map(_ ==> List())
        _ <- Ns.i.a1.instant_(Seq(instant1)).query.get.map(_ ==> List(a))
        // OR semantics for multiple args ("is this or that")
        _ <- Ns.i.a1.instant_(instant1, instant2).query.get.map(_ ==> List(a, b))
        _ <- Ns.i.a1.instant_(instant1, instant0).query.get.map(_ ==> List(a))
        _ <- Ns.i.a1.instant_(Seq(instant1, instant2)).query.get.map(_ ==> List(a, b))
        _ <- Ns.i.a1.instant_(Seq(instant1, instant0)).query.get.map(_ ==> List(a))
        // Empty Seq of args matches no values
        _ <- Ns.i.a1.instant_(Seq.empty[Instant]).query.get.map(_ ==> List())

        // Match non-matching values without returning them
        _ <- Ns.i.a1.instant_.not(instant0).query.get.map(_ ==> List(a, b, c))
        _ <- Ns.i.a1.instant_.not(instant1).query.get.map(_ ==> List(b, c))
        _ <- Ns.i.a1.instant_.not(instant2).query.get.map(_ ==> List(a, c))
        _ <- Ns.i.a1.instant_.not(instant3).query.get.map(_ ==> List(a, b))
        _ <- Ns.i.a1.instant_.not(Seq(instant0)).query.get.map(_ ==> List(a, b, c))
        _ <- Ns.i.a1.instant_.not(Seq(instant1)).query.get.map(_ ==> List(b, c))
        _ <- Ns.i.a1.instant_.not(Seq(instant2)).query.get.map(_ ==> List(a, c))
        _ <- Ns.i.a1.instant_.not(Seq(instant3)).query.get.map(_ ==> List(a, b))
        // OR semantics for multiple args
        _ <- Ns.i.a1.instant_.not(instant0, instant1).query.get.map(_ ==> List(b, c))
        _ <- Ns.i.a1.instant_.not(instant1, instant2).query.get.map(_ ==> List(c))
        _ <- Ns.i.a1.instant_.not(instant2, instant3).query.get.map(_ ==> List(a))
        _ <- Ns.i.a1.instant_.not(Seq(instant0, instant1)).query.get.map(_ ==> List(b, c))
        _ <- Ns.i.a1.instant_.not(Seq(instant1, instant2)).query.get.map(_ ==> List(c))
        _ <- Ns.i.a1.instant_.not(Seq(instant2, instant3)).query.get.map(_ ==> List(a))
        // Empty Seq of negation args matches all asserted values (non-null)
        _ <- Ns.i.a1.instant_.not(Seq.empty[Instant]).query.get.map(_ ==> List(a, b, c))

        // Match value ranges without returning them
        _ <- Ns.i.a1.instant_.<(instant2).query.get.map(_ ==> List(a))
        _ <- Ns.i.a1.instant_.>(instant2).query.get.map(_ ==> List(c))
        _ <- Ns.i.a1.instant_.<=(instant2).query.get.map(_ ==> List(a, b))
        _ <- Ns.i.a1.instant_.>=(instant2).query.get.map(_ ==> List(b, c))
      } yield ()
    }


    "Optional" - types { implicit conn =>
      val a = (1, Some(instant1))
      val b = (2, Some(instant2))
      val c = (3, Some(instant3))
      val x = (4, Option.empty[Instant])
      for {
        _ <- Ns.i.instant_?.insert(List(a, b, c, x)).transact

        // Find all optional attribute values
        _ <- Ns.i.a1.instant_?.query.get.map(_ ==> List(a, b, c, x))

        // Find optional values matching
        _ <- Ns.i.a1.instant_?(Some(instant0)).query.get.map(_ ==> List())
        _ <- Ns.i.a1.instant_?(Some(instant1)).query.get.map(_ ==> List(a))

        // None matches non-asserted/null values
        _ <- Ns.i.a1.instant_?(Option.empty[Instant]).query.get.map(_ ==> List(x))
        // Easier to apply nothing to tacit attribute
        _ <- Ns.i.a1.instant_().query.get.map(_ ==> List(4))
      } yield ()
    }
  }
}
