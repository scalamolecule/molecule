package molecule.sql.mysql.compliance.validation.crud.insert

import molecule.coreTests.spi.validation.insert._
import molecule.sql.mysql.setup.TestAsync_mysql

object FormatConstants  extends FormatConstants with TestAsync_mysql
object FormatVariables extends FormatVariables with TestAsync_mysql
object Nested extends Nested with TestAsync_mysql
object Semantics extends Semantics with TestAsync_mysql
object TypesOne extends TypesOne with TestAsync_mysql
object TypesOneOpt extends TypesOneOpt with TestAsync_mysql
object TypesSet extends TypesSet with TestAsync_mysql
object TypesSetOpt extends TypesSetOpt with TestAsync_mysql
object TypesSeq extends TypesSeq with TestAsync_mysql
object TypesSetOpq extends TypesSeqOpt with TestAsync_mysql
