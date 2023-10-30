package molecule.document.mongodb

import java.time.LocalTime
import molecule.core.util.Executor._
import molecule.coreTests.dataModels.core.dsl.Types.Ns
import molecule.document.mongodb.async._
import molecule.document.mongodb.setup.TestSuite_mongodb
import utest._
import scala.collection.immutable.{List, Seq, Set}
import scala.language.implicitConversions


object AdhocJVM_mongodb extends TestSuite_mongodb {


  override lazy val tests = Tests {

    "types" - types { implicit conn =>
      import molecule.coreTests.dataModels.core.dsl.Types._


      for {
        _ <- Ns.i(0).save.transact
        _ <- Ns.i.ints.insert(List(
          (1, Set(int1, int2)),
          (2, Set(int2, int3, int4))
        )).transact

        _ <- Ns.i.a1.query.get.map(_ ==> List(0, 1, 2))
        _ <- Ns.i.a1.ints_.query.get.map(_ ==> List(1, 2))


//      val a = (1, Set(ref1, ref2))
//      val b = (2, Set(ref2, ref3, ref4))
//      for {
//        _ <- Ns.i.refs.insert(List(a, b)).transact
//
//        _ <- Ns.i.a1.refs.query.get.map(_ ==> List(a, b))


      } yield ()
    }

    "refs" - refs { implicit conn =>
      import molecule.coreTests.dataModels.core.dsl.Refs._
      for {
        _ <- A.i(1).B.i(2).save.transact
//        _ <- A.i.B.i.insert(1, 2).transact

//        _ <- rawQuery(
//          """{
//            |  "i": 1
//            |}""".stripMargin
//        )

//        _ <- A.i.query.get.map(_ ==> List(1))
//        _ <- B.i.query.get.map(_ ==> List(2))
        _ <- A.i.B.i.query.get.map(_ ==> List((1, 2)))

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
