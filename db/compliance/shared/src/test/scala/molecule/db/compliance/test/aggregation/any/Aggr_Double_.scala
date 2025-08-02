// GENERATED CODE ********************************
package molecule.db.compliance.test.aggregation.any

import molecule.base.error.ModelError
import molecule.core.setup.{MUnit, TestUtils}
import molecule.db.common.api.Api_async
import molecule.db.common.spi.Spi_async
import molecule.db.common.util.Executor.*
import molecule.db.compliance.domains.dsl.Types.*
import molecule.db.compliance.setup.DbProviders
import org.scalactic.Equality

case class Aggr_Double_(
  suite: MUnit,
  api: Api_async & Spi_async & DbProviders
) extends TestUtils {

  import api.*
  import suite.*

  "distinct" - types {
    for {
      _ <- Entity.i.double.insert(List(
        (1, double1),
        (2, double2),
        (2, double2),
        (2, double3),
      )).transact

      _ <- Entity.i.double.a1.query.get.map(_ ==> List(
        (1, double1),
        (2, double2), // 2 rows coalesced
        (2, double3),
      ))

      // Distinct values are returned in a Set
      _ <- Entity.i.a1.double(distinct).query.get.map(_ ==> List(
        (1, Set(double1)),
        (2, Set(double2, double3)),
      ))

      _ <- Entity.double(distinct).query.get.map(_.head ==> Set(
        double1, double2, double3
      ))

      // Include aggregated attribute too (possible but will always be the same)
      _ <- Entity.double.a1.double(distinct).query.get.map(_ ==> List(
        (double1, Set(double1)),
        (double2, Set(double2)),
        (double3, Set(double3)),
      ))
      _ <- Entity.double(distinct).double.a1.query.get.map(_ ==> List(
        (Set(double1), double1),
        (Set(double2), double2),
        (Set(double3), double3),
      ))
    } yield ()
  }


  "min" - types {
    val (a, b) = ((1, double1), (1, double2))
    for {
      _ <- Entity.i.double.insert(a, b).transact

      // 1 attribute
      _ <- Entity.double(min).query.get.map(_ ==> List(double1))

      _ <- Entity.double(min)(double1).query.get.map(_ ==> List(double1))
      _ <- Entity.double(min)(double2).query.get.map(_ ==> List())

      _ <- Entity.double(min).not(double1).query.get.map(_ ==> List())
      _ <- Entity.double(min).not(double2).query.get.map(_ ==> List(double1))

      _ <- Entity.double(min).<(double1).query.get.map(_ ==> List())
      _ <- Entity.double(min).<(double2).query.get.map(_ ==> List(double1))

      _ <- Entity.double(min).<=(double0).query.get.map(_ ==> List())
      _ <- Entity.double(min).<=(double1).query.get.map(_ ==> List(double1))

      _ <- Entity.double(min).>(double1).query.get.map(_ ==> List())
      _ <- Entity.double(min).>(double0).query.get.map(_ ==> List(double1))

      _ <- Entity.double(min).>=(double1).query.get.map(_ ==> List(double1))
      _ <- Entity.double(min).>=(double2).query.get.map(_ ==> List())

      // n attributes
      _ <- Entity.i.double(min).query.get.map(_ ==> List(a))

      _ <- Entity.i.double(min)(double1).query.get.map(_ ==> List(a))
      _ <- Entity.i.double(min)(double2).query.get.map(_ ==> List())

      _ <- Entity.i.double(min).not(double1).query.get.map(_ ==> List())
      _ <- Entity.i.double(min).not(double2).query.get.map(_ ==> List(a))

      _ <- Entity.i.double(min).<(double1).query.get.map(_ ==> List())
      _ <- Entity.i.double(min).<(double2).query.get.map(_ ==> List(a))

      _ <- Entity.i.double(min).<=(double0).query.get.map(_ ==> List())
      _ <- Entity.i.double(min).<=(double1).query.get.map(_ ==> List(a))

      _ <- Entity.i.double(min).>(double1).query.get.map(_ ==> List())
      _ <- Entity.i.double(min).>(double0).query.get.map(_ ==> List(a))

      _ <- Entity.i.double(min).>=(double1).query.get.map(_ ==> List(a))
      _ <- Entity.i.double(min).>=(double2).query.get.map(_ ==> List())

      // Include aggregated attribute too (possible but will always be the same)
      _ <- Entity.double.a1.double(min).query.get.map(_ ==> List(
        (double1, double1),
        (double2, double2),
      ))
      _ <- Entity.double(min).double.a1.query.get.map(_ ==> List(
        (double1, double1),
        (double2, double2),
      ))
    } yield ()
  }


  "max" - types {
    val (a, b) = ((1, double1), (1, double2))
    for {
      _ <- Entity.i.double.insert(a, b).transact

      // 1 attribute
      _ <- Entity.double(max).query.get.map(_ ==> List(double2))

      _ <- Entity.double(max)(double2).query.get.map(_ ==> List(double2))
      _ <- Entity.double(max)(double1).query.get.map(_ ==> List())

      _ <- Entity.double(max).not(double2).query.get.map(_ ==> List())
      _ <- Entity.double(max).not(double1).query.get.map(_ ==> List(double2))

      _ <- Entity.double(max).<(double2).query.get.map(_ ==> List())
      _ <- Entity.double(max).<(double3).query.get.map(_ ==> List(double2))

      _ <- Entity.double(max).<=(double1).query.get.map(_ ==> List())
      _ <- Entity.double(max).<=(double2).query.get.map(_ ==> List(double2))

      _ <- Entity.double(max).>(double2).query.get.map(_ ==> List())
      _ <- Entity.double(max).>(double1).query.get.map(_ ==> List(double2))

      _ <- Entity.double(max).>=(double2).query.get.map(_ ==> List(double2))
      _ <- Entity.double(max).>=(double3).query.get.map(_ ==> List())

      // n attributes
      _ <- Entity.i.double(max).query.get.map(_ ==> List(b))

      _ <- Entity.i.double(max)(double2).query.get.map(_ ==> List(b))
      _ <- Entity.i.double(max)(double1).query.get.map(_ ==> List())

      _ <- Entity.i.double(max).not(double2).query.get.map(_ ==> List())
      _ <- Entity.i.double(max).not(double1).query.get.map(_ ==> List(b))

      _ <- Entity.i.double(max).<(double2).query.get.map(_ ==> List())
      _ <- Entity.i.double(max).<(double3).query.get.map(_ ==> List(b))

      _ <- Entity.i.double(max).<=(double1).query.get.map(_ ==> List())
      _ <- Entity.i.double(max).<=(double2).query.get.map(_ ==> List(b))

      _ <- Entity.i.double(max).>(double2).query.get.map(_ ==> List())
      _ <- Entity.i.double(max).>(double1).query.get.map(_ ==> List(b))

      _ <- Entity.i.double(max).>=(double2).query.get.map(_ ==> List(b))
      _ <- Entity.i.double(max).>=(double3).query.get.map(_ ==> List())

      // Include aggregated attribute too (possible but will always be the same)
      _ <- Entity.double.a1.double(max).query.get.map(_ ==> List(
        (double1, double1),
        (double2, double2),
      ))
      _ <- Entity.double(max).double.a1.query.get.map(_ ==> List(
        (double1, double1),
        (double2, double2),
      ))
    } yield ()
  }


  "min/max" - types {
    for {
      _ <- Entity.i.double.insert(
        (1, double1),
        (1, double2),
        (2, double3),
        (2, double4),
      ).transact

      _ <- Entity.double(min).double(max).query.get.map(_ ==> List((double1, double4)))

      _ <- Entity.double(min)(double1).double(max)(double4).query.get.map(_ ==> List((double1, double4)))
      _ <- Entity.double(min)(double1).double(max)(double5).query.get.map(_ ==> List())

      _ <- Entity.double(min).not(double2).double(max).not(double3).query.get.map(_ ==> List((double1, double4)))
      _ <- Entity.double(min).not(double2).double(max).not(double4).query.get.map(_ ==> List())

      _ <- Entity.double(min).<(double2).double(max).>(double3).query.get.map(_ ==> List((double1, double4)))
      _ <- Entity.double(min).<(double2).double(max).>(double4).query.get.map(_ ==> List())

      _ <- Entity.double(min).<=(double1).double(max).>=(double4).query.get.map(_ ==> List((double1, double4)))
      _ <- Entity.double(min).<=(double1).double(max).>=(double5).query.get.map(_ ==> List())
    } yield ()
  }


  "min/max n" - types {
    for {
      _ <- Entity.i.double.insert(
        (1, double1),
        (1, double2),
        (1, double3),
        (2, double4),
        (2, double5),
        (2, double6),
        (2, double6), // (make sure grouped values coalesce)
      ).transact

      _ <- Entity.double(min(1)).query.get.map(_ ==> List(Set(double1)))
      _ <- Entity.double(min(2)).query.get.map(_ ==> List(Set(double1, double2)))

      _ <- Entity.double(max(1)).query.get.map(_ ==> List(Set(double6)))
      _ <- Entity.double(max(2)).query.get.map(_ ==> List(Set(double5, double6)))

      _ <- Entity.i.a1.double(min(2)).query.get.map(_ ==> List(
        (1, Set(double1, double2)),
        (2, Set(double4, double5))
      ))

      _ <- Entity.i.a1.double(max(2)).query.get.map(_ ==> List(
        (1, Set(double2, double3)),
        (2, Set(double5, double6))
      ))

      _ <- Entity.i.a1.double(min(2)).double(max(2)).query.get.map(_ ==> List(
        (1, Set(double1, double2), Set(double2, double3)),
        (2, Set(double4, double5), Set(double5, double6))
      ))

      // Include aggregated attribute too (possible but will always be the same)
      _ <- Entity.double.a1.double(min(2)).query.get.map(_ ==> List(
        (double1, Set(double1)),
        (double2, Set(double2)),
        (double3, Set(double3)),
        (double4, Set(double4)),
        (double5, Set(double5)),
        (double6, Set(double6)),
      ))
      _ <- Entity.double(min(2)).double.a1.query.get.map(_ ==> List(
        (Set(double1), double1),
        (Set(double2), double2),
        (Set(double3), double3),
        (Set(double4), double4),
        (Set(double5), double5),
        (Set(double6), double6),
      ))

      _ <- Entity.double.a1.double(max(2)).query.get.map(_ ==> List(
        (double1, Set(double1)),
        (double2, Set(double2)),
        (double3, Set(double3)),
        (double4, Set(double4)),
        (double5, Set(double5)),
        (double6, Set(double6)),
      ))
      _ <- Entity.double(max(2)).double.a1.query.get.map(_ ==> List(
        (Set(double1), double1),
        (Set(double2), double2),
        (Set(double3), double3),
        (Set(double4), double4),
        (Set(double5), double5),
        (Set(double6), double6),
      ))
    } yield ()
  }


  "sample" - types {
    val all      = Set(double1, double2, double3)
    val allPairs = List((1, double1), (2, double2), (3, double3))
    for {
      _ <- Entity.i.double.insert(allPairs).transact

      // 1 attribute
      _ <- Entity.double(sample).query.get.map(res => all.contains(res.head) ==> true)

      // 1 attribute
      _ <- Entity.i.double(sample).query.get.map(res => allPairs.contains(res.head) ==> true)
    } yield ()
  }

  "sample ops" - types {
    if (Seq("mariadb", "mysql").contains(database)) {
      Entity.double(sample)(double1).query.get
        .map(_ ==> "Unexpected success").recover { case ModelError(err) =>
          err ==> "Operations on sample not implemented for this database."
        }
    } else {
      val all       = Set(double1, double2, double3)
      val (a, b, c) = ((1, double1), (2, double2), (3, double3))
      val allPairs  = List(a, b, c)
      for {
        _ <- Entity.i.double.insert(allPairs).transact

        // 1 attribute
        // Checking for equality on a sample doesn't make sense
        // _ <- Entity.double(sample)(double2).query.get.map(res => all.contains(res.head) ==> true)
        // If you want a specific value, this would be the natural query
        _ <- Entity.double(double2).query.get.map(_ ==> List(double2))

        _ <- Entity.double(sample).not(double2).query.get.map { res =>
          List(double1, double3).contains(res.head) ==> true
          (res.head == double2) ==> false
        }
        _ <- Entity.double(sample).<(double3).query.get.map { res =>
          List(double1, double2).contains(res.head) ==> true
          (res.head == double3) ==> false
        }
        _ <- Entity.double(sample).<=(double2).query.get.map { res =>
          List(double1, double2).contains(res.head) ==> true
          (res.head == double3) ==> false
        }
        _ <- Entity.double(sample).>(double1).query.get.map { res =>
          List(double2, double3).contains(res.head) ==> true
          (res.head == double1) ==> false
        }
        _ <- Entity.double(sample).>=(double2).query.get.map { res =>
          List(double2, double3).contains(res.head) ==> true
          (res.head == double1) ==> false
        }

        // 1 attribute
        _ <- Entity.i.double(sample).not(double2).query.get.map { res =>
          List(a, c).contains(res.head) ==> true
          (res.head == b) ==> false
        }
        _ <- Entity.i.double(sample).<(double3).query.get.map { res =>
          List(a, b).contains(res.head) ==> true
          (res.head == c) ==> false
        }
        _ <- Entity.i.double(sample).<=(double2).query.get.map { res =>
          List(a, b).contains(res.head) ==> true
          (res.head == c) ==> false
        }
        _ <- Entity.i.double(sample).>(double1).query.get.map { res =>
          List(b, c).contains(res.head) ==> true
          (res.head == a) ==> false
        }
        _ <- Entity.i.double(sample).>=(double2).query.get.map { res =>
          List(b, c).contains(res.head) ==> true
          (res.head == a) ==> false
        }
      } yield ()
    }
  }


  "samples(n)" - types {
    val all = Set(double1, double2, double3)
    for {
      _ <- Entity.double.insert(List(double1, double2, double3)).transact
      _ <- Entity.double(sample(1)).query.get.map(res => all.intersect(res.head).nonEmpty ==> true)
      _ <- Entity.double(sample(2)).query.get.map(res => all.intersect(res.head).nonEmpty ==> true)
    } yield ()
  }


  "count" - types {
    val (a, b) = ((1, 1), (2, 3))
    for {
      _ <- Entity.i.double.insert(List(
        (1, double1),
        (2, double2),
        (2, double2),
        (2, double3),
      )).transact

      // 1 attribute
      _ <- Entity.double(count).query.get.map(_ ==> List(4))

      _ <- Entity.double(count)(3).query.get.map(_ ==> List())
      _ <- Entity.double(count)(4).query.get.map(_ ==> List(4))

      _ <- Entity.double(count).not(3).query.get.map(_ ==> List(4))
      _ <- Entity.double(count).not(4).query.get.map(_ ==> List())

      _ <- Entity.double(count).<(5).query.get.map(_ ==> List(4))
      _ <- Entity.double(count).<(4).query.get.map(_ ==> List())

      _ <- Entity.double(count).<=(4).query.get.map(_ ==> List(4))
      _ <- Entity.double(count).<=(3).query.get.map(_ ==> List())

      _ <- Entity.double(count).>(3).query.get.map(_ ==> List(4))
      _ <- Entity.double(count).>(4).query.get.map(_ ==> List())

      _ <- Entity.double(count).>=(4).query.get.map(_ ==> List(4))
      _ <- Entity.double(count).>=(5).query.get.map(_ ==> List())

      // n attributes
      _ <- Entity.i.a1.double(count).query.get.map(_ ==> List(a, b))

      _ <- Entity.i.a1.double(count)(3).query.get.map(_ ==> List(b))
      _ <- Entity.i.a1.double(count)(2).query.get.map(_ ==> List())

      _ <- Entity.i.a1.double(count).not(3).query.get.map(_ ==> List(a))
      _ <- Entity.i.a1.double(count).not(2).query.get.map(_ ==> List(a, b))

      _ <- Entity.i.a1.double(count).<(3).query.get.map(_ ==> List(a))
      _ <- Entity.i.a1.double(count).<(4).query.get.map(_ ==> List(a, b))

      _ <- Entity.i.a1.double(count).<=(3).query.get.map(_ ==> List(a, b))
      _ <- Entity.i.a1.double(count).<=(2).query.get.map(_ ==> List(a))

      _ <- Entity.i.a1.double(count).>(2).query.get.map(_ ==> List(b))
      _ <- Entity.i.a1.double(count).>(3).query.get.map(_ ==> List())

      _ <- Entity.i.a1.double(count).>=(3).query.get.map(_ ==> List(b))
      _ <- Entity.i.a1.double(count).>=(4).query.get.map(_ ==> List())

      // Include aggregated attribute too
      _ <- Entity.double.a1.double(count).query.get.map(_ ==> List(
        (double1, 1),
        (double2, 2),
        (double3, 1),
      ))
      _ <- Entity.double(count).double.a1.query.get.map(_ ==> List(
        (1, double1),
        (2, double2),
        (1, double3),
      ))
    } yield ()
  }


  "countDistinct" - types {
    val (a, b) = ((1, 1), (2, 2))
    for {
      _ <- Entity.i.double.insert(List(
        (1, double1),
        (2, double2),
        (2, double2),
        (2, double3),
      )).transact

      // 1 attribute
      _ <- Entity.double(countDistinct).query.get.map(_ ==> List(3))

      _ <- Entity.double(countDistinct)(2).query.get.map(_ ==> List())
      _ <- Entity.double(countDistinct)(3).query.get.map(_ ==> List(3))

      _ <- Entity.double(countDistinct).not(2).query.get.map(_ ==> List(3))
      _ <- Entity.double(countDistinct).not(3).query.get.map(_ ==> List())

      _ <- Entity.double(countDistinct).<(4).query.get.map(_ ==> List(3))
      _ <- Entity.double(countDistinct).<(3).query.get.map(_ ==> List())

      _ <- Entity.double(countDistinct).<=(3).query.get.map(_ ==> List(3))
      _ <- Entity.double(countDistinct).<=(2).query.get.map(_ ==> List())

      _ <- Entity.double(countDistinct).>(2).query.get.map(_ ==> List(3))
      _ <- Entity.double(countDistinct).>(3).query.get.map(_ ==> List())

      _ <- Entity.double(countDistinct).>=(3).query.get.map(_ ==> List(3))
      _ <- Entity.double(countDistinct).>=(4).query.get.map(_ ==> List())

      // n attributes
      _ <- Entity.i.a1.double(countDistinct).query.get.map(_ ==> List(a, b))

      _ <- Entity.i.a1.double(countDistinct)(2).query.get.map(_ ==> List(b))
      _ <- Entity.i.a1.double(countDistinct)(3).query.get.map(_ ==> List())

      _ <- Entity.i.a1.double(countDistinct).not(2).query.get.map(_ ==> List(a))
      _ <- Entity.i.a1.double(countDistinct).not(3).query.get.map(_ ==> List(a, b))

      _ <- Entity.i.a1.double(countDistinct).<(2).query.get.map(_ ==> List(a))
      _ <- Entity.i.a1.double(countDistinct).<(3).query.get.map(_ ==> List(a, b))

      _ <- Entity.i.a1.double(countDistinct).<=(2).query.get.map(_ ==> List(a, b))
      _ <- Entity.i.a1.double(countDistinct).<=(1).query.get.map(_ ==> List(a))

      _ <- Entity.i.a1.double(countDistinct).>(1).query.get.map(_ ==> List(b))
      _ <- Entity.i.a1.double(countDistinct).>(2).query.get.map(_ ==> List())

      _ <- Entity.i.a1.double(countDistinct).>=(2).query.get.map(_ ==> List(b))
      _ <- Entity.i.a1.double(countDistinct).>=(3).query.get.map(_ ==> List())

      // Include aggregated attribute too
      _ <- Entity.double.a1.double(countDistinct).query.get.map(_ ==> List(
        (double1, 1),
        (double2, 1),
        (double3, 1),
      ))
      _ <- Entity.double(countDistinct).double.a1.query.get.map(_ ==> List(
        (1, double1),
        (1, double2),
        (1, double3),
      ))
    } yield ()
  }
}