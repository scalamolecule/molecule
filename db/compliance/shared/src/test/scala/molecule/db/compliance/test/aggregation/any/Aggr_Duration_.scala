// GENERATED CODE ********************************
package molecule.db.compliance.test.aggregation.any

import java.time.Duration
import molecule.base.error.ModelError
import molecule.core.setup.{MUnit, TestUtils}
import molecule.db.common.api.Api_async
import molecule.db.common.spi.Spi_async
import molecule.db.common.util.Executor.*
import molecule.db.compliance.domains.dsl.Types.*
import molecule.db.compliance.setup.DbProviders
import org.scalactic.Equality

case class Aggr_Duration_(
  suite: MUnit,
  api: Api_async & Spi_async & DbProviders
) extends TestUtils {

  import api.*
  import suite.*

  "distinct" - types {
    for {
      _ <- Entity.i.duration.insert(List(
        (1, duration1),
        (2, duration2),
        (2, duration2),
        (2, duration3),
      )).transact

      _ <- Entity.i.duration.a1.query.get.map(_ ==> List(
        (1, duration1),
        (2, duration2), // 2 rows coalesced
        (2, duration3),
      ))

      // Distinct values are returned in a Set
      _ <- Entity.i.a1.duration(distinct).query.get.map(_ ==> List(
        (1, Set(duration1)),
        (2, Set(duration2, duration3)),
      ))

      _ <- Entity.duration(distinct).query.get.map(_.head ==> Set(
        duration1, duration2, duration3
      ))
    } yield ()
  }


  "min" - types {
    val (a, b) = ((1, duration1), (1, duration2))
    for {
      _ <- Entity.i.duration.insert(a, b).transact

      // 1 attribute
      _ <- Entity.duration(min).query.get.map(_ ==> List(duration1))

      _ <- Entity.duration(min)(duration1).query.get.map(_ ==> List(duration1))
      _ <- Entity.duration(min)(duration2).query.get.map(_ ==> List())

      _ <- Entity.duration(min).not(duration1).query.get.map(_ ==> List())
      _ <- Entity.duration(min).not(duration2).query.get.map(_ ==> List(duration1))

      _ <- Entity.duration(min).<(duration1).query.get.map(_ ==> List())
      _ <- Entity.duration(min).<(duration2).query.get.map(_ ==> List(duration1))

      _ <- Entity.duration(min).<=(duration0).query.get.map(_ ==> List())
      _ <- Entity.duration(min).<=(duration1).query.get.map(_ ==> List(duration1))

      _ <- Entity.duration(min).>(duration1).query.get.map(_ ==> List())
      _ <- Entity.duration(min).>(duration0).query.get.map(_ ==> List(duration1))

      _ <- Entity.duration(min).>=(duration1).query.get.map(_ ==> List(duration1))
      _ <- Entity.duration(min).>=(duration2).query.get.map(_ ==> List())


      // n attributes
      _ <- Entity.i.duration(min).query.get.map(_ ==> List(a))

      _ <- Entity.i.duration(min)(duration1).query.get.map(_ ==> List(a))
      _ <- Entity.i.duration(min)(duration2).query.get.map(_ ==> List())

      _ <- Entity.i.duration(min).not(duration1).query.get.map(_ ==> List())
      _ <- Entity.i.duration(min).not(duration2).query.get.map(_ ==> List(a))

      _ <- Entity.i.duration(min).<(duration1).query.get.map(_ ==> List())
      _ <- Entity.i.duration(min).<(duration2).query.get.map(_ ==> List(a))

      _ <- Entity.i.duration(min).<=(duration0).query.get.map(_ ==> List())
      _ <- Entity.i.duration(min).<=(duration1).query.get.map(_ ==> List(a))

      _ <- Entity.i.duration(min).>(duration1).query.get.map(_ ==> List())
      _ <- Entity.i.duration(min).>(duration0).query.get.map(_ ==> List(a))

      _ <- Entity.i.duration(min).>=(duration1).query.get.map(_ ==> List(a))
      _ <- Entity.i.duration(min).>=(duration2).query.get.map(_ ==> List())
    } yield ()
  }


  "max" - types {
    val (a, b) = ((1, duration1), (1, duration2))
    for {
      _ <- Entity.i.duration.insert(a, b).transact

      // 1 attribute
      _ <- Entity.duration(max).query.get.map(_ ==> List(duration2))

      _ <- Entity.duration(max)(duration2).query.get.map(_ ==> List(duration2))
      _ <- Entity.duration(max)(duration1).query.get.map(_ ==> List())

      _ <- Entity.duration(max).not(duration2).query.get.map(_ ==> List())
      _ <- Entity.duration(max).not(duration1).query.get.map(_ ==> List(duration2))

      _ <- Entity.duration(max).<(duration2).query.get.map(_ ==> List())
      _ <- Entity.duration(max).<(duration3).query.get.map(_ ==> List(duration2))

      _ <- Entity.duration(max).<=(duration1).query.get.map(_ ==> List())
      _ <- Entity.duration(max).<=(duration2).query.get.map(_ ==> List(duration2))

      _ <- Entity.duration(max).>(duration2).query.get.map(_ ==> List())
      _ <- Entity.duration(max).>(duration1).query.get.map(_ ==> List(duration2))

      _ <- Entity.duration(max).>=(duration2).query.get.map(_ ==> List(duration2))
      _ <- Entity.duration(max).>=(duration3).query.get.map(_ ==> List())


      // n attributes
      _ <- Entity.i.duration(max).query.get.map(_ ==> List(b))

      _ <- Entity.i.duration(max)(duration2).query.get.map(_ ==> List(b))
      _ <- Entity.i.duration(max)(duration1).query.get.map(_ ==> List())

      _ <- Entity.i.duration(max).not(duration2).query.get.map(_ ==> List())
      _ <- Entity.i.duration(max).not(duration1).query.get.map(_ ==> List(b))

      _ <- Entity.i.duration(max).<(duration2).query.get.map(_ ==> List())
      _ <- Entity.i.duration(max).<(duration3).query.get.map(_ ==> List(b))

      _ <- Entity.i.duration(max).<=(duration1).query.get.map(_ ==> List())
      _ <- Entity.i.duration(max).<=(duration2).query.get.map(_ ==> List(b))

      _ <- Entity.i.duration(max).>(duration2).query.get.map(_ ==> List())
      _ <- Entity.i.duration(max).>(duration1).query.get.map(_ ==> List(b))

      _ <- Entity.i.duration(max).>=(duration2).query.get.map(_ ==> List(b))
      _ <- Entity.i.duration(max).>=(duration3).query.get.map(_ ==> List())
    } yield ()
  }


  "min/max" - types {
    for {
      _ <- Entity.i.duration.insert(
        (1, duration1),
        (1, duration2),
        (2, duration3),
        (2, duration4),
      ).transact

      _ <- Entity.duration(min).duration(max).query.get.map(_ ==> List((duration1, duration4)))

      _ <- Entity.duration(min)(duration1).duration(max)(duration4).query.get.map(_ ==> List((duration1, duration4)))
      _ <- Entity.duration(min)(duration1).duration(max)(duration5).query.get.map(_ ==> List())

      _ <- Entity.duration(min).not(duration2).duration(max).not(duration3).query.get.map(_ ==> List((duration1, duration4)))
      _ <- Entity.duration(min).not(duration2).duration(max).not(duration4).query.get.map(_ ==> List())

      _ <- Entity.duration(min).<(duration2).duration(max).>(duration3).query.get.map(_ ==> List((duration1, duration4)))
      _ <- Entity.duration(min).<(duration2).duration(max).>(duration4).query.get.map(_ ==> List())

      _ <- Entity.duration(min).<=(duration1).duration(max).>=(duration4).query.get.map(_ ==> List((duration1, duration4)))
      _ <- Entity.duration(min).<=(duration1).duration(max).>=(duration5).query.get.map(_ ==> List())
    } yield ()
  }


  "min/max n" - types {
    for {
      _ <- Entity.i.duration.insert(
        (1, duration1),
        (1, duration2),
        (1, duration3),
        (2, duration4),
        (2, duration5),
        (2, duration6),
        (2, duration6), // (make sure grouped values coalesce)
      ).transact

      _ <- Entity.duration(min(1)).query.get.map(_ ==> List(Set(duration1)))
      _ <- Entity.duration(min(2)).query.get.map(_ ==> List(Set(duration1, duration2)))

      _ <- Entity.duration(max(1)).query.get.map(_ ==> List(Set(duration6)))
      _ <- Entity.duration(max(2)).query.get.map(_ ==> List(Set(duration5, duration6)))

      _ <- Entity.i.a1.duration(min(2)).query.get.map(_ ==> List(
        (1, Set(duration1, duration2)),
        (2, Set(duration4, duration5))
      ))

      _ <- Entity.i.a1.duration(max(2)).query.get.map(_ ==> List(
        (1, Set(duration2, duration3)),
        (2, Set(duration5, duration6))
      ))

      _ <- Entity.i.a1.duration(min(2)).duration(max(2)).query.get.map(_ ==> List(
        (1, Set(duration1, duration2), Set(duration2, duration3)),
        (2, Set(duration4, duration5), Set(duration5, duration6))
      ))
    } yield ()
  }


  "sample" - types {
    val all      = Set(duration1, duration2, duration3)
    val allPairs = List((1, duration1), (2, duration2), (3, duration3))
    for {
      _ <- Entity.i.duration.insert(allPairs).transact

      // 1 attribute
      _ <- Entity.duration(sample).query.get.map(res => all.contains(res.head) ==> true)

      // 1 attribute
      _ <- Entity.i.duration(sample).query.get.map(res => allPairs.contains(res.head) ==> true)
    } yield ()
  }

  "sample ops" - types {
    if (Seq("mariadb", "mysql").contains(database)) {
      Entity.duration(sample)(duration1).query.get
        .map(_ ==> "Unexpected success").recover { case ModelError(err) =>
          err ==> "Operations on sample not implemented for this database."
        }
    } else {
      val all       = Set(duration1, duration2, duration3)
      val (a, b, c) = ((1, duration1), (2, duration2), (3, duration3))
      val allPairs  = List(a, b, c)
      for {
        _ <- Entity.i.duration.insert(allPairs).transact

        // 1 attribute
        // Checking for equality on a sample doesn't make sense
        // _ <- Entity.duration(sample)(duration2).query.get.map(res => all.contains(res.head) ==> true)
        // If you want a specific value, this would be the natural query
        _ <- Entity.duration(duration2).query.get.map(_ ==> List(duration2))

        _ <- Entity.duration(sample).not(duration2).query.get.map { res =>
          List(duration1, duration3).contains(res.head) ==> true
          (res.head == duration2) ==> false
        }
        _ <- Entity.duration(sample).<(duration3).query.get.map { res =>
          List(duration1, duration2).contains(res.head) ==> true
          (res.head == duration3) ==> false
        }
        _ <- Entity.duration(sample).<=(duration2).query.get.map { res =>
          List(duration1, duration2).contains(res.head) ==> true
          (res.head == duration3) ==> false
        }
        _ <- Entity.duration(sample).>(duration1).query.get.map { res =>
          List(duration2, duration3).contains(res.head) ==> true
          (res.head == duration1) ==> false
        }
        _ <- Entity.duration(sample).>=(duration2).query.get.map { res =>
          List(duration2, duration3).contains(res.head) ==> true
          (res.head == duration1) ==> false
        }

        // 1 attribute
        _ <- Entity.i.duration(sample).not(duration2).query.get.map { res =>
          List(a, c).contains(res.head) ==> true
          (res.head == b) ==> false
        }
        _ <- Entity.i.duration(sample).<(duration3).query.get.map { res =>
          List(a, b).contains(res.head) ==> true
          (res.head == c) ==> false
        }
        _ <- Entity.i.duration(sample).<=(duration2).query.get.map { res =>
          List(a, b).contains(res.head) ==> true
          (res.head == c) ==> false
        }
        _ <- Entity.i.duration(sample).>(duration1).query.get.map { res =>
          List(b, c).contains(res.head) ==> true
          (res.head == a) ==> false
        }
        _ <- Entity.i.duration(sample).>=(duration2).query.get.map { res =>
          List(b, c).contains(res.head) ==> true
          (res.head == a) ==> false
        }
      } yield ()
    }
  }


  "samples(n)" - types {
    val all = Set(duration1, duration2, duration3)
    for {
      _ <- Entity.duration.insert(List(duration1, duration2, duration3)).transact
      _ <- Entity.duration(sample(1)).query.get.map(res => all.intersect(res.head).nonEmpty ==> true)
      _ <- Entity.duration(sample(2)).query.get.map(res => all.intersect(res.head).nonEmpty ==> true)
    } yield ()
  }


  "count" - types {
    val (a, b) = ((1, 1), (2, 3))
    for {
      _ <- Entity.i.duration.insert(List(
        (1, duration1),
        (2, duration2),
        (2, duration2),
        (2, duration3),
      )).transact

      // 1 attribute
      _ <- Entity.duration(count).query.get.map(_ ==> List(4))

      _ <- Entity.duration(count)(3).query.get.map(_ ==> List())
      _ <- Entity.duration(count)(4).query.get.map(_ ==> List(4))

      _ <- Entity.duration(count).not(3).query.get.map(_ ==> List(4))
      _ <- Entity.duration(count).not(4).query.get.map(_ ==> List())

      _ <- Entity.duration(count).<(5).query.get.map(_ ==> List(4))
      _ <- Entity.duration(count).<(4).query.get.map(_ ==> List())

      _ <- Entity.duration(count).<=(4).query.get.map(_ ==> List(4))
      _ <- Entity.duration(count).<=(3).query.get.map(_ ==> List())

      _ <- Entity.duration(count).>(3).query.get.map(_ ==> List(4))
      _ <- Entity.duration(count).>(4).query.get.map(_ ==> List())

      _ <- Entity.duration(count).>=(4).query.get.map(_ ==> List(4))
      _ <- Entity.duration(count).>=(5).query.get.map(_ ==> List())


      // n attributes
      _ <- Entity.i.a1.duration(count).query.get.map(_ ==> List(a, b))

      _ <- Entity.i.a1.duration(count)(3).query.get.map(_ ==> List(b))
      _ <- Entity.i.a1.duration(count)(2).query.get.map(_ ==> List())

      _ <- Entity.i.a1.duration(count).not(3).query.get.map(_ ==> List(a))
      _ <- Entity.i.a1.duration(count).not(2).query.get.map(_ ==> List(a, b))

      _ <- Entity.i.a1.duration(count).<(3).query.get.map(_ ==> List(a))
      _ <- Entity.i.a1.duration(count).<(4).query.get.map(_ ==> List(a, b))

      _ <- Entity.i.a1.duration(count).<=(3).query.get.map(_ ==> List(a, b))
      _ <- Entity.i.a1.duration(count).<=(2).query.get.map(_ ==> List(a))

      _ <- Entity.i.a1.duration(count).>(2).query.get.map(_ ==> List(b))
      _ <- Entity.i.a1.duration(count).>(3).query.get.map(_ ==> List())

      _ <- Entity.i.a1.duration(count).>=(3).query.get.map(_ ==> List(b))
      _ <- Entity.i.a1.duration(count).>=(4).query.get.map(_ ==> List())
    } yield ()
  }


  "countDistinct" - types {
    val (a, b) = ((1, 1), (2, 2))
    for {
      _ <- Entity.i.duration.insert(List(
        (1, duration1),
        (2, duration2),
        (2, duration2),
        (2, duration3),
      )).transact

      // 1 attribute
      _ <- Entity.duration(countDistinct).query.get.map(_ ==> List(3))

      _ <- Entity.duration(countDistinct)(2).query.get.map(_ ==> List())
      _ <- Entity.duration(countDistinct)(3).query.get.map(_ ==> List(3))

      _ <- Entity.duration(countDistinct).not(2).query.get.map(_ ==> List(3))
      _ <- Entity.duration(countDistinct).not(3).query.get.map(_ ==> List())

      _ <- Entity.duration(countDistinct).<(4).query.get.map(_ ==> List(3))
      _ <- Entity.duration(countDistinct).<(3).query.get.map(_ ==> List())

      _ <- Entity.duration(countDistinct).<=(3).query.get.map(_ ==> List(3))
      _ <- Entity.duration(countDistinct).<=(2).query.get.map(_ ==> List())

      _ <- Entity.duration(countDistinct).>(2).query.get.map(_ ==> List(3))
      _ <- Entity.duration(countDistinct).>(3).query.get.map(_ ==> List())

      _ <- Entity.duration(countDistinct).>=(3).query.get.map(_ ==> List(3))
      _ <- Entity.duration(countDistinct).>=(4).query.get.map(_ ==> List())


      // n attributes
      _ <- Entity.i.a1.duration(countDistinct).query.get.map(_ ==> List(a, b))

      _ <- Entity.i.a1.duration(countDistinct)(2).query.get.map(_ ==> List(b))
      _ <- Entity.i.a1.duration(countDistinct)(3).query.get.map(_ ==> List())

      _ <- Entity.i.a1.duration(countDistinct).not(2).query.get.map(_ ==> List(a))
      _ <- Entity.i.a1.duration(countDistinct).not(3).query.get.map(_ ==> List(a, b))

      _ <- Entity.i.a1.duration(countDistinct).<(2).query.get.map(_ ==> List(a))
      _ <- Entity.i.a1.duration(countDistinct).<(3).query.get.map(_ ==> List(a, b))

      _ <- Entity.i.a1.duration(countDistinct).<=(2).query.get.map(_ ==> List(a, b))
      _ <- Entity.i.a1.duration(countDistinct).<=(1).query.get.map(_ ==> List(a))

      _ <- Entity.i.a1.duration(countDistinct).>(1).query.get.map(_ ==> List(b))
      _ <- Entity.i.a1.duration(countDistinct).>(2).query.get.map(_ ==> List())

      _ <- Entity.i.a1.duration(countDistinct).>=(2).query.get.map(_ ==> List(b))
      _ <- Entity.i.a1.duration(countDistinct).>=(3).query.get.map(_ ==> List())
    } yield ()
  }
}