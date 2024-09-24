package molecule.sql.mysql.compliance.validation.crud.update

import molecule.coreTests.spi.validation.update._
import molecule.sql.mysql.setup.TestAsync_mysql

object Test_TypesOne extends TypesOne with TestAsync_mysql
object Test_TypesOneOpt extends TypesOneOpt with TestAsync_mysql
object Test_TypesSeq extends TypesSeq with TestAsync_mysql
object Test_TypesSeqOpt extends TypesSeqOpt with TestAsync_mysql
object Test_TypesSet extends TypesSet with TestAsync_mysql
object Test_TypesSetOpt extends TypesSetOpt with TestAsync_mysql
