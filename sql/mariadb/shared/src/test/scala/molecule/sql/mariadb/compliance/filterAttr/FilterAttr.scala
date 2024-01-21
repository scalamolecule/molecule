package molecule.sql.mariadb.compliance.filterAttr

import molecule.coreTests.spi.filterAttr._
import molecule.sql.mariadb.setup.TestAsync_mariadb

object FilterAttr_id extends FilterAttr_id with TestAsync_mariadb
object FilterAttrNested extends FilterAttrNested with TestAsync_mariadb
object FilterAttrRef extends FilterAttrRef with TestAsync_mariadb
