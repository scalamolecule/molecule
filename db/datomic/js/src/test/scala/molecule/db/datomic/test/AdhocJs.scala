package molecule.db.datomic.test

import boopickle.Default._
import molecule.base.util.exceptions.MoleculeException
import molecule.core.marshalling.{ConnProxy, DatomicPeerProxy}
import molecule.core.transaction.Save
import molecule.coreTests.dataModels.core.dsl.Types._
import molecule.coreTests.dataModels.core.schema.TypesSchema
import molecule.db.datomic.facade.DatomicConn_JS
import molecule.db.datomic.setup.DatomicTestSuite
import molecule.db.datomic.transaction.Save_edn
import utest._
//import molecule.db.datomic.RpcTester._
import molecule.boilerplate.ast.Model._
import molecule.db.datomic._
import molecule.core.util.Executor._
import scala.collection.mutable.{ArrayBuffer, ListBuffer}


object AdhocJs extends DatomicTestSuite {

//  val aa: Array[Int] = Array.emptyIntArray
//  val aa: Array[Int] = new Array[Int](0)
//  val aa: Array[Int] = Array.empty[Int]

  val buf = ArrayBuffer.empty[Int]
  buf.addOne(1)
  val es = Seq(AttrOneManInt("Ns", "int", V, Seq(5)))
  val edn =
    """[
      | [:db/add #db/id[db.part/user -1] :Ns/i 7]
      |]""".stripMargin


  lazy val tests = Tests {

    "types" - types { implicit conn =>
      val proxy = conn.proxy
      for {


//        _ <- conn.rpc.test(buf).map(_ ==> 42)
//        _ <- conn.rpc.test(Array(1)).map(_ ==> 42)
//        _ <- conn.rpc.test2.map(_ ==> 8)
//        _ <- conn.rpc.test3.map(_ ==> 8)
//        _ <- conn.rpc.transactEdn(proxy, edn).map(_ ==> 8)
        _ <- Ns.i(7).save.transact
        _ <- Ns.i(8).save.transact
        _ <- Ns.i.query.get.map(_ ==> List(7, 8))
//        _ <- conn.rpc.query2(conn.proxy, es)
      } yield ()
    }


    //    "refs" - refs { implicit conn =>
    //      //      Ns.i.v.Self.i(v).s
    //      //      Ns.i.>(1).as(v1).R1.i.<(v1)
    //
    //    }
  }
}
