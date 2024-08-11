package molecule.sql.sqlite.compliance.validation

import molecule.coreTests.spi.validation.RequiredAttrs
import molecule.sql.sqlite.setup.TestAsync_sqlite

object Test_RequiredAttrs extends RequiredAttrs with TestAsync_sqlite
