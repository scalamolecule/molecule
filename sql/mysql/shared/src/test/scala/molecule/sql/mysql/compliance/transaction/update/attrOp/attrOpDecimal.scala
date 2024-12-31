package molecule.sql.mysql.compliance.transaction.update.attrOp

import molecule.coreTests.setup.Test
import molecule.coreTests.spi.transaction.update.attrOp.decimal._
import molecule.sql.mysql.setup.Api_mysql_async


class AttrOpDecimal_Float_ extends Test {
  AttrOpDecimal_Float_(this, Api_mysql_async)
}
class AttrOpDecimal_Double extends Test {
  AttrOpDecimal_Double(this, Api_mysql_async)
}
class AttrOpDecimal_BigDecimal extends Test {
  AttrOpDecimal_BigDecimal(this, Api_mysql_async)
}

