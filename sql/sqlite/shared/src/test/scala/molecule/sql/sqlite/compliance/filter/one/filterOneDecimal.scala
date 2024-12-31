package molecule.sql.sqlite.compliance.filter.one

import molecule.coreTests.setup.MUnitSuite
import molecule.coreTests.spi.filter.one.decimal._
import molecule.sql.sqlite.setup.Api_sqlite_async


class FilterOneDecimal_Float_ extends MUnitSuite {
  FilterOneDecimal_Float_(this, Api_sqlite_async)
}
class FilterOneDecimal_Double extends MUnitSuite {
  FilterOneDecimal_Double(this, Api_sqlite_async)
}
class FilterOneDecimal_BigDecimal_ extends MUnitSuite {
  FilterOneDecimal_BigDecimal_(this, Api_sqlite_async)
}

