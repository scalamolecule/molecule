package molecule.boilerplate.generators

object GenSortAttrs extends _Template("sortAttrs") {
  val content = {
    val traits = (1 to 22).map(arity => Trait(arity).body).mkString("\n")
    s"""package molecule.boilerplate.api
       |
       |$traits""".stripMargin
  }

  case class Trait(arity: Int) extends TemplateVals(arity) {
    val body   =
      s"""
         |trait ${Name}Ops_$arity[${`A..N`}, t, Ns[${`_, _`}]] {
         |  protected def _addSort(sort: String): Ns[${`A..N`}, t]
         |}
         |trait ${Name}_$arity[${`A..N`}, t, Ns[${`_, _`}]] extends ${Name}Ops_$arity[${`A..N`}, t, Ns] {
         |  def a1: Ns[${`A..N`}, t] = _addSort("a1")
         |  def a2: Ns[${`A..N`}, t] = _addSort("a2")
         |  def a3: Ns[${`A..N`}, t] = _addSort("a3")
         |  def a4: Ns[${`A..N`}, t] = _addSort("a4")
         |  def a5: Ns[${`A..N`}, t] = _addSort("a5")
         |  def d1: Ns[${`A..N`}, t] = _addSort("d1")
         |  def d2: Ns[${`A..N`}, t] = _addSort("d2")
         |  def d3: Ns[${`A..N`}, t] = _addSort("d3")
         |  def d4: Ns[${`A..N`}, t] = _addSort("d4")
         |  def d5: Ns[${`A..N`}, t] = _addSort("d5")
         |}""".stripMargin
    }
  }
