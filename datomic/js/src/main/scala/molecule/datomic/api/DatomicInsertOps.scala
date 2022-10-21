package molecule.datomic.api

import java.util.{List => jList}
import molecule.core.api.{Connection, InsertOps, TxReport}


class DatomicInsertOps(edn: String) extends InsertOps {
  override def run(implicit conn: Connection): TxReport = ??? // conn.transact(rows)
}
