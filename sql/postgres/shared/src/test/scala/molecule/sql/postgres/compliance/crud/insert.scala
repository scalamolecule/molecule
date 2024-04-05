package molecule.sql.postgres.compliance.crud

import molecule.coreTests.spi.crud.insert._
import molecule.sql.postgres.setup.{TestAsync_postgres, TestSuiteArray_postgres}
import molecule.sql.postgres.spi.SpiAsync_postgres

object InsertCardOne extends InsertCardOne with TestAsync_postgres
object InsertCardSet extends InsertCardSet with TestAsync_postgres
object InsertCardSeq extends InsertCardSeq with TestSuiteArray_postgres with SpiAsync_postgres
object InsertCardMap extends InsertCardMap with TestAsync_postgres
object InsertRefs extends InsertRefs with TestAsync_postgres
object InsertRefsOwned extends InsertRefsOwned with TestAsync_postgres
object InsertSemantics extends InsertSemantics with TestAsync_postgres
