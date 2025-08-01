// GENERATED CODE ********************************
package molecule.db.compliance.test.aggregation.any

import java.time.LocalTime
import molecule.core.setup.{MUnit, TestUtils}
import molecule.db.compliance.domains.dsl.Types.*
import molecule.db.compliance.setup.DbProviders
import molecule.db.common.api.Api_async
import molecule.db.common.spi.Spi_async
import molecule.db.common.util.Executor.*
import org.scalactic.Equality
import org.scalactic.Equality

case class Aggr_LocalTime_(
  suite: MUnit,
  api: Api_async & Spi_async & DbProviders
) extends TestUtils {

  import api.*
  import suite.*

  "distinct" - types {
    for {
      _ <- Entity.i.localTime.insert(List(
        (1, localTime1),
        (2, localTime2),
        (2, localTime2),
        (2, localTime3),
      )).transact

      _ <- Entity.i.localTime.a1.query.get.map(_ ==> List(
        (1, localTime1),
        (2, localTime2), // 2 rows coalesced
        (2, localTime3),
      ))

      // Distinct values are returned in a Set
      _ <- Entity.i.a1.localTime(distinct).query.get.map(_ ==> List(
        (1, Set(localTime1)),
        (2, Set(localTime2, localTime3)),
      ))

      _ <- Entity.localTime(distinct).query.get.map(_.head ==> Set(
        localTime1, localTime2, localTime3
      ))
    } yield ()
  }


  "min" - types {
    val (a, b) = ((1, localTime1), (1, localTime2))
    for {
      _ <- Entity.i.localTime.insert(a, b).transact

      // 1 attribute
      _ <- Entity.localTime(min).query.get.map(_ ==> List(localTime1))

      _ <- Entity.localTime(min)(localTime1).query.get.map(_ ==> List(localTime1))
      _ <- Entity.localTime(min)(localTime2).query.get.map(_ ==> List())

      _ <- Entity.localTime(min).not(localTime1).query.get.map(_ ==> List())
      _ <- Entity.localTime(min).not(localTime2).query.get.map(_ ==> List(localTime1))

      _ <- Entity.localTime(min).<(localTime1).query.get.map(_ ==> List())
      _ <- Entity.localTime(min).<(localTime2).query.get.map(_ ==> List(localTime1))

      _ <- Entity.localTime(min).<=(localTime0).query.get.map(_ ==> List())
      _ <- Entity.localTime(min).<=(localTime1).query.get.map(_ ==> List(localTime1))

      _ <- Entity.localTime(min).>(localTime1).query.get.map(_ ==> List())
      _ <- Entity.localTime(min).>(localTime0).query.get.map(_ ==> List(localTime1))

      _ <- Entity.localTime(min).>=(localTime1).query.get.map(_ ==> List(localTime1))
      _ <- Entity.localTime(min).>=(localTime2).query.get.map(_ ==> List())


      // n attributes
      _ <- Entity.i.localTime(min).query.get.map(_ ==> List(a))

      _ <- Entity.i.localTime(min)(localTime1).query.get.map(_ ==> List(a))
      _ <- Entity.i.localTime(min)(localTime2).query.get.map(_ ==> List())

      _ <- Entity.i.localTime(min).not(localTime1).query.get.map(_ ==> List())
      _ <- Entity.i.localTime(min).not(localTime2).query.get.map(_ ==> List(a))

      _ <- Entity.i.localTime(min).<(localTime1).query.get.map(_ ==> List())
      _ <- Entity.i.localTime(min).<(localTime2).query.get.map(_ ==> List(a))

      _ <- Entity.i.localTime(min).<=(localTime0).query.get.map(_ ==> List())
      _ <- Entity.i.localTime(min).<=(localTime1).query.get.map(_ ==> List(a))

      _ <- Entity.i.localTime(min).>(localTime1).query.get.map(_ ==> List())
      _ <- Entity.i.localTime(min).>(localTime0).query.get.map(_ ==> List(a))

      _ <- Entity.i.localTime(min).>=(localTime1).query.get.map(_ ==> List(a))
      _ <- Entity.i.localTime(min).>=(localTime2).query.get.map(_ ==> List())
    } yield ()
  }


  "max" - types {
    val (a, b) = ((1, localTime1), (1, localTime2))
    for {
      _ <- Entity.i.localTime.insert(a, b).transact

      // 1 attribute
      _ <- Entity.localTime(max).query.get.map(_ ==> List(localTime2))

      _ <- Entity.localTime(max)(localTime2).query.get.map(_ ==> List(localTime2))
      _ <- Entity.localTime(max)(localTime1).query.get.map(_ ==> List())

      _ <- Entity.localTime(max).not(localTime2).query.get.map(_ ==> List())
      _ <- Entity.localTime(max).not(localTime1).query.get.map(_ ==> List(localTime2))

      _ <- Entity.localTime(max).<(localTime2).query.get.map(_ ==> List())
      _ <- Entity.localTime(max).<(localTime3).query.get.map(_ ==> List(localTime2))

      _ <- Entity.localTime(max).<=(localTime1).query.get.map(_ ==> List())
      _ <- Entity.localTime(max).<=(localTime2).query.get.map(_ ==> List(localTime2))

      _ <- Entity.localTime(max).>(localTime2).query.get.map(_ ==> List())
      _ <- Entity.localTime(max).>(localTime1).query.get.map(_ ==> List(localTime2))

      _ <- Entity.localTime(max).>=(localTime2).query.get.map(_ ==> List(localTime2))
      _ <- Entity.localTime(max).>=(localTime3).query.get.map(_ ==> List())


      // n attributes
      _ <- Entity.i.localTime(max).query.get.map(_ ==> List(b))

      _ <- Entity.i.localTime(max)(localTime2).query.get.map(_ ==> List(b))
      _ <- Entity.i.localTime(max)(localTime1).query.get.map(_ ==> List())

      _ <- Entity.i.localTime(max).not(localTime2).query.get.map(_ ==> List())
      _ <- Entity.i.localTime(max).not(localTime1).query.get.map(_ ==> List(b))

      _ <- Entity.i.localTime(max).<(localTime2).query.get.map(_ ==> List())
      _ <- Entity.i.localTime(max).<(localTime3).query.get.map(_ ==> List(b))

      _ <- Entity.i.localTime(max).<=(localTime1).query.get.map(_ ==> List())
      _ <- Entity.i.localTime(max).<=(localTime2).query.get.map(_ ==> List(b))

      _ <- Entity.i.localTime(max).>(localTime2).query.get.map(_ ==> List())
      _ <- Entity.i.localTime(max).>(localTime1).query.get.map(_ ==> List(b))

      _ <- Entity.i.localTime(max).>=(localTime2).query.get.map(_ ==> List(b))
      _ <- Entity.i.localTime(max).>=(localTime3).query.get.map(_ ==> List())
    } yield ()
  }


  "min/max" - types {
    for {
      _ <- Entity.i.localTime.insert(
        (1, localTime1),
        (1, localTime2),
        (2, localTime3),
        (2, localTime4),
      ).transact

      _ <- Entity.localTime(min).localTime(max).query.get.map(_ ==> List((localTime1, localTime4)))

      _ <- Entity.localTime(min)(localTime1).localTime(max)(localTime4).query.get.map(_ ==> List((localTime1, localTime4)))
      _ <- Entity.localTime(min)(localTime1).localTime(max)(localTime5).query.get.map(_ ==> List())

      _ <- Entity.localTime(min).not(localTime2).localTime(max).not(localTime3).query.get.map(_ ==> List((localTime1, localTime4)))
      _ <- Entity.localTime(min).not(localTime2).localTime(max).not(localTime4).query.get.map(_ ==> List())

      _ <- Entity.localTime(min).<(localTime2).localTime(max).>(localTime3).query.get.map(_ ==> List((localTime1, localTime4)))
      _ <- Entity.localTime(min).<(localTime2).localTime(max).>(localTime4).query.get.map(_ ==> List())

      _ <- Entity.localTime(min).<=(localTime1).localTime(max).>=(localTime4).query.get.map(_ ==> List((localTime1, localTime4)))
      _ <- Entity.localTime(min).<=(localTime1).localTime(max).>=(localTime5).query.get.map(_ ==> List())
    } yield ()
  }


  "min/max n" - types {
    for {
      _ <- Entity.i.localTime.insert(
        (1, localTime1),
        (1, localTime2),
        (1, localTime3),
        (2, localTime4),
        (2, localTime5),
        (2, localTime6),
        (2, localTime6), // (make sure grouped values coalesce)
      ).transact

      _ <- Entity.localTime(min(1)).query.get.map(_ ==> List(Set(localTime1)))
      _ <- Entity.localTime(min(2)).query.get.map(_ ==> List(Set(localTime1, localTime2)))

      _ <- Entity.localTime(max(1)).query.get.map(_ ==> List(Set(localTime6)))
      _ <- Entity.localTime(max(2)).query.get.map(_ ==> List(Set(localTime5, localTime6)))

      _ <- Entity.i.a1.localTime(min(2)).query.get.map(_ ==> List(
        (1, Set(localTime1, localTime2)),
        (2, Set(localTime4, localTime5))
      ))

      _ <- Entity.i.a1.localTime(max(2)).query.get.map(_ ==> List(
        (1, Set(localTime2, localTime3)),
        (2, Set(localTime5, localTime6))
      ))

      _ <- Entity.i.a1.localTime(min(2)).localTime(max(2)).query.get.map(_ ==> List(
        (1, Set(localTime1, localTime2), Set(localTime2, localTime3)),
        (2, Set(localTime4, localTime5), Set(localTime5, localTime6))
      ))
    } yield ()
  }


  "sample" - types {
    val all       = Set(localTime1, localTime2, localTime3)
    val (a, b, c) = ((1, localTime1), (2, localTime2), (3, localTime3))
    val allPairs  = List(a, b, c)
    for {
      _ <- Entity.i.localTime.insert(allPairs).transact

      // 1 attribute
      _ <- Entity.localTime(sample).query.get.map(res => all.contains(res.head) ==> true)

      // Checking for equality on a sample doesn't make sense
      // _ <- Entity.localTime(sample)(localTime2).query.get.map(res => all.contains(res.head) ==> true)
      // If you want a specific value, this would be the natural query
      _ <- Entity.localTime(localTime2).query.get.map(_ ==> List(localTime2))

      _ <- Entity.localTime(sample).not(localTime2).query.get.map { res =>
        List(localTime1, localTime3).contains(res.head) ==> true
        (res.head == localTime2) ==> false
      }
      _ <- Entity.localTime(sample).<(localTime3).query.get.map { res =>
        List(localTime1, localTime2).contains(res.head) ==> true
        (res.head == localTime3) ==> false
      }
      _ <- Entity.localTime(sample).<=(localTime2).query.get.map { res =>
        List(localTime1, localTime2).contains(res.head) ==> true
        (res.head == localTime3) ==> false
      }
      _ <- Entity.localTime(sample).>(localTime1).query.get.map { res =>
        List(localTime2, localTime3).contains(res.head) ==> true
        (res.head == localTime1) ==> false
      }
      _ <- Entity.localTime(sample).>=(localTime2).query.get.map { res =>
        List(localTime2, localTime3).contains(res.head) ==> true
        (res.head == localTime1) ==> false
      }

      // 1 attribute
      _ <- Entity.i.localTime(sample).query.get.map(res => allPairs.contains(res.head) ==> true)

      _ <- Entity.i.localTime(sample).not(localTime2).query.get.map { res =>
        List(a, c).contains(res.head) ==> true
        (res.head == b) ==> false
      }
      _ <- Entity.i.localTime(sample).<(localTime3).query.get.map { res =>
        List(a, b).contains(res.head) ==> true
        (res.head == c) ==> false
      }
      _ <- Entity.i.localTime(sample).<=(localTime2).query.get.map { res =>
        List(a, b).contains(res.head) ==> true
        (res.head == c) ==> false
      }
      _ <- Entity.i.localTime(sample).>(localTime1).query.get.map { res =>
        List(b, c).contains(res.head) ==> true
        (res.head == a) ==> false
      }
      _ <- Entity.i.localTime(sample).>=(localTime2).query.get.map { res =>
        List(b, c).contains(res.head) ==> true
        (res.head == a) ==> false
      }
    } yield ()
  }


  "samples(n)" - types {
    val all = Set(localTime1, localTime2, localTime3)
    for {
      _ <- Entity.localTime.insert(List(localTime1, localTime2, localTime3)).transact
      _ <- Entity.localTime(sample(1)).query.get.map(res => all.intersect(res.head).nonEmpty ==> true)
      _ <- Entity.localTime(sample(2)).query.get.map(res => all.intersect(res.head).nonEmpty ==> true)
    } yield ()
  }


  "count" - types {
    val (a, b) = ((1, 1), (2, 3))
    for {
      _ <- Entity.i.localTime.insert(List(
        (1, localTime1),
        (2, localTime2),
        (2, localTime2),
        (2, localTime3),
      )).transact

      // 1 attribute
      _ <- Entity.localTime(count).query.get.map(_ ==> List(4))

      _ <- Entity.localTime(count)(3).query.get.map(_ ==> List())
      _ <- Entity.localTime(count)(4).query.get.map(_ ==> List(4))

      _ <- Entity.localTime(count).not(3).query.get.map(_ ==> List(4))
      _ <- Entity.localTime(count).not(4).query.get.map(_ ==> List())

      _ <- Entity.localTime(count).<(5).query.get.map(_ ==> List(4))
      _ <- Entity.localTime(count).<(4).query.get.map(_ ==> List())

      _ <- Entity.localTime(count).<=(4).query.get.map(_ ==> List(4))
      _ <- Entity.localTime(count).<=(3).query.get.map(_ ==> List())

      _ <- Entity.localTime(count).>(3).query.get.map(_ ==> List(4))
      _ <- Entity.localTime(count).>(4).query.get.map(_ ==> List())

      _ <- Entity.localTime(count).>=(4).query.get.map(_ ==> List(4))
      _ <- Entity.localTime(count).>=(5).query.get.map(_ ==> List())


      // n attributes
      _ <- Entity.i.a1.localTime(count).query.get.map(_ ==> List(a, b))

      _ <- Entity.i.a1.localTime(count)(3).query.get.map(_ ==> List(b))
      _ <- Entity.i.a1.localTime(count)(2).query.get.map(_ ==> List())

      _ <- Entity.i.a1.localTime(count).not(3).query.get.map(_ ==> List(a))
      _ <- Entity.i.a1.localTime(count).not(2).query.get.map(_ ==> List(a, b))

      _ <- Entity.i.a1.localTime(count).<(3).query.get.map(_ ==> List(a))
      _ <- Entity.i.a1.localTime(count).<(4).query.get.map(_ ==> List(a, b))

      _ <- Entity.i.a1.localTime(count).<=(3).query.get.map(_ ==> List(a, b))
      _ <- Entity.i.a1.localTime(count).<=(2).query.get.map(_ ==> List(a))

      _ <- Entity.i.a1.localTime(count).>(2).query.get.map(_ ==> List(b))
      _ <- Entity.i.a1.localTime(count).>(3).query.get.map(_ ==> List())

      _ <- Entity.i.a1.localTime(count).>=(3).query.get.map(_ ==> List(b))
      _ <- Entity.i.a1.localTime(count).>=(4).query.get.map(_ ==> List())
    } yield ()
  }


  "countDistinct" - types {
    val (a, b) = ((1, 1), (2, 2))
    for {
      _ <- Entity.i.localTime.insert(List(
        (1, localTime1),
        (2, localTime2),
        (2, localTime2),
        (2, localTime3),
      )).transact

      // 1 attribute
      _ <- Entity.localTime(countDistinct).query.get.map(_ ==> List(3))

      _ <- Entity.localTime(countDistinct)(2).query.get.map(_ ==> List())
      _ <- Entity.localTime(countDistinct)(3).query.get.map(_ ==> List(3))

      _ <- Entity.localTime(countDistinct).not(2).query.get.map(_ ==> List(3))
      _ <- Entity.localTime(countDistinct).not(3).query.get.map(_ ==> List())

      _ <- Entity.localTime(countDistinct).<(4).query.get.map(_ ==> List(3))
      _ <- Entity.localTime(countDistinct).<(3).query.get.map(_ ==> List())

      _ <- Entity.localTime(countDistinct).<=(3).query.get.map(_ ==> List(3))
      _ <- Entity.localTime(countDistinct).<=(2).query.get.map(_ ==> List())

      _ <- Entity.localTime(countDistinct).>(2).query.get.map(_ ==> List(3))
      _ <- Entity.localTime(countDistinct).>(3).query.get.map(_ ==> List())

      _ <- Entity.localTime(countDistinct).>=(3).query.get.map(_ ==> List(3))
      _ <- Entity.localTime(countDistinct).>=(4).query.get.map(_ ==> List())


      // n attributes
      _ <- Entity.i.a1.localTime(countDistinct).query.get.map(_ ==> List(a, b))

      _ <- Entity.i.a1.localTime(countDistinct)(2).query.get.map(_ ==> List(b))
      _ <- Entity.i.a1.localTime(countDistinct)(3).query.get.map(_ ==> List())

      _ <- Entity.i.a1.localTime(countDistinct).not(2).query.get.map(_ ==> List(a))
      _ <- Entity.i.a1.localTime(countDistinct).not(3).query.get.map(_ ==> List(a, b))

      _ <- Entity.i.a1.localTime(countDistinct).<(2).query.get.map(_ ==> List(a))
      _ <- Entity.i.a1.localTime(countDistinct).<(3).query.get.map(_ ==> List(a, b))

      _ <- Entity.i.a1.localTime(countDistinct).<=(2).query.get.map(_ ==> List(a, b))
      _ <- Entity.i.a1.localTime(countDistinct).<=(1).query.get.map(_ ==> List(a))

      _ <- Entity.i.a1.localTime(countDistinct).>(1).query.get.map(_ ==> List(b))
      _ <- Entity.i.a1.localTime(countDistinct).>(2).query.get.map(_ ==> List())

      _ <- Entity.i.a1.localTime(countDistinct).>=(2).query.get.map(_ ==> List(b))
      _ <- Entity.i.a1.localTime(countDistinct).>=(3).query.get.map(_ ==> List())
    } yield ()
  }
}