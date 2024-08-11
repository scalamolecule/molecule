package molecule.sql.postgres.compliance.validation

import molecule.coreTests.spi.validation.RequiredAttrs
import molecule.sql.postgres.setup.TestAsync_postgres

object Test_RequiredAttrs extends RequiredAttrs with TestAsync_postgres
