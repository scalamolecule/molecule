package molecule.sql.postgres.compliance.validation

import molecule.coreTests.spi.validation.MandatoryRefs
import molecule.sql.postgres.setup.TestAsync_postgres

object MandatoryRefs extends MandatoryRefs with TestAsync_postgres
