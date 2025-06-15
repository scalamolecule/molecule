package molecule.db.compliance.domains

import java.time.*
import java.util.Date
import molecule.DomainStructure

object Validation extends DomainStructure(5) {

  trait Strings {
    val email        = oneString.email
    val emailWithMsg = oneString.email("Please provide a real email")

    val regex        = oneString.regex("^[a-zA-Z0-9]+$")
    val regexWithMsg = oneString.regex(
      "^[a-zA-Z0-9]+$",
      "Username cannot contain special characters."
    )
    val enums        = many[Enum]
  }

  trait Enum {
    val luckyNumber  = oneInt.allowedValues(7, 9, 13)
    val luckyNumber2 = oneInt.allowedValues(
      Seq(7, 9, 13),
      "Lucky number can only be 7, 9 or 13"
    )
  }

  trait Type {
    val string         = oneString.validate(_ > "b")
    val int            = oneInt.validate(_ > 2)
    val long           = oneLong.validate(_ > 2L)
    val float          = oneFloat.validate(_ > 2.2f)
    val double         = oneDouble.validate(_ > 2.2)
    val boolean        = oneBoolean.validate(_ == false)
    val bigInt         = oneBigInt.validate(_ > BigInt(2))
    val bigDecimal     = oneBigDecimal.validate(_ > BigDecimal(2.2))
    val date           = oneDate.validate(_.after(new Date(993942000000L)))
    val duration       = oneDuration.validate(_.compareTo(Duration.ofMinutes(2)) > 0)
    val instant        = oneInstant.validate(_.compareTo(Instant.ofEpochSecond(2)) > 0)
    val localDate      = oneLocalDate.validate(_.compareTo(LocalDate.of(2002, 1, 1)) > 0)
    val localTime      = oneLocalTime.validate(_.compareTo(LocalTime.of(2, 2)) > 0)
    val localDateTime  = oneLocalDateTime.validate(_.compareTo(LocalDateTime.of(2002, 1, 1, 1, 2)) > 0)
    val offsetTime     = oneOffsetTime.validate(_.compareTo(OffsetTime.of(2, 2, 2, 2, ZoneOffset.ofHours(2))) > 0)
    val offsetDateTime = oneOffsetDateTime.validate(_.compareTo(OffsetDateTime.of(2002, 1, 1, 1, 1, 1, 1, ZoneOffset.ofHours(2))) > 0)
    val zonedDateTime  = oneZonedDateTime.validate(_.compareTo(ZonedDateTime.of(2002, 1, 1, 1, 1, 1, 1, ZoneOffset.ofHours(2))) > 0)
    val uuid           = oneUUID.validate(_.toString != "aaaaaaaa-aaaa-aaaa-aaaa-aaaaaaaaaaaa")
    val uri            = oneURI.validate(_.toString.length > 2)
    val byte           = oneByte.validate(_ > 2)
    val short          = oneShort.validate(_ > 2)
    val char           = oneChar.validate(_ > 'b')
    val ref            = one[Strings]

    val stringSet         = setString.validate(_ > "c")
    val intSet            = setInt.validate(_ > 3)
    val longSet           = setLong.validate(_ > 3L)
    val floatSet          = setFloat.validate(_ > 3.3f)
    val doubleSet         = setDouble.validate(_ > 3.3)
    val booleanSet        = setBoolean.validate(_ == false)
    val bigIntSet         = setBigInt.validate(_ > BigInt(3))
    val bigDecimalSet     = setBigDecimal.validate(_ > BigDecimal(3.3))
    val dateSet           = setDate.validate(_.after(new Date(1057010400000L)))
    val durationSet       = setDuration.validate(_.compareTo(Duration.ofMinutes(2)) > 0)
    val instantSet        = setInstant.validate(_.compareTo(Instant.ofEpochSecond(2)) > 0)
    val localDateSet      = setLocalDate.validate(_.compareTo(LocalDate.of(2002, 1, 1)) > 0)
    val localTimeSet      = setLocalTime.validate(_.compareTo(LocalTime.of(2, 2)) > 0)
    val localDateTimeSet  = setLocalDateTime.validate(_.compareTo(LocalDateTime.of(2002, 1, 1, 1, 2)) > 0)
    val offsetTimeSet     = setOffsetTime.validate(_.compareTo(OffsetTime.of(2, 2, 2, 2, ZoneOffset.ofHours(2))) > 0)
    val offsetDateTimeSet = setOffsetDateTime.validate(_.compareTo(OffsetDateTime.of(2002, 1, 1, 1, 1, 1, 1, ZoneOffset.ofHours(2))) > 0)
    val zonedDateTimeSet  = setZonedDateTime.validate(_.compareTo(ZonedDateTime.of(2002, 1, 1, 1, 1, 1, 1, ZoneOffset.ofHours(2))) > 0)
    val uuidSet           = setUUID.validate(_.toString != "aaaaaaaa-aaaa-aaaa-aaaa-aaaaaaaaaaaa")
    val uriSet            = setURI.validate(_.toString.length > 3)
    val byteSet           = setByte.validate(_ > 3)
    val shortSet          = setShort.validate(_ > 3)
    val charSet           = setChar.validate(_ > 'c')
    val refs              = many[Strings]

