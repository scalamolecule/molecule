package molecule.sql.postgres.test.validation.crud.save

import molecule.coreTests.test.validation.save._
import molecule.sql.postgres.setup.TestAsync_postgres

object FormatConstants  extends FormatConstants with TestAsync_postgres
object FormatVariables extends FormatVariables with TestAsync_postgres
object Semantics extends Semantics with TestAsync_postgres
object TypesOne extends TypesOne with TestAsync_postgres
object TypesOneOpt extends TypesOneOpt with TestAsync_postgres
object TypesSet extends TypesSet with TestAsync_postgres
object TypesSetOpt extends TypesSetOpt with TestAsync_postgres
