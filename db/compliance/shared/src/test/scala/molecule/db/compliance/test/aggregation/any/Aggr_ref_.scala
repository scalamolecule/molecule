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

case class Aggr_ref_(
  suite: MUnit,
  api: Api_async & Spi_async & DbProviders
) extends TestUtils {

  import api.*
  import suite.*

  "distinct" - types {
    for {
      _ <- Entity.i.ref.insert(List(
        (1, ref1),
        (2, ref2),
        (2, ref2),
        (2, ref3),
      )).transact

      _ <- Entity.i.ref.a1.query.get.map(_ ==> List(
        (1, ref1),
        (2, ref2), // 2 rows coalesced
        (2, ref3),
      ))

      // Distinct values are returned in a Set
      _ <- Entity.i.a1.ref(distinct).query.get.map(_ ==> List(
        (1, Set(ref1)),
        (2, Set(ref2, ref3)),
      ))

      _ <- Entity.ref(distinct).query.get.map(_.head ==> Set(
        ref1, ref2, ref3
      ))
    } yield ()
  }


  "min" - types {
    val (a, b) = ((1, ref1), (1, ref2))
    for {
      _ <- Entity.i.ref.insert(a, b).transact

      // 1 attribute
      _ <- Entity.ref(min).query.get.map(_ ==> List(ref1))

      _ <- Entity.ref(min)(ref1).query.get.map(_ ==> List(ref1))
      _ <- Entity.ref(min)(ref2).query.get.map(_ ==> List())

      _ <- Entity.ref(min).not(ref1).query.get.map(_ ==> List())
      _ <- Entity.ref(min).not(ref2).query.get.map(_ ==> List(ref1))

      _ <- Entity.ref(min).<(ref1).query.get.map(_ ==> List())
      _ <- Entity.ref(min).<(ref2).query.get.map(_ ==> List(ref1))

      _ <- Entity.ref(min).<=(ref0).query.get.map(_ ==> List())
      _ <- Entity.ref(min).<=(ref1).query.get.map(_ ==> List(ref1))

      _ <- Entity.ref(min).>(ref1).query.get.map(_ ==> List())
      _ <- Entity.ref(min).>(ref0).query.get.map(_ ==> List(ref1))

      _ <- Entity.ref(min).>=(ref1).query.get.map(_ ==> List(ref1))
      _ <- Entity.ref(min).>=(ref2).query.get.map(_ ==> List())


      // n attributes
      _ <- Entity.i.ref(min).query.get.map(_ ==> List(a))

      _ <- Entity.i.ref(min)(ref1).query.get.map(_ ==> List(a))
      _ <- Entity.i.ref(min)(ref2).query.get.map(_ ==> List())

      _ <- Entity.i.ref(min).not(ref1).query.get.map(_ ==> List())
      _ <- Entity.i.ref(min).not(ref2).query.get.map(_ ==> List(a))

      _ <- Entity.i.ref(min).<(ref1).query.get.map(_ ==> List())
      _ <- Entity.i.ref(min).<(ref2).query.get.map(_ ==> List(a))

      _ <- Entity.i.ref(min).<=(ref0).query.get.map(_ ==> List())
      _ <- Entity.i.ref(min).<=(ref1).query.get.map(_ ==> List(a))

      _ <- Entity.i.ref(min).>(ref1).query.get.map(_ ==> List())
      _ <- Entity.i.ref(min).>(ref0).query.get.map(_ ==> List(a))

      _ <- Entity.i.ref(min).>=(ref1).query.get.map(_ ==> List(a))
      _ <- Entity.i.ref(min).>=(ref2).query.get.map(_ ==> List())
    } yield ()
  }


  "max" - types {
    val (a, b) = ((1, ref1), (1, ref2))
    for {
      _ <- Entity.i.ref.insert(a, b).transact

      // 1 attribute
      _ <- Entity.ref(max).query.get.map(_ ==> List(ref2))

      _ <- Entity.ref(max)(ref2).query.get.map(_ ==> List(ref2))
      _ <- Entity.ref(max)(ref1).query.get.map(_ ==> List())

      _ <- Entity.ref(max).not(ref2).query.get.map(_ ==> List())
      _ <- Entity.ref(max).not(ref1).query.get.map(_ ==> List(ref2))

      _ <- Entity.ref(max).<(ref2).query.get.map(_ ==> List())
      _ <- Entity.ref(max).<(ref3).query.get.map(_ ==> List(ref2))

      _ <- Entity.ref(max).<=(ref1).query.get.map(_ ==> List())
      _ <- Entity.ref(max).<=(ref2).query.get.map(_ ==> List(ref2))

      _ <- Entity.ref(max).>(ref2).query.get.map(_ ==> List())
      _ <- Entity.ref(max).>(ref1).query.get.map(_ ==> List(ref2))

      _ <- Entity.ref(max).>=(ref2).query.get.map(_ ==> List(ref2))
      _ <- Entity.ref(max).>=(ref3).query.get.map(_ ==> List())


      // n attributes
      _ <- Entity.i.ref(max).query.get.map(_ ==> List(b))

      _ <- Entity.i.ref(max)(ref2).query.get.map(_ ==> List(b))
      _ <- Entity.i.ref(max)(ref1).query.get.map(_ ==> List())

      _ <- Entity.i.ref(max).not(ref2).query.get.map(_ ==> List())
      _ <- Entity.i.ref(max).not(ref1).query.get.map(_ ==> List(b))

      _ <- Entity.i.ref(max).<(ref2).query.get.map(_ ==> List())
      _ <- Entity.i.ref(max).<(ref3).query.get.map(_ ==> List(b))

      _ <- Entity.i.ref(max).<=(ref1).query.get.map(_ ==> List())
      _ <- Entity.i.ref(max).<=(ref2).query.get.map(_ ==> List(b))

      _ <- Entity.i.ref(max).>(ref2).query.get.map(_ ==> List())
      _ <- Entity.i.ref(max).>(ref1).query.get.map(_ ==> List(b))

      _ <- Entity.i.ref(max).>=(ref2).query.get.map(_ ==> List(b))
      _ <- Entity.i.ref(max).>=(ref3).query.get.map(_ ==> List())
    } yield ()
  }


  "min/max" - types {
    for {
      _ <- Entity.i.ref.insert(
        (1, ref1),
        (1, ref2),
        (2, ref3),
        (2, ref4),
      ).transact

      _ <- Entity.ref(min).ref(max).query.get.map(_ ==> List((ref1, ref4)))

      _ <- Entity.ref(min)(ref1).ref(max)(ref4).query.get.map(_ ==> List((ref1, ref4)))
      _ <- Entity.ref(min)(ref1).ref(max)(ref5).query.get.map(_ ==> List())

      _ <- Entity.ref(min).not(ref2).ref(max).not(ref3).query.get.map(_ ==> List((ref1, ref4)))
      _ <- Entity.ref(min).not(ref2).ref(max).not(ref4).query.get.map(_ ==> List())

      _ <- Entity.ref(min).<(ref2).ref(max).>(ref3).query.get.map(_ ==> List((ref1, ref4)))
      _ <- Entity.ref(min).<(ref2).ref(max).>(ref4).query.get.map(_ ==> List())

      _ <- Entity.ref(min).<=(ref1).ref(max).>=(ref4).query.get.map(_ ==> List((ref1, ref4)))
      _ <- Entity.ref(min).<=(ref1).ref(max).>=(ref5).query.get.map(_ ==> List())
    } yield ()
  }


  "min/max n" - types {
    for {
      _ <- Entity.i.ref.insert(
        (1, ref1),
        (1, ref2),
        (1, ref3),
        (2, ref4),
        (2, ref5),
        (2, ref6),
        (2, ref6), // (make sure grouped values coalesce)
      ).transact

      _ <- Entity.ref(min(1)).query.get.map(_ ==> List(Set(ref1)))
      _ <- Entity.ref(min(2)).query.get.map(_ ==> List(Set(ref1, ref2)))

      _ <- Entity.ref(max(1)).query.get.map(_ ==> List(Set(ref6)))
      _ <- Entity.ref(max(2)).query.get.map(_ ==> List(Set(ref5, ref6)))

      _ <- Entity.i.a1.ref(min(2)).query.get.map(_ ==> List(
        (1, Set(ref1, ref2)),
        (2, Set(ref4, ref5))
      ))

      _ <- Entity.i.a1.ref(max(2)).query.get.map(_ ==> List(
        (1, Set(ref2, ref3)),
        (2, Set(ref5, ref6))
      ))

      _ <- Entity.i.a1.ref(min(2)).ref(max(2)).query.get.map(_ ==> List(
        (1, Set(ref1, ref2), Set(ref2, ref3)),
        (2, Set(ref4, ref5), Set(ref5, ref6))
      ))
    } yield ()
  }


  "sample" - types {
    val all      = Set(ref1, ref2, ref3)
    val allPairs = List((1, ref1), (2, ref2), (3, ref3))
    for {
      _ <- Entity.i.ref.insert(allPairs).transact

      // 1 attribute
      _ <- Entity.ref(sample).query.get.map(res => all.contains(res.head) ==> true)

      // 1 attribute
      _ <- Entity.i.ref(sample).query.get.map(res => allPairs.contains(res.head) ==> true)
    } yield ()
  }

  "sample ops" - types {
    if (Seq("mariadb", "mysql").contains(database)) {
      Entity.ref(sample)(ref1).query.get
        .map(_ ==> "Unexpected success").recover { case ModelError(err) =>
          err ==> "Operations on sample not implemented for this database."
        }
    } else {
      val all       = Set(ref1, ref2, ref3)
      val (a, b, c) = ((1, ref1), (2, ref2), (3, ref3))
      val allPairs  = List(a, b, c)
      for {
        _ <- Entity.i.ref.insert(allPairs).transact

        // 1 attribute
        // Checking for equality on a sample doesn't make sense
        // _ <- Entity.ref(sample)(ref2).query.get.map(res => all.contains(res.head) ==> true)
        // If you want a specific value, this would be the natural query
        _ <- Entity.ref(ref2).query.get.map(_ ==> List(ref2))

        _ <- Entity.ref(sample).not(ref2).query.get.map { res =>
          List(ref1, ref3).contains(res.head) ==> true
          (res.head == ref2) ==> false
        }
        _ <- Entity.ref(sample).<(ref3).query.get.map { res =>
          List(ref1, ref2).contains(res.head) ==> true
          (res.head == ref3) ==> false
        }
        _ <- Entity.ref(sample).<=(ref2).query.get.map { res =>
          List(ref1, ref2).contains(res.head) ==> true
          (res.head == ref3) ==> false
        }
        _ <- Entity.ref(sample).>(ref1).query.get.map { res =>
          List(ref2, ref3).contains(res.head) ==> true
          (res.head == ref1) ==> false
        }
        _ <- Entity.ref(sample).>=(ref2).query.get.map { res =>
          List(ref2, ref3).contains(res.head) ==> true
          (res.head == ref1) ==> false
        }

        // 1 attribute
        _ <- Entity.i.ref(sample).not(ref2).query.get.map { res =>
          List(a, c).contains(res.head) ==> true
          (res.head == b) ==> false
        }
        _ <- Entity.i.ref(sample).<(ref3).query.get.map { res =>
          List(a, b).contains(res.head) ==> true
          (res.head == c) ==> false
        }
        _ <- Entity.i.ref(sample).<=(ref2).query.get.map { res =>
          List(a, b).contains(res.head) ==> true
          (res.head == c) ==> false
        }
        _ <- Entity.i.ref(sample).>(ref1).query.get.map { res =>
          List(b, c).contains(res.head) ==> true
          (res.head == a) ==> false
        }
        _ <- Entity.i.ref(sample).>=(ref2).query.get.map { res =>
          List(b, c).contains(res.head) ==> true
          (res.head == a) ==> false
        }
      } yield ()
    }
  }


  "samples(n)" - types {
    val all = Set(ref1, ref2, ref3)
    for {
      _ <- Entity.ref.insert(List(ref1, ref2, ref3)).transact
      _ <- Entity.ref(sample(1)).query.get.map(res => all.intersect(res.head).nonEmpty ==> true)
      _ <- Entity.ref(sample(2)).query.get.map(res => all.intersect(res.head).nonEmpty ==> true)
    } yield ()
  }


  "count" - types {
    val (a, b) = ((1, 1), (2, 3))
    for {
      _ <- Entity.i.ref.insert(List(
        (1, ref1),
        (2, ref2),
        (2, ref2),
        (2, ref3),
      )).transact

      // 1 attribute
      _ <- Entity.ref(count).query.get.map(_ ==> List(4))

      _ <- Entity.ref(count)(3).query.get.map(_ ==> List())
      _ <- Entity.ref(count)(4).query.get.map(_ ==> List(4))

      _ <- Entity.ref(count).not(3).query.get.map(_ ==> List(4))
      _ <- Entity.ref(count).not(4).query.get.map(_ ==> List())

      _ <- Entity.ref(count).<(5).query.get.map(_ ==> List(4))
      _ <- Entity.ref(count).<(4).query.get.map(_ ==> List())

      _ <- Entity.ref(count).<=(4).query.get.map(_ ==> List(4))
      _ <- Entity.ref(count).<=(3).query.get.map(_ ==> List())

      _ <- Entity.ref(count).>(3).query.get.map(_ ==> List(4))
      _ <- Entity.ref(count).>(4).query.get.map(_ ==> List())

      _ <- Entity.ref(count).>=(4).query.get.map(_ ==> List(4))
      _ <- Entity.ref(count).>=(5).query.get.map(_ ==> List())


      // n attributes
      _ <- Entity.i.a1.ref(count).query.get.map(_ ==> List(a, b))

      _ <- Entity.i.a1.ref(count)(3).query.get.map(_ ==> List(b))
      _ <- Entity.i.a1.ref(count)(2).query.get.map(_ ==> List())

      _ <- Entity.i.a1.ref(count).not(3).query.get.map(_ ==> List(a))
      _ <- Entity.i.a1.ref(count).not(2).query.get.map(_ ==> List(a, b))

      _ <- Entity.i.a1.ref(count).<(3).query.get.map(_ ==> List(a))
      _ <- Entity.i.a1.ref(count).<(4).query.get.map(_ ==> List(a, b))

      _ <- Entity.i.a1.ref(count).<=(3).query.get.map(_ ==> List(a, b))
      _ <- Entity.i.a1.ref(count).<=(2).query.get.map(_ ==> List(a))

      _ <- Entity.i.a1.ref(count).>(2).query.get.map(_ ==> List(b))
      _ <- Entity.i.a1.ref(count).>(3).query.get.map(_ ==> List())

      _ <- Entity.i.a1.ref(count).>=(3).query.get.map(_ ==> List(b))
      _ <- Entity.i.a1.ref(count).>=(4).query.get.map(_ ==> List())
    } yield ()
  }


  "countDistinct" - types {
    val (a, b) = ((1, 1), (2, 2))
    for {
      _ <- Entity.i.ref.insert(List(
        (1, ref1),
        (2, ref2),
        (2, ref2),
        (2, ref3),
      )).transact

      // 1 attribute
      _ <- Entity.ref(countDistinct).query.get.map(_ ==> List(3))

      _ <- Entity.ref(countDistinct)(2).query.get.map(_ ==> List())
      _ <- Entity.ref(countDistinct)(3).query.get.map(_ ==> List(3))

      _ <- Entity.ref(countDistinct).not(2).query.get.map(_ ==> List(3))
      _ <- Entity.ref(countDistinct).not(3).query.get.map(_ ==> List())

      _ <- Entity.ref(countDistinct).<(4).query.get.map(_ ==> List(3))
      _ <- Entity.ref(countDistinct).<(3).query.get.map(_ ==> List())

      _ <- Entity.ref(countDistinct).<=(3).query.get.map(_ ==> List(3))
      _ <- Entity.ref(countDistinct).<=(2).query.get.map(_ ==> List())

      _ <- Entity.ref(countDistinct).>(2).query.get.map(_ ==> List(3))
      _ <- Entity.ref(countDistinct).>(3).query.get.map(_ ==> List())

      _ <- Entity.ref(countDistinct).>=(3).query.get.map(_ ==> List(3))
      _ <- Entity.ref(countDistinct).>=(4).query.get.map(_ ==> List())


      // n attributes
      _ <- Entity.i.a1.ref(countDistinct).query.get.map(_ ==> List(a, b))

      _ <- Entity.i.a1.ref(countDistinct)(2).query.get.map(_ ==> List(b))
      _ <- Entity.i.a1.ref(countDistinct)(3).query.get.map(_ ==> List())

      _ <- Entity.i.a1.ref(countDistinct).not(2).query.get.map(_ ==> List(a))
      _ <- Entity.i.a1.ref(countDistinct).not(3).query.get.map(_ ==> List(a, b))

      _ <- Entity.i.a1.ref(countDistinct).<(2).query.get.map(_ ==> List(a))
      _ <- Entity.i.a1.ref(countDistinct).<(3).query.get.map(_ ==> List(a, b))

      _ <- Entity.i.a1.ref(countDistinct).<=(2).query.get.map(_ ==> List(a, b))
      _ <- Entity.i.a1.ref(countDistinct).<=(1).query.get.map(_ ==> List(a))

      _ <- Entity.i.a1.ref(countDistinct).>(1).query.get.map(_ ==> List(b))
      _ <- Entity.i.a1.ref(countDistinct).>(2).query.get.map(_ ==> List())

      _ <- Entity.i.a1.ref(countDistinct).>=(2).query.get.map(_ ==> List(b))
      _ <- Entity.i.a1.ref(countDistinct).>=(3).query.get.map(_ ==> List())
    } yield ()
  }
}