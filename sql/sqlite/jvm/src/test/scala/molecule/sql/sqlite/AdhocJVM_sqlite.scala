package molecule.sql.sqlite

import java.net.URI
import java.time.{Duration, Instant, LocalDate, LocalDateTime, LocalTime, OffsetDateTime, OffsetTime, ZonedDateTime}
import java.util.{Date, UUID}
import molecule.base.error.{ExecutionError, ModelError}
import molecule.core.util.Executor._
import molecule.coreTests.dataModels.core.dsl.Refs.{A, B}
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


//        id <- Ns.intMap(Map("a" -> 1)).save.transact.map(_.id)
        id <- Ns.intMap(Map("a" -> 1, "b" -> 2)).save.transact.map(_.id)



        //        _ <- rawQuery(
        //          """SELECT
        //            | x.key,
        //            | x.value
        //            |FROM Ns, JSON_tree(intMap, '$*') AS x
        //            |""".stripMargin, true)
        //
        //
        //        _ <- rawQuery(
        //          """SELECT
        //            |_vs.key
        //            |_vs.value
        //            |FROM Ns, JSON_tree(intMap, '$.a') AS _vs
        //            |""".stripMargin, true)



//        _ <- rawTransact(
//          """UPDATE Ns
//            |SET
//            |  intMap = json_object("b", 10, "c", 30)
//            |WHERE
//            |  Ns.id IN(1)
//            |""".stripMargin)

//        _ <- rawTransact(
//          """UPDATE Ns
//            |SET
//            |  intMap = json_set(intMap, "$.b", 11, "$.c", 21)
//            |WHERE
//            |  Ns.id IN(1)
//            |""".stripMargin)

        //        _ <- rawQuery(
        //          """SELECT JSON_GROUP_ARRAY(VALUE)
        //            |    FROM (
        //            |      SELECT _vs.value FROM Ns, JSON_EACH(stringSeq) AS _vs
        //            |      UNION all
        //            |      SELECT _vs.value FROM JSON_EACH('["a", "b"]') AS _vs
        //            |    )
        //            |""".stripMargin, true)



//        _ <- rawQuery(
//          """select
//            |  json_remove(intMap, '$.a')
//            |from Ns
//            |""".stripMargin, true)

        _ <- rawQuery(
          """
            |select
            |(
            |case json_remove(intMap, '$.x')
            |when '{}' then null
            |when null then intMap
            |else json_remove(intMap, '$.x')
            |end
            |) as z
            |from Ns
            |""".stripMargin, true)

        // Removing all remaining pairs deletes the attribute
        _ <- Ns(id).intMap.remove(Seq(string1)).update.i.transact
        _ <- Ns.intMap.query.get.map(_ ==> Nil)
        //        _ <- rawTransact(
        //          """UPDATE Ns
        //            |SET
        //            |  stringSeq = (
        //            |    SELECT JSON_GROUP_ARRAY(VALUE)
        //            |    FROM (
        //            |      SELECT _vs.value FROM Ns, JSON_EACH(stringSeq) AS _vs
        //            |      UNION ALL
        //            |      SELECT _vs.value FROM Ns, JSON_EACH(?) AS _vs
        //            |    )
        //            |  )
        //            |WHERE
        //            |  stringSeq IS NOT NULL AND
        //            |  Ns.id IN(1)
        //            |""".stripMargin)


      } yield ()
    }


    "refs" - refs { implicit conn =>
      import molecule.coreTests.dataModels.core.dsl.Refs._
      for {


        _ <- A.s("a").save.transact // no A.i filter match
        _ <- A.i(1).save.transact

        _ <- A.s("a").B.s("b").save.transact // no A.i filter match
        _ <- A.s("a").B.i(2).save.transact // no A.i filter match
        _ <- A.i(3).B.s("b").save.transact
        _ <- A.i(4).B.i(4).save.transact

        _ <- A.s("a").B.i(5).C.s("c").save.transact // no A.i filter match
        _ <- A.s("a").B.i(6).C.i(6).save.transact // no A.i filter match
        _ <- A.i(7).B.s("b").C.s("c").save.transact
        _ <- A.i(8).B.s("b").C.i(8).save.transact
        _ <- A.i(9).B.i(9).C.s("c").save.transact
        _ <- A.i(10).B.i(10).C.i(10).save.transact

        // Not filtering on C attribute makes ref to C unknown

        // Only entities having A.i value will have existing B.i and C.i values updated
        _ <- A.i_.B.i(11).C.i(11).update.transact
        _ <- A.i.B.i.C.i.query.get.map(_ ==> List(
          (10, 11, 11) // B.i and C.i updated
        ))

        // Insert refs to B + C or C and set C.i values for all entities that have A.i value
        _ <- A.i_.B.i(12).C.i(12).upsert.i.transact

        _ <- A.i.a1.B.i.C.i.query.get.map(_ ==> List(
          (1, 12, 12), // ref to B inserted, B.i inserted, ref to C inserted, C.i inserted
          (3, 12, 12), // B.i inserted, ref to C inserted, C.i inserted
          (4, 12, 12), // B.i updated, ref to C inserted, C.i inserted
          (7, 12, 12), // B.i inserted, C.i inserted
          (8, 12, 12), // B.i inserted, C.i updated
          (9, 12, 12), // B.i updated, C.i inserted
          (10, 12, 12), // B.i updated, C.i updated
        ))


//        _ <- rawQuery(
//          """SELECT DISTINCT
//            |  A.i,
//            |  JSON_GROUP_ARRAY(_B_iSet.value) as B_iSet
//            |FROM A
//            |  INNER JOIN B ON A.b = B.id
//            |  inner join JSON_EACH(B.iSet) as _B_iSet
//            |WHERE
//            |  NOT EXISTS (
//            |    SELECT *
//            |    FROM JSON_EACH(B.iSet)
//            |    WHERE JSON_EACH.VALUE = A.i
//            |  ) AND
//            |  A.i    IS NOT NULL AND
//            |  A.iSet IS NOT NULL AND
//            |  B.iSet IS NOT NULL
//            |group by A.i
//            |ORDER BY A.i
//            |""".stripMargin, true)






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
