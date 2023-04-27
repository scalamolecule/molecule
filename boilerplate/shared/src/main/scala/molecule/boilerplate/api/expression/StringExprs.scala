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

//
//  // todo: this set of String searchers instead - check Django ORM and other ORMs too
//
//  def startsWith(str: String, caseSensitive: Boolean = false): Ns with Attr = ???
//  def endsWith(str: String): Ns with Attr = ???
//  def contains(subString: String): Ns with Attr = ???
//  def containsWord(word: String, moreWords: String*): Ns with Attr = ???
//  def regex(pattern: String): Ns with Attr = ???
//}
