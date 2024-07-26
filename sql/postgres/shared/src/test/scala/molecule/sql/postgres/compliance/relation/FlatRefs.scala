package molecule.sql.postgres.compliance.relation

import molecule.coreTests.spi.relation.flat.{FlatRef, FlatRefOpt, FlatRefOptNested}
import molecule.sql.postgres.setup.TestAsync_postgres

object FlatRefs extends FlatRef with TestAsync_postgres
object FlatRefOpt extends FlatRefOpt with TestAsync_postgres
object FlatRefOptNested extends FlatRefOptNested with TestAsync_postgres
