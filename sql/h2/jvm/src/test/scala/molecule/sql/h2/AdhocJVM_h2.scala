package molecule.sql.h2

import java.time.Instant
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

        id <- Ns.instantMap(Map("a" -> instant0)).save.transact.map(_.id)

        // Adding pair with existing key replaces the value
        _ <- Ns(id).instantMap.add("a" -> instant1).update.transact
        _ <- Ns.instantMap.query.get.map(_.head ==> Map(pinstant1))

        // Adding a non-asserted map attribute has no effect with update
        _ <- Ns(id).stringMap.add(pstring1).update.transact
        _ <- Ns.instantMap.stringMap_?.query.get.map(_ ==> List((Map(pinstant1), None)))

        // Adding a non-asserted map attribute adds the value with upsert
        _ <- Ns(id).stringMap.add(pstring1).upsert.transact
        _ <- Ns.instantMap.stringMap_?.query.get.map(_ ==> List((Map(pinstant1), Some(Map(pstring1)))))

        // Add pair
        _ <- Ns(id).instantMap.add(pinstant2).update.transact
        _ <- Ns.instantMap.query.get.map(_.head ==> Map(pinstant1, pinstant2))

        // Add multiple pairs (vararg)
        _ <- Ns(id).instantMap.add(pinstant3, pinstant4).update.transact
        _ <- Ns.instantMap.query.get.map(_.head ==> Map(pinstant1, pinstant2, pinstant3, pinstant4))

        // Add multiple pairs (Seq)
        _ <- Ns(id).instantMap.add(Seq(pinstant5, pinstant6)).update.transact
        _ <- Ns.instantMap.query.get.map(_.head ==> Map(pinstant1, pinstant2, pinstant3, pinstant4, pinstant5, pinstant6))

        // Adding empty Seq of pairs has no effect
        _ <- Ns(id).instantMap.add(Seq.empty[(String, Instant)]).update.transact
        _ <- Ns.instantMap.query.get.map(_.head ==> Map(pinstant1, pinstant2, pinstant3, pinstant4, pinstant5, pinstant6))

        //        _ <- rawTransact(
        //          """INSERT INTO Ns (
        //            |  intMap
        //            |) VALUES (JSON '{ "a": 1 }')
        //            |""".stripMargin)


        //        _ <- rawQuery(
        //          """SELECT DISTINCT
        //            |  Ns.intSeq
        //            |FROM Ns
        //            |WHERE
        //            |  Ns.intSeq IS NOT NULL;
        //            |""".stripMargin, true)
        //          .map(println)


      } yield ()
    }


    "refs" - refs { implicit conn =>
      import molecule.coreTests.dataModels.core.dsl.Refs._
      for {

        _ <- A.i.B.iSeq.insert(
          (1, List(1, 2, 2)),
          (2, List(2)),
          (2, List(7)),
          (3, List(3)),
          (4, List())
        ).transact

        // has

        //        _ <- A.i.a1.B.iSeq.has(1).query.get.map(_ ==> List(
        //          (1, List(1, 2, 2)),
        //        ))
        //        _ <- A.i.a1.B.iSeq.has(2).query.get.map(_ ==> List(
        //          (1, List(1, 2, 2)),
        //          (2, List(2)),
        //        ))
        //
        //        _ <- A.i.a1.B.iSeq.has(2, 1).query.get.map(_ ==> List(
        //          (1, List(1, 2, 2)),
        //          (2, List(2)),
        //        ))



        _ <- rawQuery(
          """SELECT DISTINCT
            |  A.i,
            |  ARRAY_AGG(B.iSeq)
            |FROM A
            |  INNER JOIN B ON A.b = B.id
            |WHERE
            |  ((ARRAY_CONTAINS(B.iSeq, 2)) OR
            |   (ARRAY_CONTAINS(B.iSeq, 7))) AND
            |  A.i    IS NOT NULL AND
            |  B.iSeq IS NOT NULL
            |GROUP BY A.i
            |ORDER BY A.i;
            |""".stripMargin, true).map(println)

        _ <- rawQuery(
          """SELECT DISTINCT
            |  A.i,
            |  B.iSeq
            |FROM A
            |  INNER JOIN B ON A.b = B.id
            |WHERE
            |  ((ARRAY_CONTAINS(B.iSeq, 2)) OR
            |   (ARRAY_CONTAINS(B.iSeq, 7))) AND
            |  A.i    IS NOT NULL AND
            |  B.iSeq IS NOT NULL
            |//GROUP BY A.i
            |ORDER BY A.i;
            |""".stripMargin, true).map(println)



        _ <- A.i.a1.B.iSeq.has(2, 7).query.i.get.map(_ ==> List(
          (1, List(1, 2, 2)),
          (2, List(2)),
          (2, List(7)),
        ))

        _ <- A.i.a1.B.iSeq_.has(1).query.get.map(_ ==> List(1))
        _ <- A.i.a1.B.iSeq_.has(2).query.get.map(_ ==> List(1, 2))
        _ <- A.i.a1.B.iSeq_.has(2, 7).query.get.map(_ ==> List(1, 2, 2))
        _ <- A.i.a1.B.iSeq_.has(2, 3).query.get.map(_ ==> List(1, 2, 3))

        // hasNo
        _ <- A.i.a1.B.iSeq_.hasNo(1).query.get.map(_ ==> List(2, 2, 3))
        _ <- A.i.a1.B.iSeq_.hasNo(2).query.get.map(_ ==> List(2, 3))
        _ <- A.i.a1.B.iSeq_.hasNo(3).query.get.map(_ ==> List(1, 2, 2))

        // no value - match non-asserted attribute (null)
        // Nothing returned since there's no relationship to B
        _ <- A.i.a1.B.iSeq_().query.i.get.map(_ ==> Nil)

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
