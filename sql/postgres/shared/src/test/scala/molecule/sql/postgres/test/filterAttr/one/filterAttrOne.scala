package molecule.sql.postgres.test.filterAttr.one

import molecule.coreTests.test.filterAttr.FilterAttr_id
import molecule.coreTests.test.filterAttr.one._
import molecule.sql.postgres.setup.TestAsync_postgres

object Adjacent extends Adjacent with TestAsync_postgres
object CrossNs extends CrossNs with TestAsync_postgres
object Semantics extends Semantics with TestAsync_postgres
object Sorting extends Sorting with TestAsync_postgres
object Types extends Types with TestAsync_postgres

object FilterAttr_id extends FilterAttr_id with TestAsync_postgres
