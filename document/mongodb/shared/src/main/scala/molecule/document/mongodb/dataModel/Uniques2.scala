package molecule.document.mongodb.dataModel

import molecule.DataModel

object Uniques2 extends DataModel(3) {

  trait Uniques {
    val i = oneInt
    val s = oneString

    // Cardinality one
    val string         = oneString.unique
    val int            = oneInt.unique
    val long           = oneLong.unique
    val float          = oneFloat.unique
    val double         = oneDouble.unique
    val boolean        = oneBoolean.unique
    val bigInt         = oneBigInt.unique
    val bigDecimal     = oneBigDecimal.unique
    val date           = oneDate.unique
    val duration       = oneDuration.unique
    val instant        = oneInstant.unique
    val localDate      = oneLocalDate.unique
    val localTime      = oneLocalTime.unique
    val localDateTime  = oneLocalDateTime.unique
    val offsetTime     = oneOffsetTime.unique
    val offsetDateTime = oneOffsetDateTime.unique
    val zonedDateTime  = oneZonedDateTime.unique
    val uuid           = oneUUID.unique
    val uri            = oneURI.unique
    val byte           = oneByte.unique
    val short          = oneShort.unique
    val char           = oneChar.unique

    // Cardinality many (Set)
    val ints = setInt.unique

    val ref  = one[Ref].owner
    val refs = many[Ref].owner
  }

  trait Ref {
    val i   = oneInt
    val s   = oneString
    val int = oneInt.unique
  }

  trait Other {
    val i   = oneInt
    val s   = oneString
    val int = oneInt.unique
  }
}