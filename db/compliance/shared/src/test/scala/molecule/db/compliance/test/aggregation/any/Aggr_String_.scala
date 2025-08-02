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

case class Aggr_String_(
  suite: MUnit,
  api: Api_async & Spi_async & DbProviders
) extends TestUtils {

  import api.*
  import suite.*

  "distinct" - types {
    for {
      _ <- Entity.i.string.insert(List(
        (1, string1),
        (2, string2),
        (2, string2),
        (2, string3),
      )).transact

      _ <- Entity.i.string.a1.query.get.map(_ ==> List(
        (1, string1),
        (2, string2), // 2 rows coalesced
        (2, string3),
      ))

      // Distinct values are returned in a Set
      _ <- Entity.i.a1.string(distinct).query.get.map(_ ==> List(
        (1, Set(string1)),
        (2, Set(string2, string3)),
      ))

      _ <- Entity.string(distinct).query.get.map(_.head ==> Set(
        string1, string2, string3
      ))

      // Include aggregated attribute too (possible but will always be the same)
      _ <- Entity.string.a1.string(distinct).query.get.map(_ ==> List(
        (string1, Set(string1)),
        (string2, Set(string2)),
        (string3, Set(string3)),
      ))
      _ <- Entity.string(distinct).string.a1.query.get.map(_ ==> List(
        (Set(string1), string1),
        (Set(string2), string2),
        (Set(string3), string3),
      ))
    } yield ()
  }


  "min" - types {
    val (a, b) = ((1, string1), (1, string2))
    for {
      _ <- Entity.i.string.insert(a, b).transact

      // 1 attribute
      _ <- Entity.string(min).query.get.map(_ ==> List(string1))

      _ <- Entity.string(min)(string1).query.get.map(_ ==> List(string1))
      _ <- Entity.string(min)(string2).query.get.map(_ ==> List())

      _ <- Entity.string(min).not(string1).query.get.map(_ ==> List())
      _ <- Entity.string(min).not(string2).query.get.map(_ ==> List(string1))

      _ <- Entity.string(min).<(string1).query.get.map(_ ==> List())
      _ <- Entity.string(min).<(string2).query.get.map(_ ==> List(string1))

      _ <- Entity.string(min).<=(string0).query.get.map(_ ==> List())
      _ <- Entity.string(min).<=(string1).query.get.map(_ ==> List(string1))

      _ <- Entity.string(min).>(string1).query.get.map(_ ==> List())
      _ <- Entity.string(min).>(string0).query.get.map(_ ==> List(string1))

      _ <- Entity.string(min).>=(string1).query.get.map(_ ==> List(string1))
      _ <- Entity.string(min).>=(string2).query.get.map(_ ==> List())

      // n attributes
      _ <- Entity.i.string(min).query.get.map(_ ==> List(a))

      _ <- Entity.i.string(min)(string1).query.get.map(_ ==> List(a))
      _ <- Entity.i.string(min)(string2).query.get.map(_ ==> List())

      _ <- Entity.i.string(min).not(string1).query.get.map(_ ==> List())
      _ <- Entity.i.string(min).not(string2).query.get.map(_ ==> List(a))

      _ <- Entity.i.string(min).<(string1).query.get.map(_ ==> List())
      _ <- Entity.i.string(min).<(string2).query.get.map(_ ==> List(a))

      _ <- Entity.i.string(min).<=(string0).query.get.map(_ ==> List())
      _ <- Entity.i.string(min).<=(string1).query.get.map(_ ==> List(a))

      _ <- Entity.i.string(min).>(string1).query.get.map(_ ==> List())
      _ <- Entity.i.string(min).>(string0).query.get.map(_ ==> List(a))

      _ <- Entity.i.string(min).>=(string1).query.get.map(_ ==> List(a))
      _ <- Entity.i.string(min).>=(string2).query.get.map(_ ==> List())

      // Include aggregated attribute too (possible but will always be the same)
      _ <- Entity.string.a1.string(min).query.get.map(_ ==> List(
        (string1, string1),
        (string2, string2),
      ))
      _ <- Entity.string(min).string.a1.query.get.map(_ ==> List(
        (string1, string1),
        (string2, string2),
      ))
    } yield ()
  }


  "max" - types {
    val (a, b) = ((1, string1), (1, string2))
    for {
      _ <- Entity.i.string.insert(a, b).transact

      // 1 attribute
      _ <- Entity.string(max).query.get.map(_ ==> List(string2))

      _ <- Entity.string(max)(string2).query.get.map(_ ==> List(string2))
      _ <- Entity.string(max)(string1).query.get.map(_ ==> List())

      _ <- Entity.string(max).not(string2).query.get.map(_ ==> List())
      _ <- Entity.string(max).not(string1).query.get.map(_ ==> List(string2))

      _ <- Entity.string(max).<(string2).query.get.map(_ ==> List())
      _ <- Entity.string(max).<(string3).query.get.map(_ ==> List(string2))

      _ <- Entity.string(max).<=(string1).query.get.map(_ ==> List())
      _ <- Entity.string(max).<=(string2).query.get.map(_ ==> List(string2))

      _ <- Entity.string(max).>(string2).query.get.map(_ ==> List())
      _ <- Entity.string(max).>(string1).query.get.map(_ ==> List(string2))

      _ <- Entity.string(max).>=(string2).query.get.map(_ ==> List(string2))
      _ <- Entity.string(max).>=(string3).query.get.map(_ ==> List())

      // n attributes
      _ <- Entity.i.string(max).query.get.map(_ ==> List(b))

      _ <- Entity.i.string(max)(string2).query.get.map(_ ==> List(b))
      _ <- Entity.i.string(max)(string1).query.get.map(_ ==> List())

      _ <- Entity.i.string(max).not(string2).query.get.map(_ ==> List())
      _ <- Entity.i.string(max).not(string1).query.get.map(_ ==> List(b))

      _ <- Entity.i.string(max).<(string2).query.get.map(_ ==> List())
      _ <- Entity.i.string(max).<(string3).query.get.map(_ ==> List(b))

      _ <- Entity.i.string(max).<=(string1).query.get.map(_ ==> List())
      _ <- Entity.i.string(max).<=(string2).query.get.map(_ ==> List(b))

      _ <- Entity.i.string(max).>(string2).query.get.map(_ ==> List())
      _ <- Entity.i.string(max).>(string1).query.get.map(_ ==> List(b))

      _ <- Entity.i.string(max).>=(string2).query.get.map(_ ==> List(b))
      _ <- Entity.i.string(max).>=(string3).query.get.map(_ ==> List())

      // Include aggregated attribute too (possible but will always be the same)
      _ <- Entity.string.a1.string(max).query.get.map(_ ==> List(
        (string1, string1),
        (string2, string2),
      ))
      _ <- Entity.string(max).string.a1.query.get.map(_ ==> List(
        (string1, string1),
        (string2, string2),
      ))
    } yield ()
  }


  "min/max" - types {
    for {
      _ <- Entity.i.string.insert(
        (1, string1),
        (1, string2),
        (2, string3),
        (2, string4),
      ).transact

      _ <- Entity.string(min).string(max).query.get.map(_ ==> List((string1, string4)))

      _ <- Entity.string(min)(string1).string(max)(string4).query.get.map(_ ==> List((string1, string4)))
      _ <- Entity.string(min)(string1).string(max)(string5).query.get.map(_ ==> List())

      _ <- Entity.string(min).not(string2).string(max).not(string3).query.get.map(_ ==> List((string1, string4)))
      _ <- Entity.string(min).not(string2).string(max).not(string4).query.get.map(_ ==> List())

      _ <- Entity.string(min).<(string2).string(max).>(string3).query.get.map(_ ==> List((string1, string4)))
      _ <- Entity.string(min).<(string2).string(max).>(string4).query.get.map(_ ==> List())

      _ <- Entity.string(min).<=(string1).string(max).>=(string4).query.get.map(_ ==> List((string1, string4)))
      _ <- Entity.string(min).<=(string1).string(max).>=(string5).query.get.map(_ ==> List())
    } yield ()
  }


  "min/max n" - types {
    for {
      _ <- Entity.i.string.insert(
        (1, string1),
        (1, string2),
        (1, string3),
        (2, string4),
        (2, string5),
        (2, string6),
        (2, string6), // (make sure grouped values coalesce)
      ).transact

      _ <- Entity.string(min(1)).query.get.map(_ ==> List(Set(string1)))
      _ <- Entity.string(min(2)).query.get.map(_ ==> List(Set(string1, string2)))

      _ <- Entity.string(max(1)).query.get.map(_ ==> List(Set(string6)))
      _ <- Entity.string(max(2)).query.get.map(_ ==> List(Set(string5, string6)))

      _ <- Entity.i.a1.string(min(2)).query.get.map(_ ==> List(
        (1, Set(string1, string2)),
        (2, Set(string4, string5))
      ))

      _ <- Entity.i.a1.string(max(2)).query.get.map(_ ==> List(
        (1, Set(string2, string3)),
        (2, Set(string5, string6))
      ))

      _ <- Entity.i.a1.string(min(2)).string(max(2)).query.get.map(_ ==> List(
        (1, Set(string1, string2), Set(string2, string3)),
        (2, Set(string4, string5), Set(string5, string6))
      ))

      // Include aggregated attribute too (possible but will always be the same)
      _ <- Entity.string.a1.string(min(2)).query.get.map(_ ==> List(
        (string1, Set(string1)),
        (string2, Set(string2)),
        (string3, Set(string3)),
        (string4, Set(string4)),
        (string5, Set(string5)),
        (string6, Set(string6)),
      ))
      _ <- Entity.string(min(2)).string.a1.query.get.map(_ ==> List(
        (Set(string1), string1),
        (Set(string2), string2),
        (Set(string3), string3),
        (Set(string4), string4),
        (Set(string5), string5),
        (Set(string6), string6),
      ))

      _ <- Entity.string.a1.string(max(2)).query.get.map(_ ==> List(
        (string1, Set(string1)),
        (string2, Set(string2)),
        (string3, Set(string3)),
        (string4, Set(string4)),
        (string5, Set(string5)),
        (string6, Set(string6)),
      ))
      _ <- Entity.string(max(2)).string.a1.query.get.map(_ ==> List(
        (Set(string1), string1),
        (Set(string2), string2),
        (Set(string3), string3),
        (Set(string4), string4),
        (Set(string5), string5),
        (Set(string6), string6),
      ))
    } yield ()
  }


  "sample" - types {
    val all      = Set(string1, string2, string3)
    val allPairs = List((1, string1), (2, string2), (3, string3))
    for {
      _ <- Entity.i.string.insert(allPairs).transact

      // 1 attribute
      _ <- Entity.string(sample).query.get.map(res => all.contains(res.head) ==> true)

      // 1 attribute
      _ <- Entity.i.string(sample).query.get.map(res => allPairs.contains(res.head) ==> true)
    } yield ()
  }

  "sample ops" - types {
    if (Seq("mariadb", "mysql").contains(database)) {
      Entity.string(sample)(string1).query.get
        .map(_ ==> "Unexpected success").recover { case ModelError(err) =>
          err ==> "Operations on sample not implemented for this database."
        }
    } else {
      val all       = Set(string1, string2, string3)
      val (a, b, c) = ((1, string1), (2, string2), (3, string3))
      val allPairs  = List(a, b, c)
      for {
        _ <- Entity.i.string.insert(allPairs).transact

        // 1 attribute
        // Checking for equality on a sample doesn't make sense
        // _ <- Entity.string(sample)(string2).query.get.map(res => all.contains(res.head) ==> true)
        // If you want a specific value, this would be the natural query
        _ <- Entity.string(string2).query.get.map(_ ==> List(string2))

        _ <- Entity.string(sample).not(string2).query.get.map { res =>
          List(string1, string3).contains(res.head) ==> true
          (res.head == string2) ==> false
        }
        _ <- Entity.string(sample).<(string3).query.get.map { res =>
          List(string1, string2).contains(res.head) ==> true
          (res.head == string3) ==> false
        }
        _ <- Entity.string(sample).<=(string2).query.get.map { res =>
          List(string1, string2).contains(res.head) ==> true
          (res.head == string3) ==> false
        }
        _ <- Entity.string(sample).>(string1).query.get.map { res =>
          List(string2, string3).contains(res.head) ==> true
          (res.head == string1) ==> false
        }
        _ <- Entity.string(sample).>=(string2).query.get.map { res =>
          List(string2, string3).contains(res.head) ==> true
          (res.head == string1) ==> false
        }

        // 1 attribute
        _ <- Entity.i.string(sample).not(string2).query.get.map { res =>
          List(a, c).contains(res.head) ==> true
          (res.head == b) ==> false
        }
        _ <- Entity.i.string(sample).<(string3).query.get.map { res =>
          List(a, b).contains(res.head) ==> true
          (res.head == c) ==> false
        }
        _ <- Entity.i.string(sample).<=(string2).query.get.map { res =>
          List(a, b).contains(res.head) ==> true
          (res.head == c) ==> false
        }
        _ <- Entity.i.string(sample).>(string1).query.get.map { res =>
          List(b, c).contains(res.head) ==> true
          (res.head == a) ==> false
        }
        _ <- Entity.i.string(sample).>=(string2).query.get.map { res =>
          List(b, c).contains(res.head) ==> true
          (res.head == a) ==> false
        }
      } yield ()
    }
  }


  "samples(n)" - types {
    val all = Set(string1, string2, string3)
    for {
      _ <- Entity.string.insert(List(string1, string2, string3)).transact
      _ <- Entity.string(sample(1)).query.get.map(res => all.intersect(res.head).nonEmpty ==> true)
      _ <- Entity.string(sample(2)).query.get.map(res => all.intersect(res.head).nonEmpty ==> true)
    } yield ()
  }


  "count" - types {
    val (a, b) = ((1, 1), (2, 3))
    for {
      _ <- Entity.i.string.insert(List(
        (1, string1),
        (2, string2),
        (2, string2),
        (2, string3),
      )).transact

      // 1 attribute
      _ <- Entity.string(count).query.get.map(_ ==> List(4))

      _ <- Entity.string(count)(3).query.get.map(_ ==> List())
      _ <- Entity.string(count)(4).query.get.map(_ ==> List(4))

      _ <- Entity.string(count).not(3).query.get.map(_ ==> List(4))
      _ <- Entity.string(count).not(4).query.get.map(_ ==> List())

      _ <- Entity.string(count).<(5).query.get.map(_ ==> List(4))
      _ <- Entity.string(count).<(4).query.get.map(_ ==> List())

      _ <- Entity.string(count).<=(4).query.get.map(_ ==> List(4))
      _ <- Entity.string(count).<=(3).query.get.map(_ ==> List())

      _ <- Entity.string(count).>(3).query.get.map(_ ==> List(4))
      _ <- Entity.string(count).>(4).query.get.map(_ ==> List())

      _ <- Entity.string(count).>=(4).query.get.map(_ ==> List(4))
      _ <- Entity.string(count).>=(5).query.get.map(_ ==> List())

      // n attributes
      _ <- Entity.i.a1.string(count).query.get.map(_ ==> List(a, b))

      _ <- Entity.i.a1.string(count)(3).query.get.map(_ ==> List(b))
      _ <- Entity.i.a1.string(count)(2).query.get.map(_ ==> List())

      _ <- Entity.i.a1.string(count).not(3).query.get.map(_ ==> List(a))
      _ <- Entity.i.a1.string(count).not(2).query.get.map(_ ==> List(a, b))

      _ <- Entity.i.a1.string(count).<(3).query.get.map(_ ==> List(a))
      _ <- Entity.i.a1.string(count).<(4).query.get.map(_ ==> List(a, b))

      _ <- Entity.i.a1.string(count).<=(3).query.get.map(_ ==> List(a, b))
      _ <- Entity.i.a1.string(count).<=(2).query.get.map(_ ==> List(a))

      _ <- Entity.i.a1.string(count).>(2).query.get.map(_ ==> List(b))
      _ <- Entity.i.a1.string(count).>(3).query.get.map(_ ==> List())

      _ <- Entity.i.a1.string(count).>=(3).query.get.map(_ ==> List(b))
      _ <- Entity.i.a1.string(count).>=(4).query.get.map(_ ==> List())

      // Include aggregated attribute too
      _ <- Entity.string.a1.string(count).query.get.map(_ ==> List(
        (string1, 1),
        (string2, 2),
        (string3, 1),
      ))
      _ <- Entity.string(count).string.a1.query.get.map(_ ==> List(
        (1, string1),
        (2, string2),
        (1, string3),
      ))
    } yield ()
  }


  "countDistinct" - types {
    val (a, b) = ((1, 1), (2, 2))
    for {
      _ <- Entity.i.string.insert(List(
        (1, string1),
        (2, string2),
        (2, string2),
        (2, string3),
      )).transact

      // 1 attribute
      _ <- Entity.string(countDistinct).query.get.map(_ ==> List(3))

      _ <- Entity.string(countDistinct)(2).query.get.map(_ ==> List())
      _ <- Entity.string(countDistinct)(3).query.get.map(_ ==> List(3))

      _ <- Entity.string(countDistinct).not(2).query.get.map(_ ==> List(3))
      _ <- Entity.string(countDistinct).not(3).query.get.map(_ ==> List())

      _ <- Entity.string(countDistinct).<(4).query.get.map(_ ==> List(3))
      _ <- Entity.string(countDistinct).<(3).query.get.map(_ ==> List())

      _ <- Entity.string(countDistinct).<=(3).query.get.map(_ ==> List(3))
      _ <- Entity.string(countDistinct).<=(2).query.get.map(_ ==> List())

      _ <- Entity.string(countDistinct).>(2).query.get.map(_ ==> List(3))
      _ <- Entity.string(countDistinct).>(3).query.get.map(_ ==> List())

      _ <- Entity.string(countDistinct).>=(3).query.get.map(_ ==> List(3))
      _ <- Entity.string(countDistinct).>=(4).query.get.map(_ ==> List())

      // n attributes
      _ <- Entity.i.a1.string(countDistinct).query.get.map(_ ==> List(a, b))

      _ <- Entity.i.a1.string(countDistinct)(2).query.get.map(_ ==> List(b))
      _ <- Entity.i.a1.string(countDistinct)(3).query.get.map(_ ==> List())

      _ <- Entity.i.a1.string(countDistinct).not(2).query.get.map(_ ==> List(a))
      _ <- Entity.i.a1.string(countDistinct).not(3).query.get.map(_ ==> List(a, b))

      _ <- Entity.i.a1.string(countDistinct).<(2).query.get.map(_ ==> List(a))
      _ <- Entity.i.a1.string(countDistinct).<(3).query.get.map(_ ==> List(a, b))

      _ <- Entity.i.a1.string(countDistinct).<=(2).query.get.map(_ ==> List(a, b))
      _ <- Entity.i.a1.string(countDistinct).<=(1).query.get.map(_ ==> List(a))

      _ <- Entity.i.a1.string(countDistinct).>(1).query.get.map(_ ==> List(b))
      _ <- Entity.i.a1.string(countDistinct).>(2).query.get.map(_ ==> List())

      _ <- Entity.i.a1.string(countDistinct).>=(2).query.get.map(_ ==> List(b))
      _ <- Entity.i.a1.string(countDistinct).>=(3).query.get.map(_ ==> List())

      // Include aggregated attribute too
      _ <- Entity.string.a1.string(countDistinct).query.get.map(_ ==> List(
        (string1, 1),
        (string2, 1),
        (string3, 1),
      ))
      _ <- Entity.string(countDistinct).string.a1.query.get.map(_ ==> List(
        (1, string1),
        (1, string2),
        (1, string3),
      ))
    } yield ()
  }
}