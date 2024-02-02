package molecule.sql.postgres

import molecule.base.error.{ExecutionError, ModelError}
import molecule.core.util.Executor._
import molecule.sql.postgres.async._
import molecule.sql.postgres.setup.TestSuite_postgres
import utest._
import scala.collection.immutable.List
import scala.language.implicitConversions

object AdhocJVM_postgres extends TestSuite_postgres {

  override lazy val tests = Tests {

    "types" - types { implicit conn =>
      import molecule.coreTests.dataModels.core.dsl.Types._
      implicit val tolerantDouble = tolerantDoubleEquality(toleranceDouble)
      for {

//        _ <- rawQuery(
//          """SELECT DISTINCT
//            |  Ns.i,
//            |  array_AGG(distinct Ns_ints)
//            |FROM Ns
//            |  , unnest(Ns.ints) as Ns_ints
//            |WHERE
//            |  Ns.i    IS NOT NULL AND
//            |  Ns.ints IS NOT NULL
//            |GROUP BY Ns.i
//            |ORDER BY Ns.i NULLS FIRST;
//            |""".stripMargin, true)

        _ <- Ns.i.shorts.insert(List(
          (1, Set(short1, short2)),
          (2, Set(short2)),
          (2, Set(short3, short4)),
          (2, Set(short3, short4)),
        )).transact

        // Matching values coalesced shorto one Set

        _ <- Ns.shorts(min).query.get.map(_ ==> List(Set(short1)))
        _ <- Ns.shorts(min(1)).query.get.map(_ ==> List(Set(short1)))

        //        _ <- Ns.int.insert(1).transact
        //        _ <- Ns.int.query.get.map(_ ==> List(1))

      } yield ()
    }

    "refs" - refs { implicit conn =>
      import molecule.coreTests.dataModels.core.dsl.Refs._
      for {
        //            id <- A.i(1).B.i(2).C.i(3).save.transact.map(_.id)
        //            _ <- A.i.B.i.C.i.query.get.map(_ ==> List((1, 2, 3)))


        //        _ <- rawQuery(
        //          """SELECT DISTINCT
        //            |  Ns.i,
        //            |  array_AGG(DISTINCT array_to_string(Ns.ints, chr(29)))
        //            |FROM Ns
        //            |WHERE
        //            |  Ns.i    IS NOT NULL AND
        //            |  Ns.ints IS NOT NULL
        //            |GROUP BY Ns.i
        //            |ORDER BY Ns.i NULLS FIRST;
        //            |""".stripMargin, true)
        _ <- A.i.B.ii.insert(List(
          (1, Set(1, 2)),
          (2, Set(2)),
          (2, Set(3, 4)),
          (2, Set(3, 4)),
        )).transact
        //        all = Set(1, 2, 3, 4)
        all = List(
          Set(1, 2),
          Set(2),
          Set(3, 4),
        )

        //        _ <- A.B.ii(sample).query.get.map(res => all.contains(res.head.head) ==> true)
        //        _ <- A.B.ii(sample(1)).query.get.map(res => all.intersect(res.head).nonEmpty ==> true)

        x: B_1[Set[Int], Int] = A.B.ii.apply(sample)
        y: B_1[Set[Int], Int] = A.B.ii.apply(sample(2))
        _ <- A.B.ii.apply(sample(2)).query.get.map { res =>
          println("------")
          println(res.head)
//          all.intersect(res.head).nonEmpty ==> true
          all.contains(res.head) ==> true
        }

      } yield ()
    }


    //    "unique" - unique { implicit conn =>
    //      import molecule.coreTests.dataModels.core.dsl.Uniques._
    //      for {
    //        _ <- Uniques.i(1).save.transact
    //
    //      } yield ()
    //    }
    //

    //    "validation" - validation { implicit conn =>
    //      import molecule.coreTests.dataModels.core.dsl.Validation._
    //
    //      for {
    //        List(r1, r2) <- RefB.i.insert(2, 3).transact.map(_.ids)
    //
    //      } yield ()
    //    }
  }
}