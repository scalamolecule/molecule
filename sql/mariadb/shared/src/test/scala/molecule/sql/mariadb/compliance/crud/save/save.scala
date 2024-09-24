package molecule.sql.mariadb.compliance.crud.save

import molecule.coreTests.spi.crud.save._
import molecule.sql.mariadb.setup.{Test_mariadb_async, TestSuite_mariadb_array}
import molecule.sql.mariadb.spi.Spi_mariadb_async

object Test_SaveCardOne extends SaveCardOne with Test_mariadb_async
object Test_SaveCardSeq extends SaveCardSeq with TestSuite_mariadb_array with Spi_mariadb_async
object Test_SaveCardSet extends SaveCardSet with Test_mariadb_async
object Test_SaveCardMap extends SaveCardMap with Test_mariadb_async
object Test_SaveRefs extends SaveRefs with Test_mariadb_async
object Test_SaveSemantics extends SaveSemantics with Test_mariadb_async
