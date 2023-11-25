package molecule.document.mongodb

import com.mongodb.client.model.Filters
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

  override lazy val tests = Tests {

    "types" - types { implicit conn =>
      import molecule.coreTests.dataModels.core.dsl.Types._
      for {
        _ <- Ns.i.int.insert(
          (1, int1),
          (1, int2),
          (1, int3),
          (2, int4),
          (2, int5),
          (2, int6),
          (2, int6), // (make sure grouped values coalesce)
        ).transact

        _ <- Ns.int(min(1)).query.get.map(_ ==> List(Set(int1)))
        _ <- Ns.int(min(2)).query.get.map(_ ==> List(Set(int1, int2)))

        _ <- Ns.int(max(1)).query.get.map(_ ==> List(Set(int6)))
        _ <- Ns.int(max(2)).query.get.map(_ ==> List(Set(int5, int6)))

        _ <- Ns.i.a1.int(min(2)).query.get.map(_ ==> List(
          (1, Set(int1, int2)),
          (2, Set(int4, int5))
        ))

        _ <- Ns.i.a1.int(max(2)).query.get.map(_ ==> List(
          (1, Set(int2, int3)),
          (2, Set(int5, int6))
        ))

        _ <- Ns.i.a1.int(min(2)).int(max(2)).query.get.map(_ ==> List(
          (1, Set(int1, int2), Set(int2, int3)),
          (2, Set(int4, int5), Set(int5, int6))
        ))

//        _ <- Ns.i.ints.insert(List(
//          (1, Set(int1, int2)),
//          (2, Set(int2, int3)),
//          (2, Set(int3, int4)),
//          (2, Set(int3, int4)),
//        )).i.transact
//
//        // Non-aggregated card-many Set of attribute values coalesce
//        _ <- Ns.i.a1.ints.query.i.get.map(_ ==> List(
//          (1, Set(int1, int2)),
//          (2, Set(int2, int3, int4)), // 3 rows coalesced
//        ))
//
//        // Use `distinct` keyword to retrieve unique Sets of Sets
//        _ <- Ns.i.a1.ints(distinct).query.get.map(_ ==> List(
//          (1, Set(Set(int1, int2))),
//          (2, Set(
//            Set(int2, int3),
//            Set(int3, int4) // 2 rows coalesced
//          ))
//        ))
//
//        _ <- Ns.ints(distinct).query.get.map(_ ==> List(
//          Set(
//            Set(int1, int2),
//            Set(int2, int3),
//            Set(int3, int4),
//          )
//        ))
      } yield ()
    }


    "refs" - refs { implicit conn =>
      import molecule.coreTests.dataModels.core.dsl.Refs._
      for {
        _ <- A.i.B.i.insert(List(
          (1, 1),
          (2, 2),
          (2, 2),
          (2, 3),
        )).i.transact

        _ <- A.B.i(count).query.i.get.map(_ ==> List(4))
        _ <- A.i.a1.B.i(count).query.get.map(_ ==> List(
          (1, 1),
          (2, 3)
        ))

        _ <- A.B.i(countDistinct).query.get.map(_ ==> List(3))
        _ <- A.i.a1.B.i(countDistinct).query.get.map(_ ==> List(
          (1, 1),
          (2, 2)
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
