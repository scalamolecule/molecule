package molecule.db.sqlite.compliance.action.update.attrOp

import molecule.core.setup.MUnit
import molecule.db
import molecule.db.compliance.test.action.update.attrOp.decimal.{AttrOpDecimal_BigDecimal, AttrOpDecimal_Double, AttrOpDecimal_Float_}
import molecule.db.sqlite.setup.Api_sqlite_async


class AttrOpDecimal_Float_Test extends MUnit {
  AttrOpDecimal_Float_(this, Api_sqlite_async)
}
class AttrOpDecimal_DoubleTest extends MUnit {
  AttrOpDecimal_Double(this, Api_sqlite_async)
}
class AttrOpDecimal_BigDecimalTest extends MUnit {
  AttrOpDecimal_BigDecimal(this, Api_sqlite_async)
}

