// GENERATED CODE ********************************
package molecule.db.compliance.test.aggregation.any

import java.time.LocalDateTime
import molecule.base.error.ModelError
import molecule.core.setup.{MUnit, TestUtils}
import molecule.db.common.api.Api_async
import molecule.db.common.spi.Spi_async
import molecule.db.common.util.Executor.*
import molecule.db.compliance.domains.dsl.Types.*
import molecule.db.compliance.setup.DbProviders
import org.scalactic.Equality

case class Aggr_LocalDateTime_(
  suite: MUnit,
  api: Api_async & Spi_async & DbProviders
) extends TestUtils {

  import api.*
  import suite.*

  "distinct" - types {
    for {
      _ <- Entity.i.localDateTime.insert(List(
        (1, localDateTime1),
        (2, localDateTime2),
        (2, localDateTime2),
        (2, localDateTime3),
      )).transact

      _ <- Entity.i.localDateTime.a1.query.get.map(_ ==> List(
        (1, localDateTime1),
        (2, localDateTime2), // 2 rows coalesced
        (2, localDateTime3),
      ))

      // Distinct values are returned in a Set
      _ <- Entity.i.a1.localDateTime(distinct).query.get.map(_ ==> List(
        (1, Set(localDateTime1)),
        (2, Set(localDateTime2, localDateTime3)),
      ))

      _ <- Entity.localDateTime(distinct).query.get.map(_.head ==> Set(
        localDateTime1, localDateTime2, localDateTime3
      ))
    } yield ()
  }


  "min" - types {
    val (a, b) = ((1, localDateTime1), (1, localDateTime2))
    for {
      _ <- Entity.i.localDateTime.insert(a, b).transact

      // 1 attribute
      _ <- Entity.localDateTime(min).query.get.map(_ ==> List(localDateTime1))

      _ <- Entity.localDateTime(min)(localDateTime1).query.get.map(_ ==> List(localDateTime1))
      _ <- Entity.localDateTime(min)(localDateTime2).query.get.map(_ ==> List())

      _ <- Entity.localDateTime(min).not(localDateTime1).query.get.map(_ ==> List())
      _ <- Entity.localDateTime(min).not(localDateTime2).query.get.map(_ ==> List(localDateTime1))

      _ <- Entity.localDateTime(min).<(localDateTime1).query.get.map(_ ==> List())
      _ <- Entity.localDateTime(min).<(localDateTime2).query.get.map(_ ==> List(localDateTime1))

      _ <- Entity.localDateTime(min).<=(localDateTime0).query.get.map(_ ==> List())
      _ <- Entity.localDateTime(min).<=(localDateTime1).query.get.map(_ ==> List(localDateTime1))

      _ <- Entity.localDateTime(min).>(localDateTime1).query.get.map(_ ==> List())
      _ <- Entity.localDateTime(min).>(localDateTime0).query.get.map(_ ==> List(localDateTime1))

      _ <- Entity.localDateTime(min).>=(localDateTime1).query.get.map(_ ==> List(localDateTime1))
      _ <- Entity.localDateTime(min).>=(localDateTime2).query.get.map(_ ==> List())


      // n attributes
      _ <- Entity.i.localDateTime(min).query.get.map(_ ==> List(a))

      _ <- Entity.i.localDateTime(min)(localDateTime1).query.get.map(_ ==> List(a))
      _ <- Entity.i.localDateTime(min)(localDateTime2).query.get.map(_ ==> List())

      _ <- Entity.i.localDateTime(min).not(localDateTime1).query.get.map(_ ==> List())
      _ <- Entity.i.localDateTime(min).not(localDateTime2).query.get.map(_ ==> List(a))

      _ <- Entity.i.localDateTime(min).<(localDateTime1).query.get.map(_ ==> List())
      _ <- Entity.i.localDateTime(min).<(localDateTime2).query.get.map(_ ==> List(a))

      _ <- Entity.i.localDateTime(min).<=(localDateTime0).query.get.map(_ ==> List())
      _ <- Entity.i.localDateTime(min).<=(localDateTime1).query.get.map(_ ==> List(a))

      _ <- Entity.i.localDateTime(min).>(localDateTime1).query.get.map(_ ==> List())
      _ <- Entity.i.localDateTime(min).>(localDateTime0).query.get.map(_ ==> List(a))

      _ <- Entity.i.localDateTime(min).>=(localDateTime1).query.get.map(_ ==> List(a))
      _ <- Entity.i.localDateTime(min).>=(localDateTime2).query.get.map(_ ==> List())
    } yield ()
  }


  "max" - types {
    val (a, b) = ((1, localDateTime1), (1, localDateTime2))
    for {
      _ <- Entity.i.localDateTime.insert(a, b).transact

      // 1 attribute
      _ <- Entity.localDateTime(max).query.get.map(_ ==> List(localDateTime2))

      _ <- Entity.localDateTime(max)(localDateTime2).query.get.map(_ ==> List(localDateTime2))
      _ <- Entity.localDateTime(max)(localDateTime1).query.get.map(_ ==> List())

      _ <- Entity.localDateTime(max).not(localDateTime2).query.get.map(_ ==> List())
      _ <- Entity.localDateTime(max).not(localDateTime1).query.get.map(_ ==> List(localDateTime2))

      _ <- Entity.localDateTime(max).<(localDateTime2).query.get.map(_ ==> List())
      _ <- Entity.localDateTime(max).<(localDateTime3).query.get.map(_ ==> List(localDateTime2))

      _ <- Entity.localDateTime(max).<=(localDateTime1).query.get.map(_ ==> List())
      _ <- Entity.localDateTime(max).<=(localDateTime2).query.get.map(_ ==> List(localDateTime2))

      _ <- Entity.localDateTime(max).>(localDateTime2).query.get.map(_ ==> List())
      _ <- Entity.localDateTime(max).>(localDateTime1).query.get.map(_ ==> List(localDateTime2))

      _ <- Entity.localDateTime(max).>=(localDateTime2).query.get.map(_ ==> List(localDateTime2))
      _ <- Entity.localDateTime(max).>=(localDateTime3).query.get.map(_ ==> List())


      // n attributes
      _ <- Entity.i.localDateTime(max).query.get.map(_ ==> List(b))

      _ <- Entity.i.localDateTime(max)(localDateTime2).query.get.map(_ ==> List(b))
      _ <- Entity.i.localDateTime(max)(localDateTime1).query.get.map(_ ==> List())

      _ <- Entity.i.localDateTime(max).not(localDateTime2).query.get.map(_ ==> List())
      _ <- Entity.i.localDateTime(max).not(localDateTime1).query.get.map(_ ==> List(b))

      _ <- Entity.i.localDateTime(max).<(localDateTime2).query.get.map(_ ==> List())
      _ <- Entity.i.localDateTime(max).<(localDateTime3).query.get.map(_ ==> List(b))

      _ <- Entity.i.localDateTime(max).<=(localDateTime1).query.get.map(_ ==> List())
      _ <- Entity.i.localDateTime(max).<=(localDateTime2).query.get.map(_ ==> List(b))

      _ <- Entity.i.localDateTime(max).>(localDateTime2).query.get.map(_ ==> List())
      _ <- Entity.i.localDateTime(max).>(localDateTime1).query.get.map(_ ==> List(b))

      _ <- Entity.i.localDateTime(max).>=(localDateTime2).query.get.map(_ ==> List(b))
      _ <- Entity.i.localDateTime(max).>=(localDateTime3).query.get.map(_ ==> List())
    } yield ()
  }


  "min/max" - types {
    for {
      _ <- Entity.i.localDateTime.insert(
        (1, localDateTime1),
        (1, localDateTime2),
        (2, localDateTime3),
        (2, localDateTime4),
      ).transact

      _ <- Entity.localDateTime(min).localDateTime(max).query.get.map(_ ==> List((localDateTime1, localDateTime4)))

      _ <- Entity.localDateTime(min)(localDateTime1).localDateTime(max)(localDateTime4).query.get.map(_ ==> List((localDateTime1, localDateTime4)))
      _ <- Entity.localDateTime(min)(localDateTime1).localDateTime(max)(localDateTime5).query.get.map(_ ==> List())

      _ <- Entity.localDateTime(min).not(localDateTime2).localDateTime(max).not(localDateTime3).query.get.map(_ ==> List((localDateTime1, localDateTime4)))
      _ <- Entity.localDateTime(min).not(localDateTime2).localDateTime(max).not(localDateTime4).query.get.map(_ ==> List())

      _ <- Entity.localDateTime(min).<(localDateTime2).localDateTime(max).>(localDateTime3).query.get.map(_ ==> List((localDateTime1, localDateTime4)))
      _ <- Entity.localDateTime(min).<(localDateTime2).localDateTime(max).>(localDateTime4).query.get.map(_ ==> List())

      _ <- Entity.localDateTime(min).<=(localDateTime1).localDateTime(max).>=(localDateTime4).query.get.map(_ ==> List((localDateTime1, localDateTime4)))
      _ <- Entity.localDateTime(min).<=(localDateTime1).localDateTime(max).>=(localDateTime5).query.get.map(_ ==> List())
    } yield ()
  }


  "min/max n" - types {
    for {
      _ <- Entity.i.localDateTime.insert(
        (1, localDateTime1),
        (1, localDateTime2),
        (1, localDateTime3),
        (2, localDateTime4),
        (2, localDateTime5),
        (2, localDateTime6),
        (2, localDateTime6), // (make sure grouped values coalesce)
      ).transact

      _ <- Entity.localDateTime(min(1)).query.get.map(_ ==> List(Set(localDateTime1)))
      _ <- Entity.localDateTime(min(2)).query.get.map(_ ==> List(Set(localDateTime1, localDateTime2)))

      _ <- Entity.localDateTime(max(1)).query.get.map(_ ==> List(Set(localDateTime6)))
      _ <- Entity.localDateTime(max(2)).query.get.map(_ ==> List(Set(localDateTime5, localDateTime6)))

      _ <- Entity.i.a1.localDateTime(min(2)).query.get.map(_ ==> List(
        (1, Set(localDateTime1, localDateTime2)),
        (2, Set(localDateTime4, localDateTime5))
      ))

      _ <- Entity.i.a1.localDateTime(max(2)).query.get.map(_ ==> List(
        (1, Set(localDateTime2, localDateTime3)),
        (2, Set(localDateTime5, localDateTime6))
      ))

      _ <- Entity.i.a1.localDateTime(min(2)).localDateTime(max(2)).query.get.map(_ ==> List(
        (1, Set(localDateTime1, localDateTime2), Set(localDateTime2, localDateTime3)),
        (2, Set(localDateTime4, localDateTime5), Set(localDateTime5, localDateTime6))
      ))
    } yield ()
  }


  "sample" - types {
    val all      = Set(localDateTime1, localDateTime2, localDateTime3)
    val allPairs = List((1, localDateTime1), (2, localDateTime2), (3, localDateTime3))
    for {
      _ <- Entity.i.localDateTime.insert(allPairs).transact

      // 1 attribute
      _ <- Entity.localDateTime(sample).query.get.map(res => all.contains(res.head) ==> true)

      // 1 attribute
      _ <- Entity.i.localDateTime(sample).query.get.map(res => allPairs.contains(res.head) ==> true)
    } yield ()
  }

  "sample ops" - types {
    if (Seq("mariadb", "mysql").contains(database)) {
      Entity.localDateTime(sample)(localDateTime1).query.get
        .map(_ ==> "Unexpected success").recover { case ModelError(err) =>
          err ==> "Operations on sample not implemented for this database."
        }
    } else {
      val all       = Set(localDateTime1, localDateTime2, localDateTime3)
      val (a, b, c) = ((1, localDateTime1), (2, localDateTime2), (3, localDateTime3))
      val allPairs  = List(a, b, c)
      for {
        _ <- Entity.i.localDateTime.insert(allPairs).transact

        // 1 attribute
        // Checking for equality on a sample doesn't make sense
        // _ <- Entity.localDateTime(sample)(localDateTime2).query.get.map(res => all.contains(res.head) ==> true)
        // If you want a specific value, this would be the natural query
        _ <- Entity.localDateTime(localDateTime2).query.get.map(_ ==> List(localDateTime2))

        _ <- Entity.localDateTime(sample).not(localDateTime2).query.get.map { res =>
          List(localDateTime1, localDateTime3).contains(res.head) ==> true
          (res.head == localDateTime2) ==> false
        }
        _ <- Entity.localDateTime(sample).<(localDateTime3).query.get.map { res =>
          List(localDateTime1, localDateTime2).contains(res.head) ==> true
          (res.head == localDateTime3) ==> false
        }
        _ <- Entity.localDateTime(sample).<=(localDateTime2).query.get.map { res =>
          List(localDateTime1, localDateTime2).contains(res.head) ==> true
          (res.head == localDateTime3) ==> false
        }
        _ <- Entity.localDateTime(sample).>(localDateTime1).query.get.map { res =>
          List(localDateTime2, localDateTime3).contains(res.head) ==> true
          (res.head == localDateTime1) ==> false
        }
        _ <- Entity.localDateTime(sample).>=(localDateTime2).query.get.map { res =>
          List(localDateTime2, localDateTime3).contains(res.head) ==> true
          (res.head == localDateTime1) ==> false
        }

        // 1 attribute
        _ <- Entity.i.localDateTime(sample).not(localDateTime2).query.get.map { res =>
          List(a, c).contains(res.head) ==> true
          (res.head == b) ==> false
        }
        _ <- Entity.i.localDateTime(sample).<(localDateTime3).query.get.map { res =>
          List(a, b).contains(res.head) ==> true
          (res.head == c) ==> false
        }
        _ <- Entity.i.localDateTime(sample).<=(localDateTime2).query.get.map { res =>
          List(a, b).contains(res.head) ==> true
          (res.head == c) ==> false
        }
        _ <- Entity.i.localDateTime(sample).>(localDateTime1).query.get.map { res =>
          List(b, c).contains(res.head) ==> true
          (res.head == a) ==> false
        }
        _ <- Entity.i.localDateTime(sample).>=(localDateTime2).query.get.map { res =>
          List(b, c).contains(res.head) ==> true
          (res.head == a) ==> false
        }
      } yield ()
    }
  }


  "samples(n)" - types {
    val all = Set(localDateTime1, localDateTime2, localDateTime3)
    for {
      _ <- Entity.localDateTime.insert(List(localDateTime1, localDateTime2, localDateTime3)).transact
      _ <- Entity.localDateTime(sample(1)).query.get.map(res => all.intersect(res.head).nonEmpty ==> true)
      _ <- Entity.localDateTime(sample(2)).query.get.map(res => all.intersect(res.head).nonEmpty ==> true)
    } yield ()
  }


  "count" - types {
    val (a, b) = ((1, 1), (2, 3))
    for {
      _ <- Entity.i.localDateTime.insert(List(
        (1, localDateTime1),
        (2, localDateTime2),
        (2, localDateTime2),
        (2, localDateTime3),
      )).transact

      // 1 attribute
      _ <- Entity.localDateTime(count).query.get.map(_ ==> List(4))

      _ <- Entity.localDateTime(count)(3).query.get.map(_ ==> List())
      _ <- Entity.localDateTime(count)(4).query.get.map(_ ==> List(4))

      _ <- Entity.localDateTime(count).not(3).query.get.map(_ ==> List(4))
      _ <- Entity.localDateTime(count).not(4).query.get.map(_ ==> List())

      _ <- Entity.localDateTime(count).<(5).query.get.map(_ ==> List(4))
      _ <- Entity.localDateTime(count).<(4).query.get.map(_ ==> List())

      _ <- Entity.localDateTime(count).<=(4).query.get.map(_ ==> List(4))
      _ <- Entity.localDateTime(count).<=(3).query.get.map(_ ==> List())

      _ <- Entity.localDateTime(count).>(3).query.get.map(_ ==> List(4))
      _ <- Entity.localDateTime(count).>(4).query.get.map(_ ==> List())

      _ <- Entity.localDateTime(count).>=(4).query.get.map(_ ==> List(4))
      _ <- Entity.localDateTime(count).>=(5).query.get.map(_ ==> List())


      // n attributes
      _ <- Entity.i.a1.localDateTime(count).query.get.map(_ ==> List(a, b))

      _ <- Entity.i.a1.localDateTime(count)(3).query.get.map(_ ==> List(b))
      _ <- Entity.i.a1.localDateTime(count)(2).query.get.map(_ ==> List())

      _ <- Entity.i.a1.localDateTime(count).not(3).query.get.map(_ ==> List(a))
      _ <- Entity.i.a1.localDateTime(count).not(2).query.get.map(_ ==> List(a, b))

      _ <- Entity.i.a1.localDateTime(count).<(3).query.get.map(_ ==> List(a))
      _ <- Entity.i.a1.localDateTime(count).<(4).query.get.map(_ ==> List(a, b))

      _ <- Entity.i.a1.localDateTime(count).<=(3).query.get.map(_ ==> List(a, b))
      _ <- Entity.i.a1.localDateTime(count).<=(2).query.get.map(_ ==> List(a))

      _ <- Entity.i.a1.localDateTime(count).>(2).query.get.map(_ ==> List(b))
      _ <- Entity.i.a1.localDateTime(count).>(3).query.get.map(_ ==> List())

      _ <- Entity.i.a1.localDateTime(count).>=(3).query.get.map(_ ==> List(b))
      _ <- Entity.i.a1.localDateTime(count).>=(4).query.get.map(_ ==> List())
    } yield ()
  }


  "countDistinct" - types {
    val (a, b) = ((1, 1), (2, 2))
    for {
      _ <- Entity.i.localDateTime.insert(List(
        (1, localDateTime1),
        (2, localDateTime2),
        (2, localDateTime2),
        (2, localDateTime3),
      )).transact

      // 1 attribute
      _ <- Entity.localDateTime(countDistinct).query.get.map(_ ==> List(3))

      _ <- Entity.localDateTime(countDistinct)(2).query.get.map(_ ==> List())
      _ <- Entity.localDateTime(countDistinct)(3).query.get.map(_ ==> List(3))

      _ <- Entity.localDateTime(countDistinct).not(2).query.get.map(_ ==> List(3))
      _ <- Entity.localDateTime(countDistinct).not(3).query.get.map(_ ==> List())

      _ <- Entity.localDateTime(countDistinct).<(4).query.get.map(_ ==> List(3))
      _ <- Entity.localDateTime(countDistinct).<(3).query.get.map(_ ==> List())

      _ <- Entity.localDateTime(countDistinct).<=(3).query.get.map(_ ==> List(3))
      _ <- Entity.localDateTime(countDistinct).<=(2).query.get.map(_ ==> List())

      _ <- Entity.localDateTime(countDistinct).>(2).query.get.map(_ ==> List(3))
      _ <- Entity.localDateTime(countDistinct).>(3).query.get.map(_ ==> List())

      _ <- Entity.localDateTime(countDistinct).>=(3).query.get.map(_ ==> List(3))
      _ <- Entity.localDateTime(countDistinct).>=(4).query.get.map(_ ==> List())


      // n attributes
      _ <- Entity.i.a1.localDateTime(countDistinct).query.get.map(_ ==> List(a, b))

      _ <- Entity.i.a1.localDateTime(countDistinct)(2).query.get.map(_ ==> List(b))
      _ <- Entity.i.a1.localDateTime(countDistinct)(3).query.get.map(_ ==> List())

      _ <- Entity.i.a1.localDateTime(countDistinct).not(2).query.get.map(_ ==> List(a))
      _ <- Entity.i.a1.localDateTime(countDistinct).not(3).query.get.map(_ ==> List(a, b))

      _ <- Entity.i.a1.localDateTime(countDistinct).<(2).query.get.map(_ ==> List(a))
      _ <- Entity.i.a1.localDateTime(countDistinct).<(3).query.get.map(_ ==> List(a, b))

      _ <- Entity.i.a1.localDateTime(countDistinct).<=(2).query.get.map(_ ==> List(a, b))
      _ <- Entity.i.a1.localDateTime(countDistinct).<=(1).query.get.map(_ ==> List(a))

      _ <- Entity.i.a1.localDateTime(countDistinct).>(1).query.get.map(_ ==> List(b))
      _ <- Entity.i.a1.localDateTime(countDistinct).>(2).query.get.map(_ ==> List())

      _ <- Entity.i.a1.localDateTime(countDistinct).>=(2).query.get.map(_ ==> List(b))
      _ <- Entity.i.a1.localDateTime(countDistinct).>=(3).query.get.map(_ ==> List())
    } yield ()
  }
}