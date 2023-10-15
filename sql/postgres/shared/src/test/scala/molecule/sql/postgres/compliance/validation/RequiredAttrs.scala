package molecule.sql.postgres.compliance.validation

import molecule.coreTests.compliance.validation.RequiredAttrs
import molecule.sql.postgres.setup.TestAsync_postgres

object RequiredAttrs extends RequiredAttrs with TestAsync_postgres
