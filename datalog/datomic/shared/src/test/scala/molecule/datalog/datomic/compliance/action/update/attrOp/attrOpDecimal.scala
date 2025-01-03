package molecule.datalog.datomic.compliance.action.update.attrOp

import molecule.coreTests.setup.Test
import molecule.coreTests.spi.action.update.attrOp.decimal._
import molecule.datalog.datomic.setup.Api_datomic_async


class AttrOpDecimal_Float_ extends Test {
  AttrOpDecimal_Float_(this, Api_datomic_async)
}
class AttrOpDecimal_Double extends Test {
  AttrOpDecimal_Double(this, Api_datomic_async)
}
class AttrOpDecimal_BigDecimal extends Test {
  AttrOpDecimal_BigDecimal(this, Api_datomic_async)
}
