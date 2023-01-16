package molecule.db.datomic.api.action

import boopickle.Default._
import molecule.boilerplate.ast.Model._
import molecule.boilerplate.util.MoleculeLogging
import molecule.core.api.action.InsertApi
import molecule.core.api.{Connection, TxReport}
import molecule.core.marshalling.serialize.PickleTpls
import molecule.db.datomic.facade.DatomicConn_JS
import scala.concurrent.{ExecutionContext, Future}

class DatomicInsertApiJS(elements: List[Element], tpls: Seq[Product])
  extends InsertApi with MoleculeLogging {

  override def transact(implicit conn0: Connection, ec: ExecutionContext): Future[TxReport] = tryFuture {
    val conn                      = conn0.asInstanceOf[DatomicConn_JS]
    val (tplElements, txElements) = splitElements(elements)
    val tplsSerialized            = PickleTpls(tplElements, Right(tpls), true).pickle
    conn.rpc.insert(conn.proxy, tplElements, tplsSerialized, txElements).future
  }
}
