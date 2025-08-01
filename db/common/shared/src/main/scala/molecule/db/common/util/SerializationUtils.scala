package molecule.db.common.util

import java.nio.ByteBuffer


trait SerializationUtils {

  // Drop non-used bytes of internal array initialized with size 512
  extension (byteBuffer: ByteBuffer) {
    def toArray: Array[Byte] = {
      val length    = byteBuffer.remaining()
      val byteArray = Array.ofDim[Byte](length)
      System.arraycopy(byteBuffer.array(), 0, byteArray, 0, length)
      byteArray
    }
  }
}
