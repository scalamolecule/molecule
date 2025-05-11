package molecule.db.sql.mysql.compliance.aggregation

import molecule.db.compliance.setup.Test
import molecule.db.compliance.test.aggregation.number.{AggrNum_BigDecimal_, AggrNum_BigInt_, AggrNum_Byte_, AggrNum_Double_, AggrNum_Float_, AggrNum_Int, AggrNum_Long_, AggrNum_Short_}
import molecule.db.sql
import molecule.db.sql.mysql.setup.Api_mysql_async

class AggrNum_IntTest extends Test {
  AggrNum_Int(this, Api_mysql_async)
}
class AggrNum_Long_Test extends Test {
  AggrNum_Long_(this, Api_mysql_async)
}
class AggrNum_Float_Test extends Test {
  AggrNum_Float_(this, Api_mysql_async)
}
class AggrNum_Double_Test extends Test {
  AggrNum_Double_(this, Api_mysql_async)
}
class AggrNum_BigInt_Test extends Test {
  AggrNum_BigInt_(this, Api_mysql_async)
}
class AggrNum_BigDecimal_Test extends Test {
  AggrNum_BigDecimal_(this, Api_mysql_async)
}
class AggrNum_Byte_Test extends Test {
  AggrNum_Byte_(this, Api_mysql_async)
}
class AggrNum_Short_Test extends Test {
  AggrNum_Short_(this, Api_mysql_async)
}


