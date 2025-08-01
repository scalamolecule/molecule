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

case class Aggr_BigDecimal_(
  suite: MUnit,
  api: Api_async & Spi_async & DbProviders
) extends TestUtils {

  import api.*
  import suite.*

  "distinct" - types {
    for {
      _ <- Entity.i.bigDecimal.insert(List(
        (1, bigDecimal1),
        (2, bigDecimal2),
        (2, bigDecimal2),
        (2, bigDecimal3),
      )).transact

      _ <- Entity.i.bigDecimal.a1.query.get.map(_ ==> List(
        (1, bigDecimal1),
        (2, bigDecimal2), // 2 rows coalesced
        (2, bigDecimal3),
      ))

      // Distinct values are returned in a Set
      _ <- Entity.i.a1.bigDecimal(distinct).query.get.map(_ ==> List(
        (1, Set(bigDecimal1)),
        (2, Set(bigDecimal2, bigDecimal3)),
      ))

      _ <- Entity.bigDecimal(distinct).query.get.map(_.head ==> Set(
        bigDecimal1, bigDecimal2, bigDecimal3
      ))
    } yield ()
  }


  "min" - types {
    val (a, b) = ((1, bigDecimal1), (1, bigDecimal2))
    for {
      _ <- Entity.i.bigDecimal.insert(a, b).transact

      // 1 attribute
      _ <- Entity.bigDecimal(min).query.get.map(_ ==> List(bigDecimal1))

      _ <- Entity.bigDecimal(min)(bigDecimal1).query.get.map(_ ==> List(bigDecimal1))
      _ <- Entity.bigDecimal(min)(bigDecimal2).query.get.map(_ ==> List())

      _ <- Entity.bigDecimal(min).not(bigDecimal1).query.get.map(_ ==> List())
      _ <- Entity.bigDecimal(min).not(bigDecimal2).query.get.map(_ ==> List(bigDecimal1))

      _ <- Entity.bigDecimal(min).<(bigDecimal1).query.get.map(_ ==> List())
      _ <- Entity.bigDecimal(min).<(bigDecimal2).query.get.map(_ ==> List(bigDecimal1))

      _ <- Entity.bigDecimal(min).<=(bigDecimal0).query.get.map(_ ==> List())
      _ <- Entity.bigDecimal(min).<=(bigDecimal1).query.get.map(_ ==> List(bigDecimal1))

      _ <- Entity.bigDecimal(min).>(bigDecimal1).query.get.map(_ ==> List())
      _ <- Entity.bigDecimal(min).>(bigDecimal0).query.get.map(_ ==> List(bigDecimal1))

      _ <- Entity.bigDecimal(min).>=(bigDecimal1).query.get.map(_ ==> List(bigDecimal1))
      _ <- Entity.bigDecimal(min).>=(bigDecimal2).query.get.map(_ ==> List())


      // n attributes
      _ <- Entity.i.bigDecimal(min).query.get.map(_ ==> List(a))

      _ <- Entity.i.bigDecimal(min)(bigDecimal1).query.get.map(_ ==> List(a))
      _ <- Entity.i.bigDecimal(min)(bigDecimal2).query.get.map(_ ==> List())

      _ <- Entity.i.bigDecimal(min).not(bigDecimal1).query.get.map(_ ==> List())
      _ <- Entity.i.bigDecimal(min).not(bigDecimal2).query.get.map(_ ==> List(a))

      _ <- Entity.i.bigDecimal(min).<(bigDecimal1).query.get.map(_ ==> List())
      _ <- Entity.i.bigDecimal(min).<(bigDecimal2).query.get.map(_ ==> List(a))

      _ <- Entity.i.bigDecimal(min).<=(bigDecimal0).query.get.map(_ ==> List())
      _ <- Entity.i.bigDecimal(min).<=(bigDecimal1).query.get.map(_ ==> List(a))

      _ <- Entity.i.bigDecimal(min).>(bigDecimal1).query.get.map(_ ==> List())
      _ <- Entity.i.bigDecimal(min).>(bigDecimal0).query.get.map(_ ==> List(a))

      _ <- Entity.i.bigDecimal(min).>=(bigDecimal1).query.get.map(_ ==> List(a))
      _ <- Entity.i.bigDecimal(min).>=(bigDecimal2).query.get.map(_ ==> List())
    } yield ()
  }


  "max" - types {
    val (a, b) = ((1, bigDecimal1), (1, bigDecimal2))
    for {
      _ <- Entity.i.bigDecimal.insert(a, b).transact

      // 1 attribute
      _ <- Entity.bigDecimal(max).query.get.map(_ ==> List(bigDecimal2))

      _ <- Entity.bigDecimal(max)(bigDecimal2).query.get.map(_ ==> List(bigDecimal2))
      _ <- Entity.bigDecimal(max)(bigDecimal1).query.get.map(_ ==> List())

      _ <- Entity.bigDecimal(max).not(bigDecimal2).query.get.map(_ ==> List())
      _ <- Entity.bigDecimal(max).not(bigDecimal1).query.get.map(_ ==> List(bigDecimal2))

      _ <- Entity.bigDecimal(max).<(bigDecimal2).query.get.map(_ ==> List())
      _ <- Entity.bigDecimal(max).<(bigDecimal3).query.get.map(_ ==> List(bigDecimal2))

      _ <- Entity.bigDecimal(max).<=(bigDecimal1).query.get.map(_ ==> List())
      _ <- Entity.bigDecimal(max).<=(bigDecimal2).query.get.map(_ ==> List(bigDecimal2))

      _ <- Entity.bigDecimal(max).>(bigDecimal2).query.get.map(_ ==> List())
      _ <- Entity.bigDecimal(max).>(bigDecimal1).query.get.map(_ ==> List(bigDecimal2))

      _ <- Entity.bigDecimal(max).>=(bigDecimal2).query.get.map(_ ==> List(bigDecimal2))
      _ <- Entity.bigDecimal(max).>=(bigDecimal3).query.get.map(_ ==> List())


      // n attributes
      _ <- Entity.i.bigDecimal(max).query.get.map(_ ==> List(b))

      _ <- Entity.i.bigDecimal(max)(bigDecimal2).query.get.map(_ ==> List(b))
      _ <- Entity.i.bigDecimal(max)(bigDecimal1).query.get.map(_ ==> List())

      _ <- Entity.i.bigDecimal(max).not(bigDecimal2).query.get.map(_ ==> List())
      _ <- Entity.i.bigDecimal(max).not(bigDecimal1).query.get.map(_ ==> List(b))

      _ <- Entity.i.bigDecimal(max).<(bigDecimal2).query.get.map(_ ==> List())
      _ <- Entity.i.bigDecimal(max).<(bigDecimal3).query.get.map(_ ==> List(b))

      _ <- Entity.i.bigDecimal(max).<=(bigDecimal1).query.get.map(_ ==> List())
      _ <- Entity.i.bigDecimal(max).<=(bigDecimal2).query.get.map(_ ==> List(b))

      _ <- Entity.i.bigDecimal(max).>(bigDecimal2).query.get.map(_ ==> List())
      _ <- Entity.i.bigDecimal(max).>(bigDecimal1).query.get.map(_ ==> List(b))

      _ <- Entity.i.bigDecimal(max).>=(bigDecimal2).query.get.map(_ ==> List(b))
      _ <- Entity.i.bigDecimal(max).>=(bigDecimal3).query.get.map(_ ==> List())
    } yield ()
  }


  "min/max" - types {
    for {
      _ <- Entity.i.bigDecimal.insert(
        (1, bigDecimal1),
        (1, bigDecimal2),
        (2, bigDecimal3),
        (2, bigDecimal4),
      ).transact

      _ <- Entity.bigDecimal(min).bigDecimal(max).query.get.map(_ ==> List((bigDecimal1, bigDecimal4)))

      _ <- Entity.bigDecimal(min)(bigDecimal1).bigDecimal(max)(bigDecimal4).query.get.map(_ ==> List((bigDecimal1, bigDecimal4)))
      _ <- Entity.bigDecimal(min)(bigDecimal1).bigDecimal(max)(bigDecimal5).query.get.map(_ ==> List())

      _ <- Entity.bigDecimal(min).not(bigDecimal2).bigDecimal(max).not(bigDecimal3).query.get.map(_ ==> List((bigDecimal1, bigDecimal4)))
      _ <- Entity.bigDecimal(min).not(bigDecimal2).bigDecimal(max).not(bigDecimal4).query.get.map(_ ==> List())

      _ <- Entity.bigDecimal(min).<(bigDecimal2).bigDecimal(max).>(bigDecimal3).query.get.map(_ ==> List((bigDecimal1, bigDecimal4)))
      _ <- Entity.bigDecimal(min).<(bigDecimal2).bigDecimal(max).>(bigDecimal4).query.get.map(_ ==> List())

      _ <- Entity.bigDecimal(min).<=(bigDecimal1).bigDecimal(max).>=(bigDecimal4).query.get.map(_ ==> List((bigDecimal1, bigDecimal4)))
      _ <- Entity.bigDecimal(min).<=(bigDecimal1).bigDecimal(max).>=(bigDecimal5).query.get.map(_ ==> List())
    } yield ()
  }


  "min/max n" - types {
    for {
      _ <- Entity.i.bigDecimal.insert(
        (1, bigDecimal1),
        (1, bigDecimal2),
        (1, bigDecimal3),
        (2, bigDecimal4),
        (2, bigDecimal5),
        (2, bigDecimal6),
        (2, bigDecimal6), // (make sure grouped values coalesce)
      ).transact

      _ <- Entity.bigDecimal(min(1)).query.get.map(_ ==> List(Set(bigDecimal1)))
      _ <- Entity.bigDecimal(min(2)).query.get.map(_ ==> List(Set(bigDecimal1, bigDecimal2)))

      _ <- Entity.bigDecimal(max(1)).query.get.map(_ ==> List(Set(bigDecimal6)))
      _ <- Entity.bigDecimal(max(2)).query.get.map(_ ==> List(Set(bigDecimal5, bigDecimal6)))

      _ <- Entity.i.a1.bigDecimal(min(2)).query.get.map(_ ==> List(
        (1, Set(bigDecimal1, bigDecimal2)),
        (2, Set(bigDecimal4, bigDecimal5))
      ))

      _ <- Entity.i.a1.bigDecimal(max(2)).query.get.map(_ ==> List(
        (1, Set(bigDecimal2, bigDecimal3)),
        (2, Set(bigDecimal5, bigDecimal6))
      ))

      _ <- Entity.i.a1.bigDecimal(min(2)).bigDecimal(max(2)).query.get.map(_ ==> List(
        (1, Set(bigDecimal1, bigDecimal2), Set(bigDecimal2, bigDecimal3)),
        (2, Set(bigDecimal4, bigDecimal5), Set(bigDecimal5, bigDecimal6))
      ))
    } yield ()
  }


  "sample" - types {
    val all      = Set(bigDecimal1, bigDecimal2, bigDecimal3)
    val allPairs = List((1, bigDecimal1), (2, bigDecimal2), (3, bigDecimal3))
    for {
      _ <- Entity.i.bigDecimal.insert(allPairs).transact

      // 1 attribute
      _ <- Entity.bigDecimal(sample).query.get.map(res => all.contains(res.head) ==> true)

      // 1 attribute
      _ <- Entity.i.bigDecimal(sample).query.get.map(res => allPairs.contains(res.head) ==> true)
    } yield ()
  }

  "sample ops" - types {
    if (Seq("mariadb", "mysql").contains(database)) {
      Entity.bigDecimal(sample)(bigDecimal1).query.get
        .map(_ ==> "Unexpected success").recover { case ModelError(err) =>
          err ==> "Operations on sample not implemented for this database."
        }
    } else {
      val all       = Set(bigDecimal1, bigDecimal2, bigDecimal3)
      val (a, b, c) = ((1, bigDecimal1), (2, bigDecimal2), (3, bigDecimal3))
      val allPairs  = List(a, b, c)
      for {
        _ <- Entity.i.bigDecimal.insert(allPairs).transact

        // 1 attribute
        // Checking for equality on a sample doesn't make sense
        // _ <- Entity.bigDecimal(sample)(bigDecimal2).query.get.map(res => all.contains(res.head) ==> true)
        // If you want a specific value, this would be the natural query
        _ <- Entity.bigDecimal(bigDecimal2).query.get.map(_ ==> List(bigDecimal2))

        _ <- Entity.bigDecimal(sample).not(bigDecimal2).query.get.map { res =>
          List(bigDecimal1, bigDecimal3).contains(res.head) ==> true
          (res.head == bigDecimal2) ==> false
        }
        _ <- Entity.bigDecimal(sample).<(bigDecimal3).query.get.map { res =>
          List(bigDecimal1, bigDecimal2).contains(res.head) ==> true
          (res.head == bigDecimal3) ==> false
        }
        _ <- Entity.bigDecimal(sample).<=(bigDecimal2).query.get.map { res =>
          List(bigDecimal1, bigDecimal2).contains(res.head) ==> true
          (res.head == bigDecimal3) ==> false
        }
        _ <- Entity.bigDecimal(sample).>(bigDecimal1).query.get.map { res =>
          List(bigDecimal2, bigDecimal3).contains(res.head) ==> true
          (res.head == bigDecimal1) ==> false
        }
        _ <- Entity.bigDecimal(sample).>=(bigDecimal2).query.get.map { res =>
          List(bigDecimal2, bigDecimal3).contains(res.head) ==> true
          (res.head == bigDecimal1) ==> false
        }

        // 1 attribute
        _ <- Entity.i.bigDecimal(sample).not(bigDecimal2).query.get.map { res =>
          List(a, c).contains(res.head) ==> true
          (res.head == b) ==> false
        }
        _ <- Entity.i.bigDecimal(sample).<(bigDecimal3).query.get.map { res =>
          List(a, b).contains(res.head) ==> true
          (res.head == c) ==> false
        }
        _ <- Entity.i.bigDecimal(sample).<=(bigDecimal2).query.get.map { res =>
          List(a, b).contains(res.head) ==> true
          (res.head == c) ==> false
        }
        _ <- Entity.i.bigDecimal(sample).>(bigDecimal1).query.get.map { res =>
          List(b, c).contains(res.head) ==> true
          (res.head == a) ==> false
        }
        _ <- Entity.i.bigDecimal(sample).>=(bigDecimal2).query.get.map { res =>
          List(b, c).contains(res.head) ==> true
          (res.head == a) ==> false
        }
      } yield ()
    }
  }


  "samples(n)" - types {
    val all = Set(bigDecimal1, bigDecimal2, bigDecimal3)
    for {
      _ <- Entity.bigDecimal.insert(List(bigDecimal1, bigDecimal2, bigDecimal3)).transact
      _ <- Entity.bigDecimal(sample(1)).query.get.map(res => all.intersect(res.head).nonEmpty ==> true)
      _ <- Entity.bigDecimal(sample(2)).query.get.map(res => all.intersect(res.head).nonEmpty ==> true)
    } yield ()
  }


  "count" - types {
    val (a, b) = ((1, 1), (2, 3))
    for {
      _ <- Entity.i.bigDecimal.insert(List(
        (1, bigDecimal1),
        (2, bigDecimal2),
        (2, bigDecimal2),
        (2, bigDecimal3),
      )).transact

      // 1 attribute
      _ <- Entity.bigDecimal(count).query.get.map(_ ==> List(4))

      _ <- Entity.bigDecimal(count)(3).query.get.map(_ ==> List())
      _ <- Entity.bigDecimal(count)(4).query.get.map(_ ==> List(4))

      _ <- Entity.bigDecimal(count).not(3).query.get.map(_ ==> List(4))
      _ <- Entity.bigDecimal(count).not(4).query.get.map(_ ==> List())

      _ <- Entity.bigDecimal(count).<(5).query.get.map(_ ==> List(4))
      _ <- Entity.bigDecimal(count).<(4).query.get.map(_ ==> List())

      _ <- Entity.bigDecimal(count).<=(4).query.get.map(_ ==> List(4))
      _ <- Entity.bigDecimal(count).<=(3).query.get.map(_ ==> List())

      _ <- Entity.bigDecimal(count).>(3).query.get.map(_ ==> List(4))
      _ <- Entity.bigDecimal(count).>(4).query.get.map(_ ==> List())

      _ <- Entity.bigDecimal(count).>=(4).query.get.map(_ ==> List(4))
      _ <- Entity.bigDecimal(count).>=(5).query.get.map(_ ==> List())


      // n attributes
      _ <- Entity.i.a1.bigDecimal(count).query.get.map(_ ==> List(a, b))

      _ <- Entity.i.a1.bigDecimal(count)(3).query.get.map(_ ==> List(b))
      _ <- Entity.i.a1.bigDecimal(count)(2).query.get.map(_ ==> List())

      _ <- Entity.i.a1.bigDecimal(count).not(3).query.get.map(_ ==> List(a))
      _ <- Entity.i.a1.bigDecimal(count).not(2).query.get.map(_ ==> List(a, b))

      _ <- Entity.i.a1.bigDecimal(count).<(3).query.get.map(_ ==> List(a))
      _ <- Entity.i.a1.bigDecimal(count).<(4).query.get.map(_ ==> List(a, b))

      _ <- Entity.i.a1.bigDecimal(count).<=(3).query.get.map(_ ==> List(a, b))
      _ <- Entity.i.a1.bigDecimal(count).<=(2).query.get.map(_ ==> List(a))

      _ <- Entity.i.a1.bigDecimal(count).>(2).query.get.map(_ ==> List(b))
      _ <- Entity.i.a1.bigDecimal(count).>(3).query.get.map(_ ==> List())

      _ <- Entity.i.a1.bigDecimal(count).>=(3).query.get.map(_ ==> List(b))
      _ <- Entity.i.a1.bigDecimal(count).>=(4).query.get.map(_ ==> List())
    } yield ()
  }


  "countDistinct" - types {
    val (a, b) = ((1, 1), (2, 2))
    for {
      _ <- Entity.i.bigDecimal.insert(List(
        (1, bigDecimal1),
        (2, bigDecimal2),
        (2, bigDecimal2),
        (2, bigDecimal3),
      )).transact

      // 1 attribute
      _ <- Entity.bigDecimal(countDistinct).query.get.map(_ ==> List(3))

      _ <- Entity.bigDecimal(countDistinct)(2).query.get.map(_ ==> List())
      _ <- Entity.bigDecimal(countDistinct)(3).query.get.map(_ ==> List(3))

      _ <- Entity.bigDecimal(countDistinct).not(2).query.get.map(_ ==> List(3))
      _ <- Entity.bigDecimal(countDistinct).not(3).query.get.map(_ ==> List())

      _ <- Entity.bigDecimal(countDistinct).<(4).query.get.map(_ ==> List(3))
      _ <- Entity.bigDecimal(countDistinct).<(3).query.get.map(_ ==> List())

      _ <- Entity.bigDecimal(countDistinct).<=(3).query.get.map(_ ==> List(3))
      _ <- Entity.bigDecimal(countDistinct).<=(2).query.get.map(_ ==> List())

      _ <- Entity.bigDecimal(countDistinct).>(2).query.get.map(_ ==> List(3))
      _ <- Entity.bigDecimal(countDistinct).>(3).query.get.map(_ ==> List())

      _ <- Entity.bigDecimal(countDistinct).>=(3).query.get.map(_ ==> List(3))
      _ <- Entity.bigDecimal(countDistinct).>=(4).query.get.map(_ ==> List())


      // n attributes
      _ <- Entity.i.a1.bigDecimal(countDistinct).query.get.map(_ ==> List(a, b))

      _ <- Entity.i.a1.bigDecimal(countDistinct)(2).query.get.map(_ ==> List(b))
      _ <- Entity.i.a1.bigDecimal(countDistinct)(3).query.get.map(_ ==> List())

      _ <- Entity.i.a1.bigDecimal(countDistinct).not(2).query.get.map(_ ==> List(a))
      _ <- Entity.i.a1.bigDecimal(countDistinct).not(3).query.get.map(_ ==> List(a, b))

      _ <- Entity.i.a1.bigDecimal(countDistinct).<(2).query.get.map(_ ==> List(a))
      _ <- Entity.i.a1.bigDecimal(countDistinct).<(3).query.get.map(_ ==> List(a, b))

      _ <- Entity.i.a1.bigDecimal(countDistinct).<=(2).query.get.map(_ ==> List(a, b))
      _ <- Entity.i.a1.bigDecimal(countDistinct).<=(1).query.get.map(_ ==> List(a))

      _ <- Entity.i.a1.bigDecimal(countDistinct).>(1).query.get.map(_ ==> List(b))
      _ <- Entity.i.a1.bigDecimal(countDistinct).>(2).query.get.map(_ ==> List())

      _ <- Entity.i.a1.bigDecimal(countDistinct).>=(2).query.get.map(_ ==> List(b))
      _ <- Entity.i.a1.bigDecimal(countDistinct).>=(3).query.get.map(_ ==> List())
    } yield ()
  }
}