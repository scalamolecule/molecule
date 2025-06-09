package molecule.graphql.client.action

import molecule.core.ast.DataModel

case class Mutate(
  dataModel: DataModel,
  private[molecule] val printInspect: Boolean = false,
  //  private[molecule] val doValidate: Boolean = true
) {

  // Inspect also
  def i: Mutate = copy(printInspect = true)
}
