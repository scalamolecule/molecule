package molecule.graphql.client.action

trait QueryBind[Tpl, QueryType[_]] {

  protected def bind(inputs: List[Any]): QueryType[Tpl]

  def apply(a: Any): QueryType[Tpl] = bind(List(a))
  def apply(a: Any, b: Any): QueryType[Tpl] = bind(List(a, b))
  def apply(a: Any, b: Any, c: Any): QueryType[Tpl] = bind(List(a, b, c))
  def apply(a: Any, b: Any, c: Any, d: Any): QueryType[Tpl] = bind(List(a, b, c, d))
  def apply(a: Any, b: Any, c: Any, d: Any, e: Any): QueryType[Tpl] = bind(List(a, b, c, d, e))
  def apply(a: Any, b: Any, c: Any, d: Any, e: Any, f: Any): QueryType[Tpl] = bind(List(a, b, c, d, e, f))
  def apply(a: Any, b: Any, c: Any, d: Any, e: Any, f: Any, g: Any): QueryType[Tpl] = bind(List(a, b, c, d, e, f, g))
  def apply(a: Any, b: Any, c: Any, d: Any, e: Any, f: Any, g: Any, h: Any): QueryType[Tpl] = bind(List(a, b, c, d, e, f, g, h))
  def apply(a: Any, b: Any, c: Any, d: Any, e: Any, f: Any, g: Any, h: Any, i: Any): QueryType[Tpl] = bind(List(a, b, c, d, e, f, g, h, i))
  def apply(a: Any, b: Any, c: Any, d: Any, e: Any, f: Any, g: Any, h: Any, i: Any, j: Any): QueryType[Tpl] = bind(List(a, b, c, d, e, f, g, h, i, j))
  def apply(a: Any, b: Any, c: Any, d: Any, e: Any, f: Any, g: Any, h: Any, i: Any, j: Any, k: Any): QueryType[Tpl] = bind(List(a, b, c, d, e, f, g, h, i, j, k))
  def apply(a: Any, b: Any, c: Any, d: Any, e: Any, f: Any, g: Any, h: Any, i: Any, j: Any, k: Any, l: Any): QueryType[Tpl] = bind(List(a, b, c, d, e, f, g, h, i, j, k, l))
  def apply(a: Any, b: Any, c: Any, d: Any, e: Any, f: Any, g: Any, h: Any, i: Any, j: Any, k: Any, l: Any, m: Any): QueryType[Tpl] = bind(List(a, b, c, d, e, f, g, h, i, j, k, l, m))
  def apply(a: Any, b: Any, c: Any, d: Any, e: Any, f: Any, g: Any, h: Any, i: Any, j: Any, k: Any, l: Any, m: Any, n: Any): QueryType[Tpl] = bind(List(a, b, c, d, e, f, g, h, i, j, k, l, m, n))
  def apply(a: Any, b: Any, c: Any, d: Any, e: Any, f: Any, g: Any, h: Any, i: Any, j: Any, k: Any, l: Any, m: Any, n: Any, o: Any): QueryType[Tpl] = bind(List(a, b, c, d, e, f, g, h, i, j, k, l, m, n, o))
  def apply(a: Any, b: Any, c: Any, d: Any, e: Any, f: Any, g: Any, h: Any, i: Any, j: Any, k: Any, l: Any, m: Any, n: Any, o: Any, p: Any): QueryType[Tpl] = bind(List(a, b, c, d, e, f, g, h, i, j, k, l, m, n, o, p))
  def apply(a: Any, b: Any, c: Any, d: Any, e: Any, f: Any, g: Any, h: Any, i: Any, j: Any, k: Any, l: Any, m: Any, n: Any, o: Any, p: Any, q: Any): QueryType[Tpl] = bind(List(a, b, c, d, e, f, g, h, i, j, k, l, m, n, o, p, q))
  def apply(a: Any, b: Any, c: Any, d: Any, e: Any, f: Any, g: Any, h: Any, i: Any, j: Any, k: Any, l: Any, m: Any, n: Any, o: Any, p: Any, q: Any, r: Any): QueryType[Tpl] = bind(List(a, b, c, d, e, f, g, h, i, j, k, l, m, n, o, p, q, r))
  def apply(a: Any, b: Any, c: Any, d: Any, e: Any, f: Any, g: Any, h: Any, i: Any, j: Any, k: Any, l: Any, m: Any, n: Any, o: Any, p: Any, q: Any, r: Any, s: Any): QueryType[Tpl] = bind(List(a, b, c, d, e, f, g, h, i, j, k, l, m, n, o, p, q, r, s))
  def apply(a: Any, b: Any, c: Any, d: Any, e: Any, f: Any, g: Any, h: Any, i: Any, j: Any, k: Any, l: Any, m: Any, n: Any, o: Any, p: Any, q: Any, r: Any, s: Any, t: Any): QueryType[Tpl] = bind(List(a, b, c, d, e, f, g, h, i, j, k, l, m, n, o, p, q, r, s, t))
  def apply(a: Any, b: Any, c: Any, d: Any, e: Any, f: Any, g: Any, h: Any, i: Any, j: Any, k: Any, l: Any, m: Any, n: Any, o: Any, p: Any, q: Any, r: Any, s: Any, t: Any, u: Any): QueryType[Tpl] = bind(List(a, b, c, d, e, f, g, h, i, j, k, l, m, n, o, p, q, r, s, t, u))
  def apply(a: Any, b: Any, c: Any, d: Any, e: Any, f: Any, g: Any, h: Any, i: Any, j: Any, k: Any, l: Any, m: Any, n: Any, o: Any, p: Any, q: Any, r: Any, s: Any, t: Any, u: Any, v: Any): QueryType[Tpl] = bind(List(a, b, c, d, e, f, g, h, i, j, k, l, m, n, o, p, q, r, s, t, u, v))
}
