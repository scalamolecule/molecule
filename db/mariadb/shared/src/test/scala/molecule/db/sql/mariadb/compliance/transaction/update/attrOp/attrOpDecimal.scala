package molecule.db.sql.mariadb.compliance.transaction.update.attrOp

import molecule.core.setup.MUnit
import molecule.db.compliance.test.action.update.attrOp.decimal.{AttrOpDecimal_BigDecimal, AttrOpDecimal_Double, AttrOpDecimal_Float_}
import molecule.db.sql.mariadb.setup.Api_mariadb_async


class AttrOpDecimal_Float_Test extends MUnit {
  AttrOpDecimal_Float_(this, Api_mariadb_async)
}
class AttrOpDecimal_DoubleTest extends MUnit {
  AttrOpDecimal_Double(this, Api_mariadb_async)
}
class AttrOpDecimal_BigDecimalTest extends MUnit {
  AttrOpDecimal_BigDecimal(this, Api_mariadb_async)
}

