package molecule.sql.mariadb.compliance.validation.crud.save

import molecule.coreTests.spi.validation.save._
import molecule.sql.mariadb.setup.TestAsync_mariadb

object Test_FormatConstants extends FormatConstants with TestAsync_mariadb
object Test_FormatVariables extends FormatVariables with TestAsync_mariadb
object Test_Semantics extends Semantics with TestAsync_mariadb
object Test_TypesOne extends TypesOne with TestAsync_mariadb
object Test_TypesOneOpt extends TypesOneOpt with TestAsync_mariadb
object Test_TypesSeq extends TypesSeq with TestAsync_mariadb
object Test_TypesSeqOpt extends TypesSeqOpt with TestAsync_mariadb
object Test_TypesSet extends TypesSet with TestAsync_mariadb
object Test_TypesSetOpt extends TypesSetOpt with TestAsync_mariadb
