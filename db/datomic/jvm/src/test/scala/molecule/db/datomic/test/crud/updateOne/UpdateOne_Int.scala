package molecule.db.datomic.test.crud.updateOne


import molecule.base.util.exceptions.MoleculeException
import molecule.coreTests.dataModels.core.dsl.Types._
import molecule.db.datomic._
import molecule.db.datomic.setup.DatomicTestSuite
import utest._

object UpdateOne_Int extends DatomicTestSuite {


  lazy val tests = Tests {

    "With single entity id" - types { implicit conn =>

      val eid = Ns.int.insert(1).transact.eids.head

      // Apply new value to attribute for entity id `eid`
      Ns(eid).int(2).update.transact
      Ns.int.query.get ==> List(2)

      // Same as
      Ns.e_(eid).int(3).update.transact
      Ns.int.query.get ==> List(3)

    }






//      intercept[MoleculeException](
//        Ns(List(b, c)).int(4).update.transact
//      ).message ==> "Please provide explicit `update.multiple` to update multiple entities."

//    "With multiple entity ids" - types { implicit conn =>
//
//      val List(a, b, c) = Ns.int.insert(1, 2, 3).transact.eids
//
//
//      Ns(List(b, c)).int(4).update.multiple.transact
//      Ns.e.a1.int.query.get ==> List(
//        (a, 1),
//        (b, 4),
//        (c, 4),
//      )
//
//      Ns.int.query.get ==> List(2)
//
//
//      Ns.int_.>(2).string("new value").update.multiple.transact ==> List(2)
//
//      //      // Apply new value
//      //      Ns(eid).int(2).update
//      //      Ns.int.query.get ==> List(2)
//      //
//      //      // Apply empty value (retract `int` datom)
//      //      Ns(eid).int().update
//      //      Ns.int.query.get ==> Nil
//
//
//      // Applying multiple values to card-one attribute not allowed
//
//      //      Ns(eid).int.update.map(_ ==> "Unexpected success").recover { case VerifyModelException(err) =>
//      //        err ==> "[onlyAtomsWithValue]  Update molecule can only have attributes with some value(s) applied/added/replaced etc."
//      //      }
//      //
//      //      Ns(eid).int(2, 3).update.map(_ ==> "Unexpected success").recover { case VerifyModelException(err) =>
//      //        err ==> "[noConflictingCardOneValues]  Can't update multiple values for cardinality-one attribute:" +
//      //          s"\n  Ns ... int(2, 3)"
//      //      }
//
//    }
//
//
//    "With unique attribute value" - types { implicit conn =>
//
//    }
//
//    "Matching" - types { implicit conn =>
//      Ns.int_.>(2).string("new value").update.multiple.transact ==> List(2)
//    }
  }
}
