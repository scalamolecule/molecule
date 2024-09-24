package molecule.sql.mysql.compliance.pagination.cursor.noUnique

import molecule.coreTests.spi.pagination.cursor.noUnique._
import molecule.sql.mysql.setup.Test_mysql_async

object Test_AttrOrderMandatory extends AttrOrderMandatory with Test_mysql_async
object Test_AttrOrderOptional extends AttrOrderOptional with Test_mysql_async
object Test_DirectionsMandatory extends DirectionsMandatory with Test_mysql_async
object Test_DirectionsOptional extends DirectionsOptional with Test_mysql_async
object Test_MutationAdd extends MutationAdd with Test_mysql_async
object Test_MutationDelete extends MutationDelete with Test_mysql_async
object Test_Nested extends Nested with Test_mysql_async
object Test_OptNested extends OptNested with Test_mysql_async
object Test_TypesOptional extends TypesOptional with Test_mysql_async
