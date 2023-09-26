package molecule.core.marshalling

case class Reserved(
  hasAnyReserved: Boolean,
  reservedNss: Array[Boolean],
  nssWithReservedAttrs: Array[Boolean],
  reservedAttrs: Array[Boolean],
)
