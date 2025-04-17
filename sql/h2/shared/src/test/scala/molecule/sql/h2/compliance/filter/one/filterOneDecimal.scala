package molecule.sql.h2.compliance.filter.one

import molecule.coreTests.setup.Test
import molecule.coreTests.spi.filter.one.decimal._
import molecule.sql.h2.setup.Api_h2_async


class FilterOneDecimal_Float_Test extends Test {
  FilterOneDecimal_Float_(this, Api_h2_async)
}
class FilterOneDecimal_DoubleTest extends Test {
  FilterOneDecimal_Double(this, Api_h2_async)
}
class FilterOneDecimal_BigDecimal_Test extends Test {
  FilterOneDecimal_BigDecimal_(this, Api_h2_async)
}

