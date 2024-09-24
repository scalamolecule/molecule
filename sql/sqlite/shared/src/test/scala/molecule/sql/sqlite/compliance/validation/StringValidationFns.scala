package molecule.sql.sqlite.compliance.validation

import molecule.coreTests.spi.validation.StringValidationFns
import molecule.sql.sqlite.setup.Test_sqlite_async

object Test_StringValidationFns extends StringValidationFns with Test_sqlite_async
