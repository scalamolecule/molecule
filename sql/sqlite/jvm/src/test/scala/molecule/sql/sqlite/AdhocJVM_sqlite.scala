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

        a <- A.i(1).save.transact.map(_.id)
        b <- A.i(2).B.s("b").save.transact.map(_.id)
        c <- A.i(3).B.s("c").i(3).save.transact.map(_.id)

//        // Current entity with A value and ref to B value
//        _ <- A.i.a1.B.i.query.get.map(_ ==> List(
//          (3, 3)
//        ))
//
//        // Filter by A ids, update existing B values
//        _ <- A(a, b, c).B.i(4).update.transact
//
//        _ <- A.i.a1.B.i.query.get.map(_ ==> List(
//          (3, 4) // B value updated since there was a previous value
//        ))

        // Filter by A ids, upsert B values (insert if not already present)
        _ <- A(a, b, c).B.i(5).upsert.i.transact

        // Now three A entities with referenced B value
        _ <- A.i.a1.B.i.query.get.map(_ ==> List(
          (1, 5), // relationship to B created + B value inserted
          (2, 5), // B value inserted
          (3, 5), // B value updated
        ))

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
