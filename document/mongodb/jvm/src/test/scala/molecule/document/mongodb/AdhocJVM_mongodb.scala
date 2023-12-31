package molecule.document.mongodb

import molecule.core.util.AggrUtils
import molecule.core.util.Executor._
import molecule.document.mongodb.async._
import molecule.document.mongodb.setup.TestSuite_mongodb
import utest._
import scala.language.implicitConversions


object AdhocJVM_mongodb extends TestSuite_mongodb with AggrUtils {

  override lazy val tests = Tests {

    "types" - types { implicit conn =>
      import molecule.coreTests.dataModels.core.dsl.Types._
      implicit val tolerant = tolerantIntEquality(toleranceInt)
      //      implicit val tolerantDouble = tolerantDoubleEquality(toleranceDouble)
      for {
        _ <- Ns.i.int.insert(List(
          (1, int1),
          (1, int2),
          (2, int2),
          (2, int3),
          (2, int4),
        )).transact

        // Sum of distinct values (Set semantics)

        _ <- Ns.int(sum).query.i.get.map(_.head ==~ int1 + int2 + int3 + int4)
        //        _ <- Ns.i.int(sum).query.get.map(_.map {
        //          case (1, sum) => sum ==~ int1 + int2
        //          case (2, sum) => sum ==~ int2 + int3 + int4
        //        })


        //        _ <- Ns.i(1).save.transact
        //        _ <- Ns.i.query.get.map(_ ==> List(1))
      } yield ()
    }


    "refs" - refs { implicit conn =>
      import molecule.coreTests.dataModels.core.dsl.Refs._

      //      implicit val tolerant = tolerantIntEquality(toleranceInt)
      implicit val tolerantDouble = tolerantDoubleEquality(toleranceDouble)
      for {
        _ <- A.i.B.i.insert(List(
          (1, 1),
          (1, 2),
          (2, 2),
          (2, 3),
          (2, 4),
        )).transact

        _ <- A.B.i(variance).query.i.get.map(_.head ==~ varianceOf(1, 2, 3, 4))

        _ <- A.i.B.i(variance).query.i.get.map(_.map {
          case (1, variance) => variance ==~ varianceOf(1, 2)
          case (2, variance) => variance ==~ varianceOf(2, 3, 4)
        })
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
