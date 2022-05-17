# VideoCutPic
used java implements cut picture for frame of video.
# pom.xml
```xml
  <dependency>
      <groupId>org.bytedeco</groupId>
      <artifactId>javacv-platform</artifactId>
      <version>1.5.7</version>
    </dependency>
    <dependency>
      <groupId>org.bytedeco.javacpp-presets</groupId>
      <artifactId>ffmpeg</artifactId>
      <version>4.0.2-1.4.3</version>
      <classifier>windows-x86_64</classifier>
    </dependency>
```
# Demo
```java
    /**
     * @Description:  传入文件全路径名称,实现帧截图操作.
     * @Param: [FileURL]
     * @return: boolean
     */
    public static boolean VideoCutPic(String FileURL) throws Exception {
        FFmpegFrameGrabber fFmpegFrameGrabber = new FFmpegFrameGrabber(FileURL);
        fFmpegFrameGrabber.start();
        Frame frame = null;
        /**
         *  获取视频总帧数
         */
        int frames = fFmpegFrameGrabber.getLengthInFrames();
        int i = 0;
        while (i<frames){
            frame = fFmpegFrameGrabber.grabImage();
            /**
             *  可添加条件,获取指定帧数或者指定某一帧
             */
            if (frame != null) {
                String fileName = "\\path\\"+System.currentTimeMillis()+"formatName";
                File file = new File(fileName);
                /**
                 *  图片输出
                 */
                Java2DFrameConverter converter = new Java2DFrameConverter();
                BufferedImage image = converter.getBufferedImage(frame);
                ImageIO.write(image,"jpg",file);
            }
            i++;
        }
        fFmpegFrameGrabber.stop();
        if (fFmpegFrameGrabber!=null) {
            fFmpegFrameGrabber.close();
            System.out.println("output : "+i);
            return true;
        }
        return false;
    }
``` 
