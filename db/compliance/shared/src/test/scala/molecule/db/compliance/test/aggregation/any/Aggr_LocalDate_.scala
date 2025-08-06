// GENERATED CODE ********************************
package molecule.db.compliance.test.aggregation.any

import java.time.LocalDate
import molecule.core.error.ModelError
import molecule.core.setup.{MUnit, TestUtils}
import molecule.db.common.api.Api_async
import molecule.db.common.spi.Spi_async
import molecule.db.common.util.Executor.*
import molecule.db.compliance.domains.dsl.Types.*
import molecule.db.compliance.setup.DbProviders
import org.scalactic.Equality

case class Aggr_LocalDate_(
  suite: MUnit,
  api: Api_async & Spi_async & DbProviders
) extends TestUtils {

  import api.*
  import suite.*

  "distinct" - types {
    for {
      _ <- Entity.i.localDate.insert(List(
        (1, localDate1),
        (2, localDate2),
        (2, localDate2),
        (2, localDate3),
      )).transact

      _ <- Entity.i.localDate.a1.query.get.map(_ ==> List(
        (1, localDate1),
        (2, localDate2), // 2 rows coalesced
        (2, localDate3),
      ))

      // Distinct values are returned in a Set
      _ <- Entity.i.a1.localDate(distinct).query.get.map(_ ==> List(
        (1, Set(localDate1)),
        (2, Set(localDate2, localDate3)),
      ))

      _ <- Entity.localDate(distinct).query.get.map(_.head ==> Set(
        localDate1, localDate2, localDate3
      ))

      // Include aggregated attribute too (possible but will always be the same)
      _ <- Entity.localDate.a1.localDate(distinct).query.get.map(_ ==> List(
        (localDate1, Set(localDate1)),
        (localDate2, Set(localDate2)),
        (localDate3, Set(localDate3)),
      ))
      _ <- Entity.localDate(distinct).localDate.a1.query.get.map(_ ==> List(
        (Set(localDate1), localDate1),
        (Set(localDate2), localDate2),
        (Set(localDate3), localDate3),
      ))
    } yield ()
  }


  "min" - types {
    val (a, b) = ((1, localDate1), (1, localDate2))
    for {
      _ <- Entity.i.localDate.insert(a, b).transact

      // 1 attribute
      _ <- Entity.localDate(min).query.get.map(_ ==> List(localDate1))

      _ <- Entity.localDate(min)(localDate1).query.get.map(_ ==> List(localDate1))
      _ <- Entity.localDate(min)(localDate2).query.get.map(_ ==> List())

      _ <- Entity.localDate(min).not(localDate1).query.get.map(_ ==> List())
      _ <- Entity.localDate(min).not(localDate2).query.get.map(_ ==> List(localDate1))

      _ <- Entity.localDate(min).<(localDate1).query.get.map(_ ==> List())
      _ <- Entity.localDate(min).<(localDate2).query.get.map(_ ==> List(localDate1))

      _ <- Entity.localDate(min).<=(localDate0).query.get.map(_ ==> List())
      _ <- Entity.localDate(min).<=(localDate1).query.get.map(_ ==> List(localDate1))

      _ <- Entity.localDate(min).>(localDate1).query.get.map(_ ==> List())
      _ <- Entity.localDate(min).>(localDate0).query.get.map(_ ==> List(localDate1))

      _ <- Entity.localDate(min).>=(localDate1).query.get.map(_ ==> List(localDate1))
      _ <- Entity.localDate(min).>=(localDate2).query.get.map(_ ==> List())

      // n attributes
      _ <- Entity.i.localDate(min).query.get.map(_ ==> List(a))

      _ <- Entity.i.localDate(min)(localDate1).query.get.map(_ ==> List(a))
      _ <- Entity.i.localDate(min)(localDate2).query.get.map(_ ==> List())

      _ <- Entity.i.localDate(min).not(localDate1).query.get.map(_ ==> List())
      _ <- Entity.i.localDate(min).not(localDate2).query.get.map(_ ==> List(a))

      _ <- Entity.i.localDate(min).<(localDate1).query.get.map(_ ==> List())
      _ <- Entity.i.localDate(min).<(localDate2).query.get.map(_ ==> List(a))

      _ <- Entity.i.localDate(min).<=(localDate0).query.get.map(_ ==> List())
      _ <- Entity.i.localDate(min).<=(localDate1).query.get.map(_ ==> List(a))

      _ <- Entity.i.localDate(min).>(localDate1).query.get.map(_ ==> List())
      _ <- Entity.i.localDate(min).>(localDate0).query.get.map(_ ==> List(a))

      _ <- Entity.i.localDate(min).>=(localDate1).query.get.map(_ ==> List(a))
      _ <- Entity.i.localDate(min).>=(localDate2).query.get.map(_ ==> List())

      // Include aggregated attribute too (possible but will always be the same)
      _ <- Entity.localDate.a1.localDate(min).query.get.map(_ ==> List(
        (localDate1, localDate1),
        (localDate2, localDate2),
      ))
      _ <- Entity.localDate(min).localDate.a1.query.get.map(_ ==> List(
        (localDate1, localDate1),
        (localDate2, localDate2),
      ))
    } yield ()
  }


  "max" - types {
    val (a, b) = ((1, localDate1), (1, localDate2))
    for {
      _ <- Entity.i.localDate.insert(a, b).transact

      // 1 attribute
      _ <- Entity.localDate(max).query.get.map(_ ==> List(localDate2))

      _ <- Entity.localDate(max)(localDate2).query.get.map(_ ==> List(localDate2))
      _ <- Entity.localDate(max)(localDate1).query.get.map(_ ==> List())

      _ <- Entity.localDate(max).not(localDate2).query.get.map(_ ==> List())
      _ <- Entity.localDate(max).not(localDate1).query.get.map(_ ==> List(localDate2))

      _ <- Entity.localDate(max).<(localDate2).query.get.map(_ ==> List())
      _ <- Entity.localDate(max).<(localDate3).query.get.map(_ ==> List(localDate2))

      _ <- Entity.localDate(max).<=(localDate1).query.get.map(_ ==> List())
      _ <- Entity.localDate(max).<=(localDate2).query.get.map(_ ==> List(localDate2))

      _ <- Entity.localDate(max).>(localDate2).query.get.map(_ ==> List())
      _ <- Entity.localDate(max).>(localDate1).query.get.map(_ ==> List(localDate2))

      _ <- Entity.localDate(max).>=(localDate2).query.get.map(_ ==> List(localDate2))
      _ <- Entity.localDate(max).>=(localDate3).query.get.map(_ ==> List())

      // n attributes
      _ <- Entity.i.localDate(max).query.get.map(_ ==> List(b))

      _ <- Entity.i.localDate(max)(localDate2).query.get.map(_ ==> List(b))
      _ <- Entity.i.localDate(max)(localDate1).query.get.map(_ ==> List())

      _ <- Entity.i.localDate(max).not(localDate2).query.get.map(_ ==> List())
      _ <- Entity.i.localDate(max).not(localDate1).query.get.map(_ ==> List(b))

      _ <- Entity.i.localDate(max).<(localDate2).query.get.map(_ ==> List())
      _ <- Entity.i.localDate(max).<(localDate3).query.get.map(_ ==> List(b))

      _ <- Entity.i.localDate(max).<=(localDate1).query.get.map(_ ==> List())
      _ <- Entity.i.localDate(max).<=(localDate2).query.get.map(_ ==> List(b))

      _ <- Entity.i.localDate(max).>(localDate2).query.get.map(_ ==> List())
      _ <- Entity.i.localDate(max).>(localDate1).query.get.map(_ ==> List(b))

      _ <- Entity.i.localDate(max).>=(localDate2).query.get.map(_ ==> List(b))
      _ <- Entity.i.localDate(max).>=(localDate3).query.get.map(_ ==> List())

      // Include aggregated attribute too (possible but will always be the same)
      _ <- Entity.localDate.a1.localDate(max).query.get.map(_ ==> List(
        (localDate1, localDate1),
        (localDate2, localDate2),
      ))
      _ <- Entity.localDate(max).localDate.a1.query.get.map(_ ==> List(
        (localDate1, localDate1),
        (localDate2, localDate2),
      ))
    } yield ()
  }


  "min/max" - types {
    for {
      _ <- Entity.i.localDate.insert(
        (1, localDate1),
        (1, localDate2),
        (2, localDate3),
        (2, localDate4),
      ).transact

      _ <- Entity.localDate(min).localDate(max).query.get.map(_ ==> List((localDate1, localDate4)))

      _ <- Entity.localDate(min)(localDate1).localDate(max)(localDate4).query.get.map(_ ==> List((localDate1, localDate4)))
      _ <- Entity.localDate(min)(localDate1).localDate(max)(localDate5).query.get.map(_ ==> List())

      _ <- Entity.localDate(min).not(localDate2).localDate(max).not(localDate3).query.get.map(_ ==> List((localDate1, localDate4)))
      _ <- Entity.localDate(min).not(localDate2).localDate(max).not(localDate4).query.get.map(_ ==> List())

      _ <- Entity.localDate(min).<(localDate2).localDate(max).>(localDate3).query.get.map(_ ==> List((localDate1, localDate4)))
      _ <- Entity.localDate(min).<(localDate2).localDate(max).>(localDate4).query.get.map(_ ==> List())

      _ <- Entity.localDate(min).<=(localDate1).localDate(max).>=(localDate4).query.get.map(_ ==> List((localDate1, localDate4)))
      _ <- Entity.localDate(min).<=(localDate1).localDate(max).>=(localDate5).query.get.map(_ ==> List())
    } yield ()
  }


  "min/max n" - types {
    for {
      _ <- Entity.i.localDate.insert(
        (1, localDate1),
        (1, localDate2),
        (1, localDate3),
        (2, localDate4),
        (2, localDate5),
        (2, localDate6),
        (2, localDate6), // (make sure grouped values coalesce)
      ).transact

      _ <- Entity.localDate(min(1)).query.get.map(_ ==> List(Set(localDate1)))
      _ <- Entity.localDate(min(2)).query.get.map(_ ==> List(Set(localDate1, localDate2)))

      _ <- Entity.localDate(max(1)).query.get.map(_ ==> List(Set(localDate6)))
      _ <- Entity.localDate(max(2)).query.get.map(_ ==> List(Set(localDate5, localDate6)))

      _ <- Entity.i.a1.localDate(min(2)).query.get.map(_ ==> List(
        (1, Set(localDate1, localDate2)),
        (2, Set(localDate4, localDate5))
      ))

      _ <- Entity.i.a1.localDate(max(2)).query.get.map(_ ==> List(
        (1, Set(localDate2, localDate3)),
        (2, Set(localDate5, localDate6))
      ))

      _ <- Entity.i.a1.localDate(min(2)).localDate(max(2)).query.get.map(_ ==> List(
        (1, Set(localDate1, localDate2), Set(localDate2, localDate3)),
        (2, Set(localDate4, localDate5), Set(localDate5, localDate6))
      ))

      // Include aggregated attribute too (possible but will always be the same)
      _ <- Entity.localDate.a1.localDate(min(2)).query.get.map(_ ==> List(
        (localDate1, Set(localDate1)),
        (localDate2, Set(localDate2)),
        (localDate3, Set(localDate3)),
        (localDate4, Set(localDate4)),
        (localDate5, Set(localDate5)),
        (localDate6, Set(localDate6)),
      ))
      _ <- Entity.localDate(min(2)).localDate.a1.query.get.map(_ ==> List(
        (Set(localDate1), localDate1),
        (Set(localDate2), localDate2),
        (Set(localDate3), localDate3),
        (Set(localDate4), localDate4),
        (Set(localDate5), localDate5),
        (Set(localDate6), localDate6),
      ))

      _ <- Entity.localDate.a1.localDate(max(2)).query.get.map(_ ==> List(
        (localDate1, Set(localDate1)),
        (localDate2, Set(localDate2)),
        (localDate3, Set(localDate3)),
        (localDate4, Set(localDate4)),
        (localDate5, Set(localDate5)),
        (localDate6, Set(localDate6)),
      ))
      _ <- Entity.localDate(max(2)).localDate.a1.query.get.map(_ ==> List(
        (Set(localDate1), localDate1),
        (Set(localDate2), localDate2),
        (Set(localDate3), localDate3),
        (Set(localDate4), localDate4),
        (Set(localDate5), localDate5),
        (Set(localDate6), localDate6),
      ))
    } yield ()
  }


  "sample" - types {
    val all      = Set(localDate1, localDate2, localDate3)
    val allPairs = List((1, localDate1), (2, localDate2), (3, localDate3))
    for {
      _ <- Entity.i.localDate.insert(allPairs).transact

      // 1 attribute
      _ <- Entity.localDate(sample).query.get.map(res => all.contains(res.head) ==> true)

      // 1 attribute
      _ <- Entity.i.localDate(sample).query.get.map(res => allPairs.contains(res.head) ==> true)
    } yield ()
  }

  "sample ops" - types {
    if (Seq("mariadb", "mysql").contains(database)) {
      Entity.localDate(sample)(localDate1).query.get
        .map(_ ==> "Unexpected success").recover { case ModelError(err) =>
          err ==> "Operations on sample not implemented for this database."
        }
    } else {
      val all       = Set(localDate1, localDate2, localDate3)
      val (a, b, c) = ((1, localDate1), (2, localDate2), (3, localDate3))
      val allPairs  = List(a, b, c)
      for {
        _ <- Entity.i.localDate.insert(allPairs).transact

        // 1 attribute
        // Checking for equality on a sample doesn't make sense
        // _ <- Entity.localDate(sample)(localDate2).query.get.map(res => all.contains(res.head) ==> true)
        // If you want a specific value, this would be the natural query
        _ <- Entity.localDate(localDate2).query.get.map(_ ==> List(localDate2))

        _ <- Entity.localDate(sample).not(localDate2).query.get.map { res =>
          List(localDate1, localDate3).contains(res.head) ==> true
          (res.head == localDate2) ==> false
        }
        _ <- Entity.localDate(sample).<(localDate3).query.get.map { res =>
          List(localDate1, localDate2).contains(res.head) ==> true
          (res.head == localDate3) ==> false
        }
        _ <- Entity.localDate(sample).<=(localDate2).query.get.map { res =>
          List(localDate1, localDate2).contains(res.head) ==> true
          (res.head == localDate3) ==> false
        }
        _ <- Entity.localDate(sample).>(localDate1).query.get.map { res =>
          List(localDate2, localDate3).contains(res.head) ==> true
          (res.head == localDate1) ==> false
        }
        _ <- Entity.localDate(sample).>=(localDate2).query.get.map { res =>
          List(localDate2, localDate3).contains(res.head) ==> true
          (res.head == localDate1) ==> false
        }

        // 1 attribute
        _ <- Entity.i.localDate(sample).not(localDate2).query.get.map { res =>
          List(a, c).contains(res.head) ==> true
          (res.head == b) ==> false
        }
        _ <- Entity.i.localDate(sample).<(localDate3).query.get.map { res =>
          List(a, b).contains(res.head) ==> true
          (res.head == c) ==> false
        }
        _ <- Entity.i.localDate(sample).<=(localDate2).query.get.map { res =>
          List(a, b).contains(res.head) ==> true
          (res.head == c) ==> false
        }
        _ <- Entity.i.localDate(sample).>(localDate1).query.get.map { res =>
          List(b, c).contains(res.head) ==> true
          (res.head == a) ==> false
        }
        _ <- Entity.i.localDate(sample).>=(localDate2).query.get.map { res =>
          List(b, c).contains(res.head) ==> true
          (res.head == a) ==> false
        }
      } yield ()
    }
  }


  "samples(n)" - types {
    val all = Set(localDate1, localDate2, localDate3)
    for {
      _ <- Entity.localDate.insert(List(localDate1, localDate2, localDate3)).transact
      _ <- Entity.localDate(sample(1)).query.get.map(res => all.intersect(res.head).nonEmpty ==> true)
      _ <- Entity.localDate(sample(2)).query.get.map(res => all.intersect(res.head).nonEmpty ==> true)
    } yield ()
  }


  "count" - types {
    val (a, b) = ((1, 1), (2, 3))
    for {
      _ <- Entity.i.localDate.insert(List(
        (1, localDate1),
        (2, localDate2),
        (2, localDate2),
        (2, localDate3),
      )).transact

      // 1 attribute
      _ <- Entity.localDate(count).query.get.map(_ ==> List(4))

      _ <- Entity.localDate(count)(3).query.get.map(_ ==> List())
      _ <- Entity.localDate(count)(4).query.get.map(_ ==> List(4))

      _ <- Entity.localDate(count).not(3).query.get.map(_ ==> List(4))
      _ <- Entity.localDate(count).not(4).query.get.map(_ ==> List())

      _ <- Entity.localDate(count).<(5).query.get.map(_ ==> List(4))
      _ <- Entity.localDate(count).<(4).query.get.map(_ ==> List())

      _ <- Entity.localDate(count).<=(4).query.get.map(_ ==> List(4))
      _ <- Entity.localDate(count).<=(3).query.get.map(_ ==> List())

      _ <- Entity.localDate(count).>(3).query.get.map(_ ==> List(4))
      _ <- Entity.localDate(count).>(4).query.get.map(_ ==> List())

      _ <- Entity.localDate(count).>=(4).query.get.map(_ ==> List(4))
      _ <- Entity.localDate(count).>=(5).query.get.map(_ ==> List())

      // n attributes
      _ <- Entity.i.a1.localDate(count).query.get.map(_ ==> List(a, b))

      _ <- Entity.i.a1.localDate(count)(3).query.get.map(_ ==> List(b))
      _ <- Entity.i.a1.localDate(count)(2).query.get.map(_ ==> List())

      _ <- Entity.i.a1.localDate(count).not(3).query.get.map(_ ==> List(a))
      _ <- Entity.i.a1.localDate(count).not(2).query.get.map(_ ==> List(a, b))

      _ <- Entity.i.a1.localDate(count).<(3).query.get.map(_ ==> List(a))
      _ <- Entity.i.a1.localDate(count).<(4).query.get.map(_ ==> List(a, b))

      _ <- Entity.i.a1.localDate(count).<=(3).query.get.map(_ ==> List(a, b))
      _ <- Entity.i.a1.localDate(count).<=(2).query.get.map(_ ==> List(a))

      _ <- Entity.i.a1.localDate(count).>(2).query.get.map(_ ==> List(b))
      _ <- Entity.i.a1.localDate(count).>(3).query.get.map(_ ==> List())

      _ <- Entity.i.a1.localDate(count).>=(3).query.get.map(_ ==> List(b))
      _ <- Entity.i.a1.localDate(count).>=(4).query.get.map(_ ==> List())

      // Include aggregated attribute too
      _ <- Entity.localDate.a1.localDate(count).query.get.map(_ ==> List(
        (localDate1, 1),
        (localDate2, 2),
        (localDate3, 1),
      ))
      _ <- Entity.localDate(count).localDate.a1.query.get.map(_ ==> List(
        (1, localDate1),
        (2, localDate2),
        (1, localDate3),
      ))
    } yield ()
  }


  "countDistinct" - types {
    val (a, b) = ((1, 1), (2, 2))
    for {
      _ <- Entity.i.localDate.insert(List(
        (1, localDate1),
        (2, localDate2),
        (2, localDate2),
        (2, localDate3),
      )).transact

      // 1 attribute
      _ <- Entity.localDate(countDistinct).query.get.map(_ ==> List(3))

      _ <- Entity.localDate(countDistinct)(2).query.get.map(_ ==> List())
      _ <- Entity.localDate(countDistinct)(3).query.get.map(_ ==> List(3))

      _ <- Entity.localDate(countDistinct).not(2).query.get.map(_ ==> List(3))
      _ <- Entity.localDate(countDistinct).not(3).query.get.map(_ ==> List())

      _ <- Entity.localDate(countDistinct).<(4).query.get.map(_ ==> List(3))
      _ <- Entity.localDate(countDistinct).<(3).query.get.map(_ ==> List())

      _ <- Entity.localDate(countDistinct).<=(3).query.get.map(_ ==> List(3))
      _ <- Entity.localDate(countDistinct).<=(2).query.get.map(_ ==> List())

      _ <- Entity.localDate(countDistinct).>(2).query.get.map(_ ==> List(3))
      _ <- Entity.localDate(countDistinct).>(3).query.get.map(_ ==> List())

      _ <- Entity.localDate(countDistinct).>=(3).query.get.map(_ ==> List(3))
      _ <- Entity.localDate(countDistinct).>=(4).query.get.map(_ ==> List())

      // n attributes
      _ <- Entity.i.a1.localDate(countDistinct).query.get.map(_ ==> List(a, b))

      _ <- Entity.i.a1.localDate(countDistinct)(2).query.get.map(_ ==> List(b))
      _ <- Entity.i.a1.localDate(countDistinct)(3).query.get.map(_ ==> List())

      _ <- Entity.i.a1.localDate(countDistinct).not(2).query.get.map(_ ==> List(a))
      _ <- Entity.i.a1.localDate(countDistinct).not(3).query.get.map(_ ==> List(a, b))

      _ <- Entity.i.a1.localDate(countDistinct).<(2).query.get.map(_ ==> List(a))
      _ <- Entity.i.a1.localDate(countDistinct).<(3).query.get.map(_ ==> List(a, b))

      _ <- Entity.i.a1.localDate(countDistinct).<=(2).query.get.map(_ ==> List(a, b))
      _ <- Entity.i.a1.localDate(countDistinct).<=(1).query.get.map(_ ==> List(a))

      _ <- Entity.i.a1.localDate(countDistinct).>(1).query.get.map(_ ==> List(b))
      _ <- Entity.i.a1.localDate(countDistinct).>(2).query.get.map(_ ==> List())

      _ <- Entity.i.a1.localDate(countDistinct).>=(2).query.get.map(_ ==> List(b))
      _ <- Entity.i.a1.localDate(countDistinct).>=(3).query.get.map(_ ==> List())

      // Include aggregated attribute too
      _ <- Entity.localDate.a1.localDate(countDistinct).query.get.map(_ ==> List(
        (localDate1, 1),
        (localDate2, 1),
        (localDate3, 1),
      ))
      _ <- Entity.localDate(countDistinct).localDate.a1.query.get.map(_ ==> List(
        (1, localDate1),
        (1, localDate2),
        (1, localDate3),
      ))
    } yield ()
  }
}