package molecule.datalog.datomic.compliance.aggregation

import molecule.coreTests.setup.Test
import molecule.coreTests.spi.aggregation.number._
import molecule.datalog.datomic.setup.Api_datomic_async

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
