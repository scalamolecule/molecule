package molecule.sql.postgres.test.crud

import molecule.coreTests.test.crud.insert._
import molecule.sql.postgres.setup.TestAsync_postgres

object InsertCardOne extends InsertCardOne with TestAsync_postgres
object InsertCardSet extends InsertCardSet with TestAsync_postgres
object InsertRefs extends InsertRefs with TestAsync_postgres
object InsertSemantics extends InsertSemantics with TestAsync_postgres
