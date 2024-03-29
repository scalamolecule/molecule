package molecule.sql.h2.compliance.aggr

import molecule.coreTests.spi.aggr.any._
import molecule.sql.h2.setup.TestAsync_h2

object Aggr_String extends Aggr_String_ with TestAsync_h2
object Aggr_Int extends Aggr_Int with TestAsync_h2
object Aggr_Long extends Aggr_Long_ with TestAsync_h2
object Aggr_Float extends Aggr_Float_ with TestAsync_h2
object Aggr_Double extends Aggr_Double_ with TestAsync_h2
object Aggr_Boolean extends Aggr_Boolean with TestAsync_h2
object Aggr_BigInt extends Aggr_BigInt_ with TestAsync_h2
object Aggr_BigDecimal extends Aggr_BigDecimal_ with TestAsync_h2
object Aggr_Date extends Aggr_Date_ with TestAsync_h2
object Aggr_Duration extends Aggr_Duration_ with TestAsync_h2
object Aggr_Instant extends Aggr_Instant_ with TestAsync_h2
object Aggr_LocalDate extends Aggr_LocalDate_ with TestAsync_h2
object Aggr_LocalTime extends Aggr_LocalTime_ with TestAsync_h2
object Aggr_LocalDateTime extends Aggr_LocalDateTime_ with TestAsync_h2
object Aggr_OffsetTime extends Aggr_OffsetTime_ with TestAsync_h2
object Aggr_OffsetDateTime extends Aggr_OffsetDateTime_ with TestAsync_h2
object Aggr_ZonedDateTime extends Aggr_ZonedDateTime_ with TestAsync_h2
object Aggr_UUID extends Aggr_UUID_ with TestAsync_h2
object Aggr_URI extends Aggr_URI_ with TestAsync_h2
object Aggr_Byte extends Aggr_Byte_ with TestAsync_h2
object Aggr_Short extends Aggr_Short_ with TestAsync_h2
object Aggr_Char extends Aggr_Char_ with TestAsync_h2

object Aggr_ref extends Aggr_ref_ with TestAsync_h2
