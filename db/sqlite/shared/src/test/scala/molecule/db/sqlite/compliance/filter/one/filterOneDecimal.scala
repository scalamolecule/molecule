package molecule.db.sqlite.compliance.filter.one

import molecule.core.setup.MUnit
import molecule.db
import molecule.db.compliance.test.filter.one.decimal.{FilterOneDecimal_BigDecimal_, FilterOneDecimal_Double, FilterOneDecimal_Float_}
import molecule.db.sqlite.setup.Api_sqlite_async


class FilterOneDecimal_Float_Test extends MUnit {
  FilterOneDecimal_Float_(this, Api_sqlite_async)
}
class FilterOneDecimal_DoubleTest extends MUnit {
  FilterOneDecimal_Double(this, Api_sqlite_async)
}
class FilterOneDecimal_BigDecimal_Test extends MUnit {
  FilterOneDecimal_BigDecimal_(this, Api_sqlite_async)
}

