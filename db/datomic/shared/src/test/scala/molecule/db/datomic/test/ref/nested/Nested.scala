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

//            Ns.str.Refs1.*(Ref1.n1).insert.apply(List(
//              ("a", Seq(30, 31)),
//              ("a", Seq(10, 11)),
//              ("b", Seq(20, 21)),
//            )).transact
//
//
//      //      Ns.str.a1.Refs1.n1.a2.query.get.foreach(println)
//
//            Ns.str.a1.Refs1.n1.a2.query.get ==> List(
//              ("a", 10),
//              ("a", 11),
//              ("a", 30),
//              ("a", 31),
//              ("b", 20),
//              ("b", 21),
//            )
//
//      //      Ns.str.a1.Refs1.*(Ref1.n1.a2).query.get ==> List(
//      //      Ns.str.a2.Refs1.*(Ref1.n1.a1).query.get ==> List(
//      //      Ns.str.a1.Refs1.*(Ref1.n1).query.get ==> List(
//            Ns.str.a1.Refs1.*(Ref1.n1.a1).query.get ==> List(
//              ("a", Seq(30, 31)),
//              ("a", Seq(10, 11)),
//              ("b", Seq(20, 21)),
//            )


//      m(Ns.str.Refs1 * Ref1.int1.str1_?).insert(List(
//        ("A", List((1, Some("a")), (2, None))),
//        ("B", List())
//      )).transact
//
//      m(Ns.str.Refs1 *? Ref1.int1.str1_?).query.get ==> List(
//        ("A", List((1, Some("a")), (2, None))),
//        ("B", List())
//      )

//      m(Ns.str.Refs1 * Ref1.int1.str1_?).query.get ==> List(
//        ("A", List((1, Some("a")), (2, None)))
//      )


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
