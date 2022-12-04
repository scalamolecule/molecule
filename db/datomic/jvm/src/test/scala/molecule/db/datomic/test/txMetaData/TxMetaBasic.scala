//package molecule.db.datomic.test.txMetaData
//
//import molecule.coreTests.dataModels.core.dsl.Refs._
//import molecule.db.datomic._
//import molecule.db.datomic.setup.DatomicTestSuite
//import molecule.db.datomic.test.ref.nested.NestedComposite.refs
//import utest._
//
//
//object TxMetaBasic extends DatomicTestSuite {
//
//  // See also:
//  // molecule.db.datomic.test.crud.save.SaveTxMetaData
//  // molecule.db.datomic.test.crud.insert.InsertTxMetaData
//
//
//  lazy val tests = Tests {
//
//    "basic" - refs { implicit conn =>
//      Ns.i.Tx(R2.i).insert(1, 2).transact
//      Ns.i.Tx(R2.i).query.get ==> List((1, 2))
//    }
//
////    "multiple attrs" - refs { implicit conn =>
////      Ns.i.Tx(R2.i.s.ii).insert(1, 2, ).transact
////      Ns.i.Tx(R2.i.s.ii).query.get ==> List((1, 2))
////    }
////
////
////    "1 level" - refs { implicit conn =>
////      Ns.i.Rs1.*(R1.i).Tx(R4.i).insert(1, List(2), 3).transact
////      Ns.i.Rs1.*(R1.i).Tx(R4.i).query.get ==> List((1, List(2), 3))
////    }
//
//
//    "1 level" - refs { implicit conn =>
//      Ns.i.Rs1.*(R1.i.s + R3.s.i).insert(0, List(((1, "a"), ("b", 2)))).transact
//
//      Ns.i.Rs1.*(R1.i.s + R3.s.i).query.get ==> List((0, List(((1, "a"), ("b", 2)))))
//      Ns.i.Rs1.*(R1.i.s + R3.s).query.get ==> List((0, List(((1, "a"), "b"))))
//      Ns.i.Rs1.*(R1.i + R3.s.i).query.get ==> List((0, List((1, ("b", 2)))))
//      Ns.i.Rs1.*(R1.i + R3.i).query.get ==> List((0, List((1, 2))))
//    }
//
//
//    "2 levels" - refs { implicit conn =>
//      Ns.i.Rs1.*(R1.i.Rs2.*(R2.i.s + R4.s.i)).insert(
//        0, List(
//          (1, List(
//            ((2, "a"), ("b", 3)))))
//      ).transact
//
//      Ns.i.Rs1.*(R1.i.Rs2.*(R2.i.s + R4.s.i)).query.get ==> List(
//        (0, List(
//          (1, List(
//            ((2, "a"), ("b", 3))))))
//      )
//      Ns.i.Rs1.*(R1.i.Rs2.*(R2.i.s + R4.s)).query.get ==> List(
//        (0, List(
//          (1, List(
//            ((2, "a"), "b")))))
//      )
//      Ns.i.Rs1.*(R1.i.Rs2.*(R2.i + R4.s.i)).query.get ==> List(
//        (0, List(
//          (1, List(
//            (2, ("b", 3))))))
//      )
//      Ns.i.Rs1.*(R1.i.Rs2.*(R2.i + R4.i)).query.get ==> List(
//        (0, List(
//          (1, List(
//            (2, 3)))))
//      )
//    }
//
//  }
//}
