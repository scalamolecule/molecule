package molecule.sql.sqlite.transaction

import molecule.sql.core.transaction.SqlBase_JVM
import molecule.sql.core.transaction.strategy.SqlOps

trait TxBase_sqlite { self: SqlBase_JVM =>

  override implicit lazy val sqlOps: SqlOps = new SqlOps_sqlite
}
