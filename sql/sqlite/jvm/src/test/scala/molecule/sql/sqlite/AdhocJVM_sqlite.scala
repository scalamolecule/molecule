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

        List(r1, r2) <- Ref.i.insert(1, 2).transact.map(_.ids)
        _ <- Ns.refs(Set(r1, r2)).save.i.transact
//        _ <- Ns.refs.query.get.map(_.head ==> Set(r1, r2))

//        // Values are still typed
//        _ <- rawQuery(
//          """SELECT DISTINCT
//            |  Ns.string,
//            |  Ns.int
//            |FROM Ns
//            |WHERE
//            |  Ns.string IS NOT NULL AND
//            |  Ns.int    IS NOT NULL;
//            |""".stripMargin).map(_.head == List("a", "1") ==> false)


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


        _ <- A.i.a1.Bb.*?(B.s_?.iSeq_?).insert(
          //          (1, List()),
          //          (2, List((Some("a"), None))),
          //          (3, List((Some("b"), None), (Some("c"), None))),
          //          (4, List((Some("d"), Some(Seq(1, 2))))),
          (5, List((Some("e"), Some(Seq(2, 3))), (Some("f"), Some(Seq(3, 4))))),
          //          (6, List((Some("g"), Some(Seq(4, 5))), (Some("h"), None))),
        ).transact.map(_.ids)


        //        _ <- rawTransact(
        //          """UPDATE B
        //            |SET
        //            |  iSeq = iSeq
        //            |WHERE
        //            |  iSeq IS NOT NULL AND
        //            |  B.id IN(1, 2)
        //            |""".stripMargin)

        //        _ <- rawTransact(
        //          """UPDATE B
        //            |SET
        //            |  iSeq = '[4, 8]'
        //            |WHERE
        //            |  iSeq IS NOT NULL AND
        //            |  B.id IN(1, 2)
        //            |""".stripMargin)

        //        _ <- rawTransact(
        //          """UPDATE B
        //            |SET
        //            |  iSeq = (
        //            |    SELECT JSON_GROUP_ARRAY(VALUE)
        //            |    FROM (
        //            |      SELECT _vs.value FROM B as b2, JSON_EACH(iSeq) AS _vs where b2.id = B.id
        //            |      UNION ALL
        //            |      SELECT _vs.value FROM JSON_EACH('[4, 5]') AS _vs
        //            |    )
        //            |  )
        //            |WHERE
        //            |  iSeq IS NOT NULL AND
        //            |  id IN(1, 2)
        //            |""".stripMargin)

        //        _ <- rawTransact(
        //          """UPDATE B
        //            |SET
        //            |  iSeq = (
        //            |    SELECT JSON_GROUP_ARRAY(VALUE)
        //            |    FROM (
        //            |      SELECT _vs.value FROM B, JSON_EACH(iSeq) AS _vs where B
        //            |      UNION ALL
        //            |      SELECT _vs.value FROM JSON_EACH('[4, 5]') AS _vs
        //            |    )
        //            |  )
        //            |WHERE
        //            |  iSeq IS NOT NULL AND
        //            |  B.id IN(1, 2)
        //            |""".stripMargin)

        // Filter by A ids, update B values
        _ <- A.i_.Bb.iSeq.add(4, 5).update.transact

        _ <- A.i.a1.Bb.*?(B.s_?.a1.iSeq).query.get.map(_ ==> List(
          //          (1, List()), //                                                           no B value to update
          //          (2, List()), //                                                           no B value to update
          //          (3, List()), //                                                           no B value to update
          //          (4, List((Some("d"), Seq(1, 2, 4, 5)))), //                               update in 1 ref entity
          (5, List((Some("e"), Seq(2, 3, 4, 5)), (Some("f"), Seq(3, 4, 4, 5)))), // update in 2 ref entities
          //          (6, List((Some("g"), Seq(4, 5, 4, 5)))), //                               update, but already has same values
        ))

        //        // Filter by A ids, upsert B values
        //        _ <- A.i_.Bb.iSeq.add(5, 6).upsert.transact
        //
        //        _ <- A.i.a1.Bb.*?(B.s_?.a1.iSeq).query.get.map(_ ==> List(
        //          (1, List((None, Seq(5, 6)))), //                                                      ref + insertion
        //          (2, List((Some("a"), Seq(5, 6)))), //                                                 insertion in 1 ref entity
        //          (3, List((Some("b"), Seq(5, 6)), (Some("c"), Seq(5, 6)))), //                         insertion in 2 ref entities
        //          (4, List((Some("d"), Seq(1, 2, 4, 5, 5, 6)))), //                                     update in 1 ref entity
        //          (5, List((Some("e"), Seq(2, 3, 4, 5, 5, 6)), (Some("f"), Seq(3, 4, 4, 5, 5, 6)))), // update in 2 ref entities
        //          (6, List((Some("g"), Seq(4, 5, 4, 5, 5, 6)), (Some("h"), Seq(5, 6)))), //             update in one ref entity and insertion in another
        //        ))


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


//    "validation" - validation { implicit conn =>
//      import molecule.coreTests.dataModels.core.dsl.Validation._
//      for {
//        id <- MandatoryAttr.name("Bob").age(42).hobbies(Set("golf", "stamps")).save.transact.map(_.id)
//
//        // We can remove a value from a Set as long as it's not the last value
//        _ <- MandatoryAttr(id).hobbies.remove("stamps").update.i.transact
//
//        // Can't remove the last value of a mandatory attribute Set of values
//        _ <- MandatoryAttr(id).hobbies.remove("golf").update.transact
//          .map(_ ==> "Unexpected success").recover {
//            case ModelError(error) =>
//              error ==>
//                """Can't delete mandatory attributes (or remove last values of card-many attributes):
//                  |  MandatoryAttr.hobbies
//                  |""".stripMargin
//          }
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
