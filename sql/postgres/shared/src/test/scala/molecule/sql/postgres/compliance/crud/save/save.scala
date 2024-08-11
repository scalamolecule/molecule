package molecule.sql.postgres.compliance.crud.save

import molecule.coreTests.spi.crud.save._
import molecule.sql.postgres.setup.{TestAsync_postgres, TestSuiteArray_postgres}
import molecule.sql.postgres.spi.SpiAsync_postgres

object Test_SaveCardOne extends SaveCardOne with TestAsync_postgres
object Test_SaveCardSeq extends SaveCardSeq with TestSuiteArray_postgres with SpiAsync_postgres
object Test_SaveCardSet extends SaveCardSet with TestAsync_postgres
object Test_SaveCardMap extends SaveCardMap with TestAsync_postgres
object Test_SaveRefs extends SaveRefs with TestAsync_postgres
object Test_SaveSemantics extends SaveSemantics with TestAsync_postgres
