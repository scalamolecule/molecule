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

        // Initial values
        //        _ <- Ns.i(1).intSet(Set(int1)).save.transact
        _ <- Ns.i(1).refs(Set(ref1)).save.transact
        //        _ <- Ns.i.Refs.*(Ref.i).insert(
        //          (1, List(2))
        //        ).i.transact


        //        _ <- Ns.i(2).intSet_.update.i.transact


        //        _ <- rawQuery(
        //          """select count(*) from Ns
        //            |    INNER JOIN Ns_refs_Ref ON Ns.id = Ns_refs_Ref.Ns_id
        //            |""".stripMargin, true)
        //
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

        _ <- Ns.i.refs_.query.i.get



        // Update i using asserted filter
        _ <- Ns.i(2).refs_.update.i.transact

        // i has been updated
        //        _ <- Ns.i.intSet_.query.get.map(_.head ==> 2)
        _ <- Ns.i.refs_.query.get.map(_.head ==> 2)


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
        //          """UPDATE Ns
        //            |SET
        //            |  intSet = case when intSet is null then array[] else intSet end || array[1]
        //            |WHERE Ns.id IN(1)
        //            |""".stripMargin)

        //        _ <- rawTransact(
        //          """UPDATE Ns
        //            |SET
        //            |  intSet = case when intSet is null then array[1] else intSet || array[1] end
        //            |WHERE Ns.id IN(1)
        //            |""".stripMargin)


      } yield ()
    }


    "a" - refs { implicit conn =>
      import molecule.coreTests.dataModels.core.dsl.Refs._
      for {
        _ <- A.i(1).B.i(2).save.transact
        _ <- A.i_.B.i(3).upsert.transact
        _ <- A.i.B.i.query.get.map(_ ==> List((1, 3)))
      } yield ()
    }


    "refs" - refs { implicit conn =>
      import molecule.coreTests.dataModels.core.dsl.Refs._
      for {

        _ <- B.i(1).save.transact
        _ <- C.i(1).save.transact
        _ <- C.i(2).save.transact

        id <- A.i(1).B.i(2)._A.C.i(3).save.transact.map(_.id)
        _ <- A.i.B.i._A.C.i.query.get.map(_ ==> List((1, 2, 3)))

        // Updating A.B.i and A.C.i
        _ <- A(id).i(10).B.i(20)._A.C.i(30).update.transact
        _ <- A.i.B.i._A.C.i.query.get.map(_ ==> List((10, 20, 30)))


        //        _ <- A.i(5).B.s("b").i_.upsert.transact
        //        _ <- A.s("a").i_.B.i(5).upsert.transact
        //
        //        _ <- A.i_.B.i(2).C.i(3).upsert.transact
        //        _ <- A.i(1).B.i_.C.i(3).upsert.transact
        //        _ <- A.i(1).B.i(2).C.i_.upsert.transact
        //
        //        _ <- A.i_.B.i_.C.i(3).upsert.transact
        //        _ <- A.i_.B.i(2).C.i_.upsert.transact
        //        _ <- A.i(1).B.i_.C.i_.upsert.transact

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
