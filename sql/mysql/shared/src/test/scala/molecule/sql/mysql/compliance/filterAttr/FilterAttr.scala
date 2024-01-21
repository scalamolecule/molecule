package molecule.sql.mysql.compliance.filterAttr

import molecule.coreTests.spi.filterAttr._
import molecule.sql.mysql.setup.TestAsync_mysql

object FilterAttr_id extends FilterAttr_id with TestAsync_mysql
object FilterAttrNested extends FilterAttrNested with TestAsync_mysql
object FilterAttrRef extends FilterAttrRef with TestAsync_mysql
