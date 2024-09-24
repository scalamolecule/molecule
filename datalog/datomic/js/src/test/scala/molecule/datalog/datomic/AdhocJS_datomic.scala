package molecule.datalog.datomic

import boopickle.Default._
import molecule.base.error.{InsertError, InsertErrors, ModelError}
import molecule.core.util.Executor._
import molecule.coreTests.dataModels.dsl.Types._
import molecule.coreTests.dataModels.dsl.Validation.Constants
import molecule.datalog.datomic.async._
import molecule.datalog.datomic.setup.TestSuite_datomic
import utest._
import scala.annotation.{nowarn, tailrec}
import scala.util.Random


object AdhocJS_datomic extends TestSuite_datomic {


  @tailrec
  final def getData(acc: List[(Int, Int, List[Int])]): List[(Int, Int, List[Int])] = {
    if (acc.length != 5) {
      val pair = (Random.nextInt(3) + 1, Random.nextInt(6) + 1, List(1))
      // No duplicate rows
      if (!acc.contains(pair)) getData(acc :+ pair) else getData(acc)
    } else {
      acc
    }
  }

  // testOnly molecule.datalog.datomic.AdhocJS_datomic
  @nowarn override lazy val tests = Tests {

//    "types" - types { implicit conn =>
//      val pairs               = getData(Nil)
//      val List(a, b, c, d, e) = pairs.sortBy(p => (p._1, p._2))
//      val query               = (cursor: String, limit: Int) =>
//        Ns.i.a1.int.a2.Refs.*(Ref.i).query.from(cursor).limit(limit)
//      for {
//        _ <- Ns.i.int.Refs.*(Ref.i).insert(pairs).transact
//        c1 <- query("", -2).get.map { case (List(`d`, `e`), cursor, true) => cursor }
//        c2 <- query(c1, -2).get.map { case (List(`b`, `c`), cursor, true) => cursor }
//        c3 <- query(c2, -2).get.map { case (List(`a`), cursor, false) => cursor }
//        c2 <- query(c3, 2).get.map { case (List(`b`, `c`), cursor, true) => cursor }
//        _ <- query(c2, 2).get.map { case (List(`d`, `e`), _, false) => () }
//
//
//
////        List(a, b) <- Ns.int.insert(1, 2).transact.map(_.ids)
////        _ <- Ns.int(3).save.transact
////        _ <- Ns.int.a1.query.get.map(_ ==> List(1, 2, 3))
////        _ <- Ns(a).int(10).update.transact
////        _ <- Ns(b).delete.transact
////        _ <- Ns.int.a1.query.get.map(_ ==> List(3, 10))
//
//
//
//
//      } yield ()
//    }


    //    "refs" - refs { implicit conn =>
    //      import molecule.coreTests.dataModels.dsl.Refs._
    //      for {
    //
    ////        _ <- A.i.B.?(B.i).insert(List(
    ////          (1, None),
    ////          (2, Some(20)),
    ////        )).transact
    ////
    ////        _ <- A.i.a1.B.?(B.i).query.get.map(_ ==> List(
    ////          (1, None),
    ////          (2, Some(20)),
    ////        ))
    ////
    ////        _ <- A.i.B.i.query.get.map(_ ==> List(
    ////          (2, 20),
    ////        ))
    //
    //        _ <- A.i
    //          .B.?(B.i.s)
    //          .C.?(C.s.i).insert(List(
    //            (1, None, None),
    //            (2, Some((20, "b")), None),
    //            (3, None, Some(("c", 300))),
    //            (4, Some((40, "b")), Some(("c", 400))),
    //          )).transact
    //
    //        _ <- A.i.a1
    //          .B.?(B.i.s)
    //          .C.?(C.s.i).query.get.map(_ ==> List(
    //            (1, None, None),
    //            (2, Some((20, "b")), None),
    //            (3, None, Some(("c", 300))),
    //            (4, Some((40, "b")), Some(("c", 400))),
    //          ))
    //
    //      } yield ()
    //        }


    "validation" - validation { implicit conn =>
//      import molecule.coreTests.dataModels.dsl.Refs._
      import molecule.coreTests.dataModels.dsl.Validation._

      for {
//        List(r1, r2) <- RefB.i.insert(2, 3).transact.map(_.ids)
//
//        id <- MandatoryRefsB.i(1).refsB(Set(r1, r2)).save.transact.map(_.ids)
//
//        // Mandatory refs can be removed as long as some ref ids remain
//        _ <- MandatoryRefsB(id).refsB.remove(r2).update.transact
//
//        // Last mandatory ref can't be removed. This can prevent creating orphan relationships.
//        _ <- MandatoryRefsB(id).refsB.remove(r1).update.transact
//          .map(_ ==> "Unexpected success").recover {
//            case ModelError(error) =>
//              error ==>
//                """Can't delete mandatory attributes (or remove last values of card-many attributes):
//                  |  MandatoryRefsB.refsB
//                  |""".stripMargin
//          }






        id <- MandatoryAttr.name("Bob").age(42).hobbies(Set("golf", "stamps")).save.transact.map(_.id)

        // We can remove a value from a Set as long as it's not the last value
        _ <- MandatoryAttr(id).hobbies.remove("stamps").update.transact

        // Can't remove the last value of a mandatory attribute Set of values
        _ <- MandatoryAttr(id).hobbies.remove("golf").update.transact
          .map(_ ==> "Unexpected success").recover {
            case ModelError(error) =>
              error ==>
                """Can't delete mandatory attributes (or remove last values of card-many attributes):
                  |  MandatoryAttr.hobbies
                  |""".stripMargin
          }



      } yield ()
    }
  }
}

/*
testOnly molecule.datalog.datomic.compliance.api.Test_IOApi

testOnly molecule.datalog.datomic.compliance.filter.map.Test_FilterMap_Long_

testOnly molecule.datalog.datomic.compliance.relation.Test_FlatRefOptAdjacent
testOnly molecule.datalog.datomic.compliance.relation.Test_FlatRefOptNested
testOnly molecule.datalog.datomic.compliance.relation.Test_FlatRefOpt
testOnly molecule.datalog.datomic.compliance.relation.*

testOnly molecule.datalog.datomic.compliance.subscription.Test_Subscription

testOnly molecule.datalog.datomic.compliance.validation.insert.Test_FormatConstants
testOnly molecule.datalog.datomic.compliance.validation.insert.Test_FormatVariables
testOnly molecule.datalog.datomic.compliance.validation.insert.*

testOnly molecule.datalog.datomic.compliance.validation.save.Test_FormatVariables
testOnly molecule.datalog.datomic.compliance.validation.Test_RequiredAttrs


testOnly molecule.datalog.datomic.compliance.pagination.cursor.noUnique.Nested
testOnly molecule.datalog.datomic.compliance.validation.Test_MandatoryRefs
testOnly molecule.datalog.datomic.compliance.validation.Test_MandatoryAttrs
 */