    val stringSeq         = seqString.validate(_ > "c")
    val intSeq            = seqInt.validate(_ > 3)
    val longSeq           = seqLong.validate(_ > 3L)
    val floatSeq          = seqFloat.validate(_ > 3.3f)
    val doubleSeq         = seqDouble.validate(_ > 3.3)
    val booleanSeq        = seqBoolean.validate(_ == false)
    val bigIntSeq         = seqBigInt.validate(_ > BigInt(3))
    val bigDecimalSeq     = seqBigDecimal.validate(_ > BigDecimal(3.3))
    val dateSeq           = seqDate.validate(_.after(new Date(1057010400000L)))
    val durationSeq       = seqDuration.validate(_.compareTo(Duration.ofMinutes(2)) > 0)
    val instantSeq        = seqInstant.validate(_.compareTo(Instant.ofEpochSecond(2)) > 0)
    val localDateSeq      = seqLocalDate.validate(_.compareTo(LocalDate.of(2002, 1, 1)) > 0)
    val localTimeSeq      = seqLocalTime.validate(_.compareTo(LocalTime.of(2, 2)) > 0)
    val localDateTimeSeq  = seqLocalDateTime.validate(_.compareTo(LocalDateTime.of(2002, 1, 1, 1, 2)) > 0)
    val offsetTimeSeq     = seqOffsetTime.validate(_.compareTo(OffsetTime.of(2, 2, 2, 2, ZoneOffset.ofHours(2))) > 0)
    val offsetDateTimeSeq = seqOffsetDateTime.validate(_.compareTo(OffsetDateTime.of(2002, 1, 1, 1, 1, 1, 1, ZoneOffset.ofHours(2))) > 0)
    val zonedDateTimeSeq  = seqZonedDateTime.validate(_.compareTo(ZonedDateTime.of(2002, 1, 1, 1, 1, 1, 1, ZoneOffset.ofHours(2))) > 0)
    val uuidSeq           = seqUUID.validate(_.toString != "aaaaaaaa-aaaa-aaaa-aaaa-aaaaaaaaaaaa")
    val uriSeq            = seqURI.validate(_.toString.length > 3)
    val shortSeq          = seqShort.validate(_ > 3)
    val charSeq           = seqChar.validate(_ > 'c')

    // Validation not supported for map attributes
    // val intMap = mapInt.validate(_ > 3)
  }

  trait Constants {
    val noErrorMsg               = oneInt.validate(_ > 2)
    val errorMsg                 = oneInt.validate(
      _ > 2,
      "One-line error msg"
    )
    val errorMsgWithValue        = oneInt.validate(
      _ > 2,
      "One-line error msg. Found $v"
    )
    val errorMsgWithValueQuoted  = oneString.validate(
      _.startsWith("hello"),
      "Expected hello. Found \"$v\"."
    )
    val errorMsgWithValueQuoted2 = oneString.validate(
      _.startsWith("hello"),
      """Expected hello. Found "$v"."""
    )
    // String interpolation in validation error messages not allowed
    //    val errorMsgWithValueQuoted3 = oneString.validate(
    //      _.startsWith("hello"),
    //      s"""Expected hello. Found $$v."""
    //    )
    val multilineErrorMsg        = oneInt.validate((v: Int) => v.>(2),
      """Long error explanation
        |with multiple lines""".stripMargin
    )
    val multilineMsgWithValue    = oneInt.validate((v: Int) => v.>(2),
      """Validation failed:
        |Input value $v is not bigger than 2.""".stripMargin
    )
    val multilineMsgWithValue2   = oneInt.validate((v: Int) => v.>(2),
      """Validation failed:
        |Input value "$v" is not bigger than 2.""".stripMargin
    )

    val multiLine  = oneInt.validate { v =>
      val data   = 22
      val result = data % 10
      v > result
    }
    val multiLine2 = oneInt.validate(
      { v =>
        val data   = 22
        val result = {
          data % 10
        }
        v > result
      },
      "One-line error msg"
    )
    val multiLine3 = oneInt.validate({ v =>
      val data   = 22
      val result = data % 10
      v > result
    },
      """Long error explanation
        |with multiple lines""".stripMargin
    )


