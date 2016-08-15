package geotag.images

import java.io.File

import org.jcodec.api.SequenceEncoder
import scala.concurrent.duration._

/**
  * Created by martonpapp on 15/08/16.
  */
object RunImages {

  def main(args: Array[String]): Unit = {
    val videoFile = new File("target/video.mp4")

//    val encoder = new SequenceEncoder(videoFile)
//    encoder.encodeNativeFrame()
    import scala.collection.JavaConversions._

    val oml = JpegImagesToMovie.createMediaLocator(videoFile.getAbsolutePath)



    val root = new File("/Volumes/TT_CAM/DCIM/100TTCAM/")

    val imgList = root
      .listFiles()
      .filter(_.lastModified() > System.currentTimeMillis() - 1.day.toMillis)
      .to[Seq]
      .sortBy(_.lastModified())
      .filter(_.getName.endsWith(".JPG"))
      .filterNot(_.getName.startsWith("."))
      .map(_.getAbsolutePath)
//      .take(100)
    println(imgList.size)

    val itm = new JpegImagesToMovie()
    itm.doIt(1024, 768, 5, imgList, oml)





  }

}
