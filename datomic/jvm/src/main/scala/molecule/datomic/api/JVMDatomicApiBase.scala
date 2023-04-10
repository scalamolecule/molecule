package molecule.datomic.api

import datomic.Peer
import molecule.base.error._
import molecule.boilerplate.ast.Model._
import molecule.core.action.Insert
import molecule.core.api.{ApiAsync, Connection, TxReport}
import molecule.core.marshalling.ConnProxy
import molecule.core.transaction.{DeleteExtraction, InsertExtraction, SaveExtraction, UpdateExtraction}
import molecule.core.util.FutureUtils
import molecule.core.validation.ModelValidation
import molecule.core.validation.insert.InsertValidation
import molecule.datomic.action._
import molecule.datomic.facade.DatomicConn_JVM
import molecule.datomic.marshalling.DatomicRpcJVM.Data
import molecule.datomic.query.{DatomicQueryResolveCursor, DatomicQueryResolveOffset}
import molecule.datomic.subscription.SubscriptionStarter
import molecule.datomic.transaction.{Delete_stmts, Insert_stmts, Save_stmts, Update_stmts}
import scala.collection.mutable.ListBuffer
import scala.concurrent.{ExecutionContext, Future}


trait JVMDatomicApiBase {

  def validateUpdate(conn0: Connection, elements: List[Element]): Map[String, Seq[String]] = {
    val conn                              = conn0.asInstanceOf[DatomicConn_JVM]
    val proxy                             = conn.proxy
    val db                                = conn.peerConn.db()
    val getCurSetValues: Attr => Set[Any] = (attr: Attr) => {
      val a = s":${attr.ns}/${attr.attr}"
      try {
        val curValues = Peer.q(s"[:find ?vs :where [_ $a ?vs]]", db)
        if (curValues.isEmpty) {
          throw ExecutionError(s"While checking to avoid removing the last values of mandatory " +
            s"attribute ${attr.ns}.${attr.attr} the current Set of values couldn't be found.")
        }
        val vs = ListBuffer.empty[Any]
        curValues.forEach(row => vs.addOne(row.get(0)))
        vs.toSet
      } catch {
        case e: MoleculeError => throw e
        case t: Throwable     => throw ExecutionError(
          s"Unexpected error trying to find current values of mandatory attribute ${attr.name}")
      }
    }
    ModelValidation(
      proxy.nsMap,
      proxy.attrMap,
      "update",
      Some(getCurSetValues)
    ).check(elements)
  }
}
