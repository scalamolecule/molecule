package molecule.sql.postgres.compliance.filterAttr.one

import molecule.coreTests.spi.filterAttr.one._
import molecule.sql.postgres.setup.TestAsync_postgres

object Adjacent extends Adjacent with TestAsync_postgres
object CrossNs extends CrossNs with TestAsync_postgres
object CrossNsOwned extends CrossNsOwned with TestAsync_postgres
object FilterAttr_id extends FilterAttr_id with TestAsync_postgres
object FilterAttrNested extends FilterAttrNested with TestAsync_postgres
object FilterAttrRef extends FilterAttrRef with TestAsync_postgres
object Semantics extends Semantics with TestAsync_postgres
object Sorting extends Sorting with TestAsync_postgres
object Types extends Types with TestAsync_postgres
