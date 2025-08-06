// GENERATED CODE ********************************
package molecule.db.compliance.test.aggregation.any

import molecule.core.error.ModelError
import molecule.core.setup.{MUnit, TestUtils}
import molecule.db.common.api.Api_async
import molecule.db.common.spi.Spi_async
import molecule.db.common.util.Executor.*
import molecule.db.compliance.domains.dsl.Types.*
import molecule.db.compliance.setup.DbProviders
import org.scalactic.Equality

case class Aggr_BigInt_(
  suite: MUnit,
  api: Api_async & Spi_async & DbProviders
) extends TestUtils {

  import api.*
  import suite.*

  "distinct" - types {
    for {
      _ <- Entity.i.bigInt.insert(List(
        (1, bigInt1),
        (2, bigInt2),
        (2, bigInt2),
        (2, bigInt3),
      )).transact

      _ <- Entity.i.bigInt.a1.query.get.map(_ ==> List(
        (1, bigInt1),
        (2, bigInt2), // 2 rows coalesced
        (2, bigInt3),
      ))

      // Distinct values are returned in a Set
      _ <- Entity.i.a1.bigInt(distinct).query.get.map(_ ==> List(
        (1, Set(bigInt1)),
        (2, Set(bigInt2, bigInt3)),
      ))

      _ <- Entity.bigInt(distinct).query.get.map(_.head ==> Set(
        bigInt1, bigInt2, bigInt3
      ))

      // Include aggregated attribute too (possible but will always be the same)
      _ <- Entity.bigInt.a1.bigInt(distinct).query.get.map(_ ==> List(
        (bigInt1, Set(bigInt1)),
        (bigInt2, Set(bigInt2)),
        (bigInt3, Set(bigInt3)),
      ))
      _ <- Entity.bigInt(distinct).bigInt.a1.query.get.map(_ ==> List(
        (Set(bigInt1), bigInt1),
        (Set(bigInt2), bigInt2),
        (Set(bigInt3), bigInt3),
      ))
    } yield ()
  }


  "min" - types {
    val (a, b) = ((1, bigInt1), (1, bigInt2))
    for {
      _ <- Entity.i.bigInt.insert(a, b).transact

      // 1 attribute
      _ <- Entity.bigInt(min).query.get.map(_ ==> List(bigInt1))

      _ <- Entity.bigInt(min)(bigInt1).query.get.map(_ ==> List(bigInt1))
      _ <- Entity.bigInt(min)(bigInt2).query.get.map(_ ==> List())

      _ <- Entity.bigInt(min).not(bigInt1).query.get.map(_ ==> List())
      _ <- Entity.bigInt(min).not(bigInt2).query.get.map(_ ==> List(bigInt1))

      _ <- Entity.bigInt(min).<(bigInt1).query.get.map(_ ==> List())
      _ <- Entity.bigInt(min).<(bigInt2).query.get.map(_ ==> List(bigInt1))

      _ <- Entity.bigInt(min).<=(bigInt0).query.get.map(_ ==> List())
      _ <- Entity.bigInt(min).<=(bigInt1).query.get.map(_ ==> List(bigInt1))

      _ <- Entity.bigInt(min).>(bigInt1).query.get.map(_ ==> List())
      _ <- Entity.bigInt(min).>(bigInt0).query.get.map(_ ==> List(bigInt1))

      _ <- Entity.bigInt(min).>=(bigInt1).query.get.map(_ ==> List(bigInt1))
      _ <- Entity.bigInt(min).>=(bigInt2).query.get.map(_ ==> List())

      // n attributes
      _ <- Entity.i.bigInt(min).query.get.map(_ ==> List(a))

      _ <- Entity.i.bigInt(min)(bigInt1).query.get.map(_ ==> List(a))
      _ <- Entity.i.bigInt(min)(bigInt2).query.get.map(_ ==> List())

      _ <- Entity.i.bigInt(min).not(bigInt1).query.get.map(_ ==> List())
      _ <- Entity.i.bigInt(min).not(bigInt2).query.get.map(_ ==> List(a))

      _ <- Entity.i.bigInt(min).<(bigInt1).query.get.map(_ ==> List())
      _ <- Entity.i.bigInt(min).<(bigInt2).query.get.map(_ ==> List(a))

      _ <- Entity.i.bigInt(min).<=(bigInt0).query.get.map(_ ==> List())
      _ <- Entity.i.bigInt(min).<=(bigInt1).query.get.map(_ ==> List(a))

      _ <- Entity.i.bigInt(min).>(bigInt1).query.get.map(_ ==> List())
      _ <- Entity.i.bigInt(min).>(bigInt0).query.get.map(_ ==> List(a))

      _ <- Entity.i.bigInt(min).>=(bigInt1).query.get.map(_ ==> List(a))
      _ <- Entity.i.bigInt(min).>=(bigInt2).query.get.map(_ ==> List())

      // Include aggregated attribute too (possible but will always be the same)
      _ <- Entity.bigInt.a1.bigInt(min).query.get.map(_ ==> List(
        (bigInt1, bigInt1),
        (bigInt2, bigInt2),
      ))
      _ <- Entity.bigInt(min).bigInt.a1.query.get.map(_ ==> List(
        (bigInt1, bigInt1),
        (bigInt2, bigInt2),
      ))
    } yield ()
  }


  "max" - types {
    val (a, b) = ((1, bigInt1), (1, bigInt2))
    for {
      _ <- Entity.i.bigInt.insert(a, b).transact

      // 1 attribute
      _ <- Entity.bigInt(max).query.get.map(_ ==> List(bigInt2))

      _ <- Entity.bigInt(max)(bigInt2).query.get.map(_ ==> List(bigInt2))
      _ <- Entity.bigInt(max)(bigInt1).query.get.map(_ ==> List())

      _ <- Entity.bigInt(max).not(bigInt2).query.get.map(_ ==> List())
      _ <- Entity.bigInt(max).not(bigInt1).query.get.map(_ ==> List(bigInt2))

      _ <- Entity.bigInt(max).<(bigInt2).query.get.map(_ ==> List())
      _ <- Entity.bigInt(max).<(bigInt3).query.get.map(_ ==> List(bigInt2))

      _ <- Entity.bigInt(max).<=(bigInt1).query.get.map(_ ==> List())
      _ <- Entity.bigInt(max).<=(bigInt2).query.get.map(_ ==> List(bigInt2))

      _ <- Entity.bigInt(max).>(bigInt2).query.get.map(_ ==> List())
      _ <- Entity.bigInt(max).>(bigInt1).query.get.map(_ ==> List(bigInt2))

      _ <- Entity.bigInt(max).>=(bigInt2).query.get.map(_ ==> List(bigInt2))
      _ <- Entity.bigInt(max).>=(bigInt3).query.get.map(_ ==> List())

      // n attributes
      _ <- Entity.i.bigInt(max).query.get.map(_ ==> List(b))

      _ <- Entity.i.bigInt(max)(bigInt2).query.get.map(_ ==> List(b))
      _ <- Entity.i.bigInt(max)(bigInt1).query.get.map(_ ==> List())

      _ <- Entity.i.bigInt(max).not(bigInt2).query.get.map(_ ==> List())
      _ <- Entity.i.bigInt(max).not(bigInt1).query.get.map(_ ==> List(b))

      _ <- Entity.i.bigInt(max).<(bigInt2).query.get.map(_ ==> List())
      _ <- Entity.i.bigInt(max).<(bigInt3).query.get.map(_ ==> List(b))

      _ <- Entity.i.bigInt(max).<=(bigInt1).query.get.map(_ ==> List())
      _ <- Entity.i.bigInt(max).<=(bigInt2).query.get.map(_ ==> List(b))

      _ <- Entity.i.bigInt(max).>(bigInt2).query.get.map(_ ==> List())
      _ <- Entity.i.bigInt(max).>(bigInt1).query.get.map(_ ==> List(b))

      _ <- Entity.i.bigInt(max).>=(bigInt2).query.get.map(_ ==> List(b))
      _ <- Entity.i.bigInt(max).>=(bigInt3).query.get.map(_ ==> List())

      // Include aggregated attribute too (possible but will always be the same)
      _ <- Entity.bigInt.a1.bigInt(max).query.get.map(_ ==> List(
        (bigInt1, bigInt1),
        (bigInt2, bigInt2),
      ))
      _ <- Entity.bigInt(max).bigInt.a1.query.get.map(_ ==> List(
        (bigInt1, bigInt1),
        (bigInt2, bigInt2),
      ))
    } yield ()
  }


  "min/max" - types {
    for {
      _ <- Entity.i.bigInt.insert(
        (1, bigInt1),
        (1, bigInt2),
        (2, bigInt3),
        (2, bigInt4),
      ).transact

      _ <- Entity.bigInt(min).bigInt(max).query.get.map(_ ==> List((bigInt1, bigInt4)))

      _ <- Entity.bigInt(min)(bigInt1).bigInt(max)(bigInt4).query.get.map(_ ==> List((bigInt1, bigInt4)))
      _ <- Entity.bigInt(min)(bigInt1).bigInt(max)(bigInt5).query.get.map(_ ==> List())

      _ <- Entity.bigInt(min).not(bigInt2).bigInt(max).not(bigInt3).query.get.map(_ ==> List((bigInt1, bigInt4)))
      _ <- Entity.bigInt(min).not(bigInt2).bigInt(max).not(bigInt4).query.get.map(_ ==> List())

      _ <- Entity.bigInt(min).<(bigInt2).bigInt(max).>(bigInt3).query.get.map(_ ==> List((bigInt1, bigInt4)))
      _ <- Entity.bigInt(min).<(bigInt2).bigInt(max).>(bigInt4).query.get.map(_ ==> List())

      _ <- Entity.bigInt(min).<=(bigInt1).bigInt(max).>=(bigInt4).query.get.map(_ ==> List((bigInt1, bigInt4)))
      _ <- Entity.bigInt(min).<=(bigInt1).bigInt(max).>=(bigInt5).query.get.map(_ ==> List())
    } yield ()
  }


  "min/max n" - types {
    for {
      _ <- Entity.i.bigInt.insert(
        (1, bigInt1),
        (1, bigInt2),
        (1, bigInt3),
        (2, bigInt4),
        (2, bigInt5),
        (2, bigInt6),
        (2, bigInt6), // (make sure grouped values coalesce)
      ).transact

      _ <- Entity.bigInt(min(1)).query.get.map(_ ==> List(Set(bigInt1)))
      _ <- Entity.bigInt(min(2)).query.get.map(_ ==> List(Set(bigInt1, bigInt2)))

      _ <- Entity.bigInt(max(1)).query.get.map(_ ==> List(Set(bigInt6)))
      _ <- Entity.bigInt(max(2)).query.get.map(_ ==> List(Set(bigInt5, bigInt6)))

      _ <- Entity.i.a1.bigInt(min(2)).query.get.map(_ ==> List(
        (1, Set(bigInt1, bigInt2)),
        (2, Set(bigInt4, bigInt5))
      ))

      _ <- Entity.i.a1.bigInt(max(2)).query.get.map(_ ==> List(
        (1, Set(bigInt2, bigInt3)),
        (2, Set(bigInt5, bigInt6))
      ))

      _ <- Entity.i.a1.bigInt(min(2)).bigInt(max(2)).query.get.map(_ ==> List(
        (1, Set(bigInt1, bigInt2), Set(bigInt2, bigInt3)),
        (2, Set(bigInt4, bigInt5), Set(bigInt5, bigInt6))
      ))

      // Include aggregated attribute too (possible but will always be the same)
      _ <- Entity.bigInt.a1.bigInt(min(2)).query.get.map(_ ==> List(
        (bigInt1, Set(bigInt1)),
        (bigInt2, Set(bigInt2)),
        (bigInt3, Set(bigInt3)),
        (bigInt4, Set(bigInt4)),
        (bigInt5, Set(bigInt5)),
        (bigInt6, Set(bigInt6)),
      ))
      _ <- Entity.bigInt(min(2)).bigInt.a1.query.get.map(_ ==> List(
        (Set(bigInt1), bigInt1),
        (Set(bigInt2), bigInt2),
        (Set(bigInt3), bigInt3),
        (Set(bigInt4), bigInt4),
        (Set(bigInt5), bigInt5),
        (Set(bigInt6), bigInt6),
      ))

      _ <- Entity.bigInt.a1.bigInt(max(2)).query.get.map(_ ==> List(
        (bigInt1, Set(bigInt1)),
        (bigInt2, Set(bigInt2)),
        (bigInt3, Set(bigInt3)),
        (bigInt4, Set(bigInt4)),
        (bigInt5, Set(bigInt5)),
        (bigInt6, Set(bigInt6)),
      ))
      _ <- Entity.bigInt(max(2)).bigInt.a1.query.get.map(_ ==> List(
        (Set(bigInt1), bigInt1),
        (Set(bigInt2), bigInt2),
        (Set(bigInt3), bigInt3),
        (Set(bigInt4), bigInt4),
        (Set(bigInt5), bigInt5),
        (Set(bigInt6), bigInt6),
      ))
    } yield ()
  }


  "sample" - types {
    val all      = Set(bigInt1, bigInt2, bigInt3)
    val allPairs = List((1, bigInt1), (2, bigInt2), (3, bigInt3))
    for {
      _ <- Entity.i.bigInt.insert(allPairs).transact

      // 1 attribute
      _ <- Entity.bigInt(sample).query.get.map(res => all.contains(res.head) ==> true)

      // 1 attribute
      _ <- Entity.i.bigInt(sample).query.get.map(res => allPairs.contains(res.head) ==> true)
    } yield ()
  }

  "sample ops" - types {
    if (Seq("mariadb", "mysql").contains(database)) {
      Entity.bigInt(sample)(bigInt1).query.get
        .map(_ ==> "Unexpected success").recover { case ModelError(err) =>
          err ==> "Operations on sample not implemented for this database."
        }
    } else {
      val all       = Set(bigInt1, bigInt2, bigInt3)
      val (a, b, c) = ((1, bigInt1), (2, bigInt2), (3, bigInt3))
      val allPairs  = List(a, b, c)
      for {
        _ <- Entity.i.bigInt.insert(allPairs).transact

        // 1 attribute
        // Checking for equality on a sample doesn't make sense
        // _ <- Entity.bigInt(sample)(bigInt2).query.get.map(res => all.contains(res.head) ==> true)
        // If you want a specific value, this would be the natural query
        _ <- Entity.bigInt(bigInt2).query.get.map(_ ==> List(bigInt2))

        _ <- Entity.bigInt(sample).not(bigInt2).query.get.map { res =>
          List(bigInt1, bigInt3).contains(res.head) ==> true
          (res.head == bigInt2) ==> false
        }
        _ <- Entity.bigInt(sample).<(bigInt3).query.get.map { res =>
          List(bigInt1, bigInt2).contains(res.head) ==> true
          (res.head == bigInt3) ==> false
        }
        _ <- Entity.bigInt(sample).<=(bigInt2).query.get.map { res =>
          List(bigInt1, bigInt2).contains(res.head) ==> true
          (res.head == bigInt3) ==> false
        }
        _ <- Entity.bigInt(sample).>(bigInt1).query.get.map { res =>
          List(bigInt2, bigInt3).contains(res.head) ==> true
          (res.head == bigInt1) ==> false
        }
        _ <- Entity.bigInt(sample).>=(bigInt2).query.get.map { res =>
          List(bigInt2, bigInt3).contains(res.head) ==> true
          (res.head == bigInt1) ==> false
        }

        // 1 attribute
        _ <- Entity.i.bigInt(sample).not(bigInt2).query.get.map { res =>
          List(a, c).contains(res.head) ==> true
          (res.head == b) ==> false
        }
        _ <- Entity.i.bigInt(sample).<(bigInt3).query.get.map { res =>
          List(a, b).contains(res.head) ==> true
          (res.head == c) ==> false
        }
        _ <- Entity.i.bigInt(sample).<=(bigInt2).query.get.map { res =>
          List(a, b).contains(res.head) ==> true
          (res.head == c) ==> false
        }
        _ <- Entity.i.bigInt(sample).>(bigInt1).query.get.map { res =>
          List(b, c).contains(res.head) ==> true
          (res.head == a) ==> false
        }
        _ <- Entity.i.bigInt(sample).>=(bigInt2).query.get.map { res =>
          List(b, c).contains(res.head) ==> true
          (res.head == a) ==> false
        }
      } yield ()
    }
  }


  "samples(n)" - types {
    val all = Set(bigInt1, bigInt2, bigInt3)
    for {
      _ <- Entity.bigInt.insert(List(bigInt1, bigInt2, bigInt3)).transact
      _ <- Entity.bigInt(sample(1)).query.get.map(res => all.intersect(res.head).nonEmpty ==> true)
      _ <- Entity.bigInt(sample(2)).query.get.map(res => all.intersect(res.head).nonEmpty ==> true)
    } yield ()
  }


  "count" - types {
    val (a, b) = ((1, 1), (2, 3))
    for {
      _ <- Entity.i.bigInt.insert(List(
        (1, bigInt1),
        (2, bigInt2),
        (2, bigInt2),
        (2, bigInt3),
      )).transact

      // 1 attribute
      _ <- Entity.bigInt(count).query.get.map(_ ==> List(4))

      _ <- Entity.bigInt(count)(3).query.get.map(_ ==> List())
      _ <- Entity.bigInt(count)(4).query.get.map(_ ==> List(4))

      _ <- Entity.bigInt(count).not(3).query.get.map(_ ==> List(4))
      _ <- Entity.bigInt(count).not(4).query.get.map(_ ==> List())

      _ <- Entity.bigInt(count).<(5).query.get.map(_ ==> List(4))
      _ <- Entity.bigInt(count).<(4).query.get.map(_ ==> List())

      _ <- Entity.bigInt(count).<=(4).query.get.map(_ ==> List(4))
      _ <- Entity.bigInt(count).<=(3).query.get.map(_ ==> List())

      _ <- Entity.bigInt(count).>(3).query.get.map(_ ==> List(4))
      _ <- Entity.bigInt(count).>(4).query.get.map(_ ==> List())

      _ <- Entity.bigInt(count).>=(4).query.get.map(_ ==> List(4))
      _ <- Entity.bigInt(count).>=(5).query.get.map(_ ==> List())

      // n attributes
      _ <- Entity.i.a1.bigInt(count).query.get.map(_ ==> List(a, b))

      _ <- Entity.i.a1.bigInt(count)(3).query.get.map(_ ==> List(b))
      _ <- Entity.i.a1.bigInt(count)(2).query.get.map(_ ==> List())

      _ <- Entity.i.a1.bigInt(count).not(3).query.get.map(_ ==> List(a))
      _ <- Entity.i.a1.bigInt(count).not(2).query.get.map(_ ==> List(a, b))

      _ <- Entity.i.a1.bigInt(count).<(3).query.get.map(_ ==> List(a))
      _ <- Entity.i.a1.bigInt(count).<(4).query.get.map(_ ==> List(a, b))

      _ <- Entity.i.a1.bigInt(count).<=(3).query.get.map(_ ==> List(a, b))
      _ <- Entity.i.a1.bigInt(count).<=(2).query.get.map(_ ==> List(a))

      _ <- Entity.i.a1.bigInt(count).>(2).query.get.map(_ ==> List(b))
      _ <- Entity.i.a1.bigInt(count).>(3).query.get.map(_ ==> List())

      _ <- Entity.i.a1.bigInt(count).>=(3).query.get.map(_ ==> List(b))
      _ <- Entity.i.a1.bigInt(count).>=(4).query.get.map(_ ==> List())

      // Include aggregated attribute too
      _ <- Entity.bigInt.a1.bigInt(count).query.get.map(_ ==> List(
        (bigInt1, 1),
        (bigInt2, 2),
        (bigInt3, 1),
      ))
      _ <- Entity.bigInt(count).bigInt.a1.query.get.map(_ ==> List(
        (1, bigInt1),
        (2, bigInt2),
        (1, bigInt3),
      ))
    } yield ()
  }


  "countDistinct" - types {
    val (a, b) = ((1, 1), (2, 2))
    for {
      _ <- Entity.i.bigInt.insert(List(
        (1, bigInt1),
        (2, bigInt2),
        (2, bigInt2),
        (2, bigInt3),
      )).transact

      // 1 attribute
      _ <- Entity.bigInt(countDistinct).query.get.map(_ ==> List(3))

      _ <- Entity.bigInt(countDistinct)(2).query.get.map(_ ==> List())
      _ <- Entity.bigInt(countDistinct)(3).query.get.map(_ ==> List(3))

      _ <- Entity.bigInt(countDistinct).not(2).query.get.map(_ ==> List(3))
      _ <- Entity.bigInt(countDistinct).not(3).query.get.map(_ ==> List())

      _ <- Entity.bigInt(countDistinct).<(4).query.get.map(_ ==> List(3))
      _ <- Entity.bigInt(countDistinct).<(3).query.get.map(_ ==> List())

      _ <- Entity.bigInt(countDistinct).<=(3).query.get.map(_ ==> List(3))
      _ <- Entity.bigInt(countDistinct).<=(2).query.get.map(_ ==> List())

      _ <- Entity.bigInt(countDistinct).>(2).query.get.map(_ ==> List(3))
      _ <- Entity.bigInt(countDistinct).>(3).query.get.map(_ ==> List())

      _ <- Entity.bigInt(countDistinct).>=(3).query.get.map(_ ==> List(3))
      _ <- Entity.bigInt(countDistinct).>=(4).query.get.map(_ ==> List())

      // n attributes
      _ <- Entity.i.a1.bigInt(countDistinct).query.get.map(_ ==> List(a, b))

      _ <- Entity.i.a1.bigInt(countDistinct)(2).query.get.map(_ ==> List(b))
      _ <- Entity.i.a1.bigInt(countDistinct)(3).query.get.map(_ ==> List())

      _ <- Entity.i.a1.bigInt(countDistinct).not(2).query.get.map(_ ==> List(a))
      _ <- Entity.i.a1.bigInt(countDistinct).not(3).query.get.map(_ ==> List(a, b))

      _ <- Entity.i.a1.bigInt(countDistinct).<(2).query.get.map(_ ==> List(a))
      _ <- Entity.i.a1.bigInt(countDistinct).<(3).query.get.map(_ ==> List(a, b))

      _ <- Entity.i.a1.bigInt(countDistinct).<=(2).query.get.map(_ ==> List(a, b))
      _ <- Entity.i.a1.bigInt(countDistinct).<=(1).query.get.map(_ ==> List(a))

      _ <- Entity.i.a1.bigInt(countDistinct).>(1).query.get.map(_ ==> List(b))
      _ <- Entity.i.a1.bigInt(countDistinct).>(2).query.get.map(_ ==> List())

      _ <- Entity.i.a1.bigInt(countDistinct).>=(2).query.get.map(_ ==> List(b))
      _ <- Entity.i.a1.bigInt(countDistinct).>=(3).query.get.map(_ ==> List())

      // Include aggregated attribute too
      _ <- Entity.bigInt.a1.bigInt(countDistinct).query.get.map(_ ==> List(
        (bigInt1, 1),
        (bigInt2, 1),
        (bigInt3, 1),
      ))
      _ <- Entity.bigInt(countDistinct).bigInt.a1.query.get.map(_ ==> List(
        (1, bigInt1),
        (1, bigInt2),
        (1, bigInt3),
      ))
    } yield ()
  }
}