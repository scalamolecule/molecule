package molecule.db.sql.postgres.compliance.filter.one

import molecule.db.compliance.setup.Test
import molecule.db.compliance.test.filter.one.number.*
import molecule.db.sql.postgres.setup.Api_postgres_async


class FilterOneInteger_IntTest extends Test {
  FilterOneInteger_Int(this, Api_postgres_async)
}
class FilterOneInteger_Long_Test extends Test {
  FilterOneInteger_Long_(this, Api_postgres_async)
}
class FilterOneInteger_BigInt_Test extends Test {
  FilterOneInteger_BigInt_(this, Api_postgres_async)
}
class FilterOneInteger_Byte_Test extends Test {
  FilterOneInteger_Byte_(this, Api_postgres_async)
}
class FilterOneInteger_Short_Test extends Test {
  FilterOneInteger_Short_(this, Api_postgres_async)
}

