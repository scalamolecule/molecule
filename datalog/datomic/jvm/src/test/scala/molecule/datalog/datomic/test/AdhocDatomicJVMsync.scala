package molecule.datalog.datomic.test

import molecule.datalog.datomic.setup.DatomicTestSuite
import molecule.datalog.datomic.sync._
import utest._
import scala.language.implicitConversions


object AdhocDatomicJVMsync extends DatomicTestSuite {


  override lazy val tests = Tests {

    "types" - types { implicit conn =>
      import molecule.coreTests.dataModels.core.dsl.Types._


//      Ns.i.insert(1, 2, 3).transact
//      Ns.i(min(2)).query.get ==> List(Set(1, 2))

//      Ns.i.int.insert(
//        (1, 1),
//        (1, 2),
//        (1, 3),
//        (2, 4),
//        (2, 5),
//        (2, 6),
////        (3, 7),
//      ).transact
//
//      Ns.i(max(2)).int(min(2)).query.inspect
//
////      Ns.i(max(2)).i(min(2)).query.get ==> List()
//
////      Ns.i(max(2)).int(min(2)).query.get ==> List(
//      Ns.i(max(2)).int(max(2)).query.get ==> List(
//        (1, 1),
//        (2, 3),
//      )

      Ns.s.i.int.insert(
        ("a", 1, 3),
        ("a", 1, 1),
        ("b", 1, 2),
        ("b", 2, 4),
        ("c", 2, 5),
        ("c", 2, 6),
        //        (3, 7),
      ).transact


//      Ns.i(min(2)).query.get ==> List(Set(1, 2))
//      Ns.s.i.int(min(2)).query.get ==> List(
//        ("a",1,Set(1, 3)),
//        ("b",1,Set(2)),
//        ("b",2,Set(4)),
//        ("c",2,Set(5, 6))
//      )



//      Ns.s.double.insert(
//        ("a", 1.5),
//        ("a", 2.0),
//        ("b", 7.5),
//      ).transact
      //      Ns.s.double(sum).query.get.foreach(println)

    }


    //    "refs" - refs { implicit conn =>
    //      import molecule.coreTests.dataModels.core.dsl.Refs._
    //      A.s.Bb.*(B.i.a1).query.inspect
    //    }


  }
}
