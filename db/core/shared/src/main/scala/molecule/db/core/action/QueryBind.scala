package molecule.db.core.action

trait QueryBind[Tpl, QueryBind[_] <: Action] {

  protected def bind(inputs: List[Any]): QueryBind[Tpl]

  def apply(a: Any): QueryBind[Tpl] = bind(List(a))
  def apply(a: Any, b: Any): QueryBind[Tpl] = bind(List(a, b))
  def apply(a: Any, b: Any, c: Any): QueryBind[Tpl] = bind(List(a, b, c))
  def apply(a: Any, b: Any, c: Any, d: Any): QueryBind[Tpl] = bind(List(a, b, c, d))
  def apply(a: Any, b: Any, c: Any, d: Any, e: Any): QueryBind[Tpl] = bind(List(a, b, c, d, e))
  def apply(a: Any, b: Any, c: Any, d: Any, e: Any, f: Any): QueryBind[Tpl] = bind(List(a, b, c, d, e, f))
  def apply(a: Any, b: Any, c: Any, d: Any, e: Any, f: Any, g: Any): QueryBind[Tpl] = bind(List(a, b, c, d, e, f, g))
  def apply(a: Any, b: Any, c: Any, d: Any, e: Any, f: Any, g: Any, h: Any): QueryBind[Tpl] = bind(List(a, b, c, d, e, f, g, h))
  def apply(a: Any, b: Any, c: Any, d: Any, e: Any, f: Any, g: Any, h: Any, i: Any): QueryBind[Tpl] = bind(List(a, b, c, d, e, f, g, h, i))
  def apply(a: Any, b: Any, c: Any, d: Any, e: Any, f: Any, g: Any, h: Any, i: Any, j: Any): QueryBind[Tpl] = bind(List(a, b, c, d, e, f, g, h, i, j))
  def apply(a: Any, b: Any, c: Any, d: Any, e: Any, f: Any, g: Any, h: Any, i: Any, j: Any, k: Any): QueryBind[Tpl] = bind(List(a, b, c, d, e, f, g, h, i, j, k))
  def apply(a: Any, b: Any, c: Any, d: Any, e: Any, f: Any, g: Any, h: Any, i: Any, j: Any, k: Any, l: Any): QueryBind[Tpl] = bind(List(a, b, c, d, e, f, g, h, i, j, k, l))
  def apply(a: Any, b: Any, c: Any, d: Any, e: Any, f: Any, g: Any, h: Any, i: Any, j: Any, k: Any, l: Any, m: Any): QueryBind[Tpl] = bind(List(a, b, c, d, e, f, g, h, i, j, k, l, m))
  def apply(a: Any, b: Any, c: Any, d: Any, e: Any, f: Any, g: Any, h: Any, i: Any, j: Any, k: Any, l: Any, m: Any, n: Any): QueryBind[Tpl] = bind(List(a, b, c, d, e, f, g, h, i, j, k, l, m, n))
  def apply(a: Any, b: Any, c: Any, d: Any, e: Any, f: Any, g: Any, h: Any, i: Any, j: Any, k: Any, l: Any, m: Any, n: Any, o: Any): QueryBind[Tpl] = bind(List(a, b, c, d, e, f, g, h, i, j, k, l, m, n, o))
  def apply(a: Any, b: Any, c: Any, d: Any, e: Any, f: Any, g: Any, h: Any, i: Any, j: Any, k: Any, l: Any, m: Any, n: Any, o: Any, p: Any): QueryBind[Tpl] = bind(List(a, b, c, d, e, f, g, h, i, j, k, l, m, n, o, p))
  def apply(a: Any, b: Any, c: Any, d: Any, e: Any, f: Any, g: Any, h: Any, i: Any, j: Any, k: Any, l: Any, m: Any, n: Any, o: Any, p: Any, q: Any): QueryBind[Tpl] = bind(List(a, b, c, d, e, f, g, h, i, j, k, l, m, n, o, p, q))
  def apply(a: Any, b: Any, c: Any, d: Any, e: Any, f: Any, g: Any, h: Any, i: Any, j: Any, k: Any, l: Any, m: Any, n: Any, o: Any, p: Any, q: Any, r: Any): QueryBind[Tpl] = bind(List(a, b, c, d, e, f, g, h, i, j, k, l, m, n, o, p, q, r))
  def apply(a: Any, b: Any, c: Any, d: Any, e: Any, f: Any, g: Any, h: Any, i: Any, j: Any, k: Any, l: Any, m: Any, n: Any, o: Any, p: Any, q: Any, r: Any, s: Any): QueryBind[Tpl] = bind(List(a, b, c, d, e, f, g, h, i, j, k, l, m, n, o, p, q, r, s))
  def apply(a: Any, b: Any, c: Any, d: Any, e: Any, f: Any, g: Any, h: Any, i: Any, j: Any, k: Any, l: Any, m: Any, n: Any, o: Any, p: Any, q: Any, r: Any, s: Any, t: Any): QueryBind[Tpl] = bind(List(a, b, c, d, e, f, g, h, i, j, k, l, m, n, o, p, q, r, s, t))
  def apply(a: Any, b: Any, c: Any, d: Any, e: Any, f: Any, g: Any, h: Any, i: Any, j: Any, k: Any, l: Any, m: Any, n: Any, o: Any, p: Any, q: Any, r: Any, s: Any, t: Any, u: Any): QueryBind[Tpl] = bind(List(a, b, c, d, e, f, g, h, i, j, k, l, m, n, o, p, q, r, s, t, u))
  def apply(a: Any, b: Any, c: Any, d: Any, e: Any, f: Any, g: Any, h: Any, i: Any, j: Any, k: Any, l: Any, m: Any, n: Any, o: Any, p: Any, q: Any, r: Any, s: Any, t: Any, u: Any, v: Any): QueryBind[Tpl] = bind(List(a, b, c, d, e, f, g, h, i, j, k, l, m, n, o, p, q, r, s, t, u, v))
}
