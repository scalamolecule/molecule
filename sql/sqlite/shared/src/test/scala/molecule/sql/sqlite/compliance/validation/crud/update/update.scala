package molecule.sql.sqlite.compliance.validation.crud.update

import molecule.coreTests.spi.validation.update._
import molecule.sql.sqlite.setup.TestAsync_sqlite

object TypesOne extends TypesOne with TestAsync_sqlite
object TypesOneOpt extends TypesOneOpt with TestAsync_sqlite
object TypesSeq extends TypesSeq with TestAsync_sqlite
object TypesSeqOpt extends TypesSeqOpt with TestAsync_sqlite
object TypesSet extends TypesSet with TestAsync_sqlite
object TypesSetOpt extends TypesSetOpt with TestAsync_sqlite
