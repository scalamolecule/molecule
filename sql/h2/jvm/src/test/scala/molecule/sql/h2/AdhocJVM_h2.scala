package molecule.sql.h2

import molecule.base.error.ModelError
import molecule.core.util.Executor._
import molecule.coreTests.dataModels.core.dsl.Types.Ns
import molecule.sql.h2.async._
import molecule.sql.h2.setup.TestSuite_h2
import utest._
import scala.collection.immutable.List
import scala.concurrent.Future
import scala.language.implicitConversions

object AdhocJVM_h2 extends TestSuite_h2 {

  override lazy val tests = Tests {

    "types" - types { implicit conn =>
      import molecule.coreTests.dataModels.core.dsl.Types._
      implicit val tolerantDouble = tolerantDoubleEquality(toleranceDouble)
      val t  = (1, Set(true))
      val f  = (2, Set(false))
      val tf = (3, Set(true, false))
      for {
        _ <- Ns.i.booleans.insert(List(t, f, tf)).transact

        // Sets with one or more values matching


        //        _ <- rawQuery(
        //          """SELECT DISTINCT
        //            |  Ns.i,
        //            |  JSON_ARRAYAGG(t_2.vs)
        //            |FROM Ns
        //            |  LEFT OUTER JOIN JSON_TABLE(Ns.booleans, '$[*]' COLUMNS (vs TINYINT(1) PATH '$')) t_2 ON true
        //            |WHERE
        //            |  JSON_CONTAINS(Ns.booleans, JSON_ARRAY(true)) AND
        //            |  Ns.i        IS NOT NULL AND
        //            |  Ns.booleans IS NOT NULL
        //            |GROUP BY Ns.i
        //            |ORDER BY Ns.i;
        //            |""".stripMargin, true)


        // "Has this value"
        _ <- Ns.i.a1.booleans.has(true).query.i.get.map(_ ==> List(t, tf))

      } yield ()
    }

    "refs" - refs { implicit conn =>
      import molecule.coreTests.dataModels.core.dsl.Refs._
      val a = (1, Set(1, 2), Set(1, 2, 3), 3)
      val b = (2, Set(2, 3), Set(2, 3), 3)
      val c = (2, Set(4), Set(4), 4)

      val d = (2, Set(4), Set(3), 4)

      for {
        List(_, a2, a3) <- A.i.ii.B.ii.i.insert(a, b, c).transact.map(_.ids)


        //        _ <- A.i.ii_(B.ii_).B.ii.query.get.map(_ ==> List(
        //          (2, Set(2, 3, 4)) // Set(2, 3) and Set(4) are coalesced to one Set
        //        ))
        _ <- A.i.ii_.B.ii(A.ii_).query.i.get.map(_ ==> List(
          (2, Set(2, 3, 4))
        ))

        //        // To get un-coalesced Sets, separate by ids
        //        _ <- A.id.a1.i.ii_(B.ii_).B.ii.query.get.map(_ ==> List(
        //          (a2, 2, Set(2, 3)),
        //          (a3, 2, Set(4))
        //        ))
        //        _ <- A.id.a1.i.ii_.B.ii(A.ii_).query.get.map(_ ==> List(
        //          (a2, 2, Set(2, 3)),
        //          (a3, 2, Set(4))
        //        ))

      } yield ()
    }


    //    "unique" - unique { implicit conn =>
    //      import molecule.coreTests.dataModels.core.dsl.Uniques._
    //      val triples             = getTriples.map(t => (t._3, t._1, t._2))
    //      val List(a, b, c, d, e) = triples.sortBy(p => (p._2, p._3, p._1))
    //      val query               = (c: String, l: Int) => Uniques.int.a3.s.a1.i.a2.query.from(c).limit(l)
    //      for {
    //        _ <- Uniques.int.s.i.insert(triples).transact
    //
    //      } yield ()
    //    }

    //
    //    "validation" - validation { implicit conn =>
    //      import molecule.coreTests.dataModels.core.dsl.Validation._
    //      for {
    //        List(r1, r2) <- RefB.i.insert(2, 3).transact.map(_.ids)
    //
    //
    //      } yield ()
    //    }
  }
}
