package molecule.sql.sqlite.compliance.validation.crud.update

import molecule.coreTests.spi.validation.update._
import molecule.sql.sqlite.setup.TestAsync_sqlite

object Test_TypesOne extends TypesOne with TestAsync_sqlite
object Test_TypesOneOpt extends TypesOneOpt with TestAsync_sqlite
object Test_TypesSeq extends TypesSeq with TestAsync_sqlite
object Test_TypesSeqOpt extends TypesSeqOpt with TestAsync_sqlite
object Test_TypesSet extends TypesSet with TestAsync_sqlite
object Test_TypesSetOpt extends TypesSetOpt with TestAsync_sqlite
