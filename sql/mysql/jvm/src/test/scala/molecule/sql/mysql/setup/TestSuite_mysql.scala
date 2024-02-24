package molecule.sql.mysql.setup

import molecule.base.api.Schema
import molecule.base.util.BaseHelpers
import molecule.core.spi.Conn
import molecule.coreTests.dataModels.core.schema._
import molecule.coreTests.setup.CoreTestSuite
import molecule.sql.core.facade.JdbcHandler_JVM
import molecule.sql.mysql.setup.{Connection_mysql => c}


trait TestSuite_mysql extends CoreTestSuite with BaseHelpers {

  override val platform = "jvm"
  override val database = "Mysql"

  override def inMem[T](test: Conn => T, schema: Schema): T = {
    val conn = schema match {
      case TypesSchema      => JdbcHandler_JVM.recreateDb(c.conn_Types, c.recreateStmt_Types)
      case RefsSchema       => JdbcHandler_JVM.recreateDb(c.conn_Refs, c.recreateStmt_Refs)
      case UniquesSchema    => JdbcHandler_JVM.recreateDb(c.conn_Uniques, c.recreateStmt_Uniques)
      case ValidationSchema => JdbcHandler_JVM.recreateDb(c.conn_Validation, c.recreateStmt_Validation)
    }
    test(conn)
  }
}
