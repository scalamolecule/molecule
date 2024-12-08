// GENERATED CODE ********************************
package molecule.coreTests.spi.filter.one.types

import molecule.core.api.Api_async
import molecule.core.spi.Spi_async
import molecule.core.util.Executor._
import molecule.coreTests.dataModels.dsl.Types._
import molecule.coreTests.setup.CoreTestSuite
import utest._

trait FilterOne_ref extends CoreTestSuite with Api_async { spi: Spi_async =>

  override lazy val tests = Tests {

    "Mandatory" - types { implicit conn =>
      for {
        List(ref1, ref2, ref3) <- Ref.i.insert(1, 2, 3).transact.map(_.ids)
        a = (1, ref1)
        b = (2, ref2)
        c = (3, ref3)


        _ <- Ns.i.ref.insert(List(a, b, c)).transact

        // Find all attribute values
        _ <- Ns.i.a1.ref.query.get.map(_ ==> List(a, b, c))

        // Find value(s) matching
        _ <- Ns.i.a1.ref(ref0).query.get.map(_ ==> List())
        _ <- Ns.i.a1.ref(ref1).query.get.map(_ ==> List(a))
        _ <- Ns.i.a1.ref(Seq(ref0)).query.get.map(_ ==> List())
        _ <- Ns.i.a1.ref(Seq(ref1)).query.get.map(_ ==> List(a))
        // OR semantics for multiple args ("is this or that")
        _ <- Ns.i.a1.ref(ref1, ref2).query.get.map(_ ==> List(a, b))
        _ <- Ns.i.a1.ref(ref1, ref0).query.get.map(_ ==> List(a))
        _ <- Ns.i.a1.ref(Seq(ref1, ref2)).query.get.map(_ ==> List(a, b))
        _ <- Ns.i.a1.ref(Seq(ref1, ref0)).query.get.map(_ ==> List(a))
        // Empty Seq of args matches no values
        _ <- Ns.i.a1.ref(Seq.empty[Long]).query.get.map(_ ==> List())

        // Find values not matching
        _ <- Ns.i.a1.ref.not(ref0).query.get.map(_ ==> List(a, b, c))
        _ <- Ns.i.a1.ref.not(ref1).query.get.map(_ ==> List(b, c))
        _ <- Ns.i.a1.ref.not(ref2).query.get.map(_ ==> List(a, c))
        _ <- Ns.i.a1.ref.not(ref3).query.get.map(_ ==> List(a, b))
        _ <- Ns.i.a1.ref.not(Seq(ref0)).query.get.map(_ ==> List(a, b, c))
        _ <- Ns.i.a1.ref.not(Seq(ref1)).query.get.map(_ ==> List(b, c))
        _ <- Ns.i.a1.ref.not(Seq(ref2)).query.get.map(_ ==> List(a, c))
        _ <- Ns.i.a1.ref.not(Seq(ref3)).query.get.map(_ ==> List(a, b))
        // OR semantics for multiple args
        _ <- Ns.i.a1.ref.not(ref0, ref1).query.get.map(_ ==> List(b, c))
        _ <- Ns.i.a1.ref.not(ref1, ref2).query.get.map(_ ==> List(c))
        _ <- Ns.i.a1.ref.not(ref2, ref3).query.get.map(_ ==> List(a))
        _ <- Ns.i.a1.ref.not(Seq(ref0, ref1)).query.get.map(_ ==> List(b, c))
        _ <- Ns.i.a1.ref.not(Seq(ref1, ref2)).query.get.map(_ ==> List(c))
        _ <- Ns.i.a1.ref.not(Seq(ref2, ref3)).query.get.map(_ ==> List(a))
        // Empty Seq of negation args matches all values
        _ <- Ns.i.a1.ref.not(Seq.empty[Long]).query.get.map(_ ==> List(a, b, c))

        // Find values in range
        _ <- Ns.i.a1.ref.<(ref2).query.get.map(_ ==> List(a))
        _ <- Ns.i.a1.ref.>(ref2).query.get.map(_ ==> List(c))
        _ <- Ns.i.a1.ref.<=(ref2).query.get.map(_ ==> List(a, b))
        _ <- Ns.i.a1.ref.>=(ref2).query.get.map(_ ==> List(b, c))
      } yield ()
    }


    "Tacit" - types { implicit conn =>
      val (a, b, c, x) = (1, 2, 3, 4)
      for {
        List(ref1, ref2, ref3) <- Ref.i.insert(1, 2, 3).transact.map(_.ids)

        _ <- Ns.i.ref_?.insert(List(
          (a, Some(ref1)),
          (b, Some(ref2)),
          (c, Some(ref3)),
          (x, None)
        )).transact

        // Match asserted attribute without returning its value
        _ <- Ns.i.a1.ref_.query.get.map(_ ==> List(a, b, c))

        // Match non-asserted attribute (null)
        _ <- Ns.i.a1.ref_().query.get.map(_ ==> List(x))

        // Match attribute values without returning them
        _ <- Ns.i.a1.ref_(ref0).query.get.map(_ ==> List())
        _ <- Ns.i.a1.ref_(ref1).query.get.map(_ ==> List(a))
        _ <- Ns.i.a1.ref_(Seq(ref0)).query.get.map(_ ==> List())
        _ <- Ns.i.a1.ref_(Seq(ref1)).query.get.map(_ ==> List(a))
        // OR semantics for multiple args ("is this or that")
        _ <- Ns.i.a1.ref_(ref1, ref2).query.get.map(_ ==> List(a, b))
        _ <- Ns.i.a1.ref_(ref1, ref0).query.get.map(_ ==> List(a))
        _ <- Ns.i.a1.ref_(Seq(ref1, ref2)).query.get.map(_ ==> List(a, b))
        _ <- Ns.i.a1.ref_(Seq(ref1, ref0)).query.get.map(_ ==> List(a))
        // Empty Seq of args matches no values
        _ <- Ns.i.a1.ref_(Seq.empty[Long]).query.get.map(_ ==> List())

        // Match non-matching values without returning them
        _ <- Ns.i.a1.ref_.not(ref0).query.get.map(_ ==> List(a, b, c))
        _ <- Ns.i.a1.ref_.not(ref1).query.get.map(_ ==> List(b, c))
        _ <- Ns.i.a1.ref_.not(ref2).query.get.map(_ ==> List(a, c))
        _ <- Ns.i.a1.ref_.not(ref3).query.get.map(_ ==> List(a, b))
        _ <- Ns.i.a1.ref_.not(Seq(ref0)).query.get.map(_ ==> List(a, b, c))
        _ <- Ns.i.a1.ref_.not(Seq(ref1)).query.get.map(_ ==> List(b, c))
        _ <- Ns.i.a1.ref_.not(Seq(ref2)).query.get.map(_ ==> List(a, c))
        _ <- Ns.i.a1.ref_.not(Seq(ref3)).query.get.map(_ ==> List(a, b))
        // OR semantics for multiple args
        _ <- Ns.i.a1.ref_.not(ref0, ref1).query.get.map(_ ==> List(b, c))
        _ <- Ns.i.a1.ref_.not(ref1, ref2).query.get.map(_ ==> List(c))
        _ <- Ns.i.a1.ref_.not(ref2, ref3).query.get.map(_ ==> List(a))
        _ <- Ns.i.a1.ref_.not(Seq(ref0, ref1)).query.get.map(_ ==> List(b, c))
        _ <- Ns.i.a1.ref_.not(Seq(ref1, ref2)).query.get.map(_ ==> List(c))
        _ <- Ns.i.a1.ref_.not(Seq(ref2, ref3)).query.get.map(_ ==> List(a))
        // Empty Seq of negation args matches all asserted values (non-null)
        _ <- Ns.i.a1.ref_.not(Seq.empty[Long]).query.get.map(_ ==> List(a, b, c))

        // Match value ranges without returning them
        _ <- Ns.i.a1.ref_.<(ref2).query.get.map(_ ==> List(a))
        _ <- Ns.i.a1.ref_.>(ref2).query.get.map(_ ==> List(c))
        _ <- Ns.i.a1.ref_.<=(ref2).query.get.map(_ ==> List(a, b))
        _ <- Ns.i.a1.ref_.>=(ref2).query.get.map(_ ==> List(b, c))
      } yield ()
    }


    "Optional" - types { implicit conn =>
      for {
        List(ref1, ref2, ref3) <- Ref.i.insert(1, 2, 3).transact.map(_.ids)
        a = (1, Some(ref1))
        b = (2, Some(ref2))
        c = (3, Some(ref3))
        x = (4, Option.empty[Long])

        _ <- Ns.i.ref_?.insert(List(a, b, c, x)).transact

        // Find all optional attribute values
        _ <- Ns.i.a1.ref_?.query.get.map(_ ==> List(a, b, c, x))

        // Find optional values matching
        _ <- Ns.i.a1.ref_?(Some(ref0)).query.get.map(_ ==> List())
        _ <- Ns.i.a1.ref_?(Some(ref1)).query.get.map(_ ==> List(a))

        // None matches non-asserted/null values
        _ <- Ns.i.a1.ref_?(Option.empty[Long]).query.get.map(_ ==> List(x))
        // Easier to apply nothing to tacit attribute
        _ <- Ns.i.a1.ref_().query.get.map(_ ==> List(4))
      } yield ()
    }
  }
}
