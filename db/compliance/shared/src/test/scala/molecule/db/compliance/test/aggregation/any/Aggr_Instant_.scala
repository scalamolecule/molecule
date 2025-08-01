// GENERATED CODE ********************************
package molecule.db.compliance.test.aggregation.any

import java.time.Instant
import molecule.core.setup.{MUnit, TestUtils}
import molecule.db.compliance.domains.dsl.Types.*
import molecule.db.compliance.setup.DbProviders
import molecule.db.common.api.Api_async
import molecule.db.common.spi.Spi_async
import molecule.db.common.util.Executor.*
import org.scalactic.Equality
import org.scalactic.Equality

case class Aggr_Instant_(
  suite: MUnit,
  api: Api_async & Spi_async & DbProviders
) extends TestUtils {

  import api.*
  import suite.*

  "distinct" - types {
    for {
      _ <- Entity.i.instant.insert(List(
        (1, instant1),
        (2, instant2),
        (2, instant2),
        (2, instant3),
      )).transact

      _ <- Entity.i.instant.a1.query.get.map(_ ==> List(
        (1, instant1),
        (2, instant2), // 2 rows coalesced
        (2, instant3),
      ))

      // Distinct values are returned in a Set
      _ <- Entity.i.a1.instant(distinct).query.get.map(_ ==> List(
        (1, Set(instant1)),
        (2, Set(instant2, instant3)),
      ))

      _ <- Entity.instant(distinct).query.get.map(_.head ==> Set(
        instant1, instant2, instant3
      ))
    } yield ()
  }


  "min" - types {
    val (a, b) = ((1, instant1), (1, instant2))
    for {
      _ <- Entity.i.instant.insert(a, b).transact

      // 1 attribute
      _ <- Entity.instant(min).query.get.map(_ ==> List(instant1))

      _ <- Entity.instant(min)(instant1).query.get.map(_ ==> List(instant1))
      _ <- Entity.instant(min)(instant2).query.get.map(_ ==> List())

      _ <- Entity.instant(min).not(instant1).query.get.map(_ ==> List())
      _ <- Entity.instant(min).not(instant2).query.get.map(_ ==> List(instant1))

      _ <- Entity.instant(min).<(instant1).query.get.map(_ ==> List())
      _ <- Entity.instant(min).<(instant2).query.get.map(_ ==> List(instant1))

      _ <- Entity.instant(min).<=(instant0).query.get.map(_ ==> List())
      _ <- Entity.instant(min).<=(instant1).query.get.map(_ ==> List(instant1))

      _ <- Entity.instant(min).>(instant1).query.get.map(_ ==> List())
      _ <- Entity.instant(min).>(instant0).query.get.map(_ ==> List(instant1))

      _ <- Entity.instant(min).>=(instant1).query.get.map(_ ==> List(instant1))
      _ <- Entity.instant(min).>=(instant2).query.get.map(_ ==> List())


      // n attributes
      _ <- Entity.i.instant(min).query.get.map(_ ==> List(a))

      _ <- Entity.i.instant(min)(instant1).query.get.map(_ ==> List(a))
      _ <- Entity.i.instant(min)(instant2).query.get.map(_ ==> List())

      _ <- Entity.i.instant(min).not(instant1).query.get.map(_ ==> List())
      _ <- Entity.i.instant(min).not(instant2).query.get.map(_ ==> List(a))

      _ <- Entity.i.instant(min).<(instant1).query.get.map(_ ==> List())
      _ <- Entity.i.instant(min).<(instant2).query.get.map(_ ==> List(a))

      _ <- Entity.i.instant(min).<=(instant0).query.get.map(_ ==> List())
      _ <- Entity.i.instant(min).<=(instant1).query.get.map(_ ==> List(a))

      _ <- Entity.i.instant(min).>(instant1).query.get.map(_ ==> List())
      _ <- Entity.i.instant(min).>(instant0).query.get.map(_ ==> List(a))

      _ <- Entity.i.instant(min).>=(instant1).query.get.map(_ ==> List(a))
      _ <- Entity.i.instant(min).>=(instant2).query.get.map(_ ==> List())
    } yield ()
  }


  "max" - types {
    val (a, b) = ((1, instant1), (1, instant2))
    for {
      _ <- Entity.i.instant.insert(a, b).transact

      // 1 attribute
      _ <- Entity.instant(max).query.get.map(_ ==> List(instant2))

      _ <- Entity.instant(max)(instant2).query.get.map(_ ==> List(instant2))
      _ <- Entity.instant(max)(instant1).query.get.map(_ ==> List())

      _ <- Entity.instant(max).not(instant2).query.get.map(_ ==> List())
      _ <- Entity.instant(max).not(instant1).query.get.map(_ ==> List(instant2))

      _ <- Entity.instant(max).<(instant2).query.get.map(_ ==> List())
      _ <- Entity.instant(max).<(instant3).query.get.map(_ ==> List(instant2))

      _ <- Entity.instant(max).<=(instant1).query.get.map(_ ==> List())
      _ <- Entity.instant(max).<=(instant2).query.get.map(_ ==> List(instant2))

      _ <- Entity.instant(max).>(instant2).query.get.map(_ ==> List())
      _ <- Entity.instant(max).>(instant1).query.get.map(_ ==> List(instant2))

      _ <- Entity.instant(max).>=(instant2).query.get.map(_ ==> List(instant2))
      _ <- Entity.instant(max).>=(instant3).query.get.map(_ ==> List())


      // n attributes
      _ <- Entity.i.instant(max).query.get.map(_ ==> List(b))

      _ <- Entity.i.instant(max)(instant2).query.get.map(_ ==> List(b))
      _ <- Entity.i.instant(max)(instant1).query.get.map(_ ==> List())

      _ <- Entity.i.instant(max).not(instant2).query.get.map(_ ==> List())
      _ <- Entity.i.instant(max).not(instant1).query.get.map(_ ==> List(b))

      _ <- Entity.i.instant(max).<(instant2).query.get.map(_ ==> List())
      _ <- Entity.i.instant(max).<(instant3).query.get.map(_ ==> List(b))

      _ <- Entity.i.instant(max).<=(instant1).query.get.map(_ ==> List())
      _ <- Entity.i.instant(max).<=(instant2).query.get.map(_ ==> List(b))

      _ <- Entity.i.instant(max).>(instant2).query.get.map(_ ==> List())
      _ <- Entity.i.instant(max).>(instant1).query.get.map(_ ==> List(b))

      _ <- Entity.i.instant(max).>=(instant2).query.get.map(_ ==> List(b))
      _ <- Entity.i.instant(max).>=(instant3).query.get.map(_ ==> List())
    } yield ()
  }


  "min/max" - types {
    for {
      _ <- Entity.i.instant.insert(
        (1, instant1),
        (1, instant2),
        (2, instant3),
        (2, instant4),
      ).transact

      _ <- Entity.instant(min).instant(max).query.get.map(_ ==> List((instant1, instant4)))

      _ <- Entity.instant(min)(instant1).instant(max)(instant4).query.get.map(_ ==> List((instant1, instant4)))
      _ <- Entity.instant(min)(instant1).instant(max)(instant5).query.get.map(_ ==> List())

      _ <- Entity.instant(min).not(instant2).instant(max).not(instant3).query.get.map(_ ==> List((instant1, instant4)))
      _ <- Entity.instant(min).not(instant2).instant(max).not(instant4).query.get.map(_ ==> List())

      _ <- Entity.instant(min).<(instant2).instant(max).>(instant3).query.get.map(_ ==> List((instant1, instant4)))
      _ <- Entity.instant(min).<(instant2).instant(max).>(instant4).query.get.map(_ ==> List())

      _ <- Entity.instant(min).<=(instant1).instant(max).>=(instant4).query.get.map(_ ==> List((instant1, instant4)))
      _ <- Entity.instant(min).<=(instant1).instant(max).>=(instant5).query.get.map(_ ==> List())
    } yield ()
  }


  "min/max n" - types {
    for {
      _ <- Entity.i.instant.insert(
        (1, instant1),
        (1, instant2),
        (1, instant3),
        (2, instant4),
        (2, instant5),
        (2, instant6),
        (2, instant6), // (make sure grouped values coalesce)
      ).transact

      _ <- Entity.instant(min(1)).query.get.map(_ ==> List(Set(instant1)))
      _ <- Entity.instant(min(2)).query.get.map(_ ==> List(Set(instant1, instant2)))

      _ <- Entity.instant(max(1)).query.get.map(_ ==> List(Set(instant6)))
      _ <- Entity.instant(max(2)).query.get.map(_ ==> List(Set(instant5, instant6)))

      _ <- Entity.i.a1.instant(min(2)).query.get.map(_ ==> List(
        (1, Set(instant1, instant2)),
        (2, Set(instant4, instant5))
      ))

      _ <- Entity.i.a1.instant(max(2)).query.get.map(_ ==> List(
        (1, Set(instant2, instant3)),
        (2, Set(instant5, instant6))
      ))

      _ <- Entity.i.a1.instant(min(2)).instant(max(2)).query.get.map(_ ==> List(
        (1, Set(instant1, instant2), Set(instant2, instant3)),
        (2, Set(instant4, instant5), Set(instant5, instant6))
      ))
    } yield ()
  }


  "sample" - types {
    val all       = Set(instant1, instant2, instant3)
    val (a, b, c) = ((1, instant1), (2, instant2), (3, instant3))
    val allPairs  = List(a, b, c)
    for {
      _ <- Entity.i.instant.insert(allPairs).transact

      // 1 attribute
      _ <- Entity.instant(sample).query.get.map(res => all.contains(res.head) ==> true)

      // Checking for equality on a sample doesn't make sense
      // _ <- Entity.instant(sample)(instant2).query.get.map(res => all.contains(res.head) ==> true)
      // If you want a specific value, this would be the natural query
      _ <- Entity.instant(instant2).query.get.map(_ ==> List(instant2))

      _ <- Entity.instant(sample).not(instant2).query.get.map { res =>
        List(instant1, instant3).contains(res.head) ==> true
        (res.head == instant2) ==> false
      }
      _ <- Entity.instant(sample).<(instant3).query.get.map { res =>
        List(instant1, instant2).contains(res.head) ==> true
        (res.head == instant3) ==> false
      }
      _ <- Entity.instant(sample).<=(instant2).query.get.map { res =>
        List(instant1, instant2).contains(res.head) ==> true
        (res.head == instant3) ==> false
      }
      _ <- Entity.instant(sample).>(instant1).query.get.map { res =>
        List(instant2, instant3).contains(res.head) ==> true
        (res.head == instant1) ==> false
      }
      _ <- Entity.instant(sample).>=(instant2).query.get.map { res =>
        List(instant2, instant3).contains(res.head) ==> true
        (res.head == instant1) ==> false
      }

      // 1 attribute
      _ <- Entity.i.instant(sample).query.get.map(res => allPairs.contains(res.head) ==> true)

      _ <- Entity.i.instant(sample).not(instant2).query.get.map { res =>
        List(a, c).contains(res.head) ==> true
        (res.head == b) ==> false
      }
      _ <- Entity.i.instant(sample).<(instant3).query.get.map { res =>
        List(a, b).contains(res.head) ==> true
        (res.head == c) ==> false
      }
      _ <- Entity.i.instant(sample).<=(instant2).query.get.map { res =>
        List(a, b).contains(res.head) ==> true
        (res.head == c) ==> false
      }
      _ <- Entity.i.instant(sample).>(instant1).query.get.map { res =>
        List(b, c).contains(res.head) ==> true
        (res.head == a) ==> false
      }
      _ <- Entity.i.instant(sample).>=(instant2).query.get.map { res =>
        List(b, c).contains(res.head) ==> true
        (res.head == a) ==> false
      }
    } yield ()
  }


  "samples(n)" - types {
    val all = Set(instant1, instant2, instant3)
    for {
      _ <- Entity.instant.insert(List(instant1, instant2, instant3)).transact
      _ <- Entity.instant(sample(1)).query.get.map(res => all.intersect(res.head).nonEmpty ==> true)
      _ <- Entity.instant(sample(2)).query.get.map(res => all.intersect(res.head).nonEmpty ==> true)
    } yield ()
  }


  "count" - types {
    val (a, b) = ((1, 1), (2, 3))
    for {
      _ <- Entity.i.instant.insert(List(
        (1, instant1),
        (2, instant2),
        (2, instant2),
        (2, instant3),
      )).transact

      // 1 attribute
      _ <- Entity.instant(count).query.get.map(_ ==> List(4))

      _ <- Entity.instant(count)(3).query.get.map(_ ==> List())
      _ <- Entity.instant(count)(4).query.get.map(_ ==> List(4))

      _ <- Entity.instant(count).not(3).query.get.map(_ ==> List(4))
      _ <- Entity.instant(count).not(4).query.get.map(_ ==> List())

      _ <- Entity.instant(count).<(5).query.get.map(_ ==> List(4))
      _ <- Entity.instant(count).<(4).query.get.map(_ ==> List())

      _ <- Entity.instant(count).<=(4).query.get.map(_ ==> List(4))
      _ <- Entity.instant(count).<=(3).query.get.map(_ ==> List())

      _ <- Entity.instant(count).>(3).query.get.map(_ ==> List(4))
      _ <- Entity.instant(count).>(4).query.get.map(_ ==> List())

      _ <- Entity.instant(count).>=(4).query.get.map(_ ==> List(4))
      _ <- Entity.instant(count).>=(5).query.get.map(_ ==> List())


      // n attributes
      _ <- Entity.i.a1.instant(count).query.get.map(_ ==> List(a, b))

      _ <- Entity.i.a1.instant(count)(3).query.get.map(_ ==> List(b))
      _ <- Entity.i.a1.instant(count)(2).query.get.map(_ ==> List())

      _ <- Entity.i.a1.instant(count).not(3).query.get.map(_ ==> List(a))
      _ <- Entity.i.a1.instant(count).not(2).query.get.map(_ ==> List(a, b))

      _ <- Entity.i.a1.instant(count).<(3).query.get.map(_ ==> List(a))
      _ <- Entity.i.a1.instant(count).<(4).query.get.map(_ ==> List(a, b))

      _ <- Entity.i.a1.instant(count).<=(3).query.get.map(_ ==> List(a, b))
      _ <- Entity.i.a1.instant(count).<=(2).query.get.map(_ ==> List(a))

      _ <- Entity.i.a1.instant(count).>(2).query.get.map(_ ==> List(b))
      _ <- Entity.i.a1.instant(count).>(3).query.get.map(_ ==> List())

      _ <- Entity.i.a1.instant(count).>=(3).query.get.map(_ ==> List(b))
      _ <- Entity.i.a1.instant(count).>=(4).query.get.map(_ ==> List())
    } yield ()
  }


  "countDistinct" - types {
    val (a, b) = ((1, 1), (2, 2))
    for {
      _ <- Entity.i.instant.insert(List(
        (1, instant1),
        (2, instant2),
        (2, instant2),
        (2, instant3),
      )).transact

      // 1 attribute
      _ <- Entity.instant(countDistinct).query.get.map(_ ==> List(3))

      _ <- Entity.instant(countDistinct)(2).query.get.map(_ ==> List())
      _ <- Entity.instant(countDistinct)(3).query.get.map(_ ==> List(3))

      _ <- Entity.instant(countDistinct).not(2).query.get.map(_ ==> List(3))
      _ <- Entity.instant(countDistinct).not(3).query.get.map(_ ==> List())

      _ <- Entity.instant(countDistinct).<(4).query.get.map(_ ==> List(3))
      _ <- Entity.instant(countDistinct).<(3).query.get.map(_ ==> List())

      _ <- Entity.instant(countDistinct).<=(3).query.get.map(_ ==> List(3))
      _ <- Entity.instant(countDistinct).<=(2).query.get.map(_ ==> List())

      _ <- Entity.instant(countDistinct).>(2).query.get.map(_ ==> List(3))
      _ <- Entity.instant(countDistinct).>(3).query.get.map(_ ==> List())

      _ <- Entity.instant(countDistinct).>=(3).query.get.map(_ ==> List(3))
      _ <- Entity.instant(countDistinct).>=(4).query.get.map(_ ==> List())


      // n attributes
      _ <- Entity.i.a1.instant(countDistinct).query.get.map(_ ==> List(a, b))

      _ <- Entity.i.a1.instant(countDistinct)(2).query.get.map(_ ==> List(b))
      _ <- Entity.i.a1.instant(countDistinct)(3).query.get.map(_ ==> List())

      _ <- Entity.i.a1.instant(countDistinct).not(2).query.get.map(_ ==> List(a))
      _ <- Entity.i.a1.instant(countDistinct).not(3).query.get.map(_ ==> List(a, b))

      _ <- Entity.i.a1.instant(countDistinct).<(2).query.get.map(_ ==> List(a))
      _ <- Entity.i.a1.instant(countDistinct).<(3).query.get.map(_ ==> List(a, b))

      _ <- Entity.i.a1.instant(countDistinct).<=(2).query.get.map(_ ==> List(a, b))
      _ <- Entity.i.a1.instant(countDistinct).<=(1).query.get.map(_ ==> List(a))

      _ <- Entity.i.a1.instant(countDistinct).>(1).query.get.map(_ ==> List(b))
      _ <- Entity.i.a1.instant(countDistinct).>(2).query.get.map(_ ==> List())

      _ <- Entity.i.a1.instant(countDistinct).>=(2).query.get.map(_ ==> List(b))
      _ <- Entity.i.a1.instant(countDistinct).>=(3).query.get.map(_ ==> List())
    } yield ()
  }
}