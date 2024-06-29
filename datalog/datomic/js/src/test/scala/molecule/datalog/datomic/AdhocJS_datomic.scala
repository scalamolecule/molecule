package molecule.datalog.datomic

import boopickle.Default._
import molecule.core.util.Executor._
import molecule.coreTests.dataModels.core.dsl.Types._
import molecule.datalog.datomic.async._
import molecule.datalog.datomic.setup.TestSuite_datomic
import utest._


object AdhocJS_datomic extends TestSuite_datomic {

  override lazy val tests = Tests {

    "types" - types { implicit conn =>

      println("XXX " + null.asInstanceOf[Int])

      for {

        id <- Ns.i(42).save.transact.map(_.id)
//        // Map attribute not yet asserted
//        _ <- Ns.intMap.query.get.map(_ ==> Nil)
//
//        // When attribute is not already asserted, an update has no effect
//        _ <- Ns(id).intMap.add("a" -> int0).update.transact
//        _ <- Ns.intMap.query.get.map(_ ==> Nil)

        // To add values to the attribute if not already asserted, use `upsert`
        _ <- Ns(id).intMap.add("a" -> int1).upsert.i.transact
        _ <- Ns.intMap.query.get.map(_.head ==> Map("a" -> int0))
//
//        // Adding existing pair to Map changes nothing
//        _ <- Ns(id).intMap.add("a" -> int0).update.transact
//        _ <- Ns.intMap.query.get.map(_.head ==> Map("a" -> int0))
//
//        // Adding pair with existing key replaces the value of the pair
//        _ <- Ns(id).intMap.add("a" -> int1).update.transact
//        _ <- Ns.intMap.query.get.map(_.head ==> Map(pint1)) // "a" -> int1
//
//        // Add new pair
//        _ <- Ns(id).intMap.add(pint2).update.transact
//        _ <- Ns.intMap.query.get.map(_.head ==> Map(pint1, pint2))
//
//        // Add multiple pairs with varargs
//        _ <- Ns(id).intMap.add(pint3, pint4).update.transact
//        _ <- Ns.intMap.query.get.map(_.head ==> Map(pint1, pint2, pint3, pint4))
//
//        // Add multiple pairs with Iterable
//        _ <- Ns(id).intMap.add(List(pint5, pint6)).update.transact
//        _ <- Ns.intMap.query.get.map(_.head ==> Map(pint1, pint2, pint3, pint4, pint5, pint6))
//
//        // Adding empty Iterable of pairs has no effect
//        _ <- Ns(id).intMap.add(Vector.empty[(String, Int)]).update.transact
//        _ <- Ns.intMap.query.get.map(_.head ==> Map(pint1, pint2, pint3, pint4, pint5, pint6))
      } yield ()
    }


//    "refs" - refs { implicit conn =>
//      import molecule.coreTests.dataModels.core.dsl.Refs._
//      for {
//
//        a <- A.iSeq(Seq(1, 2)).save.transact.map(_.id)
//
//      } yield ()
//    }
  }
}
