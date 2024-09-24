package molecule.sql.mysql.compliance.pagination.cursor.subUnique

import molecule.coreTests.spi.pagination.cursor.subUnique._
import molecule.sql.mysql.setup.Test_mysql_async

object Test_AttrOrder extends AttrOrder with Test_mysql_async
object Test_DirectionsStandardUnique extends DirectionsStandardUnique with Test_mysql_async
object Test_DirectionsUniqueStandard extends DirectionsUniqueStandard with Test_mysql_async
object Test_MutationAdd extends MutationAdd with Test_mysql_async
object Test_MutationDelete extends MutationDelete with Test_mysql_async
object Test_Nested extends Nested with Test_mysql_async
object Test_OptNested extends OptNested with Test_mysql_async
object Test_TypesUniqueValue extends TypesUniqueValue with Test_mysql_async
