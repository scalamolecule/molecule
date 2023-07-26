package molecule.coreTests.test.aggr.one

import molecule.base.error.ModelError
import molecule.core.spi.SpiAsync
import molecule.core.util.Executor._
import molecule.coreTests.api.ApiAsyncImplicits
import molecule.coreTests.async._
import molecule.coreTests.dataModels.core.dsl.Types._
import molecule.coreTests.setup.CoreTestSuite
import utest._
import scala.concurrent.Future

trait AggrOne_tx extends CoreTestSuite with ApiAsyncImplicits { self: SpiAsync =>

  override lazy val tests = Tests {

    "id only not allowed" - types { implicit conn =>
      for {
        _ <- Ns.tx.query.get
          .map(_ ==> "Unexpected success").recover { case ModelError(err) =>
            err ==> "With Datomic we can't query for the transaction entity id only. Please add at least one attribute."
          }
      } yield ()
    }


    "distinct" - types { implicit conn =>
      // Since all transaction entity ids are unique, this aggregation function is not really needed.
      // But we keep it for parity with use with aggregating other attribute values.
      for {
        x <- Ns.s.insert("a").transact.map(_.tx)
        y <- Ns.s.insert("b").transact.map(_.tx)
        z <- Ns.s.insert("b").transact.map(_.tx)

        _ <- Ns.tx.a1.s.query.get.map(_ ==> List(
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
        _ <- Ns.tx_.s.query.get.map(_ ==> List(
          "a",
          "b", // 2 rows coalesced
        ))

        // Distinct transaction entity ids are returned in a Set
        _ <- Ns.tx(distinct).s.a1.query.get.map(_ ==> List(
          (Set(x), "a"),
          (Set(y, z), "b"),
        ))

        // Without other distinguishing values, all txs will coalesce to one Set
        _ <- Ns.tx(distinct).s_.query.get.map(_ ==> List(
          Set(x, y, z),
        ))

        _ <- Ns.tx(distinct).query.get
          .map(_ ==> "Unexpected success").recover { case ModelError(err) =>
            err ==> "With Datomic we can't query for the transaction entity id only. Please add at least one attribute."
          }
      } yield ()
    }


    "min" - types { implicit conn =>
      for {
        x <- Ns.s.insert("a").transact.map(_.tx)
        y <- Ns.s.insert("b").transact.map(_.tx)
        z <- Ns.s.insert("b").transact.map(_.tx)

        _ <- Ns.tx.a1.s.query.get.map(_ ==> List(
          (x, "a"),
          (y, "b"),
          (z, "b"),
        ))

        // This can be useful to answer the question "when was this value first asserted"
        _ <- Ns.tx(min).s.query.get.map(_ ==> List(
          (x, "a"),
          (y, "b"), // "b" was first asserted with transaction entity id y
        ))

        _ <- Ns.tx(min(1)).s.query.get.map(_ ==> List(
          (Set(x), "a"),
          (Set(y), "b"),
        ))

        // "The two first entities of each value s"
        _ <- Ns.tx(min(2)).s.query.get.map(_ ==> List(
          (Set(x), "a"),
          (Set(y, z), "b"),
        ))

        _ <- Ns.tx(min).s_.query.get.map(_ ==> List(x))
        _ <- Ns.tx(min(1)).s_.query.get.map(_ ==> List(Set(x)))
        _ <- Ns.tx(min(2)).s_.query.get.map(_ ==> List(Set(x, y)))

        _ <- Ns.tx(min).query.get
          .map(_ ==> "Unexpected success").recover { case ModelError(err) =>
            err ==> "With Datomic we can't query for the transaction entity id only. Please add at least one attribute."
          }

        _ <- Ns.tx(min(1)).query.get
          .map(_ ==> "Unexpected success").recover { case ModelError(err) =>
            err ==> "With Datomic we can't query for the transaction entity id only. Please add at least one attribute."
          }

        // Tacit aggregation on transaction entity ids not allowed.
        _ <- Future(compileError("Ns.tx_(min).s"))
        _ <- Future(compileError("Ns.tx_(min(1)).s"))
      } yield ()
    }


    "max" - types { implicit conn =>
      for {
        x <- Ns.s.insert("a").transact.map(_.tx)
        y <- Ns.s.insert("b").transact.map(_.tx)
        z <- Ns.s.insert("b").transact.map(_.tx)

        _ <- Ns.tx.a1.s.query.get.map(_ ==> List(
          (x, "a"),
          (y, "b"),
          (z, "b"),
        ))

        // This can be useful to answer the question "when was this value lastly asserted"
        _ <- Ns.tx(max).s.query.get.map(_ ==> List(
          (x, "a"),
          (z, "b"),
        ))

        _ <- Ns.tx(max(1)).s.query.get.map(_ ==> List(
          (Set(x), "a"),
          (Set(z), "b"),
        ))

        // "The two last entities of each value s"
        _ <- Ns.tx(max(2)).s.query.get.map(_ ==> List(
          (Set(x), "a"),
          (Set(y, z), "b"),
        ))

        _ <- Ns.tx(max).s_.query.get.map(_ ==> List(z))
        _ <- Ns.tx(max(1)).s_.query.get.map(_ ==> List(Set(z)))
        _ <- Ns.tx(max(2)).s_.query.get.map(_ ==> List(Set(y, z)))

        _ <- Ns.tx(max).query.get
          .map(_ ==> "Unexpected success").recover { case ModelError(err) =>
            err ==> "With Datomic we can't query for the transaction entity id only. Please add at least one attribute."
          }

        _ <- Ns.tx(max(1)).query.get
          .map(_ ==> "Unexpected success").recover { case ModelError(err) =>
            err ==> "With Datomic we can't query for the transaction entity id only. Please add at least one attribute."
          }

        // Tacit aggregation on transaction entity ids not allowed.
        _ <- Future(compileError("Ns.tx_(max).s"))
        _ <- Future(compileError("Ns.tx_(max(1)).s"))
      } yield ()
    }


    "rand" - types { implicit conn =>
      for {
        x <- Ns.s.insert("a").transact.map(_.tx)
        y <- Ns.s.insert("b").transact.map(_.tx)
        z <- Ns.s.insert("b").transact.map(_.tx)
        allIdsSet = Set(x, y, z)

        _ <- Ns.tx(rand).s_.query.get.map(randomIds =>
          allIdsSet.contains(randomIds.head) ==> true
        )
        _ <- Ns.tx(rand(1)).s_.query.get.map(randomIdSets =>
          allIdsSet.intersect(randomIdSets.head).nonEmpty ==> true
        )
        _ <- Ns.tx(rand(2)).s_.query.get.map(randomIdSets =>
          allIdsSet.intersect(randomIdSets.head).nonEmpty ==> true
        )

        _ <- Ns.tx(rand).query.get
          .map(_ ==> "Unexpected success").recover { case ModelError(err) =>
            err ==> "With Datomic we can't query for the transaction entity id only. Please add at least one attribute."
          }

        _ <- Ns.tx(rand(1)).query.get
          .map(_ ==> "Unexpected success").recover { case ModelError(err) =>
            err ==> "With Datomic we can't query for the transaction entity id only. Please add at least one attribute."
          }

        // Tacit aggregation on transaction entity ids not allowed.
        _ <- Future(compileError("Ns.tx_(rand).s"))
        _ <- Future(compileError("Ns.tx_(rand(1)).s"))
      } yield ()
    }


    "sample" - types { implicit futConn =>
      for {
        x <- Ns.s.insert("a").transact.map(_.tx)
        y <- Ns.s.insert("b").transact.map(_.tx)
        z <- Ns.s.insert("b").transact.map(_.tx)
        allIdsSet = Set(x, y, z)

        _ <- Ns.tx(sample).s_.query.get.map(sampleIds =>
          allIdsSet.contains(sampleIds.head) ==> true
        )
        _ <- Ns.tx(sample(1)).s_.query.get.map(sampleIdSets =>
          allIdsSet.intersect(sampleIdSets.head).nonEmpty ==> true
        )
        _ <- Ns.tx(sample(2)).s_.query.get.map(sampleIdSets =>
          allIdsSet.intersect(sampleIdSets.head).nonEmpty ==> true
        )

        _ <- Ns.tx(sample).query.get
          .map(_ ==> "Unexpected success").recover { case ModelError(err) =>
            err ==> "With Datomic we can't query for the transaction entity id only. Please add at least one attribute."
          }

        _ <- Ns.tx(sample(1)).query.get
          .map(_ ==> "Unexpected success").recover { case ModelError(err) =>
            err ==> "With Datomic we can't query for the transaction entity id only. Please add at least one attribute."
          }

        // Tacit aggregation on transaction entity ids not allowed.
        _ <- Future(compileError("Ns.tx_(sample).s"))
        _ <- Future(compileError("Ns.tx_(sample(1)).s"))
      } yield ()
    }


    "count, countDistinct" - types { implicit conn =>
      // Since transaction entity ids are always unique, there will be no differences between
      // using count and countDistinct. So, one might just use count for transaction entity ids.
      for {
        x <- Ns.s.insert("a").transact.map(_.tx)
        y <- Ns.s.insert("b").transact.map(_.tx)
        z <- Ns.s.insert("b").transact.map(_.tx)

        // Distinct values
        _ <- Ns.s.query.get.map(_ ==> List("a", "b"))

        // Since all ids are unique, all values are returned
        _ <- Ns.tx.a1.s.query.get.map(_ ==> List(
          (x, "a"),
          (y, "b"),
          (z, "b"),
        ))

        _ <- Ns.s(count).query.get.map(_ ==> List(3))
        _ <- Ns.s(countDistinct).query.get.map(_ ==> List(2))

        _ <- Ns.tx.apply(count).a1.s_.query.get.map(_ ==> List(3))
        _ <- Ns.tx(countDistinct).s_.query.get.map(_ ==> List(3))

        _ <- Ns.tx(count).s.query.get.map(_ ==> List(
          (1, "a"),
          (2, "b"), // 2 entities with s having value "b"
        ))
        _ <- Ns.tx(countDistinct).s.query.get.map(_ ==> List(
          (1, "a"),
          (2, "b"),
        ))

        _ <- Ns.tx.query.get
          .map(_ ==> "Unexpected success").recover { case ModelError(err) =>
            err ==> "With Datomic we can't query for the transaction entity id only. Please add at least one attribute."
          }

        _ <- Ns.tx(count).query.get
          .map(_ ==> "Unexpected success").recover { case ModelError(err) =>
            err ==> "With Datomic we can't query for the transaction entity id only. Please add at least one attribute."
          }

        _ <- Ns.tx(countDistinct).query.get
          .map(_ ==> "Unexpected success").recover { case ModelError(err) =>
            err ==> "With Datomic we can't query for the transaction entity id only. Please add at least one attribute."
          }
      } yield ()
    }


    // Aggregate functions that don't make sense for transaction entity ids and are therefore not implemented

    "sum" - types { implicit conn =>
      for {
        _ <- Ns.tx(sum).s.query.get
          .map(_ ==> "Unexpected success").recover { case ModelError(err) =>
            err ==> "Aggregating sum for transaction entity ids not implemented."
          }

        _ <- Ns.tx(sum).query.get
          .map(_ ==> "Unexpected success").recover { case ModelError(err) =>
            err ==> "With Datomic we can't query for the transaction entity id only. Please add at least one attribute."
          }

        _ <- Future(compileError("Ns.tx_(sum).s"))
      } yield ()
    }

    "median" - types { implicit conn =>
      for {
        _ <- Ns.tx(median).s.query.get
          .map(_ ==> "Unexpected success").recover { case ModelError(err) =>
            err ==> "Aggregating median for transaction entity ids not implemented."
          }

        _ <- Ns.tx(median).query.get
          .map(_ ==> "Unexpected success").recover { case ModelError(err) =>
            err ==> "With Datomic we can't query for the transaction entity id only. Please add at least one attribute."
          }

        _ <- Future(compileError("Ns.tx_(median).s"))
      } yield ()
    }

    "avg" - types { implicit conn =>
      for {
        _ <- Ns.tx(avg).s.query.get
          .map(_ ==> "Unexpected success").recover { case ModelError(err) =>
            err ==> "Aggregating avg for transaction entity ids not implemented."
          }

        _ <- Ns.tx(avg).query.get
          .map(_ ==> "Unexpected success").recover { case ModelError(err) =>
            err ==> "With Datomic we can't query for the transaction entity id only. Please add at least one attribute."
          }

        _ <- Future(compileError("Ns.tx_(avg).s"))
      } yield ()
    }

    "variance" - types { implicit conn =>
      for {
        _ <- Ns.tx(variance).s.query.get
          .map(_ ==> "Unexpected success").recover { case ModelError(err) =>
            err ==> "Aggregating variance for transaction entity ids not implemented."
          }

        _ <- Ns.tx(variance).query.get
          .map(_ ==> "Unexpected success").recover { case ModelError(err) =>
            err ==> "With Datomic we can't query for the transaction entity id only. Please add at least one attribute."
          }

        _ <- Future(compileError("Ns.tx_(variance).s"))
      } yield ()
    }

    "stddev" - types { implicit conn =>
      for {
        _ <- Ns.tx(stddev).s.query.get
          .map(_ ==> "Unexpected success").recover { case ModelError(err) =>
            err ==> "Aggregating stddev for transaction entity ids not implemented."
          }

        _ <- Ns.tx(stddev).query.get
          .map(_ ==> "Unexpected success").recover { case ModelError(err) =>
            err ==> "With Datomic we can't query for the transaction entity id only. Please add at least one attribute."
          }

        _ <- Future(compileError("Ns.tx_(stddev).s"))
      } yield ()
    }
  }
}