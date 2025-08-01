// GENERATED CODE ********************************
package molecule.db.compliance.test.aggregation.any

import java.util.UUID
import molecule.core.setup.{MUnit, TestUtils}
import molecule.db.compliance.domains.dsl.Types.*
import molecule.db.compliance.setup.DbProviders
import molecule.db.common.api.Api_async
import molecule.db.common.spi.Spi_async
import molecule.db.common.util.Executor.*
import org.scalactic.Equality
import org.scalactic.Equality

case class Aggr_UUID_(
  suite: MUnit,
  api: Api_async & Spi_async & DbProviders
) extends TestUtils {

  import api.*
  import suite.*

  "distinct" - types {
    for {
      _ <- Entity.i.uuid.insert(List(
        (1, uuid1),
        (2, uuid2),
        (2, uuid2),
        (2, uuid3),
      )).transact

      _ <- Entity.i.uuid.a1.query.get.map(_ ==> List(
        (1, uuid1),
        (2, uuid2), // 2 rows coalesced
        (2, uuid3),
      ))

      // Distinct values are returned in a Set
      _ <- Entity.i.a1.uuid(distinct).query.get.map(_ ==> List(
        (1, Set(uuid1)),
        (2, Set(uuid2, uuid3)),
      ))

      _ <- Entity.uuid(distinct).query.get.map(_.head ==> Set(
        uuid1, uuid2, uuid3
      ))
    } yield ()
  }


  "min" - types {
    val (a, b) = ((1, uuid1), (1, uuid2))
    for {
      _ <- Entity.i.uuid.insert(a, b).transact

      // 1 attribute
      _ <- Entity.uuid(min).query.get.map(_ ==> List(uuid1))

      _ <- Entity.uuid(min)(uuid1).query.get.map(_ ==> List(uuid1))
      _ <- Entity.uuid(min)(uuid2).query.get.map(_ ==> List())

      _ <- Entity.uuid(min).not(uuid1).query.get.map(_ ==> List())
      _ <- Entity.uuid(min).not(uuid2).query.get.map(_ ==> List(uuid1))

      _ <- Entity.uuid(min).<(uuid1).query.get.map(_ ==> List())
      _ <- Entity.uuid(min).<(uuid2).query.get.map(_ ==> List(uuid1))

      _ <- Entity.uuid(min).<=(uuid0).query.get.map(_ ==> List())
      _ <- Entity.uuid(min).<=(uuid1).query.get.map(_ ==> List(uuid1))

      _ <- Entity.uuid(min).>(uuid1).query.get.map(_ ==> List())
      _ <- Entity.uuid(min).>(uuid0).query.get.map(_ ==> List(uuid1))

      _ <- Entity.uuid(min).>=(uuid1).query.get.map(_ ==> List(uuid1))
      _ <- Entity.uuid(min).>=(uuid2).query.get.map(_ ==> List())


      // n attributes
      _ <- Entity.i.uuid(min).query.get.map(_ ==> List(a))

      _ <- Entity.i.uuid(min)(uuid1).query.get.map(_ ==> List(a))
      _ <- Entity.i.uuid(min)(uuid2).query.get.map(_ ==> List())

      _ <- Entity.i.uuid(min).not(uuid1).query.get.map(_ ==> List())
      _ <- Entity.i.uuid(min).not(uuid2).query.get.map(_ ==> List(a))

      _ <- Entity.i.uuid(min).<(uuid1).query.get.map(_ ==> List())
      _ <- Entity.i.uuid(min).<(uuid2).query.get.map(_ ==> List(a))

      _ <- Entity.i.uuid(min).<=(uuid0).query.get.map(_ ==> List())
      _ <- Entity.i.uuid(min).<=(uuid1).query.get.map(_ ==> List(a))

      _ <- Entity.i.uuid(min).>(uuid1).query.get.map(_ ==> List())
      _ <- Entity.i.uuid(min).>(uuid0).query.get.map(_ ==> List(a))

      _ <- Entity.i.uuid(min).>=(uuid1).query.get.map(_ ==> List(a))
      _ <- Entity.i.uuid(min).>=(uuid2).query.get.map(_ ==> List())
    } yield ()
  }


  "max" - types {
    val (a, b) = ((1, uuid1), (1, uuid2))
    for {
      _ <- Entity.i.uuid.insert(a, b).transact

      // 1 attribute
      _ <- Entity.uuid(max).query.get.map(_ ==> List(uuid2))

      _ <- Entity.uuid(max)(uuid2).query.get.map(_ ==> List(uuid2))
      _ <- Entity.uuid(max)(uuid1).query.get.map(_ ==> List())

      _ <- Entity.uuid(max).not(uuid2).query.get.map(_ ==> List())
      _ <- Entity.uuid(max).not(uuid1).query.get.map(_ ==> List(uuid2))

      _ <- Entity.uuid(max).<(uuid2).query.get.map(_ ==> List())
      _ <- Entity.uuid(max).<(uuid3).query.get.map(_ ==> List(uuid2))

      _ <- Entity.uuid(max).<=(uuid1).query.get.map(_ ==> List())
      _ <- Entity.uuid(max).<=(uuid2).query.get.map(_ ==> List(uuid2))

      _ <- Entity.uuid(max).>(uuid2).query.get.map(_ ==> List())
      _ <- Entity.uuid(max).>(uuid1).query.get.map(_ ==> List(uuid2))

      _ <- Entity.uuid(max).>=(uuid2).query.get.map(_ ==> List(uuid2))
      _ <- Entity.uuid(max).>=(uuid3).query.get.map(_ ==> List())


      // n attributes
      _ <- Entity.i.uuid(max).query.get.map(_ ==> List(b))

      _ <- Entity.i.uuid(max)(uuid2).query.get.map(_ ==> List(b))
      _ <- Entity.i.uuid(max)(uuid1).query.get.map(_ ==> List())

      _ <- Entity.i.uuid(max).not(uuid2).query.get.map(_ ==> List())
      _ <- Entity.i.uuid(max).not(uuid1).query.get.map(_ ==> List(b))

      _ <- Entity.i.uuid(max).<(uuid2).query.get.map(_ ==> List())
      _ <- Entity.i.uuid(max).<(uuid3).query.get.map(_ ==> List(b))

      _ <- Entity.i.uuid(max).<=(uuid1).query.get.map(_ ==> List())
      _ <- Entity.i.uuid(max).<=(uuid2).query.get.map(_ ==> List(b))

      _ <- Entity.i.uuid(max).>(uuid2).query.get.map(_ ==> List())
      _ <- Entity.i.uuid(max).>(uuid1).query.get.map(_ ==> List(b))

      _ <- Entity.i.uuid(max).>=(uuid2).query.get.map(_ ==> List(b))
      _ <- Entity.i.uuid(max).>=(uuid3).query.get.map(_ ==> List())
    } yield ()
  }


  "min/max" - types {
    for {
      _ <- Entity.i.uuid.insert(
        (1, uuid1),
        (1, uuid2),
        (2, uuid3),
        (2, uuid4),
      ).transact

      _ <- Entity.uuid(min).uuid(max).query.get.map(_ ==> List((uuid1, uuid4)))

      _ <- Entity.uuid(min)(uuid1).uuid(max)(uuid4).query.get.map(_ ==> List((uuid1, uuid4)))
      _ <- Entity.uuid(min)(uuid1).uuid(max)(uuid5).query.get.map(_ ==> List())

      _ <- Entity.uuid(min).not(uuid2).uuid(max).not(uuid3).query.get.map(_ ==> List((uuid1, uuid4)))
      _ <- Entity.uuid(min).not(uuid2).uuid(max).not(uuid4).query.get.map(_ ==> List())

      _ <- Entity.uuid(min).<(uuid2).uuid(max).>(uuid3).query.get.map(_ ==> List((uuid1, uuid4)))
      _ <- Entity.uuid(min).<(uuid2).uuid(max).>(uuid4).query.get.map(_ ==> List())

      _ <- Entity.uuid(min).<=(uuid1).uuid(max).>=(uuid4).query.get.map(_ ==> List((uuid1, uuid4)))
      _ <- Entity.uuid(min).<=(uuid1).uuid(max).>=(uuid5).query.get.map(_ ==> List())
    } yield ()
  }


  "min/max n" - types {
    for {
      _ <- Entity.i.uuid.insert(
        (1, uuid1),
        (1, uuid2),
        (1, uuid3),
        (2, uuid4),
        (2, uuid5),
        (2, uuid6),
        (2, uuid6), // (make sure grouped values coalesce)
      ).transact

      _ <- Entity.uuid(min(1)).query.get.map(_ ==> List(Set(uuid1)))
      _ <- Entity.uuid(min(2)).query.get.map(_ ==> List(Set(uuid1, uuid2)))

      _ <- Entity.uuid(max(1)).query.get.map(_ ==> List(Set(uuid6)))
      _ <- Entity.uuid(max(2)).query.get.map(_ ==> List(Set(uuid5, uuid6)))

      _ <- Entity.i.a1.uuid(min(2)).query.get.map(_ ==> List(
        (1, Set(uuid1, uuid2)),
        (2, Set(uuid4, uuid5))
      ))

      _ <- Entity.i.a1.uuid(max(2)).query.get.map(_ ==> List(
        (1, Set(uuid2, uuid3)),
        (2, Set(uuid5, uuid6))
      ))

      _ <- Entity.i.a1.uuid(min(2)).uuid(max(2)).query.get.map(_ ==> List(
        (1, Set(uuid1, uuid2), Set(uuid2, uuid3)),
        (2, Set(uuid4, uuid5), Set(uuid5, uuid6))
      ))
    } yield ()
  }


  "sample" - types {
    val all       = Set(uuid1, uuid2, uuid3)
    val (a, b, c) = ((1, uuid1), (2, uuid2), (3, uuid3))
    val allPairs  = List(a, b, c)
    for {
      _ <- Entity.i.uuid.insert(allPairs).transact

      // 1 attribute
      _ <- Entity.uuid(sample).query.get.map(res => all.contains(res.head) ==> true)

      // Checking for equality on a sample doesn't make sense
      // _ <- Entity.uuid(sample)(uuid2).query.get.map(res => all.contains(res.head) ==> true)
      // If you want a specific value, this would be the natural query
      _ <- Entity.uuid(uuid2).query.get.map(_ ==> List(uuid2))

      _ <- Entity.uuid(sample).not(uuid2).query.get.map { res =>
        List(uuid1, uuid3).contains(res.head) ==> true
        (res.head == uuid2) ==> false
      }
      _ <- Entity.uuid(sample).<(uuid3).query.get.map { res =>
        List(uuid1, uuid2).contains(res.head) ==> true
        (res.head == uuid3) ==> false
      }
      _ <- Entity.uuid(sample).<=(uuid2).query.get.map { res =>
        List(uuid1, uuid2).contains(res.head) ==> true
        (res.head == uuid3) ==> false
      }
      _ <- Entity.uuid(sample).>(uuid1).query.get.map { res =>
        List(uuid2, uuid3).contains(res.head) ==> true
        (res.head == uuid1) ==> false
      }
      _ <- Entity.uuid(sample).>=(uuid2).query.get.map { res =>
        List(uuid2, uuid3).contains(res.head) ==> true
        (res.head == uuid1) ==> false
      }

      // 1 attribute
      _ <- Entity.i.uuid(sample).query.get.map(res => allPairs.contains(res.head) ==> true)

      _ <- Entity.i.uuid(sample).not(uuid2).query.get.map { res =>
        List(a, c).contains(res.head) ==> true
        (res.head == b) ==> false
      }
      _ <- Entity.i.uuid(sample).<(uuid3).query.get.map { res =>
        List(a, b).contains(res.head) ==> true
        (res.head == c) ==> false
      }
      _ <- Entity.i.uuid(sample).<=(uuid2).query.get.map { res =>
        List(a, b).contains(res.head) ==> true
        (res.head == c) ==> false
      }
      _ <- Entity.i.uuid(sample).>(uuid1).query.get.map { res =>
        List(b, c).contains(res.head) ==> true
        (res.head == a) ==> false
      }
      _ <- Entity.i.uuid(sample).>=(uuid2).query.get.map { res =>
        List(b, c).contains(res.head) ==> true
        (res.head == a) ==> false
      }
    } yield ()
  }


  "samples(n)" - types {
    val all = Set(uuid1, uuid2, uuid3)
    for {
      _ <- Entity.uuid.insert(List(uuid1, uuid2, uuid3)).transact
      _ <- Entity.uuid(sample(1)).query.get.map(res => all.intersect(res.head).nonEmpty ==> true)
      _ <- Entity.uuid(sample(2)).query.get.map(res => all.intersect(res.head).nonEmpty ==> true)
    } yield ()
  }


  "count" - types {
    val (a, b) = ((1, 1), (2, 3))
    for {
      _ <- Entity.i.uuid.insert(List(
        (1, uuid1),
        (2, uuid2),
        (2, uuid2),
        (2, uuid3),
      )).transact

      // 1 attribute
      _ <- Entity.uuid(count).query.get.map(_ ==> List(4))

      _ <- Entity.uuid(count)(3).query.get.map(_ ==> List())
      _ <- Entity.uuid(count)(4).query.get.map(_ ==> List(4))

      _ <- Entity.uuid(count).not(3).query.get.map(_ ==> List(4))
      _ <- Entity.uuid(count).not(4).query.get.map(_ ==> List())

      _ <- Entity.uuid(count).<(5).query.get.map(_ ==> List(4))
      _ <- Entity.uuid(count).<(4).query.get.map(_ ==> List())

      _ <- Entity.uuid(count).<=(4).query.get.map(_ ==> List(4))
      _ <- Entity.uuid(count).<=(3).query.get.map(_ ==> List())

      _ <- Entity.uuid(count).>(3).query.get.map(_ ==> List(4))
      _ <- Entity.uuid(count).>(4).query.get.map(_ ==> List())

      _ <- Entity.uuid(count).>=(4).query.get.map(_ ==> List(4))
      _ <- Entity.uuid(count).>=(5).query.get.map(_ ==> List())


      // n attributes
      _ <- Entity.i.a1.uuid(count).query.get.map(_ ==> List(a, b))

      _ <- Entity.i.a1.uuid(count)(3).query.get.map(_ ==> List(b))
      _ <- Entity.i.a1.uuid(count)(2).query.get.map(_ ==> List())

      _ <- Entity.i.a1.uuid(count).not(3).query.get.map(_ ==> List(a))
      _ <- Entity.i.a1.uuid(count).not(2).query.get.map(_ ==> List(a, b))

      _ <- Entity.i.a1.uuid(count).<(3).query.get.map(_ ==> List(a))
      _ <- Entity.i.a1.uuid(count).<(4).query.get.map(_ ==> List(a, b))

      _ <- Entity.i.a1.uuid(count).<=(3).query.get.map(_ ==> List(a, b))
      _ <- Entity.i.a1.uuid(count).<=(2).query.get.map(_ ==> List(a))

      _ <- Entity.i.a1.uuid(count).>(2).query.get.map(_ ==> List(b))
      _ <- Entity.i.a1.uuid(count).>(3).query.get.map(_ ==> List())

      _ <- Entity.i.a1.uuid(count).>=(3).query.get.map(_ ==> List(b))
      _ <- Entity.i.a1.uuid(count).>=(4).query.get.map(_ ==> List())
    } yield ()
  }


  "countDistinct" - types {
    val (a, b) = ((1, 1), (2, 2))
    for {
      _ <- Entity.i.uuid.insert(List(
        (1, uuid1),
        (2, uuid2),
        (2, uuid2),
        (2, uuid3),
      )).transact

      // 1 attribute
      _ <- Entity.uuid(countDistinct).query.get.map(_ ==> List(3))

      _ <- Entity.uuid(countDistinct)(2).query.get.map(_ ==> List())
      _ <- Entity.uuid(countDistinct)(3).query.get.map(_ ==> List(3))

      _ <- Entity.uuid(countDistinct).not(2).query.get.map(_ ==> List(3))
      _ <- Entity.uuid(countDistinct).not(3).query.get.map(_ ==> List())

      _ <- Entity.uuid(countDistinct).<(4).query.get.map(_ ==> List(3))
      _ <- Entity.uuid(countDistinct).<(3).query.get.map(_ ==> List())

      _ <- Entity.uuid(countDistinct).<=(3).query.get.map(_ ==> List(3))
      _ <- Entity.uuid(countDistinct).<=(2).query.get.map(_ ==> List())

      _ <- Entity.uuid(countDistinct).>(2).query.get.map(_ ==> List(3))
      _ <- Entity.uuid(countDistinct).>(3).query.get.map(_ ==> List())

      _ <- Entity.uuid(countDistinct).>=(3).query.get.map(_ ==> List(3))
      _ <- Entity.uuid(countDistinct).>=(4).query.get.map(_ ==> List())


      // n attributes
      _ <- Entity.i.a1.uuid(countDistinct).query.get.map(_ ==> List(a, b))

      _ <- Entity.i.a1.uuid(countDistinct)(2).query.get.map(_ ==> List(b))
      _ <- Entity.i.a1.uuid(countDistinct)(3).query.get.map(_ ==> List())

      _ <- Entity.i.a1.uuid(countDistinct).not(2).query.get.map(_ ==> List(a))
      _ <- Entity.i.a1.uuid(countDistinct).not(3).query.get.map(_ ==> List(a, b))

      _ <- Entity.i.a1.uuid(countDistinct).<(2).query.get.map(_ ==> List(a))
      _ <- Entity.i.a1.uuid(countDistinct).<(3).query.get.map(_ ==> List(a, b))

      _ <- Entity.i.a1.uuid(countDistinct).<=(2).query.get.map(_ ==> List(a, b))
      _ <- Entity.i.a1.uuid(countDistinct).<=(1).query.get.map(_ ==> List(a))

      _ <- Entity.i.a1.uuid(countDistinct).>(1).query.get.map(_ ==> List(b))
      _ <- Entity.i.a1.uuid(countDistinct).>(2).query.get.map(_ ==> List())

      _ <- Entity.i.a1.uuid(countDistinct).>=(2).query.get.map(_ ==> List(b))
      _ <- Entity.i.a1.uuid(countDistinct).>=(3).query.get.map(_ ==> List())
    } yield ()
  }
}