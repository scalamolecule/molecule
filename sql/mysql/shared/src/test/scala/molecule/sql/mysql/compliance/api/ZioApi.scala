package molecule.sql.mysql.compliance.api

import molecule.coreTests.spi.api._
import molecule.sql.mysql.setup.Test_mysql_zio

object Test_ZioApi extends ZioApi with Test_mysql_zio
