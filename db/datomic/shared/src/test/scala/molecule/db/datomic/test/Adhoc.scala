package molecule.db.datomic.test


import molecule.core.util.Executor._
import molecule.db.datomic._
import molecule.db.datomic.setup.DatomicTestSuite
import utest._


object Adhoc extends DatomicTestSuite {


//  val v = Vector(1,2)
//
//  v match {
//    case head :: tail => head
//  }

//  def li(l: List[Int]) = 7
//  def se(l: Seq[Int]) = 7
//
//  li(Seq(2))
//  li(Vector(2))
//  se(Seq(2))
//  se(Vector(2))

  lazy val tests = Tests {

    "types" - types { implicit conn =>
      import molecule.coreTests.dataModels.core.dsl.Types._
      for {
        List(a,_,_) <- Ns.int.insert(1, 2, 3).transact.map(_.eids)
        _ <- Ns.int(1, 2).query.get.map(_ ==> List(1, 2))
        _ <- Ns.int.query.get.map(_ ==> List(1, 2, 3))
        _ <- Ns(a).int(10).update.transact
      } yield ()
    }


    //    "refs" - refs { implicit conn =>
    //      import molecule.coreTests.dataModels.core.dsl.Refs._
    //
    //
    //      //      Ns.i.v.Self.i(v).s
    //
    //      //      Ns.i.>(1).as(v1).R1.i.<(v1)
    //
    //    }
  }

}
