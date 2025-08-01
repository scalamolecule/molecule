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

case class Aggr_Char_(
  suite: MUnit,
  api: Api_async & Spi_async & DbProviders
) extends TestUtils {

  import api.*
  import suite.*

  "distinct" - types {
    for {
      _ <- Entity.i.char.insert(List(
        (1, char1),
        (2, char2),
        (2, char2),
        (2, char3),
      )).transact

      _ <- Entity.i.char.a1.query.get.map(_ ==> List(
        (1, char1),
        (2, char2), // 2 rows coalesced
        (2, char3),
      ))

      // Distinct values are returned in a Set
      _ <- Entity.i.a1.char(distinct).query.get.map(_ ==> List(
        (1, Set(char1)),
        (2, Set(char2, char3)),
      ))

      _ <- Entity.char(distinct).query.get.map(_.head ==> Set(
        char1, char2, char3
      ))
    } yield ()
  }


  "min" - types {
    val (a, b) = ((1, char1), (1, char2))
    for {
      _ <- Entity.i.char.insert(a, b).transact

      // 1 attribute
      _ <- Entity.char(min).query.get.map(_ ==> List(char1))

      _ <- Entity.char(min)(char1).query.get.map(_ ==> List(char1))
      _ <- Entity.char(min)(char2).query.get.map(_ ==> List())

      _ <- Entity.char(min).not(char1).query.get.map(_ ==> List())
      _ <- Entity.char(min).not(char2).query.get.map(_ ==> List(char1))

      _ <- Entity.char(min).<(char1).query.get.map(_ ==> List())
      _ <- Entity.char(min).<(char2).query.get.map(_ ==> List(char1))

      _ <- Entity.char(min).<=(char0).query.get.map(_ ==> List())
      _ <- Entity.char(min).<=(char1).query.get.map(_ ==> List(char1))

      _ <- Entity.char(min).>(char1).query.get.map(_ ==> List())
      _ <- Entity.char(min).>(char0).query.get.map(_ ==> List(char1))

      _ <- Entity.char(min).>=(char1).query.get.map(_ ==> List(char1))
      _ <- Entity.char(min).>=(char2).query.get.map(_ ==> List())


      // n attributes
      _ <- Entity.i.char(min).query.get.map(_ ==> List(a))

      _ <- Entity.i.char(min)(char1).query.get.map(_ ==> List(a))
      _ <- Entity.i.char(min)(char2).query.get.map(_ ==> List())

      _ <- Entity.i.char(min).not(char1).query.get.map(_ ==> List())
      _ <- Entity.i.char(min).not(char2).query.get.map(_ ==> List(a))

      _ <- Entity.i.char(min).<(char1).query.get.map(_ ==> List())
      _ <- Entity.i.char(min).<(char2).query.get.map(_ ==> List(a))

      _ <- Entity.i.char(min).<=(char0).query.get.map(_ ==> List())
      _ <- Entity.i.char(min).<=(char1).query.get.map(_ ==> List(a))

      _ <- Entity.i.char(min).>(char1).query.get.map(_ ==> List())
      _ <- Entity.i.char(min).>(char0).query.get.map(_ ==> List(a))

      _ <- Entity.i.char(min).>=(char1).query.get.map(_ ==> List(a))
      _ <- Entity.i.char(min).>=(char2).query.get.map(_ ==> List())
    } yield ()
  }


  "max" - types {
    val (a, b) = ((1, char1), (1, char2))
    for {
      _ <- Entity.i.char.insert(a, b).transact

      // 1 attribute
      _ <- Entity.char(max).query.get.map(_ ==> List(char2))

      _ <- Entity.char(max)(char2).query.get.map(_ ==> List(char2))
      _ <- Entity.char(max)(char1).query.get.map(_ ==> List())

      _ <- Entity.char(max).not(char2).query.get.map(_ ==> List())
      _ <- Entity.char(max).not(char1).query.get.map(_ ==> List(char2))

      _ <- Entity.char(max).<(char2).query.get.map(_ ==> List())
      _ <- Entity.char(max).<(char3).query.get.map(_ ==> List(char2))

      _ <- Entity.char(max).<=(char1).query.get.map(_ ==> List())
      _ <- Entity.char(max).<=(char2).query.get.map(_ ==> List(char2))

      _ <- Entity.char(max).>(char2).query.get.map(_ ==> List())
      _ <- Entity.char(max).>(char1).query.get.map(_ ==> List(char2))

      _ <- Entity.char(max).>=(char2).query.get.map(_ ==> List(char2))
      _ <- Entity.char(max).>=(char3).query.get.map(_ ==> List())


      // n attributes
      _ <- Entity.i.char(max).query.get.map(_ ==> List(b))

      _ <- Entity.i.char(max)(char2).query.get.map(_ ==> List(b))
      _ <- Entity.i.char(max)(char1).query.get.map(_ ==> List())

      _ <- Entity.i.char(max).not(char2).query.get.map(_ ==> List())
      _ <- Entity.i.char(max).not(char1).query.get.map(_ ==> List(b))

      _ <- Entity.i.char(max).<(char2).query.get.map(_ ==> List())
      _ <- Entity.i.char(max).<(char3).query.get.map(_ ==> List(b))

      _ <- Entity.i.char(max).<=(char1).query.get.map(_ ==> List())
      _ <- Entity.i.char(max).<=(char2).query.get.map(_ ==> List(b))

      _ <- Entity.i.char(max).>(char2).query.get.map(_ ==> List())
      _ <- Entity.i.char(max).>(char1).query.get.map(_ ==> List(b))

      _ <- Entity.i.char(max).>=(char2).query.get.map(_ ==> List(b))
      _ <- Entity.i.char(max).>=(char3).query.get.map(_ ==> List())
    } yield ()
  }


  "min/max" - types {
    for {
      _ <- Entity.i.char.insert(
        (1, char1),
        (1, char2),
        (2, char3),
        (2, char4),
      ).transact

      _ <- Entity.char(min).char(max).query.get.map(_ ==> List((char1, char4)))

      _ <- Entity.char(min)(char1).char(max)(char4).query.get.map(_ ==> List((char1, char4)))
      _ <- Entity.char(min)(char1).char(max)(char5).query.get.map(_ ==> List())

      _ <- Entity.char(min).not(char2).char(max).not(char3).query.get.map(_ ==> List((char1, char4)))
      _ <- Entity.char(min).not(char2).char(max).not(char4).query.get.map(_ ==> List())

      _ <- Entity.char(min).<(char2).char(max).>(char3).query.get.map(_ ==> List((char1, char4)))
      _ <- Entity.char(min).<(char2).char(max).>(char4).query.get.map(_ ==> List())

      _ <- Entity.char(min).<=(char1).char(max).>=(char4).query.get.map(_ ==> List((char1, char4)))
      _ <- Entity.char(min).<=(char1).char(max).>=(char5).query.get.map(_ ==> List())
    } yield ()
  }


  "min/max n" - types {
    for {
      _ <- Entity.i.char.insert(
        (1, char1),
        (1, char2),
        (1, char3),
        (2, char4),
        (2, char5),
        (2, char6),
        (2, char6), // (make sure grouped values coalesce)
      ).transact

      _ <- Entity.char(min(1)).query.get.map(_ ==> List(Set(char1)))
      _ <- Entity.char(min(2)).query.get.map(_ ==> List(Set(char1, char2)))

      _ <- Entity.char(max(1)).query.get.map(_ ==> List(Set(char6)))
      _ <- Entity.char(max(2)).query.get.map(_ ==> List(Set(char5, char6)))

      _ <- Entity.i.a1.char(min(2)).query.get.map(_ ==> List(
        (1, Set(char1, char2)),
        (2, Set(char4, char5))
      ))

      _ <- Entity.i.a1.char(max(2)).query.get.map(_ ==> List(
        (1, Set(char2, char3)),
        (2, Set(char5, char6))
      ))

      _ <- Entity.i.a1.char(min(2)).char(max(2)).query.get.map(_ ==> List(
        (1, Set(char1, char2), Set(char2, char3)),
        (2, Set(char4, char5), Set(char5, char6))
      ))
    } yield ()
  }


  "sample" - types {
    val all      = Set(char1, char2, char3)
    val allPairs = List((1, char1), (2, char2), (3, char3))
    for {
      _ <- Entity.i.char.insert(allPairs).transact

      // 1 attribute
      _ <- Entity.char(sample).query.get.map(res => all.contains(res.head) ==> true)

      // 1 attribute
      _ <- Entity.i.char(sample).query.get.map(res => allPairs.contains(res.head) ==> true)
    } yield ()
  }

  "sample ops" - types {
    if (Seq("mariadb", "mysql").contains(database)) {
      Entity.char(sample)(char1).query.get
        .map(_ ==> "Unexpected success").recover { case ModelError(err) =>
          err ==> "Operations on sample not implemented for this database."
        }
    } else {
      val all       = Set(char1, char2, char3)
      val (a, b, c) = ((1, char1), (2, char2), (3, char3))
      val allPairs  = List(a, b, c)
      for {
        _ <- Entity.i.char.insert(allPairs).transact

        // 1 attribute
        // Checking for equality on a sample doesn't make sense
        // _ <- Entity.char(sample)(char2).query.get.map(res => all.contains(res.head) ==> true)
        // If you want a specific value, this would be the natural query
        _ <- Entity.char(char2).query.get.map(_ ==> List(char2))

        _ <- Entity.char(sample).not(char2).query.get.map { res =>
          List(char1, char3).contains(res.head) ==> true
          (res.head == char2) ==> false
        }
        _ <- Entity.char(sample).<(char3).query.get.map { res =>
          List(char1, char2).contains(res.head) ==> true
          (res.head == char3) ==> false
        }
        _ <- Entity.char(sample).<=(char2).query.get.map { res =>
          List(char1, char2).contains(res.head) ==> true
          (res.head == char3) ==> false
        }
        _ <- Entity.char(sample).>(char1).query.get.map { res =>
          List(char2, char3).contains(res.head) ==> true
          (res.head == char1) ==> false
        }
        _ <- Entity.char(sample).>=(char2).query.get.map { res =>
          List(char2, char3).contains(res.head) ==> true
          (res.head == char1) ==> false
        }

        // 1 attribute
        _ <- Entity.i.char(sample).not(char2).query.get.map { res =>
          List(a, c).contains(res.head) ==> true
          (res.head == b) ==> false
        }
        _ <- Entity.i.char(sample).<(char3).query.get.map { res =>
          List(a, b).contains(res.head) ==> true
          (res.head == c) ==> false
        }
        _ <- Entity.i.char(sample).<=(char2).query.get.map { res =>
          List(a, b).contains(res.head) ==> true
          (res.head == c) ==> false
        }
        _ <- Entity.i.char(sample).>(char1).query.get.map { res =>
          List(b, c).contains(res.head) ==> true
          (res.head == a) ==> false
        }
        _ <- Entity.i.char(sample).>=(char2).query.get.map { res =>
          List(b, c).contains(res.head) ==> true
          (res.head == a) ==> false
        }
      } yield ()
    }
  }


  "samples(n)" - types {
    val all = Set(char1, char2, char3)
    for {
      _ <- Entity.char.insert(List(char1, char2, char3)).transact
      _ <- Entity.char(sample(1)).query.get.map(res => all.intersect(res.head).nonEmpty ==> true)
      _ <- Entity.char(sample(2)).query.get.map(res => all.intersect(res.head).nonEmpty ==> true)
    } yield ()
  }


  "count" - types {
    val (a, b) = ((1, 1), (2, 3))
    for {
      _ <- Entity.i.char.insert(List(
        (1, char1),
        (2, char2),
        (2, char2),
        (2, char3),
      )).transact

      // 1 attribute
      _ <- Entity.char(count).query.get.map(_ ==> List(4))

      _ <- Entity.char(count)(3).query.get.map(_ ==> List())
      _ <- Entity.char(count)(4).query.get.map(_ ==> List(4))

      _ <- Entity.char(count).not(3).query.get.map(_ ==> List(4))
      _ <- Entity.char(count).not(4).query.get.map(_ ==> List())

      _ <- Entity.char(count).<(5).query.get.map(_ ==> List(4))
      _ <- Entity.char(count).<(4).query.get.map(_ ==> List())

      _ <- Entity.char(count).<=(4).query.get.map(_ ==> List(4))
      _ <- Entity.char(count).<=(3).query.get.map(_ ==> List())

      _ <- Entity.char(count).>(3).query.get.map(_ ==> List(4))
      _ <- Entity.char(count).>(4).query.get.map(_ ==> List())

      _ <- Entity.char(count).>=(4).query.get.map(_ ==> List(4))
      _ <- Entity.char(count).>=(5).query.get.map(_ ==> List())


      // n attributes
      _ <- Entity.i.a1.char(count).query.get.map(_ ==> List(a, b))

      _ <- Entity.i.a1.char(count)(3).query.get.map(_ ==> List(b))
      _ <- Entity.i.a1.char(count)(2).query.get.map(_ ==> List())

      _ <- Entity.i.a1.char(count).not(3).query.get.map(_ ==> List(a))
      _ <- Entity.i.a1.char(count).not(2).query.get.map(_ ==> List(a, b))

      _ <- Entity.i.a1.char(count).<(3).query.get.map(_ ==> List(a))
      _ <- Entity.i.a1.char(count).<(4).query.get.map(_ ==> List(a, b))

      _ <- Entity.i.a1.char(count).<=(3).query.get.map(_ ==> List(a, b))
      _ <- Entity.i.a1.char(count).<=(2).query.get.map(_ ==> List(a))

      _ <- Entity.i.a1.char(count).>(2).query.get.map(_ ==> List(b))
      _ <- Entity.i.a1.char(count).>(3).query.get.map(_ ==> List())

      _ <- Entity.i.a1.char(count).>=(3).query.get.map(_ ==> List(b))
      _ <- Entity.i.a1.char(count).>=(4).query.get.map(_ ==> List())
    } yield ()
  }


  "countDistinct" - types {
    val (a, b) = ((1, 1), (2, 2))
    for {
      _ <- Entity.i.char.insert(List(
        (1, char1),
        (2, char2),
        (2, char2),
        (2, char3),
      )).transact

      // 1 attribute
      _ <- Entity.char(countDistinct).query.get.map(_ ==> List(3))

      _ <- Entity.char(countDistinct)(2).query.get.map(_ ==> List())
      _ <- Entity.char(countDistinct)(3).query.get.map(_ ==> List(3))

      _ <- Entity.char(countDistinct).not(2).query.get.map(_ ==> List(3))
      _ <- Entity.char(countDistinct).not(3).query.get.map(_ ==> List())

      _ <- Entity.char(countDistinct).<(4).query.get.map(_ ==> List(3))
      _ <- Entity.char(countDistinct).<(3).query.get.map(_ ==> List())

      _ <- Entity.char(countDistinct).<=(3).query.get.map(_ ==> List(3))
      _ <- Entity.char(countDistinct).<=(2).query.get.map(_ ==> List())

      _ <- Entity.char(countDistinct).>(2).query.get.map(_ ==> List(3))
      _ <- Entity.char(countDistinct).>(3).query.get.map(_ ==> List())

      _ <- Entity.char(countDistinct).>=(3).query.get.map(_ ==> List(3))
      _ <- Entity.char(countDistinct).>=(4).query.get.map(_ ==> List())


      // n attributes
      _ <- Entity.i.a1.char(countDistinct).query.get.map(_ ==> List(a, b))

      _ <- Entity.i.a1.char(countDistinct)(2).query.get.map(_ ==> List(b))
      _ <- Entity.i.a1.char(countDistinct)(3).query.get.map(_ ==> List())

      _ <- Entity.i.a1.char(countDistinct).not(2).query.get.map(_ ==> List(a))
      _ <- Entity.i.a1.char(countDistinct).not(3).query.get.map(_ ==> List(a, b))

      _ <- Entity.i.a1.char(countDistinct).<(2).query.get.map(_ ==> List(a))
      _ <- Entity.i.a1.char(countDistinct).<(3).query.get.map(_ ==> List(a, b))

      _ <- Entity.i.a1.char(countDistinct).<=(2).query.get.map(_ ==> List(a, b))
      _ <- Entity.i.a1.char(countDistinct).<=(1).query.get.map(_ ==> List(a))

      _ <- Entity.i.a1.char(countDistinct).>(1).query.get.map(_ ==> List(b))
      _ <- Entity.i.a1.char(countDistinct).>(2).query.get.map(_ ==> List())

      _ <- Entity.i.a1.char(countDistinct).>=(2).query.get.map(_ ==> List(b))
      _ <- Entity.i.a1.char(countDistinct).>=(3).query.get.map(_ ==> List())
    } yield ()
  }
}