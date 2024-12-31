package molecule.datalog.datomic.compliance.filter.one

import molecule.coreTests.setup.Test
import molecule.coreTests.spi.filter.one.decimal._
import molecule.datalog.datomic.setup.Api_datomic_async


class FilterOneDecimal_Float_ extends Test {
  FilterOneDecimal_Float_(this, Api_datomic_async)
}
class FilterOneDecimal_Double extends Test {
  FilterOneDecimal_Double(this, Api_datomic_async)
}
class FilterOneDecimal_BigDecimal_ extends Test {
  FilterOneDecimal_BigDecimal_(this, Api_datomic_async)
}

