package molecule.sql.postgres.compliance.validation

import molecule.coreTests.spi.validation.StringValidationFns
import molecule.sql.postgres.setup.Test_postgres_async

object Test_StringValidationFns extends StringValidationFns with Test_postgres_async
