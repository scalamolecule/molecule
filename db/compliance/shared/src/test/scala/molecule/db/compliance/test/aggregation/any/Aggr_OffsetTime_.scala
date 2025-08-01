// GENERATED CODE ********************************
package molecule.db.compliance.test.aggregation.any

import java.time.OffsetTime
import molecule.core.setup.{MUnit, TestUtils}
import molecule.db.compliance.domains.dsl.Types.*
import molecule.db.compliance.setup.DbProviders
import molecule.db.common.api.Api_async
import molecule.db.common.spi.Spi_async
import molecule.db.common.util.Executor.*
import org.scalactic.Equality
import org.scalactic.Equality

case class Aggr_OffsetTime_(
  suite: MUnit,
  api: Api_async & Spi_async & DbProviders
) extends TestUtils {

  import api.*
  import suite.*

  "distinct" - types {
    for {
      _ <- Entity.i.offsetTime.insert(List(
        (1, offsetTime1),
        (2, offsetTime2),
        (2, offsetTime2),
        (2, offsetTime3),
      )).transact

      _ <- Entity.i.offsetTime.a1.query.get.map(_ ==> List(
        (1, offsetTime1),
        (2, offsetTime2), // 2 rows coalesced
        (2, offsetTime3),
      ))

      // Distinct values are returned in a Set
      _ <- Entity.i.a1.offsetTime(distinct).query.get.map(_ ==> List(
        (1, Set(offsetTime1)),
        (2, Set(offsetTime2, offsetTime3)),
      ))

      _ <- Entity.offsetTime(distinct).query.get.map(_.head ==> Set(
        offsetTime1, offsetTime2, offsetTime3
      ))
    } yield ()
  }


  "min" - types {
    val (a, b) = ((1, offsetTime1), (1, offsetTime2))
    for {
      _ <- Entity.i.offsetTime.insert(a, b).transact

      // 1 attribute
      _ <- Entity.offsetTime(min).query.get.map(_ ==> List(offsetTime1))

      _ <- Entity.offsetTime(min)(offsetTime1).query.get.map(_ ==> List(offsetTime1))
      _ <- Entity.offsetTime(min)(offsetTime2).query.get.map(_ ==> List())

      _ <- Entity.offsetTime(min).not(offsetTime1).query.get.map(_ ==> List())
      _ <- Entity.offsetTime(min).not(offsetTime2).query.get.map(_ ==> List(offsetTime1))

      _ <- Entity.offsetTime(min).<(offsetTime1).query.get.map(_ ==> List())
      _ <- Entity.offsetTime(min).<(offsetTime2).query.get.map(_ ==> List(offsetTime1))

      _ <- Entity.offsetTime(min).<=(offsetTime0).query.get.map(_ ==> List())
      _ <- Entity.offsetTime(min).<=(offsetTime1).query.get.map(_ ==> List(offsetTime1))

      _ <- Entity.offsetTime(min).>(offsetTime1).query.get.map(_ ==> List())
      _ <- Entity.offsetTime(min).>(offsetTime0).query.get.map(_ ==> List(offsetTime1))

      _ <- Entity.offsetTime(min).>=(offsetTime1).query.get.map(_ ==> List(offsetTime1))
      _ <- Entity.offsetTime(min).>=(offsetTime2).query.get.map(_ ==> List())


      // n attributes
      _ <- Entity.i.offsetTime(min).query.get.map(_ ==> List(a))

      _ <- Entity.i.offsetTime(min)(offsetTime1).query.get.map(_ ==> List(a))
      _ <- Entity.i.offsetTime(min)(offsetTime2).query.get.map(_ ==> List())

      _ <- Entity.i.offsetTime(min).not(offsetTime1).query.get.map(_ ==> List())
      _ <- Entity.i.offsetTime(min).not(offsetTime2).query.get.map(_ ==> List(a))

      _ <- Entity.i.offsetTime(min).<(offsetTime1).query.get.map(_ ==> List())
      _ <- Entity.i.offsetTime(min).<(offsetTime2).query.get.map(_ ==> List(a))

      _ <- Entity.i.offsetTime(min).<=(offsetTime0).query.get.map(_ ==> List())
      _ <- Entity.i.offsetTime(min).<=(offsetTime1).query.get.map(_ ==> List(a))

      _ <- Entity.i.offsetTime(min).>(offsetTime1).query.get.map(_ ==> List())
      _ <- Entity.i.offsetTime(min).>(offsetTime0).query.get.map(_ ==> List(a))

      _ <- Entity.i.offsetTime(min).>=(offsetTime1).query.get.map(_ ==> List(a))
      _ <- Entity.i.offsetTime(min).>=(offsetTime2).query.get.map(_ ==> List())
    } yield ()
  }


  "max" - types {
    val (a, b) = ((1, offsetTime1), (1, offsetTime2))
    for {
      _ <- Entity.i.offsetTime.insert(a, b).transact

      // 1 attribute
      _ <- Entity.offsetTime(max).query.get.map(_ ==> List(offsetTime2))

      _ <- Entity.offsetTime(max)(offsetTime2).query.get.map(_ ==> List(offsetTime2))
      _ <- Entity.offsetTime(max)(offsetTime1).query.get.map(_ ==> List())

      _ <- Entity.offsetTime(max).not(offsetTime2).query.get.map(_ ==> List())
      _ <- Entity.offsetTime(max).not(offsetTime1).query.get.map(_ ==> List(offsetTime2))

      _ <- Entity.offsetTime(max).<(offsetTime2).query.get.map(_ ==> List())
      _ <- Entity.offsetTime(max).<(offsetTime3).query.get.map(_ ==> List(offsetTime2))

      _ <- Entity.offsetTime(max).<=(offsetTime1).query.get.map(_ ==> List())
      _ <- Entity.offsetTime(max).<=(offsetTime2).query.get.map(_ ==> List(offsetTime2))

      _ <- Entity.offsetTime(max).>(offsetTime2).query.get.map(_ ==> List())
      _ <- Entity.offsetTime(max).>(offsetTime1).query.get.map(_ ==> List(offsetTime2))

      _ <- Entity.offsetTime(max).>=(offsetTime2).query.get.map(_ ==> List(offsetTime2))
      _ <- Entity.offsetTime(max).>=(offsetTime3).query.get.map(_ ==> List())


      // n attributes
      _ <- Entity.i.offsetTime(max).query.get.map(_ ==> List(b))

      _ <- Entity.i.offsetTime(max)(offsetTime2).query.get.map(_ ==> List(b))
      _ <- Entity.i.offsetTime(max)(offsetTime1).query.get.map(_ ==> List())

      _ <- Entity.i.offsetTime(max).not(offsetTime2).query.get.map(_ ==> List())
      _ <- Entity.i.offsetTime(max).not(offsetTime1).query.get.map(_ ==> List(b))

      _ <- Entity.i.offsetTime(max).<(offsetTime2).query.get.map(_ ==> List())
      _ <- Entity.i.offsetTime(max).<(offsetTime3).query.get.map(_ ==> List(b))

      _ <- Entity.i.offsetTime(max).<=(offsetTime1).query.get.map(_ ==> List())
      _ <- Entity.i.offsetTime(max).<=(offsetTime2).query.get.map(_ ==> List(b))

      _ <- Entity.i.offsetTime(max).>(offsetTime2).query.get.map(_ ==> List())
      _ <- Entity.i.offsetTime(max).>(offsetTime1).query.get.map(_ ==> List(b))

      _ <- Entity.i.offsetTime(max).>=(offsetTime2).query.get.map(_ ==> List(b))
      _ <- Entity.i.offsetTime(max).>=(offsetTime3).query.get.map(_ ==> List())
    } yield ()
  }


  "min/max" - types {
    for {
      _ <- Entity.i.offsetTime.insert(
        (1, offsetTime1),
        (1, offsetTime2),
        (2, offsetTime3),
        (2, offsetTime4),
      ).transact

      _ <- Entity.offsetTime(min).offsetTime(max).query.get.map(_ ==> List((offsetTime1, offsetTime4)))

      _ <- Entity.offsetTime(min)(offsetTime1).offsetTime(max)(offsetTime4).query.get.map(_ ==> List((offsetTime1, offsetTime4)))
      _ <- Entity.offsetTime(min)(offsetTime1).offsetTime(max)(offsetTime5).query.get.map(_ ==> List())

      _ <- Entity.offsetTime(min).not(offsetTime2).offsetTime(max).not(offsetTime3).query.get.map(_ ==> List((offsetTime1, offsetTime4)))
      _ <- Entity.offsetTime(min).not(offsetTime2).offsetTime(max).not(offsetTime4).query.get.map(_ ==> List())

      _ <- Entity.offsetTime(min).<(offsetTime2).offsetTime(max).>(offsetTime3).query.get.map(_ ==> List((offsetTime1, offsetTime4)))
      _ <- Entity.offsetTime(min).<(offsetTime2).offsetTime(max).>(offsetTime4).query.get.map(_ ==> List())

      _ <- Entity.offsetTime(min).<=(offsetTime1).offsetTime(max).>=(offsetTime4).query.get.map(_ ==> List((offsetTime1, offsetTime4)))
      _ <- Entity.offsetTime(min).<=(offsetTime1).offsetTime(max).>=(offsetTime5).query.get.map(_ ==> List())
    } yield ()
  }


  "min/max n" - types {
    for {
      _ <- Entity.i.offsetTime.insert(
        (1, offsetTime1),
        (1, offsetTime2),
        (1, offsetTime3),
        (2, offsetTime4),
        (2, offsetTime5),
        (2, offsetTime6),
        (2, offsetTime6), // (make sure grouped values coalesce)
      ).transact

      _ <- Entity.offsetTime(min(1)).query.get.map(_ ==> List(Set(offsetTime1)))
      _ <- Entity.offsetTime(min(2)).query.get.map(_ ==> List(Set(offsetTime1, offsetTime2)))

      _ <- Entity.offsetTime(max(1)).query.get.map(_ ==> List(Set(offsetTime6)))
      _ <- Entity.offsetTime(max(2)).query.get.map(_ ==> List(Set(offsetTime5, offsetTime6)))

      _ <- Entity.i.a1.offsetTime(min(2)).query.get.map(_ ==> List(
        (1, Set(offsetTime1, offsetTime2)),
        (2, Set(offsetTime4, offsetTime5))
      ))

      _ <- Entity.i.a1.offsetTime(max(2)).query.get.map(_ ==> List(
        (1, Set(offsetTime2, offsetTime3)),
        (2, Set(offsetTime5, offsetTime6))
      ))

      _ <- Entity.i.a1.offsetTime(min(2)).offsetTime(max(2)).query.get.map(_ ==> List(
        (1, Set(offsetTime1, offsetTime2), Set(offsetTime2, offsetTime3)),
        (2, Set(offsetTime4, offsetTime5), Set(offsetTime5, offsetTime6))
      ))
    } yield ()
  }


  "sample" - types {
    val all       = Set(offsetTime1, offsetTime2, offsetTime3)
    val (a, b, c) = ((1, offsetTime1), (2, offsetTime2), (3, offsetTime3))
    val allPairs  = List(a, b, c)
    for {
      _ <- Entity.i.offsetTime.insert(allPairs).transact

      // 1 attribute
      _ <- Entity.offsetTime(sample).query.get.map(res => all.contains(res.head) ==> true)

      // Checking for equality on a sample doesn't make sense
      // _ <- Entity.offsetTime(sample)(offsetTime2).query.get.map(res => all.contains(res.head) ==> true)
      // If you want a specific value, this would be the natural query
      _ <- Entity.offsetTime(offsetTime2).query.get.map(_ ==> List(offsetTime2))

      _ <- Entity.offsetTime(sample).not(offsetTime2).query.get.map { res =>
        List(offsetTime1, offsetTime3).contains(res.head) ==> true
        (res.head == offsetTime2) ==> false
      }
      _ <- Entity.offsetTime(sample).<(offsetTime3).query.get.map { res =>
        List(offsetTime1, offsetTime2).contains(res.head) ==> true
        (res.head == offsetTime3) ==> false
      }
      _ <- Entity.offsetTime(sample).<=(offsetTime2).query.get.map { res =>
        List(offsetTime1, offsetTime2).contains(res.head) ==> true
        (res.head == offsetTime3) ==> false
      }
      _ <- Entity.offsetTime(sample).>(offsetTime1).query.get.map { res =>
        List(offsetTime2, offsetTime3).contains(res.head) ==> true
        (res.head == offsetTime1) ==> false
      }
      _ <- Entity.offsetTime(sample).>=(offsetTime2).query.get.map { res =>
        List(offsetTime2, offsetTime3).contains(res.head) ==> true
        (res.head == offsetTime1) ==> false
      }

      // 1 attribute
      _ <- Entity.i.offsetTime(sample).query.get.map(res => allPairs.contains(res.head) ==> true)

      _ <- Entity.i.offsetTime(sample).not(offsetTime2).query.get.map { res =>
        List(a, c).contains(res.head) ==> true
        (res.head == b) ==> false
      }
      _ <- Entity.i.offsetTime(sample).<(offsetTime3).query.get.map { res =>
        List(a, b).contains(res.head) ==> true
        (res.head == c) ==> false
      }
      _ <- Entity.i.offsetTime(sample).<=(offsetTime2).query.get.map { res =>
        List(a, b).contains(res.head) ==> true
        (res.head == c) ==> false
      }
      _ <- Entity.i.offsetTime(sample).>(offsetTime1).query.get.map { res =>
        List(b, c).contains(res.head) ==> true
        (res.head == a) ==> false
      }
      _ <- Entity.i.offsetTime(sample).>=(offsetTime2).query.get.map { res =>
        List(b, c).contains(res.head) ==> true
        (res.head == a) ==> false
      }
    } yield ()
  }


  "samples(n)" - types {
    val all = Set(offsetTime1, offsetTime2, offsetTime3)
    for {
      _ <- Entity.offsetTime.insert(List(offsetTime1, offsetTime2, offsetTime3)).transact
      _ <- Entity.offsetTime(sample(1)).query.get.map(res => all.intersect(res.head).nonEmpty ==> true)
      _ <- Entity.offsetTime(sample(2)).query.get.map(res => all.intersect(res.head).nonEmpty ==> true)
    } yield ()
  }


  "count" - types {
    val (a, b) = ((1, 1), (2, 3))
    for {
      _ <- Entity.i.offsetTime.insert(List(
        (1, offsetTime1),
        (2, offsetTime2),
        (2, offsetTime2),
        (2, offsetTime3),
      )).transact

      // 1 attribute
      _ <- Entity.offsetTime(count).query.get.map(_ ==> List(4))

      _ <- Entity.offsetTime(count)(3).query.get.map(_ ==> List())
      _ <- Entity.offsetTime(count)(4).query.get.map(_ ==> List(4))

      _ <- Entity.offsetTime(count).not(3).query.get.map(_ ==> List(4))
      _ <- Entity.offsetTime(count).not(4).query.get.map(_ ==> List())

      _ <- Entity.offsetTime(count).<(5).query.get.map(_ ==> List(4))
      _ <- Entity.offsetTime(count).<(4).query.get.map(_ ==> List())

      _ <- Entity.offsetTime(count).<=(4).query.get.map(_ ==> List(4))
      _ <- Entity.offsetTime(count).<=(3).query.get.map(_ ==> List())

      _ <- Entity.offsetTime(count).>(3).query.get.map(_ ==> List(4))
      _ <- Entity.offsetTime(count).>(4).query.get.map(_ ==> List())

      _ <- Entity.offsetTime(count).>=(4).query.get.map(_ ==> List(4))
      _ <- Entity.offsetTime(count).>=(5).query.get.map(_ ==> List())


      // n attributes
      _ <- Entity.i.a1.offsetTime(count).query.get.map(_ ==> List(a, b))

      _ <- Entity.i.a1.offsetTime(count)(3).query.get.map(_ ==> List(b))
      _ <- Entity.i.a1.offsetTime(count)(2).query.get.map(_ ==> List())

      _ <- Entity.i.a1.offsetTime(count).not(3).query.get.map(_ ==> List(a))
      _ <- Entity.i.a1.offsetTime(count).not(2).query.get.map(_ ==> List(a, b))

      _ <- Entity.i.a1.offsetTime(count).<(3).query.get.map(_ ==> List(a))
      _ <- Entity.i.a1.offsetTime(count).<(4).query.get.map(_ ==> List(a, b))

      _ <- Entity.i.a1.offsetTime(count).<=(3).query.get.map(_ ==> List(a, b))
      _ <- Entity.i.a1.offsetTime(count).<=(2).query.get.map(_ ==> List(a))

      _ <- Entity.i.a1.offsetTime(count).>(2).query.get.map(_ ==> List(b))
      _ <- Entity.i.a1.offsetTime(count).>(3).query.get.map(_ ==> List())

      _ <- Entity.i.a1.offsetTime(count).>=(3).query.get.map(_ ==> List(b))
      _ <- Entity.i.a1.offsetTime(count).>=(4).query.get.map(_ ==> List())
    } yield ()
  }


  "countDistinct" - types {
    val (a, b) = ((1, 1), (2, 2))
    for {
      _ <- Entity.i.offsetTime.insert(List(
        (1, offsetTime1),
        (2, offsetTime2),
        (2, offsetTime2),
        (2, offsetTime3),
      )).transact

      // 1 attribute
      _ <- Entity.offsetTime(countDistinct).query.get.map(_ ==> List(3))

      _ <- Entity.offsetTime(countDistinct)(2).query.get.map(_ ==> List())
      _ <- Entity.offsetTime(countDistinct)(3).query.get.map(_ ==> List(3))

      _ <- Entity.offsetTime(countDistinct).not(2).query.get.map(_ ==> List(3))
      _ <- Entity.offsetTime(countDistinct).not(3).query.get.map(_ ==> List())

      _ <- Entity.offsetTime(countDistinct).<(4).query.get.map(_ ==> List(3))
      _ <- Entity.offsetTime(countDistinct).<(3).query.get.map(_ ==> List())

      _ <- Entity.offsetTime(countDistinct).<=(3).query.get.map(_ ==> List(3))
      _ <- Entity.offsetTime(countDistinct).<=(2).query.get.map(_ ==> List())

      _ <- Entity.offsetTime(countDistinct).>(2).query.get.map(_ ==> List(3))
      _ <- Entity.offsetTime(countDistinct).>(3).query.get.map(_ ==> List())

      _ <- Entity.offsetTime(countDistinct).>=(3).query.get.map(_ ==> List(3))
      _ <- Entity.offsetTime(countDistinct).>=(4).query.get.map(_ ==> List())


      // n attributes
      _ <- Entity.i.a1.offsetTime(countDistinct).query.get.map(_ ==> List(a, b))

      _ <- Entity.i.a1.offsetTime(countDistinct)(2).query.get.map(_ ==> List(b))
      _ <- Entity.i.a1.offsetTime(countDistinct)(3).query.get.map(_ ==> List())

      _ <- Entity.i.a1.offsetTime(countDistinct).not(2).query.get.map(_ ==> List(a))
      _ <- Entity.i.a1.offsetTime(countDistinct).not(3).query.get.map(_ ==> List(a, b))

      _ <- Entity.i.a1.offsetTime(countDistinct).<(2).query.get.map(_ ==> List(a))
      _ <- Entity.i.a1.offsetTime(countDistinct).<(3).query.get.map(_ ==> List(a, b))

      _ <- Entity.i.a1.offsetTime(countDistinct).<=(2).query.get.map(_ ==> List(a, b))
      _ <- Entity.i.a1.offsetTime(countDistinct).<=(1).query.get.map(_ ==> List(a))

      _ <- Entity.i.a1.offsetTime(countDistinct).>(1).query.get.map(_ ==> List(b))
      _ <- Entity.i.a1.offsetTime(countDistinct).>(2).query.get.map(_ ==> List())

      _ <- Entity.i.a1.offsetTime(countDistinct).>=(2).query.get.map(_ ==> List(b))
      _ <- Entity.i.a1.offsetTime(countDistinct).>=(3).query.get.map(_ ==> List())
    } yield ()
  }
}