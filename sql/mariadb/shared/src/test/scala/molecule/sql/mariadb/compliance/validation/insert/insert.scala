package molecule.sql.mariadb.compliance.validation.crud.insert

import molecule.coreTests.spi.validation.insert._
import molecule.sql.mariadb.setup.TestAsync_mariadb

object Test_FormatConstants extends FormatConstants with TestAsync_mariadb
object Test_FormatVariables extends FormatVariables with TestAsync_mariadb
object Test_Nested extends Nested with TestAsync_mariadb
object Test_Semantics extends Semantics with TestAsync_mariadb
object Test_TypesOne extends TypesOne with TestAsync_mariadb
object Test_TypesOneOpt extends TypesOneOpt with TestAsync_mariadb
object Test_TypesSeq extends TypesSeq with TestAsync_mariadb
object Test_TypesSeqOpt extends TypesSeqOpt with TestAsync_mariadb
object Test_TypesSet extends TypesSet with TestAsync_mariadb
object Test_TypesSetOpt extends TypesSetOpt with TestAsync_mariadb
