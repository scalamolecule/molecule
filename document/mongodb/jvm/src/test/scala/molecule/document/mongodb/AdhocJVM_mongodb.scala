package molecule.document.mongodb

import molecule.base.ast._
import molecule.base.error.{ExecutionError, ModelError}
import molecule.core.util.AggrUtils
import molecule.core.util.Executor._
import molecule.coreTests.dataModels.core.dsl.Types.{Ns, Ref}
import molecule.document.mongodb.async._
import molecule.document.mongodb.setup.TestSuite_mongodb
import utest._
import scala.language.implicitConversions
import molecule.boilerplate.ast.Model._

object AdhocJVM_mongodb extends TestSuite_mongodb with AggrUtils {


  override lazy val tests = Tests {

    "types" - types { implicit conn =>
      import molecule.coreTests.dataModels.core.dsl.Types._
      val a = (1, Set(0, 1, 2), Set(1, 2, 3))
      val b = (2, Set(2, 3), Set(2, 3))
      val c = (3, Set(4), Set(3))
      for {
        //        _ <- Ns.i(1).save.transact
        //        _ <- Ns.i.query.get.map(_ ==> List(1))

        _ <- Ns.int.a1.query.limit(2).get.map(_ ==> Nil)

        _ <- Ns.int.a1.query.offset(2).get.map(_ ==> (Nil, 0, false))
        _ <- Ns.int.a1.query.limit(2).offset(2).get.map(_ ==> (Nil, 0, false))

        // Populated
        _ <- Ns.int.insert(1, 2, 3).i.transact

        _ <- Ns.int.a1.query.limit(0).get.map(_ ==> Nil)
        _ <- Ns.int.a1.query.limit(1).get.map(_ ==> List(1))
        _ <- Ns.int.a1.query.limit(2).get.map(_ ==> List(1, 2))
        _ <- Ns.int.a1.query.limit(3).get.map(_ ==> List(1, 2, 3))
        // limit beyond total count just returns all
        _ <- Ns.int.a1.query.limit(4).get.map(_ ==> List(1, 2, 3))

        _ <- Ns.int.a1.query.offset(0).get.map(_ ==> (List(1, 2, 3), 3, false))
        _ <- Ns.int.a1.query.offset(1).get.map(_ ==> (List(2, 3), 3, false))
        _ <- Ns.int.a1.query.offset(2).get.map(_ ==> (List(3), 3, false))
        _ <- Ns.int.a1.query.offset(3).get.map(_ ==> (Nil, 3, false))

        _ <- Ns.int.a1.query.limit(2).get.map(_ ==> List(1, 2))
        _ <- Ns.int.a1.query.limit(2).offset(0).get.map(_ ==> (List(1, 2), 3, true))
        _ <- Ns.int.a1.query.limit(2).offset(1).get.map(_ ==> (List(2, 3), 3, false))
        _ <- Ns.int.a1.query.limit(2).offset(2).get.map(_ ==> (List(3), 3, false))
        _ <- Ns.int.a1.query.limit(2).offset(3).get.map(_ ==> (Nil, 3, false))




        _ <- Ns.int.a1.query.limit(-1).get.map(_ ==> List(3))
        _ <- Ns.int.a1.query.limit(-2).get.map(_ ==> List(2, 3))
        _ <- Ns.int.a1.query.limit(-3).get.map(_ ==> List(1, 2, 3))
        // limit below total count just returns all
        _ <- Ns.int.a1.query.limit(-4).get.map(_ ==> List(1, 2, 3))

        // When only offset is set, there will be no further rows going backwards
        _ <- Ns.int.a1.query.offset(0).get.map(_ ==> (List(1, 2, 3), 3, false))
        _ <- Ns.int.a1.query.offset(-1).get.map(_ ==> (List(1, 2), 3, false))
        _ <- Ns.int.a1.query.offset(-2).get.map(_ ==> (List(1), 3, false))
        _ <- Ns.int.a1.query.offset(-3).get.map(_ ==> (Nil, 3, false))

        _ <- Ns.int.a1.query.limit(-2).offset(0).get.map(_ ==> (List(2, 3), 3, true))
        _ <- Ns.int.a1.query.limit(-2).offset(-1).get.map(_ ==> (List(1, 2), 3, false))
        _ <- Ns.int.a1.query.limit(-2).offset(-2).get.map(_ ==> (List(1), 3, false))
        _ <- Ns.int.a1.query.limit(-2).offset(-3).get.map(_ ==> (List(), 3, false))

      } yield ()
    }


    "refs" - refs { implicit conn =>
      import molecule.coreTests.dataModels.core.dsl.Refs._
      val a = (1, Set(1, 2), Set(1, 2, 3), 3)
      val b = (2, Set(2, 3), Set(2, 3), 3)
      val c = (2, Set(4), Set(4), 4)

      val d = (2, Set(4), Set(3), 4)
      for {

        List(x, y, z) <- A.i.ii.B.ii.i.insert(a, b, c).transact.map(_.ids)

        _ <- A.i.ii_(B.ii_).B.ii.query.get.map(_ ==> List(
          (2, Set(2, 3, 4)) // Set(2, 3) and Set(4) are coalesced to one Set
        ))
        _ <- A.i.ii_.B.ii(A.ii_).query.get.map(_ ==> List(
          (2, Set(2, 3, 4))
        ))

        // To get un-coalesced Sets, separate by ids
        _ <- A.id.a1.i.ii_(B.ii_).B.ii.query.get.map(_ ==> List(
          (y, 2, Set(2, 3)),
          (z, 2, Set(4))
        ))
        _ <- A.id.a1.i.ii_.B.ii(A.ii_).query.get.map(_ ==> List(
          (y, 2, Set(2, 3)),
          (z, 2, Set(4))
        ))
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
