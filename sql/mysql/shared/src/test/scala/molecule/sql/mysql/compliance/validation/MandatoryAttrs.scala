package molecule.sql.mysql.compliance.validation

import molecule.coreTests.spi.validation.MandatoryAttrs
import molecule.sql.mysql.setup.Test_mysql_async

object Test_MandatoryAttrs extends MandatoryAttrs with Test_mysql_async
