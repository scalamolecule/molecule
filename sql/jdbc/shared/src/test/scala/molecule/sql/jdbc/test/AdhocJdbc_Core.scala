package molecule.sql.jdbc.test

import molecule.coreTests.AdhocCoreTests
import molecule.sql.jdbc.setup.JdbcTestSuite
import scala.language.implicitConversions


object AdhocJdbc_Core extends AdhocCoreTests with JdbcTestSuite
