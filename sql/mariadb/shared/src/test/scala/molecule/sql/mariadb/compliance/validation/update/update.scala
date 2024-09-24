package molecule.sql.mariadb.compliance.validation.crud.update

import molecule.coreTests.spi.validation.update._
import molecule.sql.mariadb.setup.TestAsync_mariadb

object Test_TypesOne extends TypesOne with TestAsync_mariadb
object Test_TypesOneOpt extends TypesOneOpt with TestAsync_mariadb
object Test_TypesSeq extends TypesSeq with TestAsync_mariadb
object Test_TypesSeqOpt extends TypesSeqOpt with TestAsync_mariadb
object Test_TypesSet extends TypesSet with TestAsync_mariadb
object Test_TypesSetOpt extends TypesSetOpt with TestAsync_mariadb
