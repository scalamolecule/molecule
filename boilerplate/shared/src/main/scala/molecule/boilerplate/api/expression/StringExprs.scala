//// GENERATED CODE ********************************
//package molecule.boilerplate.api.expression
//
//import molecule.boilerplate.api._
//import molecule.boilerplate.ast.Model._
//
//
//// Make specialized operations on String type
//
//trait ExprOneManOps_1[A, t, Ns[_, _]] extends ExprBase {
//  protected def _exprOneMan(op: Op, vs: Seq[t]): Ns[A, t] with SortAttrs_1[A, t, Ns] = ???
//}
//
//trait ExprOneMan_1[A, t, Ns[_, _]]
//  extends ExprOneManOps_1[A, t, Ns]
//    with Aggregates_1[A, t, Ns]
//    with SortAttrs_1[A, t, Ns] {
//  //  def apply(                ): Ns[A, t] with SortAttrs_1[A, t, Ns] = _exprOneMan(Appl, Nil       )
//  //  def apply(v    : t, vs: t*): Ns[A, t] with SortAttrs_1[A, t, Ns] = _exprOneMan(Appl, v +: vs   )
//  //  def apply(vs   : Seq[t]   ): Ns[A, t] with SortAttrs_1[A, t, Ns] = _exprOneMan(Appl, vs        )
//  //  def not  (v    : t, vs: t*): Ns[A, t] with SortAttrs_1[A, t, Ns] = _exprOneMan(Not , v +: vs   )
//  //  def not  (vs   : Seq[t]   ): Ns[A, t] with SortAttrs_1[A, t, Ns] = _exprOneMan(Not , vs        )
//  //  def <    (upper: t        ): Ns[A, t] with SortAttrs_1[A, t, Ns] = _exprOneMan(Lt  , Seq(upper))
//  //  def <=   (upper: t        ): Ns[A, t] with SortAttrs_1[A, t, Ns] = _exprOneMan(Le  , Seq(upper))
//  //  def >    (lower: t        ): Ns[A, t] with SortAttrs_1[A, t, Ns] = _exprOneMan(Gt  , Seq(lower))
//  //  def >=   (lower: t        ): Ns[A, t] with SortAttrs_1[A, t, Ns] = _exprOneMan(Ge  , Seq(lower))
//
//
//  // todo: this set of String searchers instead - check Django ORM and other ORMs too
//
//  def startsWith(str: String): Ns with Attr = ???
//  def endsWith(str: String): Ns with Attr = ???
//  def contains(subString: String): Ns with Attr = ???
//  def containsWord(word: String, moreWords: String*): Ns with Attr = ???
//}
