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

case class Aggr_Float_(
  suite: MUnit,
  api: Api_async & Spi_async & DbProviders
) extends TestUtils {

  import api.*
  import suite.*

  "distinct" - types {
    for {
      _ <- Entity.i.float.insert(List(
        (1, float1),
        (2, float2),
        (2, float2),
        (2, float3),
      )).transact

      _ <- Entity.i.float.a1.query.get.map(_ ==> List(
        (1, float1),
        (2, float2), // 2 rows coalesced
        (2, float3),
      ))

      // Distinct values are returned in a Set
      _ <- Entity.i.a1.float(distinct).query.get.map(_ ==> List(
        (1, Set(float1)),
        (2, Set(float2, float3)),
      ))

      _ <- Entity.float(distinct).query.get.map(_.head ==> Set(
        float1, float2, float3
      ))
    } yield ()
  }


  "min" - types {
    val (a, b) = ((1, float1), (1, float2))
    for {
      _ <- Entity.i.float.insert(a, b).transact

      // 1 attribute
      _ <- Entity.float(min).query.get.map(_ ==> List(float1))

      _ <- Entity.float(min)(float1).query.get.map(_ ==> List(float1))
      _ <- Entity.float(min)(float2).query.get.map(_ ==> List())

      _ <- Entity.float(min).not(float1).query.get.map(_ ==> List())
      _ <- Entity.float(min).not(float2).query.get.map(_ ==> List(float1))

      _ <- Entity.float(min).<(float1).query.get.map(_ ==> List())
      _ <- Entity.float(min).<(float2).query.get.map(_ ==> List(float1))

      _ <- Entity.float(min).<=(float0).query.get.map(_ ==> List())
      _ <- Entity.float(min).<=(float1).query.get.map(_ ==> List(float1))

      _ <- Entity.float(min).>(float1).query.get.map(_ ==> List())
      _ <- Entity.float(min).>(float0).query.get.map(_ ==> List(float1))

      _ <- Entity.float(min).>=(float1).query.get.map(_ ==> List(float1))
      _ <- Entity.float(min).>=(float2).query.get.map(_ ==> List())


      // n attributes
      _ <- Entity.i.float(min).query.get.map(_ ==> List(a))

      _ <- Entity.i.float(min)(float1).query.get.map(_ ==> List(a))
      _ <- Entity.i.float(min)(float2).query.get.map(_ ==> List())

      _ <- Entity.i.float(min).not(float1).query.get.map(_ ==> List())
      _ <- Entity.i.float(min).not(float2).query.get.map(_ ==> List(a))

      _ <- Entity.i.float(min).<(float1).query.get.map(_ ==> List())
      _ <- Entity.i.float(min).<(float2).query.get.map(_ ==> List(a))

      _ <- Entity.i.float(min).<=(float0).query.get.map(_ ==> List())
      _ <- Entity.i.float(min).<=(float1).query.get.map(_ ==> List(a))

      _ <- Entity.i.float(min).>(float1).query.get.map(_ ==> List())
      _ <- Entity.i.float(min).>(float0).query.get.map(_ ==> List(a))

      _ <- Entity.i.float(min).>=(float1).query.get.map(_ ==> List(a))
      _ <- Entity.i.float(min).>=(float2).query.get.map(_ ==> List())
    } yield ()
  }


  "max" - types {
    val (a, b) = ((1, float1), (1, float2))
    for {
      _ <- Entity.i.float.insert(a, b).transact

      // 1 attribute
      _ <- Entity.float(max).query.get.map(_ ==> List(float2))

      _ <- Entity.float(max)(float2).query.get.map(_ ==> List(float2))
      _ <- Entity.float(max)(float1).query.get.map(_ ==> List())

      _ <- Entity.float(max).not(float2).query.get.map(_ ==> List())
      _ <- Entity.float(max).not(float1).query.get.map(_ ==> List(float2))

      _ <- Entity.float(max).<(float2).query.get.map(_ ==> List())
      _ <- Entity.float(max).<(float3).query.get.map(_ ==> List(float2))

      _ <- Entity.float(max).<=(float1).query.get.map(_ ==> List())
      _ <- Entity.float(max).<=(float2).query.get.map(_ ==> List(float2))

      _ <- Entity.float(max).>(float2).query.get.map(_ ==> List())
      _ <- Entity.float(max).>(float1).query.get.map(_ ==> List(float2))

      _ <- Entity.float(max).>=(float2).query.get.map(_ ==> List(float2))
      _ <- Entity.float(max).>=(float3).query.get.map(_ ==> List())


      // n attributes
      _ <- Entity.i.float(max).query.get.map(_ ==> List(b))

      _ <- Entity.i.float(max)(float2).query.get.map(_ ==> List(b))
      _ <- Entity.i.float(max)(float1).query.get.map(_ ==> List())

      _ <- Entity.i.float(max).not(float2).query.get.map(_ ==> List())
      _ <- Entity.i.float(max).not(float1).query.get.map(_ ==> List(b))

      _ <- Entity.i.float(max).<(float2).query.get.map(_ ==> List())
      _ <- Entity.i.float(max).<(float3).query.get.map(_ ==> List(b))

      _ <- Entity.i.float(max).<=(float1).query.get.map(_ ==> List())
      _ <- Entity.i.float(max).<=(float2).query.get.map(_ ==> List(b))

      _ <- Entity.i.float(max).>(float2).query.get.map(_ ==> List())
      _ <- Entity.i.float(max).>(float1).query.get.map(_ ==> List(b))

      _ <- Entity.i.float(max).>=(float2).query.get.map(_ ==> List(b))
      _ <- Entity.i.float(max).>=(float3).query.get.map(_ ==> List())
    } yield ()
  }


  "min/max" - types {
    for {
      _ <- Entity.i.float.insert(
        (1, float1),
        (1, float2),
        (2, float3),
        (2, float4),
      ).transact

      _ <- Entity.float(min).float(max).query.get.map(_ ==> List((float1, float4)))

      _ <- Entity.float(min)(float1).float(max)(float4).query.get.map(_ ==> List((float1, float4)))
      _ <- Entity.float(min)(float1).float(max)(float5).query.get.map(_ ==> List())

      _ <- Entity.float(min).not(float2).float(max).not(float3).query.get.map(_ ==> List((float1, float4)))
      _ <- Entity.float(min).not(float2).float(max).not(float4).query.get.map(_ ==> List())

      _ <- Entity.float(min).<(float2).float(max).>(float3).query.get.map(_ ==> List((float1, float4)))
      _ <- Entity.float(min).<(float2).float(max).>(float4).query.get.map(_ ==> List())

      _ <- Entity.float(min).<=(float1).float(max).>=(float4).query.get.map(_ ==> List((float1, float4)))
      _ <- Entity.float(min).<=(float1).float(max).>=(float5).query.get.map(_ ==> List())
    } yield ()
  }


  "min/max n" - types {
    for {
      _ <- Entity.i.float.insert(
        (1, float1),
        (1, float2),
        (1, float3),
        (2, float4),
        (2, float5),
        (2, float6),
        (2, float6), // (make sure grouped values coalesce)
      ).transact

      _ <- Entity.float(min(1)).query.get.map(_ ==> List(Set(float1)))
      _ <- Entity.float(min(2)).query.get.map(_ ==> List(Set(float1, float2)))

      _ <- Entity.float(max(1)).query.get.map(_ ==> List(Set(float6)))
      _ <- Entity.float(max(2)).query.get.map(_ ==> List(Set(float5, float6)))

      _ <- Entity.i.a1.float(min(2)).query.get.map(_ ==> List(
        (1, Set(float1, float2)),
        (2, Set(float4, float5))
      ))

      _ <- Entity.i.a1.float(max(2)).query.get.map(_ ==> List(
        (1, Set(float2, float3)),
        (2, Set(float5, float6))
      ))

      _ <- Entity.i.a1.float(min(2)).float(max(2)).query.get.map(_ ==> List(
        (1, Set(float1, float2), Set(float2, float3)),
        (2, Set(float4, float5), Set(float5, float6))
      ))
    } yield ()
  }


  "sample" - types {
    val all      = Set(float1, float2, float3)
    val allPairs = List((1, float1), (2, float2), (3, float3))
    for {
      _ <- Entity.i.float.insert(allPairs).transact

      // 1 attribute
      _ <- Entity.float(sample).query.get.map(res => all.contains(res.head) ==> true)

      // 1 attribute
      _ <- Entity.i.float(sample).query.get.map(res => allPairs.contains(res.head) ==> true)
    } yield ()
  }

  "sample ops" - types {
    if (Seq("mariadb", "mysql").contains(database)) {
      Entity.float(sample)(float1).query.get
        .map(_ ==> "Unexpected success").recover { case ModelError(err) =>
          err ==> "Operations on sample not implemented for this database."
        }
    } else {
      val all       = Set(float1, float2, float3)
      val (a, b, c) = ((1, float1), (2, float2), (3, float3))
      val allPairs  = List(a, b, c)
      for {
        _ <- Entity.i.float.insert(allPairs).transact

        // 1 attribute
        // Checking for equality on a sample doesn't make sense
        // _ <- Entity.float(sample)(float2).query.get.map(res => all.contains(res.head) ==> true)
        // If you want a specific value, this would be the natural query
        _ <- Entity.float(float2).query.get.map(_ ==> List(float2))

        _ <- Entity.float(sample).not(float2).query.get.map { res =>
          List(float1, float3).contains(res.head) ==> true
          (res.head == float2) ==> false
        }
        _ <- Entity.float(sample).<(float3).query.get.map { res =>
          List(float1, float2).contains(res.head) ==> true
          (res.head == float3) ==> false
        }
        _ <- Entity.float(sample).<=(float2).query.get.map { res =>
          List(float1, float2).contains(res.head) ==> true
          (res.head == float3) ==> false
        }
        _ <- Entity.float(sample).>(float1).query.get.map { res =>
          List(float2, float3).contains(res.head) ==> true
          (res.head == float1) ==> false
        }
        _ <- Entity.float(sample).>=(float2).query.get.map { res =>
          List(float2, float3).contains(res.head) ==> true
          (res.head == float1) ==> false
        }

        // 1 attribute
        _ <- Entity.i.float(sample).not(float2).query.get.map { res =>
          List(a, c).contains(res.head) ==> true
          (res.head == b) ==> false
        }
        _ <- Entity.i.float(sample).<(float3).query.get.map { res =>
          List(a, b).contains(res.head) ==> true
          (res.head == c) ==> false
        }
        _ <- Entity.i.float(sample).<=(float2).query.get.map { res =>
          List(a, b).contains(res.head) ==> true
          (res.head == c) ==> false
        }
        _ <- Entity.i.float(sample).>(float1).query.get.map { res =>
          List(b, c).contains(res.head) ==> true
          (res.head == a) ==> false
        }
        _ <- Entity.i.float(sample).>=(float2).query.get.map { res =>
          List(b, c).contains(res.head) ==> true
          (res.head == a) ==> false
        }
      } yield ()
    }
  }


  "samples(n)" - types {
    val all = Set(float1, float2, float3)
    for {
      _ <- Entity.float.insert(List(float1, float2, float3)).transact
      _ <- Entity.float(sample(1)).query.get.map(res => all.intersect(res.head).nonEmpty ==> true)
      _ <- Entity.float(sample(2)).query.get.map(res => all.intersect(res.head).nonEmpty ==> true)
    } yield ()
  }


  "count" - types {
    val (a, b) = ((1, 1), (2, 3))
    for {
      _ <- Entity.i.float.insert(List(
        (1, float1),
        (2, float2),
        (2, float2),
        (2, float3),
      )).transact

      // 1 attribute
      _ <- Entity.float(count).query.get.map(_ ==> List(4))

      _ <- Entity.float(count)(3).query.get.map(_ ==> List())
      _ <- Entity.float(count)(4).query.get.map(_ ==> List(4))

      _ <- Entity.float(count).not(3).query.get.map(_ ==> List(4))
      _ <- Entity.float(count).not(4).query.get.map(_ ==> List())

      _ <- Entity.float(count).<(5).query.get.map(_ ==> List(4))
      _ <- Entity.float(count).<(4).query.get.map(_ ==> List())

      _ <- Entity.float(count).<=(4).query.get.map(_ ==> List(4))
      _ <- Entity.float(count).<=(3).query.get.map(_ ==> List())

      _ <- Entity.float(count).>(3).query.get.map(_ ==> List(4))
      _ <- Entity.float(count).>(4).query.get.map(_ ==> List())

      _ <- Entity.float(count).>=(4).query.get.map(_ ==> List(4))
      _ <- Entity.float(count).>=(5).query.get.map(_ ==> List())


      // n attributes
      _ <- Entity.i.a1.float(count).query.get.map(_ ==> List(a, b))

      _ <- Entity.i.a1.float(count)(3).query.get.map(_ ==> List(b))
      _ <- Entity.i.a1.float(count)(2).query.get.map(_ ==> List())

      _ <- Entity.i.a1.float(count).not(3).query.get.map(_ ==> List(a))
      _ <- Entity.i.a1.float(count).not(2).query.get.map(_ ==> List(a, b))

      _ <- Entity.i.a1.float(count).<(3).query.get.map(_ ==> List(a))
      _ <- Entity.i.a1.float(count).<(4).query.get.map(_ ==> List(a, b))

      _ <- Entity.i.a1.float(count).<=(3).query.get.map(_ ==> List(a, b))
      _ <- Entity.i.a1.float(count).<=(2).query.get.map(_ ==> List(a))

      _ <- Entity.i.a1.float(count).>(2).query.get.map(_ ==> List(b))
      _ <- Entity.i.a1.float(count).>(3).query.get.map(_ ==> List())

      _ <- Entity.i.a1.float(count).>=(3).query.get.map(_ ==> List(b))
      _ <- Entity.i.a1.float(count).>=(4).query.get.map(_ ==> List())
    } yield ()
  }


  "countDistinct" - types {
    val (a, b) = ((1, 1), (2, 2))
    for {
      _ <- Entity.i.float.insert(List(
        (1, float1),
        (2, float2),
        (2, float2),
        (2, float3),
      )).transact

      // 1 attribute
      _ <- Entity.float(countDistinct).query.get.map(_ ==> List(3))

      _ <- Entity.float(countDistinct)(2).query.get.map(_ ==> List())
      _ <- Entity.float(countDistinct)(3).query.get.map(_ ==> List(3))

      _ <- Entity.float(countDistinct).not(2).query.get.map(_ ==> List(3))
      _ <- Entity.float(countDistinct).not(3).query.get.map(_ ==> List())

      _ <- Entity.float(countDistinct).<(4).query.get.map(_ ==> List(3))
      _ <- Entity.float(countDistinct).<(3).query.get.map(_ ==> List())

      _ <- Entity.float(countDistinct).<=(3).query.get.map(_ ==> List(3))
      _ <- Entity.float(countDistinct).<=(2).query.get.map(_ ==> List())

      _ <- Entity.float(countDistinct).>(2).query.get.map(_ ==> List(3))
      _ <- Entity.float(countDistinct).>(3).query.get.map(_ ==> List())

      _ <- Entity.float(countDistinct).>=(3).query.get.map(_ ==> List(3))
      _ <- Entity.float(countDistinct).>=(4).query.get.map(_ ==> List())


      // n attributes
      _ <- Entity.i.a1.float(countDistinct).query.get.map(_ ==> List(a, b))

      _ <- Entity.i.a1.float(countDistinct)(2).query.get.map(_ ==> List(b))
      _ <- Entity.i.a1.float(countDistinct)(3).query.get.map(_ ==> List())

      _ <- Entity.i.a1.float(countDistinct).not(2).query.get.map(_ ==> List(a))
      _ <- Entity.i.a1.float(countDistinct).not(3).query.get.map(_ ==> List(a, b))

      _ <- Entity.i.a1.float(countDistinct).<(2).query.get.map(_ ==> List(a))
      _ <- Entity.i.a1.float(countDistinct).<(3).query.get.map(_ ==> List(a, b))

      _ <- Entity.i.a1.float(countDistinct).<=(2).query.get.map(_ ==> List(a, b))
      _ <- Entity.i.a1.float(countDistinct).<=(1).query.get.map(_ ==> List(a))

      _ <- Entity.i.a1.float(countDistinct).>(1).query.get.map(_ ==> List(b))
      _ <- Entity.i.a1.float(countDistinct).>(2).query.get.map(_ ==> List())

      _ <- Entity.i.a1.float(countDistinct).>=(2).query.get.map(_ ==> List(b))
      _ <- Entity.i.a1.float(countDistinct).>=(3).query.get.map(_ ==> List())
    } yield ()
  }
}