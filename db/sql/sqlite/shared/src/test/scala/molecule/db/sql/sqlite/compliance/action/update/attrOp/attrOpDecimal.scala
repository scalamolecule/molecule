package molecule.db.sql.sqlite.compliance.action.update.attrOp

import molecule.db.compliance.setup.Test
import molecule.db.compliance.test.action.update.attrOp.decimal.{AttrOpDecimal_BigDecimal, AttrOpDecimal_Double, AttrOpDecimal_Float_}
import molecule.db.sql
import molecule.db.sql.sqlite.setup.Api_sqlite_async


class AttrOpDecimal_Float_Test extends Test {
  AttrOpDecimal_Float_(this, Api_sqlite_async)
}
class AttrOpDecimal_DoubleTest extends Test {
  AttrOpDecimal_Double(this, Api_sqlite_async)
}
class AttrOpDecimal_BigDecimalTest extends Test {
  AttrOpDecimal_BigDecimal(this, Api_sqlite_async)
}

