package molecule.db.sql.mariadb.compliance.filter.one

import molecule.db.compliance.setup.Test
import molecule.db.compliance.test.filter.one.decimal.{FilterOneDecimal_BigDecimal_, FilterOneDecimal_Double, FilterOneDecimal_Float_}
import molecule.db.sql
import molecule.db.sql.mariadb.setup.Api_mariadb_async


class FilterOneDecimal_Float_Test extends Test {
  FilterOneDecimal_Float_(this, Api_mariadb_async)
}
class FilterOneDecimal_DoubleTest extends Test {
  FilterOneDecimal_Double(this, Api_mariadb_async)
}
class FilterOneDecimal_BigDecimal_Test extends Test {
  FilterOneDecimal_BigDecimal_(this, Api_mariadb_async)
}

