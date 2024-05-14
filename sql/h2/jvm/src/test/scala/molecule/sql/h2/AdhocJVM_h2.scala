package molecule.sql.h2

import java.time.Instant
import molecule.base.error.ModelError
import molecule.core.util.Executor._
import molecule.coreTests.util.Array2List
import molecule.sql.h2.async._
import molecule.sql.h2.setup.{TestSuiteArray_h2, TestSuite_h2}
import utest._
import scala.language.implicitConversions


//object AdhocJVM_h2 extends TestSuiteArray_h2 {
object AdhocJVM_h2 extends TestSuite_h2 {

  override lazy val tests = Tests {

    "types" - types { implicit conn =>
      import molecule.coreTests.dataModels.core.dsl.Types._
      implicit val tolerantDouble = tolerantDoubleEquality(toleranceDouble)


      for {

        id <- Ns.intMap.apply(Map(pint1, pint2)).save.transact.map(_.id)


        //        _ <- rawQuery(
        //          """SELECT DISTINCT
        //            |  Ns.i,
        //            |  Ns.offsetDateTimeMap
        //            |FROM Ns
        //            |WHERE
        //            |  Ns.i                 IS NOT NULL AND
        //            |  Ns.offsetDateTimeMap IS NOT NULL
        //            |ORDER BY Ns.i;
        //            |""".stripMargin, true).map(println)

        //        _ <- rawTransact(
        //          """INSERT INTO Ns (
        //            |  intMap
        //            |) VALUES (JSON '{ "a": 1 }')
        //            |""".stripMargin)


      } yield ()
    }


    "refs" - refs { implicit conn =>
      import molecule.coreTests.dataModels.core.dsl.Refs._
      for {



        _ <- A.i(1).save.transact
        _ <- A.i(2).B.s("b").save.transact
        _ <- A.i(3).B.s("c").i(3).save.transact

        // Filter by A attribute, update B values
        _ <- A.i_.B.i(4).update.transact
//        _ <- A.i_.B.i(4).upsert.transact

        _ <- A.i.a1.B.i.query.get.map(_ ==> List(
          (1, 4), // relationship to B created + B attribute added
          (2, 4), // B attribute added
          (3, 4), // B attribute updated
        ))

        //        _ <- rawQuery(
        //          """SELECT DISTINCT
        //            |  A.i,
        //            |  ARRAY_AGG(B.iSeq)
        //            |FROM A
        //            |  INNER JOIN B ON A.b = B.id
        //            |WHERE
        //            |  ((ARRAY_CONTAINS(B.iSeq, 2)) OR
        //            |   (ARRAY_CONTAINS(B.iSeq, 7))) AND
        //            |  A.i    IS NOT NULL AND
        //            |  B.iSeq IS NOT NULL
        //            |GROUP BY A.i
        //            |ORDER BY A.i;
        //            |""".stripMargin, true).map(println)
        //
        //        _ <- rawQuery(
        //          """SELECT DISTINCT
        //            |  A.i,
        //            |  B.iSeq
        //            |FROM A
        //            |  INNER JOIN B ON A.b = B.id
        //            |WHERE
        //            |  ((ARRAY_CONTAINS(B.iSeq, 2)) OR
        //            |   (ARRAY_CONTAINS(B.iSeq, 7))) AND
        //            |  A.i    IS NOT NULL AND
        //            |  B.iSeq IS NOT NULL
        //            |//GROUP BY A.i
        //            |ORDER BY A.i;
        //            |""".stripMargin, true).map(println)


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
