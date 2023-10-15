package molecule.sql.postgres.compliance.validation.crud.update

import molecule.coreTests.compliance.validation.update._
import molecule.sql.postgres.setup.TestAsync_postgres

object TypesOne extends TypesOne with TestAsync_postgres
object TypesOneOpt extends TypesOneOpt with TestAsync_postgres
object TypesSet extends TypesSet with TestAsync_postgres
object TypesSetOpt extends TypesSetOpt with TestAsync_postgres
