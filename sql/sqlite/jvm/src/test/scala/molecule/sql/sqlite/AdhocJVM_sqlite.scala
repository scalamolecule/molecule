package molecule.sql.sqlite

import java.time.Instant
import molecule.base.error.{ExecutionError, ModelError}
import molecule.core.util.Executor._
import molecule.coreTests.dataModels.core.dsl.Types.Ns
import molecule.coreTests.util.Array2List
import molecule.sql.sqlite.async._
import molecule.sql.sqlite.setup.{TestSuiteArray_sqlite, TestSuite_sqlite}
import utest._
import scala.collection.mutable
import scala.language.implicitConversions


//object AdhocJVM_sqlite extends TestSuiteArray_sqlite {
object AdhocJVM_sqlite extends TestSuite_sqlite {

  override lazy val tests = Tests {

    "types" - types { implicit conn =>
      import molecule.coreTests.dataModels.core.dsl.Types._
      implicit val tolerantDouble = tolerantDoubleEquality(toleranceDouble)


      for {


        ref1 <- Ref.i(1).save.transact.map(_.id)
        _ <- Ns.i(1).ref(ref1).save.transact
//        _ <- Ns.i(1).ref("42").save.transact

        //        _ <- Ns.i.int.insert(
        //          (1, int1),
        //          (1, int2),
        //          (1, int3),
        //          (2, int4),
        //          (2, int5),
        //          (2, int6),
        //          (2, int6), // (make sure grouped values coalesce)
        //        ).transact
        //
        //
        //        _ <- rawQuery(
        //          """SELECT i, JSON_GROUP_ARRAY(distinct int order by int desc) as x
        //            |FROM Ns
        //            |group by i
        //            |""".stripMargin, true)
        //
        //        _ <- rawQuery(
        //          """SELECT i ,(
        //            |    SELECT JSON_GROUP_ARRAY(int)
        //            |    FROM (
        //            |        SELECT distinct int
        //            |        FROM Ns AS _t
        //            |        WHERE _t.i = Ns.i
        //            |        ORDER BY _t.int DESC
        //            |        LIMIT 2
        //            |    )
        //            |) as sum_int
        //            |FROM Ns as Ns
        //            |GROUP BY i
        //            |""".stripMargin, true)


        //        _ <- rawQuery(
        //          """SELECT Ns.i, json_group_array(distinct x.value order by x.value desc)
        //            |FROM Ns, json_each(int) as x
        //            |group by i
        //            |""".stripMargin, true)

        //        _ <- rawQuery(
        //          """SELECT json_group_array(json_extract(int, '$[' || id || ']'))
        //            |FROM Ns, json_each(int)
        //            |WHERE id < 3;
        //            |""".stripMargin, true)


        //        _ <- rawQuery(
        //          """SELECT json_group_array(json_extract(myarray, '$[' || idx || ']'))
        //            |FROM mytable, json_each(myarray)
        //            |WHERE idx < 3;
        //            |""".stripMargin, true)


        //
        //        _ <- Ns.int(min(1)).query.get.map(_ ==> List(Set(int1)))
        //        _ <- Ns.int(min(2)).query.get.map(_ ==> List(Set(int1, int2)))
        //
        //        _ <- Ns.int(max(1)).query.get.map(_ ==> List(Set(int6)))
        //        _ <- Ns.int(max(2)).query.get.map(_ ==> List(Set(int5, int6)))
        //
        //        _ <- Ns.i.a1.int(min(2)).query.get.map(_ ==> List(
        //          (1, Set(int1, int2)),
        //          (2, Set(int4, int5))
        //        ))
        //
        //        _ <- Ns.i.a1.int(max(2)).query.get.map(_ ==> List(
        //          (1, Set(int2, int3)),
        //          (2, Set(int5, int6))
        //        ))
        //
        //        _ <- Ns.i.a1.int(min(2)).int(max(2)).query.get.map(_ ==> List(
        //          (1, Set(int1, int2), Set(int2, int3)),
        //          (2, Set(int4, int5), Set(int5, int6))
        //        ))


      } yield ()
    }


    "refs" - refs { implicit conn =>
      import molecule.coreTests.dataModels.core.dsl.Refs._
      val pint20 = "b" -> 20
      val pint30 = "c" -> 30
      for {


        // Will not be updated since entity has no A -> B relationship
        _ <- B.s("x").iSeq(Seq(1, 2, 3)).save.transact


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
