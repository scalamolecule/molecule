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

case class Aggr_Long_(
  suite: MUnit,
  api: Api_async & Spi_async & DbProviders
) extends TestUtils {

  import api.*
  import suite.*

  "distinct" - types {
    for {
      _ <- Entity.i.long.insert(List(
        (1, long1),
        (2, long2),
        (2, long2),
        (2, long3),
      )).transact

      _ <- Entity.i.long.a1.query.get.map(_ ==> List(
        (1, long1),
        (2, long2), // 2 rows coalesced
        (2, long3),
      ))

      // Distinct values are returned in a Set
      _ <- Entity.i.a1.long(distinct).query.get.map(_ ==> List(
        (1, Set(long1)),
        (2, Set(long2, long3)),
      ))

      _ <- Entity.long(distinct).query.get.map(_.head ==> Set(
        long1, long2, long3
      ))
    } yield ()
  }


  "min" - types {
    val (a, b) = ((1, long1), (1, long2))
    for {
      _ <- Entity.i.long.insert(a, b).transact

      // 1 attribute
      _ <- Entity.long(min).query.get.map(_ ==> List(long1))

      _ <- Entity.long(min)(long1).query.get.map(_ ==> List(long1))
      _ <- Entity.long(min)(long2).query.get.map(_ ==> List())

      _ <- Entity.long(min).not(long1).query.get.map(_ ==> List())
      _ <- Entity.long(min).not(long2).query.get.map(_ ==> List(long1))

      _ <- Entity.long(min).<(long1).query.get.map(_ ==> List())
      _ <- Entity.long(min).<(long2).query.get.map(_ ==> List(long1))

      _ <- Entity.long(min).<=(long0).query.get.map(_ ==> List())
      _ <- Entity.long(min).<=(long1).query.get.map(_ ==> List(long1))

      _ <- Entity.long(min).>(long1).query.get.map(_ ==> List())
      _ <- Entity.long(min).>(long0).query.get.map(_ ==> List(long1))

      _ <- Entity.long(min).>=(long1).query.get.map(_ ==> List(long1))
      _ <- Entity.long(min).>=(long2).query.get.map(_ ==> List())


      // n attributes
      _ <- Entity.i.long(min).query.get.map(_ ==> List(a))

      _ <- Entity.i.long(min)(long1).query.get.map(_ ==> List(a))
      _ <- Entity.i.long(min)(long2).query.get.map(_ ==> List())

      _ <- Entity.i.long(min).not(long1).query.get.map(_ ==> List())
      _ <- Entity.i.long(min).not(long2).query.get.map(_ ==> List(a))

      _ <- Entity.i.long(min).<(long1).query.get.map(_ ==> List())
      _ <- Entity.i.long(min).<(long2).query.get.map(_ ==> List(a))

      _ <- Entity.i.long(min).<=(long0).query.get.map(_ ==> List())
      _ <- Entity.i.long(min).<=(long1).query.get.map(_ ==> List(a))

      _ <- Entity.i.long(min).>(long1).query.get.map(_ ==> List())
      _ <- Entity.i.long(min).>(long0).query.get.map(_ ==> List(a))

      _ <- Entity.i.long(min).>=(long1).query.get.map(_ ==> List(a))
      _ <- Entity.i.long(min).>=(long2).query.get.map(_ ==> List())
    } yield ()
  }


  "max" - types {
    val (a, b) = ((1, long1), (1, long2))
    for {
      _ <- Entity.i.long.insert(a, b).transact

      // 1 attribute
      _ <- Entity.long(max).query.get.map(_ ==> List(long2))

      _ <- Entity.long(max)(long2).query.get.map(_ ==> List(long2))
      _ <- Entity.long(max)(long1).query.get.map(_ ==> List())

      _ <- Entity.long(max).not(long2).query.get.map(_ ==> List())
      _ <- Entity.long(max).not(long1).query.get.map(_ ==> List(long2))

      _ <- Entity.long(max).<(long2).query.get.map(_ ==> List())
      _ <- Entity.long(max).<(long3).query.get.map(_ ==> List(long2))

      _ <- Entity.long(max).<=(long1).query.get.map(_ ==> List())
      _ <- Entity.long(max).<=(long2).query.get.map(_ ==> List(long2))

      _ <- Entity.long(max).>(long2).query.get.map(_ ==> List())
      _ <- Entity.long(max).>(long1).query.get.map(_ ==> List(long2))

      _ <- Entity.long(max).>=(long2).query.get.map(_ ==> List(long2))
      _ <- Entity.long(max).>=(long3).query.get.map(_ ==> List())


      // n attributes
      _ <- Entity.i.long(max).query.get.map(_ ==> List(b))

      _ <- Entity.i.long(max)(long2).query.get.map(_ ==> List(b))
      _ <- Entity.i.long(max)(long1).query.get.map(_ ==> List())

      _ <- Entity.i.long(max).not(long2).query.get.map(_ ==> List())
      _ <- Entity.i.long(max).not(long1).query.get.map(_ ==> List(b))

      _ <- Entity.i.long(max).<(long2).query.get.map(_ ==> List())
      _ <- Entity.i.long(max).<(long3).query.get.map(_ ==> List(b))

      _ <- Entity.i.long(max).<=(long1).query.get.map(_ ==> List())
      _ <- Entity.i.long(max).<=(long2).query.get.map(_ ==> List(b))

      _ <- Entity.i.long(max).>(long2).query.get.map(_ ==> List())
      _ <- Entity.i.long(max).>(long1).query.get.map(_ ==> List(b))

      _ <- Entity.i.long(max).>=(long2).query.get.map(_ ==> List(b))
      _ <- Entity.i.long(max).>=(long3).query.get.map(_ ==> List())
    } yield ()
  }


  "min/max" - types {
    for {
      _ <- Entity.i.long.insert(
        (1, long1),
        (1, long2),
        (2, long3),
        (2, long4),
      ).transact

      _ <- Entity.long(min).long(max).query.get.map(_ ==> List((long1, long4)))

      _ <- Entity.long(min)(long1).long(max)(long4).query.get.map(_ ==> List((long1, long4)))
      _ <- Entity.long(min)(long1).long(max)(long5).query.get.map(_ ==> List())

      _ <- Entity.long(min).not(long2).long(max).not(long3).query.get.map(_ ==> List((long1, long4)))
      _ <- Entity.long(min).not(long2).long(max).not(long4).query.get.map(_ ==> List())

      _ <- Entity.long(min).<(long2).long(max).>(long3).query.get.map(_ ==> List((long1, long4)))
      _ <- Entity.long(min).<(long2).long(max).>(long4).query.get.map(_ ==> List())

      _ <- Entity.long(min).<=(long1).long(max).>=(long4).query.get.map(_ ==> List((long1, long4)))
      _ <- Entity.long(min).<=(long1).long(max).>=(long5).query.get.map(_ ==> List())
    } yield ()
  }


  "min/max n" - types {
    for {
      _ <- Entity.i.long.insert(
        (1, long1),
        (1, long2),
        (1, long3),
        (2, long4),
        (2, long5),
        (2, long6),
        (2, long6), // (make sure grouped values coalesce)
      ).transact

      _ <- Entity.long(min(1)).query.get.map(_ ==> List(Set(long1)))
      _ <- Entity.long(min(2)).query.get.map(_ ==> List(Set(long1, long2)))

      _ <- Entity.long(max(1)).query.get.map(_ ==> List(Set(long6)))
      _ <- Entity.long(max(2)).query.get.map(_ ==> List(Set(long5, long6)))

      _ <- Entity.i.a1.long(min(2)).query.get.map(_ ==> List(
        (1, Set(long1, long2)),
        (2, Set(long4, long5))
      ))

      _ <- Entity.i.a1.long(max(2)).query.get.map(_ ==> List(
        (1, Set(long2, long3)),
        (2, Set(long5, long6))
      ))

      _ <- Entity.i.a1.long(min(2)).long(max(2)).query.get.map(_ ==> List(
        (1, Set(long1, long2), Set(long2, long3)),
        (2, Set(long4, long5), Set(long5, long6))
      ))
    } yield ()
  }


  "sample" - types {
    val all      = Set(long1, long2, long3)
    val allPairs = List((1, long1), (2, long2), (3, long3))
    for {
      _ <- Entity.i.long.insert(allPairs).transact

      // 1 attribute
      _ <- Entity.long(sample).query.get.map(res => all.contains(res.head) ==> true)

      // 1 attribute
      _ <- Entity.i.long(sample).query.get.map(res => allPairs.contains(res.head) ==> true)
    } yield ()
  }

  "sample ops" - types {
    if (Seq("mariadb", "mysql").contains(database)) {
      Entity.long(sample)(long1).query.get
        .map(_ ==> "Unexpected success").recover { case ModelError(err) =>
          err ==> "Operations on sample not implemented for this database."
        }
    } else {
      val all       = Set(long1, long2, long3)
      val (a, b, c) = ((1, long1), (2, long2), (3, long3))
      val allPairs  = List(a, b, c)
      for {
        _ <- Entity.i.long.insert(allPairs).transact

        // 1 attribute
        // Checking for equality on a sample doesn't make sense
        // _ <- Entity.long(sample)(long2).query.get.map(res => all.contains(res.head) ==> true)
        // If you want a specific value, this would be the natural query
        _ <- Entity.long(long2).query.get.map(_ ==> List(long2))

        _ <- Entity.long(sample).not(long2).query.get.map { res =>
          List(long1, long3).contains(res.head) ==> true
          (res.head == long2) ==> false
        }
        _ <- Entity.long(sample).<(long3).query.get.map { res =>
          List(long1, long2).contains(res.head) ==> true
          (res.head == long3) ==> false
        }
        _ <- Entity.long(sample).<=(long2).query.get.map { res =>
          List(long1, long2).contains(res.head) ==> true
          (res.head == long3) ==> false
        }
        _ <- Entity.long(sample).>(long1).query.get.map { res =>
          List(long2, long3).contains(res.head) ==> true
          (res.head == long1) ==> false
        }
        _ <- Entity.long(sample).>=(long2).query.get.map { res =>
          List(long2, long3).contains(res.head) ==> true
          (res.head == long1) ==> false
        }

        // 1 attribute
        _ <- Entity.i.long(sample).not(long2).query.get.map { res =>
          List(a, c).contains(res.head) ==> true
          (res.head == b) ==> false
        }
        _ <- Entity.i.long(sample).<(long3).query.get.map { res =>
          List(a, b).contains(res.head) ==> true
          (res.head == c) ==> false
        }
        _ <- Entity.i.long(sample).<=(long2).query.get.map { res =>
          List(a, b).contains(res.head) ==> true
          (res.head == c) ==> false
        }
        _ <- Entity.i.long(sample).>(long1).query.get.map { res =>
          List(b, c).contains(res.head) ==> true
          (res.head == a) ==> false
        }
        _ <- Entity.i.long(sample).>=(long2).query.get.map { res =>
          List(b, c).contains(res.head) ==> true
          (res.head == a) ==> false
        }
      } yield ()
    }
  }


  "samples(n)" - types {
    val all = Set(long1, long2, long3)
    for {
      _ <- Entity.long.insert(List(long1, long2, long3)).transact
      _ <- Entity.long(sample(1)).query.get.map(res => all.intersect(res.head).nonEmpty ==> true)
      _ <- Entity.long(sample(2)).query.get.map(res => all.intersect(res.head).nonEmpty ==> true)
    } yield ()
  }


  "count" - types {
    val (a, b) = ((1, 1), (2, 3))
    for {
      _ <- Entity.i.long.insert(List(
        (1, long1),
        (2, long2),
        (2, long2),
        (2, long3),
      )).transact

      // 1 attribute
      _ <- Entity.long(count).query.get.map(_ ==> List(4))

      _ <- Entity.long(count)(3).query.get.map(_ ==> List())
      _ <- Entity.long(count)(4).query.get.map(_ ==> List(4))

      _ <- Entity.long(count).not(3).query.get.map(_ ==> List(4))
      _ <- Entity.long(count).not(4).query.get.map(_ ==> List())

      _ <- Entity.long(count).<(5).query.get.map(_ ==> List(4))
      _ <- Entity.long(count).<(4).query.get.map(_ ==> List())

      _ <- Entity.long(count).<=(4).query.get.map(_ ==> List(4))
      _ <- Entity.long(count).<=(3).query.get.map(_ ==> List())

      _ <- Entity.long(count).>(3).query.get.map(_ ==> List(4))
      _ <- Entity.long(count).>(4).query.get.map(_ ==> List())

      _ <- Entity.long(count).>=(4).query.get.map(_ ==> List(4))
      _ <- Entity.long(count).>=(5).query.get.map(_ ==> List())


      // n attributes
      _ <- Entity.i.a1.long(count).query.get.map(_ ==> List(a, b))

      _ <- Entity.i.a1.long(count)(3).query.get.map(_ ==> List(b))
      _ <- Entity.i.a1.long(count)(2).query.get.map(_ ==> List())

      _ <- Entity.i.a1.long(count).not(3).query.get.map(_ ==> List(a))
      _ <- Entity.i.a1.long(count).not(2).query.get.map(_ ==> List(a, b))

      _ <- Entity.i.a1.long(count).<(3).query.get.map(_ ==> List(a))
      _ <- Entity.i.a1.long(count).<(4).query.get.map(_ ==> List(a, b))

      _ <- Entity.i.a1.long(count).<=(3).query.get.map(_ ==> List(a, b))
      _ <- Entity.i.a1.long(count).<=(2).query.get.map(_ ==> List(a))

      _ <- Entity.i.a1.long(count).>(2).query.get.map(_ ==> List(b))
      _ <- Entity.i.a1.long(count).>(3).query.get.map(_ ==> List())

      _ <- Entity.i.a1.long(count).>=(3).query.get.map(_ ==> List(b))
      _ <- Entity.i.a1.long(count).>=(4).query.get.map(_ ==> List())
    } yield ()
  }


  "countDistinct" - types {
    val (a, b) = ((1, 1), (2, 2))
    for {
      _ <- Entity.i.long.insert(List(
        (1, long1),
        (2, long2),
        (2, long2),
        (2, long3),
      )).transact

      // 1 attribute
      _ <- Entity.long(countDistinct).query.get.map(_ ==> List(3))

      _ <- Entity.long(countDistinct)(2).query.get.map(_ ==> List())
      _ <- Entity.long(countDistinct)(3).query.get.map(_ ==> List(3))

      _ <- Entity.long(countDistinct).not(2).query.get.map(_ ==> List(3))
      _ <- Entity.long(countDistinct).not(3).query.get.map(_ ==> List())

      _ <- Entity.long(countDistinct).<(4).query.get.map(_ ==> List(3))
      _ <- Entity.long(countDistinct).<(3).query.get.map(_ ==> List())

      _ <- Entity.long(countDistinct).<=(3).query.get.map(_ ==> List(3))
      _ <- Entity.long(countDistinct).<=(2).query.get.map(_ ==> List())

      _ <- Entity.long(countDistinct).>(2).query.get.map(_ ==> List(3))
      _ <- Entity.long(countDistinct).>(3).query.get.map(_ ==> List())

      _ <- Entity.long(countDistinct).>=(3).query.get.map(_ ==> List(3))
      _ <- Entity.long(countDistinct).>=(4).query.get.map(_ ==> List())


      // n attributes
      _ <- Entity.i.a1.long(countDistinct).query.get.map(_ ==> List(a, b))

      _ <- Entity.i.a1.long(countDistinct)(2).query.get.map(_ ==> List(b))
      _ <- Entity.i.a1.long(countDistinct)(3).query.get.map(_ ==> List())

      _ <- Entity.i.a1.long(countDistinct).not(2).query.get.map(_ ==> List(a))
      _ <- Entity.i.a1.long(countDistinct).not(3).query.get.map(_ ==> List(a, b))

      _ <- Entity.i.a1.long(countDistinct).<(2).query.get.map(_ ==> List(a))
      _ <- Entity.i.a1.long(countDistinct).<(3).query.get.map(_ ==> List(a, b))

      _ <- Entity.i.a1.long(countDistinct).<=(2).query.get.map(_ ==> List(a, b))
      _ <- Entity.i.a1.long(countDistinct).<=(1).query.get.map(_ ==> List(a))

      _ <- Entity.i.a1.long(countDistinct).>(1).query.get.map(_ ==> List(b))
      _ <- Entity.i.a1.long(countDistinct).>(2).query.get.map(_ ==> List())

      _ <- Entity.i.a1.long(countDistinct).>=(2).query.get.map(_ ==> List(b))
      _ <- Entity.i.a1.long(countDistinct).>=(3).query.get.map(_ ==> List())
    } yield ()
  }
}