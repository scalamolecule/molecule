package molecule.sql.mariadb.compliance.validation

import molecule.coreTests.spi.validation.MandatoryAttrs
import molecule.sql.mariadb.setup.Test_mariadb_async

object Test_MandatoryAttrs extends MandatoryAttrs with Test_mariadb_async
