package molecule.sql.postgres.compliance.action.update.attrOp

import molecule.coreTests.setup.Test
import molecule.coreTests.spi.action.update.attrOp.decimal._
import molecule.sql.postgres.setup.Api_postgres_async


class AttrOpDecimal_Float_Test extends Test {
  AttrOpDecimal_Float_(this, Api_postgres_async)
}
class AttrOpDecimal_DoubleTest extends Test {
  AttrOpDecimal_Double(this, Api_postgres_async)
}
class AttrOpDecimal_BigDecimalTest extends Test {
  AttrOpDecimal_BigDecimal(this, Api_postgres_async)
}

