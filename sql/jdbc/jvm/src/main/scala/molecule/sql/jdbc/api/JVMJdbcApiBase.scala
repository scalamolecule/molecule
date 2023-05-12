package molecule.sql.jdbc.api

import datomic.Peer
import molecule.base.error._
import molecule.boilerplate.ast.Model._
import molecule.core.api.Connection
import molecule.core.validation.ModelValidation
import molecule.sql.jdbc.facade.JdbcConn_JVM
import scala.collection.mutable.ListBuffer


trait JVMJdbcApiBase {

  def validateUpdate(conn0: Connection, elements: List[Element]): Map[String, Seq[String]] = {
    val conn                              = conn0.asInstanceOf[JdbcConn_JVM]
    val proxy                             = conn.proxy
//    val db                                = conn.sqlConn.db()
//    val getCurSetValues: Attr => Set[Any] = (attr: Attr) => {
//      val a = s":${attr.ns}/${attr.attr}"
//      try {
//        val curValues = Peer.q(s"[:find ?vs :where [_ $a ?vs]]", db)
//        if (curValues.isEmpty) {
//          throw ExecutionError(s"While checking to avoid removing the last values of mandatory " +
//            s"attribute ${attr.ns}.${attr.attr} the current Set of values couldn't be found.")
//        }
//        val vs = ListBuffer.empty[Any]
//        curValues.forEach(row => vs.addOne(row.get(0)))
//        vs.toSet
//      } catch {
//        case e: MoleculeError => throw e
//        case t: Throwable     => throw ExecutionError(
//          s"Unexpected error trying to find current values of mandatory attribute ${attr.name}")
//      }
//    }
    ModelValidation(
      proxy.schema.nsMap,
      proxy.schema.attrMap,
      "update",
//      Some(getCurSetValues)
      None
    ).validate(elements)
  }
}
