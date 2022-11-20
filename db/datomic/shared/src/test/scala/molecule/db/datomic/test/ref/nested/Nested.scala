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



    "insert" - refs { implicit conn =>


//      m(Ns.str.Refs1 * Ref1.int1.str1_?).insert(List(
//        ("A", List((1, Some("a")), (2, None))),
//        ("B", List())
//      )).transact
//
//      m(Ns.str.Refs1 *? Ref1.int1.str1_?).query.get ==> List(
//        ("A", List((1, Some("a")), (2, None))),
//        ("B", List())
//      )
//
////      m(Ns.str.Refs1 * Ref1.int1.str1_?).query.get ==> List(
////        ("A", List((1, Some("a")), (2, None)))
////      )


      Ns.str.Refs1.*(Ref1.int1.Refs2.*(Ref2.int2)).insert(List(
        ("a", List(
          (1, List(11, 12)),
          (2, List())
        )),
        ("b", List())
      )).transact


//      Ns.str.Refs1.*(Ref1.int1.Refs2.*(Ref2.int2)).query.get ==> List(
//        ("a", List(
//          (1, List(11, 12))
//        ))
//      )


      Ns.str.a1.Refs1.*?(Ref1.int1.Refs2.*?(Ref2.int2)).query.get ==> List(
        ("a", List(
          (1, List(11, 12)),
          (2, List())
        )),
        ("b", List())
      )

    }
  }
}
