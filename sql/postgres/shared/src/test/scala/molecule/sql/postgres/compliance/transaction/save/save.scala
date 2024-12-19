package molecule.sql.postgres.compliance.transaction.save

import molecule.coreTests.spi.transaction.save._
import molecule.sql.postgres.setup.{Test_postgres_async, TestSuite_postgres_array}
import molecule.sql.postgres.spi.Spi_postgres_async

object Test_SaveCardOne extends SaveCardOne with Test_postgres_async
object Test_SaveCardSeq extends SaveCardSeq with TestSuite_postgres_array with Spi_postgres_async
object Test_SaveCardSet extends SaveCardSet with Test_postgres_async
object Test_SaveCardMap extends SaveCardMap with Test_postgres_async
object Test_SaveRefs extends SaveRefs with Test_postgres_async
object Test_SaveSemantics extends SaveSemantics with Test_postgres_async
