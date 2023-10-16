package molecule.sql.mariadb.compliance.validation.crud.save

import molecule.coreTests.spi.validation.save._
import molecule.sql.mariadb.setup.TestAsync_mariadb

object FormatConstants extends FormatConstants with TestAsync_mariadb
object FormatVariables extends FormatVariables with TestAsync_mariadb
object Semantics extends Semantics with TestAsync_mariadb
object TypesOne extends TypesOne with TestAsync_mariadb
object TypesOneOpt extends TypesOneOpt with TestAsync_mariadb
object TypesSet extends TypesSet with TestAsync_mariadb
object TypesSetOpt extends TypesSetOpt with TestAsync_mariadb
