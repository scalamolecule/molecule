package molecule.sql.sqlite.compliance.validation.update

import molecule.coreTests.spi.validation.update._
import molecule.sql.sqlite.setup.Test_sqlite_async

object Test_TypesOne extends TypesOne with Test_sqlite_async
object Test_TypesOneOpt extends TypesOneOpt with Test_sqlite_async
object Test_TypesSeq extends TypesSeq with Test_sqlite_async
object Test_TypesSeqOpt extends TypesSeqOpt with Test_sqlite_async
object Test_TypesSet extends TypesSet with Test_sqlite_async
object Test_TypesSetOpt extends TypesSetOpt with Test_sqlite_async
