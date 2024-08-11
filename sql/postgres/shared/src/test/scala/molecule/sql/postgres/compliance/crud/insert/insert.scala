package molecule.sql.postgres.compliance.crud.insert

import molecule.coreTests.spi.crud.insert._
import molecule.sql.postgres.setup.{TestAsync_postgres, TestSuiteArray_postgres}
import molecule.sql.postgres.spi.SpiAsync_postgres

object Test_InsertCardOne extends InsertCardOne with TestAsync_postgres
object Test_InsertCardSeq extends InsertCardSeq with TestSuiteArray_postgres with SpiAsync_postgres
object Test_InsertCardSet extends InsertCardSet with TestAsync_postgres
object Test_InsertCardMap extends InsertCardMap with TestAsync_postgres
object Test_InsertRefs extends InsertRefs with TestAsync_postgres
object Test_InsertSemantics extends InsertSemantics with TestAsync_postgres
