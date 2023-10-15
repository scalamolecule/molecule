// GENERATED CODE ********************************
package molecule.coreTests.compliance.filter.one

import molecule.core.api.ApiAsync
import molecule.core.spi.SpiAsync
import molecule.core.util.Executor._
import molecule.coreTests.async._
import molecule.coreTests.dataModels.core.dsl.Types._
import molecule.coreTests.setup.CoreTestSuite
import utest._

trait FilterOne_Double_ extends CoreTestSuite with ApiAsync { spi: SpiAsync =>

  override lazy val tests = Tests {

    "Mandatory" - types { implicit conn =>
      val a = (1, double1)
      val b = (2, double2)
      val c = (3, double3)
      for {
        _ <- Ns.i.double.insert(List(a, b, c)).transact

        // Find all attribute values
        _ <- Ns.i.a1.double.query.get.map(_ ==> List(a, b, c))

        // Find value(s) matching
        _ <- Ns.i.a1.double(double0).query.get.map(_ ==> List())
        _ <- Ns.i.a1.double(double1).query.get.map(_ ==> List(a))
        _ <- Ns.i.a1.double(Seq(double0)).query.get.map(_ ==> List())
        _ <- Ns.i.a1.double(Seq(double1)).query.get.map(_ ==> List(a))
        // OR semantics for multiple args
        _ <- Ns.i.a1.double(double1, double2).query.get.map(_ ==> List(a, b))
        _ <- Ns.i.a1.double(double1, double0).query.get.map(_ ==> List(a))
        _ <- Ns.i.a1.double(Seq(double1, double2)).query.get.map(_ ==> List(a, b))
        _ <- Ns.i.a1.double(Seq(double1, double0)).query.get.map(_ ==> List(a))
        // Empty Seq of args matches no values
        _ <- Ns.i.a1.double(Seq.empty[Double]).query.get.map(_ ==> List())

        // Find values not matching
        _ <- Ns.i.a1.double.not(double0).query.get.map(_ ==> List(a, b, c))
        _ <- Ns.i.a1.double.not(double1).query.get.map(_ ==> List(b, c))
        _ <- Ns.i.a1.double.not(double2).query.get.map(_ ==> List(a, c))
        _ <- Ns.i.a1.double.not(double3).query.get.map(_ ==> List(a, b))
        _ <- Ns.i.a1.double.not(Seq(double0)).query.get.map(_ ==> List(a, b, c))
        _ <- Ns.i.a1.double.not(Seq(double1)).query.get.map(_ ==> List(b, c))
        _ <- Ns.i.a1.double.not(Seq(double2)).query.get.map(_ ==> List(a, c))
        _ <- Ns.i.a1.double.not(Seq(double3)).query.get.map(_ ==> List(a, b))
        // OR semantics for multiple args
        _ <- Ns.i.a1.double.not(double0, double1).query.get.map(_ ==> List(b, c))
        _ <- Ns.i.a1.double.not(double1, double2).query.get.map(_ ==> List(c))
        _ <- Ns.i.a1.double.not(double2, double3).query.get.map(_ ==> List(a))
        _ <- Ns.i.a1.double.not(Seq(double0, double1)).query.get.map(_ ==> List(b, c))
        _ <- Ns.i.a1.double.not(Seq(double1, double2)).query.get.map(_ ==> List(c))
        _ <- Ns.i.a1.double.not(Seq(double2, double3)).query.get.map(_ ==> List(a))
        // Empty Seq of negation args matches all values
        _ <- Ns.i.a1.double.not(Seq.empty[Double]).query.get.map(_ ==> List(a, b, c))

        // Find values in range
        _ <- Ns.i.a1.double.<(double2).query.get.map(_ ==> List(a))
        _ <- Ns.i.a1.double.>(double2).query.get.map(_ ==> List(c))
        _ <- Ns.i.a1.double.<=(double2).query.get.map(_ ==> List(a, b))
        _ <- Ns.i.a1.double.>=(double2).query.get.map(_ ==> List(b, c))
      } yield ()
    }


    "Tacit" - types { implicit conn =>
      val (a, b, c, x) = (1, 2, 3, 4)
      for {
        _ <- Ns.i.double_?.insert(List(
          (a, Some(double1)),
          (b, Some(double2)),
          (c, Some(double3)),
          (x, None)
        )).transact

        // Match asserted attribute without returning its value
        _ <- Ns.i.a1.double_.query.get.map(_ ==> List(a, b, c))

        // Match non-asserted attribute (null)
        _ <- Ns.i.a1.double_().query.get.map(_ ==> List(x))

        // Match attribute values without returning them
        _ <- Ns.i.a1.double_(double0).query.get.map(_ ==> List())
        _ <- Ns.i.a1.double_(double1).query.get.map(_ ==> List(a))
        _ <- Ns.i.a1.double_(Seq(double0)).query.get.map(_ ==> List())
        _ <- Ns.i.a1.double_(Seq(double1)).query.get.map(_ ==> List(a))
        // OR semantics for multiple args
        _ <- Ns.i.a1.double_(double1, double2).query.get.map(_ ==> List(a, b))
        _ <- Ns.i.a1.double_(double1, double0).query.get.map(_ ==> List(a))
        _ <- Ns.i.a1.double_(Seq(double1, double2)).query.get.map(_ ==> List(a, b))
        _ <- Ns.i.a1.double_(Seq(double1, double0)).query.get.map(_ ==> List(a))
        // Empty Seq of args matches no values
        _ <- Ns.i.a1.double_(Seq.empty[Double]).query.get.map(_ ==> List())

        // Match non-matching values without returning them
        _ <- Ns.i.a1.double_.not(double0).query.get.map(_ ==> List(a, b, c))
        _ <- Ns.i.a1.double_.not(double1).query.get.map(_ ==> List(b, c))
        _ <- Ns.i.a1.double_.not(double2).query.get.map(_ ==> List(a, c))
        _ <- Ns.i.a1.double_.not(double3).query.get.map(_ ==> List(a, b))
        _ <- Ns.i.a1.double_.not(Seq(double0)).query.get.map(_ ==> List(a, b, c))
        _ <- Ns.i.a1.double_.not(Seq(double1)).query.get.map(_ ==> List(b, c))
        _ <- Ns.i.a1.double_.not(Seq(double2)).query.get.map(_ ==> List(a, c))
        _ <- Ns.i.a1.double_.not(Seq(double3)).query.get.map(_ ==> List(a, b))
        // OR semantics for multiple args
        _ <- Ns.i.a1.double_.not(double0, double1).query.get.map(_ ==> List(b, c))
        _ <- Ns.i.a1.double_.not(double1, double2).query.get.map(_ ==> List(c))
        _ <- Ns.i.a1.double_.not(double2, double3).query.get.map(_ ==> List(a))
        _ <- Ns.i.a1.double_.not(Seq(double0, double1)).query.get.map(_ ==> List(b, c))
        _ <- Ns.i.a1.double_.not(Seq(double1, double2)).query.get.map(_ ==> List(c))
        _ <- Ns.i.a1.double_.not(Seq(double2, double3)).query.get.map(_ ==> List(a))
        // Empty Seq of negation args matches all asserted values (non-null)
        _ <- Ns.i.a1.double_.not(Seq.empty[Double]).query.get.map(_ ==> List(a, b, c))

        // Match value ranges without returning them
        _ <- Ns.i.a1.double_.<(double2).query.get.map(_ ==> List(a))
        _ <- Ns.i.a1.double_.>(double2).query.get.map(_ ==> List(c))
        _ <- Ns.i.a1.double_.<=(double2).query.get.map(_ ==> List(a, b))
        _ <- Ns.i.a1.double_.>=(double2).query.get.map(_ ==> List(b, c))
      } yield ()
    }


    "Optional" - types { implicit conn =>
      val a = (1, Some(double1))
      val b = (2, Some(double2))
      val c = (3, Some(double3))
      val x = (4, Option.empty[Double])
      for {
        _ <- Ns.i.double_?.insert(List(a, b, c, x)).transact

        // Find all optional attribute values
        _ <- Ns.i.a1.double_?.query.get.map(_ ==> List(a, b, c, x))

        // Find optional values matching
        _ <- Ns.i.a1.double_?(Some(double0)).query.get.map(_ ==> List())
        _ <- Ns.i.a1.double_?(Some(double1)).query.get.map(_ ==> List(a))
        _ <- Ns.i.a1.double_?(Some(Seq(double0))).query.get.map(_ ==> List())
        _ <- Ns.i.a1.double_?(Some(Seq(double1))).query.get.map(_ ==> List(a))
        // OR semantics for Ses of multiple args
        _ <- Ns.i.a1.double_?(Some(Seq(double1, double2))).query.get.map(_ ==> List(a, b))
        _ <- Ns.i.a1.double_?(Some(Seq(double1, double0))).query.get.map(_ ==> List(a))
        // Empty Seq of args matches no values
        _ <- Ns.i.a1.double_?(Some(Seq.empty[Double])).query.get.map(_ ==> List())
        // None matches non-asserted/null values
        _ <- Ns.i.a1.double_?(Option.empty[Double]).query.get.map(_ ==> List(x))
        _ <- Ns.i.a1.double_?(Option.empty[Seq[Double]]).query.get.map(_ ==> List(x))

        // Find optional values not matching
        _ <- Ns.i.a1.double_?.not(Some(double0)).query.get.map(_ ==> List(a, b, c))
        _ <- Ns.i.a1.double_?.not(Some(double1)).query.get.map(_ ==> List(b, c))
        _ <- Ns.i.a1.double_?.not(Some(double2)).query.get.map(_ ==> List(a, c))
        _ <- Ns.i.a1.double_?.not(Some(double3)).query.get.map(_ ==> List(a, b))
        _ <- Ns.i.a1.double_?.not(Some(Seq(double0))).query.get.map(_ ==> List(a, b, c))
        _ <- Ns.i.a1.double_?.not(Some(Seq(double1))).query.get.map(_ ==> List(b, c))
        _ <- Ns.i.a1.double_?.not(Some(Seq(double2))).query.get.map(_ ==> List(a, c))
        _ <- Ns.i.a1.double_?.not(Some(Seq(double3))).query.get.map(_ ==> List(a, b))
        // OR semantics for multiple negation args
        _ <- Ns.i.a1.double_?.not(Some(Seq(double0, double1))).query.get.map(_ ==> List(b, c))
        _ <- Ns.i.a1.double_?.not(Some(Seq(double1, double2))).query.get.map(_ ==> List(c))
        _ <- Ns.i.a1.double_?.not(Some(Seq(double2, double3))).query.get.map(_ ==> List(a))
        // Empty Seq of negation args matches all asserted values (non-null)
        _ <- Ns.i.a1.double_?.not(Some(Seq.empty[Double])).query.get.map(_ ==> List(a, b, c))
        // Negating None matches all asserted values (non-null)
        _ <- Ns.i.a1.double_?.not(Option.empty[Double]).query.get.map(_ ==> List(a, b, c))
        _ <- Ns.i.a1.double_?.not(Option.empty[Seq[Double]]).query.get.map(_ ==> List(a, b, c))

        // Find optional values in range
        _ <- Ns.i.a1.double_?.<(Some(double2)).query.get.map(_ ==> List(a))
        _ <- Ns.i.a1.double_?.>(Some(double2)).query.get.map(_ ==> List(c))
        _ <- Ns.i.a1.double_?.<=(Some(double2)).query.get.map(_ ==> List(a, b))
        _ <- Ns.i.a1.double_?.>=(Some(double2)).query.get.map(_ ==> List(b, c))
        // None can't be compared and returns empty result
        _ <- Ns.i.a1.double_?.<(None).query.get.map(_ ==> List())
        _ <- Ns.i.a1.double_?.<=(None).query.get.map(_ ==> List())
        _ <- Ns.i.a1.double_?.>(None).query.get.map(_ ==> List())
        _ <- Ns.i.a1.double_?.>=(None).query.get.map(_ ==> List())
      } yield ()
    }
  }
}
