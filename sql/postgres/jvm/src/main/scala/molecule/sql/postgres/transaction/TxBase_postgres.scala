package molecule.sql.postgres.transaction

import molecule.sql.core.transaction.SqlBase_JVM
import molecule.sql.core.transaction.op.DbOps

trait TxBase_postgres { self: SqlBase_JVM =>


  override implicit lazy val dbOps: DbOps = new DbOps_postgres

}
