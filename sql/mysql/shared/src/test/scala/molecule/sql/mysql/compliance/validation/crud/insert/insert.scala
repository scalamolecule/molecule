package molecule.sql.mysql.compliance.validation.crud.insert

import molecule.coreTests.spi.validation.insert._
import molecule.sql.mysql.setup.TestAsync_mysql

object Test_FormatConstants  extends FormatConstants with TestAsync_mysql
object Test_FormatVariables extends FormatVariables with TestAsync_mysql
object Test_Nested extends Nested with TestAsync_mysql
object Test_Semantics extends Semantics with TestAsync_mysql
object Test_TypesOne extends TypesOne with TestAsync_mysql
object Test_TypesOneOpt extends TypesOneOpt with TestAsync_mysql
object Test_TypesSeq extends TypesSeq with TestAsync_mysql
object Test_TypesSeqOpt extends TypesSeqOpt with TestAsync_mysql
object Test_TypesSet extends TypesSet with TestAsync_mysql
object Test_TypesSetOpt extends TypesSetOpt with TestAsync_mysql
