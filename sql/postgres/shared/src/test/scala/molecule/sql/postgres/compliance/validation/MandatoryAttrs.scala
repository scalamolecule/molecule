package molecule.sql.postgres.compliance.validation

import molecule.coreTests.spi.validation.MandatoryAttrs
import molecule.sql.postgres.setup.TestAsync_postgres

object MandatoryAttrs extends MandatoryAttrs with TestAsync_postgres
