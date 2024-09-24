package molecule.sql.mariadb.compliance.validation.update

import molecule.coreTests.spi.validation.update._
import molecule.sql.mariadb.setup.Test_mariadb_async

object Test_TypesOne extends TypesOne with Test_mariadb_async
object Test_TypesOneOpt extends TypesOneOpt with Test_mariadb_async
object Test_TypesSeq extends TypesSeq with Test_mariadb_async
object Test_TypesSeqOpt extends TypesSeqOpt with Test_mariadb_async
object Test_TypesSet extends TypesSet with Test_mariadb_async
object Test_TypesSetOpt extends TypesSetOpt with Test_mariadb_async
