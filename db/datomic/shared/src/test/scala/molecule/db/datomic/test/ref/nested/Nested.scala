package molecule.db.datomic.test.ref.nested

import molecule.coreTests.dataModels.core.dsl.Refs._
import molecule.db.datomic._
import molecule.db.datomic.setup.DatomicTestSuite
import utest._


object Nested extends DatomicTestSuite {


  lazy val tests = Tests {


//    "Nested data not allowed in save" - refs { implicit conn =>
//      Ns.int(0).Refs1.*(Ref1.int1(1)).save
//        .map(_ ==> "Unexpected success").recover { case VerifyModelException(err) =>
//        err ==> "[noNested]  Nested data structures not allowed in save molecules"
//      }
//    }


//    "save" - refs { implicit conn =>
//      Ns.n(0).Ref1.n1(1)._Ns.Refs1.n1(2).save.transact
//
//      Ns.n.Ref1.n1._Ns.Refs1.n1.query.get ==> List((0, 1, 2))
//    }
//
//
//    "insert" - refs { implicit conn =>
//      Ns.str.Ref1.str1._Ns.Refs1.int1.insert(List(
//        ("a", "a1", 1),
//        ("b", "b1", 2))).transact
//
//      Ns.str.Ref1.str1._Ns.Refs1.int1.query.get ==> List(
//        ("a", "a1", 1),
//        ("b", "b1", 2)
//      )
//
//      Ns.str.Refs1.int1._Ns.Ref1.str1.query.get ==> List(
//        ("a", 1, "a1"),
//        ("b", 2, "b1")
//      )
//    }
  }
}
