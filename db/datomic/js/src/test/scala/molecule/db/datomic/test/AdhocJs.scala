package molecule.db.datomic.test

import java.nio.ByteBuffer
import molecule.db.datomic._
import molecule.db.datomic.setup.DatomicTestSuite
import utest._
import molecule.core.util.Executor._
import molecule.core.marshalling.{MoleculeRpc, MoleculeRpcRequest}
import sloth.Client
import scala.concurrent.Future
import boopickle.Default._
//import molecule.db.datomic.RpcTester._

object AdhocJs extends DatomicTestSuite {


  lazy val tests = Tests {

    "types" - types { implicit conn =>
      import molecule.coreTests.dataModels.core.dsl.Types._

      //      for {
      ////        _ <- rpc2.test.map(_ ==> 9)
      ////        _ <- rpc2.test2(41).map(_ ==> 42)
      //        _ <- conn.rpc.test2(41).map(_ ==> 43)
      //      } yield ()

      //      Ns.i.query.get

      //      conn.rpc.test2(41) ==> 42
//      conn.rpc.test2(41) ==* 42


//      Ns.int.apply(7).save

      //      val db  = conn.asInstanceOf[Conn_Js].db
      //      val res = Peer.q(
      //        """[:find  ?b
      //          |        (pull ?a [
      //          |          {(:Ns/refs :limit nil :default "__none__") [
      //          |            (:Ref/i :limit nil :default "__none__")
      //          |            ]}])
      //          | :where [?a :Ns/i ?b]]""".stripMargin,
      //        db
      //      )
      //      println("---")
      //      println(res)


    }

    //    "refs" - refs { implicit conn =>
    //
    //
    //
    //      //      Ns.i.v.Self.i(v).s
    //
    //      //      Ns.i.>(1).as(v1).R1.i.<(v1)
    //
    //    }
  }

  /*
  core/src/main/scala/org/scalajs/macrotaskexecutor/MacrotaskExecutor.scala
   */
}
