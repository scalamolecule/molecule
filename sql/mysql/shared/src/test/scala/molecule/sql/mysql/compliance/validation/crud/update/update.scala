package molecule.sql.mysql.compliance.validation.crud.update

import molecule.coreTests.spi.validation.update._
import molecule.sql.mysql.setup.TestAsync_mysql

object TypesOne extends TypesOne with TestAsync_mysql
object TypesOneOpt extends TypesOneOpt with TestAsync_mysql
object TypesSeq extends TypesSeq with TestAsync_mysql
object TypesSeqOpt extends TypesSeqOpt with TestAsync_mysql
object TypesSet extends TypesSet with TestAsync_mysql
object TypesSetOpt extends TypesSetOpt with TestAsync_mysql
