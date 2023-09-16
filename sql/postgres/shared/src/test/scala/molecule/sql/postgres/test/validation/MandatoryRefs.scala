package molecule.sql.postgres.test.validation

import molecule.coreTests.test.validation.MandatoryRefs
import molecule.sql.postgres.setup.TestAsync_postgres

object MandatoryRefs extends MandatoryRefs with TestAsync_postgres
