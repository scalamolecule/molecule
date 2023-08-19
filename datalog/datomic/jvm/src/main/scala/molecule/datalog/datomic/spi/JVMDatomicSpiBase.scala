package molecule.datalog.datomic.spi

import datomic.Peer
import molecule.base.error._
import molecule.boilerplate.ast.Model._
import molecule.core.action.Update
import molecule.core.spi.Conn
import molecule.core.util.ModelUtils
import molecule.core.validation.ModelValidation
import molecule.datalog.datomic.facade.DatomicConn_JVM
import scala.collection.mutable.ListBuffer


trait JVMDatomicSpiBase extends ModelUtils {

  def validateUpdate(conn0: Conn, update: Update): Map[String, Seq[String]] = {
    if (update.isUpsert && isRefUpdate(update.elements))
      throw ModelError("Can't upsert referenced attributes. Please update instead.")

    val conn  = conn0.asInstanceOf[DatomicConn_JVM]
    val proxy = conn.proxy
    val db    = conn.peerConn.db()

    val getCurSetValues: Attr => Set[Any] = (attr: Attr) => {
      val a = s":${attr.ns}/${attr.attr}"
      try {
        val curValues = Peer.q(s"[:find ?vs :where [_ $a ?vs]]", db)
        if (curValues.isEmpty) {
          throw ExecutionError(s"While checking to avoid removing the last values of mandatory " +
            s"attribute ${attr.ns}.${attr.attr} the current Set of values couldn't be found.")
        }
        val vs = ListBuffer.empty[Any]
        curValues.forEach(row => vs += row.get(0))
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
    ).validate(update.elements)
  }
}
