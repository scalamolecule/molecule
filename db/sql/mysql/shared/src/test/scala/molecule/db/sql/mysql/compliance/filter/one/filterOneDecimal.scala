package molecule.db.sql.mysql.compliance.filter.one

import molecule.db.compliance.setup.Test
import molecule.db.compliance.test.filter.one.decimal.{FilterOneDecimal_BigDecimal_, FilterOneDecimal_Double, FilterOneDecimal_Float_}
import molecule.db.sql
import molecule.db.sql.mysql.setup.Api_mysql_async


class FilterOneDecimal_Float_Test extends Test {
  FilterOneDecimal_Float_(this, Api_mysql_async)
}
class FilterOneDecimal_DoubleTest extends Test {
  FilterOneDecimal_Double(this, Api_mysql_async)
}
class FilterOneDecimal_BigDecimal_Test extends Test {
  FilterOneDecimal_BigDecimal_(this, Api_mysql_async)
}

