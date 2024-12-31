// GENERATED CODE ********************************
package molecule.coreTests.spi.filter.one.types

import molecule.core.api.Api_async
import molecule.core.spi.Spi_async
import molecule.core.util.Executor._
import molecule.coreTests.domains.dsl.Types._
import molecule.coreTests.setup._

case class FilterOne_Double_(
  suite: Test,
  api: Api_async with Spi_async with DbProviders
) extends TestUtils {

  import api._
  import suite._

  "Mandatory" - types { implicit conn =>
    val a = (1, double1)
    val b = (2, double2)
    val c = (3, double3)
    for {
      _ <- Entity.i.double.insert(List(a, b, c)).transact

      // Find all attribute values
      _ <- Entity.i.a1.double.query.get.map(_ ==> List(a, b, c))

      // Find value(s) matching
      _ <- Entity.i.a1.double(double0).query.get.map(_ ==> List())
      _ <- Entity.i.a1.double(double1).query.get.map(_ ==> List(a))
      _ <- Entity.i.a1.double(Seq(double0)).query.get.map(_ ==> List())
      _ <- Entity.i.a1.double(Seq(double1)).query.get.map(_ ==> List(a))
      // OR semantics for multiple args ("is this or that")
      _ <- Entity.i.a1.double(double1, double2).query.get.map(_ ==> List(a, b))
      _ <- Entity.i.a1.double(double1, double0).query.get.map(_ ==> List(a))
      _ <- Entity.i.a1.double(Seq(double1, double2)).query.get.map(_ ==> List(a, b))
      _ <- Entity.i.a1.double(Seq(double1, double0)).query.get.map(_ ==> List(a))
      // Empty Seq of args matches no values
      _ <- Entity.i.a1.double(Seq.empty[Double]).query.get.map(_ ==> List())

      // Find values not matching
      _ <- Entity.i.a1.double.not(double0).query.get.map(_ ==> List(a, b, c))
      _ <- Entity.i.a1.double.not(double1).query.get.map(_ ==> List(b, c))
      _ <- Entity.i.a1.double.not(double2).query.get.map(_ ==> List(a, c))
      _ <- Entity.i.a1.double.not(double3).query.get.map(_ ==> List(a, b))
      _ <- Entity.i.a1.double.not(Seq(double0)).query.get.map(_ ==> List(a, b, c))
      _ <- Entity.i.a1.double.not(Seq(double1)).query.get.map(_ ==> List(b, c))
      _ <- Entity.i.a1.double.not(Seq(double2)).query.get.map(_ ==> List(a, c))
      _ <- Entity.i.a1.double.not(Seq(double3)).query.get.map(_ ==> List(a, b))
      // OR semantics for multiple args
      _ <- Entity.i.a1.double.not(double0, double1).query.get.map(_ ==> List(b, c))
      _ <- Entity.i.a1.double.not(double1, double2).query.get.map(_ ==> List(c))
      _ <- Entity.i.a1.double.not(double2, double3).query.get.map(_ ==> List(a))
      _ <- Entity.i.a1.double.not(Seq(double0, double1)).query.get.map(_ ==> List(b, c))
      _ <- Entity.i.a1.double.not(Seq(double1, double2)).query.get.map(_ ==> List(c))
      _ <- Entity.i.a1.double.not(Seq(double2, double3)).query.get.map(_ ==> List(a))
      // Empty Seq of negation args matches all values
      _ <- Entity.i.a1.double.not(Seq.empty[Double]).query.get.map(_ ==> List(a, b, c))

      // Find values in range
      _ <- Entity.i.a1.double.<(double2).query.get.map(_ ==> List(a))
      _ <- Entity.i.a1.double.>(double2).query.get.map(_ ==> List(c))
      _ <- Entity.i.a1.double.<=(double2).query.get.map(_ ==> List(a, b))
      _ <- Entity.i.a1.double.>=(double2).query.get.map(_ ==> List(b, c))
    } yield ()
  }


  "Tacit" - types { implicit conn =>
    val (a, b, c, x) = (1, 2, 3, 4)
    for {
      _ <- Entity.i.double_?.insert(List(
        (a, Some(double1)),
        (b, Some(double2)),
        (c, Some(double3)),
        (x, None)
      )).transact

      // Match asserted attribute without returning its value
      _ <- Entity.i.a1.double_.query.get.map(_ ==> List(a, b, c))

      // Match non-asserted attribute (null)
      _ <- Entity.i.a1.double_().query.get.map(_ ==> List(x))

      // Match attribute values without returning them
      _ <- Entity.i.a1.double_(double0).query.get.map(_ ==> List())
      _ <- Entity.i.a1.double_(double1).query.get.map(_ ==> List(a))
      _ <- Entity.i.a1.double_(Seq(double0)).query.get.map(_ ==> List())
      _ <- Entity.i.a1.double_(Seq(double1)).query.get.map(_ ==> List(a))
      // OR semantics for multiple args ("is this or that")
      _ <- Entity.i.a1.double_(double1, double2).query.get.map(_ ==> List(a, b))
      _ <- Entity.i.a1.double_(double1, double0).query.get.map(_ ==> List(a))
      _ <- Entity.i.a1.double_(Seq(double1, double2)).query.get.map(_ ==> List(a, b))
      _ <- Entity.i.a1.double_(Seq(double1, double0)).query.get.map(_ ==> List(a))
      // Empty Seq of args matches no values
      _ <- Entity.i.a1.double_(Seq.empty[Double]).query.get.map(_ ==> List())

      // Match non-matching values without returning them
      _ <- Entity.i.a1.double_.not(double0).query.get.map(_ ==> List(a, b, c))
      _ <- Entity.i.a1.double_.not(double1).query.get.map(_ ==> List(b, c))
      _ <- Entity.i.a1.double_.not(double2).query.get.map(_ ==> List(a, c))
      _ <- Entity.i.a1.double_.not(double3).query.get.map(_ ==> List(a, b))
      _ <- Entity.i.a1.double_.not(Seq(double0)).query.get.map(_ ==> List(a, b, c))
      _ <- Entity.i.a1.double_.not(Seq(double1)).query.get.map(_ ==> List(b, c))
      _ <- Entity.i.a1.double_.not(Seq(double2)).query.get.map(_ ==> List(a, c))
      _ <- Entity.i.a1.double_.not(Seq(double3)).query.get.map(_ ==> List(a, b))
      // OR semantics for multiple args
      _ <- Entity.i.a1.double_.not(double0, double1).query.get.map(_ ==> List(b, c))
      _ <- Entity.i.a1.double_.not(double1, double2).query.get.map(_ ==> List(c))
      _ <- Entity.i.a1.double_.not(double2, double3).query.get.map(_ ==> List(a))
      _ <- Entity.i.a1.double_.not(Seq(double0, double1)).query.get.map(_ ==> List(b, c))
      _ <- Entity.i.a1.double_.not(Seq(double1, double2)).query.get.map(_ ==> List(c))
      _ <- Entity.i.a1.double_.not(Seq(double2, double3)).query.get.map(_ ==> List(a))
      // Empty Seq of negation args matches all asserted values (non-null)
      _ <- Entity.i.a1.double_.not(Seq.empty[Double]).query.get.map(_ ==> List(a, b, c))

      // Match value ranges without returning them
      _ <- Entity.i.a1.double_.<(double2).query.get.map(_ ==> List(a))
      _ <- Entity.i.a1.double_.>(double2).query.get.map(_ ==> List(c))
      _ <- Entity.i.a1.double_.<=(double2).query.get.map(_ ==> List(a, b))
      _ <- Entity.i.a1.double_.>=(double2).query.get.map(_ ==> List(b, c))
    } yield ()
  }


  "Optional" - types { implicit conn =>
    val a = (1, Some(double1))
    val b = (2, Some(double2))
    val c = (3, Some(double3))
    val x = (4, Option.empty[Double])
    for {
      _ <- Entity.i.double_?.insert(List(a, b, c, x)).transact

      // Find all optional attribute values
      _ <- Entity.i.a1.double_?.query.get.map(_ ==> List(a, b, c, x))

      // Find optional values matching
      _ <- Entity.i.a1.double_?(Some(double0)).query.get.map(_ ==> List())
      _ <- Entity.i.a1.double_?(Some(double1)).query.get.map(_ ==> List(a))

      // None matches non-asserted/null values
      _ <- Entity.i.a1.double_?(Option.empty[Double]).query.get.map(_ ==> List(x))
      // Easier to apply nothing to tacit attribute
      _ <- Entity.i.a1.double_().query.get.map(_ ==> List(4))
    } yield ()
  }


  "Combinations" - types { implicit conn =>
    for {
      _ <- Entity.i.double.insert(
        (1, double1),
        (2, double2),
        (3, double3),
        (4, double4),
        (5, double5),
        (6, double6),
        (7, double7),
        (8, double8),
        (9, double9),
      ).transact

      _ <- Entity.i.a1.double_.>(double2).query.get.map(_ ==> List(3, 4, 5, 6, 7, 8, 9))
      _ <- Entity.i.a1.double_.>(double2).double_.<=(double8).query.get.map(_ ==> List(3, 4, 5, 6, 7, 8))
      _ <- Entity.i.a1.double_.>(double2).double_.<=(double8).double_.not(double4, double5).query.get.map(_ ==> List(3, 6, 7, 8))
    } yield ()
  }
}
