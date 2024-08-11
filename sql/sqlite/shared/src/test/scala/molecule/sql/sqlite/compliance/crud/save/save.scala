package molecule.sql.sqlite.compliance.crud.save

import molecule.coreTests.spi.crud.save._
import molecule.sql.sqlite.setup.{TestAsync_sqlite, TestSuiteArray_sqlite}
import molecule.sql.sqlite.spi.SpiAsync_sqlite

object Test_SaveCardOne extends SaveCardOne with TestAsync_sqlite
object Test_SaveCardSeq extends SaveCardSeq with TestSuiteArray_sqlite with SpiAsync_sqlite
object Test_SaveCardSet extends SaveCardSet with TestAsync_sqlite
object Test_SaveCardMap extends SaveCardMap with TestAsync_sqlite
object Test_SaveRefs extends SaveRefs with TestAsync_sqlite
object Test_SaveSemantics extends SaveSemantics with TestAsync_sqlite
