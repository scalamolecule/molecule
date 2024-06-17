package molecule.sql.sqlite.compliance.validation.crud.save

import molecule.coreTests.spi.validation.save._
import molecule.sql.sqlite.setup.TestAsync_sqlite

object FormatConstants  extends FormatConstants with TestAsync_sqlite
object FormatVariables extends FormatVariables with TestAsync_sqlite
object Semantics extends Semantics with TestAsync_sqlite
object TypesOne extends TypesOne with TestAsync_sqlite
object TypesOneOpt extends TypesOneOpt with TestAsync_sqlite
object TypesSeq extends TypesSeq with TestAsync_sqlite
object TypesSeqOpt extends TypesSeqOpt with TestAsync_sqlite
object TypesSet extends TypesSet with TestAsync_sqlite
object TypesSetOpt extends TypesSetOpt with TestAsync_sqlite
