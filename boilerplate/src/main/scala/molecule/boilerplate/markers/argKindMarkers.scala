package molecule.boilerplate.markers


object argKindMarkers {

  // Markers to distinguish overloaded multi-value methods
  trait ArgsKind

  // Values as varargs
  // v, v*
  class Vs extends ArgsKind

  // Collection of values
  // Seq(v, v*)
  class CVs extends ArgsKind

  // Collections as varargs
  // Set(..), Set(..)*
  // Map(..), Map(..)*
  class Cs extends ArgsKind

  // Collection of Collections
  // Seq(Set(..), Set(..)*)
  // Seq(Map(..), Map(..)*)
  class CCs extends ArgsKind
}
