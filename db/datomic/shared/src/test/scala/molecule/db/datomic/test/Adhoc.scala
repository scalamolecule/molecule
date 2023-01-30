package molecule.db.datomic.test


import molecule.core.util.Executor._
import molecule.db.datomic._
import molecule.db.datomic.setup.DatomicTestSuite
import utest._
import molecule.base.util.exceptions.MoleculeError

object Adhoc extends DatomicTestSuite {
  //  // See Zio-http route
  //  abstract class RouteDecode[A](f: String => A) {
  //    def unapply(a: String): Option[A] =
  //      try {
  //        Option(f(a))
  //      } catch {
  //        case _: Throwable => None
  //      }
  //  }
  //
  //  object boolean extends RouteDecode(_.toBoolean)
  //  object byte extends RouteDecode(_.toByte)
  //  object short extends RouteDecode(_.toShort)
  //  object int extends RouteDecode(_.toInt)
  //  object long extends RouteDecode(_.toLong)
  //  object float extends RouteDecode(_.toFloat)
  //  object double extends RouteDecode(_.toDouble)
  ////  object uuid extends RouteDecode(str => UUID.fromString(str))
  ////  object date extends RouteDecode(str => LocalDate.parse(str))
  ////  object time extends RouteDecode(str => LocalDateTime.parse(str))
  ////  object Person {
  ////    lazy val name = RouteDecode(str => LocalDateTime.parse(str))
  ////  }
  //
  //
  ////Person.name(hej)
  //
  //  int(hej)



  lazy val tests = Tests {

    "types" - types { implicit conn =>
      import molecule.coreTests.dataModels.core.dsl.Types._
      for {
//        List(a, b) <- Ns.int.insert(1, 2).transact.map(_.eids)
//        _ <- Ns.int(3).save.transact
//        _ <- Ns.int.query.get.map(_ ==> List(1, 2, 3))
//        _ <- Ns.apply(a).int(10).update.transact
//        _ <- Ns(b).delete.transact
//        _ <- Ns.int.query.get.map(_ ==> List(3, 10))

        _ <- Ns.string.a1.int.a1.query.get
          .map(_ ==> "Unexpected success 1").recover { case MoleculeError(err, _) =>
          err ==> "Sort index 1 should be present and additional indexes continuously " +
            "increase (in any order). Found sort index(es): 1, 1"
        }

//        _ <- Ns.int.insert(1, 2, 3).transact
//        _ <- Ns.int.query.get.map(_ ==> List(1, 2, 3))
//
//        _ <- Ns.int.query.limit(2).get.map(_ ==> List(1, 2))
//        _ <- Ns.int.query.limit(-2).get.map(_ ==> List(2, 3))

//        _ <- Ns.int.query.offset(1).get.map(_ ==> PageInfo(List(2, 3), 3, true, false))
//        _ <- Ns.int.query.offset(1).get.map(_.data ==> List(2, 3))
//        _ <- Ns.int.query.offset(1).get.map(_.totalCount ==> 3)
//
//        _ <- Ns.int.query.offset(1).limit(1).get.map(_.data ==> List(2))
//        _ <- Ns.int.query.limit(1).offset(1).get.map(_.data ==> List(2))

//        _ <- Ns.int.query.from("cursor").get.map(_ ==> Page(List(1, 2), Some(2)))
//        _ <- Ns.int.query.from("cursor").offset(2).get.map(_ ==> QueryResultList(List(1, 2), Some(2)))
//        _ <- Ns.int.query.offset(2).from("cursor").get.map(_ ==> QueryResultList(List(1, 2), Some(2)))


      } yield ()
    }


    //    "refs" - refs { implicit conn =>
    //      import molecule.coreTests.dataModels.core.dsl.Refs._
    //      for {
    //        _ <- Ns.i.Tx(R2.i).insert(1, 2).transact
    //          .map(_ ==> "Unexpected success").recover { case MoleculeError(err, _) =>
    //          err ==>
    //            """Missing applied value for attribute:
    //              |AttrOneManInt("R2", "i", V, Seq(), None, None, None)""".stripMargin
    //        }
    //      } yield ()
    //    }
  }

}
