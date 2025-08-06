// GENERATED CODE ********************************
package molecule.db.compliance.test.aggregation.any

import java.util.Date
import molecule.core.error.ModelError
import molecule.core.setup.{MUnit, TestUtils}
import molecule.db.common.api.Api_async
import molecule.db.common.spi.Spi_async
import molecule.db.common.util.Executor.*
import molecule.db.compliance.domains.dsl.Types.*
import molecule.db.compliance.setup.DbProviders
import org.scalactic.Equality

case class Aggr_Date_(
  suite: MUnit,
  api: Api_async & Spi_async & DbProviders
) extends TestUtils {

  import api.*
  import suite.*

  "distinct" - types {
    for {
      _ <- Entity.i.date.insert(List(
        (1, date1),
        (2, date2),
        (2, date2),
        (2, date3),
      )).transact

      _ <- Entity.i.date.a1.query.get.map(_ ==> List(
        (1, date1),
        (2, date2), // 2 rows coalesced
        (2, date3),
      ))

      // Distinct values are returned in a Set
      _ <- Entity.i.a1.date(distinct).query.get.map(_ ==> List(
        (1, Set(date1)),
        (2, Set(date2, date3)),
      ))

      _ <- Entity.date(distinct).query.get.map(_.head ==> Set(
        date1, date2, date3
      ))

      // Include aggregated attribute too (possible but will always be the same)
      _ <- Entity.date.a1.date(distinct).query.get.map(_ ==> List(
        (date1, Set(date1)),
        (date2, Set(date2)),
        (date3, Set(date3)),
      ))
      _ <- Entity.date(distinct).date.a1.query.get.map(_ ==> List(
        (Set(date1), date1),
        (Set(date2), date2),
        (Set(date3), date3),
      ))
    } yield ()
  }


  "min" - types {
    val (a, b) = ((1, date1), (1, date2))
    for {
      _ <- Entity.i.date.insert(a, b).transact

      // 1 attribute
      _ <- Entity.date(min).query.get.map(_ ==> List(date1))

      _ <- Entity.date(min)(date1).query.get.map(_ ==> List(date1))
      _ <- Entity.date(min)(date2).query.get.map(_ ==> List())

      _ <- Entity.date(min).not(date1).query.get.map(_ ==> List())
      _ <- Entity.date(min).not(date2).query.get.map(_ ==> List(date1))

      _ <- Entity.date(min).<(date1).query.get.map(_ ==> List())
      _ <- Entity.date(min).<(date2).query.get.map(_ ==> List(date1))

      _ <- Entity.date(min).<=(date0).query.get.map(_ ==> List())
      _ <- Entity.date(min).<=(date1).query.get.map(_ ==> List(date1))

      _ <- Entity.date(min).>(date1).query.get.map(_ ==> List())
      _ <- Entity.date(min).>(date0).query.get.map(_ ==> List(date1))

      _ <- Entity.date(min).>=(date1).query.get.map(_ ==> List(date1))
      _ <- Entity.date(min).>=(date2).query.get.map(_ ==> List())

      // n attributes
      _ <- Entity.i.date(min).query.get.map(_ ==> List(a))

      _ <- Entity.i.date(min)(date1).query.get.map(_ ==> List(a))
      _ <- Entity.i.date(min)(date2).query.get.map(_ ==> List())

      _ <- Entity.i.date(min).not(date1).query.get.map(_ ==> List())
      _ <- Entity.i.date(min).not(date2).query.get.map(_ ==> List(a))

      _ <- Entity.i.date(min).<(date1).query.get.map(_ ==> List())
      _ <- Entity.i.date(min).<(date2).query.get.map(_ ==> List(a))

      _ <- Entity.i.date(min).<=(date0).query.get.map(_ ==> List())
      _ <- Entity.i.date(min).<=(date1).query.get.map(_ ==> List(a))

      _ <- Entity.i.date(min).>(date1).query.get.map(_ ==> List())
      _ <- Entity.i.date(min).>(date0).query.get.map(_ ==> List(a))

      _ <- Entity.i.date(min).>=(date1).query.get.map(_ ==> List(a))
      _ <- Entity.i.date(min).>=(date2).query.get.map(_ ==> List())

      // Include aggregated attribute too (possible but will always be the same)
      _ <- Entity.date.a1.date(min).query.get.map(_ ==> List(
        (date1, date1),
        (date2, date2),
      ))
      _ <- Entity.date(min).date.a1.query.get.map(_ ==> List(
        (date1, date1),
        (date2, date2),
      ))
    } yield ()
  }


  "max" - types {
    val (a, b) = ((1, date1), (1, date2))
    for {
      _ <- Entity.i.date.insert(a, b).transact

      // 1 attribute
      _ <- Entity.date(max).query.get.map(_ ==> List(date2))

      _ <- Entity.date(max)(date2).query.get.map(_ ==> List(date2))
      _ <- Entity.date(max)(date1).query.get.map(_ ==> List())

      _ <- Entity.date(max).not(date2).query.get.map(_ ==> List())
      _ <- Entity.date(max).not(date1).query.get.map(_ ==> List(date2))

      _ <- Entity.date(max).<(date2).query.get.map(_ ==> List())
      _ <- Entity.date(max).<(date3).query.get.map(_ ==> List(date2))

      _ <- Entity.date(max).<=(date1).query.get.map(_ ==> List())
      _ <- Entity.date(max).<=(date2).query.get.map(_ ==> List(date2))

      _ <- Entity.date(max).>(date2).query.get.map(_ ==> List())
      _ <- Entity.date(max).>(date1).query.get.map(_ ==> List(date2))

      _ <- Entity.date(max).>=(date2).query.get.map(_ ==> List(date2))
      _ <- Entity.date(max).>=(date3).query.get.map(_ ==> List())

      // n attributes
      _ <- Entity.i.date(max).query.get.map(_ ==> List(b))

      _ <- Entity.i.date(max)(date2).query.get.map(_ ==> List(b))
      _ <- Entity.i.date(max)(date1).query.get.map(_ ==> List())

      _ <- Entity.i.date(max).not(date2).query.get.map(_ ==> List())
      _ <- Entity.i.date(max).not(date1).query.get.map(_ ==> List(b))

      _ <- Entity.i.date(max).<(date2).query.get.map(_ ==> List())
      _ <- Entity.i.date(max).<(date3).query.get.map(_ ==> List(b))

      _ <- Entity.i.date(max).<=(date1).query.get.map(_ ==> List())
      _ <- Entity.i.date(max).<=(date2).query.get.map(_ ==> List(b))

      _ <- Entity.i.date(max).>(date2).query.get.map(_ ==> List())
      _ <- Entity.i.date(max).>(date1).query.get.map(_ ==> List(b))

      _ <- Entity.i.date(max).>=(date2).query.get.map(_ ==> List(b))
      _ <- Entity.i.date(max).>=(date3).query.get.map(_ ==> List())

      // Include aggregated attribute too (possible but will always be the same)
      _ <- Entity.date.a1.date(max).query.get.map(_ ==> List(
        (date1, date1),
        (date2, date2),
      ))
      _ <- Entity.date(max).date.a1.query.get.map(_ ==> List(
        (date1, date1),
        (date2, date2),
      ))
    } yield ()
  }


  "min/max" - types {
    for {
      _ <- Entity.i.date.insert(
        (1, date1),
        (1, date2),
        (2, date3),
        (2, date4),
      ).transact

      _ <- Entity.date(min).date(max).query.get.map(_ ==> List((date1, date4)))

      _ <- Entity.date(min)(date1).date(max)(date4).query.get.map(_ ==> List((date1, date4)))
      _ <- Entity.date(min)(date1).date(max)(date5).query.get.map(_ ==> List())

      _ <- Entity.date(min).not(date2).date(max).not(date3).query.get.map(_ ==> List((date1, date4)))
      _ <- Entity.date(min).not(date2).date(max).not(date4).query.get.map(_ ==> List())

      _ <- Entity.date(min).<(date2).date(max).>(date3).query.get.map(_ ==> List((date1, date4)))
      _ <- Entity.date(min).<(date2).date(max).>(date4).query.get.map(_ ==> List())

      _ <- Entity.date(min).<=(date1).date(max).>=(date4).query.get.map(_ ==> List((date1, date4)))
      _ <- Entity.date(min).<=(date1).date(max).>=(date5).query.get.map(_ ==> List())
    } yield ()
  }


  "min/max n" - types {
    for {
      _ <- Entity.i.date.insert(
        (1, date1),
        (1, date2),
        (1, date3),
        (2, date4),
        (2, date5),
        (2, date6),
        (2, date6), // (make sure grouped values coalesce)
      ).transact

      _ <- Entity.date(min(1)).query.get.map(_ ==> List(Set(date1)))
      _ <- Entity.date(min(2)).query.get.map(_ ==> List(Set(date1, date2)))

      _ <- Entity.date(max(1)).query.get.map(_ ==> List(Set(date6)))
      _ <- Entity.date(max(2)).query.get.map(_ ==> List(Set(date5, date6)))

      _ <- Entity.i.a1.date(min(2)).query.get.map(_ ==> List(
        (1, Set(date1, date2)),
        (2, Set(date4, date5))
      ))

      _ <- Entity.i.a1.date(max(2)).query.get.map(_ ==> List(
        (1, Set(date2, date3)),
        (2, Set(date5, date6))
      ))

      _ <- Entity.i.a1.date(min(2)).date(max(2)).query.get.map(_ ==> List(
        (1, Set(date1, date2), Set(date2, date3)),
        (2, Set(date4, date5), Set(date5, date6))
      ))

      // Include aggregated attribute too (possible but will always be the same)
      _ <- Entity.date.a1.date(min(2)).query.get.map(_ ==> List(
        (date1, Set(date1)),
        (date2, Set(date2)),
        (date3, Set(date3)),
        (date4, Set(date4)),
        (date5, Set(date5)),
        (date6, Set(date6)),
      ))
      _ <- Entity.date(min(2)).date.a1.query.get.map(_ ==> List(
        (Set(date1), date1),
        (Set(date2), date2),
        (Set(date3), date3),
        (Set(date4), date4),
        (Set(date5), date5),
        (Set(date6), date6),
      ))

      _ <- Entity.date.a1.date(max(2)).query.get.map(_ ==> List(
        (date1, Set(date1)),
        (date2, Set(date2)),
        (date3, Set(date3)),
        (date4, Set(date4)),
        (date5, Set(date5)),
        (date6, Set(date6)),
      ))
      _ <- Entity.date(max(2)).date.a1.query.get.map(_ ==> List(
        (Set(date1), date1),
        (Set(date2), date2),
        (Set(date3), date3),
        (Set(date4), date4),
        (Set(date5), date5),
        (Set(date6), date6),
      ))
    } yield ()
  }


  "sample" - types {
    val all      = Set(date1, date2, date3)
    val allPairs = List((1, date1), (2, date2), (3, date3))
    for {
      _ <- Entity.i.date.insert(allPairs).transact

      // 1 attribute
      _ <- Entity.date(sample).query.get.map(res => all.contains(res.head) ==> true)

      // 1 attribute
      _ <- Entity.i.date(sample).query.get.map(res => allPairs.contains(res.head) ==> true)
    } yield ()
  }

  "sample ops" - types {
    if (Seq("mariadb", "mysql").contains(database)) {
      Entity.date(sample)(date1).query.get
        .map(_ ==> "Unexpected success").recover { case ModelError(err) =>
          err ==> "Operations on sample not implemented for this database."
        }
    } else {
      val all       = Set(date1, date2, date3)
      val (a, b, c) = ((1, date1), (2, date2), (3, date3))
      val allPairs  = List(a, b, c)
      for {
        _ <- Entity.i.date.insert(allPairs).transact

        // 1 attribute
        // Checking for equality on a sample doesn't make sense
        // _ <- Entity.date(sample)(date2).query.get.map(res => all.contains(res.head) ==> true)
        // If you want a specific value, this would be the natural query
        _ <- Entity.date(date2).query.get.map(_ ==> List(date2))

        _ <- Entity.date(sample).not(date2).query.get.map { res =>
          List(date1, date3).contains(res.head) ==> true
          (res.head == date2) ==> false
        }
        _ <- Entity.date(sample).<(date3).query.get.map { res =>
          List(date1, date2).contains(res.head) ==> true
          (res.head == date3) ==> false
        }
        _ <- Entity.date(sample).<=(date2).query.get.map { res =>
          List(date1, date2).contains(res.head) ==> true
          (res.head == date3) ==> false
        }
        _ <- Entity.date(sample).>(date1).query.get.map { res =>
          List(date2, date3).contains(res.head) ==> true
          (res.head == date1) ==> false
        }
        _ <- Entity.date(sample).>=(date2).query.get.map { res =>
          List(date2, date3).contains(res.head) ==> true
          (res.head == date1) ==> false
        }

        // 1 attribute
        _ <- Entity.i.date(sample).not(date2).query.get.map { res =>
          List(a, c).contains(res.head) ==> true
          (res.head == b) ==> false
        }
        _ <- Entity.i.date(sample).<(date3).query.get.map { res =>
          List(a, b).contains(res.head) ==> true
          (res.head == c) ==> false
        }
        _ <- Entity.i.date(sample).<=(date2).query.get.map { res =>
          List(a, b).contains(res.head) ==> true
          (res.head == c) ==> false
        }
        _ <- Entity.i.date(sample).>(date1).query.get.map { res =>
          List(b, c).contains(res.head) ==> true
          (res.head == a) ==> false
        }
        _ <- Entity.i.date(sample).>=(date2).query.get.map { res =>
          List(b, c).contains(res.head) ==> true
          (res.head == a) ==> false
        }
      } yield ()
    }
  }


  "samples(n)" - types {
    val all = Set(date1, date2, date3)
    for {
      _ <- Entity.date.insert(List(date1, date2, date3)).transact
      _ <- Entity.date(sample(1)).query.get.map(res => all.intersect(res.head).nonEmpty ==> true)
      _ <- Entity.date(sample(2)).query.get.map(res => all.intersect(res.head).nonEmpty ==> true)
    } yield ()
  }


  "count" - types {
    val (a, b) = ((1, 1), (2, 3))
    for {
      _ <- Entity.i.date.insert(List(
        (1, date1),
        (2, date2),
        (2, date2),
        (2, date3),
      )).transact

      // 1 attribute
      _ <- Entity.date(count).query.get.map(_ ==> List(4))

      _ <- Entity.date(count)(3).query.get.map(_ ==> List())
      _ <- Entity.date(count)(4).query.get.map(_ ==> List(4))

      _ <- Entity.date(count).not(3).query.get.map(_ ==> List(4))
      _ <- Entity.date(count).not(4).query.get.map(_ ==> List())

      _ <- Entity.date(count).<(5).query.get.map(_ ==> List(4))
      _ <- Entity.date(count).<(4).query.get.map(_ ==> List())

      _ <- Entity.date(count).<=(4).query.get.map(_ ==> List(4))
      _ <- Entity.date(count).<=(3).query.get.map(_ ==> List())

      _ <- Entity.date(count).>(3).query.get.map(_ ==> List(4))
      _ <- Entity.date(count).>(4).query.get.map(_ ==> List())

      _ <- Entity.date(count).>=(4).query.get.map(_ ==> List(4))
      _ <- Entity.date(count).>=(5).query.get.map(_ ==> List())

      // n attributes
      _ <- Entity.i.a1.date(count).query.get.map(_ ==> List(a, b))

      _ <- Entity.i.a1.date(count)(3).query.get.map(_ ==> List(b))
      _ <- Entity.i.a1.date(count)(2).query.get.map(_ ==> List())

      _ <- Entity.i.a1.date(count).not(3).query.get.map(_ ==> List(a))
      _ <- Entity.i.a1.date(count).not(2).query.get.map(_ ==> List(a, b))

      _ <- Entity.i.a1.date(count).<(3).query.get.map(_ ==> List(a))
      _ <- Entity.i.a1.date(count).<(4).query.get.map(_ ==> List(a, b))

      _ <- Entity.i.a1.date(count).<=(3).query.get.map(_ ==> List(a, b))
      _ <- Entity.i.a1.date(count).<=(2).query.get.map(_ ==> List(a))

      _ <- Entity.i.a1.date(count).>(2).query.get.map(_ ==> List(b))
      _ <- Entity.i.a1.date(count).>(3).query.get.map(_ ==> List())

      _ <- Entity.i.a1.date(count).>=(3).query.get.map(_ ==> List(b))
      _ <- Entity.i.a1.date(count).>=(4).query.get.map(_ ==> List())

      // Include aggregated attribute too
      _ <- Entity.date.a1.date(count).query.get.map(_ ==> List(
        (date1, 1),
        (date2, 2),
        (date3, 1),
      ))
      _ <- Entity.date(count).date.a1.query.get.map(_ ==> List(
        (1, date1),
        (2, date2),
        (1, date3),
      ))
    } yield ()
  }


  "countDistinct" - types {
    val (a, b) = ((1, 1), (2, 2))
    for {
      _ <- Entity.i.date.insert(List(
        (1, date1),
        (2, date2),
        (2, date2),
        (2, date3),
      )).transact

      // 1 attribute
      _ <- Entity.date(countDistinct).query.get.map(_ ==> List(3))

      _ <- Entity.date(countDistinct)(2).query.get.map(_ ==> List())
      _ <- Entity.date(countDistinct)(3).query.get.map(_ ==> List(3))

      _ <- Entity.date(countDistinct).not(2).query.get.map(_ ==> List(3))
      _ <- Entity.date(countDistinct).not(3).query.get.map(_ ==> List())

      _ <- Entity.date(countDistinct).<(4).query.get.map(_ ==> List(3))
      _ <- Entity.date(countDistinct).<(3).query.get.map(_ ==> List())

      _ <- Entity.date(countDistinct).<=(3).query.get.map(_ ==> List(3))
      _ <- Entity.date(countDistinct).<=(2).query.get.map(_ ==> List())

      _ <- Entity.date(countDistinct).>(2).query.get.map(_ ==> List(3))
      _ <- Entity.date(countDistinct).>(3).query.get.map(_ ==> List())

      _ <- Entity.date(countDistinct).>=(3).query.get.map(_ ==> List(3))
      _ <- Entity.date(countDistinct).>=(4).query.get.map(_ ==> List())

      // n attributes
      _ <- Entity.i.a1.date(countDistinct).query.get.map(_ ==> List(a, b))

      _ <- Entity.i.a1.date(countDistinct)(2).query.get.map(_ ==> List(b))
      _ <- Entity.i.a1.date(countDistinct)(3).query.get.map(_ ==> List())

      _ <- Entity.i.a1.date(countDistinct).not(2).query.get.map(_ ==> List(a))
      _ <- Entity.i.a1.date(countDistinct).not(3).query.get.map(_ ==> List(a, b))

      _ <- Entity.i.a1.date(countDistinct).<(2).query.get.map(_ ==> List(a))
      _ <- Entity.i.a1.date(countDistinct).<(3).query.get.map(_ ==> List(a, b))

      _ <- Entity.i.a1.date(countDistinct).<=(2).query.get.map(_ ==> List(a, b))
      _ <- Entity.i.a1.date(countDistinct).<=(1).query.get.map(_ ==> List(a))

      _ <- Entity.i.a1.date(countDistinct).>(1).query.get.map(_ ==> List(b))
      _ <- Entity.i.a1.date(countDistinct).>(2).query.get.map(_ ==> List())

      _ <- Entity.i.a1.date(countDistinct).>=(2).query.get.map(_ ==> List(b))
      _ <- Entity.i.a1.date(countDistinct).>=(3).query.get.map(_ ==> List())

      // Include aggregated attribute too
      _ <- Entity.date.a1.date(countDistinct).query.get.map(_ ==> List(
        (date1, 1),
        (date2, 1),
        (date3, 1),
      ))
      _ <- Entity.date(countDistinct).date.a1.query.get.map(_ ==> List(
        (1, date1),
        (1, date2),
        (1, date3),
      ))
    } yield ()
  }
}