package molecule.sql.postgres.compliance.crud.save

import molecule.coreTests.spi.crud.save._
import molecule.sql.postgres.setup.{TestAsync_postgres, TestSuiteArray_postgres}
import molecule.sql.postgres.spi.SpiAsync_postgres

object SaveCardOne extends SaveCardOne with TestAsync_postgres
object SaveCardSeq extends SaveCardSeq with TestSuiteArray_postgres with SpiAsync_postgres
object SaveCardSet extends SaveCardSet with TestAsync_postgres
object SaveCardMap extends SaveCardMap with TestAsync_postgres
object SaveRefs extends SaveRefs with TestAsync_postgres
object SaveSemantics extends SaveSemantics with TestAsync_postgres
