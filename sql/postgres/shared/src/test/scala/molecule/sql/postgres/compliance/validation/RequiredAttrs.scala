package molecule.sql.postgres.compliance.validation

import molecule.coreTests.spi.validation.RequiredAttrs
import molecule.sql.postgres.setup.TestAsync_postgres

object RequiredAttrs extends RequiredAttrs with TestAsync_postgres
