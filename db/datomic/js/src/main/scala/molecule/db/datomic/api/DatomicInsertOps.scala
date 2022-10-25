package molecule.db.datomic.api

import java.util.{List => jList}
import molecule.core.api.{Connection, InsertOps, TxReport}


class DatomicInsertOps(edn: String) extends InsertOps {
  override def run(implicit conn: Connection): TxReport = {
//    conn.asInstanceOf[Connection].transact(stmts)
    new TxReport{
      override def eids: List[Long] = List(123)
    }
  }
  //    ??? // conn.transact(rows)
}
