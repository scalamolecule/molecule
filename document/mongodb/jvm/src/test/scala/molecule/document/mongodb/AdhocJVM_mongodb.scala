package molecule.document.mongodb

import com.mongodb.client.model.Filters
import molecule.core.util.Executor._
import molecule.coreTests.dataModels.core.dsl.Refs.B
import molecule.document.mongodb.async._
import molecule.document.mongodb.setup.TestSuite_mongodb
import utest._
import scala.collection.immutable.List
import scala.language.implicitConversions


object AdhocJVM_mongodb extends TestSuite_mongodb {


  //  List(
  //    List("aa", "cc"),
  //    List("aa", "c", "x"),
  //  ).sorted

  override lazy val tests = Tests {

    "types" - types { implicit conn =>
      import molecule.coreTests.dataModels.core.dsl.Types._
      for {
        _ <- Ns.i.string.insert(
          (1, "hello"),
          (2, "friends"),
        ).transact

//        _ <- Ns.string.startsWith("fri").query.i.get.map(_ ==> List("friends"))
//        _ <- Ns.string.take(2).query.i.get.map(_ ==> List("he", "fr"))
//
//        _ <- Ns.string_.startsWith("fri").string.take(2).query.i.get.map(_ ==> List("fr"))
//        _ <- Ns.string_.startsWith("fri").string.takeRight(2).query.get.map(_ ==> List("ds"))
//        _ <- Ns.string_.startsWith("fri").string.drop(2).query.get.map(_ ==> List("iends"))
//        _ <- Ns.string_.startsWith("fri").string.dropRight(2).query.get.map(_ ==> List("frien"))
//        _ <- Ns.string_.startsWith("fri").string.substring(1, 4).query.get.map(_ ==> List("rie"))
//        _ <- Ns.string_.startsWith("fri").string.slice(1, 4).query.get.map(_ ==> List("rie"))
//
//        // Multiple filters
        _ <- Ns.string.startsWith("fri").string_.endsWith("ndsx").query.i.get.map(_ ==> List("friends"))
//        _ <- Ns.string_.startsWith("fri").string_.endsWith("nds").string.take(2).query.get.map(_ ==> List("fr"))
//        _ <- Ns.string_.startsWith("fri").string_.endsWith("nds").string.takeRight(2).query.get.map(_ ==> List("ds"))
//        _ <- Ns.string_.startsWith("fri").string_.endsWith("nds").string.drop(2).query.get.map(_ ==> List("iends"))
//        _ <- Ns.string_.startsWith("fri").string_.endsWith("nds").string.dropRight(2).query.get.map(_ ==> List("frien"))
//        _ <- Ns.string_.startsWith("fri").string_.endsWith("nds").string.substring(1, 4).query.get.map(_ ==> List("rie"))
//        _ <- Ns.string_.startsWith("fri").string_.endsWith("nds").string.slice(1, 4).query.get.map(_ ==> List("rie"))
      } yield ()
    }
    "types2" - types { implicit conn =>
      import molecule.coreTests.dataModels.core.dsl.Types._
      for {
        _ <- Ns.i.Ref.int.insert(
          (1, 1),
          (2, 2),
          (3, 3),
          (4, 4),
          (5, 5),
          (6, 6),
          (7, 7),
          (8, 8),
          (9, 9),
        ).transact

        // Mandatory

//        _ <- Ns.int.%(2, 0).query.get.map(_ ==> List(2, 4, 6, 8))
//        _ <- Ns.int.%(2, 1).query.get.map(_ ==> List(1, 3, 5, 7, 9))
//
//        _ <- Ns.int.%(3, 0).query.get.map(_ ==> List(3, 6, 9))
//        _ <- Ns.int.%(3, 1).query.get.map(_ ==> List(1, 4, 7))
//        _ <- Ns.int.%(3, 2).query.get.map(_ ==> List(2, 5, 8))
//
//        _ <- Ns.int.even.query.get.map(_ ==> List(2, 4, 6, 8))
//        _ <- Ns.int.odd.query.get.map(_ ==> List(1, 3, 5, 7, 9))
//
//        // Tacit
//
//        _ <- Ns.i.int_.%(2, 0).query.get.map(_ ==> List(2, 4, 6, 8))
//        _ <- Ns.i.int_.%(2, 1).query.get.map(_ ==> List(1, 3, 5, 7, 9))
//
//        _ <- Ns.i.int_.%(3, 0).query.get.map(_ ==> List(3, 6, 9))
//        _ <- Ns.i.int_.%(3, 1).query.get.map(_ ==> List(1, 4, 7))
//        _ <- Ns.i.int_.%(3, 2).query.get.map(_ ==> List(2, 5, 8))
//
//        _ <- Ns.i.int_.even.query.get.map(_ ==> List(2, 4, 6, 8))
//        _ <- Ns.i.int_.odd.query.get.map(_ ==> List(1, 3, 5, 7, 9))
//
//        // Complex filtering with multiple tacit filters
//        _ <- Ns.i.a1.int_.>(2).query.get.map(_ ==> List(3, 4, 5, 6, 7, 8, 9))
        _ <- Ns.i.a1.Ref.int_.>(2).int_.<=(8).query.i.get.map(_ ==> List(3, 4, 5, 6, 7, 8))
      } yield ()
    }

    "distinct" - types { implicit conn =>
      import molecule.coreTests.dataModels.core.dsl.Types._
      for {
        _ <- Ns.i.int.insert(List(
          (1, int1),
          (2, int2),
          (2, int2),
          (2, int3),
        )).transact

        _ <- Ns.i.int.a1.query.i.get.map(_ ==> List(
          (1, int1),
          (2, int2), // 2 rows coalesced
          (2, int3),
        ))

        // Distinct values are returned in a Set
        _ <- Ns.i.a1.int(distinct).query.get.map(_ ==> List(
          (1, Set(int1)),
          (2, Set(int2, int3)),
        ))

        _ <- Ns.int(distinct).query.get.map(_.head ==> Set(
          int1, int2, int3
        ))
      } yield ()
    }

    "distinct ref" - types { implicit conn =>
      import molecule.coreTests.dataModels.core.dsl.Types._
      for {
        _ <- Ns.i.Ref.int.insert(List(
          (1, int1),
          (2, int2),
          (2, int2),
          (2, int3),
        )).transact

        _ <- Ns.i.Ref.int.a1.query.i.get.map(_ ==> List(
          (1, int1),
          (2, int2), // 2 rows coalesced
          (2, int3),
        ))

        // Distinct values are returned in a Set
        _ <- Ns.i.a1.Ref.int(distinct).query.i.get.map(_ ==> List(
          (1, Set(int1)),
          (2, Set(int2, int3)),
        ))

        _ <- Ns.Ref.int(distinct).query.get.map(_.head ==> Set(
          int1, int2, int3
        ))
      } yield ()
    }

    "min/max ref" - types { implicit conn =>
      import molecule.coreTests.dataModels.core.dsl.Types._
      for {
        _ <- Ns.i.Ref.int.insert(
          (1, int1),
          (1, int2),
          (1, int3),
          (2, int4),
          (2, int5),
          (2, int6),
        ).transact

        _ <- Ns.Ref.int(min).query.i.get.map(_ ==> List(int1))
        _ <- Ns.Ref.int(max).query.get.map(_ ==> List(int6))
        _ <- Ns.Ref.int(min).int(max).query.i.get.map(_ ==> List((int1, int6)))

        _ <- Ns.i.a1.Ref.int(min).query.i.get.map(_ ==> List(
          (1, int1),
          (2, int4)
        ))

        _ <- Ns.i.a1.Ref.int(max).query.get.map(_ ==> List(
          (1, int3),
          (2, int6)
        ))

        _ <- Ns.i.a1.Ref.int(min).int(max).query.i.get.map(_ ==> List(
          (1, int1, int3),
          (2, int4, int6)
        ))

      } yield ()
    }

    "aggr ref" - refs { implicit conn =>
      import molecule.coreTests.dataModels.core.dsl.Refs._
      for {
        _ <- A.i.B.i.insert(
          (1, 1),
          (1, 2),
          (1, 3),
          (2, 4),
          (2, 5),
          (2, 6),
        ).transact


        _ <- A.B.i(min).query.i.get.map(_ ==> List(1))
        _ <- A.B.i(max).query.get.map(_ ==> List(6))
        _ <- A.B.i(min).i(max).query.i.get.map(_ ==> List((1, 6)))

        _ <- A.i.a1.B.i(min).query.i.get.map(_ ==> List(
          (1, 1),
          (2, 4)
        ))

        _ <- A.i.a1.B.i(max).query.get.map(_ ==> List(
          (1, 3),
          (2, 6)
        ))

        _ <- A.i.a1.B.i(min).i(max).query.i.get.map(_ ==> List(
          (1, 1, 3),
          (2, 4, 6)
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
