package molecule.sql.postgres.compliance.transaction.update.attrOp

import molecule.coreTests.setup.Test
import molecule.coreTests.spi.transaction.update.attrOp.decimal._
import molecule.sql.postgres.setup.Api_postgres_async


class AttrOpDecimal_Float_ extends Test {
  AttrOpDecimal_Float_(this, Api_postgres_async)
}
class AttrOpDecimal_Double extends Test {
  AttrOpDecimal_Double(this, Api_postgres_async)
}
class AttrOpDecimal_BigDecimal extends Test {
  AttrOpDecimal_BigDecimal(this, Api_postgres_async)
}

