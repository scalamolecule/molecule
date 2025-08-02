// GENERATED CODE ********************************
package molecule.db.compliance.test.aggregation.any

import molecule.base.error.ModelError
import molecule.core.setup.{MUnit, TestUtils}
import molecule.db.common.api.Api_async
import molecule.db.common.spi.Spi_async
import molecule.db.common.util.Executor.*
import molecule.db.compliance.domains.dsl.Types.*
import molecule.db.compliance.setup.DbProviders
import org.scalactic.Equality

case class Aggr_Short_(
  suite: MUnit,
  api: Api_async & Spi_async & DbProviders
) extends TestUtils {

  import api.*
  import suite.*

  "distinct" - types {
    for {
      _ <- Entity.i.short.insert(List(
        (1, short1),
        (2, short2),
        (2, short2),
        (2, short3),
      )).transact

      _ <- Entity.i.short.a1.query.get.map(_ ==> List(
        (1, short1),
        (2, short2), // 2 rows coalesced
        (2, short3),
      ))

      // Distinct values are returned in a Set
      _ <- Entity.i.a1.short(distinct).query.get.map(_ ==> List(
        (1, Set(short1)),
        (2, Set(short2, short3)),
      ))

      _ <- Entity.short(distinct).query.get.map(_.head ==> Set(
        short1, short2, short3
      ))

      // Include aggregated attribute too (possible but will always be the same)
      _ <- Entity.short.a1.short(distinct).query.get.map(_ ==> List(
        (short1, Set(short1)),
        (short2, Set(short2)),
        (short3, Set(short3)),
      ))
      _ <- Entity.short(distinct).short.a1.query.get.map(_ ==> List(
        (Set(short1), short1),
        (Set(short2), short2),
        (Set(short3), short3),
      ))
    } yield ()
  }


  "min" - types {
    val (a, b) = ((1, short1), (1, short2))
    for {
      _ <- Entity.i.short.insert(a, b).transact

      // 1 attribute
      _ <- Entity.short(min).query.get.map(_ ==> List(short1))

      _ <- Entity.short(min)(short1).query.get.map(_ ==> List(short1))
      _ <- Entity.short(min)(short2).query.get.map(_ ==> List())

      _ <- Entity.short(min).not(short1).query.get.map(_ ==> List())
      _ <- Entity.short(min).not(short2).query.get.map(_ ==> List(short1))

      _ <- Entity.short(min).<(short1).query.get.map(_ ==> List())
      _ <- Entity.short(min).<(short2).query.get.map(_ ==> List(short1))

      _ <- Entity.short(min).<=(short0).query.get.map(_ ==> List())
      _ <- Entity.short(min).<=(short1).query.get.map(_ ==> List(short1))

      _ <- Entity.short(min).>(short1).query.get.map(_ ==> List())
      _ <- Entity.short(min).>(short0).query.get.map(_ ==> List(short1))

      _ <- Entity.short(min).>=(short1).query.get.map(_ ==> List(short1))
      _ <- Entity.short(min).>=(short2).query.get.map(_ ==> List())

      // n attributes
      _ <- Entity.i.short(min).query.get.map(_ ==> List(a))

      _ <- Entity.i.short(min)(short1).query.get.map(_ ==> List(a))
      _ <- Entity.i.short(min)(short2).query.get.map(_ ==> List())

      _ <- Entity.i.short(min).not(short1).query.get.map(_ ==> List())
      _ <- Entity.i.short(min).not(short2).query.get.map(_ ==> List(a))

      _ <- Entity.i.short(min).<(short1).query.get.map(_ ==> List())
      _ <- Entity.i.short(min).<(short2).query.get.map(_ ==> List(a))

      _ <- Entity.i.short(min).<=(short0).query.get.map(_ ==> List())
      _ <- Entity.i.short(min).<=(short1).query.get.map(_ ==> List(a))

      _ <- Entity.i.short(min).>(short1).query.get.map(_ ==> List())
      _ <- Entity.i.short(min).>(short0).query.get.map(_ ==> List(a))

      _ <- Entity.i.short(min).>=(short1).query.get.map(_ ==> List(a))
      _ <- Entity.i.short(min).>=(short2).query.get.map(_ ==> List())

      // Include aggregated attribute too (possible but will always be the same)
      _ <- Entity.short.a1.short(min).query.get.map(_ ==> List(
        (short1, short1),
        (short2, short2),
      ))
      _ <- Entity.short(min).short.a1.query.get.map(_ ==> List(
        (short1, short1),
        (short2, short2),
      ))
    } yield ()
  }


  "max" - types {
    val (a, b) = ((1, short1), (1, short2))
    for {
      _ <- Entity.i.short.insert(a, b).transact

      // 1 attribute
      _ <- Entity.short(max).query.get.map(_ ==> List(short2))

      _ <- Entity.short(max)(short2).query.get.map(_ ==> List(short2))
      _ <- Entity.short(max)(short1).query.get.map(_ ==> List())

      _ <- Entity.short(max).not(short2).query.get.map(_ ==> List())
      _ <- Entity.short(max).not(short1).query.get.map(_ ==> List(short2))

      _ <- Entity.short(max).<(short2).query.get.map(_ ==> List())
      _ <- Entity.short(max).<(short3).query.get.map(_ ==> List(short2))

      _ <- Entity.short(max).<=(short1).query.get.map(_ ==> List())
      _ <- Entity.short(max).<=(short2).query.get.map(_ ==> List(short2))

      _ <- Entity.short(max).>(short2).query.get.map(_ ==> List())
      _ <- Entity.short(max).>(short1).query.get.map(_ ==> List(short2))

      _ <- Entity.short(max).>=(short2).query.get.map(_ ==> List(short2))
      _ <- Entity.short(max).>=(short3).query.get.map(_ ==> List())

      // n attributes
      _ <- Entity.i.short(max).query.get.map(_ ==> List(b))

      _ <- Entity.i.short(max)(short2).query.get.map(_ ==> List(b))
      _ <- Entity.i.short(max)(short1).query.get.map(_ ==> List())

      _ <- Entity.i.short(max).not(short2).query.get.map(_ ==> List())
      _ <- Entity.i.short(max).not(short1).query.get.map(_ ==> List(b))

      _ <- Entity.i.short(max).<(short2).query.get.map(_ ==> List())
      _ <- Entity.i.short(max).<(short3).query.get.map(_ ==> List(b))

      _ <- Entity.i.short(max).<=(short1).query.get.map(_ ==> List())
      _ <- Entity.i.short(max).<=(short2).query.get.map(_ ==> List(b))

      _ <- Entity.i.short(max).>(short2).query.get.map(_ ==> List())
      _ <- Entity.i.short(max).>(short1).query.get.map(_ ==> List(b))

      _ <- Entity.i.short(max).>=(short2).query.get.map(_ ==> List(b))
      _ <- Entity.i.short(max).>=(short3).query.get.map(_ ==> List())

      // Include aggregated attribute too (possible but will always be the same)
      _ <- Entity.short.a1.short(max).query.get.map(_ ==> List(
        (short1, short1),
        (short2, short2),
      ))
      _ <- Entity.short(max).short.a1.query.get.map(_ ==> List(
        (short1, short1),
        (short2, short2),
      ))
    } yield ()
  }


  "min/max" - types {
    for {
      _ <- Entity.i.short.insert(
        (1, short1),
        (1, short2),
        (2, short3),
        (2, short4),
      ).transact

      _ <- Entity.short(min).short(max).query.get.map(_ ==> List((short1, short4)))

      _ <- Entity.short(min)(short1).short(max)(short4).query.get.map(_ ==> List((short1, short4)))
      _ <- Entity.short(min)(short1).short(max)(short5).query.get.map(_ ==> List())

      _ <- Entity.short(min).not(short2).short(max).not(short3).query.get.map(_ ==> List((short1, short4)))
      _ <- Entity.short(min).not(short2).short(max).not(short4).query.get.map(_ ==> List())

      _ <- Entity.short(min).<(short2).short(max).>(short3).query.get.map(_ ==> List((short1, short4)))
      _ <- Entity.short(min).<(short2).short(max).>(short4).query.get.map(_ ==> List())

      _ <- Entity.short(min).<=(short1).short(max).>=(short4).query.get.map(_ ==> List((short1, short4)))
      _ <- Entity.short(min).<=(short1).short(max).>=(short5).query.get.map(_ ==> List())
    } yield ()
  }


  "min/max n" - types {
    for {
      _ <- Entity.i.short.insert(
        (1, short1),
        (1, short2),
        (1, short3),
        (2, short4),
        (2, short5),
        (2, short6),
        (2, short6), // (make sure grouped values coalesce)
      ).transact

      _ <- Entity.short(min(1)).query.get.map(_ ==> List(Set(short1)))
      _ <- Entity.short(min(2)).query.get.map(_ ==> List(Set(short1, short2)))

      _ <- Entity.short(max(1)).query.get.map(_ ==> List(Set(short6)))
      _ <- Entity.short(max(2)).query.get.map(_ ==> List(Set(short5, short6)))

      _ <- Entity.i.a1.short(min(2)).query.get.map(_ ==> List(
        (1, Set(short1, short2)),
        (2, Set(short4, short5))
      ))

      _ <- Entity.i.a1.short(max(2)).query.get.map(_ ==> List(
        (1, Set(short2, short3)),
        (2, Set(short5, short6))
      ))

      _ <- Entity.i.a1.short(min(2)).short(max(2)).query.get.map(_ ==> List(
        (1, Set(short1, short2), Set(short2, short3)),
        (2, Set(short4, short5), Set(short5, short6))
      ))

      // Include aggregated attribute too (possible but will always be the same)
      _ <- Entity.short.a1.short(min(2)).query.get.map(_ ==> List(
        (short1, Set(short1)),
        (short2, Set(short2)),
        (short3, Set(short3)),
        (short4, Set(short4)),
        (short5, Set(short5)),
        (short6, Set(short6)),
      ))
      _ <- Entity.short(min(2)).short.a1.query.get.map(_ ==> List(
        (Set(short1), short1),
        (Set(short2), short2),
        (Set(short3), short3),
        (Set(short4), short4),
        (Set(short5), short5),
        (Set(short6), short6),
      ))

      _ <- Entity.short.a1.short(max(2)).query.get.map(_ ==> List(
        (short1, Set(short1)),
        (short2, Set(short2)),
        (short3, Set(short3)),
        (short4, Set(short4)),
        (short5, Set(short5)),
        (short6, Set(short6)),
      ))
      _ <- Entity.short(max(2)).short.a1.query.get.map(_ ==> List(
        (Set(short1), short1),
        (Set(short2), short2),
        (Set(short3), short3),
        (Set(short4), short4),
        (Set(short5), short5),
        (Set(short6), short6),
      ))
    } yield ()
  }


  "sample" - types {
    val all      = Set(short1, short2, short3)
    val allPairs = List((1, short1), (2, short2), (3, short3))
    for {
      _ <- Entity.i.short.insert(allPairs).transact

      // 1 attribute
      _ <- Entity.short(sample).query.get.map(res => all.contains(res.head) ==> true)

      // 1 attribute
      _ <- Entity.i.short(sample).query.get.map(res => allPairs.contains(res.head) ==> true)
    } yield ()
  }

  "sample ops" - types {
    if (Seq("mariadb", "mysql").contains(database)) {
      Entity.short(sample)(short1).query.get
        .map(_ ==> "Unexpected success").recover { case ModelError(err) =>
          err ==> "Operations on sample not implemented for this database."
        }
    } else {
      val all       = Set(short1, short2, short3)
      val (a, b, c) = ((1, short1), (2, short2), (3, short3))
      val allPairs  = List(a, b, c)
      for {
        _ <- Entity.i.short.insert(allPairs).transact

        // 1 attribute
        // Checking for equality on a sample doesn't make sense
        // _ <- Entity.short(sample)(short2).query.get.map(res => all.contains(res.head) ==> true)
        // If you want a specific value, this would be the natural query
        _ <- Entity.short(short2).query.get.map(_ ==> List(short2))

        _ <- Entity.short(sample).not(short2).query.get.map { res =>
          List(short1, short3).contains(res.head) ==> true
          (res.head == short2) ==> false
        }
        _ <- Entity.short(sample).<(short3).query.get.map { res =>
          List(short1, short2).contains(res.head) ==> true
          (res.head == short3) ==> false
        }
        _ <- Entity.short(sample).<=(short2).query.get.map { res =>
          List(short1, short2).contains(res.head) ==> true
          (res.head == short3) ==> false
        }
        _ <- Entity.short(sample).>(short1).query.get.map { res =>
          List(short2, short3).contains(res.head) ==> true
          (res.head == short1) ==> false
        }
        _ <- Entity.short(sample).>=(short2).query.get.map { res =>
          List(short2, short3).contains(res.head) ==> true
          (res.head == short1) ==> false
        }

        // 1 attribute
        _ <- Entity.i.short(sample).not(short2).query.get.map { res =>
          List(a, c).contains(res.head) ==> true
          (res.head == b) ==> false
        }
        _ <- Entity.i.short(sample).<(short3).query.get.map { res =>
          List(a, b).contains(res.head) ==> true
          (res.head == c) ==> false
        }
        _ <- Entity.i.short(sample).<=(short2).query.get.map { res =>
          List(a, b).contains(res.head) ==> true
          (res.head == c) ==> false
        }
        _ <- Entity.i.short(sample).>(short1).query.get.map { res =>
          List(b, c).contains(res.head) ==> true
          (res.head == a) ==> false
        }
        _ <- Entity.i.short(sample).>=(short2).query.get.map { res =>
          List(b, c).contains(res.head) ==> true
          (res.head == a) ==> false
        }
      } yield ()
    }
  }


  "samples(n)" - types {
    val all = Set(short1, short2, short3)
    for {
      _ <- Entity.short.insert(List(short1, short2, short3)).transact
      _ <- Entity.short(sample(1)).query.get.map(res => all.intersect(res.head).nonEmpty ==> true)
      _ <- Entity.short(sample(2)).query.get.map(res => all.intersect(res.head).nonEmpty ==> true)
    } yield ()
  }


  "count" - types {
    val (a, b) = ((1, 1), (2, 3))
    for {
      _ <- Entity.i.short.insert(List(
        (1, short1),
        (2, short2),
        (2, short2),
        (2, short3),
      )).transact

      // 1 attribute
      _ <- Entity.short(count).query.get.map(_ ==> List(4))

      _ <- Entity.short(count)(3).query.get.map(_ ==> List())
      _ <- Entity.short(count)(4).query.get.map(_ ==> List(4))

      _ <- Entity.short(count).not(3).query.get.map(_ ==> List(4))
      _ <- Entity.short(count).not(4).query.get.map(_ ==> List())

      _ <- Entity.short(count).<(5).query.get.map(_ ==> List(4))
      _ <- Entity.short(count).<(4).query.get.map(_ ==> List())

      _ <- Entity.short(count).<=(4).query.get.map(_ ==> List(4))
      _ <- Entity.short(count).<=(3).query.get.map(_ ==> List())

      _ <- Entity.short(count).>(3).query.get.map(_ ==> List(4))
      _ <- Entity.short(count).>(4).query.get.map(_ ==> List())

      _ <- Entity.short(count).>=(4).query.get.map(_ ==> List(4))
      _ <- Entity.short(count).>=(5).query.get.map(_ ==> List())

      // n attributes
      _ <- Entity.i.a1.short(count).query.get.map(_ ==> List(a, b))

      _ <- Entity.i.a1.short(count)(3).query.get.map(_ ==> List(b))
      _ <- Entity.i.a1.short(count)(2).query.get.map(_ ==> List())

      _ <- Entity.i.a1.short(count).not(3).query.get.map(_ ==> List(a))
      _ <- Entity.i.a1.short(count).not(2).query.get.map(_ ==> List(a, b))

      _ <- Entity.i.a1.short(count).<(3).query.get.map(_ ==> List(a))
      _ <- Entity.i.a1.short(count).<(4).query.get.map(_ ==> List(a, b))

      _ <- Entity.i.a1.short(count).<=(3).query.get.map(_ ==> List(a, b))
      _ <- Entity.i.a1.short(count).<=(2).query.get.map(_ ==> List(a))

      _ <- Entity.i.a1.short(count).>(2).query.get.map(_ ==> List(b))
      _ <- Entity.i.a1.short(count).>(3).query.get.map(_ ==> List())

      _ <- Entity.i.a1.short(count).>=(3).query.get.map(_ ==> List(b))
      _ <- Entity.i.a1.short(count).>=(4).query.get.map(_ ==> List())

      // Include aggregated attribute too
      _ <- Entity.short.a1.short(count).query.get.map(_ ==> List(
        (short1, 1),
        (short2, 2),
        (short3, 1),
      ))
      _ <- Entity.short(count).short.a1.query.get.map(_ ==> List(
        (1, short1),
        (2, short2),
        (1, short3),
      ))
    } yield ()
  }


  "countDistinct" - types {
    val (a, b) = ((1, 1), (2, 2))
    for {
      _ <- Entity.i.short.insert(List(
        (1, short1),
        (2, short2),
        (2, short2),
        (2, short3),
      )).transact

      // 1 attribute
      _ <- Entity.short(countDistinct).query.get.map(_ ==> List(3))

      _ <- Entity.short(countDistinct)(2).query.get.map(_ ==> List())
      _ <- Entity.short(countDistinct)(3).query.get.map(_ ==> List(3))

      _ <- Entity.short(countDistinct).not(2).query.get.map(_ ==> List(3))
      _ <- Entity.short(countDistinct).not(3).query.get.map(_ ==> List())

      _ <- Entity.short(countDistinct).<(4).query.get.map(_ ==> List(3))
      _ <- Entity.short(countDistinct).<(3).query.get.map(_ ==> List())

      _ <- Entity.short(countDistinct).<=(3).query.get.map(_ ==> List(3))
      _ <- Entity.short(countDistinct).<=(2).query.get.map(_ ==> List())

      _ <- Entity.short(countDistinct).>(2).query.get.map(_ ==> List(3))
      _ <- Entity.short(countDistinct).>(3).query.get.map(_ ==> List())

      _ <- Entity.short(countDistinct).>=(3).query.get.map(_ ==> List(3))
      _ <- Entity.short(countDistinct).>=(4).query.get.map(_ ==> List())

      // n attributes
      _ <- Entity.i.a1.short(countDistinct).query.get.map(_ ==> List(a, b))

      _ <- Entity.i.a1.short(countDistinct)(2).query.get.map(_ ==> List(b))
      _ <- Entity.i.a1.short(countDistinct)(3).query.get.map(_ ==> List())

      _ <- Entity.i.a1.short(countDistinct).not(2).query.get.map(_ ==> List(a))
      _ <- Entity.i.a1.short(countDistinct).not(3).query.get.map(_ ==> List(a, b))

      _ <- Entity.i.a1.short(countDistinct).<(2).query.get.map(_ ==> List(a))
      _ <- Entity.i.a1.short(countDistinct).<(3).query.get.map(_ ==> List(a, b))

      _ <- Entity.i.a1.short(countDistinct).<=(2).query.get.map(_ ==> List(a, b))
      _ <- Entity.i.a1.short(countDistinct).<=(1).query.get.map(_ ==> List(a))

      _ <- Entity.i.a1.short(countDistinct).>(1).query.get.map(_ ==> List(b))
      _ <- Entity.i.a1.short(countDistinct).>(2).query.get.map(_ ==> List())

      _ <- Entity.i.a1.short(countDistinct).>=(2).query.get.map(_ ==> List(b))
      _ <- Entity.i.a1.short(countDistinct).>=(3).query.get.map(_ ==> List())

      // Include aggregated attribute too
      _ <- Entity.short.a1.short(countDistinct).query.get.map(_ ==> List(
        (short1, 1),
        (short2, 1),
        (short3, 1),
      ))
      _ <- Entity.short(countDistinct).short.a1.query.get.map(_ ==> List(
        (1, short1),
        (1, short2),
        (1, short3),
      ))
    } yield ()
  }
}