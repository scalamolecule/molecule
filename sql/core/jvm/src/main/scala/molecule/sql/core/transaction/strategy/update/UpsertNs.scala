//package molecule.sql.core.transaction.strategy.update
//
//import java.sql.Connection
//import molecule.sql.core.transaction.strategy.SqlOps
//import scala.collection.mutable.ListBuffer
//
//case class UpsertNs(
//  sqlConn: Connection,
//  ns: String,
//)(implicit sqlOps: SqlOps) extends UpdateAction(sqlConn, sqlOps, ns) {
//
//  rowSetters += ListBuffer.empty[PS => Unit]
//
//  // Initial namespace
//  override def initialAction: UpdateAction = this
//
//  override def execute: List[Long] = {
//    //    update
//
//    ???
//  }
//
//  override def toString: String = recurseRender(0, "UpsertNs")
//}
