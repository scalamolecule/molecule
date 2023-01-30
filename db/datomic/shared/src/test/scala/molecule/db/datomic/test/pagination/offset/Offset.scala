//package molecule.db.datomic.test.pagination.offset
//
//import molecule.core.api.result.Page
//import molecule.core.util.Executor._
//import molecule.coreTests.dataModels.core.dsl.Types._
//import molecule.db.datomic._
//import molecule.db.datomic.setup.DatomicTestSuite
//import utest._
//
//object Offset extends DatomicTestSuite {
//
//  lazy val tests = Tests {
//
//    "Flat" - types { implicit conn =>
//      for {
//        // Empty results
//        _ <- Ns.int.a1.query.limit(2).get.map(_ ==> Nil)
//
//        // When offset(n) or from(cursor) are called, a Page object is returned with data and page info
//        _ <- Ns.int.a1.query.offset(2).get.map(_ ==> Page(
//          Nil, Some(0), false, false, None)
//        )
//        _ <- Ns.int.a1.query.offset(2).get.map(_.data ==> Nil)
//        _ <- Ns.int.a1.query.limit(2).offset(2).get.map(_.data ==> Page(
//          Nil, Some(0), false, false, None)
//        )
//
//        // Populated
//        _ <- Ns.int.insert(1, 2, 3).transact
//
//        _ <- Ns.int.a1.query.limit(1).get.map(_ ==> List(1))
//        _ <- Ns.int.a1.query.limit(2).get.map(_ ==> List(1, 2))
//        _ <- Ns.int.a1.query.limit(3).get.map(_ ==> List(1, 2, 3))
//        // limit beyond total count just returns all
//        _ <- Ns.int.a1.query.limit(4).get.map(_ ==> List(1, 2, 3))
//
//        _ <- Ns.int.a1.query.limit(2).get.map(_ ==> List(1, 2))
//        _ <- Ns.int.a1.query.limit(2).offset(1).get.map(_.data ==> List(2, 3))
//        _ <- Ns.int.a1.query.limit(2).offset(2).get.map(_.data ==> List(3))
//        _ <- Ns.int.a1.query.limit(2).offset(3).get.map(_.data ==> Nil)
//      } yield ()
//    }
//
//
//    "Nested" - types { implicit conn =>
//      for {
//        // Empty results
//        _ <- Ns.int.a1.Refs.*(Ref.i).query.limit(2).get.map(_ ==> Nil)
//        _ <- Ns.int.a1.Refs.*(Ref.i).query.limit(2).offset(2).get.map(_ ==> Page(
//          Nil, Some(0), false, false, None)
//        )
//
//        _ <- Ns.int.Refs.*(Ref.i).insert(
//          (1, List(11, 12)),
//          (2, List(21, 22)),
//          (3, List(31, 32)),
//        ).transact
//
//        _ <- Ns.int.a1.Refs.*(Ref.i).query.limit(1).get.map(_ ==> List(
//          (1, List(11, 12)),
//        ))
//        _ <- Ns.int.a1.Refs.*(Ref.i).query.limit(2).get.map(_ ==> List(
//          (1, List(11, 12)),
//          (2, List(21, 22)),
//        ))
//        _ <- Ns.int.a1.Refs.*(Ref.i).query.limit(3).get.map(_ ==> List(
//          (1, List(11, 12)),
//          (2, List(21, 22)),
//          (3, List(31, 32)),
//        ))
//        _ <- Ns.int.a1.Refs.*(Ref.i).query.limit(4).get.map(_ ==> List(
//          (1, List(11, 12)),
//          (2, List(21, 22)),
//          (3, List(31, 32)),
//        ))
//
//
//        _ <- Ns.int.a1.Refs.*(Ref.i).query.limit(2).offset(1).get.map(_ ==> Page(
//          List(
//            (2, List(21, 22)),
//            (3, List(31, 32)),
//          ),
//          Some(3), true, false, None
//        ))
//        _ <- Ns.int.a1.Refs.*(Ref.i).query.limit(2).offset(2).get.map(_ ==> Page(
//          List(
//            (3, List(31, 32)),
//          ),
//          Some(3), true, false, None
//        ))
//        _ <- Ns.int.a1.Refs.*(Ref.i).query.limit(2).offset(3).get.map(_ ==> Page(
//          Nil, Some(3), true, false, None)
//        )
//      } yield ()
//    }
//
//
//    "Optional nested" - types { implicit conn =>
//      for {
//        _ <- Ns.int.Refs.*(Ref.i).insert(
//          (1, List(11, 12)),
//          (2, List(21, 22)),
//          (3, List(31, 32)),
//          (4, Nil),
//        ).transact
//
//        _ <- Ns.int.a1.Refs.*?(Ref.i).query.limit(1).get.map(_ ==> List(
//          (1, List(11, 12)),
//        ))
//        _ <- Ns.int.a1.Refs.*?(Ref.i).query.limit(2).get.map(_ ==> List(
//          (1, List(11, 12)),
//          (2, List(21, 22)),
//        ))
//        _ <- Ns.int.a1.Refs.*?(Ref.i).query.limit(3).get.map(_ ==> List(
//          (1, List(11, 12)),
//          (2, List(21, 22)),
//          (3, List(31, 32)),
//        ))
//        _ <- Ns.int.a1.Refs.*?(Ref.i).query.limit(4).get.map(_ ==> List(
//          (1, List(11, 12)),
//          (2, List(21, 22)),
//          (3, List(31, 32)),
//          (4, Nil),
//        ))
//        _ <- Ns.int.a1.Refs.*?(Ref.i).query.limit(5).get.map(_ ==> List(
//          (1, List(11, 12)),
//          (2, List(21, 22)),
//          (3, List(31, 32)),
//          (4, Nil),
//        ))
//
//
//        _ <- Ns.int.a1.Refs.*?(Ref.i).query.limit(2).offset(1).get.map(_ ==> Page(
//          List(
//            (2, List(21, 22)),
//            (3, List(31, 32)),
//          ),
//          Some(4), true, true, None
//        ))
//        _ <- Ns.int.a1.Refs.*?(Ref.i).query.limit(2).offset(2).get.map(_ ==> Page(
//          List(
//            (3, List(31, 32)),
//            (4, Nil),
//          ),
//          Some(4), true, false, None
//        ))
//        _ <- Ns.int.a1.Refs.*?(Ref.i).query.limit(2).offset(3).get.map(_ ==> Page(
//          List(
//            (4, Nil),
//          ),
//          Some(4), true, false, None
//        ))
//        _ <- Ns.int.a1.Refs.*?(Ref.i).query.limit(2).offset(4).get.map(_ ==> Page(
//          Nil, Some(4), true, false, None)
//        )
//      } yield ()
//    }
//  }
//}
