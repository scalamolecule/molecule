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

case class Aggr_Byte_(
  suite: MUnit,
  api: Api_async & Spi_async & DbProviders
) extends TestUtils {

  import api.*
  import suite.*

  "distinct" - types {
    for {
      _ <- Entity.i.byte.insert(List(
        (1, byte1),
        (2, byte2),
        (2, byte2),
        (2, byte3),
      )).transact

      _ <- Entity.i.byte.a1.query.get.map(_ ==> List(
        (1, byte1),
        (2, byte2), // 2 rows coalesced
        (2, byte3),
      ))

      // Distinct values are returned in a Set
      _ <- Entity.i.a1.byte(distinct).query.get.map(_ ==> List(
        (1, Set(byte1)),
        (2, Set(byte2, byte3)),
      ))

      _ <- Entity.byte(distinct).query.get.map(_.head ==> Set(
        byte1, byte2, byte3
      ))

      // Include aggregated attribute too (possible but will always be the same)
      _ <- Entity.byte.a1.byte(distinct).query.get.map(_ ==> List(
        (byte1, Set(byte1)),
        (byte2, Set(byte2)),
        (byte3, Set(byte3)),
      ))
      _ <- Entity.byte(distinct).byte.a1.query.get.map(_ ==> List(
        (Set(byte1), byte1),
        (Set(byte2), byte2),
        (Set(byte3), byte3),
      ))
    } yield ()
  }


  "min" - types {
    val (a, b) = ((1, byte1), (1, byte2))
    for {
      _ <- Entity.i.byte.insert(a, b).transact

      // 1 attribute
      _ <- Entity.byte(min).query.get.map(_ ==> List(byte1))

      _ <- Entity.byte(min)(byte1).query.get.map(_ ==> List(byte1))
      _ <- Entity.byte(min)(byte2).query.get.map(_ ==> List())

      _ <- Entity.byte(min).not(byte1).query.get.map(_ ==> List())
      _ <- Entity.byte(min).not(byte2).query.get.map(_ ==> List(byte1))

      _ <- Entity.byte(min).<(byte1).query.get.map(_ ==> List())
      _ <- Entity.byte(min).<(byte2).query.get.map(_ ==> List(byte1))

      _ <- Entity.byte(min).<=(byte0).query.get.map(_ ==> List())
      _ <- Entity.byte(min).<=(byte1).query.get.map(_ ==> List(byte1))

      _ <- Entity.byte(min).>(byte1).query.get.map(_ ==> List())
      _ <- Entity.byte(min).>(byte0).query.get.map(_ ==> List(byte1))

      _ <- Entity.byte(min).>=(byte1).query.get.map(_ ==> List(byte1))
      _ <- Entity.byte(min).>=(byte2).query.get.map(_ ==> List())

      // n attributes
      _ <- Entity.i.byte(min).query.get.map(_ ==> List(a))

      _ <- Entity.i.byte(min)(byte1).query.get.map(_ ==> List(a))
      _ <- Entity.i.byte(min)(byte2).query.get.map(_ ==> List())

      _ <- Entity.i.byte(min).not(byte1).query.get.map(_ ==> List())
      _ <- Entity.i.byte(min).not(byte2).query.get.map(_ ==> List(a))

      _ <- Entity.i.byte(min).<(byte1).query.get.map(_ ==> List())
      _ <- Entity.i.byte(min).<(byte2).query.get.map(_ ==> List(a))

      _ <- Entity.i.byte(min).<=(byte0).query.get.map(_ ==> List())
      _ <- Entity.i.byte(min).<=(byte1).query.get.map(_ ==> List(a))

      _ <- Entity.i.byte(min).>(byte1).query.get.map(_ ==> List())
      _ <- Entity.i.byte(min).>(byte0).query.get.map(_ ==> List(a))

      _ <- Entity.i.byte(min).>=(byte1).query.get.map(_ ==> List(a))
      _ <- Entity.i.byte(min).>=(byte2).query.get.map(_ ==> List())

      // Include aggregated attribute too (possible but will always be the same)
      _ <- Entity.byte.a1.byte(min).query.get.map(_ ==> List(
        (byte1, byte1),
        (byte2, byte2),
      ))
      _ <- Entity.byte(min).byte.a1.query.get.map(_ ==> List(
        (byte1, byte1),
        (byte2, byte2),
      ))
    } yield ()
  }


  "max" - types {
    val (a, b) = ((1, byte1), (1, byte2))
    for {
      _ <- Entity.i.byte.insert(a, b).transact

      // 1 attribute
      _ <- Entity.byte(max).query.get.map(_ ==> List(byte2))

      _ <- Entity.byte(max)(byte2).query.get.map(_ ==> List(byte2))
      _ <- Entity.byte(max)(byte1).query.get.map(_ ==> List())

      _ <- Entity.byte(max).not(byte2).query.get.map(_ ==> List())
      _ <- Entity.byte(max).not(byte1).query.get.map(_ ==> List(byte2))

      _ <- Entity.byte(max).<(byte2).query.get.map(_ ==> List())
      _ <- Entity.byte(max).<(byte3).query.get.map(_ ==> List(byte2))

      _ <- Entity.byte(max).<=(byte1).query.get.map(_ ==> List())
      _ <- Entity.byte(max).<=(byte2).query.get.map(_ ==> List(byte2))

      _ <- Entity.byte(max).>(byte2).query.get.map(_ ==> List())
      _ <- Entity.byte(max).>(byte1).query.get.map(_ ==> List(byte2))

      _ <- Entity.byte(max).>=(byte2).query.get.map(_ ==> List(byte2))
      _ <- Entity.byte(max).>=(byte3).query.get.map(_ ==> List())

      // n attributes
      _ <- Entity.i.byte(max).query.get.map(_ ==> List(b))

      _ <- Entity.i.byte(max)(byte2).query.get.map(_ ==> List(b))
      _ <- Entity.i.byte(max)(byte1).query.get.map(_ ==> List())

      _ <- Entity.i.byte(max).not(byte2).query.get.map(_ ==> List())
      _ <- Entity.i.byte(max).not(byte1).query.get.map(_ ==> List(b))

      _ <- Entity.i.byte(max).<(byte2).query.get.map(_ ==> List())
      _ <- Entity.i.byte(max).<(byte3).query.get.map(_ ==> List(b))

      _ <- Entity.i.byte(max).<=(byte1).query.get.map(_ ==> List())
      _ <- Entity.i.byte(max).<=(byte2).query.get.map(_ ==> List(b))

      _ <- Entity.i.byte(max).>(byte2).query.get.map(_ ==> List())
      _ <- Entity.i.byte(max).>(byte1).query.get.map(_ ==> List(b))

      _ <- Entity.i.byte(max).>=(byte2).query.get.map(_ ==> List(b))
      _ <- Entity.i.byte(max).>=(byte3).query.get.map(_ ==> List())

      // Include aggregated attribute too (possible but will always be the same)
      _ <- Entity.byte.a1.byte(max).query.get.map(_ ==> List(
        (byte1, byte1),
        (byte2, byte2),
      ))
      _ <- Entity.byte(max).byte.a1.query.get.map(_ ==> List(
        (byte1, byte1),
        (byte2, byte2),
      ))
    } yield ()
  }


  "min/max" - types {
    for {
      _ <- Entity.i.byte.insert(
        (1, byte1),
        (1, byte2),
        (2, byte3),
        (2, byte4),
      ).transact

      _ <- Entity.byte(min).byte(max).query.get.map(_ ==> List((byte1, byte4)))

      _ <- Entity.byte(min)(byte1).byte(max)(byte4).query.get.map(_ ==> List((byte1, byte4)))
      _ <- Entity.byte(min)(byte1).byte(max)(byte5).query.get.map(_ ==> List())

      _ <- Entity.byte(min).not(byte2).byte(max).not(byte3).query.get.map(_ ==> List((byte1, byte4)))
      _ <- Entity.byte(min).not(byte2).byte(max).not(byte4).query.get.map(_ ==> List())

      _ <- Entity.byte(min).<(byte2).byte(max).>(byte3).query.get.map(_ ==> List((byte1, byte4)))
      _ <- Entity.byte(min).<(byte2).byte(max).>(byte4).query.get.map(_ ==> List())

      _ <- Entity.byte(min).<=(byte1).byte(max).>=(byte4).query.get.map(_ ==> List((byte1, byte4)))
      _ <- Entity.byte(min).<=(byte1).byte(max).>=(byte5).query.get.map(_ ==> List())
    } yield ()
  }


  "min/max n" - types {
    for {
      _ <- Entity.i.byte.insert(
        (1, byte1),
        (1, byte2),
        (1, byte3),
        (2, byte4),
        (2, byte5),
        (2, byte6),
        (2, byte6), // (make sure grouped values coalesce)
      ).transact

      _ <- Entity.byte(min(1)).query.get.map(_ ==> List(Set(byte1)))
      _ <- Entity.byte(min(2)).query.get.map(_ ==> List(Set(byte1, byte2)))

      _ <- Entity.byte(max(1)).query.get.map(_ ==> List(Set(byte6)))
      _ <- Entity.byte(max(2)).query.get.map(_ ==> List(Set(byte5, byte6)))

      _ <- Entity.i.a1.byte(min(2)).query.get.map(_ ==> List(
        (1, Set(byte1, byte2)),
        (2, Set(byte4, byte5))
      ))

      _ <- Entity.i.a1.byte(max(2)).query.get.map(_ ==> List(
        (1, Set(byte2, byte3)),
        (2, Set(byte5, byte6))
      ))

      _ <- Entity.i.a1.byte(min(2)).byte(max(2)).query.get.map(_ ==> List(
        (1, Set(byte1, byte2), Set(byte2, byte3)),
        (2, Set(byte4, byte5), Set(byte5, byte6))
      ))

      // Include aggregated attribute too (possible but will always be the same)
      _ <- Entity.byte.a1.byte(min(2)).query.get.map(_ ==> List(
        (byte1, Set(byte1)),
        (byte2, Set(byte2)),
        (byte3, Set(byte3)),
        (byte4, Set(byte4)),
        (byte5, Set(byte5)),
        (byte6, Set(byte6)),
      ))
      _ <- Entity.byte(min(2)).byte.a1.query.get.map(_ ==> List(
        (Set(byte1), byte1),
        (Set(byte2), byte2),
        (Set(byte3), byte3),
        (Set(byte4), byte4),
        (Set(byte5), byte5),
        (Set(byte6), byte6),
      ))

      _ <- Entity.byte.a1.byte(max(2)).query.get.map(_ ==> List(
        (byte1, Set(byte1)),
        (byte2, Set(byte2)),
        (byte3, Set(byte3)),
        (byte4, Set(byte4)),
        (byte5, Set(byte5)),
        (byte6, Set(byte6)),
      ))
      _ <- Entity.byte(max(2)).byte.a1.query.get.map(_ ==> List(
        (Set(byte1), byte1),
        (Set(byte2), byte2),
        (Set(byte3), byte3),
        (Set(byte4), byte4),
        (Set(byte5), byte5),
        (Set(byte6), byte6),
      ))
    } yield ()
  }


  "sample" - types {
    val all      = Set(byte1, byte2, byte3)
    val allPairs = List((1, byte1), (2, byte2), (3, byte3))
    for {
      _ <- Entity.i.byte.insert(allPairs).transact

      // 1 attribute
      _ <- Entity.byte(sample).query.get.map(res => all.contains(res.head) ==> true)

      // 1 attribute
      _ <- Entity.i.byte(sample).query.get.map(res => allPairs.contains(res.head) ==> true)
    } yield ()
  }

  "sample ops" - types {
    if (Seq("mariadb", "mysql").contains(database)) {
      Entity.byte(sample)(byte1).query.get
        .map(_ ==> "Unexpected success").recover { case ModelError(err) =>
          err ==> "Operations on sample not implemented for this database."
        }
    } else {
      val all       = Set(byte1, byte2, byte3)
      val (a, b, c) = ((1, byte1), (2, byte2), (3, byte3))
      val allPairs  = List(a, b, c)
      for {
        _ <- Entity.i.byte.insert(allPairs).transact

        // 1 attribute
        // Checking for equality on a sample doesn't make sense
        // _ <- Entity.byte(sample)(byte2).query.get.map(res => all.contains(res.head) ==> true)
        // If you want a specific value, this would be the natural query
        _ <- Entity.byte(byte2).query.get.map(_ ==> List(byte2))

        _ <- Entity.byte(sample).not(byte2).query.get.map { res =>
          List(byte1, byte3).contains(res.head) ==> true
          (res.head == byte2) ==> false
        }
        _ <- Entity.byte(sample).<(byte3).query.get.map { res =>
          List(byte1, byte2).contains(res.head) ==> true
          (res.head == byte3) ==> false
        }
        _ <- Entity.byte(sample).<=(byte2).query.get.map { res =>
          List(byte1, byte2).contains(res.head) ==> true
          (res.head == byte3) ==> false
        }
        _ <- Entity.byte(sample).>(byte1).query.get.map { res =>
          List(byte2, byte3).contains(res.head) ==> true
          (res.head == byte1) ==> false
        }
        _ <- Entity.byte(sample).>=(byte2).query.get.map { res =>
          List(byte2, byte3).contains(res.head) ==> true
          (res.head == byte1) ==> false
        }

        // 1 attribute
        _ <- Entity.i.byte(sample).not(byte2).query.get.map { res =>
          List(a, c).contains(res.head) ==> true
          (res.head == b) ==> false
        }
        _ <- Entity.i.byte(sample).<(byte3).query.get.map { res =>
          List(a, b).contains(res.head) ==> true
          (res.head == c) ==> false
        }
        _ <- Entity.i.byte(sample).<=(byte2).query.get.map { res =>
          List(a, b).contains(res.head) ==> true
          (res.head == c) ==> false
        }
        _ <- Entity.i.byte(sample).>(byte1).query.get.map { res =>
          List(b, c).contains(res.head) ==> true
          (res.head == a) ==> false
        }
        _ <- Entity.i.byte(sample).>=(byte2).query.get.map { res =>
          List(b, c).contains(res.head) ==> true
          (res.head == a) ==> false
        }
      } yield ()
    }
  }


  "samples(n)" - types {
    val all = Set(byte1, byte2, byte3)
    for {
      _ <- Entity.byte.insert(List(byte1, byte2, byte3)).transact
      _ <- Entity.byte(sample(1)).query.get.map(res => all.intersect(res.head).nonEmpty ==> true)
      _ <- Entity.byte(sample(2)).query.get.map(res => all.intersect(res.head).nonEmpty ==> true)
    } yield ()
  }


  "count" - types {
    val (a, b) = ((1, 1), (2, 3))
    for {
      _ <- Entity.i.byte.insert(List(
        (1, byte1),
        (2, byte2),
        (2, byte2),
        (2, byte3),
      )).transact

      // 1 attribute
      _ <- Entity.byte(count).query.get.map(_ ==> List(4))

      _ <- Entity.byte(count)(3).query.get.map(_ ==> List())
      _ <- Entity.byte(count)(4).query.get.map(_ ==> List(4))

      _ <- Entity.byte(count).not(3).query.get.map(_ ==> List(4))
      _ <- Entity.byte(count).not(4).query.get.map(_ ==> List())

      _ <- Entity.byte(count).<(5).query.get.map(_ ==> List(4))
      _ <- Entity.byte(count).<(4).query.get.map(_ ==> List())

      _ <- Entity.byte(count).<=(4).query.get.map(_ ==> List(4))
      _ <- Entity.byte(count).<=(3).query.get.map(_ ==> List())

      _ <- Entity.byte(count).>(3).query.get.map(_ ==> List(4))
      _ <- Entity.byte(count).>(4).query.get.map(_ ==> List())

      _ <- Entity.byte(count).>=(4).query.get.map(_ ==> List(4))
      _ <- Entity.byte(count).>=(5).query.get.map(_ ==> List())

      // n attributes
      _ <- Entity.i.a1.byte(count).query.get.map(_ ==> List(a, b))

      _ <- Entity.i.a1.byte(count)(3).query.get.map(_ ==> List(b))
      _ <- Entity.i.a1.byte(count)(2).query.get.map(_ ==> List())

      _ <- Entity.i.a1.byte(count).not(3).query.get.map(_ ==> List(a))
      _ <- Entity.i.a1.byte(count).not(2).query.get.map(_ ==> List(a, b))

      _ <- Entity.i.a1.byte(count).<(3).query.get.map(_ ==> List(a))
      _ <- Entity.i.a1.byte(count).<(4).query.get.map(_ ==> List(a, b))

      _ <- Entity.i.a1.byte(count).<=(3).query.get.map(_ ==> List(a, b))
      _ <- Entity.i.a1.byte(count).<=(2).query.get.map(_ ==> List(a))

      _ <- Entity.i.a1.byte(count).>(2).query.get.map(_ ==> List(b))
      _ <- Entity.i.a1.byte(count).>(3).query.get.map(_ ==> List())

      _ <- Entity.i.a1.byte(count).>=(3).query.get.map(_ ==> List(b))
      _ <- Entity.i.a1.byte(count).>=(4).query.get.map(_ ==> List())

      // Include aggregated attribute too
      _ <- Entity.byte.a1.byte(count).query.get.map(_ ==> List(
        (byte1, 1),
        (byte2, 2),
        (byte3, 1),
      ))
      _ <- Entity.byte(count).byte.a1.query.get.map(_ ==> List(
        (1, byte1),
        (2, byte2),
        (1, byte3),
      ))
    } yield ()
  }


  "countDistinct" - types {
    val (a, b) = ((1, 1), (2, 2))
    for {
      _ <- Entity.i.byte.insert(List(
        (1, byte1),
        (2, byte2),
        (2, byte2),
        (2, byte3),
      )).transact

      // 1 attribute
      _ <- Entity.byte(countDistinct).query.get.map(_ ==> List(3))

      _ <- Entity.byte(countDistinct)(2).query.get.map(_ ==> List())
      _ <- Entity.byte(countDistinct)(3).query.get.map(_ ==> List(3))

      _ <- Entity.byte(countDistinct).not(2).query.get.map(_ ==> List(3))
      _ <- Entity.byte(countDistinct).not(3).query.get.map(_ ==> List())

      _ <- Entity.byte(countDistinct).<(4).query.get.map(_ ==> List(3))
      _ <- Entity.byte(countDistinct).<(3).query.get.map(_ ==> List())

      _ <- Entity.byte(countDistinct).<=(3).query.get.map(_ ==> List(3))
      _ <- Entity.byte(countDistinct).<=(2).query.get.map(_ ==> List())

      _ <- Entity.byte(countDistinct).>(2).query.get.map(_ ==> List(3))
      _ <- Entity.byte(countDistinct).>(3).query.get.map(_ ==> List())

      _ <- Entity.byte(countDistinct).>=(3).query.get.map(_ ==> List(3))
      _ <- Entity.byte(countDistinct).>=(4).query.get.map(_ ==> List())

      // n attributes
      _ <- Entity.i.a1.byte(countDistinct).query.get.map(_ ==> List(a, b))

      _ <- Entity.i.a1.byte(countDistinct)(2).query.get.map(_ ==> List(b))
      _ <- Entity.i.a1.byte(countDistinct)(3).query.get.map(_ ==> List())

      _ <- Entity.i.a1.byte(countDistinct).not(2).query.get.map(_ ==> List(a))
      _ <- Entity.i.a1.byte(countDistinct).not(3).query.get.map(_ ==> List(a, b))

      _ <- Entity.i.a1.byte(countDistinct).<(2).query.get.map(_ ==> List(a))
      _ <- Entity.i.a1.byte(countDistinct).<(3).query.get.map(_ ==> List(a, b))

      _ <- Entity.i.a1.byte(countDistinct).<=(2).query.get.map(_ ==> List(a, b))
      _ <- Entity.i.a1.byte(countDistinct).<=(1).query.get.map(_ ==> List(a))

      _ <- Entity.i.a1.byte(countDistinct).>(1).query.get.map(_ ==> List(b))
      _ <- Entity.i.a1.byte(countDistinct).>(2).query.get.map(_ ==> List())

      _ <- Entity.i.a1.byte(countDistinct).>=(2).query.get.map(_ ==> List(b))
      _ <- Entity.i.a1.byte(countDistinct).>=(3).query.get.map(_ ==> List())

      // Include aggregated attribute too
      _ <- Entity.byte.a1.byte(countDistinct).query.get.map(_ ==> List(
        (byte1, 1),
        (byte2, 1),
        (byte3, 1),
      ))
      _ <- Entity.byte(countDistinct).byte.a1.query.get.map(_ ==> List(
        (1, byte1),
        (1, byte2),
        (1, byte3),
      ))
    } yield ()
  }
}