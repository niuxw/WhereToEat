package com.example.accessingdatamysql;

import com.example.accessingdatamysql.model.Image;
import com.fasterxml.uuid.Generators;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import java.io.*;
import java.util.UUID;


/**
 * The Class ImageService.
 */
@Service
public class ImageService {
    
    /** The image repository. */
    @Autowired
    private ImageRepository imageRepository;

    /**
     * save image with unique filename.
     *
     * @param multipartFile image file
     * @param path relative path
     * @return relative path and filename
     * @throws IOException Signals that an I/O exception has occurred.
     */
    public static String saveImg(MultipartFile multipartFile, String path) throws IOException {
        File file = new File(path);
        if (!file.exists()) {
            file.mkdirs();
        }
        FileInputStream fileInputStream = (FileInputStream) multipartFile.getInputStream();
        //Create custom filename
        UUID uuid1 = Generators.timeBasedGenerator().generate();

        String filename = uuid1.toString()
                + "." +
                StringUtils.cleanPath(multipartFile.getOriginalFilename()).split("\\.")[1];
        //remove spaces and make lowercase
        //filename = filename.toLowerCase().replaceAll(" ", "-");

        String total_path = path + File.separator + filename;
        BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(total_path));
        byte[] bs = new byte[1024];
        int len;
        while ((len = fileInputStream.read(bs)) != -1) {
            bos.write(bs, 0, len);
        }
        bos.flush();
        bos.close();
        return total_path;
    }

    /**
     * Save to DB.
     *
     * @param username the username
     * @param placeid the placeid
     * @param path the path
     */
    public void saveToDB(String username, String placeid, String path){
        Image img = new Image();
        img.setUsername(username);
        img.setPlaceid(placeid);
        img.setPath(path);
        img.setType("unknown");
        imageRepository.save(img);
    }

    /**
     * Gets the imgs by username.
     *
     * @param username the username
     * @return the imgs by username
     */
    public Iterable<Image> getImgsByUsername(String username){
        return imageRepository.findAllByUsername(username);
    }

    /**
     * Gets the imgs by placeid.
     *
     * @param placeid the placeid
     * @return the imgs by placeid
     */
    public Iterable<Image> getImgsByPlaceid(String placeid){
        return imageRepository.findAllByPlaceid(placeid);
    }

    // following code comes from
    // https://blog.csdn.net/diyu122222/article/details/73277751
    /**
     * Encode.
     *
     * @param bytes the bytes
     * @return the string
     */
    private static String encode(byte[] bytes){
        return new BASE64Encoder().encode(bytes);
    }

    /**
     * Decode.
     *
     * @param encodeStr the encode str
     * @return the byte[]
     * @throws IOException Signals that an I/O exception has occurred.
     */
    private static byte[] decode(String encodeStr) throws IOException{
        byte[] bt = null;
        BASE64Decoder decoder = new BASE64Decoder();
        bt = decoder.decodeBuffer(encodeStr);
        return bt;
    }


    /**
     * Encode image.
     *
     * @param imgUrl the img url
     * @return the string
     * @throws IOException Signals that an I/O exception has occurred.
     */
    public static String encodeImage(String imgUrl) throws IOException{
        FileInputStream fis = new FileInputStream(imgUrl);
        byte[] rs = new byte[fis.available()];
        fis.read(rs);
        fis.close();
        return encode(rs);
    }
}
