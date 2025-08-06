// GENERATED CODE ********************************
package molecule.db.common.action

import java.net.URI
import java.time.*
import java.util.{Date, UUID}
import molecule.core.dataModel.*
import molecule.core.error.ModelError


trait QueryBind_[Tpl, QueryType[_] <: Action] {

  // Simply encoding the given value.
  // If of wrong type, it will be discovered further down the call chain.
  def x(raw: Any): Value = raw match {
    case v: String         => OneString(v)
    case v: Int            => OneInt(v)
    case v: Long           => OneLong(v)
    case v: Float          => OneFloat(v)
    case v: Double         => OneDouble(v)
    case v: Boolean        => OneBoolean(v)
    case v: BigInt         => OneBigInt(v)
    case v: BigDecimal     => OneBigDecimal(v)
    case v: Date           => OneDate(v)
    case v: Duration       => OneDuration(v)
    case v: Instant        => OneInstant(v)
    case v: LocalDate      => OneLocalDate(v)
    case v: LocalTime      => OneLocalTime(v)
    case v: LocalDateTime  => OneLocalDateTime(v)
    case v: OffsetTime     => OneOffsetTime(v)
    case v: OffsetDateTime => OneOffsetDateTime(v)
    case v: ZonedDateTime  => OneZonedDateTime(v)
    case v: UUID           => OneUUID(v)
    case v: URI            => OneURI(v)
    case v: Byte           => OneByte(v)
    case v: Short          => OneShort(v)
    case v: Char           => OneChar(v)
    case other             =>
      throw ModelError(
        s"""Bind input of unexpected type:
           |value: $other
           |type : ${other.getClass.getSimpleName}
           |""".stripMargin
      )
  }

  protected def bind(inputs: List[Value]): QueryType[Tpl]

