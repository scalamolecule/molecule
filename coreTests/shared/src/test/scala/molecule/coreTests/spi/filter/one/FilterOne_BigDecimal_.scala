// GENERATED CODE ********************************
package molecule.coreTests.spi.filter.one

import molecule.core.api.ApiAsync
import molecule.core.spi.SpiAsync
import molecule.core.util.Executor._
import molecule.coreTests.async._
import molecule.coreTests.dataModels.core.dsl.Types._
import molecule.coreTests.setup.CoreTestSuite
import utest._

trait FilterOne_BigDecimal_ extends CoreTestSuite with ApiAsync { spi: SpiAsync =>

  override lazy val tests = Tests {

    "Mandatory" - types { implicit conn =>
      val a = (1, bigDecimal1)
      val b = (2, bigDecimal2)
      val c = (3, bigDecimal3)
      for {
        _ <- Ns.i.bigDecimal.insert(List(a, b, c)).transact

        // Find all attribute values
        _ <- Ns.i.a1.bigDecimal.query.get.map(_ ==> List(a, b, c))

        // Find value(s) matching
        _ <- Ns.i.a1.bigDecimal(bigDecimal0).query.get.map(_ ==> List())
        _ <- Ns.i.a1.bigDecimal(bigDecimal1).query.get.map(_ ==> List(a))
        _ <- Ns.i.a1.bigDecimal(Seq(bigDecimal0)).query.get.map(_ ==> List())
        _ <- Ns.i.a1.bigDecimal(Seq(bigDecimal1)).query.get.map(_ ==> List(a))
        // OR semantics for multiple args
        _ <- Ns.i.a1.bigDecimal(bigDecimal1, bigDecimal2).query.get.map(_ ==> List(a, b))
        _ <- Ns.i.a1.bigDecimal(bigDecimal1, bigDecimal0).query.get.map(_ ==> List(a))
        _ <- Ns.i.a1.bigDecimal(Seq(bigDecimal1, bigDecimal2)).query.get.map(_ ==> List(a, b))
        _ <- Ns.i.a1.bigDecimal(Seq(bigDecimal1, bigDecimal0)).query.get.map(_ ==> List(a))
        // Empty Seq of args matches no values
        _ <- Ns.i.a1.bigDecimal(Seq.empty[BigDecimal]).query.get.map(_ ==> List())

        // Find values not matching
        _ <- Ns.i.a1.bigDecimal.not(bigDecimal0).query.get.map(_ ==> List(a, b, c))
        _ <- Ns.i.a1.bigDecimal.not(bigDecimal1).query.get.map(_ ==> List(b, c))
        _ <- Ns.i.a1.bigDecimal.not(bigDecimal2).query.get.map(_ ==> List(a, c))
        _ <- Ns.i.a1.bigDecimal.not(bigDecimal3).query.get.map(_ ==> List(a, b))
        _ <- Ns.i.a1.bigDecimal.not(Seq(bigDecimal0)).query.get.map(_ ==> List(a, b, c))
        _ <- Ns.i.a1.bigDecimal.not(Seq(bigDecimal1)).query.get.map(_ ==> List(b, c))
        _ <- Ns.i.a1.bigDecimal.not(Seq(bigDecimal2)).query.get.map(_ ==> List(a, c))
        _ <- Ns.i.a1.bigDecimal.not(Seq(bigDecimal3)).query.get.map(_ ==> List(a, b))
        // OR semantics for multiple args
        _ <- Ns.i.a1.bigDecimal.not(bigDecimal0, bigDecimal1).query.get.map(_ ==> List(b, c))
        _ <- Ns.i.a1.bigDecimal.not(bigDecimal1, bigDecimal2).query.get.map(_ ==> List(c))
        _ <- Ns.i.a1.bigDecimal.not(bigDecimal2, bigDecimal3).query.get.map(_ ==> List(a))
        _ <- Ns.i.a1.bigDecimal.not(Seq(bigDecimal0, bigDecimal1)).query.get.map(_ ==> List(b, c))
        _ <- Ns.i.a1.bigDecimal.not(Seq(bigDecimal1, bigDecimal2)).query.get.map(_ ==> List(c))
        _ <- Ns.i.a1.bigDecimal.not(Seq(bigDecimal2, bigDecimal3)).query.get.map(_ ==> List(a))
        // Empty Seq of negation args matches all values
        _ <- Ns.i.a1.bigDecimal.not(Seq.empty[BigDecimal]).query.get.map(_ ==> List(a, b, c))

        // Find values in range
        _ <- Ns.i.a1.bigDecimal.<(bigDecimal2).query.get.map(_ ==> List(a))
        _ <- Ns.i.a1.bigDecimal.>(bigDecimal2).query.get.map(_ ==> List(c))
        _ <- Ns.i.a1.bigDecimal.<=(bigDecimal2).query.get.map(_ ==> List(a, b))
        _ <- Ns.i.a1.bigDecimal.>=(bigDecimal2).query.get.map(_ ==> List(b, c))
      } yield ()
    }


    "Tacit" - types { implicit conn =>
      val (a, b, c, x) = (1, 2, 3, 4)
      for {
        _ <- Ns.i.bigDecimal_?.insert(List(
          (a, Some(bigDecimal1)),
          (b, Some(bigDecimal2)),
          (c, Some(bigDecimal3)),
          (x, None)
        )).transact

        // Match asserted attribute without returning its value
        _ <- Ns.i.a1.bigDecimal_.query.get.map(_ ==> List(a, b, c))

        // Match non-asserted attribute (null)
        _ <- Ns.i.a1.bigDecimal_().query.get.map(_ ==> List(x))

        // Match attribute values without returning them
        _ <- Ns.i.a1.bigDecimal_(bigDecimal0).query.get.map(_ ==> List())
        _ <- Ns.i.a1.bigDecimal_(bigDecimal1).query.get.map(_ ==> List(a))
        _ <- Ns.i.a1.bigDecimal_(Seq(bigDecimal0)).query.get.map(_ ==> List())
        _ <- Ns.i.a1.bigDecimal_(Seq(bigDecimal1)).query.get.map(_ ==> List(a))
        // OR semantics for multiple args
        _ <- Ns.i.a1.bigDecimal_(bigDecimal1, bigDecimal2).query.get.map(_ ==> List(a, b))
        _ <- Ns.i.a1.bigDecimal_(bigDecimal1, bigDecimal0).query.get.map(_ ==> List(a))
        _ <- Ns.i.a1.bigDecimal_(Seq(bigDecimal1, bigDecimal2)).query.get.map(_ ==> List(a, b))
        _ <- Ns.i.a1.bigDecimal_(Seq(bigDecimal1, bigDecimal0)).query.get.map(_ ==> List(a))
        // Empty Seq of args matches no values
        _ <- Ns.i.a1.bigDecimal_(Seq.empty[BigDecimal]).query.get.map(_ ==> List())

        // Match non-matching values without returning them
        _ <- Ns.i.a1.bigDecimal_.not(bigDecimal0).query.get.map(_ ==> List(a, b, c))
        _ <- Ns.i.a1.bigDecimal_.not(bigDecimal1).query.get.map(_ ==> List(b, c))
        _ <- Ns.i.a1.bigDecimal_.not(bigDecimal2).query.get.map(_ ==> List(a, c))
        _ <- Ns.i.a1.bigDecimal_.not(bigDecimal3).query.get.map(_ ==> List(a, b))
        _ <- Ns.i.a1.bigDecimal_.not(Seq(bigDecimal0)).query.get.map(_ ==> List(a, b, c))
        _ <- Ns.i.a1.bigDecimal_.not(Seq(bigDecimal1)).query.get.map(_ ==> List(b, c))
        _ <- Ns.i.a1.bigDecimal_.not(Seq(bigDecimal2)).query.get.map(_ ==> List(a, c))
        _ <- Ns.i.a1.bigDecimal_.not(Seq(bigDecimal3)).query.get.map(_ ==> List(a, b))
        // OR semantics for multiple args
        _ <- Ns.i.a1.bigDecimal_.not(bigDecimal0, bigDecimal1).query.get.map(_ ==> List(b, c))
        _ <- Ns.i.a1.bigDecimal_.not(bigDecimal1, bigDecimal2).query.get.map(_ ==> List(c))
        _ <- Ns.i.a1.bigDecimal_.not(bigDecimal2, bigDecimal3).query.get.map(_ ==> List(a))
        _ <- Ns.i.a1.bigDecimal_.not(Seq(bigDecimal0, bigDecimal1)).query.get.map(_ ==> List(b, c))
        _ <- Ns.i.a1.bigDecimal_.not(Seq(bigDecimal1, bigDecimal2)).query.get.map(_ ==> List(c))
        _ <- Ns.i.a1.bigDecimal_.not(Seq(bigDecimal2, bigDecimal3)).query.get.map(_ ==> List(a))
        // Empty Seq of negation args matches all asserted values (non-null)
        _ <- Ns.i.a1.bigDecimal_.not(Seq.empty[BigDecimal]).query.get.map(_ ==> List(a, b, c))

        // Match value ranges without returning them
        _ <- Ns.i.a1.bigDecimal_.<(bigDecimal2).query.get.map(_ ==> List(a))
        _ <- Ns.i.a1.bigDecimal_.>(bigDecimal2).query.get.map(_ ==> List(c))
        _ <- Ns.i.a1.bigDecimal_.<=(bigDecimal2).query.get.map(_ ==> List(a, b))
        _ <- Ns.i.a1.bigDecimal_.>=(bigDecimal2).query.get.map(_ ==> List(b, c))
      } yield ()
    }


    "Optional" - types { implicit conn =>
      val a = (1, Some(bigDecimal1))
      val b = (2, Some(bigDecimal2))
      val c = (3, Some(bigDecimal3))
      val x = (4, Option.empty[BigDecimal])
      for {
        _ <- Ns.i.bigDecimal_?.insert(List(a, b, c, x)).transact

        // Find all optional attribute values
        _ <- Ns.i.a1.bigDecimal_?.query.get.map(_ ==> List(a, b, c, x))

        // Find optional values matching
        _ <- Ns.i.a1.bigDecimal_?(Some(bigDecimal0)).query.get.map(_ ==> List())
        _ <- Ns.i.a1.bigDecimal_?(Some(bigDecimal1)).query.get.map(_ ==> List(a))
        _ <- Ns.i.a1.bigDecimal_?(Some(Seq(bigDecimal0))).query.get.map(_ ==> List())
        _ <- Ns.i.a1.bigDecimal_?(Some(Seq(bigDecimal1))).query.get.map(_ ==> List(a))
        // OR semantics for Ses of multiple args
        _ <- Ns.i.a1.bigDecimal_?(Some(Seq(bigDecimal1, bigDecimal2))).query.get.map(_ ==> List(a, b))
        _ <- Ns.i.a1.bigDecimal_?(Some(Seq(bigDecimal1, bigDecimal0))).query.get.map(_ ==> List(a))
        // Empty Seq of args matches no values
        _ <- Ns.i.a1.bigDecimal_?(Some(Seq.empty[BigDecimal])).query.get.map(_ ==> List())
        // None matches non-asserted/null values
        _ <- Ns.i.a1.bigDecimal_?(Option.empty[BigDecimal]).query.get.map(_ ==> List(x))
        _ <- Ns.i.a1.bigDecimal_?(Option.empty[Seq[BigDecimal]]).query.get.map(_ ==> List(x))

        // Find optional values not matching
        _ <- Ns.i.a1.bigDecimal_?.not(Some(bigDecimal0)).query.get.map(_ ==> List(a, b, c))
        _ <- Ns.i.a1.bigDecimal_?.not(Some(bigDecimal1)).query.get.map(_ ==> List(b, c))
        _ <- Ns.i.a1.bigDecimal_?.not(Some(bigDecimal2)).query.get.map(_ ==> List(a, c))
        _ <- Ns.i.a1.bigDecimal_?.not(Some(bigDecimal3)).query.get.map(_ ==> List(a, b))
        _ <- Ns.i.a1.bigDecimal_?.not(Some(Seq(bigDecimal0))).query.get.map(_ ==> List(a, b, c))
        _ <- Ns.i.a1.bigDecimal_?.not(Some(Seq(bigDecimal1))).query.get.map(_ ==> List(b, c))
        _ <- Ns.i.a1.bigDecimal_?.not(Some(Seq(bigDecimal2))).query.get.map(_ ==> List(a, c))
        _ <- Ns.i.a1.bigDecimal_?.not(Some(Seq(bigDecimal3))).query.get.map(_ ==> List(a, b))
        // OR semantics for multiple negation args
        _ <- Ns.i.a1.bigDecimal_?.not(Some(Seq(bigDecimal0, bigDecimal1))).query.get.map(_ ==> List(b, c))
        _ <- Ns.i.a1.bigDecimal_?.not(Some(Seq(bigDecimal1, bigDecimal2))).query.get.map(_ ==> List(c))
        _ <- Ns.i.a1.bigDecimal_?.not(Some(Seq(bigDecimal2, bigDecimal3))).query.get.map(_ ==> List(a))
        // Empty Seq of negation args matches all asserted values (non-null)
        _ <- Ns.i.a1.bigDecimal_?.not(Some(Seq.empty[BigDecimal])).query.get.map(_ ==> List(a, b, c))
        // Negating None matches all asserted values (non-null)
        _ <- Ns.i.a1.bigDecimal_?.not(Option.empty[BigDecimal]).query.get.map(_ ==> List(a, b, c))
        _ <- Ns.i.a1.bigDecimal_?.not(Option.empty[Seq[BigDecimal]]).query.get.map(_ ==> List(a, b, c))

        // Find optional values in range
        _ <- Ns.i.a1.bigDecimal_?.<(Some(bigDecimal2)).query.get.map(_ ==> List(a))
        _ <- Ns.i.a1.bigDecimal_?.>(Some(bigDecimal2)).query.get.map(_ ==> List(c))
        _ <- Ns.i.a1.bigDecimal_?.<=(Some(bigDecimal2)).query.get.map(_ ==> List(a, b))
        _ <- Ns.i.a1.bigDecimal_?.>=(Some(bigDecimal2)).query.get.map(_ ==> List(b, c))
        // None can't be compared and returns empty result
        _ <- Ns.i.a1.bigDecimal_?.<(None).query.get.map(_ ==> List())
        _ <- Ns.i.a1.bigDecimal_?.<=(None).query.get.map(_ ==> List())
        _ <- Ns.i.a1.bigDecimal_?.>(None).query.get.map(_ ==> List())
        _ <- Ns.i.a1.bigDecimal_?.>=(None).query.get.map(_ ==> List())
      } yield ()
    }
  }
}
