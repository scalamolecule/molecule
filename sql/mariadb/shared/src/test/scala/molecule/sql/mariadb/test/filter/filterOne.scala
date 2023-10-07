package molecule.sql.mariadb.test.filter

import molecule.coreTests.test.filter.FilterOne_id
import molecule.coreTests.test.filter.one._
import molecule.coreTests.test.filter.one.special._
import molecule.sql.mariadb.setup.TestAsync_mariadb

object FilterOne_String_ extends FilterOne_String_ with TestAsync_mariadb
object FilterOne_Int extends FilterOne_Int with TestAsync_mariadb
object FilterOne_Long_ extends FilterOne_Long_ with TestAsync_mariadb
object FilterOne_Float_ extends FilterOne_Float_ with TestAsync_mariadb
object FilterOne_Double_ extends FilterOne_Double_ with TestAsync_mariadb
object FilterOne_Boolean extends FilterOne_Boolean with TestAsync_mariadb
object FilterOne_BigInt_ extends FilterOne_BigInt_ with TestAsync_mariadb
object FilterOne_BigDecimal_ extends FilterOne_BigDecimal_ with TestAsync_mariadb
object FilterOne_Date_ extends FilterOne_Date_ with TestAsync_mariadb
object FilterOne_UUID_ extends FilterOne_UUID_ with TestAsync_mariadb
object FilterOne_URI_ extends FilterOne_URI_ with TestAsync_mariadb
object FilterOne_Byte_ extends FilterOne_Byte_ with TestAsync_mariadb
object FilterOne_Short_ extends FilterOne_Short_ with TestAsync_mariadb
object FilterOne_Char_ extends FilterOne_Char_ with TestAsync_mariadb
object FilterOne_ref_ extends FilterOne_ref_ with TestAsync_mariadb

object FilterOneSpecial_Number extends FilterOneSpecial_Number with TestAsync_mariadb
object FilterOneSpecial_String extends FilterOneSpecial_String with TestAsync_mariadb

object FilterOne_id extends FilterOne_id with TestAsync_mariadb
