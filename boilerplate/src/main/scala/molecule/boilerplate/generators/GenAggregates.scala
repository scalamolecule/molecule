package molecule.boilerplate.generators


object GenAggregates extends _Template("aggregates" ) {
  val content = {
    val traits = (1 to 22).map(arity => Trait(arity).body).mkString("\n")
    s"""package molecule.boilerplate.api
       |
       |import molecule.boilerplate.api.keywords._
       |$traits""".stripMargin
  }

  case class Trait(arity: Int) extends TemplateVals(arity) {
    val body   =
      s"""
         |
         |trait ${Name}Ops_$arity[${`A..N`}, t, Ns[${`_, _`}]] {
         |  protected def _aggrInt   (kw: Kw                ): Ns[${`A..M, `}Int    , Int   ] with SortAttrs_$arity[${`A..M, `}Int    , Int   , Ns]
         |  protected def _aggrDouble(kw: Kw                ): Ns[${`A..M, `}Double , Double] with SortAttrs_$arity[${`A..M, `}Double , Double, Ns]
         |  protected def _aggrList  (kw: Kw, n: Option[Int]): Ns[${`A..M, `}List[t], t     ] with SortAttrs_$arity[${`A..M, `}List[t], t     , Ns]
         |  protected def _aggrT     (kw: Kw                ): Ns[${`A..M, `}t      , t     ] with SortAttrs_$arity[${`A..M, `}t      , t     , Ns]
         |}
         |
         |trait ${Name}_$arity[${`A..N`}, t, Ns[${`_, _`}]] extends ${Name}Ops_$arity[${`A..N`}, t, Ns] {
         |  def apply(kw: count)        : Ns[${`A..M, `}Int    , Int   ] with SortAttrs_$arity[${`A..M, `}Int    , Int   , Ns] = _aggrInt(kw)
         |  def apply(kw: countDistinct): Ns[${`A..M, `}Int    , Int   ] with SortAttrs_$arity[${`A..M, `}Int    , Int   , Ns] = _aggrInt(kw)
         |  def apply(kw: min)          : Ns[${`A..M, `}t      , t     ] with SortAttrs_$arity[${`A..M, `}t      , t     , Ns] = _aggrT(kw)
         |  def apply(kw: max)          : Ns[${`A..M, `}t      , t     ] with SortAttrs_$arity[${`A..M, `}t      , t     , Ns] = _aggrT(kw)
         |  def apply(kw: rand)         : Ns[${`A..M, `}t      , t     ] with SortAttrs_$arity[${`A..M, `}t      , t     , Ns] = _aggrT(kw)
         |  def apply(kw: sample)       : Ns[${`A..M, `}t      , t     ] with SortAttrs_$arity[${`A..M, `}t      , t     , Ns] = _aggrT(kw)
         |  def apply(kw: sum)          : Ns[${`A..M, `}t      , t     ] with SortAttrs_$arity[${`A..M, `}t      , t     , Ns] = _aggrT(kw)
         |  def apply(kw: median)       : Ns[${`A..M, `}t      , t     ] with SortAttrs_$arity[${`A..M, `}t      , t     , Ns] = _aggrT(kw)
         |  def apply(kw: mins)         : Ns[${`A..M, `}List[t], t     ] with SortAttrs_$arity[${`A..M, `}List[t], t     , Ns] = _aggrList(kw, Some(kw.n))
         |  def apply(kw: maxs)         : Ns[${`A..M, `}List[t], t     ] with SortAttrs_$arity[${`A..M, `}List[t], t     , Ns] = _aggrList(kw, Some(kw.n))
         |  def apply(kw: rands)        : Ns[${`A..M, `}List[t], t     ] with SortAttrs_$arity[${`A..M, `}List[t], t     , Ns] = _aggrList(kw, Some(kw.n))
         |  def apply(kw: samples)      : Ns[${`A..M, `}List[t], t     ] with SortAttrs_$arity[${`A..M, `}List[t], t     , Ns] = _aggrList(kw, Some(kw.n))
         |  def apply(kw: distinct)     : Ns[${`A..M, `}List[t], t     ] with SortAttrs_$arity[${`A..M, `}List[t], t     , Ns] = _aggrList(kw, None)
         |  def apply(kw: avg)          : Ns[${`A..M, `}Double , Double] with SortAttrs_$arity[${`A..M, `}Double , Double, Ns] = _aggrDouble(kw)
         |  def apply(kw: variance)     : Ns[${`A..M, `}Double , Double] with SortAttrs_$arity[${`A..M, `}Double , Double, Ns] = _aggrDouble(kw)
         |  def apply(kw: stddev)       : Ns[${`A..M, `}Double , Double] with SortAttrs_$arity[${`A..M, `}Double , Double, Ns] = _aggrDouble(kw)
         |}""".stripMargin
  }
}
