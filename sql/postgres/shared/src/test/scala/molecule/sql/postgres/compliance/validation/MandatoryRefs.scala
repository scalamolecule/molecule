package molecule.sql.postgres.compliance.validation

import molecule.coreTests.spi.validation.MandatoryRefs
import molecule.sql.postgres.setup.Test_postgres_async

object Test_MandatoryRefs extends MandatoryRefs with Test_postgres_async