    val logic = oneInt.validate(
      v => v >= 3 && v <= 9 && v != 7 && v % 2 == 1,
      "Value must be an odd number between 3 and 9 but not 7"
    )

    val multipleErrors = oneInt.validate(
      {
        case v if v > 2  => "Test 1: Number must be bigger than 2. Found: $v"
        case v if v < 10 => "Test 2: Number must be smaller than 10. Found: $v"
        case v if v != 7 => "Test 3: Number must not be 7"
        case v if {
          // Comments in code blocks are transferred to boilerplate code
          val divider = 2
          v % divider == 1
        }                =>
          """Test 4: Number must
            |be odd. Found: $v""".stripMargin
      }
    )

  }


  trait Variables {
    // Calling `value` is only allowed in validation code
    // val intx = oneInt.value

    val int        = oneInt
    val noErrorMsg = oneInt.validate(_ > int.value)

    val int1     = oneInt
    val errorMsg = oneInt.validate(
      _ > int1.value,
      "One-line error msg"
    )

    val int2              = oneInt
    val errorMsgWithValue = oneInt.validate(
      _ > int2.value,
      "One-line error msg. Found $v"
    )

    val int3         = oneInt
    val multilineMsg = oneInt.validate((v: Int) => v.>(int3.value),
      """Validation failed:
        |Input value `$v` is not bigger than `int3` value `$int3`.""".stripMargin
    )

    val int4      = oneInt
    val multiLine = oneInt.validate { v =>
      val data   = 22
      val result = data % int4.value
      v > result
    }

    val int5       = oneInt
    val multiLine2 = oneInt.validate(
      { v =>
        val data   = 22
        val result = {
          data % int5.value
        }
        v > result
      },
      "One-line error msg"
    )

    val int6       = oneInt
    val multiLine3 = oneInt.validate({ v =>
      val data   = 22
      val result = data % int6.value
      v > result
    },
      """Long error explanation
        |with multiple lines""".stripMargin
    )

    val int7  = oneInt
    val logic = oneInt.validate(
      v => v >= 3 && v <= 9 && v != int7.value && v % 2 == 1,
      "Value must be an odd number between 3 and 9 but not `int7` value `$int7`"
    )

    val int8   = oneInt
    val str    = oneString
    val intSet = setInt
    val strs   = setString

    val multipleErrors = oneInt.validate(
      {
        case v if v > 4 =>
          "Test 1: Number must be bigger than 4. Found: $v"

        case v if v > int8.value =>
          "Test 2: Number must be bigger than `int8` value `$int8`. Found: $v"

        case v if v < str.value.length * 2 =>
          "Test 3: Number must be smaller than `str` value `$str` length `${str.length}` * 2. Found: $v"

        case v if {
          v != intSet.value.head - 3
        } => "Test 4: Number must not be `intSet` head value `${intSet.head}` minus 3. Found: $v"

        case v if {
          val divider = strs.value.size
          v % divider == 1
        } =>
          """Test 5: Number must
            |be odd. Found: $v""".stripMargin
      }
    )
  }


  trait MandatoryAttr {
    val name    = oneString.mandatory
    val hobbies = setString.mandatory
    val age     = oneInt
  }

  trait MandatoryRefAB {
    val i    = oneInt
    val refA = one[RefA].mandatory
  }
  trait MandatoryRefB {
    val i    = oneInt
    val refB = one[RefB].mandatory
  }

  trait MandatoryRefsAB {
    val i     = oneInt
    val refsA = many[RefA].mandatory
  }
  trait MandatoryRefsB {
    val i     = oneInt
    val refsB = many[RefB].mandatory
  }

  trait RefA {
    val i    = oneInt
    val refB = one[RefB].mandatory
  }
  trait RefB {
    val i = oneInt
  }

  // Mandatory circular dependencies not allowed
  //  trait Self {
  //    val self = one[Self].mandatory
  //  }
  //
  //  trait Aa {
  //    val refB = one[Bb].mandatory
  //  }
  //  trait Bb {
  //    val refC = one[Cc].mandatory
  //  }
  //  trait Cc {
  //    val refA = one[Aa].mandatory
  //  }


  trait Require {
    val i = oneInt

    // Pair of data guaranteed
    val username = oneString.require(password)
    val password = oneString

    // Triple of data guaranteed
    // Only points with all 3 coordinates present are valid
    val x = oneInt.require(y, z)
    val y = oneInt
    val z = oneInt

    // Attributes can only be required once.
    // w can't require z since z is already required
    // val w = oneInt.require(z)

    // Mixed ref/attr
    val int  = oneInt.require(refB)
    val refB = one[RefB]

    // Ref-only tuples
    val ref1 = one[RefB].require(ref2)
    val ref2 = one[Enum]
  }
}
