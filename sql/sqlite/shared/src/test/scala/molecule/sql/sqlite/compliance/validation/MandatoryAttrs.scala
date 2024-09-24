package molecule.sql.sqlite.compliance.validation

import molecule.coreTests.spi.validation.MandatoryAttrs
import molecule.sql.sqlite.setup.Test_sqlite_async

object Test_MandatoryAttrs extends MandatoryAttrs with Test_sqlite_async
