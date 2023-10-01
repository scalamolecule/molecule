package molecule.sql.h2.test

import molecule.base.error.ModelError
import molecule.core.util.Executor._
import molecule.sql.h2.async._
import molecule.sql.h2.setup.TestSuite_h2
import utest._
import scala.language.implicitConversions

object AdhocJVM_h2 extends TestSuite_h2 {

  override lazy val tests = Tests {

    "types" - types { implicit conn =>
      import molecule.coreTests.dataModels.core.dsl.Types._
      for {
//        _ <- Ns.int.insert(1).i.transact
//        _ <- Ns.int.query.i.get.map(_ ==> List(1))

//        List(r1, r2) <- Ref.i.insert(1, 2).transact.map(_.ids)
//        _ <- Ns.i(7).refs(Set(r1, r2)).save.transact
        _ <- Ns.i(7).refs(Set(4L, 5L)).save.transact
//        _ <- Ns.refs(Set(4L, 5L)).save.transact
        //        _ <- Ns.refs(Set(r1, r2)).save.transact
//        _ <- Ns.refs(Set(1L, 2L)).save.transact
        //        _ <- Ns.i(1).refs(Set(r1)).save.transact
        //        _ <- Ns.i(1).refs(Set(3L, 4L, 5L)).save.transact


        //        _ <- rawTransact(
        //          """insert into Ns(ints) values ('[6,7,8]')"""
        //        )
        //        _ <- rawQuery(
        //          """select ints from Ns"""
        //        )
        //        _ <- rawQuery(
        //          """select refs from Ns"""
        //        )

        //        _ <- rawQuery(
        //          """SELECT
        //            |  Ref_id
        //            |FROM Ns
        //            |INNER JOIN Ns_refs_Ref ON Ns.id = Ns_refs_Ref.Ns_id
        //            |WHERE
        //            |  Ns.i IS NOT NULL
        //            |;""".stripMargin, true)
        //
        //        _ <- rawQuery(
        //          """SELECT
        //            |  id,
        //            |  i, s
        //            |FROM Ns
        //            |;""".stripMargin, true)
        _ <- rawQuery(
          """SELECT
            |  Ns_id,
            |  Ref_id
            |FROM Ns_refs_Ref
            |;""".stripMargin, true)

        _ <- rawQuery(
          """SELECT
            |  count(Ns_id)
            |FROM Ns_refs_Ref
            |;""".stripMargin, true)

//        _ <- rawQuery(
//          """SELECT distinct
//            |  Ns_id,
//            |  Ref_id
//            |FROM Ns_refs_Ref
//            |;""".stripMargin, true)
//
//        _ <- rawQuery(
//          """SELECT
//            |  Ns.i,
//            |  ARRAY_AGG(Ns_refs_Ref.Ref_id) Ns_refs
//            |FROM Ns
//            |INNER JOIN Ns_refs_Ref ON Ns.id = Ns_refs_Ref.Ns_id
//            |WHERE
//            |  Ns.i IS NOT NULL
//            |GROUP BY Ns.id;""".stripMargin, true)
//
//        _ <- rawQuery(
//          """SELECT DISTINCT
//            |  Ns.i,
//            |  ARRAY_AGG(Ns_refs_Ref.Ref_id) Ns_refs
//            |FROM Ns
//            |INNER JOIN Ns_refs_Ref ON Ns.id = Ns_refs_Ref.Ns_id
//            |WHERE
//            |  Ns.i IS NOT NULL
//            |GROUP BY Ns.id;""".stripMargin, true)

        //        _ <- rawQuery(
        //          """SELECT
        //            |  JSON_ARRAYAGG(Ns_refs_Ref.Ns_id)
        //            |FROM Ns
        //            |INNER JOIN Ns_refs_Ref ON Ns.id = Ns_refs_Ref.Ns_id
        //            |;""".stripMargin, true)
        //        _ <- rawQuery(
        //          """SELECT
        //            |  JSON_ARRAYAGG(Ns_refs_Ref.Ref_id)
        //            |FROM Ns
        //            |INNER JOIN Ns_refs_Ref ON Ns.id = Ns_refs_Ref.Ns_id
        //            |;""".stripMargin, true)
        //
        //        _ <- rawQuery(
        //          """SELECT
        //            |  Ns.i,
        //            |  JSON_ARRAYAGG(Ns_refs_Ref.Ref_id) Ns_refs
        //            |FROM Ns
        //            |INNER JOIN Ns_refs_Ref ON Ns.id = Ns_refs_Ref.Ns_id
        //            |WHERE
        //            |  Ns.i IS NOT NULL
        //            |GROUP BY Ns.id;""".stripMargin, true)


//        _ <- Ns.i.refs.query.get.map(_ ==> List((7, Set(r1, r2))))

      } yield ()
    }

    "refs" - refs { implicit conn =>
      import molecule.coreTests.dataModels.core.dsl.Refs._
      for {
//        id <- A.i(1).B.i(2).C.i(3).save.transact.map(_.id)
//        _ <- A.i.B.i.C.i.query.get.map(_ ==> List((1, 2, 3)))


        _ <- A.Bb.i.insert(1).transact
//        _ <- A.Bb.i.query.get.map(_ ==> List(1))
//
//        _ <- A.i.Bb.i.insert(1, 2).transact
//        _ <- A.i.Bb.i.query.get.map(_ ==> List((1, 2)))
//
//
//        _ <- A.Bb.Cc.i.insert(1).transact
//        _ <- A.Bb.Cc.i.query.get.map(_ ==> List(1))
//
//        _ <- A.Bb.i.Cc.i.insert(1, 2).transact
//        _ <- A.Bb.i.Cc.i.query.get.map(_ ==> List((1, 2)))
//
//        _ <- A.i.Bb.Cc.i.insert(1, 2).transact
//        _ <- A.i.Bb.Cc.i.query.get.map(_ ==> List((1, 2)))
//
//        _ <- A.i.Bb.i.Cc.i.insert(1, 2, 3).transact
//        _ <- A.i.Bb.i.Cc.i.query.get.map(_ ==> List((1, 2, 3)))

      } yield ()
    }


    //    "unique" - unique { implicit conn =>
    //      import molecule.coreTests.dataModels.core.dsl.Uniques._
    //      for {
    //        _ <- Uniques.i(1).save.transact
    //
    //      } yield ()
    //    }
    //

    //    "validation" - validation { implicit conn =>
    //      import molecule.coreTests.dataModels.core.dsl.Validation._
    //
    //      for {
    //        List(r1, r2) <- RefB.i.insert(2, 3).transact.map(_.ids)
    //
    //      } yield ()
    //    }
  }
}