package com.hmm.hmm.application.component;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Slf4j
@RequiredArgsConstructor
@Component
public class S3Uploader {

    private final AmazonS3 amazonS3;

    private String directory = "/image/board/qna";

    @Value("${aws.s3.bucket}")
    private String bucket;

    @Value("${aws.s3.url}")
    private String imageUrl;


    public List<String> upload(MultipartFile[] files, long boardId) throws IOException {

        List<String> urlList = new ArrayList<>();

        String bucketDir = bucket + directory;
        ObjectMetadata omd = new ObjectMetadata();

        for(MultipartFile file : files) {
            String fileName = boardId + "_" + UUID.randomUUID().toString() + ".png";
            omd.setContentType(file.getContentType());
            omd.setContentLength(file.getSize());
            omd.setHeader("filename", fileName);

            amazonS3.putObject(new PutObjectRequest(bucketDir, fileName, file.getInputStream(), omd)
                    .withCannedAcl(CannedAccessControlList.PublicRead));

            urlList.add(imageUrl + directory + "/" + fileName);
        }

        return urlList;
    }
}
