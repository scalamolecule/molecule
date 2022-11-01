package molecule.boilerplate.api.expression

trait ExprBase {
  type X = DummyImplicit

  protected def abs2sets[t](pairs: Seq[(t, t)]): Seq[Set[t]] = {
    val (from, to) = pairs.unzip
    Seq(from.toSet, to.toSet)
  }
}
