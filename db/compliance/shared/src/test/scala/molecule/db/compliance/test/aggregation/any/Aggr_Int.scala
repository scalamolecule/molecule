package molecule.db.compliance.test.aggregation.any

import molecule.base.error.ModelError
import molecule.core.setup.{MUnit, TestUtils}
import molecule.db.common.api.Api_async
import molecule.db.common.spi.Spi_async
import molecule.db.common.util.Executor.*
import molecule.db.compliance.domains.dsl.Types.*
import molecule.db.compliance.setup.DbProviders
import org.scalactic.Equality

case class Aggr_Int(
  suite: MUnit,
  api: Api_async & Spi_async & DbProviders
) extends TestUtils {

  import api.*
  import suite.*

  "distinct" - types {
    for {
      _ <- Entity.i.int.insert(List(
        (1, int1),
        (2, int2),
        (2, int2),
        (2, int3),
      )).transact

      _ <- Entity.i.int.a1.query.get.map(_ ==> List(
        (1, int1),
        (2, int2), // 2 rows coalesced
        (2, int3),
      ))

      // Distinct values are returned in a Set
      _ <- Entity.i.a1.int(distinct).query.get.map(_ ==> List(
        (1, Set(int1)),
        (2, Set(int2, int3)),
      ))

      _ <- Entity.int(distinct).query.get.map(_.head ==> Set(
        int1, int2, int3
      ))

      // Include aggregated attribute too (possible but will always be the same)
      _ <- Entity.int.a1.int(distinct).query.get.map(_ ==> List(
        (int1, Set(int1)),
        (int2, Set(int2)),
        (int3, Set(int3)),
      ))
      _ <- Entity.int(distinct).int.a1.query.get.map(_ ==> List(
        (Set(int1), int1),
        (Set(int2), int2),
        (Set(int3), int3),
      ))
    } yield ()
  }


  "min" - types {
    val (a, b) = ((1, int1), (1, int2))
    for {
      _ <- Entity.i.int.insert(a, b).transact

      // 1 attribute
      _ <- Entity.int(min).query.get.map(_ ==> List(int1))

      _ <- Entity.int(min)(int1).query.get.map(_ ==> List(int1))
      _ <- Entity.int(min)(int2).query.get.map(_ ==> List())

      _ <- Entity.int(min).not(int1).query.get.map(_ ==> List())
      _ <- Entity.int(min).not(int2).query.get.map(_ ==> List(int1))

      _ <- Entity.int(min).<(int1).query.get.map(_ ==> List())
      _ <- Entity.int(min).<(int2).query.get.map(_ ==> List(int1))

      _ <- Entity.int(min).<=(int0).query.get.map(_ ==> List())
      _ <- Entity.int(min).<=(int1).query.get.map(_ ==> List(int1))

      _ <- Entity.int(min).>(int1).query.get.map(_ ==> List())
      _ <- Entity.int(min).>(int0).query.get.map(_ ==> List(int1))

      _ <- Entity.int(min).>=(int1).query.get.map(_ ==> List(int1))
      _ <- Entity.int(min).>=(int2).query.get.map(_ ==> List())

      // n attributes
      _ <- Entity.i.int(min).query.get.map(_ ==> List(a))

      _ <- Entity.i.int(min)(int1).query.get.map(_ ==> List(a))
      _ <- Entity.i.int(min)(int2).query.get.map(_ ==> List())

      _ <- Entity.i.int(min).not(int1).query.get.map(_ ==> List())
      _ <- Entity.i.int(min).not(int2).query.get.map(_ ==> List(a))

      _ <- Entity.i.int(min).<(int1).query.get.map(_ ==> List())
      _ <- Entity.i.int(min).<(int2).query.get.map(_ ==> List(a))

      _ <- Entity.i.int(min).<=(int0).query.get.map(_ ==> List())
      _ <- Entity.i.int(min).<=(int1).query.get.map(_ ==> List(a))

      _ <- Entity.i.int(min).>(int1).query.get.map(_ ==> List())
      _ <- Entity.i.int(min).>(int0).query.get.map(_ ==> List(a))

      _ <- Entity.i.int(min).>=(int1).query.get.map(_ ==> List(a))
      _ <- Entity.i.int(min).>=(int2).query.get.map(_ ==> List())

      // Include aggregated attribute too (possible but will always be the same)
      _ <- Entity.int.a1.int(min).query.get.map(_ ==> List(
        (int1, int1),
        (int2, int2),
      ))
      _ <- Entity.int(min).int.a1.query.get.map(_ ==> List(
        (int1, int1),
        (int2, int2),
      ))
    } yield ()
  }


  "max" - types {
    val (a, b) = ((1, int1), (1, int2))
    for {
      _ <- Entity.i.int.insert(a, b).transact

      // 1 attribute
      _ <- Entity.int(max).query.get.map(_ ==> List(int2))

      _ <- Entity.int(max)(int2).query.get.map(_ ==> List(int2))
      _ <- Entity.int(max)(int1).query.get.map(_ ==> List())

      _ <- Entity.int(max).not(int2).query.get.map(_ ==> List())
      _ <- Entity.int(max).not(int1).query.get.map(_ ==> List(int2))

      _ <- Entity.int(max).<(int2).query.get.map(_ ==> List())
      _ <- Entity.int(max).<(int3).query.get.map(_ ==> List(int2))

      _ <- Entity.int(max).<=(int1).query.get.map(_ ==> List())
      _ <- Entity.int(max).<=(int2).query.get.map(_ ==> List(int2))

      _ <- Entity.int(max).>(int2).query.get.map(_ ==> List())
      _ <- Entity.int(max).>(int1).query.get.map(_ ==> List(int2))

      _ <- Entity.int(max).>=(int2).query.get.map(_ ==> List(int2))
      _ <- Entity.int(max).>=(int3).query.get.map(_ ==> List())

      // n attributes
      _ <- Entity.i.int(max).query.get.map(_ ==> List(b))

      _ <- Entity.i.int(max)(int2).query.get.map(_ ==> List(b))
      _ <- Entity.i.int(max)(int1).query.get.map(_ ==> List())

      _ <- Entity.i.int(max).not(int2).query.get.map(_ ==> List())
      _ <- Entity.i.int(max).not(int1).query.get.map(_ ==> List(b))

      _ <- Entity.i.int(max).<(int2).query.get.map(_ ==> List())
      _ <- Entity.i.int(max).<(int3).query.get.map(_ ==> List(b))

      _ <- Entity.i.int(max).<=(int1).query.get.map(_ ==> List())
      _ <- Entity.i.int(max).<=(int2).query.get.map(_ ==> List(b))

      _ <- Entity.i.int(max).>(int2).query.get.map(_ ==> List())
      _ <- Entity.i.int(max).>(int1).query.get.map(_ ==> List(b))

      _ <- Entity.i.int(max).>=(int2).query.get.map(_ ==> List(b))
      _ <- Entity.i.int(max).>=(int3).query.get.map(_ ==> List())

      // Include aggregated attribute too (possible but will always be the same)
      _ <- Entity.int.a1.int(max).query.get.map(_ ==> List(
        (int1, int1),
        (int2, int2),
      ))
      _ <- Entity.int(max).int.a1.query.get.map(_ ==> List(
        (int1, int1),
        (int2, int2),
      ))
    } yield ()
  }


  "min/max" - types {
    for {
      _ <- Entity.i.int.insert(
        (1, int1),
        (1, int2),
        (2, int3),
        (2, int4),
      ).transact

      _ <- Entity.int(min).int(max).query.get.map(_ ==> List((int1, int4)))

      _ <- Entity.int(min)(int1).int(max)(int4).query.get.map(_ ==> List((int1, int4)))
      _ <- Entity.int(min)(int1).int(max)(int5).query.get.map(_ ==> List())

      _ <- Entity.int(min).not(int2).int(max).not(int3).query.get.map(_ ==> List((int1, int4)))
      _ <- Entity.int(min).not(int2).int(max).not(int4).query.get.map(_ ==> List())

      _ <- Entity.int(min).<(int2).int(max).>(int3).query.get.map(_ ==> List((int1, int4)))
      _ <- Entity.int(min).<(int2).int(max).>(int4).query.get.map(_ ==> List())

      _ <- Entity.int(min).<=(int1).int(max).>=(int4).query.get.map(_ ==> List((int1, int4)))
      _ <- Entity.int(min).<=(int1).int(max).>=(int5).query.get.map(_ ==> List())
    } yield ()
  }


  "min/max n" - types {
    for {
      _ <- Entity.i.int.insert(
        (1, int1),
        (1, int2),
        (1, int3),
        (2, int4),
        (2, int5),
        (2, int6),
        (2, int6), // (make sure grouped values coalesce)
      ).transact

      _ <- Entity.int(min(1)).query.get.map(_ ==> List(Set(int1)))
      _ <- Entity.int(min(2)).query.get.map(_ ==> List(Set(int1, int2)))

      _ <- Entity.int(max(1)).query.get.map(_ ==> List(Set(int6)))
      _ <- Entity.int(max(2)).query.get.map(_ ==> List(Set(int5, int6)))

      _ <- Entity.i.a1.int(min(2)).query.get.map(_ ==> List(
        (1, Set(int1, int2)),
        (2, Set(int4, int5))
      ))

      _ <- Entity.i.a1.int(max(2)).query.get.map(_ ==> List(
        (1, Set(int2, int3)),
        (2, Set(int5, int6))
      ))

      _ <- Entity.i.a1.int(min(2)).int(max(2)).query.get.map(_ ==> List(
        (1, Set(int1, int2), Set(int2, int3)),
        (2, Set(int4, int5), Set(int5, int6))
      ))

      // Include aggregated attribute too (possible but will always be the same)
      _ <- Entity.int.a1.int(min(2)).query.get.map(_ ==> List(
        (int1, Set(int1)),
        (int2, Set(int2)),
        (int3, Set(int3)),
        (int4, Set(int4)),
        (int5, Set(int5)),
        (int6, Set(int6)),
      ))
      _ <- Entity.int(min(2)).int.a1.query.get.map(_ ==> List(
        (Set(int1), int1),
        (Set(int2), int2),
        (Set(int3), int3),
        (Set(int4), int4),
        (Set(int5), int5),
        (Set(int6), int6),
      ))

      _ <- Entity.int.a1.int(max(2)).query.get.map(_ ==> List(
        (int1, Set(int1)),
        (int2, Set(int2)),
        (int3, Set(int3)),
        (int4, Set(int4)),
        (int5, Set(int5)),
        (int6, Set(int6)),
      ))
      _ <- Entity.int(max(2)).int.a1.query.get.map(_ ==> List(
        (Set(int1), int1),
        (Set(int2), int2),
        (Set(int3), int3),
        (Set(int4), int4),
        (Set(int5), int5),
        (Set(int6), int6),
      ))
    } yield ()
  }


  "sample" - types {
    val all      = Set(int1, int2, int3)
    val allPairs = List((1, int1), (2, int2), (3, int3))
    for {
      _ <- Entity.i.int.insert(allPairs).transact

      // 1 attribute
      _ <- Entity.int(sample).query.get.map(res => all.contains(res.head) ==> true)

      // 1 attribute
      _ <- Entity.i.int(sample).query.get.map(res => allPairs.contains(res.head) ==> true)
    } yield ()
  }

  "sample ops" - types {
    if (Seq("mariadb", "mysql").contains(database)) {
      Entity.int(sample)(int1).query.get
        .map(_ ==> "Unexpected success").recover { case ModelError(err) =>
          err ==> "Operations on sample not implemented for this database."
        }
    } else {
      val all       = Set(int1, int2, int3)
      val (a, b, c) = ((1, int1), (2, int2), (3, int3))
      val allPairs  = List(a, b, c)
      for {
        _ <- Entity.i.int.insert(allPairs).transact

        // 1 attribute
        // Checking for equality on a sample doesn't make sense
        // _ <- Entity.int(sample)(int2).query.get.map(res => all.contains(res.head) ==> true)
        // If you want a specific value, this would be the natural query
        _ <- Entity.int(int2).query.get.map(_ ==> List(int2))

        _ <- Entity.int(sample).not(int2).query.get.map { res =>
          List(int1, int3).contains(res.head) ==> true
          (res.head == int2) ==> false
        }
        _ <- Entity.int(sample).<(int3).query.get.map { res =>
          List(int1, int2).contains(res.head) ==> true
          (res.head == int3) ==> false
        }
        _ <- Entity.int(sample).<=(int2).query.get.map { res =>
          List(int1, int2).contains(res.head) ==> true
          (res.head == int3) ==> false
        }
        _ <- Entity.int(sample).>(int1).query.get.map { res =>
          List(int2, int3).contains(res.head) ==> true
          (res.head == int1) ==> false
        }
        _ <- Entity.int(sample).>=(int2).query.get.map { res =>
          List(int2, int3).contains(res.head) ==> true
          (res.head == int1) ==> false
        }

        // 1 attribute
        _ <- Entity.i.int(sample).not(int2).query.get.map { res =>
          List(a, c).contains(res.head) ==> true
          (res.head == b) ==> false
        }
        _ <- Entity.i.int(sample).<(int3).query.get.map { res =>
          List(a, b).contains(res.head) ==> true
          (res.head == c) ==> false
        }
        _ <- Entity.i.int(sample).<=(int2).query.get.map { res =>
          List(a, b).contains(res.head) ==> true
          (res.head == c) ==> false
        }
        _ <- Entity.i.int(sample).>(int1).query.get.map { res =>
          List(b, c).contains(res.head) ==> true
          (res.head == a) ==> false
        }
        _ <- Entity.i.int(sample).>=(int2).query.get.map { res =>
          List(b, c).contains(res.head) ==> true
          (res.head == a) ==> false
        }
      } yield ()
    }
  }


  "samples(n)" - types {
    val all = Set(int1, int2, int3)
    for {
      _ <- Entity.int.insert(List(int1, int2, int3)).transact
      _ <- Entity.int(sample(1)).query.get.map(res => all.intersect(res.head).nonEmpty ==> true)
      _ <- Entity.int(sample(2)).query.get.map(res => all.intersect(res.head).nonEmpty ==> true)
    } yield ()
  }


  "count" - types {
    val (a, b) = ((1, 1), (2, 3))
    for {
      _ <- Entity.i.int.insert(List(
        (1, int1),
        (2, int2),
        (2, int2),
        (2, int3),
      )).transact

      // 1 attribute
      _ <- Entity.int(count).query.get.map(_ ==> List(4))

      _ <- Entity.int(count)(3).query.get.map(_ ==> List())
      _ <- Entity.int(count)(4).query.get.map(_ ==> List(4))

      _ <- Entity.int(count).not(3).query.get.map(_ ==> List(4))
      _ <- Entity.int(count).not(4).query.get.map(_ ==> List())

      _ <- Entity.int(count).<(5).query.get.map(_ ==> List(4))
      _ <- Entity.int(count).<(4).query.get.map(_ ==> List())

      _ <- Entity.int(count).<=(4).query.get.map(_ ==> List(4))
      _ <- Entity.int(count).<=(3).query.get.map(_ ==> List())

      _ <- Entity.int(count).>(3).query.get.map(_ ==> List(4))
      _ <- Entity.int(count).>(4).query.get.map(_ ==> List())

      _ <- Entity.int(count).>=(4).query.get.map(_ ==> List(4))
      _ <- Entity.int(count).>=(5).query.get.map(_ ==> List())

      // n attributes
      _ <- Entity.i.a1.int(count).query.get.map(_ ==> List(a, b))

      _ <- Entity.i.a1.int(count)(3).query.get.map(_ ==> List(b))
      _ <- Entity.i.a1.int(count)(2).query.get.map(_ ==> List())

      _ <- Entity.i.a1.int(count).not(3).query.get.map(_ ==> List(a))
      _ <- Entity.i.a1.int(count).not(2).query.get.map(_ ==> List(a, b))

      _ <- Entity.i.a1.int(count).<(3).query.get.map(_ ==> List(a))
      _ <- Entity.i.a1.int(count).<(4).query.get.map(_ ==> List(a, b))

      _ <- Entity.i.a1.int(count).<=(3).query.get.map(_ ==> List(a, b))
      _ <- Entity.i.a1.int(count).<=(2).query.get.map(_ ==> List(a))

      _ <- Entity.i.a1.int(count).>(2).query.get.map(_ ==> List(b))
      _ <- Entity.i.a1.int(count).>(3).query.get.map(_ ==> List())

      _ <- Entity.i.a1.int(count).>=(3).query.get.map(_ ==> List(b))
      _ <- Entity.i.a1.int(count).>=(4).query.get.map(_ ==> List())

      // Include aggregated attribute too
      _ <- Entity.int.a1.int(count).query.get.map(_ ==> List(
        (int1, 1),
        (int2, 2),
        (int3, 1),
      ))
      _ <- Entity.int(count).int.a1.query.get.map(_ ==> List(
        (1, int1),
        (2, int2),
        (1, int3),
      ))
    } yield ()
  }


  "countDistinct" - types {
    val (a, b) = ((1, 1), (2, 2))
    for {
      _ <- Entity.i.int.insert(List(
        (1, int1),
        (2, int2),
        (2, int2),
        (2, int3),
      )).transact

      // 1 attribute
      _ <- Entity.int(countDistinct).query.get.map(_ ==> List(3))

      _ <- Entity.int(countDistinct)(2).query.get.map(_ ==> List())
      _ <- Entity.int(countDistinct)(3).query.get.map(_ ==> List(3))

      _ <- Entity.int(countDistinct).not(2).query.get.map(_ ==> List(3))
      _ <- Entity.int(countDistinct).not(3).query.get.map(_ ==> List())

      _ <- Entity.int(countDistinct).<(4).query.get.map(_ ==> List(3))
      _ <- Entity.int(countDistinct).<(3).query.get.map(_ ==> List())

      _ <- Entity.int(countDistinct).<=(3).query.get.map(_ ==> List(3))
      _ <- Entity.int(countDistinct).<=(2).query.get.map(_ ==> List())

      _ <- Entity.int(countDistinct).>(2).query.get.map(_ ==> List(3))
      _ <- Entity.int(countDistinct).>(3).query.get.map(_ ==> List())

      _ <- Entity.int(countDistinct).>=(3).query.get.map(_ ==> List(3))
      _ <- Entity.int(countDistinct).>=(4).query.get.map(_ ==> List())

      // n attributes
      _ <- Entity.i.a1.int(countDistinct).query.get.map(_ ==> List(a, b))

      _ <- Entity.i.a1.int(countDistinct)(2).query.get.map(_ ==> List(b))
      _ <- Entity.i.a1.int(countDistinct)(3).query.get.map(_ ==> List())

      _ <- Entity.i.a1.int(countDistinct).not(2).query.get.map(_ ==> List(a))
      _ <- Entity.i.a1.int(countDistinct).not(3).query.get.map(_ ==> List(a, b))

      _ <- Entity.i.a1.int(countDistinct).<(2).query.get.map(_ ==> List(a))
      _ <- Entity.i.a1.int(countDistinct).<(3).query.get.map(_ ==> List(a, b))

      _ <- Entity.i.a1.int(countDistinct).<=(2).query.get.map(_ ==> List(a, b))
      _ <- Entity.i.a1.int(countDistinct).<=(1).query.get.map(_ ==> List(a))

      _ <- Entity.i.a1.int(countDistinct).>(1).query.get.map(_ ==> List(b))
      _ <- Entity.i.a1.int(countDistinct).>(2).query.get.map(_ ==> List())

      _ <- Entity.i.a1.int(countDistinct).>=(2).query.get.map(_ ==> List(b))
      _ <- Entity.i.a1.int(countDistinct).>=(3).query.get.map(_ ==> List())

      // Include aggregated attribute too
      _ <- Entity.int.a1.int(countDistinct).query.get.map(_ ==> List(
        (int1, 1),
        (int2, 1),
        (int3, 1),
      ))
      _ <- Entity.int(countDistinct).int.a1.query.get.map(_ ==> List(
        (1, int1),
        (1, int2),
        (1, int3),
      ))
    } yield ()
  }
}