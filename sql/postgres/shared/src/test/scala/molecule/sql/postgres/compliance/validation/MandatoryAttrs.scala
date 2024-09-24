package molecule.sql.postgres.compliance.validation

import molecule.coreTests.spi.validation.MandatoryAttrs
import molecule.sql.postgres.setup.Test_postgres_async

object Test_MandatoryAttrs extends MandatoryAttrs with Test_postgres_async
