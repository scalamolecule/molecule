package molecule.document.mongodb

import molecule.core.util.AggrUtils
import molecule.core.util.Executor._
import molecule.coreTests.dataModels.core.dsl.Types.Ns
import molecule.document.mongodb.async._
import molecule.document.mongodb.setup.TestSuite_mongodb
import utest._
import scala.language.implicitConversions


object AdhocJVM_mongodb extends TestSuite_mongodb with AggrUtils {

  override lazy val tests = Tests {

    "types" - types { implicit conn =>
      import molecule.coreTests.dataModels.core.dsl.Types._
      //      implicit val tolerant = tolerantIntEquality(toleranceInt)
      //      implicit val tolerantDouble = tolerantDoubleEquality(toleranceDouble)
      for {

        _ <- Ns.i.ints.insert(List(
          (1, Set(int1, int2)),
          (2, Set(int2, int3)),
          (2, Set(int3, int4)),
          (2, Set(int3, int4)),
        )).transact

        // Sum of unique values (Set semantics)

        _ <- Ns.ints(sum).query.get.map(_.head.head ==~ int1 + int2 + int3 + int4)

        _ <- Ns.i.ints(sum).query.get.map(_.map {
          case (1, setWithSum) => setWithSum.head ==~ int1 + int2
          case (2, setWithSum) => setWithSum.head ==~ int2 + int3 + int4
        })


        //        _ <- Ns.i(1).save.transact
        //        _ <- Ns.i.query.get.map(_ ==> List(1))
      } yield ()
    }


    "refs" - refs { implicit conn =>
      import molecule.coreTests.dataModels.core.dsl.Refs._

      //      implicit val tolerant = tolerantIntEquality(toleranceInt)
      implicit val tolerantDouble = tolerantDoubleEquality(toleranceDouble)
      val (median_2_3, median_1_2) = if (database == "MongoDB") {
        (2, 1)
      } else {
        (
          (2 + 3).toDouble / 2.0,
          (1 + 2).toDouble / 2.0
        )
      }

        for {
          _ <- A.i.B.ii.insert(List(
            (1, Set(1, 2)),
            (2, Set(2, 3)),
            (2, Set(3, 4)),
            (2, Set(3, 4)),
          )).transact

//          _ <- A.B.ii.query.i.get.map(_ ==> List(Set(1, 2, 3, 4)))
          _ <- A.B.ii(median).query.i.get.map(_.head ==~ median_2_3)

//          _ <- A.i.a1.B.ii.query.get.map(_ ==> List(
//            (1, Set(1, 2)),
//            (2, Set(2, 3, 4)),
//          ))
//          _ <- A.i.B.ii(median).query.get.map(_.map {
//            case (1, median) => median ==~ median_1_2
//            case (2, median) => median ==~ 3.0
//          })


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
