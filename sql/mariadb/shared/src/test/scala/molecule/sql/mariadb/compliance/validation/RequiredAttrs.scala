package molecule.sql.mariadb.compliance.validation

import molecule.coreTests.spi.validation.RequiredAttrs
import molecule.sql.mariadb.setup.Test_mariadb_async

object Test_RequiredAttrs extends RequiredAttrs with Test_mariadb_async
