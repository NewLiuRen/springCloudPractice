package com.leyou.service.impl;

import com.leyou.service.IUploadService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

@Service
public class UploadService implements IUploadService {
    private final List<String> CONTENT_TYPES = Arrays.asList("image/jpeg", "image/gif");

    private final Logger logger = LoggerFactory.getLogger(UploadService.class);

    @Override
    public String upload(MultipartFile file) {
        // 获取名称
        String originalFilename = file.getOriginalFilename();
        // 获取文件类型
        String contentType = file.getContentType();

        if (!CONTENT_TYPES.contains(contentType)) {
            logger.info("文件类型不合法：{}", originalFilename);
            return null;
        }

        try {
            BufferedImage bufferedImage = ImageIO.read(file.getInputStream());

            if (bufferedImage == null) {
                logger.info("文件内容不合法：{}", originalFilename);
                return null;
            }

            file.transferTo(new File("E:\\IDEAProject\\leyou\\images\\" + originalFilename));

            return "http://image.leyou.com/" + originalFilename;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
