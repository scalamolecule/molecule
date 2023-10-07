package molecule.sql.mysql.test.filter

import molecule.coreTests.test.filter.set._
import molecule.sql.mysql.setup.TestAsync_mysql

object FilterSet_String_ extends FilterSet_String_ with TestAsync_mysql
object FilterSet_Int extends FilterSet_Int with TestAsync_mysql
object FilterSet_Long_ extends FilterSet_Long_ with TestAsync_mysql
object FilterSet_Float_ extends FilterSet_Float_ with TestAsync_mysql
object FilterSet_Double_ extends FilterSet_Double_ with TestAsync_mysql
object FilterSet_Boolean extends FilterSet_Boolean with TestAsync_mysql
object FilterSet_BigInt_ extends FilterSet_BigInt_ with TestAsync_mysql
object FilterSet_BigDecimal_ extends FilterSet_BigDecimal_ with TestAsync_mysql
object FilterSet_Date_ extends FilterSet_Date_ with TestAsync_mysql
object FilterSet_UUID_ extends FilterSet_UUID_ with TestAsync_mysql
object FilterSet_URI_ extends FilterSet_URI_ with TestAsync_mysql
object FilterSet_Byte_ extends FilterSet_Byte_ with TestAsync_mysql
object FilterSet_Short_ extends FilterSet_Short_ with TestAsync_mysql
object FilterSet_Char_ extends FilterSet_Char_ with TestAsync_mysql

object FilterSet_ref_ extends FilterSet_ref with TestAsync_mysql