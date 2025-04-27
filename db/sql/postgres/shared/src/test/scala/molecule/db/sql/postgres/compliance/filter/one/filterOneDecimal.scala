package molecule.db.sql.postgres.compliance.filter.one

import molecule.coreTests.setup.Test
import molecule.coreTests.spi.filter.one.decimal.*
import molecule.db.sql
import molecule.db.sql.postgres.setup.Api_postgres_async


class FilterOneDecimal_Float_Test extends Test {
  FilterOneDecimal_Float_(this, Api_postgres_async)
}
class FilterOneDecimal_DoubleTest extends Test {
  FilterOneDecimal_Double(this, Api_postgres_async)
}
class FilterOneDecimal_BigDecimal_Test extends Test {
  FilterOneDecimal_BigDecimal_(this, Api_postgres_async)
}

