//package molecule.core.marshalling
//import molecule.boilerplate.ast.Model._
//
//abstract class DataTransfer[Data](elements: Seq[Element], data: Data) {
//
//  /**
//   * Pack typed data into model for wiring
//   * @return model / List of indicies for each nested level
//   */
//  def pack: (Seq[Element], List[List[Int]])
//
//  /**
//   * Unpack typed data from model to List of expected tuples
//   *
//   * @tparam Tpl
//   * @return
//   */
//  def unpack[Tpl]: List[Tpl]
//}
