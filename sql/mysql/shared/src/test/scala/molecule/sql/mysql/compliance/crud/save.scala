package molecule.sql.mysql.compliance.crud

import molecule.coreTests.spi.crud.save._
import molecule.sql.mysql.setup.TestAsync_mysql

object SaveCardOne extends SaveCardOne with TestAsync_mysql
object SaveCardSet extends SaveCardSet with TestAsync_mysql
object SaveRefs extends SaveRefs with TestAsync_mysql
object SaveSemantics extends SaveSemantics with TestAsync_mysql
