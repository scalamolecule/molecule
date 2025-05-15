package molecule.db.datalog.datomic.compliance.aggregation

import molecule.db.compliance.setup.Test
import molecule.db.compliance.test.aggregation.number.*
import molecule.db.datalog.datomic.setup.Api_datomic_async

class AggrNum_IntTest extends Test {
  AggrNum_Int(this, Api_datomic_async)
}
class AggrNum_Long_Test extends Test {
  AggrNum_Long_(this, Api_datomic_async)
}
class AggrNum_Float_Test extends Test {
  AggrNum_Float_(this, Api_datomic_async)
}
class AggrNum_Double_Test extends Test {
  AggrNum_Double_(this, Api_datomic_async)
}
class AggrNum_BigInt_Test extends Test {
  AggrNum_BigInt_(this, Api_datomic_async)
}
class AggrNum_BigDecimal_Test extends Test {
  AggrNum_BigDecimal_(this, Api_datomic_async)
}
class AggrNum_Byte_Test extends Test {
  AggrNum_Byte_(this, Api_datomic_async)
}
class AggrNum_Short_Test extends Test {
  AggrNum_Short_(this, Api_datomic_async)
}
