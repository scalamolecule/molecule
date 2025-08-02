// GENERATED CODE ********************************
package molecule.db.compliance.test.aggregation.any

import java.time.ZonedDateTime
import molecule.base.error.ModelError
import molecule.core.setup.{MUnit, TestUtils}
import molecule.db.common.api.Api_async
import molecule.db.common.spi.Spi_async
import molecule.db.common.util.Executor.*
import molecule.db.compliance.domains.dsl.Types.*
import molecule.db.compliance.setup.DbProviders
import org.scalactic.Equality

case class Aggr_ZonedDateTime_(
  suite: MUnit,
  api: Api_async & Spi_async & DbProviders
) extends TestUtils {

  import api.*
  import suite.*

  "distinct" - types {
    for {
      _ <- Entity.i.zonedDateTime.insert(List(
        (1, zonedDateTime1),
        (2, zonedDateTime2),
        (2, zonedDateTime2),
        (2, zonedDateTime3),
      )).transact

      _ <- Entity.i.zonedDateTime.a1.query.get.map(_ ==> List(
        (1, zonedDateTime1),
        (2, zonedDateTime2), // 2 rows coalesced
        (2, zonedDateTime3),
      ))

      // Distinct values are returned in a Set
      _ <- Entity.i.a1.zonedDateTime(distinct).query.get.map(_ ==> List(
        (1, Set(zonedDateTime1)),
        (2, Set(zonedDateTime2, zonedDateTime3)),
      ))

      _ <- Entity.zonedDateTime(distinct).query.get.map(_.head ==> Set(
        zonedDateTime1, zonedDateTime2, zonedDateTime3
      ))

      // Include aggregated attribute too (possible but will always be the same)
      _ <- Entity.zonedDateTime.a1.zonedDateTime(distinct).query.get.map(_ ==> List(
        (zonedDateTime1, Set(zonedDateTime1)),
        (zonedDateTime2, Set(zonedDateTime2)),
        (zonedDateTime3, Set(zonedDateTime3)),
      ))
      _ <- Entity.zonedDateTime(distinct).zonedDateTime.a1.query.get.map(_ ==> List(
        (Set(zonedDateTime1), zonedDateTime1),
        (Set(zonedDateTime2), zonedDateTime2),
        (Set(zonedDateTime3), zonedDateTime3),
      ))
    } yield ()
  }


  "min" - types {
    val (a, b) = ((1, zonedDateTime1), (1, zonedDateTime2))
    for {
      _ <- Entity.i.zonedDateTime.insert(a, b).transact

      // 1 attribute
      _ <- Entity.zonedDateTime(min).query.get.map(_ ==> List(zonedDateTime1))

      _ <- Entity.zonedDateTime(min)(zonedDateTime1).query.get.map(_ ==> List(zonedDateTime1))
      _ <- Entity.zonedDateTime(min)(zonedDateTime2).query.get.map(_ ==> List())

      _ <- Entity.zonedDateTime(min).not(zonedDateTime1).query.get.map(_ ==> List())
      _ <- Entity.zonedDateTime(min).not(zonedDateTime2).query.get.map(_ ==> List(zonedDateTime1))

      _ <- Entity.zonedDateTime(min).<(zonedDateTime1).query.get.map(_ ==> List())
      _ <- Entity.zonedDateTime(min).<(zonedDateTime2).query.get.map(_ ==> List(zonedDateTime1))

      _ <- Entity.zonedDateTime(min).<=(zonedDateTime0).query.get.map(_ ==> List())
      _ <- Entity.zonedDateTime(min).<=(zonedDateTime1).query.get.map(_ ==> List(zonedDateTime1))

      _ <- Entity.zonedDateTime(min).>(zonedDateTime1).query.get.map(_ ==> List())
      _ <- Entity.zonedDateTime(min).>(zonedDateTime0).query.get.map(_ ==> List(zonedDateTime1))

      _ <- Entity.zonedDateTime(min).>=(zonedDateTime1).query.get.map(_ ==> List(zonedDateTime1))
      _ <- Entity.zonedDateTime(min).>=(zonedDateTime2).query.get.map(_ ==> List())

      // n attributes
      _ <- Entity.i.zonedDateTime(min).query.get.map(_ ==> List(a))

      _ <- Entity.i.zonedDateTime(min)(zonedDateTime1).query.get.map(_ ==> List(a))
      _ <- Entity.i.zonedDateTime(min)(zonedDateTime2).query.get.map(_ ==> List())

      _ <- Entity.i.zonedDateTime(min).not(zonedDateTime1).query.get.map(_ ==> List())
      _ <- Entity.i.zonedDateTime(min).not(zonedDateTime2).query.get.map(_ ==> List(a))

      _ <- Entity.i.zonedDateTime(min).<(zonedDateTime1).query.get.map(_ ==> List())
      _ <- Entity.i.zonedDateTime(min).<(zonedDateTime2).query.get.map(_ ==> List(a))

      _ <- Entity.i.zonedDateTime(min).<=(zonedDateTime0).query.get.map(_ ==> List())
      _ <- Entity.i.zonedDateTime(min).<=(zonedDateTime1).query.get.map(_ ==> List(a))

      _ <- Entity.i.zonedDateTime(min).>(zonedDateTime1).query.get.map(_ ==> List())
      _ <- Entity.i.zonedDateTime(min).>(zonedDateTime0).query.get.map(_ ==> List(a))

      _ <- Entity.i.zonedDateTime(min).>=(zonedDateTime1).query.get.map(_ ==> List(a))
      _ <- Entity.i.zonedDateTime(min).>=(zonedDateTime2).query.get.map(_ ==> List())

      // Include aggregated attribute too (possible but will always be the same)
      _ <- Entity.zonedDateTime.a1.zonedDateTime(min).query.get.map(_ ==> List(
        (zonedDateTime1, zonedDateTime1),
        (zonedDateTime2, zonedDateTime2),
      ))
      _ <- Entity.zonedDateTime(min).zonedDateTime.a1.query.get.map(_ ==> List(
        (zonedDateTime1, zonedDateTime1),
        (zonedDateTime2, zonedDateTime2),
      ))
    } yield ()
  }


  "max" - types {
    val (a, b) = ((1, zonedDateTime1), (1, zonedDateTime2))
    for {
      _ <- Entity.i.zonedDateTime.insert(a, b).transact

      // 1 attribute
      _ <- Entity.zonedDateTime(max).query.get.map(_ ==> List(zonedDateTime2))

      _ <- Entity.zonedDateTime(max)(zonedDateTime2).query.get.map(_ ==> List(zonedDateTime2))
      _ <- Entity.zonedDateTime(max)(zonedDateTime1).query.get.map(_ ==> List())

      _ <- Entity.zonedDateTime(max).not(zonedDateTime2).query.get.map(_ ==> List())
      _ <- Entity.zonedDateTime(max).not(zonedDateTime1).query.get.map(_ ==> List(zonedDateTime2))

      _ <- Entity.zonedDateTime(max).<(zonedDateTime2).query.get.map(_ ==> List())
      _ <- Entity.zonedDateTime(max).<(zonedDateTime3).query.get.map(_ ==> List(zonedDateTime2))

      _ <- Entity.zonedDateTime(max).<=(zonedDateTime1).query.get.map(_ ==> List())
      _ <- Entity.zonedDateTime(max).<=(zonedDateTime2).query.get.map(_ ==> List(zonedDateTime2))

      _ <- Entity.zonedDateTime(max).>(zonedDateTime2).query.get.map(_ ==> List())
      _ <- Entity.zonedDateTime(max).>(zonedDateTime1).query.get.map(_ ==> List(zonedDateTime2))

      _ <- Entity.zonedDateTime(max).>=(zonedDateTime2).query.get.map(_ ==> List(zonedDateTime2))
      _ <- Entity.zonedDateTime(max).>=(zonedDateTime3).query.get.map(_ ==> List())

      // n attributes
      _ <- Entity.i.zonedDateTime(max).query.get.map(_ ==> List(b))

      _ <- Entity.i.zonedDateTime(max)(zonedDateTime2).query.get.map(_ ==> List(b))
      _ <- Entity.i.zonedDateTime(max)(zonedDateTime1).query.get.map(_ ==> List())

      _ <- Entity.i.zonedDateTime(max).not(zonedDateTime2).query.get.map(_ ==> List())
      _ <- Entity.i.zonedDateTime(max).not(zonedDateTime1).query.get.map(_ ==> List(b))

      _ <- Entity.i.zonedDateTime(max).<(zonedDateTime2).query.get.map(_ ==> List())
      _ <- Entity.i.zonedDateTime(max).<(zonedDateTime3).query.get.map(_ ==> List(b))

      _ <- Entity.i.zonedDateTime(max).<=(zonedDateTime1).query.get.map(_ ==> List())
      _ <- Entity.i.zonedDateTime(max).<=(zonedDateTime2).query.get.map(_ ==> List(b))

      _ <- Entity.i.zonedDateTime(max).>(zonedDateTime2).query.get.map(_ ==> List())
      _ <- Entity.i.zonedDateTime(max).>(zonedDateTime1).query.get.map(_ ==> List(b))

      _ <- Entity.i.zonedDateTime(max).>=(zonedDateTime2).query.get.map(_ ==> List(b))
      _ <- Entity.i.zonedDateTime(max).>=(zonedDateTime3).query.get.map(_ ==> List())

      // Include aggregated attribute too (possible but will always be the same)
      _ <- Entity.zonedDateTime.a1.zonedDateTime(max).query.get.map(_ ==> List(
        (zonedDateTime1, zonedDateTime1),
        (zonedDateTime2, zonedDateTime2),
      ))
      _ <- Entity.zonedDateTime(max).zonedDateTime.a1.query.get.map(_ ==> List(
        (zonedDateTime1, zonedDateTime1),
        (zonedDateTime2, zonedDateTime2),
      ))
    } yield ()
  }


  "min/max" - types {
    for {
      _ <- Entity.i.zonedDateTime.insert(
        (1, zonedDateTime1),
        (1, zonedDateTime2),
        (2, zonedDateTime3),
        (2, zonedDateTime4),
      ).transact

      _ <- Entity.zonedDateTime(min).zonedDateTime(max).query.get.map(_ ==> List((zonedDateTime1, zonedDateTime4)))

      _ <- Entity.zonedDateTime(min)(zonedDateTime1).zonedDateTime(max)(zonedDateTime4).query.get.map(_ ==> List((zonedDateTime1, zonedDateTime4)))
      _ <- Entity.zonedDateTime(min)(zonedDateTime1).zonedDateTime(max)(zonedDateTime5).query.get.map(_ ==> List())

      _ <- Entity.zonedDateTime(min).not(zonedDateTime2).zonedDateTime(max).not(zonedDateTime3).query.get.map(_ ==> List((zonedDateTime1, zonedDateTime4)))
      _ <- Entity.zonedDateTime(min).not(zonedDateTime2).zonedDateTime(max).not(zonedDateTime4).query.get.map(_ ==> List())

      _ <- Entity.zonedDateTime(min).<(zonedDateTime2).zonedDateTime(max).>(zonedDateTime3).query.get.map(_ ==> List((zonedDateTime1, zonedDateTime4)))
      _ <- Entity.zonedDateTime(min).<(zonedDateTime2).zonedDateTime(max).>(zonedDateTime4).query.get.map(_ ==> List())

      _ <- Entity.zonedDateTime(min).<=(zonedDateTime1).zonedDateTime(max).>=(zonedDateTime4).query.get.map(_ ==> List((zonedDateTime1, zonedDateTime4)))
      _ <- Entity.zonedDateTime(min).<=(zonedDateTime1).zonedDateTime(max).>=(zonedDateTime5).query.get.map(_ ==> List())
    } yield ()
  }


  "min/max n" - types {
    for {
      _ <- Entity.i.zonedDateTime.insert(
        (1, zonedDateTime1),
        (1, zonedDateTime2),
        (1, zonedDateTime3),
        (2, zonedDateTime4),
        (2, zonedDateTime5),
        (2, zonedDateTime6),
        (2, zonedDateTime6), // (make sure grouped values coalesce)
      ).transact

      _ <- Entity.zonedDateTime(min(1)).query.get.map(_ ==> List(Set(zonedDateTime1)))
      _ <- Entity.zonedDateTime(min(2)).query.get.map(_ ==> List(Set(zonedDateTime1, zonedDateTime2)))

      _ <- Entity.zonedDateTime(max(1)).query.get.map(_ ==> List(Set(zonedDateTime6)))
      _ <- Entity.zonedDateTime(max(2)).query.get.map(_ ==> List(Set(zonedDateTime5, zonedDateTime6)))

      _ <- Entity.i.a1.zonedDateTime(min(2)).query.get.map(_ ==> List(
        (1, Set(zonedDateTime1, zonedDateTime2)),
        (2, Set(zonedDateTime4, zonedDateTime5))
      ))

      _ <- Entity.i.a1.zonedDateTime(max(2)).query.get.map(_ ==> List(
        (1, Set(zonedDateTime2, zonedDateTime3)),
        (2, Set(zonedDateTime5, zonedDateTime6))
      ))

      _ <- Entity.i.a1.zonedDateTime(min(2)).zonedDateTime(max(2)).query.get.map(_ ==> List(
        (1, Set(zonedDateTime1, zonedDateTime2), Set(zonedDateTime2, zonedDateTime3)),
        (2, Set(zonedDateTime4, zonedDateTime5), Set(zonedDateTime5, zonedDateTime6))
      ))

      // Include aggregated attribute too (possible but will always be the same)
      _ <- Entity.zonedDateTime.a1.zonedDateTime(min(2)).query.get.map(_ ==> List(
        (zonedDateTime1, Set(zonedDateTime1)),
        (zonedDateTime2, Set(zonedDateTime2)),
        (zonedDateTime3, Set(zonedDateTime3)),
        (zonedDateTime4, Set(zonedDateTime4)),
        (zonedDateTime5, Set(zonedDateTime5)),
        (zonedDateTime6, Set(zonedDateTime6)),
      ))
      _ <- Entity.zonedDateTime(min(2)).zonedDateTime.a1.query.get.map(_ ==> List(
        (Set(zonedDateTime1), zonedDateTime1),
        (Set(zonedDateTime2), zonedDateTime2),
        (Set(zonedDateTime3), zonedDateTime3),
        (Set(zonedDateTime4), zonedDateTime4),
        (Set(zonedDateTime5), zonedDateTime5),
        (Set(zonedDateTime6), zonedDateTime6),
      ))

      _ <- Entity.zonedDateTime.a1.zonedDateTime(max(2)).query.get.map(_ ==> List(
        (zonedDateTime1, Set(zonedDateTime1)),
        (zonedDateTime2, Set(zonedDateTime2)),
        (zonedDateTime3, Set(zonedDateTime3)),
        (zonedDateTime4, Set(zonedDateTime4)),
        (zonedDateTime5, Set(zonedDateTime5)),
        (zonedDateTime6, Set(zonedDateTime6)),
      ))
      _ <- Entity.zonedDateTime(max(2)).zonedDateTime.a1.query.get.map(_ ==> List(
        (Set(zonedDateTime1), zonedDateTime1),
        (Set(zonedDateTime2), zonedDateTime2),
        (Set(zonedDateTime3), zonedDateTime3),
        (Set(zonedDateTime4), zonedDateTime4),
        (Set(zonedDateTime5), zonedDateTime5),
        (Set(zonedDateTime6), zonedDateTime6),
      ))
    } yield ()
  }


  "sample" - types {
    val all      = Set(zonedDateTime1, zonedDateTime2, zonedDateTime3)
    val allPairs = List((1, zonedDateTime1), (2, zonedDateTime2), (3, zonedDateTime3))
    for {
      _ <- Entity.i.zonedDateTime.insert(allPairs).transact

      // 1 attribute
      _ <- Entity.zonedDateTime(sample).query.get.map(res => all.contains(res.head) ==> true)

      // 1 attribute
      _ <- Entity.i.zonedDateTime(sample).query.get.map(res => allPairs.contains(res.head) ==> true)
    } yield ()
  }

  "sample ops" - types {
    if (Seq("mariadb", "mysql").contains(database)) {
      Entity.zonedDateTime(sample)(zonedDateTime1).query.get
        .map(_ ==> "Unexpected success").recover { case ModelError(err) =>
          err ==> "Operations on sample not implemented for this database."
        }
    } else {
      val all       = Set(zonedDateTime1, zonedDateTime2, zonedDateTime3)
      val (a, b, c) = ((1, zonedDateTime1), (2, zonedDateTime2), (3, zonedDateTime3))
      val allPairs  = List(a, b, c)
      for {
        _ <- Entity.i.zonedDateTime.insert(allPairs).transact

        // 1 attribute
        // Checking for equality on a sample doesn't make sense
        // _ <- Entity.zonedDateTime(sample)(zonedDateTime2).query.get.map(res => all.contains(res.head) ==> true)
        // If you want a specific value, this would be the natural query
        _ <- Entity.zonedDateTime(zonedDateTime2).query.get.map(_ ==> List(zonedDateTime2))

        _ <- Entity.zonedDateTime(sample).not(zonedDateTime2).query.get.map { res =>
          List(zonedDateTime1, zonedDateTime3).contains(res.head) ==> true
          (res.head == zonedDateTime2) ==> false
        }
        _ <- Entity.zonedDateTime(sample).<(zonedDateTime3).query.get.map { res =>
          List(zonedDateTime1, zonedDateTime2).contains(res.head) ==> true
          (res.head == zonedDateTime3) ==> false
        }
        _ <- Entity.zonedDateTime(sample).<=(zonedDateTime2).query.get.map { res =>
          List(zonedDateTime1, zonedDateTime2).contains(res.head) ==> true
          (res.head == zonedDateTime3) ==> false
        }
        _ <- Entity.zonedDateTime(sample).>(zonedDateTime1).query.get.map { res =>
          List(zonedDateTime2, zonedDateTime3).contains(res.head) ==> true
          (res.head == zonedDateTime1) ==> false
        }
        _ <- Entity.zonedDateTime(sample).>=(zonedDateTime2).query.get.map { res =>
          List(zonedDateTime2, zonedDateTime3).contains(res.head) ==> true
          (res.head == zonedDateTime1) ==> false
        }

        // 1 attribute
        _ <- Entity.i.zonedDateTime(sample).not(zonedDateTime2).query.get.map { res =>
          List(a, c).contains(res.head) ==> true
          (res.head == b) ==> false
        }
        _ <- Entity.i.zonedDateTime(sample).<(zonedDateTime3).query.get.map { res =>
          List(a, b).contains(res.head) ==> true
          (res.head == c) ==> false
        }
        _ <- Entity.i.zonedDateTime(sample).<=(zonedDateTime2).query.get.map { res =>
          List(a, b).contains(res.head) ==> true
          (res.head == c) ==> false
        }
        _ <- Entity.i.zonedDateTime(sample).>(zonedDateTime1).query.get.map { res =>
          List(b, c).contains(res.head) ==> true
          (res.head == a) ==> false
        }
        _ <- Entity.i.zonedDateTime(sample).>=(zonedDateTime2).query.get.map { res =>
          List(b, c).contains(res.head) ==> true
          (res.head == a) ==> false
        }
      } yield ()
    }
  }


  "samples(n)" - types {
    val all = Set(zonedDateTime1, zonedDateTime2, zonedDateTime3)
    for {
      _ <- Entity.zonedDateTime.insert(List(zonedDateTime1, zonedDateTime2, zonedDateTime3)).transact
      _ <- Entity.zonedDateTime(sample(1)).query.get.map(res => all.intersect(res.head).nonEmpty ==> true)
      _ <- Entity.zonedDateTime(sample(2)).query.get.map(res => all.intersect(res.head).nonEmpty ==> true)
    } yield ()
  }


  "count" - types {
    val (a, b) = ((1, 1), (2, 3))
    for {
      _ <- Entity.i.zonedDateTime.insert(List(
        (1, zonedDateTime1),
        (2, zonedDateTime2),
        (2, zonedDateTime2),
        (2, zonedDateTime3),
      )).transact

      // 1 attribute
      _ <- Entity.zonedDateTime(count).query.get.map(_ ==> List(4))

      _ <- Entity.zonedDateTime(count)(3).query.get.map(_ ==> List())
      _ <- Entity.zonedDateTime(count)(4).query.get.map(_ ==> List(4))

      _ <- Entity.zonedDateTime(count).not(3).query.get.map(_ ==> List(4))
      _ <- Entity.zonedDateTime(count).not(4).query.get.map(_ ==> List())

      _ <- Entity.zonedDateTime(count).<(5).query.get.map(_ ==> List(4))
      _ <- Entity.zonedDateTime(count).<(4).query.get.map(_ ==> List())

      _ <- Entity.zonedDateTime(count).<=(4).query.get.map(_ ==> List(4))
      _ <- Entity.zonedDateTime(count).<=(3).query.get.map(_ ==> List())

      _ <- Entity.zonedDateTime(count).>(3).query.get.map(_ ==> List(4))
      _ <- Entity.zonedDateTime(count).>(4).query.get.map(_ ==> List())

      _ <- Entity.zonedDateTime(count).>=(4).query.get.map(_ ==> List(4))
      _ <- Entity.zonedDateTime(count).>=(5).query.get.map(_ ==> List())

      // n attributes
      _ <- Entity.i.a1.zonedDateTime(count).query.get.map(_ ==> List(a, b))

      _ <- Entity.i.a1.zonedDateTime(count)(3).query.get.map(_ ==> List(b))
      _ <- Entity.i.a1.zonedDateTime(count)(2).query.get.map(_ ==> List())

      _ <- Entity.i.a1.zonedDateTime(count).not(3).query.get.map(_ ==> List(a))
      _ <- Entity.i.a1.zonedDateTime(count).not(2).query.get.map(_ ==> List(a, b))

      _ <- Entity.i.a1.zonedDateTime(count).<(3).query.get.map(_ ==> List(a))
      _ <- Entity.i.a1.zonedDateTime(count).<(4).query.get.map(_ ==> List(a, b))

      _ <- Entity.i.a1.zonedDateTime(count).<=(3).query.get.map(_ ==> List(a, b))
      _ <- Entity.i.a1.zonedDateTime(count).<=(2).query.get.map(_ ==> List(a))

      _ <- Entity.i.a1.zonedDateTime(count).>(2).query.get.map(_ ==> List(b))
      _ <- Entity.i.a1.zonedDateTime(count).>(3).query.get.map(_ ==> List())

      _ <- Entity.i.a1.zonedDateTime(count).>=(3).query.get.map(_ ==> List(b))
      _ <- Entity.i.a1.zonedDateTime(count).>=(4).query.get.map(_ ==> List())

      // Include aggregated attribute too
      _ <- Entity.zonedDateTime.a1.zonedDateTime(count).query.get.map(_ ==> List(
        (zonedDateTime1, 1),
        (zonedDateTime2, 2),
        (zonedDateTime3, 1),
      ))
      _ <- Entity.zonedDateTime(count).zonedDateTime.a1.query.get.map(_ ==> List(
        (1, zonedDateTime1),
        (2, zonedDateTime2),
        (1, zonedDateTime3),
      ))
    } yield ()
  }


  "countDistinct" - types {
    val (a, b) = ((1, 1), (2, 2))
    for {
      _ <- Entity.i.zonedDateTime.insert(List(
        (1, zonedDateTime1),
        (2, zonedDateTime2),
        (2, zonedDateTime2),
        (2, zonedDateTime3),
      )).transact

      // 1 attribute
      _ <- Entity.zonedDateTime(countDistinct).query.get.map(_ ==> List(3))

      _ <- Entity.zonedDateTime(countDistinct)(2).query.get.map(_ ==> List())
      _ <- Entity.zonedDateTime(countDistinct)(3).query.get.map(_ ==> List(3))

      _ <- Entity.zonedDateTime(countDistinct).not(2).query.get.map(_ ==> List(3))
      _ <- Entity.zonedDateTime(countDistinct).not(3).query.get.map(_ ==> List())

      _ <- Entity.zonedDateTime(countDistinct).<(4).query.get.map(_ ==> List(3))
      _ <- Entity.zonedDateTime(countDistinct).<(3).query.get.map(_ ==> List())

      _ <- Entity.zonedDateTime(countDistinct).<=(3).query.get.map(_ ==> List(3))
      _ <- Entity.zonedDateTime(countDistinct).<=(2).query.get.map(_ ==> List())

      _ <- Entity.zonedDateTime(countDistinct).>(2).query.get.map(_ ==> List(3))
      _ <- Entity.zonedDateTime(countDistinct).>(3).query.get.map(_ ==> List())

      _ <- Entity.zonedDateTime(countDistinct).>=(3).query.get.map(_ ==> List(3))
      _ <- Entity.zonedDateTime(countDistinct).>=(4).query.get.map(_ ==> List())

      // n attributes
      _ <- Entity.i.a1.zonedDateTime(countDistinct).query.get.map(_ ==> List(a, b))

      _ <- Entity.i.a1.zonedDateTime(countDistinct)(2).query.get.map(_ ==> List(b))
      _ <- Entity.i.a1.zonedDateTime(countDistinct)(3).query.get.map(_ ==> List())

      _ <- Entity.i.a1.zonedDateTime(countDistinct).not(2).query.get.map(_ ==> List(a))
      _ <- Entity.i.a1.zonedDateTime(countDistinct).not(3).query.get.map(_ ==> List(a, b))

      _ <- Entity.i.a1.zonedDateTime(countDistinct).<(2).query.get.map(_ ==> List(a))
      _ <- Entity.i.a1.zonedDateTime(countDistinct).<(3).query.get.map(_ ==> List(a, b))

      _ <- Entity.i.a1.zonedDateTime(countDistinct).<=(2).query.get.map(_ ==> List(a, b))
      _ <- Entity.i.a1.zonedDateTime(countDistinct).<=(1).query.get.map(_ ==> List(a))

      _ <- Entity.i.a1.zonedDateTime(countDistinct).>(1).query.get.map(_ ==> List(b))
      _ <- Entity.i.a1.zonedDateTime(countDistinct).>(2).query.get.map(_ ==> List())

      _ <- Entity.i.a1.zonedDateTime(countDistinct).>=(2).query.get.map(_ ==> List(b))
      _ <- Entity.i.a1.zonedDateTime(countDistinct).>=(3).query.get.map(_ ==> List())

      // Include aggregated attribute too
      _ <- Entity.zonedDateTime.a1.zonedDateTime(countDistinct).query.get.map(_ ==> List(
        (zonedDateTime1, 1),
        (zonedDateTime2, 1),
        (zonedDateTime3, 1),
      ))
      _ <- Entity.zonedDateTime(countDistinct).zonedDateTime.a1.query.get.map(_ ==> List(
        (1, zonedDateTime1),
        (1, zonedDateTime2),
        (1, zonedDateTime3),
      ))
    } yield ()
  }
}