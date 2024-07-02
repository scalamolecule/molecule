package molecule.sql.sqlite

import molecule.core.util.Executor._
import molecule.sql.sqlite.async._
import molecule.sql.sqlite.setup.TestSuite_sqlite
import utest._
import scala.language.implicitConversions


//object AdhocJVM_sqlite extends TestSuiteArray_sqlite {
object AdhocJVM_sqlite extends TestSuite_sqlite {

  override lazy val tests = Tests {

    "types" - types { implicit conn =>
      import molecule.coreTests.dataModels.core.dsl.Types._
      implicit val tolerantDouble = tolerantDoubleEquality(toleranceDouble)

      for {

        List(a, b) <- Ns.iMap.int.insert(
          (Map("a" -> 1, "b" -> 2), 1),
          (Map("b" -> 2, "c" -> 3), 2),
        ).transact.map(_.ids)

        // Update all entities where `iMap` has a key = "a"
        _ <- Ns.iMap_("a").int(3).update.transact

        // 1 entity updated
        _ <- Ns.id.a1.iMap.int.query.get.map(_ ==> List(
          (a, Map("a" -> 1, "b" -> 2), 3), // updated
          (b, Map("b" -> 2, "c" -> 3), 2),
        ))



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


        _ <- A.i(1).save.transact
        _ <- A.i(2).B.s("b").save.transact
        _ <- A.i(3).B.s("c").i(3).save.transact

//        // Current entity with A value and ref to B value
//        _ <- A.i.a1.B.i.query.get.map(_ ==> List(
//          (3, 3)
//        ))
//
//        // Filter by A value, update existing B values
//        _ <- A.i_.B.i(4).update.transact
//
//        _ <- A.i.a1.B.i.query.get.map(_ ==> List(
//          (3, 4) // B value updated since there was a previous value
//        ))

        // Filter by A ids, upsert B values (insert if not already present)
        _ <- A.i_.B.i(5).upsert.i.transact

        // Now three A entities with referenced B value
        _ <- A.i.a1.B.i.query.get.map(_ ==> List(
          (1, 5), // relationship to B created and B value inserted
          (2, 5), // B value inserted
          (3, 5), // B value updated
        ))

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
