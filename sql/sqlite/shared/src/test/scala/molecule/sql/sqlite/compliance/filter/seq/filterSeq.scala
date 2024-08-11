package molecule.sql.sqlite.compliance.filter.seq

import molecule.coreTests.spi.filter.seq.ref._
import molecule.coreTests.spi.filter.seq.types._
import molecule.sql.sqlite.setup.{TestAsync_sqlite, TestSuiteArray_sqlite}
import molecule.sql.sqlite.spi.SpiAsync_sqlite

object Test_FilterSeq_String_ extends FilterSeq_String_ with TestAsync_sqlite
object Test_FilterSeq_Int extends FilterSeq_Int with TestAsync_sqlite
object Test_FilterSeq_Long_ extends FilterSeq_Long_ with TestAsync_sqlite
object Test_FilterSeq_Float_ extends FilterSeq_Float_ with TestAsync_sqlite
object Test_FilterSeq_Double_ extends FilterSeq_Double_ with TestAsync_sqlite
object Test_FilterSeq_Boolean extends FilterSeq_Boolean with TestAsync_sqlite
object Test_FilterSeq_BigInt_ extends FilterSeq_BigInt_ with TestAsync_sqlite
object Test_FilterSeq_BigDecimal_ extends FilterSeq_BigDecimal_ with TestAsync_sqlite
object Test_FilterSeq_Date_ extends FilterSeq_Date_ with TestAsync_sqlite
object Test_FilterSeq_Duration_ extends FilterSeq_Duration_ with TestAsync_sqlite
object Test_FilterSeq_Instant_ extends FilterSeq_Instant_ with TestAsync_sqlite
object Test_FilterSeq_LocalDate_ extends FilterSeq_LocalDate_ with TestAsync_sqlite
object Test_FilterSeq_LocalTime_ extends FilterSeq_LocalTime_ with TestAsync_sqlite
object Test_FilterSeq_LocalDateTime_ extends FilterSeq_LocalDateTime_ with TestAsync_sqlite
object Test_FilterSeq_OffsetTime_ extends FilterSeq_OffsetTime_ with TestAsync_sqlite
object Test_FilterSeq_OffsetDateTime_ extends FilterSeq_OffsetDateTime_ with TestAsync_sqlite
object Test_FilterSeq_ZonedDateTime_ extends FilterSeq_ZonedDateTime_ with TestAsync_sqlite
object Test_FilterSeq_UUID_ extends FilterSeq_UUID_ with TestAsync_sqlite
object Test_FilterSeq_URI_ extends FilterSeq_URI_ with TestAsync_sqlite
object Test_FilterSeq_ByteArray extends FilterSeq_ByteArray with TestSuiteArray_sqlite with SpiAsync_sqlite
object Test_FilterSeq_Short_ extends FilterSeq_Short_ with TestAsync_sqlite
object Test_FilterSeq_Char_ extends FilterSeq_Char_ with TestAsync_sqlite


object Test_FilterRefSeq_Card1Ref extends FilterRefSeq_Card1Ref with TestAsync_sqlite
object Test_FilterRefSeq_Card1RefOwned extends FilterRefSeq_Card1RefOwned with TestAsync_sqlite
object Test_FilterRefSeq_Card2Ref extends FilterRefSeq_Card2Ref with TestAsync_sqlite
object Test_FilterRefSeq_Card2RefOwned extends FilterRefSeq_Card2RefOwned with TestAsync_sqlite
