package molecule.sql.postgres.compliance.validation.types.save

import molecule.coreTests.spi.validation.save._
import molecule.sql.postgres.setup.TestAsync_postgres

object FormatConstants extends FormatConstants with TestAsync_postgres
object FormatVariables extends FormatVariables with TestAsync_postgres
object Semantics extends Semantics with TestAsync_postgres
object TypesOne extends TypesOne with TestAsync_postgres
object TypesOneOpt extends TypesOneOpt with TestAsync_postgres
object TypesSeq extends TypesSeq with TestAsync_postgres
object TypesSeqOpt extends TypesSeqOpt with TestAsync_postgres
object TypesSet extends TypesSet with TestAsync_postgres
object TypesSetOpt extends TypesSetOpt with TestAsync_postgres
