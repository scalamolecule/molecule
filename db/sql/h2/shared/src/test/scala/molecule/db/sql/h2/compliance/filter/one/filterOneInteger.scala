package molecule.db.sql.h2.compliance.filter.one

import molecule.db.compliance.setup.Test
import molecule.db.compliance.test.filter.one.number.{FilterOneInteger_BigInt_, FilterOneInteger_Byte_, FilterOneInteger_Int, FilterOneInteger_Long_, FilterOneInteger_Short_}
import molecule.db.sql
import molecule.db.sql.h2.setup.Api_h2_async


class FilterOneInteger_IntTest extends Test {
  FilterOneInteger_Int(this, Api_h2_async)
}
class FilterOneInteger_Long_Test extends Test {
  FilterOneInteger_Long_(this, Api_h2_async)
}
class FilterOneInteger_BigInt_Test extends Test {
  FilterOneInteger_BigInt_(this, Api_h2_async)
}
class FilterOneInteger_Byte_Test extends Test {
  FilterOneInteger_Byte_(this, Api_h2_async)
}
class FilterOneInteger_Short_Test extends Test {
  FilterOneInteger_Short_(this, Api_h2_async)
}


