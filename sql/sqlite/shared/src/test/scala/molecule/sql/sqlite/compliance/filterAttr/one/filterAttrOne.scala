package molecule.sql.sqlite.compliance.filterAttr.one

import molecule.coreTests.spi.filterAttr.one._
import molecule.sql.sqlite.setup.TestAsync_sqlite

object Adjacent extends Adjacent with TestAsync_sqlite
object CrossNs extends CrossNs with TestAsync_sqlite
object CrossNsOwned extends CrossNsOwned with TestAsync_sqlite
object FilterAttr_id extends FilterAttr_id with TestAsync_sqlite
object FilterAttrNested extends FilterAttrNested with TestAsync_sqlite
object FilterAttrRef extends FilterAttrRef with TestAsync_sqlite
object Semantics extends Semantics with TestAsync_sqlite
object Sorting extends Sorting with TestAsync_sqlite
object Types extends Types with TestAsync_sqlite
