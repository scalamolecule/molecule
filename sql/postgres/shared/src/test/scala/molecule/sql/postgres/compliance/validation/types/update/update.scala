package molecule.sql.postgres.compliance.validation.types.update

import molecule.coreTests.spi.validation.update._
import molecule.sql.postgres.setup.TestAsync_postgres

object Test_TypesOne extends TypesOne with TestAsync_postgres
object Test_TypesOneOpt extends TypesOneOpt with TestAsync_postgres
object Test_TypesSeq extends TypesSeq with TestAsync_postgres
object Test_TypesSeqOpt extends TypesSeqOpt with TestAsync_postgres
object Test_TypesSet extends TypesSet with TestAsync_postgres
object Test_TypesSetOpt extends TypesSetOpt with TestAsync_postgres