  def apply(a: Any): QueryType[Tpl] = bind(List(x(a)))
  def apply(a: Any, b: Any): QueryType[Tpl] = bind(List(x(a), x(b)))
  def apply(a: Any, b: Any, c: Any): QueryType[Tpl] = bind(List(x(a), x(b), x(c)))
  def apply(a: Any, b: Any, c: Any, d: Any): QueryType[Tpl] = bind(List(x(a), x(b), x(c), x(d)))
  def apply(a: Any, b: Any, c: Any, d: Any, e: Any): QueryType[Tpl] = bind(List(x(a), x(b), x(c), x(d), x(e)))
  def apply(a: Any, b: Any, c: Any, d: Any, e: Any, f: Any): QueryType[Tpl] = bind(List(x(a), x(b), x(c), x(d), x(e), x(f)))
  def apply(a: Any, b: Any, c: Any, d: Any, e: Any, f: Any, g: Any): QueryType[Tpl] = bind(List(x(a), x(b), x(c), x(d), x(e), x(f), x(g)))
  def apply(a: Any, b: Any, c: Any, d: Any, e: Any, f: Any, g: Any, h: Any): QueryType[Tpl] = bind(List(x(a), x(b), x(c), x(d), x(e), x(f), x(g), x(h)))
  def apply(a: Any, b: Any, c: Any, d: Any, e: Any, f: Any, g: Any, h: Any, i: Any): QueryType[Tpl] = bind(List(x(a), x(b), x(c), x(d), x(e), x(f), x(g), x(h), x(i)))
  def apply(a: Any, b: Any, c: Any, d: Any, e: Any, f: Any, g: Any, h: Any, i: Any, j: Any): QueryType[Tpl] = bind(List(x(a), x(b), x(c), x(d), x(e), x(f), x(g), x(h), x(i), x(j)))
  def apply(a: Any, b: Any, c: Any, d: Any, e: Any, f: Any, g: Any, h: Any, i: Any, j: Any, k: Any): QueryType[Tpl] = bind(List(x(a), x(b), x(c), x(d), x(e), x(f), x(g), x(h), x(i), x(j), x(k)))
  def apply(a: Any, b: Any, c: Any, d: Any, e: Any, f: Any, g: Any, h: Any, i: Any, j: Any, k: Any, l: Any): QueryType[Tpl] = bind(List(x(a), x(b), x(c), x(d), x(e), x(f), x(g), x(h), x(i), x(j), x(k), x(l)))
  def apply(a: Any, b: Any, c: Any, d: Any, e: Any, f: Any, g: Any, h: Any, i: Any, j: Any, k: Any, l: Any, m: Any): QueryType[Tpl] = bind(List(x(a), x(b), x(c), x(d), x(e), x(f), x(g), x(h), x(i), x(j), x(k), x(l), x(m)))
  def apply(a: Any, b: Any, c: Any, d: Any, e: Any, f: Any, g: Any, h: Any, i: Any, j: Any, k: Any, l: Any, m: Any, n: Any): QueryType[Tpl] = bind(List(x(a), x(b), x(c), x(d), x(e), x(f), x(g), x(h), x(i), x(j), x(k), x(l), x(m), x(n)))
  def apply(a: Any, b: Any, c: Any, d: Any, e: Any, f: Any, g: Any, h: Any, i: Any, j: Any, k: Any, l: Any, m: Any, n: Any, o: Any): QueryType[Tpl] = bind(List(x(a), x(b), x(c), x(d), x(e), x(f), x(g), x(h), x(i), x(j), x(k), x(l), x(m), x(n), x(o)))
  def apply(a: Any, b: Any, c: Any, d: Any, e: Any, f: Any, g: Any, h: Any, i: Any, j: Any, k: Any, l: Any, m: Any, n: Any, o: Any, p: Any): QueryType[Tpl] = bind(List(x(a), x(b), x(c), x(d), x(e), x(f), x(g), x(h), x(i), x(j), x(k), x(l), x(m), x(n), x(o), x(p)))
  def apply(a: Any, b: Any, c: Any, d: Any, e: Any, f: Any, g: Any, h: Any, i: Any, j: Any, k: Any, l: Any, m: Any, n: Any, o: Any, p: Any, q: Any): QueryType[Tpl] = bind(List(x(a), x(b), x(c), x(d), x(e), x(f), x(g), x(h), x(i), x(j), x(k), x(l), x(m), x(n), x(o), x(p), x(q)))
  def apply(a: Any, b: Any, c: Any, d: Any, e: Any, f: Any, g: Any, h: Any, i: Any, j: Any, k: Any, l: Any, m: Any, n: Any, o: Any, p: Any, q: Any, r: Any): QueryType[Tpl] = bind(List(x(a), x(b), x(c), x(d), x(e), x(f), x(g), x(h), x(i), x(j), x(k), x(l), x(m), x(n), x(o), x(p), x(q), x(r)))
  def apply(a: Any, b: Any, c: Any, d: Any, e: Any, f: Any, g: Any, h: Any, i: Any, j: Any, k: Any, l: Any, m: Any, n: Any, o: Any, p: Any, q: Any, r: Any, s: Any): QueryType[Tpl] = bind(List(x(a), x(b), x(c), x(d), x(e), x(f), x(g), x(h), x(i), x(j), x(k), x(l), x(m), x(n), x(o), x(p), x(q), x(r), x(s)))
  def apply(a: Any, b: Any, c: Any, d: Any, e: Any, f: Any, g: Any, h: Any, i: Any, j: Any, k: Any, l: Any, m: Any, n: Any, o: Any, p: Any, q: Any, r: Any, s: Any, t: Any): QueryType[Tpl] = bind(List(x(a), x(b), x(c), x(d), x(e), x(f), x(g), x(h), x(i), x(j), x(k), x(l), x(m), x(n), x(o), x(p), x(q), x(r), x(s), x(t)))
  def apply(a: Any, b: Any, c: Any, d: Any, e: Any, f: Any, g: Any, h: Any, i: Any, j: Any, k: Any, l: Any, m: Any, n: Any, o: Any, p: Any, q: Any, r: Any, s: Any, t: Any, u: Any): QueryType[Tpl] = bind(List(x(a), x(b), x(c), x(d), x(e), x(f), x(g), x(h), x(i), x(j), x(k), x(l), x(m), x(n), x(o), x(p), x(q), x(r), x(s), x(t), x(u)))
  def apply(a: Any, b: Any, c: Any, d: Any, e: Any, f: Any, g: Any, h: Any, i: Any, j: Any, k: Any, l: Any, m: Any, n: Any, o: Any, p: Any, q: Any, r: Any, s: Any, t: Any, u: Any, v: Any): QueryType[Tpl] = bind(List(x(a), x(b), x(c), x(d), x(e), x(f), x(g), x(h), x(i), x(j), x(k), x(l), x(m), x(n), x(o), x(p), x(q), x(r), x(s), x(t), x(u), x(v)))
}