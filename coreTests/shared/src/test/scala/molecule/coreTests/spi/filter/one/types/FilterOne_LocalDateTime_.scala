// GENERATED CODE ********************************
package molecule.coreTests.spi.filter.one.types

import java.time.LocalDateTime
import molecule.core.api.ApiAsync
import molecule.core.spi.SpiAsync
import molecule.core.util.Executor._
import molecule.coreTests.async._
import molecule.coreTests.dataModels.core.dsl.Types._
import molecule.coreTests.setup.CoreTestSuite
import utest._

trait FilterOne_LocalDateTime_ extends CoreTestSuite with ApiAsync { spi: SpiAsync =>

  override lazy val tests = Tests {

    "Mandatory" - types { implicit conn =>
      val a = (1, localDateTime1)
      val b = (2, localDateTime2)
      val c = (3, localDateTime3)
      for {
        _ <- Ns.i.localDateTime.insert(List(a, b, c)).transact

        // Find all attribute values
        _ <- Ns.i.a1.localDateTime.query.get.map(_ ==> List(a, b, c))

        // Find value(s) matching
        _ <- Ns.i.a1.localDateTime(localDateTime0).query.get.map(_ ==> List())
        _ <- Ns.i.a1.localDateTime(localDateTime1).query.get.map(_ ==> List(a))
        _ <- Ns.i.a1.localDateTime(Seq(localDateTime0)).query.get.map(_ ==> List())
        _ <- Ns.i.a1.localDateTime(Seq(localDateTime1)).query.get.map(_ ==> List(a))
        // OR semantics for multiple args ("is this or that")
        _ <- Ns.i.a1.localDateTime(localDateTime1, localDateTime2).query.get.map(_ ==> List(a, b))
        _ <- Ns.i.a1.localDateTime(localDateTime1, localDateTime0).query.get.map(_ ==> List(a))
        _ <- Ns.i.a1.localDateTime(Seq(localDateTime1, localDateTime2)).query.get.map(_ ==> List(a, b))
        _ <- Ns.i.a1.localDateTime(Seq(localDateTime1, localDateTime0)).query.get.map(_ ==> List(a))
        // Empty Seq of args matches no values
        _ <- Ns.i.a1.localDateTime(Seq.empty[LocalDateTime]).query.get.map(_ ==> List())

        // Find values not matching
        _ <- Ns.i.a1.localDateTime.not(localDateTime0).query.get.map(_ ==> List(a, b, c))
        _ <- Ns.i.a1.localDateTime.not(localDateTime1).query.get.map(_ ==> List(b, c))
        _ <- Ns.i.a1.localDateTime.not(localDateTime2).query.get.map(_ ==> List(a, c))
        _ <- Ns.i.a1.localDateTime.not(localDateTime3).query.get.map(_ ==> List(a, b))
        _ <- Ns.i.a1.localDateTime.not(Seq(localDateTime0)).query.get.map(_ ==> List(a, b, c))
        _ <- Ns.i.a1.localDateTime.not(Seq(localDateTime1)).query.get.map(_ ==> List(b, c))
        _ <- Ns.i.a1.localDateTime.not(Seq(localDateTime2)).query.get.map(_ ==> List(a, c))
        _ <- Ns.i.a1.localDateTime.not(Seq(localDateTime3)).query.get.map(_ ==> List(a, b))
        // OR semantics for multiple args
        _ <- Ns.i.a1.localDateTime.not(localDateTime0, localDateTime1).query.get.map(_ ==> List(b, c))
        _ <- Ns.i.a1.localDateTime.not(localDateTime1, localDateTime2).query.get.map(_ ==> List(c))
        _ <- Ns.i.a1.localDateTime.not(localDateTime2, localDateTime3).query.get.map(_ ==> List(a))
        _ <- Ns.i.a1.localDateTime.not(Seq(localDateTime0, localDateTime1)).query.get.map(_ ==> List(b, c))
        _ <- Ns.i.a1.localDateTime.not(Seq(localDateTime1, localDateTime2)).query.get.map(_ ==> List(c))
        _ <- Ns.i.a1.localDateTime.not(Seq(localDateTime2, localDateTime3)).query.get.map(_ ==> List(a))
        // Empty Seq of negation args matches all values
        _ <- Ns.i.a1.localDateTime.not(Seq.empty[LocalDateTime]).query.get.map(_ ==> List(a, b, c))

        // Find values in range
        _ <- Ns.i.a1.localDateTime.<(localDateTime2).query.get.map(_ ==> List(a))
        _ <- Ns.i.a1.localDateTime.>(localDateTime2).query.get.map(_ ==> List(c))
        _ <- Ns.i.a1.localDateTime.<=(localDateTime2).query.get.map(_ ==> List(a, b))
        _ <- Ns.i.a1.localDateTime.>=(localDateTime2).query.get.map(_ ==> List(b, c))
      } yield ()
    }


    "Tacit" - types { implicit conn =>
      val (a, b, c, x) = (1, 2, 3, 4)
      for {
        _ <- Ns.i.localDateTime_?.insert(List(
          (a, Some(localDateTime1)),
          (b, Some(localDateTime2)),
          (c, Some(localDateTime3)),
          (x, None)
        )).transact

        // Match asserted attribute without returning its value
        _ <- Ns.i.a1.localDateTime_.query.get.map(_ ==> List(a, b, c))

        // Match non-asserted attribute (null)
        _ <- Ns.i.a1.localDateTime_().query.get.map(_ ==> List(x))

        // Match attribute values without returning them
        _ <- Ns.i.a1.localDateTime_(localDateTime0).query.get.map(_ ==> List())
        _ <- Ns.i.a1.localDateTime_(localDateTime1).query.get.map(_ ==> List(a))
        _ <- Ns.i.a1.localDateTime_(Seq(localDateTime0)).query.get.map(_ ==> List())
        _ <- Ns.i.a1.localDateTime_(Seq(localDateTime1)).query.get.map(_ ==> List(a))
        // OR semantics for multiple args ("is this or that")
        _ <- Ns.i.a1.localDateTime_(localDateTime1, localDateTime2).query.get.map(_ ==> List(a, b))
        _ <- Ns.i.a1.localDateTime_(localDateTime1, localDateTime0).query.get.map(_ ==> List(a))
        _ <- Ns.i.a1.localDateTime_(Seq(localDateTime1, localDateTime2)).query.get.map(_ ==> List(a, b))
        _ <- Ns.i.a1.localDateTime_(Seq(localDateTime1, localDateTime0)).query.get.map(_ ==> List(a))
        // Empty Seq of args matches no values
        _ <- Ns.i.a1.localDateTime_(Seq.empty[LocalDateTime]).query.get.map(_ ==> List())

        // Match non-matching values without returning them
        _ <- Ns.i.a1.localDateTime_.not(localDateTime0).query.get.map(_ ==> List(a, b, c))
        _ <- Ns.i.a1.localDateTime_.not(localDateTime1).query.get.map(_ ==> List(b, c))
        _ <- Ns.i.a1.localDateTime_.not(localDateTime2).query.get.map(_ ==> List(a, c))
        _ <- Ns.i.a1.localDateTime_.not(localDateTime3).query.get.map(_ ==> List(a, b))
        _ <- Ns.i.a1.localDateTime_.not(Seq(localDateTime0)).query.get.map(_ ==> List(a, b, c))
        _ <- Ns.i.a1.localDateTime_.not(Seq(localDateTime1)).query.get.map(_ ==> List(b, c))
        _ <- Ns.i.a1.localDateTime_.not(Seq(localDateTime2)).query.get.map(_ ==> List(a, c))
        _ <- Ns.i.a1.localDateTime_.not(Seq(localDateTime3)).query.get.map(_ ==> List(a, b))
        // OR semantics for multiple args
        _ <- Ns.i.a1.localDateTime_.not(localDateTime0, localDateTime1).query.get.map(_ ==> List(b, c))
        _ <- Ns.i.a1.localDateTime_.not(localDateTime1, localDateTime2).query.get.map(_ ==> List(c))
        _ <- Ns.i.a1.localDateTime_.not(localDateTime2, localDateTime3).query.get.map(_ ==> List(a))
        _ <- Ns.i.a1.localDateTime_.not(Seq(localDateTime0, localDateTime1)).query.get.map(_ ==> List(b, c))
        _ <- Ns.i.a1.localDateTime_.not(Seq(localDateTime1, localDateTime2)).query.get.map(_ ==> List(c))
        _ <- Ns.i.a1.localDateTime_.not(Seq(localDateTime2, localDateTime3)).query.get.map(_ ==> List(a))
        // Empty Seq of negation args matches all asserted values (non-null)
        _ <- Ns.i.a1.localDateTime_.not(Seq.empty[LocalDateTime]).query.get.map(_ ==> List(a, b, c))

        // Match value ranges without returning them
        _ <- Ns.i.a1.localDateTime_.<(localDateTime2).query.get.map(_ ==> List(a))
        _ <- Ns.i.a1.localDateTime_.>(localDateTime2).query.get.map(_ ==> List(c))
        _ <- Ns.i.a1.localDateTime_.<=(localDateTime2).query.get.map(_ ==> List(a, b))
        _ <- Ns.i.a1.localDateTime_.>=(localDateTime2).query.get.map(_ ==> List(b, c))
      } yield ()
    }


    "Optional" - types { implicit conn =>
      val a = (1, Some(localDateTime1))
      val b = (2, Some(localDateTime2))
      val c = (3, Some(localDateTime3))
      val x = (4, Option.empty[LocalDateTime])
      for {
        _ <- Ns.i.localDateTime_?.insert(List(a, b, c, x)).transact

        // Find all optional attribute values
        _ <- Ns.i.a1.localDateTime_?.query.get.map(_ ==> List(a, b, c, x))

        // Find optional values matching
        _ <- Ns.i.a1.localDateTime_?(Some(localDateTime0)).query.get.map(_ ==> List())
        _ <- Ns.i.a1.localDateTime_?(Some(localDateTime1)).query.get.map(_ ==> List(a))

        // None matches non-asserted/null values
        _ <- Ns.i.a1.localDateTime_?(Option.empty[LocalDateTime]).query.get.map(_ ==> List(x))
        // Easier to apply nothing to tacit attribute
        _ <- Ns.i.a1.localDateTime_().query.get.map(_ ==> List(4))
      } yield ()
    }
  }
}
