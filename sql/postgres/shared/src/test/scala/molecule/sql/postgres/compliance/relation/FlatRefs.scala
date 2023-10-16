package molecule.sql.postgres.compliance.relation

import molecule.coreTests.spi.relation.flat.FlatRefs
import molecule.sql.postgres.setup.TestAsync_postgres

object FlatRefs extends FlatRefs with TestAsync_postgres
