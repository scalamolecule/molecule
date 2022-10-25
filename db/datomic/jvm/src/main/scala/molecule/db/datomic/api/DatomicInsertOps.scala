package molecule.db.datomic.api

import java.util.{List => jList}
import molecule.core.api.{Connection, InsertOps, TxReport}
import molecule.db.datomic.facade.Conn_Peer

class DatomicInsertOps(stmts: jList[jList[_]]) extends InsertOps {
  override def run(implicit conn: Connection): TxReport =
    conn.asInstanceOf[Conn_Peer].transact(stmts)
}
