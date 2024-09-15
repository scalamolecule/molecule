// GENERATED CODE ********************************
package molecule.coreTests.spi.filter.one.types

import java.time.Duration
import molecule.core.api.ApiAsync
import molecule.core.spi.SpiAsync
import molecule.core.util.Executor._
import molecule.coreTests.async._
import molecule.coreTests.dataModels.dsl.Types._
import molecule.coreTests.setup.CoreTestSuite
import utest._

trait FilterOne_Duration_ extends CoreTestSuite with ApiAsync { spi: SpiAsync =>

  override lazy val tests = Tests {

    "Mandatory" - types { implicit conn =>
      val a = (1, duration1)
      val b = (2, duration2)
      val c = (3, duration3)
      for {
        _ <- Ns.i.duration.insert(List(a, b, c)).transact

        // Find all attribute values
        _ <- Ns.i.a1.duration.query.get.map(_ ==> List(a, b, c))

        // Find value(s) matching
        _ <- Ns.i.a1.duration(duration0).query.get.map(_ ==> List())
        _ <- Ns.i.a1.duration(duration1).query.get.map(_ ==> List(a))
        _ <- Ns.i.a1.duration(Seq(duration0)).query.get.map(_ ==> List())
        _ <- Ns.i.a1.duration(Seq(duration1)).query.get.map(_ ==> List(a))
        // OR semantics for multiple args ("is this or that")
        _ <- Ns.i.a1.duration(duration1, duration2).query.get.map(_ ==> List(a, b))
        _ <- Ns.i.a1.duration(duration1, duration0).query.get.map(_ ==> List(a))
        _ <- Ns.i.a1.duration(Seq(duration1, duration2)).query.get.map(_ ==> List(a, b))
        _ <- Ns.i.a1.duration(Seq(duration1, duration0)).query.get.map(_ ==> List(a))
        // Empty Seq of args matches no values
        _ <- Ns.i.a1.duration(Seq.empty[Duration]).query.get.map(_ ==> List())

        // Find values not matching
        _ <- Ns.i.a1.duration.not(duration0).query.get.map(_ ==> List(a, b, c))
        _ <- Ns.i.a1.duration.not(duration1).query.get.map(_ ==> List(b, c))
        _ <- Ns.i.a1.duration.not(duration2).query.get.map(_ ==> List(a, c))
        _ <- Ns.i.a1.duration.not(duration3).query.get.map(_ ==> List(a, b))
        _ <- Ns.i.a1.duration.not(Seq(duration0)).query.get.map(_ ==> List(a, b, c))
        _ <- Ns.i.a1.duration.not(Seq(duration1)).query.get.map(_ ==> List(b, c))
        _ <- Ns.i.a1.duration.not(Seq(duration2)).query.get.map(_ ==> List(a, c))
        _ <- Ns.i.a1.duration.not(Seq(duration3)).query.get.map(_ ==> List(a, b))
        // OR semantics for multiple args
        _ <- Ns.i.a1.duration.not(duration0, duration1).query.get.map(_ ==> List(b, c))
        _ <- Ns.i.a1.duration.not(duration1, duration2).query.get.map(_ ==> List(c))
        _ <- Ns.i.a1.duration.not(duration2, duration3).query.get.map(_ ==> List(a))
        _ <- Ns.i.a1.duration.not(Seq(duration0, duration1)).query.get.map(_ ==> List(b, c))
        _ <- Ns.i.a1.duration.not(Seq(duration1, duration2)).query.get.map(_ ==> List(c))
        _ <- Ns.i.a1.duration.not(Seq(duration2, duration3)).query.get.map(_ ==> List(a))
        // Empty Seq of negation args matches all values
        _ <- Ns.i.a1.duration.not(Seq.empty[Duration]).query.get.map(_ ==> List(a, b, c))

        // Find values in range
        _ <- Ns.i.a1.duration.<(duration2).query.get.map(_ ==> List(a))
        _ <- Ns.i.a1.duration.>(duration2).query.get.map(_ ==> List(c))
        _ <- Ns.i.a1.duration.<=(duration2).query.get.map(_ ==> List(a, b))
        _ <- Ns.i.a1.duration.>=(duration2).query.get.map(_ ==> List(b, c))
      } yield ()
    }


    "Tacit" - types { implicit conn =>
      val (a, b, c, x) = (1, 2, 3, 4)
      for {
        _ <- Ns.i.duration_?.insert(List(
          (a, Some(duration1)),
          (b, Some(duration2)),
          (c, Some(duration3)),
          (x, None)
        )).transact

        // Match asserted attribute without returning its value
        _ <- Ns.i.a1.duration_.query.get.map(_ ==> List(a, b, c))

        // Match non-asserted attribute (null)
        _ <- Ns.i.a1.duration_().query.get.map(_ ==> List(x))

        // Match attribute values without returning them
        _ <- Ns.i.a1.duration_(duration0).query.get.map(_ ==> List())
        _ <- Ns.i.a1.duration_(duration1).query.get.map(_ ==> List(a))
        _ <- Ns.i.a1.duration_(Seq(duration0)).query.get.map(_ ==> List())
        _ <- Ns.i.a1.duration_(Seq(duration1)).query.get.map(_ ==> List(a))
        // OR semantics for multiple args ("is this or that")
        _ <- Ns.i.a1.duration_(duration1, duration2).query.get.map(_ ==> List(a, b))
        _ <- Ns.i.a1.duration_(duration1, duration0).query.get.map(_ ==> List(a))
        _ <- Ns.i.a1.duration_(Seq(duration1, duration2)).query.get.map(_ ==> List(a, b))
        _ <- Ns.i.a1.duration_(Seq(duration1, duration0)).query.get.map(_ ==> List(a))
        // Empty Seq of args matches no values
        _ <- Ns.i.a1.duration_(Seq.empty[Duration]).query.get.map(_ ==> List())

        // Match non-matching values without returning them
        _ <- Ns.i.a1.duration_.not(duration0).query.get.map(_ ==> List(a, b, c))
        _ <- Ns.i.a1.duration_.not(duration1).query.get.map(_ ==> List(b, c))
        _ <- Ns.i.a1.duration_.not(duration2).query.get.map(_ ==> List(a, c))
        _ <- Ns.i.a1.duration_.not(duration3).query.get.map(_ ==> List(a, b))
        _ <- Ns.i.a1.duration_.not(Seq(duration0)).query.get.map(_ ==> List(a, b, c))
        _ <- Ns.i.a1.duration_.not(Seq(duration1)).query.get.map(_ ==> List(b, c))
        _ <- Ns.i.a1.duration_.not(Seq(duration2)).query.get.map(_ ==> List(a, c))
        _ <- Ns.i.a1.duration_.not(Seq(duration3)).query.get.map(_ ==> List(a, b))
        // OR semantics for multiple args
        _ <- Ns.i.a1.duration_.not(duration0, duration1).query.get.map(_ ==> List(b, c))
        _ <- Ns.i.a1.duration_.not(duration1, duration2).query.get.map(_ ==> List(c))
        _ <- Ns.i.a1.duration_.not(duration2, duration3).query.get.map(_ ==> List(a))
        _ <- Ns.i.a1.duration_.not(Seq(duration0, duration1)).query.get.map(_ ==> List(b, c))
        _ <- Ns.i.a1.duration_.not(Seq(duration1, duration2)).query.get.map(_ ==> List(c))
        _ <- Ns.i.a1.duration_.not(Seq(duration2, duration3)).query.get.map(_ ==> List(a))
        // Empty Seq of negation args matches all asserted values (non-null)
        _ <- Ns.i.a1.duration_.not(Seq.empty[Duration]).query.get.map(_ ==> List(a, b, c))

        // Match value ranges without returning them
        _ <- Ns.i.a1.duration_.<(duration2).query.get.map(_ ==> List(a))
        _ <- Ns.i.a1.duration_.>(duration2).query.get.map(_ ==> List(c))
        _ <- Ns.i.a1.duration_.<=(duration2).query.get.map(_ ==> List(a, b))
        _ <- Ns.i.a1.duration_.>=(duration2).query.get.map(_ ==> List(b, c))
      } yield ()
    }


    "Optional" - types { implicit conn =>
      val a = (1, Some(duration1))
      val b = (2, Some(duration2))
      val c = (3, Some(duration3))
      val x = (4, Option.empty[Duration])
      for {
        _ <- Ns.i.duration_?.insert(List(a, b, c, x)).transact

        // Find all optional attribute values
        _ <- Ns.i.a1.duration_?.query.get.map(_ ==> List(a, b, c, x))

        // Find optional values matching
        _ <- Ns.i.a1.duration_?(Some(duration0)).query.get.map(_ ==> List())
        _ <- Ns.i.a1.duration_?(Some(duration1)).query.get.map(_ ==> List(a))

        // None matches non-asserted/null values
        _ <- Ns.i.a1.duration_?(Option.empty[Duration]).query.get.map(_ ==> List(x))
        // Easier to apply nothing to tacit attribute
        _ <- Ns.i.a1.duration_().query.get.map(_ ==> List(4))
      } yield ()
    }
  }
}
