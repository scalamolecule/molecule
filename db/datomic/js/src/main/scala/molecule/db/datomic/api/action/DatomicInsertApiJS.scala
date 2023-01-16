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

  override def transact(implicit conn0: Connection, ec: ExecutionContext): Future[TxReport] =  {
    Future { // (catch exceptions before rpc call)
      val conn                      = conn0.asInstanceOf[DatomicConn_JS]
      val (tplElements, txElements) = splitElements(elements)
//      if (txElements.nonEmpty) {
//        logger.error("A")
//        attrsHaveAppliedValue(txElements)
//      }

      //      val tplsClean = (if (countValueAttrs(tplElements) == 1) {
//        tpls.map {
//          case Tuple1(v) => v
//          case tpl       => tpl
//        }
//      } else tpls).asInstanceOf[Seq[Product]]
//      val tplsSerialized = PickleTpls(tplElements, Right(tplsClean)).pickle

      val tplsSerialized = PickleTpls(tplElements, Right(tpls), true).pickle
      conn.rpc.insert(conn.proxy, tplElements, tplsSerialized, txElements).future
    }.flatten
  }
}
