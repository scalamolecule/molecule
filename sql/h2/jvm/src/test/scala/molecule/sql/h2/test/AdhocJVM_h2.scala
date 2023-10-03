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

        id <- Ns.refs(Set(ref1, ref2)).save.transact.map(_.id)

        //        _ <- rawQuery(
        //          """SELECT
        //            |  JSON_ARRAYAGG(
        //            |    case
        //            |      when t1.vs = 6 then 8
        //            |      else t1.vs
        //            |    end
        //            |  )
        //            |FROM Ns, JSON_TABLE(Ns.ints, '$[*]' COLUMNS (vs int PATH '$')) t1
        //            |""".stripMargin, true)
        //
        //        _ <- rawQuery(
        //          """SELECT JSON_ARRAYAGG(t1.v)
        //            |FROM Ns, JSON_TABLE(Ns.ints, '$[*]' COLUMNS (v int PATH '$')) t1
        //            |WHERE t1.v NOT IN(6)
        //            |""".stripMargin, true)

        //        _ <- rawTransact(
        //          """UPDATE Ns SET
        //            |  ints = (
        //            |    SELECT JSON_MERGE(JSON_ARRAYAGG(t_1.vs), JSON_ARRAY(8))
        //            |    FROM JSON_TABLE(Ns.ints, '$[*]' COLUMNS (vs int PATH '$')) t_1
        //            |    WHERE t_1.vs NOT IN(6)
        //            |  )
        //            |WHERE Ns.id IN(1) AND
        //            |  Ns.ints IS NOT NULL
        //            |""".stripMargin)




        _ <- Ns(id).refs(Set(ref3, ref4)).update.i.transact
        _ <- Ns.refs.query.get.map(_.head ==> Set(ref3, ref4))

        // Apply Seq of values
        _ <- Ns(id).refs(Set(ref4, ref5)).update.transact
        _ <- Ns.refs.query.get.map(_.head ==> Set(ref4, ref5))

        // Apply empty Seq of values (deleting all values!)
        _ <- Ns(id).refs(Seq.empty[Long]).update.transact
        _ <- Ns.refs.query.get.map(_ ==> Nil)

        _ <- Ns(id).refs(Set(ref1, ref2)).update.transact

        // Delete all (apply no values)
        _ <- Ns(id).refs().update.transact
        _ <- Ns.refs.query.get.map(_ ==> Nil)

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