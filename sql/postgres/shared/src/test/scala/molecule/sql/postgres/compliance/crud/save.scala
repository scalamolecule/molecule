package molecule.sql.postgres.compliance.crud

import molecule.coreTests.spi.crud.save._
import molecule.sql.postgres.setup.TestAsync_postgres

object SaveCardOne extends SaveCardOne with TestAsync_postgres
object SaveCardSet extends SaveCardSet with TestAsync_postgres
object SaveRefs extends SaveRefs with TestAsync_postgres
object SaveSemantics extends SaveSemantics with TestAsync_postgres
