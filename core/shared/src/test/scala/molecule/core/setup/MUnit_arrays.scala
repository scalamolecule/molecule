package molecule.core.setup

import munit.FunSuite
import scala.concurrent.duration.{DurationInt, FiniteDuration}

trait MUnit_arrays extends FunSuite {

  override def munitTimeout: FiniteDuration = 3.minutes

  extension (s: String) {
    def -(x: => Any): Unit = test(s)(x)
  }

  // Allow comparing Arrays

  extension (lhs: Any) {
    def ==>[V](rhs: V): Unit = {
      val (left, right) = (r(lhs), r(rhs))
      Predef.assert(
        left == right,
        s"""(Arrays converted to Lists)
           |Expected: $right
           |Actual  : $left
           |""".stripMargin
      )
    }
  }

  private def r(code: Any): Any = code match {
    case arr: Array[_]  => arr.toList
    case opt: Option[_] => opt.map(r)
    case seq: Seq[_]    => seq.map(r)
    case set: Set[_]    => set.map(r)
    case map: Map[_, _] => map.map { case (k, v) => (k, r(v)) }
    case tpl: Product   => tpl match {
      case Tuple1(a)                                                                 => Tuple1(a)
      case Tuple2(a, b)                                                              => Tuple2(r(a), r(b))
      case Tuple3(a, b, c)                                                           => Tuple3(r(a), r(b), r(c))
      case Tuple4(a, b, c, d)                                                        => Tuple4(r(a), r(b), r(c), r(d))
      case Tuple5(a, b, c, d, e)                                                     => Tuple5(r(a), r(b), r(c), r(d), r(e))
      case Tuple6(a, b, c, d, e, f)                                                  => Tuple6(r(a), r(b), r(c), r(d), r(e), r(f))
      case Tuple7(a, b, c, d, e, f, g)                                               => Tuple7(r(a), r(b), r(c), r(d), r(e), r(f), r(g))
      case Tuple8(a, b, c, d, e, f, g, h)                                            => Tuple8(r(a), r(b), r(c), r(d), r(e), r(f), r(g), r(h))
      case Tuple9(a, b, c, d, e, f, g, h, i)                                         => Tuple9(r(a), r(b), r(c), r(d), r(e), r(f), r(g), r(h), r(i))
      case Tuple10(a, b, c, d, e, f, g, h, i, j)                                     => Tuple10(r(a), r(b), r(c), r(d), r(e), r(f), r(g), r(h), r(i), r(j))
      case Tuple11(a, b, c, d, e, f, g, h, i, j, k)                                  => Tuple11(r(a), r(b), r(c), r(d), r(e), r(f), r(g), r(h), r(i), r(j), r(k))
      case Tuple12(a, b, c, d, e, f, g, h, i, j, k, l)                               => Tuple12(r(a), r(b), r(c), r(d), r(e), r(f), r(g), r(h), r(i), r(j), r(k), r(l))
      case Tuple13(a, b, c, d, e, f, g, h, i, j, k, l, m)                            => Tuple13(r(a), r(b), r(c), r(d), r(e), r(f), r(g), r(h), r(i), r(j), r(k), r(l), r(m))
      case Tuple14(a, b, c, d, e, f, g, h, i, j, k, l, m, n)                         => Tuple14(r(a), r(b), r(c), r(d), r(e), r(f), r(g), r(h), r(i), r(j), r(k), r(l), r(m), r(n))
      case Tuple15(a, b, c, d, e, f, g, h, i, j, k, l, m, n, o)                      => Tuple15(r(a), r(b), r(c), r(d), r(e), r(f), r(g), r(h), r(i), r(j), r(k), r(l), r(m), r(n), r(o))
      case Tuple16(a, b, c, d, e, f, g, h, i, j, k, l, m, n, o, p)                   => Tuple16(r(a), r(b), r(c), r(d), r(e), r(f), r(g), r(h), r(i), r(j), r(k), r(l), r(m), r(n), r(o), r(p))
      case Tuple17(a, b, c, d, e, f, g, h, i, j, k, l, m, n, o, p, q)                => Tuple17(r(a), r(b), r(c), r(d), r(e), r(f), r(g), r(h), r(i), r(j), r(k), r(l), r(m), r(n), r(o), r(p), r(q))
      case Tuple18(a, b, c, d, e, f, g, h, i, j, k, l, m, n, o, p, q, w)             => Tuple18(r(a), r(b), r(c), r(d), r(e), r(f), r(g), r(h), r(i), r(j), r(k), r(l), r(m), r(n), r(o), r(p), r(q), r(w))
      case Tuple19(a, b, c, d, e, f, g, h, i, j, k, l, m, n, o, p, q, w, s)          => Tuple19(r(a), r(b), r(c), r(d), r(e), r(f), r(g), r(h), r(i), r(j), r(k), r(l), r(m), r(n), r(o), r(p), r(q), r(w), r(s))
      case Tuple20(a, b, c, d, e, f, g, h, i, j, k, l, m, n, o, p, q, w, s, t)       => Tuple20(r(a), r(b), r(c), r(d), r(e), r(f), r(g), r(h), r(i), r(j), r(k), r(l), r(m), r(n), r(o), r(p), r(q), r(w), r(s), r(t))
      case Tuple21(a, b, c, d, e, f, g, h, i, j, k, l, m, n, o, p, q, w, s, t, u)    => Tuple21(r(a), r(b), r(c), r(d), r(e), r(f), r(g), r(h), r(i), r(j), r(k), r(l), r(m), r(n), r(o), r(p), r(q), r(w), r(s), r(t), r(u))
      case Tuple22(a, b, c, d, e, f, g, h, i, j, k, l, m, n, o, p, q, w, s, t, u, v) => Tuple22(r(a), r(b), r(c), r(d), r(e), r(f), r(g), r(h), r(i), r(j), r(k), r(l), r(m), r(n), r(o), r(p), r(q), r(w), r(s), r(t), r(u), r(v))
      case bigger                                                                    => bigger // Scala 3
    }
    case value          => value
  }
}
