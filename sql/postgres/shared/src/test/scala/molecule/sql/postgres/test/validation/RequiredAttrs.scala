package molecule.sql.postgres.test.validation

import molecule.coreTests.test.validation.RequiredAttrs
import molecule.sql.postgres.setup.TestAsync_postgres

object RequiredAttrs extends RequiredAttrs with TestAsync_postgres
