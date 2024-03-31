// GENERATED CODE ********************************
package molecule.coreTests.spi.filter.one.types

import molecule.core.api.ApiAsync
import molecule.core.spi.SpiAsync
import molecule.core.util.Executor._
import molecule.coreTests.async._
import molecule.coreTests.dataModels.core.dsl.Types._
import molecule.coreTests.setup.CoreTestSuite
import utest._

trait FilterOne_Long_ extends CoreTestSuite with ApiAsync { spi: SpiAsync =>

  override lazy val tests = Tests {

    "Mandatory" - types { implicit conn =>
      val a = (1, long1)
      val b = (2, long2)
      val c = (3, long3)
      for {
        _ <- Ns.i.long.insert(List(a, b, c)).transact

        // Find all attribute values
        _ <- Ns.i.a1.long.query.get.map(_ ==> List(a, b, c))

        // Find value(s) matching
        _ <- Ns.i.a1.long(long0).query.get.map(_ ==> List())
        _ <- Ns.i.a1.long(long1).query.get.map(_ ==> List(a))
        _ <- Ns.i.a1.long(Seq(long0)).query.get.map(_ ==> List())
        _ <- Ns.i.a1.long(Seq(long1)).query.get.map(_ ==> List(a))
        // OR semantics for multiple args ("is this or that")
        _ <- Ns.i.a1.long(long1, long2).query.get.map(_ ==> List(a, b))
        _ <- Ns.i.a1.long(long1, long0).query.get.map(_ ==> List(a))
        _ <- Ns.i.a1.long(Seq(long1, long2)).query.get.map(_ ==> List(a, b))
        _ <- Ns.i.a1.long(Seq(long1, long0)).query.get.map(_ ==> List(a))
        // Empty Seq of args matches no values
        _ <- Ns.i.a1.long(Seq.empty[Long]).query.get.map(_ ==> List())

        // Find values not matching
        _ <- Ns.i.a1.long.not(long0).query.get.map(_ ==> List(a, b, c))
        _ <- Ns.i.a1.long.not(long1).query.get.map(_ ==> List(b, c))
        _ <- Ns.i.a1.long.not(long2).query.get.map(_ ==> List(a, c))
        _ <- Ns.i.a1.long.not(long3).query.get.map(_ ==> List(a, b))
        _ <- Ns.i.a1.long.not(Seq(long0)).query.get.map(_ ==> List(a, b, c))
        _ <- Ns.i.a1.long.not(Seq(long1)).query.get.map(_ ==> List(b, c))
        _ <- Ns.i.a1.long.not(Seq(long2)).query.get.map(_ ==> List(a, c))
        _ <- Ns.i.a1.long.not(Seq(long3)).query.get.map(_ ==> List(a, b))
        // OR semantics for multiple args
        _ <- Ns.i.a1.long.not(long0, long1).query.get.map(_ ==> List(b, c))
        _ <- Ns.i.a1.long.not(long1, long2).query.get.map(_ ==> List(c))
        _ <- Ns.i.a1.long.not(long2, long3).query.get.map(_ ==> List(a))
        _ <- Ns.i.a1.long.not(Seq(long0, long1)).query.get.map(_ ==> List(b, c))
        _ <- Ns.i.a1.long.not(Seq(long1, long2)).query.get.map(_ ==> List(c))
        _ <- Ns.i.a1.long.not(Seq(long2, long3)).query.get.map(_ ==> List(a))
        // Empty Seq of negation args matches all values
        _ <- Ns.i.a1.long.not(Seq.empty[Long]).query.get.map(_ ==> List(a, b, c))

        // Find values in range
        _ <- Ns.i.a1.long.<(long2).query.get.map(_ ==> List(a))
        _ <- Ns.i.a1.long.>(long2).query.get.map(_ ==> List(c))
        _ <- Ns.i.a1.long.<=(long2).query.get.map(_ ==> List(a, b))
        _ <- Ns.i.a1.long.>=(long2).query.get.map(_ ==> List(b, c))
      } yield ()
    }


    "Tacit" - types { implicit conn =>
      val (a, b, c, x) = (1, 2, 3, 4)
      for {
        _ <- Ns.i.long_?.insert(List(
          (a, Some(long1)),
          (b, Some(long2)),
          (c, Some(long3)),
          (x, None)
        )).transact

        // Match asserted attribute without returning its value
        _ <- Ns.i.a1.long_.query.get.map(_ ==> List(a, b, c))

        // Match non-asserted attribute (null)
        _ <- Ns.i.a1.long_().query.get.map(_ ==> List(x))

        // Match attribute values without returning them
        _ <- Ns.i.a1.long_(long0).query.get.map(_ ==> List())
        _ <- Ns.i.a1.long_(long1).query.get.map(_ ==> List(a))
        _ <- Ns.i.a1.long_(Seq(long0)).query.get.map(_ ==> List())
        _ <- Ns.i.a1.long_(Seq(long1)).query.get.map(_ ==> List(a))
        // OR semantics for multiple args ("is this or that")
        _ <- Ns.i.a1.long_(long1, long2).query.get.map(_ ==> List(a, b))
        _ <- Ns.i.a1.long_(long1, long0).query.get.map(_ ==> List(a))
        _ <- Ns.i.a1.long_(Seq(long1, long2)).query.get.map(_ ==> List(a, b))
        _ <- Ns.i.a1.long_(Seq(long1, long0)).query.get.map(_ ==> List(a))
        // Empty Seq of args matches no values
        _ <- Ns.i.a1.long_(Seq.empty[Long]).query.get.map(_ ==> List())

        // Match non-matching values without returning them
        _ <- Ns.i.a1.long_.not(long0).query.get.map(_ ==> List(a, b, c))
        _ <- Ns.i.a1.long_.not(long1).query.get.map(_ ==> List(b, c))
        _ <- Ns.i.a1.long_.not(long2).query.get.map(_ ==> List(a, c))
        _ <- Ns.i.a1.long_.not(long3).query.get.map(_ ==> List(a, b))
        _ <- Ns.i.a1.long_.not(Seq(long0)).query.get.map(_ ==> List(a, b, c))
        _ <- Ns.i.a1.long_.not(Seq(long1)).query.get.map(_ ==> List(b, c))
        _ <- Ns.i.a1.long_.not(Seq(long2)).query.get.map(_ ==> List(a, c))
        _ <- Ns.i.a1.long_.not(Seq(long3)).query.get.map(_ ==> List(a, b))
        // OR semantics for multiple args
        _ <- Ns.i.a1.long_.not(long0, long1).query.get.map(_ ==> List(b, c))
        _ <- Ns.i.a1.long_.not(long1, long2).query.get.map(_ ==> List(c))
        _ <- Ns.i.a1.long_.not(long2, long3).query.get.map(_ ==> List(a))
        _ <- Ns.i.a1.long_.not(Seq(long0, long1)).query.get.map(_ ==> List(b, c))
        _ <- Ns.i.a1.long_.not(Seq(long1, long2)).query.get.map(_ ==> List(c))
        _ <- Ns.i.a1.long_.not(Seq(long2, long3)).query.get.map(_ ==> List(a))
        // Empty Seq of negation args matches all asserted values (non-null)
        _ <- Ns.i.a1.long_.not(Seq.empty[Long]).query.get.map(_ ==> List(a, b, c))

        // Match value ranges without returning them
        _ <- Ns.i.a1.long_.<(long2).query.get.map(_ ==> List(a))
        _ <- Ns.i.a1.long_.>(long2).query.get.map(_ ==> List(c))
        _ <- Ns.i.a1.long_.<=(long2).query.get.map(_ ==> List(a, b))
        _ <- Ns.i.a1.long_.>=(long2).query.get.map(_ ==> List(b, c))
      } yield ()
    }


    "Optional" - types { implicit conn =>
      val a = (1, Some(long1))
      val b = (2, Some(long2))
      val c = (3, Some(long3))
      val x = (4, Option.empty[Long])
      for {
        _ <- Ns.i.long_?.insert(List(a, b, c, x)).transact

        // Find all optional attribute values
        _ <- Ns.i.a1.long_?.query.get.map(_ ==> List(a, b, c, x))

        // Find optional values matching
        _ <- Ns.i.a1.long_?(Some(long0)).query.get.map(_ ==> List())
        _ <- Ns.i.a1.long_?(Some(long1)).query.get.map(_ ==> List(a))

        // None matches non-asserted/null values
        _ <- Ns.i.a1.long_?(Option.empty[Long]).query.get.map(_ ==> List(x))
        // Easier to apply nothing to tacit attribute
        _ <- Ns.i.a1.long_().query.get.map(_ ==> List(4))
      } yield ()
    }
  }
}
