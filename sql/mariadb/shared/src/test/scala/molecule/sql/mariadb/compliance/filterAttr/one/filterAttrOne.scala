package molecule.sql.mariadb.compliance.filterAttr.one

import molecule.coreTests.compliance.filterAttr.FilterAttr_id
import molecule.coreTests.compliance.filterAttr.one._
import molecule.sql.mariadb.setup.TestAsync_mariadb

object Adjacent extends Adjacent with TestAsync_mariadb
object CrossNs extends CrossNs with TestAsync_mariadb
object Semantics extends Semantics with TestAsync_mariadb
object Sorting extends Sorting with TestAsync_mariadb
object Types extends Types with TestAsync_mariadb

object FilterAttr_id extends FilterAttr_id with TestAsync_mariadb
