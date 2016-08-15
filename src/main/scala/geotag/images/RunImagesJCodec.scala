package geotag.images

import java.awt.image.BufferedImage
import javax.imageio.ImageIO

import org.jcodec.api.awt.SequenceEncoder

/**
  * Created by martonpapp on 15/08/16.
  */
object RunImagesJCodec {

  def main(args: Array[String]): Unit = {
    val enc = new SequenceEncoder(RunImages.videoFile)

    RunImages.imgList
//      .take(10)
      .zipWithIndex
      .foreach {
        case (f, idx) =>
          println(s"${f} (${idx+1} / ${RunImages.imgList.size})")
          val image = ImageIO.read(f)
          enc.encodeImage(image)
      }
    enc.finish()

  }

}
