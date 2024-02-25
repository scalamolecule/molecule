package molecule.sql.h2

import molecule.core.util.Executor._
import molecule.sql.h2.async._
import molecule.sql.h2.setup.TestSuite_h2
import utest._
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
      for {

        _ <- A.s.Bb.s.Cc.*(C.s)
          .insert("book", "Jan", List("Musician")).transact

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
    //
    //    "partitions" - partition { implicit conn =>
    //      import molecule.coreTests.dataModels.core.dsl.Partitions._
    //      for {
    //
    //        _ <- lit_Book.title.Reviewers.name.Professions.*(gen_Profession.name)
    //          .insert("book", "Jan", List("Musician")).transact
    //
    //      } yield ()
    //    }
  }
}
