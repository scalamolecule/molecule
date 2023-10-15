package molecule.sql.mysql.compliance.validation.crud.save

import molecule.coreTests.compliance.validation.save._
import molecule.sql.mysql.setup.TestAsync_mysql

object FormatConstants  extends FormatConstants with TestAsync_mysql
object FormatVariables extends FormatVariables with TestAsync_mysql
object Semantics extends Semantics with TestAsync_mysql
object TypesOne extends TypesOne with TestAsync_mysql
object TypesOneOpt extends TypesOneOpt with TestAsync_mysql
object TypesSet extends TypesSet with TestAsync_mysql
object TypesSetOpt extends TypesSetOpt with TestAsync_mysql
