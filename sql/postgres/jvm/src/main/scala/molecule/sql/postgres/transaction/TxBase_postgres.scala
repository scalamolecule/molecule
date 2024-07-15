package molecule.sql.postgres.transaction

import molecule.sql.core.transaction.SqlBase_JVM
import molecule.sql.core.transaction.strategy.SqlOps

trait TxBase_postgres { self: SqlBase_JVM =>


  override implicit lazy val dbOps: SqlOps = new SqlOps_postgres

}
