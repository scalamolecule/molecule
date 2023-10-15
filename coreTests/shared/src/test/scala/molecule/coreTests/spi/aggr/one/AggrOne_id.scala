package molecule.coreTests.spi.aggr.one

import molecule.base.error.ModelError
import molecule.core.api.ApiAsync
import molecule.core.spi.SpiAsync
import molecule.core.util.Executor._
import molecule.coreTests.async._
import molecule.coreTests.dataModels.core.dsl.Types._
import molecule.coreTests.setup.CoreTestSuite
import utest._
import scala.concurrent.Future

trait AggrOne_id extends CoreTestSuite with ApiAsync { spi: SpiAsync =>

  override lazy val tests = Tests {

    "id only not allowed" - types { implicit conn =>
      for {
        _ <- Ns.id.query.get
          .map(_ ==> "Unexpected success").recover { case ModelError(err) =>
            err ==> "Querying for the entity id only is not allowed. " +
              "Please add at least one attribute (can be tacit)."
          }
      } yield ()
    }


    "distinct" - types { implicit conn =>
      // Since all entity ids are unique, this aggregation function is not really needed.
      // But we keep it for parity with use with aggregating other attribute values.
      for {
        List(x, y, z) <- Ns.s.insert("a", "b", "b").transact.map(_.ids)

        _ <- Ns.id.a1.s.query.get.map(_ ==> List(
          (x, "a"),
          (y, "b"),
          (z, "b"),
        ))

        // Without unique ids (or other unique attribute values), equal `s` values coalesce to one value
        _ <- Ns.s.query.get.map(_ ==> List(
          "a",
          "b", // 2 rows coalesced
        ))

        // Tacit id without any filter/aggregate function makes no difference
        _ <- Ns.id_.s.query.get.map(_ ==> List(
          "a",
          "b", // 2 rows coalesced
        ))

        // Distinct entity ids are returned in a Set
        _ <- Ns.id(distinct).s.a1.query.get.map(_ ==> List(
          (Set(x), "a"),
          (Set(y, z), "b"),
        ))

        // Without other distinguishing values, all ids will coalesce to one Set
        _ <- Ns.id(distinct).s_.query.get.map(_ ==> List(
          Set(x, y, z),
        ))

        _ <- Ns.id(distinct).query.get
          .map(_ ==> "Unexpected success").recover { case ModelError(err) =>
            err ==> "Querying for the entity id only is not allowed. " +
              "Please add at least one attribute (can be tacit)."
          }
      } yield ()
    }


    "min" - types { implicit conn =>
      for {
        List(x, y, z) <- Ns.s.insert("a", "b", "b").transact.map(_.ids)

        _ <- Ns.id.a1.s.query.get.map(_ ==> List(
          (x, "a"),
          (y, "b"),
          (z, "b"),
        ))

        // This can be useful to answer the question "when was this value first asserted"
        _ <- Ns.id(min).s.query.get.map(_ ==> List(
          (x, "a"),
          (y, "b"), // "b" was first asserted with entity id y
        ))

        _ <- Ns.id(min(1)).s.a1.query.get.map(_ ==> List(
          (Set(x), "a"),
          (Set(y), "b"),
        ))

        // "The two first entity ids of each value s"
        _ <- Ns.id(min(2)).s.a1.query.get.map(_ ==> List(
          (Set(x), "a"),
          (Set(y, z), "b"),
        ))

        _ <- Ns.id(min).s_.query.get.map(_ ==> List(x))
        _ <- Ns.id(min(1)).s_.query.get.map(_ ==> List(Set(x)))
        _ <- Ns.id(min(2)).s_.query.get.map(_ ==> List(Set(x, y)))

        _ <- Ns.id(min).query.get
          .map(_ ==> "Unexpected success").recover { case ModelError(err) =>
            err ==> "Querying for the entity id only is not allowed. " +
              "Please add at least one attribute (can be tacit)."
          }

        _ <- Ns.id(min(1)).query.get
          .map(_ ==> "Unexpected success").recover { case ModelError(err) =>
            err ==> "Querying for the entity id only is not allowed. " +
              "Please add at least one attribute (can be tacit)."
          }

        // Tacit aggregation on entity ids not allowed.
        _ <- Future(compileError("Ns.id_(min).s"))
        _ <- Future(compileError("Ns.id_(min(1)).s"))
      } yield ()
    }


    "max" - types { implicit conn =>
      for {
        List(x, y, z) <- Ns.s.insert("a", "b", "b").transact.map(_.ids)

        _ <- Ns.id.a1.s.query.get.map(_ ==> List(
          (x, "a"),
          (y, "b"),
          (z, "b"),
        ))

        // This can be useful to answer the question "when was this value lastly asserted"
        _ <- Ns.id(max).s.query.get.map(_ ==> List(
          (x, "a"),
          (z, "b"),
        ))

        _ <- Ns.id(max(1)).s.query.get.map(_ ==> List(
          (Set(x), "a"),
          (Set(z), "b"),
        ))

        // "The two last entities of each value s"
        _ <- Ns.id(max(2)).s.query.get.map(_ ==> List(
          (Set(x), "a"),
          (Set(y, z), "b"),
        ))

        _ <- Ns.id(max).s_.query.get.map(_ ==> List(z))
        _ <- Ns.id(max(1)).s_.query.get.map(_ ==> List(Set(z)))
        _ <- Ns.id(max(2)).s_.query.get.map(_ ==> List(Set(y, z)))

        _ <- Ns.id(max).query.get
          .map(_ ==> "Unexpected success").recover { case ModelError(err) =>
            err ==> "Querying for the entity id only is not allowed. " +
              "Please add at least one attribute (can be tacit)."
          }

        _ <- Ns.id(max(1)).query.get
          .map(_ ==> "Unexpected success").recover { case ModelError(err) =>
            err ==> "Querying for the entity id only is not allowed. " +
              "Please add at least one attribute (can be tacit)."
          }

        // Tacit aggregation on entity ids not allowed.
        _ <- Future(compileError("Ns.id_(max).s"))
        _ <- Future(compileError("Ns.id_(max(1)).s"))
      } yield ()
    }


    "rand" - types { implicit conn =>
      for {
        allIdsSet <- Ns.s.insert("a", "b", "c").transact.map(_.ids.toSet)

        _ <- Ns.id(rand).s_.query.get.map(randomIds =>
          allIdsSet.contains(randomIds.head) ==> true
        )
        _ <- Ns.id(rand(1)).s_.query.get.map(randomIdSets =>
          allIdsSet.intersect(randomIdSets.head).nonEmpty ==> true
        )
        _ <- Ns.id(rand(2)).s_.query.get.map(randomIdSets =>
          allIdsSet.intersect(randomIdSets.head).nonEmpty ==> true
        )

        _ <- Ns.id(rand).query.get
          .map(_ ==> "Unexpected success").recover { case ModelError(err) =>
            err ==> "Querying for the entity id only is not allowed. " +
              "Please add at least one attribute (can be tacit)."
          }

        _ <- Ns.id(rand(1)).query.get
          .map(_ ==> "Unexpected success").recover { case ModelError(err) =>
            err ==> "Querying for the entity id only is not allowed. " +
              "Please add at least one attribute (can be tacit)."
          }

        // Tacit aggregation on entity ids not allowed.
        _ <- Future(compileError("Ns.id_(rand).s"))
        _ <- Future(compileError("Ns.id_(rand(1)).s"))
      } yield ()
    }


    "sample" - types { implicit futConn =>
      for {
        allIdsSet <- Ns.s.insert("a", "b", "c").transact.map(_.ids.toSet)

        _ <- Ns.id(sample).s_.query.get.map(sampleIds =>
          allIdsSet.contains(sampleIds.head) ==> true
        )
        _ <- Ns.id(sample(1)).s_.query.get.map(sampleIdSets =>
          allIdsSet.intersect(sampleIdSets.head).nonEmpty ==> true
        )
        _ <- Ns.id(sample(2)).s_.query.get.map(sampleIdSets =>
          allIdsSet.intersect(sampleIdSets.head).nonEmpty ==> true
        )

        _ <- Ns.id(sample).query.get
          .map(_ ==> "Unexpected success").recover { case ModelError(err) =>
            err ==> "Querying for the entity id only is not allowed. " +
              "Please add at least one attribute (can be tacit)."
          }

        _ <- Ns.id(sample(1)).query.get
          .map(_ ==> "Unexpected success").recover { case ModelError(err) =>
            err ==> "Querying for the entity id only is not allowed. " +
              "Please add at least one attribute (can be tacit)."
          }

        // Tacit aggregation on entity ids not allowed.
        _ <- Future(compileError("Ns.id_(sample).s"))
        _ <- Future(compileError("Ns.id_(sample(1)).s"))
      } yield ()
    }


    "count, countDistinct" - types { implicit conn =>
      // Since entity ids are always unique, there will be no differences between
      // using count and countDistinct. So, one might just use count for entity ids.
      for {
        List(x, y, z) <- Ns.s.insert("a", "b", "b").transact.map(_.ids)

        // Distinct values
        _ <- Ns.s.query.get.map(_ ==> List("a", "b"))

        // Since all ids are unique, all values are returned
        _ <- Ns.id.a1.s.query.get.map(_ ==> List(
          (x, "a"),
          (y, "b"),
          (z, "b"),
        ))

        _ <- Ns.s(count).query.get.map(_ ==> List(3))
        _ <- Ns.s(countDistinct).query.get.map(_ ==> List(2))

        _ <- Ns.id(count).a1.s_.query.get.map(_ ==> List(3))
        _ <- Ns.id(countDistinct).s_.query.get.map(_ ==> List(3))

        _ <- Ns.id(count).s.query.get.map(_ ==> List(
          (1, "a"),
          (2, "b"), // 2 entities with s having value "b"
        ))
        _ <- Ns.id(countDistinct).s.query.get.map(_ ==> List(
          (1, "a"),
          (2, "b"),
        ))

        _ <- Ns.id.query.get
          .map(_ ==> "Unexpected success").recover { case ModelError(err) =>
            err ==> "Querying for the entity id only is not allowed. " +
              "Please add at least one attribute (can be tacit)."
          }

        _ <- Ns.id(count).query.get
          .map(_ ==> "Unexpected success").recover { case ModelError(err) =>
            err ==> "Querying for the entity id only is not allowed. " +
              "Please add at least one attribute (can be tacit)."
          }

        _ <- Ns.id(countDistinct).query.get
          .map(_ ==> "Unexpected success").recover { case ModelError(err) =>
            err ==> "Querying for the entity id only is not allowed. " +
              "Please add at least one attribute (can be tacit)."
          }
      } yield ()
    }


    // Aggregate functions that don't make sense for entity ids and are therefore not implemented

    "sum" - types { implicit conn =>
      for {
        List(x, y, z) <- Ns.i.insert(List(1, 1, 1)).transact.map(_.ids)
        _ <- Ns.id.a1.i.query.get.map(_ ==> List(
          (x, 1),
          (y, 1),
          (z, 1),
        ))

        _ <- Ns.id(sum).i_.query.get.map(_.head ==~ (x + y + z))

        _ <- Ns.id(sum).query.get
          .map(_ ==> "Unexpected success").recover { case ModelError(err) =>
            err ==> "Querying for the entity id only is not allowed. " +
              "Please add at least one attribute (can be tacit)."
          }
      } yield ()
    }

    "median" - types { implicit conn =>
      for {

        List(x, y, z) <- Ns.i.insert(List(1, 1, 1)).transact.map(_.ids)
        _ <- Ns.id.a1.i.query.get.map(_ ==> List(
          (x, 1),
          (y, 1),
          (z, 1),
        ))

        _ <- Ns.id(median).i_.query.get.map(_.head ==~ (x + y + z).toDouble / 3.0)

        _ <- Ns.id(median).query.get
          .map(_ ==> "Unexpected success").recover { case ModelError(err) =>
            err ==> "Querying for the entity id only is not allowed. " +
              "Please add at least one attribute (can be tacit)."
          }
      } yield ()
    }

    "avg" - types { implicit conn =>
      for {
        List(x, y, z) <- Ns.i.insert(List(1, 1, 1)).transact.map(_.ids)
        _ <- Ns.id.a1.i.query.get.map(_ ==> List(
          (x, 1),
          (y, 1),
          (z, 1),
        ))

        _ <- Ns.id(avg).i_.query.get.map(_.head ==~ (x + y + z).toDouble / 3.0)

        _ <- Ns.id(avg).query.get
          .map(_ ==> "Unexpected success").recover { case ModelError(err) =>
            err ==> "Querying for the entity id only is not allowed. " +
              "Please add at least one attribute (can be tacit)."
          }
      } yield ()
    }

    "variance" - types { implicit conn =>
      for {List(x, y, z) <- Ns.i.insert(List(1, 1, 1)).transact.map(_.ids)
           _ <- Ns.id.a1.i.query.get.map(_ ==> List(
             (x, 1),
             (y, 1),
             (z, 1),
           ))

           _ <- Ns.id(variance).i_.query.get.map(_.head ==~ varianceOf(x, y, z))

           _ <- Ns.id(variance).query.get
             .map(_ ==> "Unexpected success").recover { case ModelError(err) =>
               err ==> "Querying for the entity id only is not allowed. " +
                 "Please add at least one attribute (can be tacit)."
             }
           } yield ()
    }

    "stddev" - types { implicit conn =>
      for {
        List(x, y, z) <- Ns.i.insert(List(1, 1, 1)).transact.map(_.ids)
        _ <- Ns.id.a1.i.query.get.map(_ ==> List(
          (x, 1),
          (y, 1),
          (z, 1),
        ))

        _ <- Ns.id(stddev).i_.query.get.map(_.head ==~ stdDevOf(x, y, z))

        _ <- Ns.id(stddev).query.get
          .map(_ ==> "Unexpected success").recover { case ModelError(err) =>
            err ==> "Querying for the entity id only is not allowed. " +
              "Please add at least one attribute (can be tacit)."
          }
      } yield ()
    }
  }
}