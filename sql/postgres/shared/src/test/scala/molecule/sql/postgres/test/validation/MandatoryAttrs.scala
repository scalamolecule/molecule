package molecule.sql.postgres.test.validation

import molecule.coreTests.test.validation.MandatoryAttrs
import molecule.sql.postgres.setup.TestAsync_postgres

object MandatoryAttrs extends MandatoryAttrs with TestAsync_postgres
