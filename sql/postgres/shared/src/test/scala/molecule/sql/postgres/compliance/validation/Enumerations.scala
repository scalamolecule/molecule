package molecule.sql.postgres.compliance.validation

import molecule.coreTests.spi.validation.Enumerations
import molecule.sql.postgres.setup.TestAsync_postgres

object Test_Enumerations extends Enumerations with TestAsync_postgres
