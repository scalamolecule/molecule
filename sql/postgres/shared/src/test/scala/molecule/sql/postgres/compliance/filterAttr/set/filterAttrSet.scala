package molecule.sql.postgres.compliance.filterAttr.set

import molecule.coreTests.spi.filterAttr.set._
import molecule.sql.postgres.setup.TestAsync_postgres

object Adjacent extends Adjacent with TestAsync_postgres
object CrossNs extends CrossNs with TestAsync_postgres
object CrossNsOwned extends CrossNsOwned with TestAsync_postgres
object Semantics extends Semantics with TestAsync_postgres
object Types extends Types with TestAsync_postgres
