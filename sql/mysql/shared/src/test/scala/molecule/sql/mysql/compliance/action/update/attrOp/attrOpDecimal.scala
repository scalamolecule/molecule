package molecule.sql.mysql.compliance.action.update.attrOp

import molecule.coreTests.setup.Test
import molecule.coreTests.spi.action.update.attrOp.decimal._
import molecule.sql.mysql.setup.Api_mysql_async


class AttrOpDecimal_Float_Test extends Test {
  AttrOpDecimal_Float_(this, Api_mysql_async)
}
class AttrOpDecimal_DoubleTest extends Test {
  AttrOpDecimal_Double(this, Api_mysql_async)
}
class AttrOpDecimal_BigDecimalTest extends Test {
  AttrOpDecimal_BigDecimal(this, Api_mysql_async)
}

