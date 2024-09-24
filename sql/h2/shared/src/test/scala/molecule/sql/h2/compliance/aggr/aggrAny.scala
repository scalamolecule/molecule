package molecule.sql.h2.compliance.aggr

import molecule.coreTests.spi.aggr.any._
import molecule.sql.h2.setup.Test_h2_async

object Test_Aggr_String extends Aggr_String_ with Test_h2_async
object Test_Aggr_Int extends Aggr_Int with Test_h2_async
object Test_Aggr_Long extends Aggr_Long_ with Test_h2_async
object Test_Aggr_Float extends Aggr_Float_ with Test_h2_async
object Test_Aggr_Double extends Aggr_Double_ with Test_h2_async
object Test_Aggr_Boolean extends Aggr_Boolean with Test_h2_async
object Test_Aggr_BigInt extends Aggr_BigInt_ with Test_h2_async
object Test_Aggr_BigDecimal extends Aggr_BigDecimal_ with Test_h2_async
object Test_Aggr_Date extends Aggr_Date_ with Test_h2_async
object Test_Aggr_Duration extends Aggr_Duration_ with Test_h2_async
object Test_Aggr_Instant extends Aggr_Instant_ with Test_h2_async
object Test_Aggr_LocalDate extends Aggr_LocalDate_ with Test_h2_async
object Test_Aggr_LocalTime extends Aggr_LocalTime_ with Test_h2_async
object Test_Aggr_LocalDateTime extends Aggr_LocalDateTime_ with Test_h2_async
object Test_Aggr_OffsetTime extends Aggr_OffsetTime_ with Test_h2_async
object Test_Aggr_OffsetDateTime extends Aggr_OffsetDateTime_ with Test_h2_async
object Test_Aggr_ZonedDateTime extends Aggr_ZonedDateTime_ with Test_h2_async
object Test_Aggr_UUID extends Aggr_UUID_ with Test_h2_async
object Test_Aggr_URI extends Aggr_URI_ with Test_h2_async
object Test_Aggr_Byte extends Aggr_Byte_ with Test_h2_async
object Test_Aggr_Short extends Aggr_Short_ with Test_h2_async
object Test_Aggr_Char extends Aggr_Char_ with Test_h2_async

object Test_Aggr_ref extends Aggr_ref_ with Test_h2_async
