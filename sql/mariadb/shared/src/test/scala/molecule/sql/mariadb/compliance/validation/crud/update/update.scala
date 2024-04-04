package molecule.sql.mariadb.compliance.validation.crud.update

import molecule.coreTests.spi.validation.update._
import molecule.sql.mariadb.setup.TestAsync_mariadb

object TypesOne extends TypesOne with TestAsync_mariadb
object TypesOneOpt extends TypesOneOpt with TestAsync_mariadb
object TypesSet extends TypesSet with TestAsync_mariadb
object TypesSetOpt extends TypesSetOpt with TestAsync_mariadb
object TypesSeq extends TypesSeq with TestAsync_mariadb
object TypesSetOpq extends TypesSeqOpt with TestAsync_mariadb
