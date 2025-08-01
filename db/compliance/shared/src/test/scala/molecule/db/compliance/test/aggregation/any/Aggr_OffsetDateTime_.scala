// GENERATED CODE ********************************
package molecule.db.compliance.test.aggregation.any

import java.time.OffsetDateTime
import molecule.core.setup.{MUnit, TestUtils}
import molecule.db.compliance.domains.dsl.Types.*
import molecule.db.compliance.setup.DbProviders
import molecule.db.common.api.Api_async
import molecule.db.common.spi.Spi_async
import molecule.db.common.util.Executor.*
import org.scalactic.Equality
import org.scalactic.Equality

case class Aggr_OffsetDateTime_(
  suite: MUnit,
  api: Api_async & Spi_async & DbProviders
) extends TestUtils {

  import api.*
  import suite.*

  "distinct" - types {
    for {
      _ <- Entity.i.offsetDateTime.insert(List(
        (1, offsetDateTime1),
        (2, offsetDateTime2),
        (2, offsetDateTime2),
        (2, offsetDateTime3),
      )).transact

      _ <- Entity.i.offsetDateTime.a1.query.get.map(_ ==> List(
        (1, offsetDateTime1),
        (2, offsetDateTime2), // 2 rows coalesced
        (2, offsetDateTime3),
      ))

      // Distinct values are returned in a Set
      _ <- Entity.i.a1.offsetDateTime(distinct).query.get.map(_ ==> List(
        (1, Set(offsetDateTime1)),
        (2, Set(offsetDateTime2, offsetDateTime3)),
      ))

      _ <- Entity.offsetDateTime(distinct).query.get.map(_.head ==> Set(
        offsetDateTime1, offsetDateTime2, offsetDateTime3
      ))
    } yield ()
  }


  "min" - types {
    val (a, b) = ((1, offsetDateTime1), (1, offsetDateTime2))
    for {
      _ <- Entity.i.offsetDateTime.insert(a, b).transact

      // 1 attribute
      _ <- Entity.offsetDateTime(min).query.get.map(_ ==> List(offsetDateTime1))

      _ <- Entity.offsetDateTime(min)(offsetDateTime1).query.get.map(_ ==> List(offsetDateTime1))
      _ <- Entity.offsetDateTime(min)(offsetDateTime2).query.get.map(_ ==> List())

      _ <- Entity.offsetDateTime(min).not(offsetDateTime1).query.get.map(_ ==> List())
      _ <- Entity.offsetDateTime(min).not(offsetDateTime2).query.get.map(_ ==> List(offsetDateTime1))

      _ <- Entity.offsetDateTime(min).<(offsetDateTime1).query.get.map(_ ==> List())
      _ <- Entity.offsetDateTime(min).<(offsetDateTime2).query.get.map(_ ==> List(offsetDateTime1))

      _ <- Entity.offsetDateTime(min).<=(offsetDateTime0).query.get.map(_ ==> List())
      _ <- Entity.offsetDateTime(min).<=(offsetDateTime1).query.get.map(_ ==> List(offsetDateTime1))

      _ <- Entity.offsetDateTime(min).>(offsetDateTime1).query.get.map(_ ==> List())
      _ <- Entity.offsetDateTime(min).>(offsetDateTime0).query.get.map(_ ==> List(offsetDateTime1))

      _ <- Entity.offsetDateTime(min).>=(offsetDateTime1).query.get.map(_ ==> List(offsetDateTime1))
      _ <- Entity.offsetDateTime(min).>=(offsetDateTime2).query.get.map(_ ==> List())


      // n attributes
      _ <- Entity.i.offsetDateTime(min).query.get.map(_ ==> List(a))

      _ <- Entity.i.offsetDateTime(min)(offsetDateTime1).query.get.map(_ ==> List(a))
      _ <- Entity.i.offsetDateTime(min)(offsetDateTime2).query.get.map(_ ==> List())

      _ <- Entity.i.offsetDateTime(min).not(offsetDateTime1).query.get.map(_ ==> List())
      _ <- Entity.i.offsetDateTime(min).not(offsetDateTime2).query.get.map(_ ==> List(a))

      _ <- Entity.i.offsetDateTime(min).<(offsetDateTime1).query.get.map(_ ==> List())
      _ <- Entity.i.offsetDateTime(min).<(offsetDateTime2).query.get.map(_ ==> List(a))

      _ <- Entity.i.offsetDateTime(min).<=(offsetDateTime0).query.get.map(_ ==> List())
      _ <- Entity.i.offsetDateTime(min).<=(offsetDateTime1).query.get.map(_ ==> List(a))

      _ <- Entity.i.offsetDateTime(min).>(offsetDateTime1).query.get.map(_ ==> List())
      _ <- Entity.i.offsetDateTime(min).>(offsetDateTime0).query.get.map(_ ==> List(a))

      _ <- Entity.i.offsetDateTime(min).>=(offsetDateTime1).query.get.map(_ ==> List(a))
      _ <- Entity.i.offsetDateTime(min).>=(offsetDateTime2).query.get.map(_ ==> List())
    } yield ()
  }


  "max" - types {
    val (a, b) = ((1, offsetDateTime1), (1, offsetDateTime2))
    for {
      _ <- Entity.i.offsetDateTime.insert(a, b).transact

      // 1 attribute
      _ <- Entity.offsetDateTime(max).query.get.map(_ ==> List(offsetDateTime2))

      _ <- Entity.offsetDateTime(max)(offsetDateTime2).query.get.map(_ ==> List(offsetDateTime2))
      _ <- Entity.offsetDateTime(max)(offsetDateTime1).query.get.map(_ ==> List())

      _ <- Entity.offsetDateTime(max).not(offsetDateTime2).query.get.map(_ ==> List())
      _ <- Entity.offsetDateTime(max).not(offsetDateTime1).query.get.map(_ ==> List(offsetDateTime2))

      _ <- Entity.offsetDateTime(max).<(offsetDateTime2).query.get.map(_ ==> List())
      _ <- Entity.offsetDateTime(max).<(offsetDateTime3).query.get.map(_ ==> List(offsetDateTime2))

      _ <- Entity.offsetDateTime(max).<=(offsetDateTime1).query.get.map(_ ==> List())
      _ <- Entity.offsetDateTime(max).<=(offsetDateTime2).query.get.map(_ ==> List(offsetDateTime2))

      _ <- Entity.offsetDateTime(max).>(offsetDateTime2).query.get.map(_ ==> List())
      _ <- Entity.offsetDateTime(max).>(offsetDateTime1).query.get.map(_ ==> List(offsetDateTime2))

      _ <- Entity.offsetDateTime(max).>=(offsetDateTime2).query.get.map(_ ==> List(offsetDateTime2))
      _ <- Entity.offsetDateTime(max).>=(offsetDateTime3).query.get.map(_ ==> List())


      // n attributes
      _ <- Entity.i.offsetDateTime(max).query.get.map(_ ==> List(b))

      _ <- Entity.i.offsetDateTime(max)(offsetDateTime2).query.get.map(_ ==> List(b))
      _ <- Entity.i.offsetDateTime(max)(offsetDateTime1).query.get.map(_ ==> List())

      _ <- Entity.i.offsetDateTime(max).not(offsetDateTime2).query.get.map(_ ==> List())
      _ <- Entity.i.offsetDateTime(max).not(offsetDateTime1).query.get.map(_ ==> List(b))

      _ <- Entity.i.offsetDateTime(max).<(offsetDateTime2).query.get.map(_ ==> List())
      _ <- Entity.i.offsetDateTime(max).<(offsetDateTime3).query.get.map(_ ==> List(b))

      _ <- Entity.i.offsetDateTime(max).<=(offsetDateTime1).query.get.map(_ ==> List())
      _ <- Entity.i.offsetDateTime(max).<=(offsetDateTime2).query.get.map(_ ==> List(b))

      _ <- Entity.i.offsetDateTime(max).>(offsetDateTime2).query.get.map(_ ==> List())
      _ <- Entity.i.offsetDateTime(max).>(offsetDateTime1).query.get.map(_ ==> List(b))

      _ <- Entity.i.offsetDateTime(max).>=(offsetDateTime2).query.get.map(_ ==> List(b))
      _ <- Entity.i.offsetDateTime(max).>=(offsetDateTime3).query.get.map(_ ==> List())
    } yield ()
  }


  "min/max" - types {
    for {
      _ <- Entity.i.offsetDateTime.insert(
        (1, offsetDateTime1),
        (1, offsetDateTime2),
        (2, offsetDateTime3),
        (2, offsetDateTime4),
      ).transact

      _ <- Entity.offsetDateTime(min).offsetDateTime(max).query.get.map(_ ==> List((offsetDateTime1, offsetDateTime4)))

      _ <- Entity.offsetDateTime(min)(offsetDateTime1).offsetDateTime(max)(offsetDateTime4).query.get.map(_ ==> List((offsetDateTime1, offsetDateTime4)))
      _ <- Entity.offsetDateTime(min)(offsetDateTime1).offsetDateTime(max)(offsetDateTime5).query.get.map(_ ==> List())

      _ <- Entity.offsetDateTime(min).not(offsetDateTime2).offsetDateTime(max).not(offsetDateTime3).query.get.map(_ ==> List((offsetDateTime1, offsetDateTime4)))
      _ <- Entity.offsetDateTime(min).not(offsetDateTime2).offsetDateTime(max).not(offsetDateTime4).query.get.map(_ ==> List())

      _ <- Entity.offsetDateTime(min).<(offsetDateTime2).offsetDateTime(max).>(offsetDateTime3).query.get.map(_ ==> List((offsetDateTime1, offsetDateTime4)))
      _ <- Entity.offsetDateTime(min).<(offsetDateTime2).offsetDateTime(max).>(offsetDateTime4).query.get.map(_ ==> List())

      _ <- Entity.offsetDateTime(min).<=(offsetDateTime1).offsetDateTime(max).>=(offsetDateTime4).query.get.map(_ ==> List((offsetDateTime1, offsetDateTime4)))
      _ <- Entity.offsetDateTime(min).<=(offsetDateTime1).offsetDateTime(max).>=(offsetDateTime5).query.get.map(_ ==> List())
    } yield ()
  }


  "min/max n" - types {
    for {
      _ <- Entity.i.offsetDateTime.insert(
        (1, offsetDateTime1),
        (1, offsetDateTime2),
        (1, offsetDateTime3),
        (2, offsetDateTime4),
        (2, offsetDateTime5),
        (2, offsetDateTime6),
        (2, offsetDateTime6), // (make sure grouped values coalesce)
      ).transact

      _ <- Entity.offsetDateTime(min(1)).query.get.map(_ ==> List(Set(offsetDateTime1)))
      _ <- Entity.offsetDateTime(min(2)).query.get.map(_ ==> List(Set(offsetDateTime1, offsetDateTime2)))

      _ <- Entity.offsetDateTime(max(1)).query.get.map(_ ==> List(Set(offsetDateTime6)))
      _ <- Entity.offsetDateTime(max(2)).query.get.map(_ ==> List(Set(offsetDateTime5, offsetDateTime6)))

      _ <- Entity.i.a1.offsetDateTime(min(2)).query.get.map(_ ==> List(
        (1, Set(offsetDateTime1, offsetDateTime2)),
        (2, Set(offsetDateTime4, offsetDateTime5))
      ))

      _ <- Entity.i.a1.offsetDateTime(max(2)).query.get.map(_ ==> List(
        (1, Set(offsetDateTime2, offsetDateTime3)),
        (2, Set(offsetDateTime5, offsetDateTime6))
      ))

      _ <- Entity.i.a1.offsetDateTime(min(2)).offsetDateTime(max(2)).query.get.map(_ ==> List(
        (1, Set(offsetDateTime1, offsetDateTime2), Set(offsetDateTime2, offsetDateTime3)),
        (2, Set(offsetDateTime4, offsetDateTime5), Set(offsetDateTime5, offsetDateTime6))
      ))
    } yield ()
  }


  "sample" - types {
    val all       = Set(offsetDateTime1, offsetDateTime2, offsetDateTime3)
    val (a, b, c) = ((1, offsetDateTime1), (2, offsetDateTime2), (3, offsetDateTime3))
    val allPairs  = List(a, b, c)
    for {
      _ <- Entity.i.offsetDateTime.insert(allPairs).transact

      // 1 attribute
      _ <- Entity.offsetDateTime(sample).query.get.map(res => all.contains(res.head) ==> true)

      // Checking for equality on a sample doesn't make sense
      // _ <- Entity.offsetDateTime(sample)(offsetDateTime2).query.get.map(res => all.contains(res.head) ==> true)
      // If you want a specific value, this would be the natural query
      _ <- Entity.offsetDateTime(offsetDateTime2).query.get.map(_ ==> List(offsetDateTime2))

      _ <- Entity.offsetDateTime(sample).not(offsetDateTime2).query.get.map { res =>
        List(offsetDateTime1, offsetDateTime3).contains(res.head) ==> true
        (res.head == offsetDateTime2) ==> false
      }
      _ <- Entity.offsetDateTime(sample).<(offsetDateTime3).query.get.map { res =>
        List(offsetDateTime1, offsetDateTime2).contains(res.head) ==> true
        (res.head == offsetDateTime3) ==> false
      }
      _ <- Entity.offsetDateTime(sample).<=(offsetDateTime2).query.get.map { res =>
        List(offsetDateTime1, offsetDateTime2).contains(res.head) ==> true
        (res.head == offsetDateTime3) ==> false
      }
      _ <- Entity.offsetDateTime(sample).>(offsetDateTime1).query.get.map { res =>
        List(offsetDateTime2, offsetDateTime3).contains(res.head) ==> true
        (res.head == offsetDateTime1) ==> false
      }
      _ <- Entity.offsetDateTime(sample).>=(offsetDateTime2).query.get.map { res =>
        List(offsetDateTime2, offsetDateTime3).contains(res.head) ==> true
        (res.head == offsetDateTime1) ==> false
      }

      // 1 attribute
      _ <- Entity.i.offsetDateTime(sample).query.get.map(res => allPairs.contains(res.head) ==> true)

      _ <- Entity.i.offsetDateTime(sample).not(offsetDateTime2).query.get.map { res =>
        List(a, c).contains(res.head) ==> true
        (res.head == b) ==> false
      }
      _ <- Entity.i.offsetDateTime(sample).<(offsetDateTime3).query.get.map { res =>
        List(a, b).contains(res.head) ==> true
        (res.head == c) ==> false
      }
      _ <- Entity.i.offsetDateTime(sample).<=(offsetDateTime2).query.get.map { res =>
        List(a, b).contains(res.head) ==> true
        (res.head == c) ==> false
      }
      _ <- Entity.i.offsetDateTime(sample).>(offsetDateTime1).query.get.map { res =>
        List(b, c).contains(res.head) ==> true
        (res.head == a) ==> false
      }
      _ <- Entity.i.offsetDateTime(sample).>=(offsetDateTime2).query.get.map { res =>
        List(b, c).contains(res.head) ==> true
        (res.head == a) ==> false
      }
    } yield ()
  }


  "samples(n)" - types {
    val all = Set(offsetDateTime1, offsetDateTime2, offsetDateTime3)
    for {
      _ <- Entity.offsetDateTime.insert(List(offsetDateTime1, offsetDateTime2, offsetDateTime3)).transact
      _ <- Entity.offsetDateTime(sample(1)).query.get.map(res => all.intersect(res.head).nonEmpty ==> true)
      _ <- Entity.offsetDateTime(sample(2)).query.get.map(res => all.intersect(res.head).nonEmpty ==> true)
    } yield ()
  }


  "count" - types {
    val (a, b) = ((1, 1), (2, 3))
    for {
      _ <- Entity.i.offsetDateTime.insert(List(
        (1, offsetDateTime1),
        (2, offsetDateTime2),
        (2, offsetDateTime2),
        (2, offsetDateTime3),
      )).transact

      // 1 attribute
      _ <- Entity.offsetDateTime(count).query.get.map(_ ==> List(4))

      _ <- Entity.offsetDateTime(count)(3).query.get.map(_ ==> List())
      _ <- Entity.offsetDateTime(count)(4).query.get.map(_ ==> List(4))

      _ <- Entity.offsetDateTime(count).not(3).query.get.map(_ ==> List(4))
      _ <- Entity.offsetDateTime(count).not(4).query.get.map(_ ==> List())

      _ <- Entity.offsetDateTime(count).<(5).query.get.map(_ ==> List(4))
      _ <- Entity.offsetDateTime(count).<(4).query.get.map(_ ==> List())

      _ <- Entity.offsetDateTime(count).<=(4).query.get.map(_ ==> List(4))
      _ <- Entity.offsetDateTime(count).<=(3).query.get.map(_ ==> List())

      _ <- Entity.offsetDateTime(count).>(3).query.get.map(_ ==> List(4))
      _ <- Entity.offsetDateTime(count).>(4).query.get.map(_ ==> List())

      _ <- Entity.offsetDateTime(count).>=(4).query.get.map(_ ==> List(4))
      _ <- Entity.offsetDateTime(count).>=(5).query.get.map(_ ==> List())


      // n attributes
      _ <- Entity.i.a1.offsetDateTime(count).query.get.map(_ ==> List(a, b))

      _ <- Entity.i.a1.offsetDateTime(count)(3).query.get.map(_ ==> List(b))
      _ <- Entity.i.a1.offsetDateTime(count)(2).query.get.map(_ ==> List())

      _ <- Entity.i.a1.offsetDateTime(count).not(3).query.get.map(_ ==> List(a))
      _ <- Entity.i.a1.offsetDateTime(count).not(2).query.get.map(_ ==> List(a, b))

      _ <- Entity.i.a1.offsetDateTime(count).<(3).query.get.map(_ ==> List(a))
      _ <- Entity.i.a1.offsetDateTime(count).<(4).query.get.map(_ ==> List(a, b))

      _ <- Entity.i.a1.offsetDateTime(count).<=(3).query.get.map(_ ==> List(a, b))
      _ <- Entity.i.a1.offsetDateTime(count).<=(2).query.get.map(_ ==> List(a))

      _ <- Entity.i.a1.offsetDateTime(count).>(2).query.get.map(_ ==> List(b))
      _ <- Entity.i.a1.offsetDateTime(count).>(3).query.get.map(_ ==> List())

      _ <- Entity.i.a1.offsetDateTime(count).>=(3).query.get.map(_ ==> List(b))
      _ <- Entity.i.a1.offsetDateTime(count).>=(4).query.get.map(_ ==> List())
    } yield ()
  }


  "countDistinct" - types {
    val (a, b) = ((1, 1), (2, 2))
    for {
      _ <- Entity.i.offsetDateTime.insert(List(
        (1, offsetDateTime1),
        (2, offsetDateTime2),
        (2, offsetDateTime2),
        (2, offsetDateTime3),
      )).transact

      // 1 attribute
      _ <- Entity.offsetDateTime(countDistinct).query.get.map(_ ==> List(3))

      _ <- Entity.offsetDateTime(countDistinct)(2).query.get.map(_ ==> List())
      _ <- Entity.offsetDateTime(countDistinct)(3).query.get.map(_ ==> List(3))

      _ <- Entity.offsetDateTime(countDistinct).not(2).query.get.map(_ ==> List(3))
      _ <- Entity.offsetDateTime(countDistinct).not(3).query.get.map(_ ==> List())

      _ <- Entity.offsetDateTime(countDistinct).<(4).query.get.map(_ ==> List(3))
      _ <- Entity.offsetDateTime(countDistinct).<(3).query.get.map(_ ==> List())

      _ <- Entity.offsetDateTime(countDistinct).<=(3).query.get.map(_ ==> List(3))
      _ <- Entity.offsetDateTime(countDistinct).<=(2).query.get.map(_ ==> List())

      _ <- Entity.offsetDateTime(countDistinct).>(2).query.get.map(_ ==> List(3))
      _ <- Entity.offsetDateTime(countDistinct).>(3).query.get.map(_ ==> List())

      _ <- Entity.offsetDateTime(countDistinct).>=(3).query.get.map(_ ==> List(3))
      _ <- Entity.offsetDateTime(countDistinct).>=(4).query.get.map(_ ==> List())


      // n attributes
      _ <- Entity.i.a1.offsetDateTime(countDistinct).query.get.map(_ ==> List(a, b))

      _ <- Entity.i.a1.offsetDateTime(countDistinct)(2).query.get.map(_ ==> List(b))
      _ <- Entity.i.a1.offsetDateTime(countDistinct)(3).query.get.map(_ ==> List())

      _ <- Entity.i.a1.offsetDateTime(countDistinct).not(2).query.get.map(_ ==> List(a))
      _ <- Entity.i.a1.offsetDateTime(countDistinct).not(3).query.get.map(_ ==> List(a, b))

      _ <- Entity.i.a1.offsetDateTime(countDistinct).<(2).query.get.map(_ ==> List(a))
      _ <- Entity.i.a1.offsetDateTime(countDistinct).<(3).query.get.map(_ ==> List(a, b))

      _ <- Entity.i.a1.offsetDateTime(countDistinct).<=(2).query.get.map(_ ==> List(a, b))
      _ <- Entity.i.a1.offsetDateTime(countDistinct).<=(1).query.get.map(_ ==> List(a))

      _ <- Entity.i.a1.offsetDateTime(countDistinct).>(1).query.get.map(_ ==> List(b))
      _ <- Entity.i.a1.offsetDateTime(countDistinct).>(2).query.get.map(_ ==> List())

      _ <- Entity.i.a1.offsetDateTime(countDistinct).>=(2).query.get.map(_ ==> List(b))
      _ <- Entity.i.a1.offsetDateTime(countDistinct).>=(3).query.get.map(_ ==> List())
    } yield ()
  }
}