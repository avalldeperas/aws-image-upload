package com.avalldeperas.awsimageupload.config;

import com.avalldeperas.awsimageupload.model.AWSCredential;
import com.avalldeperas.awsimageupload.model.AWS_KeyLabel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;

@Component
@RequiredArgsConstructor
public class CSVReader {

    @Getter @Value("classpath:files/rootkey.csv")
    private Resource resourceFile;
    private static Logger logger = LogManager.getLogger(CSVReader.class);
    @Getter
    private AWSCredential awsCredential;

    @PostConstruct
    private void init() {
        try {
            Reader in = new FileReader(resourceFile.getFile());
            Iterable<CSVRecord> records = CSVFormat.EXCEL.parse(in);
            awsCredential = new AWSCredential();
            for (CSVRecord record : records) {
                String[] split = record.get(0).split("=");
                if (split.length > 2) throw new IllegalArgumentException("CSV is not correct.");
                String key = split[0], value = split[1];
                if (AWS_KeyLabel.AWS_ACCESS_KEY_ID.equalValue(key) && record.getRecordNumber() == 1) {
                    awsCredential.setAWSAccessKeyId(value);
                } else if (AWS_KeyLabel.AWS_SECRET_KEY.equalValue(key) && record.getRecordNumber() == 2) {
                    awsCredential.setAWSSecretKey(value);
                } else {
                    throw new IllegalArgumentException("CSV is not correct.");
                }
            }
        } catch (IOException | IllegalArgumentException e) {
            logger.error("Error: " + e.getMessage());
        }
    }
}
