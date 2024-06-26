package molecule.sql.sqlite

import java.net.URI
import java.time.{Duration, Instant, LocalDate, LocalDateTime, LocalTime, OffsetDateTime, OffsetTime, ZonedDateTime}
import java.util.{Date, UUID}
import molecule.base.error.{ExecutionError, ModelError}
import molecule.core.util.Executor._
import molecule.coreTests.dataModels.core.dsl.Refs.A
import molecule.coreTests.dataModels.core.dsl.Types.Ns
import molecule.coreTests.util.Array2List
import molecule.sql.sqlite.async._
import molecule.sql.sqlite.setup.{TestSuiteArray_sqlite, TestSuite_sqlite}
import utest._
import scala.collection.mutable
import scala.concurrent.Future
import scala.language.implicitConversions


//object AdhocJVM_sqlite extends TestSuiteArray_sqlite {
object AdhocJVM_sqlite extends TestSuite_sqlite {

  override lazy val tests = Tests {

    "types" - types { implicit conn =>
      import molecule.coreTests.dataModels.core.dsl.Types._
      implicit val tolerantDouble = tolerantDoubleEquality(toleranceDouble)

      for {

        _ <- Ns.int.i.intMap_?.insert(2, 1, Option.empty[Map[String, Int]]).transact
        _ <- Ns.int.i.intMap_?.insert(2, 2, Some(Map.empty[String, Int])).transact

        _ <- Ns.int.i.intMap_?.insert(2, 3, Some(Map(pint1, pint2))).transact


        //        _ <- rawQuery(
        //          """SELECT DISTINCT
        //            |  Ns.i,
        //            |  Ns.intMap
        //            |FROM Ns
        //            |WHERE
        //            |  Ns.int = 2 AND
        //            |  Ns.int IS NOT NULL AND
        //            |  Ns.i   IS NOT NULL
        //            |ORDER BY Ns.i;
        //            |""".stripMargin, true)


        _ <- Ns.int_(2).i.a1.intMap_?.query.i.get.map(_ ==>
          List((1, None), (2, None), (3, Some(Map(pint1, pint2)))))

        //        _ <- rawTransact(
        //          """insert into Ns default values
        //            |""".stripMargin)

        //        _ <- rawQuery(
        //          """select id from Ns
        //            |""".stripMargin, true)


      } yield ()
    }


    "refs" - refs { implicit conn =>
      import molecule.coreTests.dataModels.core.dsl.Refs._
      for {


        _ <- A.i.Bb.*(B.iSet).insert((1, List(Set.empty[Int]))).transact

//        // A.i was inserted
//        _ <- A.i.query.get.map(_ ==> List(1))


        _ <- rawQuery(
          """SELECT DISTINCT
            |  A.id,
            |  A.i,
            |  JSON_GROUP_ARRAY(_B_iSet.VALUE) as B_iSet
            |FROM A
            |  LEFT JOIN  A_bb_B                       ON A.id        = A_bb_B.A_id
            |  LEFT JOIN  B                            ON A_bb_B.B_id = B.id
            |  Left JOIN JSON_EACH(B.iSet) AS _B_iSet
            |WHERE
            |  A.i IS NOT NULL
            |GROUP BY A.i, A.id
            |HAVING COUNT(*) > 0;
            |""".stripMargin, true)


        _ <- A.i.Bb.*?(B.iSet).query.i.get.map(_ ==> List((1, Nil)))
//        _ <- A.i.Bb.*(B.iSet).query.get.map(_ ==> Nil)
//
//        // B.iSet was not inserted
//        _ <- A.i.Bb.iSet_?.query.get.map(_ ==> List((1, None)))
//        _ <- A.i.Bb.iSet.query.get.map(_ ==> Nil)



        //        _ <- rawQuery(
        //          """SELECT DISTINCT
        //            |  i,
        //            |  json_group_array(iSet)
        //            |FROM A
        //            |where iSet is not null
        //            |group by i
        //            |""".stripMargin, true)


        //        _ <- rawTransact(
        //          """UPDATE Ns
        //            |SET
        //            |  i = 7
        //            |WHERE
        //            |  Ns.i IS NOT NULL AND
        //            |  exists (
        //            |    select * from Ns
        //            |      INNER JOIN Ns_refs_Ref ON Ns.id = Ns_refs_Ref.Ns_id
        //            |  )
        //            |""".stripMargin)


      } yield ()
    }


    //    "unique" - unique { implicit conn =>
    //      import molecule.coreTests.dataModels.core.dsl.Uniques._
    //      //          val triples             = getTriples.map(t => (t._3, t._1, t._2))
    //      //          val List(a, b, c, d, e) = triples.sortBy(p => (p._2, p._3, p._1))
    //      //          val query               = (c: String, l: Int) => Uniques.int.a3.s.a1.i.a2.query.from(c).limit(l)
    //      for {
    //
    //
    //        _ <- Uniques.i.float.insert((1, float1), (2, float2)).transact
    //        //        _ <- Uniques.i.float.insert((1, float1)).transact
    //        //        _ <- Uniques.i.double.insert((1, double1), (2, double2)).transact
    //        //            _ <- Uniques.i.float.insert((2, float2)).transact
    //
    //        _ <- Uniques.i.float.query.get.map(_ ==> List(
    //          (1, 1.1f),
    //          (2, 2.2f),
    //        ))
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
