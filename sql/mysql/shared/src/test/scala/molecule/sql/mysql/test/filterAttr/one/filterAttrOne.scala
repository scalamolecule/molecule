package molecule.sql.mysql.test.filterAttr.one

import molecule.coreTests.test.filterAttr.FilterAttr_id
import molecule.coreTests.test.filterAttr.one._
import molecule.sql.mysql.setup.TestAsync_mysql

object Adjacent extends Adjacent with TestAsync_mysql
object CrossNs extends CrossNs with TestAsync_mysql
object Semantics extends Semantics with TestAsync_mysql
object Sorting extends Sorting with TestAsync_mysql
object Types extends Types with TestAsync_mysql

object FilterAttr_id extends FilterAttr_id with TestAsync_mysql
