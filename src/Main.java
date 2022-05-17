import org.bytedeco.javacv.FFmpegFrameGrabber;
import org.bytedeco.javacv.Frame;
import org.bytedeco.javacv.Java2DFrameConverter;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;

/**
 * @description:
 * @author: SorDomS
 * @time: 2022/5/17 8:52
 */
public class Main {

    /**
     * @Description:  �����ļ�ȫ·������,ʵ��֡��ͼ����.
     * @Param: [FileURL]
     * @return: boolean
     * @Author: SurDowney
     * @Date: 2022/5/17 13:56
     */
    public static boolean VideoCutPic(String FileURL) throws Exception {
        FFmpegFrameGrabber fFmpegFrameGrabber = new FFmpegFrameGrabber(FileURL);
        fFmpegFrameGrabber.start();
        Frame frame = null;
        /**
         *  ��ȡ��Ƶ��֡��
         */
        int frames = fFmpegFrameGrabber.getLengthInFrames();
        int i = 0;
        while (i<frames){
            frame = fFmpegFrameGrabber.grabImage();
            /**
             *  ���������,��ȡָ��֡������ָ��ĳһ֡
             */
            if (frame != null) {
                String fileName = "D:\\Video\\"+System.currentTimeMillis()+".jpg";
                File file = new File(fileName);
                /**
                 *  ͼƬ���
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

    public static void main(String[] args) throws Exception {
        String url = "D:\\Video\\Demo.mp4";
        System.out.println(VideoCutPic(url));
    }
}
