package molecule.db.sqlite.compliance.aggregation

import molecule.core.setup.MUnit
import molecule.db
import molecule.db.compliance.test.aggregation.number.*
import molecule.db.sqlite.setup.Api_sqlite_async

class AggrNum_IntTest extends MUnit {
  AggrNum_Int(this, Api_sqlite_async)
}
class AggrNum_Long_Test extends MUnit {
  AggrNum_Long_(this, Api_sqlite_async)
}
class AggrNum_Float_Test extends MUnit {
  AggrNum_Float(this, Api_sqlite_async)
}
class AggrNum_Double_Test extends MUnit {
  AggrNum_Double_(this, Api_sqlite_async)
}
class AggrNum_BigInt_Test extends MUnit {
  AggrNum_BigInt_(this, Api_sqlite_async)
}
class AggrNum_BigDecimal_Test extends MUnit {
  AggrNum_BigDecimal_(this, Api_sqlite_async)
}
class AggrNum_Byte_Test extends MUnit {
  AggrNum_Byte_(this, Api_sqlite_async)
}
class AggrNum_Short_Test extends MUnit {
  AggrNum_Short_(this, Api_sqlite_async)
}
