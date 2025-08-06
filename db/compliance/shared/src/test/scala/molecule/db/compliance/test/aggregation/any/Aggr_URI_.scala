// GENERATED CODE ********************************
package molecule.db.compliance.test.aggregation.any

import java.net.URI
import molecule.core.error.ModelError
import molecule.core.setup.{MUnit, TestUtils}
import molecule.db.common.api.Api_async
import molecule.db.common.spi.Spi_async
import molecule.db.common.util.Executor.*
import molecule.db.compliance.domains.dsl.Types.*
import molecule.db.compliance.setup.DbProviders
import org.scalactic.Equality

case class Aggr_URI_(
  suite: MUnit,
  api: Api_async & Spi_async & DbProviders
) extends TestUtils {

  import api.*
  import suite.*

  "distinct" - types {
    for {
      _ <- Entity.i.uri.insert(List(
        (1, uri1),
        (2, uri2),
        (2, uri2),
        (2, uri3),
      )).transact

      _ <- Entity.i.uri.a1.query.get.map(_ ==> List(
        (1, uri1),
        (2, uri2), // 2 rows coalesced
        (2, uri3),
      ))

      // Distinct values are returned in a Set
      _ <- Entity.i.a1.uri(distinct).query.get.map(_ ==> List(
        (1, Set(uri1)),
        (2, Set(uri2, uri3)),
      ))

      _ <- Entity.uri(distinct).query.get.map(_.head ==> Set(
        uri1, uri2, uri3
      ))

      // Include aggregated attribute too (possible but will always be the same)
      _ <- Entity.uri.a1.uri(distinct).query.get.map(_ ==> List(
        (uri1, Set(uri1)),
        (uri2, Set(uri2)),
        (uri3, Set(uri3)),
      ))
      _ <- Entity.uri(distinct).uri.a1.query.get.map(_ ==> List(
        (Set(uri1), uri1),
        (Set(uri2), uri2),
        (Set(uri3), uri3),
      ))
    } yield ()
  }


  "min" - types {
    val (a, b) = ((1, uri1), (1, uri2))
    for {
      _ <- Entity.i.uri.insert(a, b).transact

      // 1 attribute
      _ <- Entity.uri(min).query.get.map(_ ==> List(uri1))

      _ <- Entity.uri(min)(uri1).query.get.map(_ ==> List(uri1))
      _ <- Entity.uri(min)(uri2).query.get.map(_ ==> List())

      _ <- Entity.uri(min).not(uri1).query.get.map(_ ==> List())
      _ <- Entity.uri(min).not(uri2).query.get.map(_ ==> List(uri1))

      _ <- Entity.uri(min).<(uri1).query.get.map(_ ==> List())
      _ <- Entity.uri(min).<(uri2).query.get.map(_ ==> List(uri1))

      _ <- Entity.uri(min).<=(uri0).query.get.map(_ ==> List())
      _ <- Entity.uri(min).<=(uri1).query.get.map(_ ==> List(uri1))

      _ <- Entity.uri(min).>(uri1).query.get.map(_ ==> List())
      _ <- Entity.uri(min).>(uri0).query.get.map(_ ==> List(uri1))

      _ <- Entity.uri(min).>=(uri1).query.get.map(_ ==> List(uri1))
      _ <- Entity.uri(min).>=(uri2).query.get.map(_ ==> List())

      // n attributes
      _ <- Entity.i.uri(min).query.get.map(_ ==> List(a))

      _ <- Entity.i.uri(min)(uri1).query.get.map(_ ==> List(a))
      _ <- Entity.i.uri(min)(uri2).query.get.map(_ ==> List())

      _ <- Entity.i.uri(min).not(uri1).query.get.map(_ ==> List())
      _ <- Entity.i.uri(min).not(uri2).query.get.map(_ ==> List(a))

      _ <- Entity.i.uri(min).<(uri1).query.get.map(_ ==> List())
      _ <- Entity.i.uri(min).<(uri2).query.get.map(_ ==> List(a))

      _ <- Entity.i.uri(min).<=(uri0).query.get.map(_ ==> List())
      _ <- Entity.i.uri(min).<=(uri1).query.get.map(_ ==> List(a))

      _ <- Entity.i.uri(min).>(uri1).query.get.map(_ ==> List())
      _ <- Entity.i.uri(min).>(uri0).query.get.map(_ ==> List(a))

      _ <- Entity.i.uri(min).>=(uri1).query.get.map(_ ==> List(a))
      _ <- Entity.i.uri(min).>=(uri2).query.get.map(_ ==> List())

      // Include aggregated attribute too (possible but will always be the same)
      _ <- Entity.uri.a1.uri(min).query.get.map(_ ==> List(
        (uri1, uri1),
        (uri2, uri2),
      ))
      _ <- Entity.uri(min).uri.a1.query.get.map(_ ==> List(
        (uri1, uri1),
        (uri2, uri2),
      ))
    } yield ()
  }


  "max" - types {
    val (a, b) = ((1, uri1), (1, uri2))
    for {
      _ <- Entity.i.uri.insert(a, b).transact

      // 1 attribute
      _ <- Entity.uri(max).query.get.map(_ ==> List(uri2))

      _ <- Entity.uri(max)(uri2).query.get.map(_ ==> List(uri2))
      _ <- Entity.uri(max)(uri1).query.get.map(_ ==> List())

      _ <- Entity.uri(max).not(uri2).query.get.map(_ ==> List())
      _ <- Entity.uri(max).not(uri1).query.get.map(_ ==> List(uri2))

      _ <- Entity.uri(max).<(uri2).query.get.map(_ ==> List())
      _ <- Entity.uri(max).<(uri3).query.get.map(_ ==> List(uri2))

      _ <- Entity.uri(max).<=(uri1).query.get.map(_ ==> List())
      _ <- Entity.uri(max).<=(uri2).query.get.map(_ ==> List(uri2))

      _ <- Entity.uri(max).>(uri2).query.get.map(_ ==> List())
      _ <- Entity.uri(max).>(uri1).query.get.map(_ ==> List(uri2))

      _ <- Entity.uri(max).>=(uri2).query.get.map(_ ==> List(uri2))
      _ <- Entity.uri(max).>=(uri3).query.get.map(_ ==> List())

      // n attributes
      _ <- Entity.i.uri(max).query.get.map(_ ==> List(b))

      _ <- Entity.i.uri(max)(uri2).query.get.map(_ ==> List(b))
      _ <- Entity.i.uri(max)(uri1).query.get.map(_ ==> List())

      _ <- Entity.i.uri(max).not(uri2).query.get.map(_ ==> List())
      _ <- Entity.i.uri(max).not(uri1).query.get.map(_ ==> List(b))

      _ <- Entity.i.uri(max).<(uri2).query.get.map(_ ==> List())
      _ <- Entity.i.uri(max).<(uri3).query.get.map(_ ==> List(b))

      _ <- Entity.i.uri(max).<=(uri1).query.get.map(_ ==> List())
      _ <- Entity.i.uri(max).<=(uri2).query.get.map(_ ==> List(b))

      _ <- Entity.i.uri(max).>(uri2).query.get.map(_ ==> List())
      _ <- Entity.i.uri(max).>(uri1).query.get.map(_ ==> List(b))

      _ <- Entity.i.uri(max).>=(uri2).query.get.map(_ ==> List(b))
      _ <- Entity.i.uri(max).>=(uri3).query.get.map(_ ==> List())

      // Include aggregated attribute too (possible but will always be the same)
      _ <- Entity.uri.a1.uri(max).query.get.map(_ ==> List(
        (uri1, uri1),
        (uri2, uri2),
      ))
      _ <- Entity.uri(max).uri.a1.query.get.map(_ ==> List(
        (uri1, uri1),
        (uri2, uri2),
      ))
    } yield ()
  }


  "min/max" - types {
    for {
      _ <- Entity.i.uri.insert(
        (1, uri1),
        (1, uri2),
        (2, uri3),
        (2, uri4),
      ).transact

      _ <- Entity.uri(min).uri(max).query.get.map(_ ==> List((uri1, uri4)))

      _ <- Entity.uri(min)(uri1).uri(max)(uri4).query.get.map(_ ==> List((uri1, uri4)))
      _ <- Entity.uri(min)(uri1).uri(max)(uri5).query.get.map(_ ==> List())

      _ <- Entity.uri(min).not(uri2).uri(max).not(uri3).query.get.map(_ ==> List((uri1, uri4)))
      _ <- Entity.uri(min).not(uri2).uri(max).not(uri4).query.get.map(_ ==> List())

      _ <- Entity.uri(min).<(uri2).uri(max).>(uri3).query.get.map(_ ==> List((uri1, uri4)))
      _ <- Entity.uri(min).<(uri2).uri(max).>(uri4).query.get.map(_ ==> List())

      _ <- Entity.uri(min).<=(uri1).uri(max).>=(uri4).query.get.map(_ ==> List((uri1, uri4)))
      _ <- Entity.uri(min).<=(uri1).uri(max).>=(uri5).query.get.map(_ ==> List())
    } yield ()
  }


  "min/max n" - types {
    for {
      _ <- Entity.i.uri.insert(
        (1, uri1),
        (1, uri2),
        (1, uri3),
        (2, uri4),
        (2, uri5),
        (2, uri6),
        (2, uri6), // (make sure grouped values coalesce)
      ).transact

      _ <- Entity.uri(min(1)).query.get.map(_ ==> List(Set(uri1)))
      _ <- Entity.uri(min(2)).query.get.map(_ ==> List(Set(uri1, uri2)))

      _ <- Entity.uri(max(1)).query.get.map(_ ==> List(Set(uri6)))
      _ <- Entity.uri(max(2)).query.get.map(_ ==> List(Set(uri5, uri6)))

      _ <- Entity.i.a1.uri(min(2)).query.get.map(_ ==> List(
        (1, Set(uri1, uri2)),
        (2, Set(uri4, uri5))
      ))

      _ <- Entity.i.a1.uri(max(2)).query.get.map(_ ==> List(
        (1, Set(uri2, uri3)),
        (2, Set(uri5, uri6))
      ))

      _ <- Entity.i.a1.uri(min(2)).uri(max(2)).query.get.map(_ ==> List(
        (1, Set(uri1, uri2), Set(uri2, uri3)),
        (2, Set(uri4, uri5), Set(uri5, uri6))
      ))

      // Include aggregated attribute too (possible but will always be the same)
      _ <- Entity.uri.a1.uri(min(2)).query.get.map(_ ==> List(
        (uri1, Set(uri1)),
        (uri2, Set(uri2)),
        (uri3, Set(uri3)),
        (uri4, Set(uri4)),
        (uri5, Set(uri5)),
        (uri6, Set(uri6)),
      ))
      _ <- Entity.uri(min(2)).uri.a1.query.get.map(_ ==> List(
        (Set(uri1), uri1),
        (Set(uri2), uri2),
        (Set(uri3), uri3),
        (Set(uri4), uri4),
        (Set(uri5), uri5),
        (Set(uri6), uri6),
      ))

      _ <- Entity.uri.a1.uri(max(2)).query.get.map(_ ==> List(
        (uri1, Set(uri1)),
        (uri2, Set(uri2)),
        (uri3, Set(uri3)),
        (uri4, Set(uri4)),
        (uri5, Set(uri5)),
        (uri6, Set(uri6)),
      ))
      _ <- Entity.uri(max(2)).uri.a1.query.get.map(_ ==> List(
        (Set(uri1), uri1),
        (Set(uri2), uri2),
        (Set(uri3), uri3),
        (Set(uri4), uri4),
        (Set(uri5), uri5),
        (Set(uri6), uri6),
      ))
    } yield ()
  }


  "sample" - types {
    val all      = Set(uri1, uri2, uri3)
    val allPairs = List((1, uri1), (2, uri2), (3, uri3))
    for {
      _ <- Entity.i.uri.insert(allPairs).transact

      // 1 attribute
      _ <- Entity.uri(sample).query.get.map(res => all.contains(res.head) ==> true)

      // 1 attribute
      _ <- Entity.i.uri(sample).query.get.map(res => allPairs.contains(res.head) ==> true)
    } yield ()
  }

  "sample ops" - types {
    if (Seq("mariadb", "mysql").contains(database)) {
      Entity.uri(sample)(uri1).query.get
        .map(_ ==> "Unexpected success").recover { case ModelError(err) =>
          err ==> "Operations on sample not implemented for this database."
        }
    } else {
      val all       = Set(uri1, uri2, uri3)
      val (a, b, c) = ((1, uri1), (2, uri2), (3, uri3))
      val allPairs  = List(a, b, c)
      for {
        _ <- Entity.i.uri.insert(allPairs).transact

        // 1 attribute
        // Checking for equality on a sample doesn't make sense
        // _ <- Entity.uri(sample)(uri2).query.get.map(res => all.contains(res.head) ==> true)
        // If you want a specific value, this would be the natural query
        _ <- Entity.uri(uri2).query.get.map(_ ==> List(uri2))

        _ <- Entity.uri(sample).not(uri2).query.get.map { res =>
          List(uri1, uri3).contains(res.head) ==> true
          (res.head == uri2) ==> false
        }
        _ <- Entity.uri(sample).<(uri3).query.get.map { res =>
          List(uri1, uri2).contains(res.head) ==> true
          (res.head == uri3) ==> false
        }
        _ <- Entity.uri(sample).<=(uri2).query.get.map { res =>
          List(uri1, uri2).contains(res.head) ==> true
          (res.head == uri3) ==> false
        }
        _ <- Entity.uri(sample).>(uri1).query.get.map { res =>
          List(uri2, uri3).contains(res.head) ==> true
          (res.head == uri1) ==> false
        }
        _ <- Entity.uri(sample).>=(uri2).query.get.map { res =>
          List(uri2, uri3).contains(res.head) ==> true
          (res.head == uri1) ==> false
        }

        // 1 attribute
        _ <- Entity.i.uri(sample).not(uri2).query.get.map { res =>
          List(a, c).contains(res.head) ==> true
          (res.head == b) ==> false
        }
        _ <- Entity.i.uri(sample).<(uri3).query.get.map { res =>
          List(a, b).contains(res.head) ==> true
          (res.head == c) ==> false
        }
        _ <- Entity.i.uri(sample).<=(uri2).query.get.map { res =>
          List(a, b).contains(res.head) ==> true
          (res.head == c) ==> false
        }
        _ <- Entity.i.uri(sample).>(uri1).query.get.map { res =>
          List(b, c).contains(res.head) ==> true
          (res.head == a) ==> false
        }
        _ <- Entity.i.uri(sample).>=(uri2).query.get.map { res =>
          List(b, c).contains(res.head) ==> true
          (res.head == a) ==> false
        }
      } yield ()
    }
  }


  "samples(n)" - types {
    val all = Set(uri1, uri2, uri3)
    for {
      _ <- Entity.uri.insert(List(uri1, uri2, uri3)).transact
      _ <- Entity.uri(sample(1)).query.get.map(res => all.intersect(res.head).nonEmpty ==> true)
      _ <- Entity.uri(sample(2)).query.get.map(res => all.intersect(res.head).nonEmpty ==> true)
    } yield ()
  }


  "count" - types {
    val (a, b) = ((1, 1), (2, 3))
    for {
      _ <- Entity.i.uri.insert(List(
        (1, uri1),
        (2, uri2),
        (2, uri2),
        (2, uri3),
      )).transact

      // 1 attribute
      _ <- Entity.uri(count).query.get.map(_ ==> List(4))

      _ <- Entity.uri(count)(3).query.get.map(_ ==> List())
      _ <- Entity.uri(count)(4).query.get.map(_ ==> List(4))

      _ <- Entity.uri(count).not(3).query.get.map(_ ==> List(4))
      _ <- Entity.uri(count).not(4).query.get.map(_ ==> List())

      _ <- Entity.uri(count).<(5).query.get.map(_ ==> List(4))
      _ <- Entity.uri(count).<(4).query.get.map(_ ==> List())

      _ <- Entity.uri(count).<=(4).query.get.map(_ ==> List(4))
      _ <- Entity.uri(count).<=(3).query.get.map(_ ==> List())

      _ <- Entity.uri(count).>(3).query.get.map(_ ==> List(4))
      _ <- Entity.uri(count).>(4).query.get.map(_ ==> List())

      _ <- Entity.uri(count).>=(4).query.get.map(_ ==> List(4))
      _ <- Entity.uri(count).>=(5).query.get.map(_ ==> List())

      // n attributes
      _ <- Entity.i.a1.uri(count).query.get.map(_ ==> List(a, b))

      _ <- Entity.i.a1.uri(count)(3).query.get.map(_ ==> List(b))
      _ <- Entity.i.a1.uri(count)(2).query.get.map(_ ==> List())

      _ <- Entity.i.a1.uri(count).not(3).query.get.map(_ ==> List(a))
      _ <- Entity.i.a1.uri(count).not(2).query.get.map(_ ==> List(a, b))

      _ <- Entity.i.a1.uri(count).<(3).query.get.map(_ ==> List(a))
      _ <- Entity.i.a1.uri(count).<(4).query.get.map(_ ==> List(a, b))

      _ <- Entity.i.a1.uri(count).<=(3).query.get.map(_ ==> List(a, b))
      _ <- Entity.i.a1.uri(count).<=(2).query.get.map(_ ==> List(a))

      _ <- Entity.i.a1.uri(count).>(2).query.get.map(_ ==> List(b))
      _ <- Entity.i.a1.uri(count).>(3).query.get.map(_ ==> List())

      _ <- Entity.i.a1.uri(count).>=(3).query.get.map(_ ==> List(b))
      _ <- Entity.i.a1.uri(count).>=(4).query.get.map(_ ==> List())

      // Include aggregated attribute too
      _ <- Entity.uri.a1.uri(count).query.get.map(_ ==> List(
        (uri1, 1),
        (uri2, 2),
        (uri3, 1),
      ))
      _ <- Entity.uri(count).uri.a1.query.get.map(_ ==> List(
        (1, uri1),
        (2, uri2),
        (1, uri3),
      ))
    } yield ()
  }


  "countDistinct" - types {
    val (a, b) = ((1, 1), (2, 2))
    for {
      _ <- Entity.i.uri.insert(List(
        (1, uri1),
        (2, uri2),
        (2, uri2),
        (2, uri3),
      )).transact

      // 1 attribute
      _ <- Entity.uri(countDistinct).query.get.map(_ ==> List(3))

      _ <- Entity.uri(countDistinct)(2).query.get.map(_ ==> List())
      _ <- Entity.uri(countDistinct)(3).query.get.map(_ ==> List(3))

      _ <- Entity.uri(countDistinct).not(2).query.get.map(_ ==> List(3))
      _ <- Entity.uri(countDistinct).not(3).query.get.map(_ ==> List())

      _ <- Entity.uri(countDistinct).<(4).query.get.map(_ ==> List(3))
      _ <- Entity.uri(countDistinct).<(3).query.get.map(_ ==> List())

      _ <- Entity.uri(countDistinct).<=(3).query.get.map(_ ==> List(3))
      _ <- Entity.uri(countDistinct).<=(2).query.get.map(_ ==> List())

      _ <- Entity.uri(countDistinct).>(2).query.get.map(_ ==> List(3))
      _ <- Entity.uri(countDistinct).>(3).query.get.map(_ ==> List())

      _ <- Entity.uri(countDistinct).>=(3).query.get.map(_ ==> List(3))
      _ <- Entity.uri(countDistinct).>=(4).query.get.map(_ ==> List())

      // n attributes
      _ <- Entity.i.a1.uri(countDistinct).query.get.map(_ ==> List(a, b))

      _ <- Entity.i.a1.uri(countDistinct)(2).query.get.map(_ ==> List(b))
      _ <- Entity.i.a1.uri(countDistinct)(3).query.get.map(_ ==> List())

      _ <- Entity.i.a1.uri(countDistinct).not(2).query.get.map(_ ==> List(a))
      _ <- Entity.i.a1.uri(countDistinct).not(3).query.get.map(_ ==> List(a, b))

      _ <- Entity.i.a1.uri(countDistinct).<(2).query.get.map(_ ==> List(a))
      _ <- Entity.i.a1.uri(countDistinct).<(3).query.get.map(_ ==> List(a, b))

      _ <- Entity.i.a1.uri(countDistinct).<=(2).query.get.map(_ ==> List(a, b))
      _ <- Entity.i.a1.uri(countDistinct).<=(1).query.get.map(_ ==> List(a))

      _ <- Entity.i.a1.uri(countDistinct).>(1).query.get.map(_ ==> List(b))
      _ <- Entity.i.a1.uri(countDistinct).>(2).query.get.map(_ ==> List())

      _ <- Entity.i.a1.uri(countDistinct).>=(2).query.get.map(_ ==> List(b))
      _ <- Entity.i.a1.uri(countDistinct).>=(3).query.get.map(_ ==> List())

      // Include aggregated attribute too
      _ <- Entity.uri.a1.uri(countDistinct).query.get.map(_ ==> List(
        (uri1, 1),
        (uri2, 1),
        (uri3, 1),
      ))
      _ <- Entity.uri(countDistinct).uri.a1.query.get.map(_ ==> List(
        (1, uri1),
        (1, uri2),
        (1, uri3),
      ))
    } yield ()
  }
}