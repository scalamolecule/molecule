package molecule.sql.mysql.query

import java.net.URI
import java.util.{Date, UUID}
import molecule.sql.core.query.{LambdasSet, SqlQueryBase}

trait LambdasSet_mysql extends LambdasSet { self: SqlQueryBase =>

  override protected lazy val set2sqlArrayString    : Set[String] => String     = (set: Set[String]) => set.map(_.replace("'", "''")).mkString("ARRAY['", "', '", "']::text[]")
  override protected lazy val set2sqlArrayInt       : Set[Int] => String        = (set: Set[Int]) => set.mkString("ARRAY[", ", ", "]::integer[]")
  override protected lazy val set2sqlArrayLong      : Set[Long] => String       = (set: Set[Long]) => set.mkString("ARRAY[", ", ", "]::bigint[]")
  override protected lazy val set2sqlArrayFloat     : Set[Float] => String      = (set: Set[Float]) => set.mkString("ARRAY[", ", ", "]::decimal[]")
  override protected lazy val set2sqlArrayDouble    : Set[Double] => String     = (set: Set[Double]) => set.mkString("ARRAY[", ", ", "]::double precision[]")
  override protected lazy val set2sqlArrayBoolean   : Set[Boolean] => String    = (set: Set[Boolean]) => set.mkString("ARRAY[", ", ", "]::boolean[]")
  override protected lazy val set2sqlArrayBigInt    : Set[BigInt] => String     = (set: Set[BigInt]) => set.mkString("ARRAY[", ", ", "]::numeric[]")
  override protected lazy val set2sqlArrayBigDecimal: Set[BigDecimal] => String = (set: Set[BigDecimal]) => set.mkString("ARRAY[", ", ", "]::numeric[]")
  override protected lazy val set2sqlArrayDate      : Set[Date] => String       = (set: Set[Date]) => set.map(date2str(_)).mkString("ARRAY['", "', '", "']::date[]")
  override protected lazy val set2sqlArrayUUID      : Set[UUID] => String       = (set: Set[UUID]) => set.mkString("ARRAY['", "', '", "']::uuid[]")
  override protected lazy val set2sqlArrayURI       : Set[URI] => String        = (set: Set[URI]) => set.map(_.toString.replace("'", "''")).mkString("ARRAY['", "', '", "']::varchar[]")
  override protected lazy val set2sqlArrayByte      : Set[Byte] => String       = (set: Set[Byte]) => set.mkString("ARRAY[", ", ", "]::smallint[]")
  override protected lazy val set2sqlArrayShort     : Set[Short] => String      = (set: Set[Short]) => set.mkString("ARRAY[", ", ", "]::smallint[]")
  override protected lazy val set2sqlArrayChar      : Set[Char] => String       = (set: Set[Char]) => set.mkString("ARRAY['", "', '", "']::char[]")

  override protected lazy val j2Float: Any => Float = (v: Any) => v.toString.toFloat
  override protected lazy val j2Byte : Any => Byte  = (v: Any) => v.asInstanceOf[Short].toByte
  override protected lazy val j2Short: Any => Short = (v: Any) => v.asInstanceOf[Short]
}
