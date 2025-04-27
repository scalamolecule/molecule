package molecule.sql.h2.compliance.pagination.cursor.noUnique

import molecule.coreTests.setup.Test
import molecule.coreTests.spi.pagination.cursor.noUnique.*
import molecule.sql.h2.setup.Api_h2_async

class AttrOrderMandatoryTest extends Test {
  AttrOrderMandatory(this, Api_h2_async)
}
class AttrOrderOptionalTest extends Test {
  AttrOrderOptional(this, Api_h2_async)
}
class DirectionsMandatoryTest extends Test {
  DirectionsMandatory(this, Api_h2_async)
}
class DirectionsOptionalTest extends Test {
  DirectionsOptional(this, Api_h2_async)
}
class MutationAddTest extends Test {
  MutationAdd(this, Api_h2_async)
}
class MutationDeleteTest extends Test {
  MutationDelete(this, Api_h2_async)
}
class NestedTest extends Test {
  Nested(this, Api_h2_async)
}
class OptNestedTest extends Test {
  OptNested(this, Api_h2_async)
}
class TypesOptionalTest extends Test {
  TypesOptional(this, Api_h2_async)
}
