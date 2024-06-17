package molecule.sql.sqlite.compliance.crud.insert

import molecule.coreTests.spi.crud.insert._
import molecule.sql.sqlite.setup.{TestAsync_sqlite, TestSuiteArray_sqlite}
import molecule.sql.sqlite.spi.SpiAsync_sqlite

object InsertCardOne extends InsertCardOne with TestAsync_sqlite
object InsertCardSet extends InsertCardSet with TestAsync_sqlite
object InsertCardMap extends InsertCardMap with TestAsync_sqlite
object InsertCardSeq extends InsertCardSeq with TestSuiteArray_sqlite with SpiAsync_sqlite
object InsertRefs extends InsertRefs with TestAsync_sqlite
object InsertSemantics extends InsertSemantics with TestAsync_sqlite
