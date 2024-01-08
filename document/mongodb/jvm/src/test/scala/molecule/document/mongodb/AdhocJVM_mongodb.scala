package molecule.document.mongodb

import molecule.base.error.ModelError
import molecule.core.util.AggrUtils
import molecule.core.util.Executor._
import molecule.coreTests.dataModels.core.dsl.Types.{Ns, Ref}
import molecule.document.mongodb.async._
import molecule.document.mongodb.setup.TestSuite_mongodb
import utest._
import scala.collection.mutable.ListBuffer
import scala.concurrent.Future
import scala.language.implicitConversions


object AdhocJVM_mongodb extends TestSuite_mongodb with AggrUtils {

  override lazy val tests = Tests {

    "types" - types { implicit conn =>
      import molecule.coreTests.dataModels.core.dsl.Types._
      for {


        List(a, b, c) <- Ns.ints.insert(Set(1), Set(2), Set(3)).transact.map(_.ids)
        _ <- Ns.id.a1.ints.query.i.get.map(_ ==> List(
          (a, Set(1)),
          (b, Set(2)),
          (c, Set(3)),
        ))
//        ).sortBy(_._1))
        /*
        List(
        (659bd9643783b4093936bc8c,Set(3)),
        (659bd9643783b4093936bc8b,Set(2)),
        (659bd9643783b4093936bc8a,Set(1)))
         */

//        _ <- Ns(List(b, c)).ints(4).update.transact
//        _ <- Ns.id.a1.ints.query.get.map(_ ==> List(
//          (a, Set(1)),
//          (b, Set(4)),
//          (c, Set(4)),
//        ))




        //        _ <- Ns.i(1).save.i.transact
        //        _ <- Ns.i.query.get.map(_ ==> List(1))
      } yield ()
    }


    "refs" - refs { implicit conn =>
      import molecule.coreTests.dataModels.core.dsl.Refs._
      for {


        id <- A.ii(Set(1)).B.ii(Set(2)).C.ii(Set(3)).save.transact.map(_.id)
        _ <- A.ii.B.ii.C.ii.query.get.map(_ ==> List((Set(1), Set(2), Set(3))))

        // A
        _ <- A(id).ii(Set(10)).update.transact
        _ <- A.ii.B.ii.C.ii.query.get.map(_ ==> List((Set(10), Set(2), Set(3))))

        // A + B
        _ <- A(id).ii(Set(11)).B.ii(Set(20)).update.transact
        _ <- A.ii.B.ii.C.ii.query.get.map(_ ==> List((Set(11), Set(20), Set(3))))

        // B
        _ <- A(id).B.ii(Set(21)).update.transact
        _ <- A.ii.B.ii.C.ii.query.get.map(_ ==> List((Set(11), Set(21), Set(3))))

        // A + B + C
        _ <- A(id).ii(Set(12)).B.ii(Set(22)).C.ii(Set(30)).update.transact
        _ <- A.ii.B.ii.C.ii.query.get.map(_ ==> List((Set(12), Set(22), Set(30))))

        // A + C
        _ <- A(id).ii(Set(13)).B.C.ii(Set(31)).update.transact
        _ <- A.ii.B.ii.C.ii.query.get.map(_ ==> List((Set(13), Set(22), Set(31))))

        // B + C
        _ <- A(id).B.ii(Set(23)).C.ii(Set(32)).update.transact
        _ <- A.ii.B.ii.C.ii.query.get.map(_ ==> List((Set(13), Set(23), Set(32))))

        // C
        _ <- A(id).B.C.ii(Set(33)).update.transact
        _ <- A.ii.B.ii.C.ii.query.get.map(_ ==> List((Set(13), Set(23), Set(33))))

      } yield ()
    }


    "unique" - unique { implicit conn =>
      import molecule.coreTests.dataModels.core.dsl.Uniques._
      for {
        //            _ <- Uniques.i(1).save.transact


        _ <- Uniques.i(1).i(2).int_(1).update.transact
          .map(_ ==> "Unexpected success").recover { case ModelError(err) =>
            err ==> "Can't transact duplicate attribute Uniques.i"
          }

      } yield ()
    }


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
