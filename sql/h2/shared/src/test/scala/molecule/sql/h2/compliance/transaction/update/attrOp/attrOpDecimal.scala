package molecule.sql.h2.compliance.transaction.update.attrOp

import molecule.coreTests.setup.MUnitSuite
import molecule.coreTests.spi.action.update.attrOp.decimal._
import molecule.sql.h2.setup.Api_h2_async


class AttrOpDecimal_Float_ extends MUnitSuite {
  AttrOpDecimal_Float_(this, Api_h2_async)
}
class AttrOpDecimal_Double extends MUnitSuite {
  AttrOpDecimal_Double(this, Api_h2_async)
}
class AttrOpDecimal_BigDecimal extends MUnitSuite {
  AttrOpDecimal_BigDecimal(this, Api_h2_async)
}

