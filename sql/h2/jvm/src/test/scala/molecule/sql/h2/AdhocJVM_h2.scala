package molecule.sql.h2

import java.time.Instant
import molecule.base.error.ModelError
import molecule.core.util.Executor._
import molecule.coreTests.dataModels.core.dsl.Types.Ns
import molecule.coreTests.util.Array2List
import molecule.sql.h2.async._
import molecule.sql.h2.setup.{TestSuiteArray_h2, TestSuite_h2}
import utest._
import scala.collection.mutable
import scala.language.implicitConversions


//object AdhocJVM_h2 extends TestSuiteArray_h2 {
object AdhocJVM_h2 extends TestSuite_h2 {

  override lazy val tests = Tests {

    "types" - types { implicit conn =>
      import molecule.coreTests.dataModels.core.dsl.Types._
      implicit val tolerantDouble = tolerantDoubleEquality(toleranceDouble)
      for {

        //        _ <- Ns.i(1).refs(Set(ref1)).save.transact

        id <- Ns.byteArray(Array(byte1, byte2, byte2)).save.transact.map(_.id)
        _ <- Ns.byteArray.query.get.map(_.head ==> Array(byte1, byte2, byte2))

        // Applying Byte Array replaces previous Array
        _ <- Ns(id).byteArray(Array(byte3, byte4, byte4)).update.transact
        //        _ <- Ns(id).byteArray(Array(byte3, byte4, byte4)).upsert.transact
        _ <- Ns.byteArray.query.get.map(_.head ==> Array(byte3, byte4, byte4))




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
      } yield ()
    }


    "refs" - refs { implicit conn =>
      import molecule.coreTests.dataModels.core.dsl.Refs._
      val pint20 = "b" -> 20
      val pint30 = "c" -> 30

      for {

        //        _ <- rawTransact(
        //          """UPDATE B
        //            |SET
        //            |  B.iMap = JSON_OBJECT('iMap': 100 format json)
        //            |WHERE
        //            |  B.iMap IS NOT NULL AND
        //            |  B.id IN(42)
        //            |""".stripMargin)

        //        _ <- rawTransact(
        //          """MERGE INTO B
        //            |USING VALUES (
        //            |  JSON_OBJECT('a': 6)
        //            |) _v(iMap)
        //            |ON B.id IN(1)
        //            |WHEN MATCHED THEN
        //            |  UPDATE SET
        //            |    B.iMap = JSON_OBJECT('a': 7)
        //            |WHEN NOT MATCHED THEN
        //            |  INSERT (iMap) VALUES (
        //            |    JSON_OBJECT('a': 8)
        //            |  )
        //            |""".stripMargin)

        _ <- A.i(1).save.transact
        _ <- A.i(2).B.s("b").save.transact
        _ <- A.i(3).B.s("c").iMap(Map(pint1, pint2)).save.transact
        _ <- A.i(4).B.s("c").iMap(Map(pint2, pint3)).save.transact
        _ <- A.i(5).B.s("c").iMap(Map(pint3, pint4)).save.transact

        // Filter by A ids, update B values
        _ <- A.i_.B.iMap.remove(string3, string4).update.transact

        // 2 entities left with remaining values
        _ <- A.i.a1.B.iMap.query.get.map(_ ==> List(
          (3, Map(pint1, pint2)),
          (4, Map(pint2)),
        ))


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
