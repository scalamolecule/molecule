package molecule.sql.postgres.compliance.crud

import molecule.coreTests.spi.crud.update.seq.ops._
import molecule.sql.postgres.setup.{TestAsync_postgres, TestSuiteArray_postgres}
import molecule.sql.postgres.spi.SpiAsync_postgres

object UpdateSeqOps_String_ extends UpdateSeqOps_String_ with TestAsync_postgres
object UpdateSeqOps_Int extends UpdateSeqOps_Int with TestAsync_postgres
object UpdateSeqOps_Long_ extends UpdateSeqOps_Long_ with TestAsync_postgres
object UpdateSeqOps_Float_ extends UpdateSeqOps_Float_ with TestAsync_postgres
object UpdateSeqOps_Double_ extends UpdateSeqOps_Double_ with TestAsync_postgres
object UpdateSeqOps_BigInt_ extends UpdateSeqOps_BigInt_ with TestAsync_postgres
object UpdateSeqOps_BigDecimal_ extends UpdateSeqOps_BigDecimal_ with TestAsync_postgres
object UpdateSeqOps_Date_ extends UpdateSeqOps_Date_ with TestAsync_postgres
object UpdateSeqOps_Duration_ extends UpdateSeqOps_Duration_ with TestAsync_postgres
object UpdateSeqOps_Instant_ extends UpdateSeqOps_Instant_ with TestAsync_postgres
object UpdateSeqOps_LocalDate_ extends UpdateSeqOps_LocalDate_ with TestAsync_postgres
object UpdateSeqOps_LocalTime_ extends UpdateSeqOps_LocalTime_ with TestAsync_postgres
object UpdateSeqOps_LocalDateTime_ extends UpdateSeqOps_LocalDateTime_ with TestAsync_postgres
object UpdateSeqOps_OffsetTime_ extends UpdateSeqOps_OffsetTime_ with TestAsync_postgres
object UpdateSeqOps_OffsetDateTime_ extends UpdateSeqOps_OffsetDateTime_ with TestAsync_postgres
object UpdateSeqOps_ZonedDateTime_ extends UpdateSeqOps_ZonedDateTime_ with TestAsync_postgres
object UpdateSeqOps_UUID_ extends UpdateSeqOps_UUID_ with TestAsync_postgres
object UpdateSeqOps_URI_ extends UpdateSeqOps_URI_ with TestAsync_postgres
object UpdateSeqOps_Byte_ extends UpdateByteArrayOps_Byte with TestSuiteArray_postgres with SpiAsync_postgres
object UpdateSeqOps_Short_ extends UpdateSeqOps_Short_ with TestAsync_postgres
object UpdateSeqOps_Char_ extends UpdateSeqOps_Char_ with TestAsync_postgres
