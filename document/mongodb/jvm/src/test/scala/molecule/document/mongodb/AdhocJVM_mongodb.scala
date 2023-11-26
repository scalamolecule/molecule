package molecule.document.mongodb

import com.mongodb.client.model.Filters
import molecule.base.error.ModelError
import molecule.core.util.Executor._
import molecule.coreTests.dataModels.core.dsl.Refs.B
import molecule.coreTests.dataModels.core.dsl.Types.Ns
import molecule.document.mongodb.AdhocJVM_mongodb.int2
import molecule.document.mongodb.async._
import molecule.document.mongodb.setup.TestSuite_mongodb
import utest._
import scala.collection.immutable.List
import scala.language.implicitConversions


object AdhocJVM_mongodb extends TestSuite_mongodb {
  val (median_2_3, median_1_2) = if (database == "MongoDB") {
    (int2, int1)
  } else {
    (
      (int2 + int3).toDouble / 2.0,
      (int1 + int2).toDouble / 2.0
    )
  }

  override lazy val tests = Tests {

    "types" - types { implicit conn =>
      import molecule.coreTests.dataModels.core.dsl.Types._
      implicit val tolerantDouble = tolerantDoubleEquality(toleranceDouble)

      for {
        _ <- Ns.i.int.insert(
          (1, int1),
          (1, int2),
          (1, int3),
          (2, int4),
          (2, int5),
          (2, int6),
        ).transact

        _ <- Ns.int(min).query.get.map(_ ==> List(int1))
        _ <- Ns.int(max).query.get.map(_ ==> List(int6))
        _ <- Ns.int(min).int(max).query.get.map(_ ==> List((int1, int6)))

        _ <- Ns.i.a1.int(min).query.get.map(_ ==> List(
          (1, int1),
          (2, int4)
        ))

        _ <- Ns.i.a1.int(max).query.get.map(_ ==> List(
          (1, int3),
          (2, int6)
        ))

        _ <- Ns.i.a1.int(min).int(max).query.get.map(_ ==> List(
          (1, int1, int3),
          (2, int4, int6)
        ))
      } yield ()
    }


    "refs" - refs { implicit conn =>
      import molecule.coreTests.dataModels.core.dsl.Refs._
      implicit val tolerantDouble = tolerantDoubleEquality(toleranceDouble)
      //      implicit val tolerant = tolerantIntEquality(toleranceInt)

      for {
        _ <- A.i.B.i.insert(List(
          (1, 1),
          (1, 2),
          (2, 2),
          (2, 3),
          (2, 4),
        )).transact

        _ <- A.B.i(sum).query.get.map(_.head ==> 1 + 2 + 3 + 4)
        _ <- A.i.a1.B.i(sum).query.get.map(_ ==> List(
          (1, 1 + 2),
          (2, 2 + 3 + 4),
        ))
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
