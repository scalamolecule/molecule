package molecule.db.sql.h2.compliance.filter.one

import molecule.core.setup.MUnit
import molecule.db.compliance.test.filter.one.number.*
import molecule.db.sql.h2.setup.Api_h2_async


class FilterOneInteger_IntTest extends MUnit {
  FilterOneInteger_Int(this, Api_h2_async)
}
class FilterOneInteger_Long_Test extends MUnit {
  FilterOneInteger_Long_(this, Api_h2_async)
}
class FilterOneInteger_BigInt_Test extends MUnit {
  FilterOneInteger_BigInt_(this, Api_h2_async)
}
class FilterOneInteger_Byte_Test extends MUnit {
  FilterOneInteger_Byte_(this, Api_h2_async)
}
class FilterOneInteger_Short_Test extends MUnit {
  FilterOneInteger_Short_(this, Api_h2_async)
}


