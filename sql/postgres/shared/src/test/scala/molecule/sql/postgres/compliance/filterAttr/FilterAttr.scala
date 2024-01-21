package molecule.sql.postgres.compliance.filterAttr

import molecule.coreTests.spi.filterAttr._
import molecule.sql.postgres.setup.TestAsync_postgres

object FilterAttr_id extends FilterAttr_id with TestAsync_postgres
object FilterAttrNested extends FilterAttrNested with TestAsync_postgres
object FilterAttrRef extends FilterAttrRef with TestAsync_postgres
