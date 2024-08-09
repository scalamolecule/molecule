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

        List(a, b) <- Ns.int.insert(1, 2).transact.map(_.ids)
        //        _ <- Ns.int(3).save.transact
        //        _ <- Ns.int.a1.query.get.map(_ ==> List(1, 2, 3))
        //        _ <- Ns(a).int(10).update.transact
        //        _ <- Ns(b).delete.transact
        //        _ <- Ns.int.a1.query.get.map(_ ==> List(3, 10))

      } yield ()
    }


    "refs" - refs { implicit conn =>
      import molecule.coreTests.dataModels.core.dsl.Refs._
      implicit val tolerantDouble = tolerantDoubleEquality(toleranceDouble)

      for {

        _ <- A.i.B.?(B.iSet).insert(
          (0, None),
          (1, Some(Set(1, 2))),
        ).transact

        _ <- rawQuery(
          """SELECT DISTINCT
            |  A.i,
            |  JSON_GROUP_ARRAY(_B_iSet.VALUE) AS B_iSet
            |FROM A
            |  LEFT JOIN B ON
            |    A.b = B.id
            |  left JOIN JSON_EACH(B.iSet) _B_iSet ON
            |    B.iSet IS NOT NULL
            |WHERE
            |  A.i IS NOT NULL
            |GROUP BY A.i
            |HAVING COUNT(*) > 0;
            |""".stripMargin, true)

        _ <- A.i.B.?(B.iSet).query.i.get.map(_ ==> List(
          (0, None),
          (1, Some(Set(1, 2))),
        ))

        //        _ <- A.i.B.?(B.iSeq).insert(
        //          (0, None),
        //          (1, Some(Seq(1, 2, 1))),
        //        ).transact
        //
        //        _ <- A.i.B.?(B.iSeq).query.i.get.map(_ ==> List(
        //          (0, None),
        //          (1, Some(Seq(1, 2, 1))),
        //        ))

        //        _ <- A.i.B.?(B.iMap).insert(
        //          (0, None),
        //          (1, Some(Map("a" -> 1, "b" -> 2))),
        //        ).transact
        //
        //        _ <- A.i.B.?(B.iMap).query.i.get.map(_ ==> List(
        //          (0, None),
        //          (1, Some(Map("a" -> 1, "b" -> 2))),
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
