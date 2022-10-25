package codegen.boilerplate.api

import codegen.BoilerplateGenBase

object _Aggregates extends BoilerplateGenBase("AggregatesX", "/api") {
  val content = {
    val traits = (1 to 22).map(arity => Trait(arity).body).mkString("\n")
    s"""// GENERATED CODE ********************************
       |molecule.boilerplate.api
       |
       |import molecule.boilerplate.api.Keywords._
       |$traits""".stripMargin
  }

  case class Trait(arity: Int) extends TemplateVals(arity) {
    val body =
      s"""
         |
         |trait ${fileName}Ops_$arity[${`A..V`}, t, Ns[${`_, _`}]] {
         |  protected def _aggrInt   (kw: Kw                ): Ns[${`A..U, `}Int    , Int   ] with SortAttrs_$arity[${`A..U, `}Int    , Int   , Ns]
         |  protected def _aggrDouble(kw: Kw                ): Ns[${`A..U, `}Double , Double] with SortAttrs_$arity[${`A..U, `}Double , Double, Ns]
         |  protected def _aggrList  (kw: Kw, n: Option[Int]): Ns[${`A..U, `}List[t], t     ] with SortAttrs_$arity[${`A..U, `}List[t], t     , Ns]
         |  protected def _aggrT     (kw: Kw                ): Ns[${`A..U, `}t      , t     ] with SortAttrs_$arity[${`A..U, `}t      , t     , Ns]
         |}
         |
         |trait ${fileName}_$arity[${`A..V`}, t, Ns[${`_, _`}]] extends ${fileName}Ops_$arity[${`A..V`}, t, Ns] {
         |  def apply(kw: count)        : Ns[${`A..U, `}Int    , Int   ] with SortAttrs_$arity[${`A..U, `}Int    , Int   , Ns] = _aggrInt(kw)
         |  def apply(kw: countDistinct): Ns[${`A..U, `}Int    , Int   ] with SortAttrs_$arity[${`A..U, `}Int    , Int   , Ns] = _aggrInt(kw)
         |  def apply(kw: min)          : Ns[${`A..U, `}t      , t     ] with SortAttrs_$arity[${`A..U, `}t      , t     , Ns] = _aggrT(kw)
         |  def apply(kw: max)          : Ns[${`A..U, `}t      , t     ] with SortAttrs_$arity[${`A..U, `}t      , t     , Ns] = _aggrT(kw)
         |  def apply(kw: rand)         : Ns[${`A..U, `}t      , t     ] with SortAttrs_$arity[${`A..U, `}t      , t     , Ns] = _aggrT(kw)
         |  def apply(kw: sample)       : Ns[${`A..U, `}t      , t     ] with SortAttrs_$arity[${`A..U, `}t      , t     , Ns] = _aggrT(kw)
         |  def apply(kw: sum)          : Ns[${`A..U, `}t      , t     ] with SortAttrs_$arity[${`A..U, `}t      , t     , Ns] = _aggrT(kw)
         |  def apply(kw: median)       : Ns[${`A..U, `}t      , t     ] with SortAttrs_$arity[${`A..U, `}t      , t     , Ns] = _aggrT(kw)
         |  def apply(kw: mins)         : Ns[${`A..U, `}List[t], t     ] with SortAttrs_$arity[${`A..U, `}List[t], t     , Ns] = _aggrList(kw, Some(kw.n))
         |  def apply(kw: maxs)         : Ns[${`A..U, `}List[t], t     ] with SortAttrs_$arity[${`A..U, `}List[t], t     , Ns] = _aggrList(kw, Some(kw.n))
         |  def apply(kw: rands)        : Ns[${`A..U, `}List[t], t     ] with SortAttrs_$arity[${`A..U, `}List[t], t     , Ns] = _aggrList(kw, Some(kw.n))
         |  def apply(kw: samples)      : Ns[${`A..U, `}List[t], t     ] with SortAttrs_$arity[${`A..U, `}List[t], t     , Ns] = _aggrList(kw, Some(kw.n))
         |  def apply(kw: distinct)     : Ns[${`A..U, `}List[t], t     ] with SortAttrs_$arity[${`A..U, `}List[t], t     , Ns] = _aggrList(kw, None)
         |  def apply(kw: avg)          : Ns[${`A..U, `}Double , Double] with SortAttrs_$arity[${`A..U, `}Double , Double, Ns] = _aggrDouble(kw)
         |  def apply(kw: variance)     : Ns[${`A..U, `}Double , Double] with SortAttrs_$arity[${`A..U, `}Double , Double, Ns] = _aggrDouble(kw)
         |  def apply(kw: stddev)       : Ns[${`A..U, `}Double , Double] with SortAttrs_$arity[${`A..U, `}Double , Double, Ns] = _aggrDouble(kw)
         |}""".stripMargin
  }
}
