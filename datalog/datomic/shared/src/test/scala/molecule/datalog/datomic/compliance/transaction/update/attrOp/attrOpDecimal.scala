package molecule.datalog.datomic.compliance.transaction.update.attrOp

import molecule.coreTests.setup.MUnitSuite
import molecule.coreTests.spi.action.update.attrOp.decimal._
import molecule.datalog.datomic.setup.Api_datomic_async


class AttrOpDecimal_Float_ extends MUnitSuite {
  AttrOpDecimal_Float_(this, Api_datomic_async)
}
class AttrOpDecimal_Double extends MUnitSuite {
  AttrOpDecimal_Double(this, Api_datomic_async)
}
class AttrOpDecimal_BigDecimal extends MUnitSuite {
  AttrOpDecimal_BigDecimal(this, Api_datomic_async)
}

