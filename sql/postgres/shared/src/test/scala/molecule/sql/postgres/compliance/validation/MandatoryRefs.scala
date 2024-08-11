package molecule.sql.postgres.compliance.validation

import molecule.coreTests.spi.validation.MandatoryRefs
import molecule.sql.postgres.setup.TestAsync_postgres

object Test_MandatoryRefs extends MandatoryRefs with TestAsync_postgres
